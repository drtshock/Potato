package org.drtshock.api.condiments;

public class VeganCondiments {
    private static final String[] veganFoods =
            new String[]{
                    "chives", "butter", "pepper",
                    "salt", "tabasco", "tomatoes",
                    "onion"}; // add more vegan foods here if you want more vegan toppings!

    /**
     * determines that food is vegan
     * @return isVegan
     */
    public static boolean isVegan(String name) {
        for (String veganFood: veganFoods) {
            if (name.equalsIgnoreCase(veganFood)) {
                return true;
            }
        }
        return false;
    }
}
