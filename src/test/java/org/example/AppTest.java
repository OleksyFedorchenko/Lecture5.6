package org.example;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void InputPathIsNull() throws IllegalAccessException, IOException, InstantiationException {
        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage("Input data cannot be NULL");
        ParsingFileAndReturnInstanceOfObject.loadFromProperties(Person.class,null);
    }
}
