package org.example;

import org.example.exceptions.WrongPropertyException;
import org.example.exceptions.WrongPropertyFileException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */

    @Test
    public void testTypeOfInstance() throws IOException, InstantiationException, IllegalAccessException, WrongPropertyFileException {
        Path path = Paths.get("\\in.properties");
        String expected = Person.class.getTypeName();
        assertEquals(expected, ParsingFileAndReturnInstanceOfObject.loadFromProperties(Person.class, path).getClass().getTypeName());
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


    @Test
    public void testWrongProperty() throws IllegalAccessException, IOException, InstantiationException, WrongPropertyFileException {
        Path path = Paths.get("\\wrong.properties");
        exceptionRule.expect(WrongPropertyException.class);
        ParsingFileAndReturnInstanceOfObject.loadFromProperties(Person.class, path);
    }
}
