package com.chabbay.errorhandling.advice;

import com.chabbay.errorhandling.exceptions.TimezoneNotFoundException;
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
public class TimezoneNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(TimezoneNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String hotelNotFoundHandler(TimezoneNotFoundException e) {
        if (e == null) return "";
        return e.getMessage();
    }
}