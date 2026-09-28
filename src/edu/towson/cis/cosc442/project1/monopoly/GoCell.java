package edu.towson.cis.cosc442.project1.monopoly;

public class GoCell extends Cell {
	/**
	 * Constructs a GoCell with the name set to "Go" and marks it as unavailable.
	 */
	public GoCell() {
		super.setName("Go");
		setAvailable(false);
	}

	/**
	 * Performs the action associated with landing on the Go cell, though currently it has no implementation.
	 */
	public void playAction() {
	}
	
	/**
	 * Overrides the name setter method but currently provides no implementation to change the name.
	 * @param name the new name intended to be set for the cell
	 */
	void setName(String name) {
	}
}
