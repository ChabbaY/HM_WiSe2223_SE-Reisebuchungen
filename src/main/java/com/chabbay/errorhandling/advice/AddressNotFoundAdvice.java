package com.chabbay.errorhandling.advice;

import com.chabbay.errorhandling.exceptions.AddressNotFoundException;
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
public class AddressNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(AddressNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String hotelNotFoundHandler(AddressNotFoundException e) {
        if (e == null) return "";
        return e.getMessage();
    }
}