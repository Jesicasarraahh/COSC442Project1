package edu.towson.cis.cosc442.project1.monopoly;

public class JailCell extends Cell {
	public static int BAIL = 50;
	
	/**
	 * Constructs a JailCell with the name set to "Jail".
	 */
	public JailCell() {
		setName("Jail");
	}
	
	/**
	 * Defines the action to be performed when a player lands on this JailCell, currently with no implementation.
	 */
	public void playAction() {
		
	}
}
