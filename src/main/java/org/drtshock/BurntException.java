package org.drtshock;

/**
 * An exception to describe that something went wrong with our oven!
 */
public class BurntException extends Exception {

    public BurntException(int degrees) {
        super("Potato was badly burnt from trying to boil it at " + degrees + " degrees!!");
    }

    public BurntException(long bakeTime) {
        super("Potato was badly burnt from baking too long!! (" + bakeTime + "ms)");
    }

}
