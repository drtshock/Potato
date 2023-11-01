package org.drtshock;

import org.drtshock.exceptions.BurntException;
import org.drtshock.exceptions.NotDeliciousException;

/**
 * Where the potato deliciousness begins.
 */
public class Main {
	public static void main(String[] args) {
		Potato potato = new Potato(args.length == 1 && args[0].equals("--vegan"));
		potato = Math.random() < 0.75 ? potato : potato.turnIntoFries();
		if (potato.isVegan()) System.out.println("This potato is vegan.");
		try {
			potato.prepare();
			potato.listCondiments();
			System.out.println("Of course Potato is prepared and delicious.");
		} catch (NotDeliciousException e) {
			System.err.println("Fatal error! How could Potato not be delicious?\nReason: " + e.getReason());
		} catch (BurntException e) {
			System.err.println("Fatal error! Potato has been burnt to a crisp!\nTemperature: " + e.getTemperature() + " degrees");
		}
	}
}
