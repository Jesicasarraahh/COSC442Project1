package edu.towson.cis.cosc442.project1.monopoly;

import java.util.ArrayList;
import java.util.Iterator;


public class GameMaster {

	private static GameMaster gameMaster;
	static final public int MAX_PLAYER = 8;	
	private Die[] dice;
	private GameBoard gameBoard;
	private MonopolyGUI gui;
	private int initAmountOfMoney;
	private ArrayList<Player> players = new ArrayList<Player>();
	private int turn = 0;
	private int utilDiceRoll;
	private boolean testMode;

	/**
	 * Returns the singleton instance of the GameMaster class, creating it if it does not exist.
	 * @return The singleton GameMaster instance.
	 */
	public static GameMaster instance() {
		if(gameMaster == null) {
			gameMaster = new GameMaster();
		}
		return gameMaster;
	}

	/**
	 * Constructs a new GameMaster instance initializing starting money and dice.
	 */
	public GameMaster() {
		initAmountOfMoney = 1500;
		dice = new Die[]{new Die(), new Die()};
	}

    /**
     * Handles the event when the 'Buy House' button is clicked, showing the buy house dialog for the current player.
     */
    public void btnBuyHouseClicked() {
        gui.showBuyHouseDialog(getCurrentPlayer());
    }

    /**
     * Handles the event when the 'Draw Card' button is clicked, drawing and applying the appropriate card action for the current player's position.
     * @return The Card drawn and applied for the current player.
     */
    public Card btnDrawCardClicked() {
        gui.setDrawCardEnabled(false);
        CardCell cell = (CardCell)getCurrentPlayer().getPosition();
        Card card = null;
        if(cell.getType() == Card.TYPE_CC) {
            card = getGameBoard().drawCCCard();
            card.applyAction();
        } else {
            card = getGameBoard().drawChanceCard();
            card.applyAction();
        }
        gui.setEndTurnEnabled(true);
        return card;
    }

    /**
     * Handles the event when the 'End Turn' button is clicked, processing the end of the current player's turn and updating the GUI accordingly.
     */
    public void btnEndTurnClicked() {
		setAllButtonEnabled(false);
		getCurrentPlayer().getPosition().playAction();
		if(getCurrentPlayer().isBankrupt()) {
			gui.setBuyHouseEnabled(false);
			gui.setDrawCardEnabled(false);
			gui.setEndTurnEnabled(false);
			gui.setGetOutOfJailEnabled(false);
			gui.setPurchasePropertyEnabled(false);
			gui.setRollDiceEnabled(false);
			gui.setTradeEnabled(getCurrentPlayerIndex(),false);
			updateGUI();
		}
		else {
			switchTurn();
			updateGUI();
		}
    }

    /**
     * Handles the event when the 'Get Out Of Jail' button is clicked, processing the current player's attempt to get out of jail and updating GUI buttons.
     */
    public void btnGetOutOfJailClicked() {
		getCurrentPlayer().getOutOfJail();
		if(getCurrentPlayer().isBankrupt()) {
			gui.setBuyHouseEnabled(false);
			gui.setDrawCardEnabled(false);
			gui.setEndTurnEnabled(false);
			gui.setGetOutOfJailEnabled(false);
			gui.setPurchasePropertyEnabled(false);
			gui.setRollDiceEnabled(false);
			gui.setTradeEnabled(getCurrentPlayerIndex(),false);
		}
		else {
			gui.setRollDiceEnabled(true);
			gui.setBuyHouseEnabled(getCurrentPlayer().canBuyHouse());
			gui.setGetOutOfJailEnabled(getCurrentPlayer().isInJail());
		}
    }

    /**
     * Handles the event when the 'Purchase Property' button is clicked, making the current player purchase the property and updating the GUI.
     */
    public void btnPurchasePropertyClicked() {
        Player player = getCurrentPlayer();
		player.purchase();
		gui.setPurchasePropertyEnabled(false);
		updateGUI();
    }
    
    /**
     * Handles the event when the 'Roll Dice' button is clicked, rolling the dice and moving the current player accordingly while updating the GUI.
     */
    public void btnRollDiceClicked() {
		int[] rolls = rollDice();
		if((rolls[0]+rolls[1]) > 0) {
			Player player = getCurrentPlayer();
			gui.setRollDiceEnabled(false);
			StringBuffer msg = new StringBuffer();
			msg.append(player.getName())
					.append(", you rolled ")
					.append(rolls[0])
					.append(" and ")
					.append(rolls[1]);
			gui.showMessage(msg.toString());
			movePlayer(player, rolls[0] + rolls[1]);
			gui.setBuyHouseEnabled(false);
		}
    }

    /**
     * Handles the event when the 'Trade' button is clicked, managing the trade dialog and completing the trade if accepted.
     */
    public void btnTradeClicked() {
        TradeDialog dialog = gui.openTradeDialog();
        TradeDeal deal = dialog.getTradeDeal();
        if(deal != null) {
            RespondDialog rDialog = gui.openRespondDialog(deal);
            if(rDialog.getResponse()) {
                completeTrade(deal);
                updateGUI();
            }
        }
    }

    /**
     * Completes a trade deal between the seller and current player by selling and buying the specified property and amount.
     * @param deal The trade deal containing player index, property name, and amount.
     */
    public void completeTrade(TradeDeal deal) {
        Player seller = getPlayer(deal.getPlayerIndex());
        Cell property = gameBoard.queryCell(deal.getPropertyName());
        seller.sellProperty(property, deal.getAmount());
        getCurrentPlayer().buyProperty(property, deal.getAmount());
    }

    /**
     * Draws a community chest card from the game board.
     * @return The drawn community chest Card.
     */
    public Card drawCCCard() {
        return gameBoard.drawCCCard();
    }

    /**
     * Draws a chance card from the game board.
     * @return The drawn chance Card.
     */
    public Card drawChanceCard() {
        return gameBoard.drawChanceCard();
    }

	
	/**
	 * Returns the player whose turn it currently is.
	 * @return The current Player.
	 */
	public Player getCurrentPlayer() {
		return getPlayer(turn);
	}
    
    /**
     * Returns the index of the current player whose turn it is.
     * @return The index of the current player.
     */
    public int getCurrentPlayerIndex() {
        return turn;
    }

	/**
	 * Returns the game board instance used in the game.
	 * @return The GameBoard instance.
	 */
	public GameBoard getGameBoard() {
		return gameBoard;
	}

    /**
     * Returns the GUI instance used for the game interface.
     * @return The MonopolyGUI instance.
     */
    public MonopolyGUI getGUI() {
        return gui;
    }

	/**
	 * Returns the initial amount of money assigned to each player at game start.
	 * @return The initial amount of money per player.
	 */
	public int getInitAmountOfMoney() {
		return initAmountOfMoney;
	}
	
	/**
	 * Returns the current number of players in the game.
	 * @return The number of players.
	 */
	public int getNumberOfPlayers() {
		return players.size();
	}

    /**
     * Returns the number of potential sellers excluding the current player.
     * @return The number of other players available to sell properties.
     */
    public int getNumberOfSellers() {
        return players.size() - 1;
    }

	/**
	 * Returns the player at the specified index.
	 * @param index The index of the player to retrieve.
	 * @return The Player at the specified index.
	 */
	public Player getPlayer(int index) {
		return (Player)players.get(index);
	}
	
	/**
	 * Returns the index of the specified player in the players list.
	 * @param player The Player whose index is to be found.
	 * @return The index of the specified player.
	 */
	public int getPlayerIndex(Player player) {
		return players.indexOf(player);
	}

    /**
     * Returns a list of players excluding the current player, representing potential sellers.
     * @return An ArrayList of players other than the current player.
     */
    public ArrayList<Player> getSellerList() {
        ArrayList<Player> sellers = new ArrayList<Player>();
        for (Iterator<Player> iter = players.iterator(); iter.hasNext();) {
            Player player = (Player) iter.next();
            if(player != getCurrentPlayer()) sellers.add(player);
        }
        return sellers;
    }

	/**
	 * Returns the index of the current turn's player.
	 * @return The current turn index.
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * Returns the stored utility dice roll value.
	 * @return The utility dice roll value.
	 */
	public int getUtilDiceRoll() {
		return this.utilDiceRoll;
	}

	/**
	 * Moves the player at the given index forward by the specified dice value, handling passing Go and updating the GUI.
	 * @param playerIndex The index of the player to move.
	 * @param diceValue The dice roll amount to move the player.
	 */
	public void movePlayer(int playerIndex, int diceValue) {
		Player player = (Player)players.get(playerIndex);
		movePlayer(player, diceValue);
	}
	
	/**
	 * Moves the specified player forward by the dice value, crediting $200 if passing Go, updates GUI and triggers player movement effects.
	 * @param player The Player to move.
	 * @param diceValue The dice roll amount to move the player.
	 */
	public void movePlayer(Player player, int diceValue) {
		Cell currentPosition = player.getPosition();
		int positionIndex = gameBoard.queryCellIndex(currentPosition.getName());
		int newIndex = (positionIndex+diceValue)%gameBoard.getCellNumber();
		if(newIndex <= positionIndex || diceValue > gameBoard.getCellNumber()) {
			player.setMoney(player.getMoney() + 200);
		}
		player.setPosition(gameBoard.getCell(newIndex));
		gui.movePlayer(getPlayerIndex(player), positionIndex, newIndex);
		playerMoved(player);
		updateGUI();
	}

	/**
	 * Handles post-move logic for the player such as enabling GUI buttons based on the player's new position.
	 * @param player The Player who has moved.
	 */
	public void playerMoved(Player player) {
		Cell cell = player.getPosition();
		int playerIndex = getPlayerIndex(player);
		if(cell instanceof CardCell) {
		    gui.setDrawCardEnabled(true);
		} else{
			if(cell.isAvailable()) {
				int price = cell.getPrice();
				if(price <= player.getMoney() && price > 0) {
					gui.enablePurchaseBtn(playerIndex);
				}
			}	
			gui.enableEndTurnBtn(playerIndex);
		}
        gui.setTradeEnabled(turn, false);
	}

	/**
	 * Resets the game by returning all players to start and removing cards, resetting turn to zero.
	 */
	public void reset() {
		for(int i = 0; i < getNumberOfPlayers(); i++){
			Player player = (Player)players.get(i);
			player.setPosition(gameBoard.getCell(0));
		}
		if(gameBoard != null) gameBoard.removeCards();
		turn = 0;
	}
	
	/**
	 * Rolls two dice or retrieves the dice roll from GUI in test mode.
	 * @return An int array of two dice roll values.
	 */
	public int[] rollDice() {
		if(testMode) {
			return gui.getDiceRoll();
		}
		else {
			return new int[]{
					dice[0].getRoll(),
					dice[1].getRoll()
			};
		}
	}
	
	/**
	 * Sends the specified player to the Jail cell, updates their status and GUI position accordingly.
	 * @param player The Player to send to jail.
	 */
	public void sendToJail(Player player) {
	    int oldPosition = gameBoard.queryCellIndex(getCurrentPlayer().getPosition().getName());
		player.setPosition(gameBoard.queryCell("Jail"));
		player.setInJail(true);
		int jailIndex = gameBoard.queryCellIndex("Jail");
		gui.movePlayer(
		        getPlayerIndex(player),
		        oldPosition,
		        jailIndex);
	}
    
	/**
	 * Enables or disables all relevant GUI buttons based on the given enabled flag.
	 * @param enabled True to enable all buttons, false to disable.
	 */
	private void setAllButtonEnabled(boolean enabled) {
		gui.setRollDiceEnabled(enabled);
		gui.setPurchasePropertyEnabled(enabled);
		gui.setEndTurnEnabled(enabled);
        gui.setTradeEnabled(turn, enabled);
        gui.setBuyHouseEnabled(enabled);
        gui.setDrawCardEnabled(enabled);
        gui.setGetOutOfJailEnabled(enabled);
	}

	/**
	 * Sets the game board instance used by the game master.
	 * @param board The GameBoard instance to set.
	 */
	public void setGameBoard(GameBoard board) {
		this.gameBoard = board;
	}
	
	/**
	 * Sets the GUI instance used by the game master.
	 * @param gui The MonopolyGUI instance to set.
	 */
	public void setGUI(MonopolyGUI gui) {
		this.gui = gui;
	}

	/**
	 * Sets the initial amount of money each player starts with.
	 * @param money The initial money amount to assign to players.
	 */
	public void setInitAmountOfMoney(int money) {
		this.initAmountOfMoney = money;
	}

	/**
	 * Sets the number of players in the game and initializes them with the starting money.
	 * @param number The number of players to set.
	 */
	public void setNumberOfPlayers(int number) {
		players.clear();
		for(int i =0;i<number;i++) {
			Player player = new Player();
			player.setMoney(initAmountOfMoney);
			players.add(player);
		}
	}

	/**
	 * Sets the utility dice roll value used internally for dice rolling.
	 * @param diceRoll The dice roll value to set.
	 */
	public void setUtilDiceRoll(int diceRoll) {
		this.utilDiceRoll = diceRoll;
	}
	
	/**
	 * Starts the game by initializing the GUI and enabling the first player's turn.
	 */
	public void startGame() {
		gui.startGame();
		gui.enablePlayerTurn(0);
        gui.setTradeEnabled(0, true);
	}

	/**
	 * Advances the turn to the next player, enabling appropriate GUI controls based on player jail status.
	 */
	public void switchTurn() {
		turn = (turn + 1) % getNumberOfPlayers();
		if(!getCurrentPlayer().isInJail()) {
			gui.enablePlayerTurn(turn);
			gui.setBuyHouseEnabled(getCurrentPlayer().canBuyHouse());
            gui.setTradeEnabled(turn, true);
		}
		else {
			gui.setGetOutOfJailEnabled(true);
		}
	}
	
	/**
	 * Updates the graphical user interface to reflect the current game state.
	 */
	public void updateGUI() {
		gui.update();
	}

	/**
	 * Prompts the GUI to perform a utility dice roll and stores the result.
	 */
	public void utilRollDice() {
		this.utilDiceRoll = gui.showUtilDiceRoll();
	}

	/**
	 * Enables or disables test mode for dice rolling behavior.
	 * @param b True to enable test mode, false to disable.
	 */
	public void setTestMode(boolean b) {
		testMode = b;
	}
}
