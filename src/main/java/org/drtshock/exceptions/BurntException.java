package org.drtshock.exceptions;

/**
 * An exception to describe that something went wrong with our oven!
 */
public class BurntException extends Exception {

    private int temperature;
    private long bakeTime;

    public BurntException(int degrees) {
        super("Potato is badly burnt by trying to boil it at " + degrees + " degrees!!");
        this.temperature = degrees;
    }

    public BurntException(long bakeTime) {
        super("Potato is badly burnt by baking for too long!! (" + bakeTime + "ms)");
        this.bakeTime = bakeTime;
    }

    public int getTemperature() {
        return temperature;
    }

    public long getBakeTime() {
        return bakeTime;
    }

}
