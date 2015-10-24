package org.drtshock;

public class NotDeliciousException extends Exception {

    public NotDeliciousException() {
    }

    public NotDeliciousException(String message) {
        super(message);
    }

    public NotDeliciousException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotDeliciousException(Throwable cause) {
        super(cause);
    }

    public NotDeliciousException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
