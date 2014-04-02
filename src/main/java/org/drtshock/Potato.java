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
        if (potato.prepare()) System.out.println("Of course potato is prepared and delicious.");
        else System.err.println("Fatal error! How could potato not be delicious?");
    }

    public boolean prepare() {
        this.addCondiments("sour cream", "chives", "butter", "crumbled bacon", "grated cheese", "ketchup", "salt", "tabasco");
        this.listCondiments();
        return this.isDelicious();
    }

    public void addCondiments(String... names) {
        synchronized (this.condiments) {
            for (String condimentName : names) this.condiments.add(new Condiment(condimentName));
        }
    }

    public void listCondiments() {
        for (Condiment condiment : this.condiments) {
            System.out.println(condiment.getName());
        }
    }

    public boolean isPutIntoOven() {
        try {
            final URL url = new URL("https://www.google.com/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int inOven = connection.getResponseCode();
            return inOven == 200;
        } catch (IOException ex) {
            return false;
        }
    }

    public boolean isBaked() {
        return this.isPutIntoOven();
    }

    public boolean isDelicious() {
        return this.isBaked();
    }

    public Tuber propagate() {
        return new Potato();
    }

    private class Condiment {
        private final String name;

        public Condiment(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

}
