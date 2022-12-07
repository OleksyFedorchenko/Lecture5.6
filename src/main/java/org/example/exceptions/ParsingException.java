package org.example.exceptions;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

public class ParsingException extends DateTimeException {
    public ParsingException(String errorMessage) {
        super(errorMessage);
    }
}
