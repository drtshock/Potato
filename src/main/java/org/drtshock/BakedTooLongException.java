package org.drtshock;

/**
 * An exception to describe that our potato was baked too long!
 */
public class BakedTooLongException extends BurntException {

    public BakedTooLongException(long bakeTime) {
        super("Potato is badly burnt by baking for too long!! (" + bakeTime + "ms)");
    }

}
