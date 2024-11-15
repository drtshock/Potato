package org.drtshock.exceptions;

/**
 * An exception to describe that something went wrong with our oven!
 */
public class OvenException extends Exception {

    public OvenException(Exception internalException) {
        super(internalException);
    }

}
