package org.drtshock;

/**
 * An exception to describe that something went wrong with our oven!
 */
public class BurntException extends Exception {

    private static final long serialVersionUID = 1L;

    public BurntException(int degrees) {
        super("Potato is badly burnt by trying to boil it at " + degrees + " degrees!!");
    }

    public BurntException(long bakeTime) {
        super("Potato is badly burnt by baking for too long!! (" + bakeTime + "ms)");
    }

}
