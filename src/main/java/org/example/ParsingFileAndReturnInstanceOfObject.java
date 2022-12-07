package org.example;

import org.example.exceptions.NotFoundAnnotationFormat;
import org.example.exceptions.ParsingException;
import org.example.exceptions.WrongPropertyException;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ParsingFileAndReturnInstanceOfObject {
    public static <T> T loadFromProperties(Class<T> cls, Path propertyPath) throws InstantiationException, IllegalAccessException, IOException {
        T c = cls.newInstance();
        Map<String, String> propertySet = new HashMap<>();

        //Зчитуємо in.property в мапу
        Scanner scan = new Scanner(propertyPath.getFileName());
        while (scan.hasNext()) {
            String[] s = scan.nextLine().split("=");
            propertySet.put(s[0], s[1]);
        }
        scan.close();

        //отримуємо всі поля з класу
        Field[] fields = c.getClass().getDeclaredFields();

        //Парсимо in.property на філди класу
        for (Map.Entry<String, String> entry : propertySet.entrySet()) {
            for (Field f : fields) {
                if (f.isAnnotationPresent(Property.class)) {
                    if (entry.getKey().equals(f.getAnnotation(Property.class).name())) {
                        parseProperty(c, entry, f);
                    }
                }
                if (entry.getKey().equals(f.getName())) {
                    parseProperty(c, entry, f);
                }
            }
        }
        return c;
    }

    private static <T> void parseProperty(T c, Map.Entry<String, String> entry, Field f) {
        switch (f.getType().getName()) {
            case "java.lang.String":
                invokeSetter(c, f.getName(), entry.getValue());
                break;
            case "int":
            case "java.lang.Integer":
                try {
                    invokeSetter(c, f.getName(), Integer.parseInt(entry.getValue()));
                } catch (NumberFormatException e) {
                    throw new WrongPropertyException("Can't cast property to " + f.getType().getName() + " field. " + "Property:" + entry.getKey());
                }
                break;
            case "java.time.Instant":
                try {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(f.getAnnotation(Property.class).format());
                    LocalDateTime localDateTime = LocalDateTime.parse(entry.getValue(), dateTimeFormatter);
                    invokeSetter(c, f.getName(), localDateTime.toInstant(ZoneOffset.UTC));
                } catch (DateTimeException e) {
                    throw new ParsingException("Couldn't parse property: " + entry.getKey() + "=" + entry.getValue() + " to field annotation: format=" + f.getAnnotation(Property.class).format());
                } catch (NullPointerException e) {
                    throw new NotFoundAnnotationFormat("Required Property annotation 'format' by field " + f.getName());
                }
                break;
        }
    }

    private static void invokeSetter(Object obj, String propertyName, Object variableValue) {
        PropertyDescriptor pd;
        try {
            pd = new PropertyDescriptor(propertyName, obj.getClass());
            Method setter = pd.getWriteMethod();
            try {
                setter.invoke(obj, variableValue);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }
}
