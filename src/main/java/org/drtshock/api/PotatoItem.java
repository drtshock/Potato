package org.drtshock.api;

import org.drtshock.Potato;
import org.drtshock.exceptions.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * for multithreading :)
 */
public class PotatoItem implements DelectableItem, Runnable {
    private final int index;
    private final boolean isVegan;
    private final List<Condiment> condiments = new ArrayList<>();

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
            prepare();
            System.out.println("Of course Potato with id " + index + " is prepared and delicious.");
        } catch (NotDeliciousException e) {
            System.err.println("Fatal error! How could Potato with id " + index + " not be delicious?\nReason: " + e.getReason() + "\n");
        } catch (VeganException e) {
            System.out.println("--------");
            System.err.println("error in potato " + index + "\n" + e.getMsg() + "\n--------\n");
        }
    }

    public PotatoItem(int index, boolean isVegan) {
        this.index = index;
        this.isVegan = isVegan;

        System.out.println("potato with id " + index + " created");
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
     */
    public void prepare() throws NotDeliciousException, VeganException {
        this.addCondiments("chives", "butter", "pepper", "salt", "tabasco", "tomatoes", "onion");
        if (!this.isVegan) this.addCondiments("sour cream", "crumbled bacon", "grated cheese", "ketchup");
        this.listCondiments();
        if (!this.isDelicious()) throw new NotDeliciousException(NotDeliciousReason.UNDERCOOKED);
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
        if (!condiment.isDelicious()) throw new NotDeliciousException(NotDeliciousReason.NOT_DELICIOUS_CONDIMENT);
        if (condiment.isExpired()) throw new NotDeliciousException(NotDeliciousReason.EXPIRED_CONDIMENT);
        if (!condiment.isVegan() && isVegan) {
            throw new VeganException(condiment);
        }
        this.getCondiments().add(condiment);
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
    public boolean isPutIntoOven() throws OvenException, BurntException {
        try {
            long begin = System.currentTimeMillis();
            final URL url = new URL("https://www.google.com/search?q=potato");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("User-Agent", "Potato/2.0.0");
            connection.connect();
            int inOven = connection.getResponseCode();
            long bakeTime = (System.currentTimeMillis() - begin);
            if (bakeTime > 1100) throw new BurntException(bakeTime, NotDeliciousReason.OVERCOOKED);
            return inOven == 200;
        } catch (IOException ex) {
            //ex.printStackTrace(); // throw error p:
            System.out.println("--------");
            System.err.println("error in potato " + index + "\n" + ex.getMessage() + "\n--------\n");
            throw new OvenException(ex);
        }
    }

    /**
     * Checks if this potato is baked. Returns the result of {@link #isPutIntoOven()}.
     *
     * @return true if this potato is baked, false if otherwise
     */
    public boolean isBaked() {
        try {
            return this.isPutIntoOven();
        } catch (OvenException | BurntException e) {
            return false;
        }
    }

    /**
     * Checks if the potato is succesfully boiled at the right amount of degrees.
     *
     * @return true if the potato has succesfully been boiled, false if otherwise
     * @throws BurntException if the potato has been burned during the process of cooking
     */
    public boolean hasBeenBoiledInWater() throws BurntException {
        int waterDegrees = (int) (Math.random() * 200);
        System.out.println("Trying to boil potato with id " + index + " at " + waterDegrees + " degrees.");
        if (waterDegrees < 70) {
            return false;
        } else if (waterDegrees > 130) {
            throw new BurntException(waterDegrees, NotDeliciousReason.BOILED_AT_WRONG_DEGREES);
        }
        return true;
    }

    /**
     * Checks if this potato is cooked. Returns the result of {@link #hasBeenBoiledInWater()}.
     *
     * @return true if this potato is baked, false if otherwise
     */
    public boolean isBoiled() {
        try {
            return this.hasBeenBoiledInWater();
        } catch (BurntException e) {
            System.out.println("--------");
            System.err.println("error in potato " + index + "\n" + e.getMessage() + "\n--------\n");
            return false;
        }
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
}
