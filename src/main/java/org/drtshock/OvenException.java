package org.drtshock;

/**
 * An exception to describe that something went wrong with our oven!
 */
public class OvenException extends Exception {

	private static final long serialVersionUID = 1L;

	public OvenException(Exception internalException) {
        super(internalException);
    }

}
