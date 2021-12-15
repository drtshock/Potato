package org.drtshock.api.condiments;

import org.drtshock.api.DelectableItem;

/**
 * A type of food added to DelectableItems.
 */
public class Condiment implements DelectableItem, CanExpire {
    private final String name;
    private final boolean delicious;
    private final boolean expired;
    private final boolean isVegan;

    public Condiment(String name, boolean delicious, boolean isVegan, boolean expired) {
        this.name = name;
        this.delicious = delicious;
        this.expired = expired;
        this.isVegan = isVegan;
    }

    public Condiment(String name, boolean delicious, boolean isVegan) {
        this(name, delicious, isVegan, Math.random() * 100 < 3);
    }

    /**
     * Returns if this condiment is delicious or not.
     *
     * @return true if delicious, false if otherwise
     */
    @Override
    public boolean isDelicious() {
        return this.delicious;
    }

    /**
     * Returns if this condiment is expired or not.
     *
     * @return true if expired, false if otherwise
     */
    public boolean isExpired() {
        return expired;
    }

    /**
     * Returns if this condiment is vegan or not.
     *
     * @return true if vegan, false if otherwise
     */
    public boolean isVegan() {
        return isVegan;
    }

    /**
     * Gets the name of this condiment.
     *
     * @return Name
     */
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Condiment{" +
                "name='" + name + '\'' +
                ", delicious=" + delicious +
                ", expired=" + expired +
                ", isVegan=" + isVegan +
                "}\n";
    }
}
