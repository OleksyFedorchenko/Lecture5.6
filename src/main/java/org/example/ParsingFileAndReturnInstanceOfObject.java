package org.example;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Scanner;

public class ParsingFileAndReturnInstanceOfObject {
    public static <T> T loadFromProperties(Class<T> cls, Path propertyPath) throws InstantiationException, IllegalAccessException, NoSuchFieldException, IOException {
        T c = cls.newInstance();
        String stringProperty;
        int numberProperty;
        Instant timeProperty;



        Field field = c.getClass().getDeclaredField("name");
        field.setAccessible(true);
//        field.set(c, name);
        return c;
    }
}
