package org.example;


import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ParsingFileAndReturnInstanceOfObject {
    public static <T> T loadFromProperties(Class<T> cls, Path propertyPath) throws InstantiationException, IllegalAccessException, NoSuchFieldException, IOException {
        T c = cls.newInstance();
        String stringProperty;
        int numberProperty;

        Map<String,String> propertySet = new HashMap<>();

        Scanner scan = new Scanner(propertyPath.getFileName());
        while (scan.hasNext()){
            String[] s = scan.next().split("=");
            propertySet.put(s[0],s[1]);
        }
        System.out.println(propertySet);
        scan.close();

        //отримуємо всі поля з класу
        Field[] fields = c.getClass().getDeclaredFields();
        //робимо мапу з назвами полів і відповідними аннотаціями
        Map<String, String> fieldsAnnotations = new HashMap<>();
        for (Field f : fields
        ) {
            Annotation a = f.getAnnotation(Property.class);
            if (a != null) {
                Property anotationName = (Property) a;
                System.out.println(anotationName.name());
                fieldsAnnotations.put(f.getName(), anotationName.name());
            } else fieldsAnnotations.put(f.getName(), "");
        }
        System.out.println(fieldsAnnotations);



//        Field field = c.getClass().getDeclaredField("name");
//        field.setAccessible(true);
//        field.set(c, name);
        return c;
    }
}
