package org.example;

import org.example.exceptions.WrongPropertyFileException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App2 {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException, WrongPropertyFileException {
        Path path = Paths.get("\\in.properties");
        Person p = ParsingFileAndReturnInstanceOfObject.loadFromProperties(Person.class, path);
        System.out.println(p);
    }
}
