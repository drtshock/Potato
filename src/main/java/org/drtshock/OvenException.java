package org.drtshock;

/**
 * Created by Joe Hirschfeld on 10/2/2015.
 */
public class OvenException extends Exception {

    public OvenException(Exception internalException){
        super(internalException);
    }

}
