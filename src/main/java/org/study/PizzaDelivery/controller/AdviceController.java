package org.study.PizzaDelivery.controller;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String NoHandlerFoundExceptionHandler(Exception ex) {

        return "error/404";
    }

    @ExceptionHandler(ConversionNotSupportedException.class)
    public String ConversionNotSupportedExceptionHandler(Exception ex) {

        return "error/500";
    }
}
