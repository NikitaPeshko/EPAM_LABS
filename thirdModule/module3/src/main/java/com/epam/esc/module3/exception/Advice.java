package com.epam.esc.module3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Advice {

    @ExceptionHandler(NoEntityException.class)
    public ResponseEntity<Response> handleException(NoEntityException e) {
        Response response = new Response(e.getMessage(),e.getErrorcode());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @ExceptionHandler(DAOException.class)
    public ResponseEntity<Response> daoException(DAOException e) {
        Response response = new Response(e.getMessage(),e.getErrorcode());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Response> daoException(ServiceException e) {
        Response response = new Response(e.getMessage(),e.getErrorcode());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
