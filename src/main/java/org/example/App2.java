package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App2 {
    public static void main(String[] args) throws NoSuchFieldException, InstantiationException, IllegalAccessException, IOException {

        Path path = Paths.get("\\in.property");
        System.out.println(path.getFileName());
        Person p = ParsingFileAndReturnInstanceOfObject.loadFromProperties(Person.class, path);
//        System.out.println(p.getName());
    }
}
