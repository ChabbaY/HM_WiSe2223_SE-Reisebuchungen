package com.chabbay.errorhandling;

/**
 * exception that will be thrown if id doesn't exist -> will lead to 404
 *
 * @author Linus Englert
 */
public class HotelNotFoundException extends RuntimeException {
    public HotelNotFoundException(Long id) {
        super("Could not find Hotel " + id);
    }
}