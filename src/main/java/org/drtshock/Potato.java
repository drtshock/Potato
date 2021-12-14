package org.drtshock;

//import org.drtshock.api.Cancellable;
//import org.drtshock.api.Event;
import org.drtshock.api.PotatoItem;
import org.drtshock.api.events.PotatoItemCreateEvent;
import org.drtshock.api.events.PotatoItemErrorEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Potato {
    public static PrintStream stream;
    private static final List<PotatoItem> items = new ArrayList<>();

    public static boolean uselessFeatures = false;

    private static final PotatoItemCreateEvent potatoItemCreateEvent = new PotatoItemCreateEvent();
    public static PotatoItemCreateEvent getPotatoItemCreateEvent() {
        return potatoItemCreateEvent;
    }

    private static final PotatoItemErrorEvent potatoItemErrorEvent = new PotatoItemErrorEvent();
    public static PotatoItemErrorEvent getPotatoItemErrorEvent() {
        return potatoItemErrorEvent;
    }

//    public static class PotatoItemErrorTestEvent implements Event {
//        @Override
//        public boolean execute(Event event) {
//            System.out.println("Yo PotatoItemErrorTestEvent here: " + ((PotatoItemErrorEvent)event).getErrorMessage());
//
//            return true;
//        }
//
//        @Override
//        public void addHandle(Event handle) {}
//    }

    public static void main(String[] args) {
//        Debugging
//        for(int i = 0; i<args.length; i++) {
//            System.out.println("args[" + i + "]: " + args[i]);
//        }

        // getPotatoItemErrorEvent().addHandle(new PotatoItemErrorTestEvent());

        PrintStream streamOut = System.out;
        stream = System.out;

        boolean isVegan = args.length >= 1 && args[0].equalsIgnoreCase("--vegan");
        int potatoes =
                isVegan ? Integer.parseInt(args[2])
                        : args.length >= 2 ? Integer.parseInt(args[1])
                        : 1;
        uselessFeatures = args.length >= 1 && (args[0].equalsIgnoreCase("--vegan") ? args[3].equalsIgnoreCase("--uselessfeatures") || args[1].equalsIgnoreCase("--uselessfeatures") : args[0].equalsIgnoreCase("--uselessfeatures"));

        if (uselessFeatures) System.out.println("uselessFeatures is enabled");

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("--tests")) {
                long time = System.currentTimeMillis();
                int potatoesToTest = Integer.parseInt(args[1]);
                for (int i = 1; i <= potatoesToTest; i++) {
                    try {
                        File file = new File("tests/");
                        boolean mkdirsResult = file.mkdirs();
                        stream = new PrintStream(file.getPath() + "/tests-output-" + i + ".txt");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    createPotatoes(isVegan, i * 5);
                    stream.close();
                }

                System.setOut(streamOut);
                System.out.println("Potatoes have been tested all tests are located in \"/tests/\"" +
                        " Time took to test " + potatoesToTest * 5 + " potatoes: " + (System.currentTimeMillis() - time) + "ms");

                return;
            }
        }

        createPotatoes(isVegan, potatoes);
    }

    private static void createPotatoes(boolean isVegan, int potatoes) {
        items.clear();
        for (int i = 1; i <= potatoes; i++) items.add(createPotato(i, isVegan));

        for (PotatoItem item : items) {
//            Thread thread = new Thread(item);
//            thread.start();

            item.calculate();

            // this is here so each thread actually runs after each other as
            // it will be out of sync otherwise.
            // while (thread.isAlive()); // the threading is probably useless so i probably will remove it if i decide to optimise
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
