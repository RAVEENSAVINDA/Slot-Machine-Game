package slotMachine;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Reel {

	/* Creating instances of Symbol type to hold different six symbols */
	private Symbol seven = new Symbol(7, "Images/redseven.png");
	private Symbol bell = new Symbol(6, "Images/bell.png");
	private Symbol watermelon = new Symbol(5, "Images/watermelon.png");
	private Symbol plum = new Symbol(4, "Images/plum.png");
	private Symbol lemon = new Symbol(3, "Images/lemon.png");
	private Symbol cherry = new Symbol(2, "Images/cherry.png");

	/* This array holds the six different Symbol objects */
	private Symbol[] picArray = new Symbol[6];

	/*
	 * This method returns an randomly shuffled array with the six Symbols
	 * objects
	 */
	public Symbol[] spin() {
		initializeArray(picArray);
		shuffleArray(picArray);
		return picArray;
	}

	/* Randomly shuffles a Symbol type Array */
	private void shuffleArray(Symbol[] ar) {
		// A random number generator isolated to the current thread
		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.length - 1; i > 0; i--) {
			// Returns a random, uniformly distributed value between the given
			// least value (inclusive) and bound (exclusive)
			int index = rnd.nextInt(i + 1);
			// Simple swap
			Symbol a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

	/* Initialize the Symbol array with six Symbol Objects */
	private void initializeArray(Symbol[] ar) {
		ar[0] = seven;
		ar[1] = bell;
		ar[2] = watermelon;
		ar[3] = plum;
		ar[4] = lemon;
		ar[5] = cherry;
	}
}
