package org.drtshock;

import org.drtshock.api.PotatoItem;
import org.drtshock.api.events.PotatoItemCreateEvent;
import org.drtshock.api.events.PotatoItemErrorEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Potato {
    public static PrintStream stream;
    public static boolean uselessFeatures = false;

    private static final PotatoItemCreateEvent potatoItemCreateEvent = new PotatoItemCreateEvent();
    public static PotatoItemCreateEvent getPotatoItemCreateEvent() {
        return potatoItemCreateEvent;
    }

    private static final PotatoItemErrorEvent potatoItemErrorEvent = new PotatoItemErrorEvent();
    public static PotatoItemErrorEvent getPotatoItemErrorEvent() {
        return potatoItemErrorEvent;
    }

    public static void main(String[] args) {
        boolean isVegan = args.length >= 1 && args[0].equalsIgnoreCase("--vegan");
        int potatoes = isVegan ? Integer.parseInt(args[2])
                : args.length >= 2 ? Integer.parseInt(args[1]) : 1;
        uselessFeatures = args.length >= 1 &&
                (args[0].equalsIgnoreCase("--vegan") ? args[3].equalsIgnoreCase("--uselessfeatures")
                        || args[1].equalsIgnoreCase("--uselessfeatures")
                        : args[0].equalsIgnoreCase("--uselessfeatures"));
        if (uselessFeatures) System.out.println("uselessFeatures is enabled");

        PrintStream streamOut = System.out;
        stream = System.out;

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("--tests")) {
                long time = System.currentTimeMillis();
                int potatoesToTest = Integer.parseInt(args[1]);
                File file;
                for (int i = 1; i <= potatoesToTest; i++) {
                    try {
                        file = new File("tests/");
                        if (file.mkdirs());
                        stream = new PrintStream(file.getPath() + "/tests-output-" + i + ".txt");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    createPotatoes(isVegan, i * 5);
                    stream.close();
                }

                System.setOut(streamOut);
                System.out.println(
                        "Potatoes have been tested all tests are located in \"/tests/\"" +
                        " Time took to test " + potatoesToTest * 5 + " potatoes: " +
                        (System.currentTimeMillis() - time) + "ms");
                return;
            }
        }

        createPotatoes(isVegan, potatoes);
    }

    private static void createPotatoes(boolean isVegan, int potatoes) {
        for (int i = 1; i <= potatoes; i++) createPotato(i, isVegan).calculate();
    }

    /**
     * create new potato
     * @return PotatoItem.class
     */
    private static PotatoItem createPotato(int index, boolean isVegan) {
        return new PotatoItem(index, isVegan);
    }
}
