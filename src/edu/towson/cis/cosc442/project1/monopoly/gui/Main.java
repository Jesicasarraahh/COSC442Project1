package edu.towson.cis.cosc442.project1.monopoly.gui;

import javax.swing.JOptionPane;

import edu.towson.cis.cosc442.project1.monopoly.*;

public class Main {

	/**
	 * Prompts the user to input the number of players and sets it in the GameMaster while validating the input.
	 * @param window The MainWindow instance used as the parent for dialog boxes.
	 * @return The validated number of players entered by the user.
	 */
	private static int inputNumberOfPlayers(MainWindow window) {
		int numPlayers = 0;
		while(numPlayers <= 0 || numPlayers > GameMaster.MAX_PLAYER) {
			String numberOfPlayers = JOptionPane.showInputDialog(window, "How many players");
			if(numberOfPlayers == null) {
				System.exit(0);
			}
			try {
				numPlayers = Integer.parseInt(numberOfPlayers);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(window, "Please input a number");
			}
			if (numPlayers <= 0 || numPlayers > GameMaster.MAX_PLAYER) {
				JOptionPane.showMessageDialog(window, "Please input a number between one and eight");
			} else {
				GameMaster.instance().setNumberOfPlayers(numPlayers);
			}
		}
		return numPlayers;
	}
	/**
	 * Creates and returns a new instance of a GameBoard subclass specified by its class name, handling any instantiation errors.
	 * @param className The fully qualified name of the GameBoard subclass to instantiate.
	 * @param window The MainWindow instance used to display error messages.
	 * @return The newly instantiated GameBoard object.
	 */
	public static GameBoard createGameBoard(String className, MainWindow window) {
		GameBoard gameBoard = null;
		try {
			Class<?> c = Class.forName(className);
			gameBoard = (GameBoard)c.newInstance();
		}
		catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(window, "Class Not Found.  Program will exit");
			System.exit(0);
		}
		catch (IllegalAccessException e ) {
			JOptionPane.showMessageDialog(window, "Illegal Access of Class.  Program will exit");
			System.exit(0);
		}
		catch (InstantiationException e) {
			JOptionPane.showMessageDialog(window, "Class Cannot be Instantiated.  Program will exit");
			System.exit(0);
		}
		return gameBoard;
	}

	@SuppressWarnings("deprecation")
	/**
	 * Initializes and starts the Monopoly game GUI and logic, optionally configuring test mode and game board via command line arguments.
	 * @param args An array of command-line arguments used to configure the game.
	 */
	public static void main(String[] args) {
		GameMaster master = GameMaster.instance();
		MainWindow window = new MainWindow();
		GameBoard gameBoard = null;
		//i think shifting the try and catch part to a completely new method would be better
		if(args.length > 0) {
			if(args[0].equals("test")) {
				master.setTestMode(true);
			}
			gameBoard = createGameBoard(args[1], window);
		/** 	try {
				Class<?> c = Class.forName(args[1]);
				gameBoard = (GameBoard)c.newInstance();
			}
			catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(window, "Class Not Found.  Program will exit");
				System.exit(0);
			}
			catch (IllegalAccessException e ) {
				JOptionPane.showMessageDialog(window, "Illegal Access of Class.  Program will exit");
				System.exit(0);
			}
			catch (InstantiationException e) {
				JOptionPane.showMessageDialog(window, "Class Cannot be Instantiated.  Program will exit");
				System.exit(0);
			}
				*/
		}
		else {
			gameBoard = new GameBoardFull();
		}
	
		
//      GameBoard gameBoard = new GameBoardFull();
//		GameBoard gameBoard = new GameBoardCCMovePlayer();
//		GameBoard gameBoard = new GameBoardCCLoseMoney();
//		GameBoard gameBoard = new GameBoardCCJail();
//		GameBoard gameBoard = new GameBoardUtility();
//		GameBoard gameBoard = new GameBoardRailRoad();
//		GameBoard gameBoard = new GameBoard14();
//		GameBoard gameBoard = new SimpleGameBoard();
//		GameBoard gameBoard = new GameBoardJail();
//		GameBoard gameBoard = new GameBoardFreeParking();

		master.setGameBoard(gameBoard);
		int numPlayers = inputNumberOfPlayers(window);
		for(int i = 0; i < numPlayers; i++) {
			String name = 
				JOptionPane.showInputDialog(window, "Please input name for Player " + (i+1));
			GameMaster.instance().getPlayer(i).setName(name);
		}
		window.setupGameBoard(gameBoard);
		window.show();
		master.setGUI(window);
		master.startGame();
	}
}
