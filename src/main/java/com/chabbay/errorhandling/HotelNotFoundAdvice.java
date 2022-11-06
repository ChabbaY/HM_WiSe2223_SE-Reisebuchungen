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
public class HotelNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(HotelNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String hotelNotFoundHandler(HotelNotFoundException e) {
        if (e == null) return "";
        return e.getMessage();
    }
}