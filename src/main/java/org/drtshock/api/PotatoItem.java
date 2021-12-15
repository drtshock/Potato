package org.drtshock.api;

import org.drtshock.Potato;
import org.drtshock.api.condiments.Condiment;
import org.drtshock.api.condiments.DelishCondiments;
import org.drtshock.api.condiments.VeganCondiments;
import org.drtshock.api.events.Cancellable;
import org.drtshock.api.events.PotatoItemRemoveCondimentEvent;
import org.drtshock.exceptions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * for multithreading :)
 */
public class PotatoItem implements DelectableItem, Runnable {
    private final int index;
    private final boolean isVegan;
    private final List<Condiment> condiments = new ArrayList<>();

    private boolean isBaked, isBoiled;

    /**
     * Multithreading capabilities !
     */
    @Override
    public void run() {
        calculate();
    }

    public void calculate() {
        System.setOut(Potato.stream);
        System.setErr(Potato.stream);

        if (isVegan) System.out.println("Potato with id " + index + " is vegan.");
        try {
            if (prepare()) {
                System.out.println("potato with id " + index + " created");
                System.out.println("Of course Potato with id " + index + " is prepared and delicious.");
            } else {
                System.out.println("potato with id " + index + " was not created? reason: The PotatoItemCreateEvent was canceled");
            }
        } catch (NotDeliciousException e) {
            Potato.getPotatoItemErrorEvent().setErrorMessage("Fatal error! How could Potato with id " + index + " not be delicious?\nReason: " + e.getReason() + "\n");
            Potato.getPotatoItemErrorEvent().execute(Potato.getPotatoItemErrorEvent());
            System.err.println("Fatal error! How could Potato with id " + index + " not be delicious?\nReason: " + e.getReason() + "\n");
        } catch (VeganException e) {
            System.out.println("--------");
            Potato.getPotatoItemErrorEvent().setErrorMessage("error in potato " + index + "\n" + e.getMsg() + "\n--------\n");
            Potato.getPotatoItemErrorEvent().execute(Potato.getPotatoItemErrorEvent());
            System.err.println("error in potato " + index + "\n" + e.getMsg() + "\n--------\n");
        }
        Potato.getPotatoItemCreateEvent().setCanceled(false);
    }

    public PotatoItem(int index, boolean isVegan) {
        this.index = index;
        this.isVegan = isVegan;
    }

    /**
     * Gets the condiments on this potato.
     *
     * @return Mutable list of condiments
     */
    public List<Condiment> getCondiments() {
        return this.condiments;
    }

    /**
     * Prepares the potato for consumption. Adds various condiments and prints them to stdout. Ensures that the potato
     * is delicious. If it is not, a {@link NotDeliciousException} is thrown.
     *
     * @throws NotDeliciousException If the potato is not delicious
     * @return isCanceled
     */
    public boolean prepare() throws NotDeliciousException, VeganException {
        if (!this.isVegan) this.addCondiments("sour cream", "crumbled bacon", "grated cheese", "ketchup", "horseradish");
        this.addCondiments("chives", "butter", "pepper", "salt", "tabasco", "tomatoes", "onion");

        try {
            isBoiled = boil();
            isBaked = bake();
        } catch (OvenException | BurntException e) {
            Potato.getPotatoItemErrorEvent().setErrorMessage("error in potato " + index + "\n" + e.getMessage() + "\n--------\n");
            Potato.getPotatoItemErrorEvent().execute(Potato.getPotatoItemErrorEvent());
            System.out.println("--------");
            System.err.println("error in potato " + index + "\n" + e.getMessage() + "\n--------\n");
        }

        Potato.getPotatoItemCreateEvent().setItem(this);
        Potato.getPotatoItemCreateEvent().execute(Potato.getPotatoItemCreateEvent());
        if (!((Cancellable)Potato.getPotatoItemCreateEvent()).isCanceled()) {
            this.listCondiments();
            if (!this.isDelicious()) throw new NotDeliciousException(NotDeliciousReason.UNDERCOOKED);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds condiments to the potato.
     *
     * @param names Names of the condiments to add
     */
    public void addCondiments(String... names) throws NotDeliciousException, VeganException {
        for (String condimentName : names) {
            addCondiment(condimentName);
        }
    }

    /**
     * Adds condiment to the potato.
     *
     * @param name Name of the condiment to add
     */
    public void addCondiment(String name) throws NotDeliciousException, VeganException {
        Condiment condiment = new Condiment(name, DelishCondiments.isDelish(name), VeganCondiments.isVegan(name));
        Potato.getPotatoItemAddCondimentEvent().condimentName = name;
        Potato.getPotatoItemAddCondimentEvent().condiment = condiment;
        Potato.getPotatoItemAddCondimentEvent().execute(Potato.getPotatoItemAddCondimentEvent());
        if (!Potato.getPotatoItemAddCondimentEvent().isCanceled()) {
            if (condiment.isExpired()) throw new NotDeliciousException(NotDeliciousReason.EXPIRED_CONDIMENT);
            if (!condiment.isDelicious()) throw new NotDeliciousException(NotDeliciousReason.NOT_DELICIOUS_CONDIMENT);
            if (!condiment.isVegan() && isVegan) {
                VeganException error = new VeganException(condiment);
                Potato.getPotatoItemErrorEvent().setErrorMessage(error.getMsg());
                Potato.getPotatoItemErrorEvent().execute(Potato.getPotatoItemErrorEvent());
                Potato.getPotatoItemAddCondimentEvent().setCanceled(false);
                throw error;
            }
            this.getCondiments().add(condiment);
        }
        Potato.getPotatoItemAddCondimentEvent().setCanceled(false);
    }

    /**
     * Removes condiment from the potato.
     *
     * @param name Name of the condiment to remove
     */
    public void removeCondiment(String name) {
        if (this.getCondiments().size() <= 0) return;

        PotatoItemRemoveCondimentEvent event = Potato.getPotatoItemRemoveCondimentEvent();
        Potato.getPotatoItemAddCondimentEvent().condimentName = name;
        Potato.getPotatoItemRemoveCondimentEvent().execute(event);
        if (!Potato.getPotatoItemRemoveCondimentEvent().isCanceled()) {
            Condiment condiment = (Condiment) this.getCondiments().stream().filter((cond) -> cond.getName().equalsIgnoreCase(name)).toArray()[0];
            this.getCondiments().remove(condiment);
        }
        Potato.getPotatoItemRemoveCondimentEvent().setCanceled(false);
    }

    /**
     * Prints the names of the condiments on this potato to stdout.
     *
     * @see #getCondiments()
     */
    public void listCondiments() {
        System.out.println("------");
        System.out.println("condiments in potato " + index + "\n");
        for (Condiment condiment : this.getCondiments()) {
            System.out.println(condiment.getName() + " {"
                    + "isVegan: " + condiment.isVegan()
                    + ", isExpired: " + condiment.isExpired()
                    + ", isDelicious: " + condiment.isDelicious()
                    + " }");
        }
        System.out.println("------");
    }

    /**
     * Checks if the potato is put into the oven.
     *
     * @return true if potato is in the oven, false if otherwise
     * @throws OvenException if the oven encounters an internal exception
     */
    public boolean bake() throws OvenException {
        try {
            double bakeTime = Math.random() * 1400;
            if (bakeTime > 1100) throw new BurntException(bakeTime, NotDeliciousReason.OVERCOOKED);
            return true;
        } catch (BurntException ex) {
            //ex.printStackTrace(); // throw error p:
            Potato.getPotatoItemErrorEvent().setErrorMessage("error in potato " + index + "\n" + ex.getMessage() + "\n--------\n");
            Potato.getPotatoItemErrorEvent().execute(Potato.getPotatoItemErrorEvent());
            System.out.println("--------");
            System.err.println("error in potato " + index + "\n" + ex.getMessage() + "\n--------\n");
            try {
                throw new OvenException(ex);
            } catch (OvenException e) {
                Potato.getPotatoItemErrorEvent().setErrorMessage("error in potato " + index + "\n" + e.getMessage() + "\n--------\n");
                Potato.getPotatoItemErrorEvent().execute(Potato.getPotatoItemErrorEvent());
                System.out.println("--------");
                System.err.println("error in potato " + index + "\n" + e.getMessage() + "\n--------\n");
            }
            return false;
        }
    }

    /**
     * Checks if this potato is baked. Returns the result of {@link #bake()}.
     *
     * @return true if this potato is baked, false if otherwise
     */
    public boolean isBaked() {
        return isBaked;
    }

    /**
     * Checks if the potato is successfully boiled at the right amount of degrees.
     *
     * @return true if the potato has successfully been boiled, false if otherwise
     * @throws BurntException if the potato has been burned during the process of cooking
     */
    public boolean boil() throws BurntException {
        return boil((int) (Math.random() * 200));
    }

    /**
     * Checks if the potato is successfully boiled at the right amount of degrees.
     *
     * @return true if the potato has successfully been boiled, false if otherwise
     * @throws BurntException if the potato has been burned during the process of cooking
     */
    public boolean boil(int degrees) throws BurntException {
        System.out.println("Trying to boil potato with id " + index + " at " + degrees + " degrees.");
        if (degrees < 70) {
            return false;
        } else if (degrees > 130) {
            throw new BurntException(degrees, NotDeliciousReason.BOILED_AT_WRONG_DEGREES);
        }
        return true;
    }

    /**
     * Checks if this potato is cooked. Returns the result of {@link #boil()}.
     *
     * @return true if this potato is baked, false if otherwise
     */
    public boolean isBoiled() {
        return isBoiled;
    }

    /**
     * Checks if this potato is delicious. Returns the result of {@link #isBaked()}.
     *
     * @return true if this potato is delicious, false if otherwise
     */
    @Override
    public boolean isDelicious() {
        return this.isBaked() || this.isBoiled();
    }

    @Override
    public String toString() {
        return "PotatoItem{" +
                "index=" + index +
                ", isVegan=" + isVegan +
                ", condiments=\n" + condiments +
                ", isBaked=" + isBaked +
                ", isBoiled=" + isBoiled +
                "}\n";
    }
}
