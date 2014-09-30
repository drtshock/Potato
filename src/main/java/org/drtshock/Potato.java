package org.drtshock;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Potato implements Tuber {

    private final List<Condiment> condiments = new ArrayList<Condiment>();

    public static void main(String[] args) {
        final Potato potato = new Potato();
        try {
        	potato.prepare();
        	System.out.println("Of course potato is prepared and delicious.");
        } catch (NotDeliciousException e) {
        	System.err.println("Fatal error! How could potato not be delicious?");
        	return;
        }
    }

    public void prepare() throws NotDeliciousException {
        this.addCondiments("sour cream", "chives", "butter", "crumbled bacon", "grated cheese", "ketchup", "salt", "tabasco");
        this.listCondiments();
        if(!this.isDelicious()) throw NotDeliciousException();
    }

    public void addCondiments(String... names) {
        synchronized (this.condiments) {
            for (String condimentName : names) this.condiments.add(new Condiment(condimentName, this));
        }
    }

    public void listCondiments() {
        for (Condiment condiment : this.condiments) {
            System.out.println(condiment.getName());
        }
    }

    public boolean isPutIntoOven() {
        try {
            final URL url = new URL("https://www.google.com/search?q=potato");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int inOven = connection.getResponseCode();
            return inOven == 200;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean isBaked() {
        return this.isPutIntoOven();
    }

    public boolean isDelicious() {
        return this.isBaked() && areCondimentsDelicious();
    }
    
    public boolean areCondimentsDelicious() {
        for(Condiment condiment : condiments)
            if(!condiment.isDelicious())
                return false;
        return true;
    }

    public Tuber propagate() {
        return new Potato();
    }

    private class Condiment {
        private final String name;
        private final Potato potato;

        public Condiment(String name, Potato potato) {
            this.name = name;
            this.potato = potato;
        }

        public String getName() {
            return this.name;
        }
        
        public boolean isDelicious() {
            return potato instanceof Potato;
        }
    }

}
