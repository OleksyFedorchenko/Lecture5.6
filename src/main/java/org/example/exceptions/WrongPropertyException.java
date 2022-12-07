package org.example.exceptions;

public class WrongPropertyException extends NumberFormatException {
    public WrongPropertyException(String errorMessage) {
        super(errorMessage);
    }
}

