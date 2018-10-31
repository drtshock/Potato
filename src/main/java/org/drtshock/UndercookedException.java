package org.drtshock;

/**
 * An exception to describe that something hasn't cooked enough!
 */
public class UndercookedException extends Exception {

    private static final long serialVersionUID = 1L;

    public UndercookedException(int degrees) {
        super("Potato is badly undercooked at " + degrees + " degrees and thus too hard and not nice!");
    }

}
