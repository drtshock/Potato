package org.drtshock;

/**
 * An exception to describe that something went wrong with our oven!
 */
public class BurntException extends Exception {

    public BurntException(String message) {
        super(message);
    }

    public BurntException(String message, Throwable cause) {
        super(message, cause);
    }

    public BurntException(Throwable cause) {
        super(cause);
    }

}
