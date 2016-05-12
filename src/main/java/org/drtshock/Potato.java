package org.drtshock;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A delicious tuber that is eaten by various peoples all over the world.
 */
public class Potato implements Tuber {

    private final List<Condiment> condiments = new ArrayList<Condiment>();

    public static void main(String[] args) {
        final Potato potato = new Potato();
        try {
            potato.prepare();
            System.out.println("Of course potato is prepared and delicious.");
        } catch (NotDeliciousException e) {
            System.err.println("Fatal error! How could potato not be delicious?");
        }
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
    public void prepare() throws NotDeliciousException {
        this.addCondiments("sour cream", "chives", "butter", "crumbled bacon", "grated cheese", "ketchup", "salt", "tabasco");
        this.listCondiments();
        if (!this.isDelicious()) throw new NotDeliciousException(NotDeliciousReason.NOT_BAKED);
    }

    /**
     * Adds condiments to the potato.
     *
     * @param names Names of the condiments to add
     */
    public void addCondiments(String... names) throws NotDeliciousException {
        for (String condimentName : names) {
            Condiment condiment = new Condiment(condimentName, true);
            if (!condiment.isDelicious()) throw new NotDeliciousException(NotDeliciousReason.NOT_DELICIOUS_CONDIMENT);
            this.getCondiments().add(condiment);
        }
    }

    /**
     * Prints the names of the condiments on this potato to stdout.
     *
     * @see #getCondiments()
     */
    public void listCondiments() {
        for (Condiment condiment : this.getCondiments()) {
            System.out.println(condiment.getName());
        }
    }

    /**
     * Checks if the potato is put into the oven.
     *
     * @return true if potato is in the oven, false if otherwise
     * @throws OvenException if the oven encounters an internal exception
     */
    public boolean isPutIntoOven() throws OvenException {
        try {
            final URL url = new URL("https://www.google.com/search?q=potato");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("User-Agent", "Potato");
            connection.connect();
            int inOven = connection.getResponseCode();
            return inOven == 200;
        } catch (IOException ex) {
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
        } catch (OvenException e) {
            return false;
        }
    }

    /**
     * Checks if this potato is cooked. Returns the result of {@link #hasBeenBoiledInWater()}.
     *
     * @return true if this potato is baked, false if otherwise
     */
    public boolean isCooked() {
        try {
            return this.hasBeenBoiledInWater();
        } catch (BurntException e) {
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
        System.out.println("Trying to boil potato at " + waterDegrees + " degrees.");
        if (waterDegrees < 70) {
            return false;
        } else if (waterDegrees > 130) {
            throw new BurntException(waterDegrees);
        }
        return true;
    }

    /**
     * Checks if this potato is delicious. Returns the result of {@link #isBaked()}.
     *
     * @return true if this potato is delicious, false if otherwise
     */
    @Override
    public boolean isDelicious() {
        return this.isBaked() || this.isCooked();
    }

    /**
     * Propagates a new potato.
     *
     * @return A new potato
     */
    @Override
    public Tuber propagate() {
        return new Potato();
    }

    /**
     * A type of food added to tubers.
     */
    private class Condiment {
        private final String name;
        private final boolean delicious;

        public Condiment(String name, boolean delicious) {
            this.name = name;
            this.delicious = delicious;
        }

        /**
         * Returns if this condiment is delicious or not.
         *
         * @return true if delicious, false if otherwise
         */
        public boolean isDelicious() {
            return this.delicious;
        }

        /**
         * Gets the name of this condiment.
         *
         * @return Name
         */
        public String getName() {
            return this.name;
        }
    }

}
