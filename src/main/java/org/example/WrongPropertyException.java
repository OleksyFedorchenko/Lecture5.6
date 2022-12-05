package org.example;

public class WrongPropertyException extends NumberFormatException {
    public WrongPropertyException(String errorMessage) {
        super(errorMessage);
    }
}

