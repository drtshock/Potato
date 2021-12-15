package org.drtshock;

import org.drtshock.api.Api;
import org.drtshock.api.PotatoItem;
import org.drtshock.api.events.PotatoItemAddCondimentEvent;
import org.drtshock.api.events.PotatoItemCreateEvent;
import org.drtshock.api.events.PotatoItemErrorEvent;
import org.drtshock.api.events.PotatoItemRemoveCondimentEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Potato {
    public static PrintStream stream;

    /**
     * Events
     */
    private static final PotatoItemCreateEvent potatoItemCreateEvent = new PotatoItemCreateEvent();
    public static PotatoItemCreateEvent getPotatoItemCreateEvent() {
        return potatoItemCreateEvent;
    }

    private static final PotatoItemErrorEvent potatoItemErrorEvent = new PotatoItemErrorEvent();
    public static PotatoItemErrorEvent getPotatoItemErrorEvent() {
        return potatoItemErrorEvent;
    }

    private static final PotatoItemAddCondimentEvent potatoItemAddCondimentEvent = new PotatoItemAddCondimentEvent();
    public static PotatoItemAddCondimentEvent getPotatoItemAddCondimentEvent() {
        return potatoItemAddCondimentEvent;
    }

    private static final PotatoItemRemoveCondimentEvent potatoItemRemoveCondimentEvent = new PotatoItemRemoveCondimentEvent();
    public static PotatoItemRemoveCondimentEvent getPotatoItemRemoveCondimentEvent() {
        return potatoItemRemoveCondimentEvent;
    }

    public static void main(String[] args) {
        init(args);
    }

    public static void init(String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("--help")) {
                String path = new File(Potato.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName();
                System.out.println("Command line arguments:");
                System.out.println("./" + path
                        + " --help");
                System.out.println("./" + path
                        + " --tests <number>");
                System.out.println("./" + path
                        + " --vegan=true");
                System.out.println("./" + path
                        + " --vegan=true --count=<number>");
                System.out.println("./" + path
                        + " --count=<number>");

                return;
            }
        }

        boolean isVegan = Api.CLIArgumentLibrary.getArgBool(args, "vegan");
        int potatoes = Api.CLIArgumentLibrary.getArgInt(args, "count");

        PrintStream streamOut = System.out;
        stream = System.out;

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("--tests")) {
                long time = System.currentTimeMillis();
                int potatoesToTest = Integer.parseInt(args[1]);
                int testedPotatoes = 0;
                File file;
                for (int i = 1; i <= potatoesToTest; i++) {
                    try {
                        file = new File("tests/");
                        file.mkdirs();
                        stream = new PrintStream(file.getPath() + "/tests-output-" + i + ".txt");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    createPotatoes(isVegan, i * 5);
                    testedPotatoes+=i * 5;
                    stream.close();
                }

                System.setOut(streamOut);
                System.out.println(
                        "Potatoes have been tested all tests are located in \"/tests/\"" +
                                " Time took to test " + testedPotatoes + " potatoes: " +
                                (System.currentTimeMillis() - time) + "ms");
                return;
            }
        }

        createPotatoes(isVegan, potatoes);
    }

    public static void createPotatoes(boolean isVegan, int potatoes) {
        for (int i = 1; i <= potatoes; i++) createPotato(i, isVegan).calculate();
    }

    /**
     * create new potato
     * @return PotatoItem.class
     */
    public static PotatoItem createPotato(int index, boolean isVegan) {
        return new PotatoItem(index, isVegan);
    }
}
