package org.drtshock;

import org.drtshock.api.PotatoItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Potato {
    public static PrintStream stream;
    private static PrintStream streamOut;
    private static final List<PotatoItem> items = new ArrayList<>();

    public static void main(String[] args) {
//        Debugging
//        for(int i = 0; i<args.length; i++) {
//            System.out.println("args[" + i + "]: " + args[i]);
//        }

        streamOut = System.out;
        stream = System.out;

        boolean isVegan = args.length >= 1 && args[0].equals("--vegan");
        int potatoes =
                args.length >= 3 ? Integer.parseInt(args[2])
                        : args.length >= 2 ? Integer.parseInt(args[1])
                        : 1;

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("--tests")) {
                long time = System.currentTimeMillis();
                int potatoesToTest = 1;
                for (int i = 1; i <= potatoesToTest; i++) {
                    try {
                        File file = new File("tests/");
                        file.mkdirs();
                        stream = new PrintStream(file.getPath() + "/tests-output-" + i + ".txt");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    createPotatoes(isVegan, i * 5);
                    stream.close();
                }

                System.setOut(streamOut);
                System.out.println("Potatoes have been tested all tests are located in \"/tests/\"" +
                        " Time took to test " + potatoesToTest + " potatoes: " + (System.currentTimeMillis() - time) + "ms");

                return;
            }
        }

        createPotatoes(isVegan, potatoes);
    }

    private static void createPotatoes(boolean isVegan, int potatoes) {
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
