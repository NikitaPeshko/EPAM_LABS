package com.epam.esc.exception;

public class NoEntityException extends RuntimeException{
    private String message;
    private String code;



    public NoEntityException(String message) {
        super(message);
    }
}
