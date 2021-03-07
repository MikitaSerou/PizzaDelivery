package org.study.PizzaDelivery.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class AdviceController {

    private static final Logger logger = LogManager.getLogger(AdviceController.class);


    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public String Handler404(Exception ex, Model model) {
        logger.error("NoHandlerFoundException " + ex.getMessage());
        model.addAttribute("ex",ex.getClass().getSimpleName());

        return "error/404";
    }

    @ExceptionHandler(ConversionNotSupportedException.class)
    public String Handler500(Exception ex, Model model) {
        logger.error("ConversionNotSupportedException " + ex.getMessage());
        model.addAttribute("ex",ex.getClass().getSimpleName());

        return "error/500";
    }

    @ExceptionHandler(value = {TypeMismatchException.class})
    public String Handler400(Exception ex, Model model) {
        logger.error("TypeMismatchException " + ex.getMessage());
        model.addAttribute("ex",ex.getClass().getSimpleName());

        return "error/400";
    }
}
