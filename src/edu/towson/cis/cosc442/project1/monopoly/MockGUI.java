package edu.towson.cis.cosc442.project1.monopoly;

public class MockGUI implements MonopolyGUI {
    private boolean btnDrawCardState, btnEndTurnState, btnGetOutOfJailState;
    private boolean[] btnTradeState = new boolean[2];

    /**
     * Enables the end turn button for the specified player index.
     * @param playerIndex the index of the player for whom to enable the end turn button
     */
    public void enableEndTurnBtn(int playerIndex) {
    }

    /**
     * Enables user interface elements related to the specified player's turn.
     * @param playerIndex the index of the player whose turn is being enabled
     */
    public void enablePlayerTurn(int playerIndex) {
    }

    /**
     * Enables the purchase button for the specified player index.
     * @param playerIndex the index of the player for whom to enable the purchase button
     */
    public void enablePurchaseBtn(int playerIndex) {
    }
	/**
	 * Returns a fixed dice roll array representing a dice roll result.
	 * @return an integer array of length 2 representing the dice roll values
	 */
	public int[] getDiceRoll() {
		int roll[] = new int[2];
		roll[0] = 2;
		roll[1] = 3;
		return roll;
	}

    /**
     * Indicates whether the draw card button is currently enabled.
     * @return true if the draw card button is enabled; false otherwise
     */
    public boolean isDrawCardButtonEnabled() {
        return btnDrawCardState;
    }

    /**
     * Indicates whether the end turn button is currently enabled.
     * @return true if the end turn button is enabled; false otherwise
     */
    public boolean isEndTurnButtonEnabled() {
        return btnEndTurnState;
    }
	
	/**
	 * Indicates whether the get out of jail button is currently enabled.
	 * @return true if the get out of jail button is enabled; false otherwise
	 */
	public boolean isGetOutOfJailButtonEnabled() {
		return btnGetOutOfJailState;
	}

    /**
     * Indicates whether the trade button for a specified player index is enabled.
     * @param i the player index to check trade button status for
     * @return true if the trade button is enabled for the specified index; false otherwise
     */
    public boolean isTradeButtonEnabled(int i) {
        return btnTradeState[i];
    }

    /**
     * Moves a player from one board position to another.
     * @param index the index of the player to move
     * @param from the starting position index
     * @param to the destination position index
     */
    public void movePlayer(int index, int from, int to) {
    }

    /**
     * Opens and returns a dialog allowing a player to respond to a trade deal.
     * @param deal the trade deal to respond to
     * @return a RespondDialog to handle the trade response
     */
    public RespondDialog openRespondDialog(TradeDeal deal) {
        RespondDialog dialog = new MockRespondDialog(deal);
        return dialog;
    }

    /**
     * Opens and returns a dialog allowing a player to initiate a trade.
     * @return a TradeDialog to facilitate trade proposals
     */
    public TradeDialog openTradeDialog() {
        TradeDialog dialog = new MockTradeDialog();
        return dialog;
    }

    /**
     * Sets the enabled state of the buy house button.
     * @param b true to enable the buy house button; false to disable
     */
    public void setBuyHouseEnabled(boolean b) {
    }

    /**
     * Sets the enabled state of the draw card button.
     * @param b true to enable the draw card button; false to disable
     */
    public void setDrawCardEnabled(boolean b) {
        btnDrawCardState = b;
    }

    /**
     * Sets the enabled state of the end turn button.
     * @param enabled true to enable the end turn button; false to disable
     */
    public void setEndTurnEnabled(boolean enabled) {
        btnEndTurnState = enabled;
    }

    /**
     * Sets the enabled state of the get out of jail button.
     * @param b true to enable the get out of jail button; false to disable
     */
    public void setGetOutOfJailEnabled(boolean b) {
    	this.btnGetOutOfJailState = b;
    }

    /**
     * Sets the enabled state of the purchase property button.
     * @param enabled true to enable the purchase property button; false to disable
     */
    public void setPurchasePropertyEnabled(boolean enabled) {
    }

    /**
     * Sets the enabled state of the roll dice button.
     * @param b true to enable the roll dice button; false to disable
     */
    public void setRollDiceEnabled(boolean b) {
    }

    /**
     * Sets the enabled state of the trade button for a specified player index.
     * @param index the player index whose trade button state is being set
     * @param b true to enable the trade button; false to disable
     */
    public void setTradeEnabled(int index, boolean b) {
        this.btnTradeState[index] = b;
    }

    /**
     * Displays a dialog for the specified player to buy a house.
     * @param currentPlayer the player currently buying a house
     */
    public void showBuyHouseDialog(Player currentPlayer) {
    }

    /**
     * Displays a message to the user through the GUI.
     * @param string the message text to display
     */
    public void showMessage(String string) {
    }

	/**
	 * Returns a fixed integer simulating a utility dice roll result.
	 * @return an integer representing the utility dice roll total
	 */
	public int showUtilDiceRoll() {
//		int[] diceValues = GameMaster.instance().rollDice();
//		return diceValues[0] + diceValues[1];
		return 10;
	}

    /**
     * Initializes and starts the game interface.
     */
    public void startGame() {
    }

	/**
	 * Updates the GUI to reflect any changes in game state.
	 */
	public void update() {
	}
}
