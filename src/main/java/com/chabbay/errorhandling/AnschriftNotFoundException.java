package com.chabbay.errorhandling;

/**
 * exception that will be thrown if id doesn't exist -> will lead to 404
 *
 * @author Linus Englert
 */
public class AnschriftNotFoundException extends RuntimeException {
    public AnschriftNotFoundException(Long id) {
        super("Could not find Hotel " + id);
    }
}
