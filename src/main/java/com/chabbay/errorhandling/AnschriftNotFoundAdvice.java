package com.chabbay.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * handles an exception
 *
 * @author Linus Englert
 */
@ControllerAdvice
public class AnschriftNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(AnschriftNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String anschriftNotFoundHandler(AnschriftNotFoundException e) {
        if (e == null) return "";
        return e.getMessage();
    }
}