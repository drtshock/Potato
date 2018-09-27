package org.drtshock;

/**
 * An exception to describe that while cooking the potato, it was burnt due to being cooked for too long.
 */
public class BurntTimeException extends BurntException {

    private final long bakeTime;

    /**
     * @param bakeTime The time the potato was baked, in milliseconds.
     */
    public BurntTimeException(long bakeTime) {
        super("Potato is badly burnt by baking for too long!! (" + bakeTime + "ms)");
        this.bakeTime = bakeTime;
    }

    /**
     * @return The time the potato was baked in milliseconds.
     */
    public long getBakeTime() {
        return bakeTime;
    }

}
