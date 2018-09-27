package org.drtshock;

/**
 * An exception which describes that the oven burnt the potato via too high of temperature
 */
public class BurntTemperatureException extends BurntException {

    private final int degrees;

    /**
     * @param degrees The temperature at which the potato was cooked too hot and caused burns
     */
    public BurntTemperatureException(int degrees) {
        super("Potato is badly burnt by trying to boil it at " + degrees + " degrees!!");
        this.degrees = degrees;
    }

    /**
     * @return The temperature at which the potato was cooked too hot
     */
    public int getDegrees() {
        return degrees;
    }

}
