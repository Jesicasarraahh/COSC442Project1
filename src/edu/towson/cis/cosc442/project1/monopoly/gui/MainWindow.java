package edu.towson.cis.cosc442.project1.monopoly.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import edu.towson.cis.cosc442.project1.monopoly.*;

public class MainWindow extends JFrame implements MonopolyGUI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel eastPanel = new JPanel();
	ArrayList<GUICell> guiCells = new ArrayList<GUICell>();

	JPanel northPanel = new JPanel();
	PlayerPanel[] playerPanels;
	JPanel southPanel = new JPanel();
	JPanel westPanel = new JPanel();

	/**
	 * Constructs the main window for the Monopoly GUI and initializes its layout and panels.
	 */
	public MainWindow() {
		northPanel.setBorder(new LineBorder(Color.BLACK));
		southPanel.setBorder(new LineBorder(Color.BLACK));
		westPanel.setBorder(new LineBorder(Color.BLACK));
		eastPanel.setBorder(new LineBorder(Color.BLACK));
		
		Container c = getContentPane();
		//setSize(800, 600);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		setSize(d);
		c.add(northPanel, BorderLayout.NORTH);
		c.add(southPanel, BorderLayout.SOUTH);
		c.add(eastPanel, BorderLayout.EAST);
		c.add(westPanel, BorderLayout.WEST);
		
		this.addWindowListener(new WindowAdapter(){
			/**
			 * Handles the window closing event by terminating the application.
			 * @param e the window event triggered when the window is closing
			 */
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	/**
	 * Adds GUI cells representing game board cells to the given panel and stores them for reference.
	 * @param panel the JPanel to add the GUI cells to
	 * @param cells the list of cells from the game board to represent
	 */
	private void addCells(JPanel panel, List<?> cells) {
		for(int x=0; x<cells.size(); x++) {
			GUICell cell = new GUICell((Cell)cells.get(x));
			panel.add(cell);
			guiCells.add(cell);
		}
	}
	
	/**
	 * Creates and displays player panels for all players currently in the game.
	 */
	private void buildPlayerPanels() {
		GameMaster master = GameMaster.instance();
		JPanel infoPanel = new JPanel();
        int players = master.getNumberOfPlayers();
        infoPanel.setLayout(new GridLayout(2, (players+1)/2));
		getContentPane().add(infoPanel, BorderLayout.CENTER);
		playerPanels = new PlayerPanel[master.getNumberOfPlayers()];
		for (int i = 0; i< master.getNumberOfPlayers(); i++){
			playerPanels[i] = new PlayerPanel(master.getPlayer(i));
			infoPanel.add(playerPanels[i]);
			playerPanels[i].displayInfo();
		}
	}

	/**
	 * Enables the "End Turn" button for the specified player panel.
	 * @param playerIndex the index of the player
	 */
	public void enableEndTurnBtn(int playerIndex) {
		playerPanels[playerIndex].setEndTurnEnabled(true);
	}
	
	/**
	 * Enables the "Roll Dice" button for the specified player's turn.
	 * @param playerIndex the index of the player
	 */
	public void enablePlayerTurn(int playerIndex) {
		playerPanels[playerIndex].setRollDiceEnabled(true);
		
	}

	/**
	 * Enables the "Purchase Property" button for the specified player panel.
	 * @param playerIndex the index of the player
	 */
	public void enablePurchaseBtn(int playerIndex) {
		playerPanels[playerIndex].setPurchasePropertyEnabled(true);
	}

	@SuppressWarnings("deprecation")
	/**
	 * Displays a dialog to get a dice roll from the user and returns the dice values.
	 * @return an int array containing the values of the dice rolled
	 */
	public int[] getDiceRoll() {
		TestDiceRollDialog dialog = new TestDiceRollDialog(this);
		dialog.show();
		return dialog.getDiceRoll();
	}

    /**
     * Checks if the current player's "Draw Card" button is enabled.
     * @return true if the button is enabled; false otherwise
     */
    public boolean isDrawCardButtonEnabled() {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        return playerPanels[currentPlayerIndex].isDrawCardButtonEnabled();
    }

    /**
     * Checks if the current player's "End Turn" button is enabled.
     * @return true if the button is enabled; false otherwise
     */
    public boolean isEndTurnButtonEnabled() {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        return playerPanels[currentPlayerIndex].isEndTurnButtonEnabled();
    }

	/**
	 * Checks if the current player's "Get Out of Jail" button is enabled.
	 * @return true if the button is enabled; false otherwise
	 */
	public boolean isGetOutOfJailButtonEnabled() {
		int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
		return playerPanels[currentPlayerIndex].isGetOutOfJailButtonEnabled();
	}

    /**
     * Checks if the "Trade" button is enabled for the specified player.
     * @param i the index of the player
     * @return true if enabled; false otherwise
     */
    public boolean isTradeButtonEnabled(int i) {
        return playerPanels[i].isTradeButtonEnabled();
    }
	
	/**
	 * Moves the specified player visually from one cell to another on the GUI board.
	 * @param index the player index
	 * @param from the starting cell index
	 * @param to the destination cell index
	 */
	public void movePlayer(int index, int from, int to) {
		GUICell fromCell = queryCell(from);
		GUICell toCell = queryCell(to);
		fromCell.removePlayer(index);
		toCell.addPlayer(index);
	}

    @SuppressWarnings("deprecation")
	/**
	 * Opens a dialog for responding to a trade deal and returns the dialog.
	 * @param deal the trade deal to respond to
	 * @return the RespondDialog used for responding
	 */
	public RespondDialog openRespondDialog(TradeDeal deal) {
        GUIRespondDialog dialog = new GUIRespondDialog();
        dialog.setDeal(deal);
        dialog.show();
        return dialog;
    }

    @SuppressWarnings("deprecation")
	/**
	 * Opens a dialog to initiate a trade between players and returns the dialog.
	 * @return the TradeDialog for creating trades
	 */
	public TradeDialog openTradeDialog() {
        GUITradeDialog dialog = new GUITradeDialog(this);
        dialog.show();
        return dialog;
    }
	
	/**
	 * Retrieves the GUI representation of the cell at the specified index.
	 * @param index the index of the cell on the game board
	 * @return the GUICell corresponding to the given index
	 */
	private GUICell queryCell(int index) {
		Cell cell = GameMaster.instance().getGameBoard().getCell(index);
		for(int x = 0; x < guiCells.size(); x++) {
			GUICell guiCell = (GUICell)guiCells.get(x);
			if(guiCell.getCell() == cell) return guiCell;
		}
		return null;
	}

    /**
     * Enables or disables the "Buy House" button for the current player.
     * @param b true to enable, false to disable
     */
    public void setBuyHouseEnabled(boolean b) {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        playerPanels[currentPlayerIndex].setBuyHouseEnabled(b);
    }

    /**
     * Enables or disables the "Draw Card" button for the current player.
     * @param b true to enable, false to disable
     */
    public void setDrawCardEnabled(boolean b) {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        playerPanels[currentPlayerIndex].setDrawCardEnabled(b);
    }

    /**
     * Enables or disables the "End Turn" button for the current player.
     * @param enabled true to enable, false to disable
     */
    public void setEndTurnEnabled(boolean enabled) {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        playerPanels[currentPlayerIndex].setEndTurnEnabled(enabled);
    }

    /**
     * Enables or disables the "Get Out of Jail" button for the current player.
     * @param b true to enable, false to disable
     */
    public void setGetOutOfJailEnabled(boolean b) {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        playerPanels[currentPlayerIndex].setGetOutOfJailEnabled(b);
    }

    /**
     * Enables or disables the "Purchase Property" button for the current player.
     * @param enabled true to enable, false to disable
     */
    public void setPurchasePropertyEnabled(boolean enabled) {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        playerPanels[currentPlayerIndex].setPurchasePropertyEnabled(enabled);
    }

    /**
     * Enables or disables the "Roll Dice" button for the current player.
     * @param b true to enable, false to disable
     */
    public void setRollDiceEnabled(boolean b) {
        int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
        playerPanels[currentPlayerIndex].setRollDiceEnabled(b);
    }

    /**
     * Enables or disables the "Trade" button for the specified player.
     * @param index the index of the player
     * @param b true to enable, false to disable
     */
    public void setTradeEnabled(int index, boolean b) {
        playerPanels[index].setTradeEnabled(b);
    }
	
	/**
	 * Sets up the GUI game board using the given game board layout and initializes player panels.
	 * @param board the GameBoard containing the cells to be displayed
	 */
	public void setupGameBoard(GameBoard board) {
		Dimension dimension = GameBoardUtil.calculateDimension(board.getCellNumber());
		northPanel.setLayout(new GridLayout(1, dimension.width + 2));
		southPanel.setLayout(new GridLayout(1, dimension.width + 2));
		westPanel.setLayout(new GridLayout(dimension.height, 1));
		eastPanel.setLayout(new GridLayout(dimension.height, 1));
		addCells(northPanel, GameBoardUtil.getNorthCells(board));
		addCells(southPanel, GameBoardUtil.getSouthCells(board));
		addCells(eastPanel, GameBoardUtil.getEastCells(board));
		addCells(westPanel, GameBoardUtil.getWestCells(board));
		buildPlayerPanels();
	}

    @SuppressWarnings("deprecation")
	/**
	 * Shows a dialog enabling the specified player to buy houses.
	 * @param currentPlayer the player who may buy houses
	 */
	public void showBuyHouseDialog(Player currentPlayer) {
        BuyHouseDialog dialog = new BuyHouseDialog(currentPlayer);
        dialog.show();
    }

    /**
     * Displays a message to the user in a dialog box.
     * @param msg the message to show
     */
    public void showMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg);
    }

	/**
	 * Displays a dialog to perform a utility dice roll and returns the result.
	 * @return the result of the utility dice roll
	 */
	public int showUtilDiceRoll() {
		return UtilDiceRoll.showDialog();
	}

	/**
	 * Initializes the game state by moving all players to the starting cell.
	 */
	public void startGame() {
		int numberOfPlayers = GameMaster.instance().getNumberOfPlayers();
		for(int i = 0; i < numberOfPlayers; i++) {
			movePlayer(i, 0, 0);
		}
	}

	/**
	 * Refreshes the display information for all player panels and GUI cells.
	 */
	public void update() {
		for(int i = 0; i < playerPanels.length; i++) {
			playerPanels[i].displayInfo();
		}
		for(int j = 0; j < guiCells.size(); j++ ) {
			GUICell cell = (GUICell)guiCells.get(j);
			cell.displayInfo();
		}
	}
}
