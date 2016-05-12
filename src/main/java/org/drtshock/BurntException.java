package org.drtshock;

/**
 * An exception to describe that something went wrong with our oven!
 */
public class BurntException extends Exception {

    public BurntException(int degrees) {
        super("Potato is badly burnt by trying to boil it at " + degrees + " degrees!!");
    }

}
