package edu.towson.cis.cosc442.project1.monopoly;

import java.util.ArrayList;
import java.util.Hashtable;

public class GameBoard {

	private ArrayList<Cell> cells = new ArrayList<Cell>();
    private ArrayList<Card> chanceCards = new ArrayList<Card>();
	//the key of colorGroups is the name of the color group.
	private Hashtable<String, Integer> colorGroups = new Hashtable<String, Integer>();
	private ArrayList<Card> communityChestCards = new ArrayList<Card>();
	/**
	 * Initializes a new game board and adds the starting Go cell.
	 */
	public GameBoard() {
		Cell go = new GoCell();
		addCell(go);
	}

    /**
     * Adds a card to the appropriate deck based on its type (Community Chest or Chance).
     * @param card the card to be added
     */
    public void addCard(Card card) {
        if(card.getCardType() == Card.TYPE_CC) {
            communityChestCards.add(card);
        } else {
            chanceCards.add(card);
        }
    }
	
	/**
	 * Adds a general cell to the game board.
	 * @param cell the cell to add to the board
	 */
	public void addCell(Cell cell) {
		cells.add(cell);
	}
	
	/**
	 * Adds a property cell to the game board and updates the count of properties in its color group.
	 * @param cell the property cell to add
	 */
	public void addCell(PropertyCell cell) {
		String colorGroup = cell.getColorGroup();
		int propertyNumber = getPropertyNumberForColor(colorGroup);
		colorGroups.put(cell.getColorGroup(), new Integer(propertyNumber + 1));
        cells.add(cell);
	}

    /**
     * Draws the top card from the Community Chest deck, returns it, and places it back at the bottom.
     * @return the drawn Community Chest card
     */
    public Card drawCCCard() {
        Card card = communityChestCards.get(0);
        communityChestCards.remove(0);
        addCard(card);
        return card;
    }

    /**
     * Draws the top card from the Chance deck, returns it, and places it back at the bottom.
     * @return the drawn Chance card
     */
    public Card drawChanceCard() {
        Card card = chanceCards.get(0);
        chanceCards.remove(0);
        addCard(card);
        return card;
    }

	/**
	 * Retrieves the cell located at the given index on the game board.
	 * @param newIndex the index of the cell to retrieve
	 * @return the cell at the specified index
	 */
	public Cell getCell(int newIndex) {
		return cells.get(newIndex);
	}
	
	/**
	 * Returns the total number of cells currently on the game board.
	 * @return the count of cells on the board
	 */
	public int getCellNumber() {
		return cells.size();
	}
	
	/**
	 * Returns an array of property cells belonging to the specified color group.
	 * @param color the color group name to query
	 * @return an array of property cells in the specified color group
	 */
	public PropertyCell[] getPropertiesInMonopoly(String color) {
		PropertyCell[] monopolyCells = 
			new PropertyCell[getPropertyNumberForColor(color)];
		int counter = 0;
		for (int i = 0; i < getCellNumber(); i++) {
			Cell c = getCell(i);
			if(c instanceof PropertyCell) {
				PropertyCell pc = (PropertyCell)c;
				if(pc.getColorGroup().equals(color)) {
					monopolyCells[counter] = pc;
					counter++;
				}
			}
		}
		return monopolyCells;
	}
	
	/**
	 * Returns the number of properties available for a given color group.
	 * @param name the name of the color group
	 * @return the count of properties in the specified color group
	 */
	public int getPropertyNumberForColor(String name) {
		Integer number = (Integer)colorGroups.get(name);
		if(number != null) {
			return number.intValue();
		}
		return 0;
	}

	/**
	 * Searches for a cell by its name and returns it if found.
	 * @param string the name of the cell to find
	 * @return the cell matching the given name or null if not found
	 */
	public Cell queryCell(String string) {
		for(int i = 0; i < cells.size(); i++){
			Cell temp = cells.get(i); 
			if(temp.getName().equals(string)) {
				return temp;
			}
		}
		return null;
	}
	
	/**
	 * Finds the index of a cell by its name, returning -1 if not found.
	 * @param string the name of the cell to locate
	 * @return the index of the cell or -1 if not found
	 */
	public int queryCellIndex(String string){
		for(int i = 0; i < cells.size(); i++){
			Cell temp = cells.get(i); 
			if(temp.getName().equals(string)) {
				return i;
			}
		}
		return -1;
	}

    /**
     * Clears all cards from the Community Chest deck.
     */
    public void removeCards() {
        communityChestCards.clear();
    }
}
