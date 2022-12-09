package org.example.exceptions;

public class WrongPropertyFileException extends Exception{
    public WrongPropertyFileException(String errormessage){
        super(errormessage);
    }
}
