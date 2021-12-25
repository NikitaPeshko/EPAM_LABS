package com.epam.esc.module3.exception;

public class ServiceException extends Exception{
    private String errorcode;

    public ServiceException(String message, String errorcode) {
        super(message);
        this.errorcode = errorcode;
    }

    public String getErrorcode() {
        return errorcode;
    }
}
