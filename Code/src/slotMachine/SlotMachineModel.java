package slotMachine;

import java.io.Serializable;

public class SlotMachineModel implements Serializable {
	private static final long serialVersionUID = 9141927019784603397L;

	/* Increase the credit value by one and returns the new credit value */
	public int addCoin(int credits) {
		return credits + 1;
	}

	/* Decrease the credit value by one and returns the new credit value */
	public int removeOneCoin(int credits) {
		return credits - 1;
	}

	/*
	 * Cancels current bet and decreases the credits by 3 and returns the new
	 * credit value
	 */
	public int betMax(int credits, int existingBet) {
		return credits + existingBet - 3;
	}

	/* Increase the bet amount by one and returns the new bet value */
	public int betOne(int betAmount) {
		return betAmount + 1;
	}

	/*
	 * Adds winning credits to the existing credits and returns the new credit
	 * value
	 */
	public int addWinningCoins(int credits, int betAmnt, int symbolAmnt) {
		return (betAmnt * symbolAmnt) + credits + betAmnt;
	}
}
