package com.chabbay.errorhandling.exceptions;

/**
 * exception that will be thrown if id doesn't exist -> will lead to 404
 *
 * @author Linus Englert
 */
public class TimezoneNotFoundException extends RuntimeException {
    public TimezoneNotFoundException(Long id) {
        super("Could not find Timezone " + id + "❗");
    }
}