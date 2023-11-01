package org.drtshock;

import org.drtshock.exceptions.NotDeliciousException;

/**
 * Where the potato deliciousness begins.
 */
public class Main {
	public static void main(String[] args) {
		final Potato potato = new Potato(args.length == 1 && args[0].equals("--vegan"));
		if (potato.isVegan()) System.out.println("This potato is vegan.");
		try {
			potato.prepare();
			System.out.println("Of course Potato is prepared and delicious.");
		} catch (
				NotDeliciousException e) {
			System.err.println("Fatal error! How could Potato not be delicious?\nReason: " + e.getReason());
		}
	}
}
