package edu.towson.cis.cosc442.project1.monopoly;

public class GoToJailCell extends Cell {
	
	/**
	 * Constructs a GoToJailCell and sets its name to "Go to Jail".
	 */
	public GoToJailCell() {
		setName("Go to Jail");
	}

	/**
	 * Sends the current player to the Jail cell as part of the game action.
	 */
	public void playAction() {
		Player currentPlayer = GameMaster.instance().getCurrentPlayer();
		GameMaster.instance().getGameBoard().queryCell("Jail");
		GameMaster.instance().sendToJail(currentPlayer);
	}
}
