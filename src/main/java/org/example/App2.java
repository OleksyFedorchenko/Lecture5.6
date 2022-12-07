package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App2 {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException {
        Path path = Paths.get("\\in.property");
        Person p = ParsingFileAndReturnInstanceOfObject.loadFromProperties(Person.class, path);
        System.out.println(p);
    }
}
