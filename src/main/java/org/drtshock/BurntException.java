package org.drtshock;

/**
 * An exception to describe that our potato was burnt!
 */
public class BurntException extends Exception {

    public BurntException() {
        super("Potato is badly burnt!!");
    }

    public BurntException(String reason) {
        super(reason);
    }

}
