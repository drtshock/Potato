package org.drtshock.api;

import org.drtshock.exceptions.NotDeliciousException;
import org.drtshock.exceptions.VeganException;

import java.util.Arrays;

public class Api {
    public static class CLIArgumentLibrary {
        public static Object getArg(String[] args, String argument) {
            Object[] found = Arrays.stream(String.join(" ", String.join(" ", args)
                            .split(String.format("\\%s=(.*;)", "--" + argument))[0]
                            .split(argument + "="))
                    .split(" ")).filter((str) -> !str.equalsIgnoreCase("") && !str.startsWith("--")).toArray();
            return found.length == 0 || found[0].equals("") ? null : found[0];
        }

        public static boolean getArgBool(String[] args, String argument) {
            Object arg = getArg(args, argument);
            return arg != null && Boolean.parseBoolean((String) arg);
        }

        public static int getArgInt(String[] args, String argument) {
            Object arg = getArg(args, argument);
            return arg == null ? 1 : Integer.parseInt((String) arg);
        }

        public static long getArgLong(String[] args, String argument) {
            Object arg = getArg(args, argument);
            return arg == null ? 1 : Long.parseLong((String) arg);
        }

        public static double getArgDouble(String[] args, String argument) {
            Object arg = getArg(args, argument);
            return arg == null ? 1 : Double.parseDouble((String) arg);
        }

        public static String getArgString(String[] args, String argument) {
            Object arg = getArg(args, argument);
            return arg == null ? "" : (String) arg;
        }
    }

    public static boolean isBoiled(PotatoItem item) {
        return item.isBoiled();
    }

    public static boolean isBaked(PotatoItem item) {
        return item.isBaked();
    }

    public static boolean isDelicious(PotatoItem item) {
        return item.isDelicious();
    }

    public static void listCondiments(PotatoItem item) {
        item.listCondiments();
    }

    public static void addCondiment(PotatoItem item, String condiment) {
        try {
            item.addCondiment(condiment);
        } catch (NotDeliciousException | VeganException e) {
            e.printStackTrace();
        }
    }

    public static void removeCondiment(PotatoItem item, String condiment) {
        item.removeCondiment(condiment);
    }
}
