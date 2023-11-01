package org.drtshock;

import org.drtshock.exceptions.BurntException;
import org.drtshock.exceptions.NotDeliciousException;
import org.drtshock.types.NotDeliciousReason;

public class Fries extends Potato {

    public Fries(boolean isVegan) {
        super(isVegan);
    }

    public void prepare() throws NotDeliciousException, BurntException {
        this.addCondiments("salt", "pepper");
        if (!this.isVegan()) this.addCondiments("ketchup", "mayo");
        if (!this.isDelicious()) throw new NotDeliciousException(NotDeliciousReason.UNDERCOOKED);
        else if (Math.random() < 0.1) throw new BurntException(250 + (int) (Math.random() * 100));
    }

}
