package org.drtshock.exceptions;

/**
 * An exception to describe that something went wrong with our oven!
 */
public class BurntException extends Exception {
    private final NotDeliciousReason cause;

    public BurntException(int degrees, NotDeliciousReason cause) {
        super("Potato is badly burnt by trying to boil it at " + degrees + " degrees!!");

        this.cause = cause;
    }

    public BurntException(double bakeTime, NotDeliciousReason cause) {
        super("Potato is badly burnt by baking for too long!! (" + bakeTime + "ms)");
        this.cause = cause;
    }

    public NotDeliciousReason getReason() {
        return this.cause;
    }
}
