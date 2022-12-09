package org.example.exceptions;

public class NotFoundAnnotationFormatException extends NullPointerException {
    public NotFoundAnnotationFormatException(String errorMessage) {
        super(errorMessage);
    }
}
