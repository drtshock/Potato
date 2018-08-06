package org.drtshock;

/**
 * An exception to describe that something went wrong with our oven!
 */
public class BurntException extends Exception {

    public BurntException(int waterDegreesFahrenheit, int waterDegreesCelsius) {
        super("Potato is badly burnt by trying to boil it at (F:" + waterDegreesFahrenheit + ") (C:" + waterDegreesCelsius + ") degrees!!");
    }

    public BurntException(long bakeTime) {
        super("Potato is badly burnt by baking for too long!! (" + bakeTime + "ms)");
    }

}
