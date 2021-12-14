package org.drtshock;

import org.drtshock.api.PotatoItem;
import org.drtshock.api.events.PotatoItemCreateEvent;
import org.drtshock.api.events.PotatoItemErrorEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;

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
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("--help")) {
                String path = new File(Potato.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName();
                System.out.println("Command line arguments:");
                System.out.println("./" + path
                        + " --help");
                System.out.println("./" + path
                        + " --tests=<number>");
                System.out.println("./" + path
                        + " --vegan=true");
                System.out.println("./" + path
                        + " --uselessFeatures=true");
                System.out.println("./" + path
                        + " --uselessFeatures=true --vegan=true");
                System.out.println("./" + path
                        + " --uselessFeatures=true --vegan=true --count=<number>");
                System.out.println("./" + path
                        + " --uselessFeatures=true --count=<number>");
                System.out.println("./" + path
                        + " --vegan=true --count=<number>");
                System.out.println("./" + path
                        + " --count=<number>");

                return;
            }
        }

        boolean isVegan = getArgBool(args, "--vegan");
        int potatoes = getArgInt(args, "--count");
        uselessFeatures = getArgBool(args, "--uselessFeatures");
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

    private static Object getArg(String[] args, String argument) {
        Object[] found = Arrays.stream(String.join(" ", String.join(" ", args)
                .split(String.format("\\%s=(.*;)", argument))[0]
                .split(argument + "="))
                .split(" ")).filter((str) -> !str.equalsIgnoreCase("") && !str.startsWith("--")).toArray();
        return found.length == 0 || found[0].equals("") ? null : found[0];
    }

    private static boolean getArgBool(String[] args, String argument) {
        Object arg = getArg(args, argument);
        return arg != null && Boolean.parseBoolean((String) arg);
    }

    private static int getArgInt(String[] args, String argument) {
        Object arg = getArg(args, argument);
        return arg == null ? 1 : Integer.parseInt((String)arg);
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
