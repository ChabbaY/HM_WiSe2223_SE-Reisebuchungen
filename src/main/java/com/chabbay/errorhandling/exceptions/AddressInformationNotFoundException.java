package com.chabbay.errorhandling.exceptions;

/**
 * exception that will be thrown if id doesn't exist -> will lead to 404
 *
 * @author Linus Englert
 */
public class AddressInformationNotFoundException extends RuntimeException {
    public AddressInformationNotFoundException(Long id) {
        super("Could not find AddressInformation " + id + "❗");
    }
}
