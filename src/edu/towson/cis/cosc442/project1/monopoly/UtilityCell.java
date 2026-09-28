package edu.towson.cis.cosc442.project1.monopoly;

public class UtilityCell extends Cell {

	public static final String COLOR_GROUP = "UTILITY";
	private static int PRICE;

	/**
	 * Sets the static purchase price for all UtilityCell instances.
	 * @param price the price to set for utility properties
	 */
	public static void setPrice(int price) {
		UtilityCell.PRICE = price;
	}

	/**
	 * Retrieves the static purchase price of the utility property.
	 * @return the price of the utility property
	 */
	public int getPrice() {
		return UtilityCell.PRICE;
	}

	/**
	 * Calculates the rent due based on the dice roll and number of utilities owned by the owner.
	 * @param diceRoll the value of the dice roll to determine rent
	 * @return the rent amount owed based on the diceRoll and number of utilities owned
	 */
	public int getRent(int diceRoll) {
		if(theOwner.numberOfUtil() == 1) {
			return diceRoll * 4;
		} else if (theOwner.numberOfUtil() >= 2) {
			return diceRoll * 10;
		}
		return 0;
	}

	/**
	 * Executes the action when a player lands on the utility cell, charging rent if owned by another player.
	 */
	public void playAction() {
		Player currentPlayer = null;
		if(!isAvailable()) {
			currentPlayer = GameMaster.instance().getCurrentPlayer();
			if(theOwner != currentPlayer) {
				GameMaster.instance().utilRollDice();
				int diceRoll = GameMaster.instance().getUtilDiceRoll();
				currentPlayer.payRentTo(theOwner, getRent(diceRoll));
			}
		}
	}
}
