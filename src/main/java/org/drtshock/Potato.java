package org.drtshock;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A delicious tuber that is eaten by various peoples all over the world.
 */
public class Potato implements Tuber {

	private final Random potatential = new Random();
    private final List<Condiment> condiments = new ArrayList<>();
    private boolean boiled = false;
    private boolean baked = false;

    public static void main(String[] args) {
        final Potato potato = new Potato();
        try {
            potato.prepare();
            System.out.println("Of course Potato is prepared and delicious.");
        } catch (NotDeliciousException e) {
            System.err.println("Fatal error! How could Potato not be delicious?\nReason: " + e.getReason());
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
        this.maybeAddCondiments("sour cream", "chives", "butter", "crumbled bacon", "grated cheese", "ketchup", "pepper",
                "salt", "tabasco", "tomatoes");
        this.listCondiments();
        if(!getCondiments().stream().anyMatch(condiment -> condiment.getName().equals("crumbled bacon")))
        	throw new NotDeliciousException(NotDeliciousReason.NO_BACON);
        if (!this.isDelicious()) throw new NotDeliciousException(NotDeliciousReason.NOT_RIGHT_TEXTURE_PERHAPS_BOTH_COOKED_AND_BAKED);
    }

    /**
     * Maybe adds condiments to the potato.
     *
     * @param names Names of the condiments to add
     */
    public void maybeAddCondiments(String... names) throws NotDeliciousException {
        for (String condimentName : names) {
        	if (potatential.nextBoolean()) {
                Condiment condiment = new Condiment(condimentName, true);
                if (!condiment.isDelicious()) throw new NotDeliciousException(NotDeliciousReason.NOT_DELICIOUS_CONDIMENT);
                if (condiment.isExpired()) throw new NotDeliciousException(NotDeliciousReason.EXPIRED_CONDIMENT);
                this.getCondiments().add(condiment);
        	}
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
     * Bakes the potato in an oven and give useful feedback about it.
     *
     * @return true if potato is correctly baked.
     * @throws BurntException if the potato is burnt to a point of no deliciousness.
     * @throws OvenException if the oven encounters an internal exception
     */
    public void bake() throws BurntException, OvenException {
        try {
        	System.out.println("Trying to bake potato in the oven...");
            long begin = System.currentTimeMillis();
            final URL url = new URL("https://www.google.com/search?q=potato");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("User-Agent", "Potato/1.7.5");
            connection.connect();
            int inOven = connection.getResponseCode();
            long bakeTime = (System.currentTimeMillis() - begin);
            if (bakeTime > 1100) throw new BurntException(bakeTime);
            System.out.println("Baked potato for " + bakeTime + " millis.");
            baked = (inOven == 200);
            if (!baked) {
            	System.out.println("Potato hasn't been baked!");
            }
        } catch (IOException ex) {
            throw new OvenException(ex);
        }
    }

    /**
     * Boils a potato in water and gives useful feedback about it.
     *
     * @return true if the potato has succesfully been boiled, false if otherwise
     * @throws UndercookedException if the potato hasn't cooked long enough.
     * @throws BurntException if the potato has been burned during the process of cooking
     */
    public void boil() throws BurntException, UndercookedException {
        int waterDegrees = (int) (Math.random() * 200);
        System.out.println("Trying to boil potato at " + waterDegrees + " degrees.");
        if (waterDegrees < 70 && !baked) {
            throw new UndercookedException(waterDegrees);
        } else if (waterDegrees > 130) {
            throw new BurntException(waterDegrees);
        }
        boiled = true;
    }

    /**
     * Checks if this potato is delicious.
     * A potato can only be delicious if it has been either cooked or baked.
     *
     * @return true if this potato is delicious, false if otherwise
     * @throws NotDeliciousException if the potato is not delicious!
     */
    @Override
    public boolean isDelicious() throws NotDeliciousException {
		try {
			if (potatential.nextBoolean()) bake();
			if (potatential.nextBoolean()) boil();
		} catch (BurntException e) {
			throw new NotDeliciousException(NotDeliciousReason.BURNT);
		} catch (OvenException | UndercookedException e) {
			throw new NotDeliciousException(NotDeliciousReason.UNDERCOOKED);
		}
		if (!boiled && ! baked) {
			throw new NotDeliciousException(NotDeliciousReason.RAW);
		}
		return boiled ^ baked;
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
        private final boolean expired;

        public Condiment(String name, boolean delicious, boolean expired) {
            this.name = name;
            this.delicious = delicious;
            this.expired = expired;
        }

        public Condiment(String name, boolean delicious) {
            this(name, delicious, Math.random() * 100 < 3);
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
         * Returns if this condiment is expired or not.
         *
         * @return true if expired, false if otherwise
         */
        public boolean isExpired() {
            return expired;
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
