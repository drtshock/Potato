package org.drtshock.exceptions;

/**
 * An exception to describe that our potato isn't cooked properly!
 */
public class UndercookedException extends Exception {

	public UndercookedException(long bakeTime) {
		super("Potato has only been baking for " + bakeTime + "!\nGive it some more time...");
	}

}
