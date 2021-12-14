package org.drtshock.exceptions;

public class VeganException extends Exception {
    public VeganException() {
        System.out.println("A non vegan condiment was about to be added, but potato is vegan?");
    }
}
