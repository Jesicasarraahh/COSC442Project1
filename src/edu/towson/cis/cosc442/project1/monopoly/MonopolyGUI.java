package edu.towson.cis.cosc442.project1.monopoly;

public interface MonopolyGUI {
	/**
	 * Enables the 'End Turn' button for the specified player.
	 * @param playerIndex The index identifying the player
	 */
	public void enableEndTurnBtn(int playerIndex);
	/**
	 * Enables the UI elements to indicate and allow the specified player's turn.
	 * @param playerIndex The index identifying the player
	 */
	public void enablePlayerTurn(int playerIndex);
	/**
	 * Enables the purchase button for the specified player.
	 * @param playerIndex The index identifying the player
	 */
	public void enablePurchaseBtn(int playerIndex);
	/**
	 * Retrieves the current dice roll values.
	 * @return An integer array representing the values rolled on the dice.
	 */
	public int[] getDiceRoll();
    /**
     * Checks if the 'Draw Card' button is currently enabled.
     * @return True if the 'Draw Card' button is enabled; otherwise false.
     */
    public boolean isDrawCardButtonEnabled();
    /**
     * Checks if the 'End Turn' button is currently enabled.
     * @return True if the 'End Turn' button is enabled; otherwise false.
     */
    public boolean isEndTurnButtonEnabled();
	/**
	 * Checks if the 'Get Out of Jail' button is currently enabled.
	 * @return True if the 'Get Out of Jail' button is enabled; otherwise false.
	 */
	public boolean isGetOutOfJailButtonEnabled();
    /**
     * Checks if the 'Trade' button is enabled for the specified player.
     * @param i The index identifying the player
     * @return True if the 'Trade' button for the given player is enabled; otherwise false.
     */
    public boolean isTradeButtonEnabled(int i);
	/**
	 * Moves the specified player from one board position to another.
	 * @param index The index identifying the player
	 * @param from The starting position on the board
	 * @param to The ending position on the board
	 */
	public void movePlayer(int index, int from, int to);
    /**
     * Opens a dialog for the player to respond to a trade deal.
     * @param deal The trade deal requiring a response
     * @return A RespondDialog instance representing the opened response dialog.
     */
    public RespondDialog openRespondDialog(TradeDeal deal);
    /**
     * Opens a dialog to initiate a trade between players.
     * @return A TradeDialog instance representing the opened trade dialog.
     */
    public TradeDialog openTradeDialog();
    /**
     * Enables or disables the option to buy a house.
     * @param b True to enable buying a house; false to disable
     */
    public void setBuyHouseEnabled(boolean b);
    /**
     * Enables or disables the 'Draw Card' button.
     * @param b True to enable the button; false to disable
     */
    public void setDrawCardEnabled(boolean b);
    /**
     * Enables or disables the 'End Turn' button.
     * @param enabled True to enable the button; false to disable
     */
    public void setEndTurnEnabled(boolean enabled);
    /**
     * Enables or disables the 'Get Out of Jail' button.
     * @param b True to enable the button; false to disable
     */
    public void setGetOutOfJailEnabled(boolean b);
    /**
     * Enables or disables the ability to purchase a property.
     * @param enabled True to enable property purchase; false to disable
     */
    public void setPurchasePropertyEnabled(boolean enabled);
    /**
     * Enables or disables the ability to roll the dice.
     * @param b True to enable rolling the dice; false to disable
     */
    public void setRollDiceEnabled(boolean b);
    /**
     * Enables or disables the 'Trade' button for a specified player.
     * @param index The index identifying the player
     * @param b True to enable the button; false to disable
     */
    public void setTradeEnabled(int index, boolean b);
    /**
     * Displays the dialog for the specified player to buy a house.
     * @param currentPlayer The player attempting to buy a house
     */
    public void showBuyHouseDialog(Player currentPlayer);
    /**
     * Displays a message to the user.
     * @param string The message text to be displayed
     */
    public void showMessage(String string);
	/**
	 * Shows the dice roll specifically for utilities and returns the roll result.
	 * @return An integer representing the utility dice roll value.
	 */
	public int showUtilDiceRoll();
	/**
	 * Initiates the start of the game.
	 */
	public void startGame();
	/**
	 * Updates the GUI to reflect the current game state.
	 */
	public void update();
}
