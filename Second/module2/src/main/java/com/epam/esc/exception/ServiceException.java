package com.epam.esc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


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
