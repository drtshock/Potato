package org.drtshock;

/**
 * An exception to describe that our potato was boiled too hot!
 */
public class BoiledTooHotException extends BurntException {

    public BoiledTooHotException(int degrees) {
        super("Potato is badly burnt by trying to boil it at " + degrees + " degrees!!");
    }

}
