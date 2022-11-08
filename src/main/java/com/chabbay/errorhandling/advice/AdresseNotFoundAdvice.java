package com.chabbay.errorhandling.advice;

import com.chabbay.errorhandling.exceptions.AdresseNotFoundException;
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
public class AdresseNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(AdresseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String hotelNotFoundHandler(AdresseNotFoundException e) {
        if (e == null) return "";
        return e.getMessage();
    }
}