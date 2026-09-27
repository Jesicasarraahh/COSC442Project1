package edu.towson.cis.cosc442.project1.monopoly;

public abstract class Cell {
	private boolean available = true;
	private String name;
	protected Player theOwner;

	/**
	 * Returns the name of this cell.
	 * @return The name of the cell.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the player who owns this cell.
	 * @return The owner of the cell, or null if none.
	 */
	public Player getTheOwner() {
		return theOwner;
	}
	
	/**
	 * Returns the price of this cell, which defaults to zero in the base class.
	 * @return The price value of the cell.
	 */
	public int getPrice() {
		return 0;
	}

	/**
	 * Indicates whether this cell is currently available.
	 * @return True if the cell is available, false otherwise.
	 */
	public boolean isAvailable() {
		return available;
	}
	
	/**
	 * Executes the action associated with landing on this cell.
	 */
	public abstract void playAction();

	/**
	 * Sets the availability status of this cell.
	 * @param available The new availability status to set.
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	/**
	 * Sets the name of this cell.
	 * @param name The new name to assign to the cell.
	 */
	void setName(String name) {
		this.name = name;
	}

	/**
	 * Assigns the owner of this cell to the specified player.
	 * @param owner The player to set as the owner.
	 */
	public void setTheOwner(Player owner) {
		this.theOwner = owner;
	}
    
    /**
     * Returns a string representation of the cell, which is its name.
     * @return The name of the cell as a string.
     */
    public String toString() {
        return name;
    }
}
