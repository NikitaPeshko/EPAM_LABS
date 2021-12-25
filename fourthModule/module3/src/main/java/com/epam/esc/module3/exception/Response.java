package com.epam.esc.module3.exception;

public class Response {
    private String message;
    private String errCode;


    public Response() {
    }

    public Response(String message, String errCode) {
        this.message = message;
        this.errCode = errCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
}
