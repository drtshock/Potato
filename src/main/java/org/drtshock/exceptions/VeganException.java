package org.drtshock.exceptions;

import org.drtshock.api.condiments.Condiment;

public class VeganException extends Exception {
    private String msg;

    public VeganException(Condiment condiment) {
        this.msg = "condiment name: " + condiment.getName() + ", vegan: " + condiment.isVegan() + "\nA non vegan condiment was about to be added, but potato is vegan?";
    }

    public String getMsg() {
        return msg;
    }
}
