package org.drtshock;

public class OvenException extends Exception {

    public OvenException() {
    }

    public OvenException(String message) {
        super(message);
    }

    public OvenException(String message, Throwable cause) {
        super(message, cause);
    }

    public OvenException(Throwable cause) {
        super(cause);
    }

    public OvenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
