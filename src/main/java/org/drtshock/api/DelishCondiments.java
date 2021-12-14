package org.drtshock.api;

/**
 * which condiments are delish (yum!)
 */
public class DelishCondiments {
    private static final String[] deliciousCondiments = new String[]{
            "chives", "butter", "pepper",
            "salt", "tabasco", "tomatoes",
            "onion", "sour cream", "crumbled bacon",
            "grated cheese", "ketchup"};

    /**
     * determines that food is delish
     * @return delish
     */
    public static boolean isDelish(String name) {
        for (String dilishFood: deliciousCondiments) {
            if (name.equalsIgnoreCase(dilishFood)) {
                return true;
            }
        }
        return false;
    }
}
