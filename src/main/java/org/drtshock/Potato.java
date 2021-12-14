package org.drtshock;

import org.drtshock.api.PotatoItem;

import java.util.ArrayList;
import java.util.List;

public class Potato {
    private static final List<PotatoItem> items = new ArrayList<>();

    public static void main(String[] args) {
        boolean isVegan = args.length == 1 && args[0].equals("--vegan");
        int potatoes =
                args.length == 3 ? Integer.parseInt(args[2].split("--count ")[0])
                : args.length == 2 ? Integer.parseInt(args[1])
                : 1;

        for (int i = 0; i <= potatoes; i++) {
            items.add(createPotato(i, isVegan));
        }

        for (PotatoItem item : items) {
            Thread thread = new Thread(item);
            thread.start();

            // this is here so each thread actually runs after each other as
            // it will be out of sync otherwise.
            while (thread.isAlive());
        }
    }

    /**
     * create new potato
     * @return PotatoItem.class
     */
    private static PotatoItem createPotato(int index, boolean isVegan) {
        return new PotatoItem(index, isVegan);
    }
}
