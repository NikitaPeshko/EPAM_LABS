package com.epam.esc.exception;

public class NoEntityException extends Exception{

    private String code;

    public NoEntityException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getErrorcode() {
        return code;
    }
}
