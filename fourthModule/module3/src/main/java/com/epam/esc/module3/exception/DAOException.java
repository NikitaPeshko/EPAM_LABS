package com.epam.esc.module3.exception;

public class DAOException extends Exception{
    private String errorcode;

    public DAOException(String message, String errorcode) {
        super(message);
        this.errorcode = errorcode;
    }

    public String getErrorcode() {
        return errorcode;
    }

}
