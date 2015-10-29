package org.drtshock;

public class OvenException extends Exception {

    public OvenException(Exception internalException) {
        super(internalException);
    }

}
