package com.example.springboot.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

@RestControllerAdvice
public class ExceptionEntryPointHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public void commence(HttpServletRequest _request, HttpServletResponse response,
                         EntityNotFoundException exception) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "NOT FOUND : " + exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void commence(HttpServletRequest _request, HttpServletResponse response,
                         IllegalArgumentException exception) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Illegal argument : " + exception.getMessage());
    }
}
