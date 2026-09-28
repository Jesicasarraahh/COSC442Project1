package edu.towson.cis.cosc442.project1.monopoly;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;


public class Player {
	//the key of colorGroups is the name of the color group.
	private Hashtable<String, Integer> colorGroups = new Hashtable<String, Integer>();
	private boolean inJail;
	private int money;
	private String name;

	private Cell position;
	private ArrayList<PropertyCell> properties = new ArrayList<PropertyCell>();
	private ArrayList<Cell> railroads = new ArrayList<Cell>();
	private ArrayList<Cell> utilities = new ArrayList<Cell>();
	
	/**
	 * Initializes a new Player positioned at the 'Go' cell and not in jail.
	 */
	public Player() {
		GameBoard gb = GameMaster.instance().getGameBoard();
		inJail = false;
		if(gb != null) {
			position = gb.queryCell("Go");
		}
	}

    /**
     * Assigns ownership of a property to the player and deducts the purchase amount from the player's money.
     * @param property The property cell to be purchased.
     * @param amount The amount paid for the property.
     */
    public void buyProperty(Cell property, int amount) {
        property.setTheOwner(this);
        if(property instanceof PropertyCell) {
            PropertyCell cell = (PropertyCell)property;
            properties.add(cell);
            colorGroups.put(
                    cell.getColorGroup(), 
                    new Integer(getPropertyNumberForColor(cell.getColorGroup())+1));
        }
        if(property instanceof RailRoadCell) {
            railroads.add(property);
            colorGroups.put(
                    RailRoadCell.COLOR_GROUP, 
                    new Integer(getPropertyNumberForColor(RailRoadCell.COLOR_GROUP)+1));
        }
        if(property instanceof UtilityCell) {
            utilities.add(property);
            colorGroups.put(
                    UtilityCell.COLOR_GROUP, 
                    new Integer(getPropertyNumberForColor(UtilityCell.COLOR_GROUP)+1));
        }
        setMoney(getMoney() - amount);
    }
	
	/**
	 * Determines if the player can buy houses by checking if they have any monopolies.
	 * @return True if the player owns at least one monopoly, false otherwise.
	 */
	public boolean canBuyHouse() {
		return (getMonopolies().length != 0);
	}

	/**
	 * Checks if the player owns a property with the given name.
	 * @param property The name of the property to check ownership for.
	 * @return True if the player owns the specified property, false otherwise.
	 */
	public boolean checkProperty(String property) {
		for(int i=0;i<properties.size();i++) {
			Cell cell = (Cell)properties.get(i);
			if(cell.getName().equals(property)) {
				return true;
			}
		}
		return false;
		
	}
	
	/**
	 * Transfers all properties from this player to another player or resets them if the other player is null.
	 * @param player The player to receive the properties, or null to release ownership.
	 */
	public void exchangeProperty(Player player) {
		for(int i = 0; i < getPropertyNumber(); i++ ) {
			PropertyCell cell = getProperty(i);
			cell.setTheOwner(player);
			if(player == null) {
				cell.setAvailable(true);
				cell.setNumHouses(0);
			}
			else {
				player.properties.add(cell);
				colorGroups.put(
						cell.getColorGroup(), 
						new Integer(getPropertyNumberForColor(cell.getColorGroup())+1));
			}
		}
		properties.clear();
	}
    
    /**
     * Retrieves all properties, including standard properties, railroads, and utilities, owned by the player.
     * @return An array of Cells representing all properties owned by the player.
     */
    public Cell[] getAllProperties() {
        ArrayList<Cell> list = new ArrayList<Cell>();
        list.addAll(properties);
        list.addAll(utilities);
        list.addAll(railroads);
        return (Cell[])list.toArray(new Cell[list.size()]);
    }

	/**
	 * Returns the current amount of money the player has.
	 * @return The player's current money amount.
	 */
	public int getMoney() {
		return this.money;
	}
	
	/**
	 * Gets the color groups for which the player owns all properties, indicating monopolies.
	 * @return An array of color group names where the player has a monopoly.
	 */
	public String[] getMonopolies() {
		ArrayList<String> monopolies = new ArrayList<String>();
		Enumeration<String> colors = colorGroups.keys();
		while(colors.hasMoreElements()) {
			String color = (String)colors.nextElement();
            if(!(color.equals(RailRoadCell.COLOR_GROUP)) && !(color.equals(UtilityCell.COLOR_GROUP))) {
    			Integer num = (Integer)colorGroups.get(color);
    			GameBoard gameBoard = GameMaster.instance().getGameBoard();
    			if(num.intValue() == gameBoard.getPropertyNumberForColor(color)) {
    				monopolies.add(color);
    			}
            }
		}
		return (String[])monopolies.toArray(new String[monopolies.size()]);
	}

	/**
	 * Returns the name of the player.
	 * @return The player's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Pays bail to get the player out of jail, resetting their jail status and updating the game state accordingly.
	 */
	public void getOutOfJail() {
		money -= JailCell.BAIL;
		if(isBankrupt()) {
			money = 0;
			exchangeProperty(null);
		}
		inJail = false;
		GameMaster.instance().updateGUI();
	}

	/**
	 * Returns the current position of the player on the game board.
	 * @return The Cell representing the player's current position.
	 */
	public Cell getPosition() {
		return this.position;
	}
	
	/**
	 * Returns the property owned by the player at the specified index.
	 * @param index The index of the property in the player's property list.
	 * @return The PropertyCell at the specified index.
	 */
	public PropertyCell getProperty(int index) {
		return (PropertyCell)properties.get(index);
	}
	
	/**
	 * Returns the number of properties owned by the player.
	 * @return The count of properties the player owns.
	 */
	public int getPropertyNumber() {
		return properties.size();
	}

	/**
	 * Returns the number of properties the player owns within the specified color group.
	 * @param name The name of the color group.
	 * @return The count of properties owned in the specified color group.
	 */
	private int getPropertyNumberForColor(String name) {
		Integer number = (Integer)colorGroups.get(name);
		if(number != null) {
			return number.intValue();
		}
		return 0;
	}

	/**
	 * Checks if the player is bankrupt based on their money balance.
	 * @return True if the player has zero or negative money, false otherwise.
	 */
	public boolean isBankrupt() {
		return money <= 0;
	}

	/**
	 * Checks whether the player is currently in jail.
	 * @return True if the player is in jail, false otherwise.
	 */
	public boolean isInJail() {
		return inJail;
	}

	/**
	 * Returns the number of railroads owned by the player.
	 * @return The count of railroads owned by the player.
	 */
	public int numberOfRR() {
		return getPropertyNumberForColor(RailRoadCell.COLOR_GROUP);
	}

	/**
	 * Returns the number of utilities owned by the player.
	 * @return The count of utilities owned by the player.
	 */
	public int numberOfUtil() {
		return getPropertyNumberForColor(UtilityCell.COLOR_GROUP);
	}
	
	/**
	 * Pays rent to another player, adjusting money and transferring properties if bankruptcy occurs.
	 * @param owner The player receiving the rent.
	 * @param rentValue The amount of rent to be paid.
	 */
	public void payRentTo(Player owner, int rentValue) {
		if(money < rentValue) {
			owner.money += money;
			money -= rentValue;
		}
		else {
			money -= rentValue;
			owner.money +=rentValue;
		}
		if(isBankrupt()) {
			money = 0;
			exchangeProperty(owner);
		}
	}
	
	/**
	 * Attempts to purchase the property at the player's current position if it is available.
	 */
	public void purchase() {
		if(getPosition().isAvailable()) {
			Cell c = getPosition();
			c.setAvailable(false);
			if(c instanceof PropertyCell) {
				PropertyCell cell = (PropertyCell)c;
				purchaseProperty(cell);
			}
			if(c instanceof RailRoadCell) {
				RailRoadCell cell = (RailRoadCell)c;
				purchaseRailRoad(cell);
			}
			if(c instanceof UtilityCell) {
				UtilityCell cell = (UtilityCell)c;
				purchaseUtility(cell);
			}
		}
	}
	
	/**
	 * Purchases the specified number of houses for all properties in a selected monopoly if the player has sufficient funds.
	 * @param selectedMonopoly The name of the monopoly color group where houses will be purchased.
	 * @param houses The number of houses to purchase per property.
	 */
	public void purchaseHouse(String selectedMonopoly, int houses) {
		GameBoard gb = GameMaster.instance().getGameBoard();
		PropertyCell[] cells = gb.getPropertiesInMonopoly(selectedMonopoly);
		if((money >= (cells.length * (cells[0].getHousePrice() * houses)))) {
			for(int i = 0; i < cells.length; i++) {
				int newNumber = cells[i].getNumHouses() + houses;
				if (newNumber <= 5) {
					cells[i].setNumHouses(newNumber);
					this.setMoney(money - (cells[i].getHousePrice() * houses));
					GameMaster.instance().updateGUI();
				}
			}
		}
	}
	
	/**
	 * Purchases a specific standard property for the player by paying its price.
	 * @param cell The PropertyCell to purchase.
	 */
	private void purchaseProperty(PropertyCell cell) {
        buyProperty(cell, cell.getPrice());
	}

	/**
	 * Purchases a railroad property for the player by paying its price.
	 * @param cell The RailRoadCell to purchase.
	 */
	private void purchaseRailRoad(RailRoadCell cell) {
	    buyProperty(cell, cell.getPrice());
	}

	/**
	 * Purchases a utility property for the player by paying its price.
	 * @param cell The UtilityCell to purchase.
	 */
	private void purchaseUtility(UtilityCell cell) {
	    buyProperty(cell, cell.getPrice());
	}

    /**
     * Sells a property owned by the player, removing ownership and adding the sale amount to the player's money.
     * @param property The property cell to sell.
     * @param amount The amount received from selling the property.
     */
    public void sellProperty(Cell property, int amount) {
        property.setTheOwner(null);
        if(property instanceof PropertyCell) {
            properties.remove(property);
        }
        if(property instanceof RailRoadCell) {
            railroads.remove(property);
        }
        if(property instanceof UtilityCell) {
            utilities.remove(property);
        }
        setMoney(getMoney() + amount);
    }

	/**
	 * Sets the player's jail status.
	 * @param inJail True if the player is in jail; false otherwise.
	 */
	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}

	/**
	 * Updates the player's money to the specified amount.
	 * @param money The new amount of money for the player.
	 */
	public void setMoney(int money) {
		this.money = money;
	}

	/**
	 * Sets the player's name.
	 * @param name The new name for the player.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the player's position on the game board to the specified cell.
	 * @param newPosition The new position cell for the player.
	 */
	public void setPosition(Cell newPosition) {
		this.position = newPosition;
	}

    /**
     * Returns the string representation of the player, which is their name.
     * @return The player's name as a string.
     */
    public String toString() {
        return name;
    }
    
    /**
     * Clears all properties, railroads, and utilities owned by the player.
     */
    public void resetProperty() {
    	properties = new ArrayList<PropertyCell>();
    	railroads = new ArrayList<Cell>();
    	utilities = new ArrayList<Cell>();
	}
}
