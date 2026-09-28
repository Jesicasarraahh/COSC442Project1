package edu.towson.cis.cosc442.project1.monopoly;

public class TradeDeal {
    private int amount;
    private int playerIndex;
    private String propertyName;

    /**
     * Returns the amount of money involved in the trade deal.
     * @return the amount of money in the trade deal
     */
    public int getAmount() {
        return amount;
    }
    
    /**
     * Returns the index of the player selling the property in the trade deal.
     * @return the index of the seller player
     */
    public int getPlayerIndex() {
        return playerIndex;
    }
    
    /**
     * Returns the name of the property involved in the trade deal.
     * @return the property name
     */
    public String getPropertyName() {
        return propertyName;
    }
    
    /**
     * Constructs a message describing the trade deal and prompts the seller to respond.
     * @return a string message representing the trade offer and prompt
     */
    public String makeMessage() {
        return GameMaster.instance().getCurrentPlayer() + 
        	" wishes to purchase " +
        	propertyName + " from " + 
        	GameMaster.instance().getPlayer(playerIndex) +
        	" for " + amount + ".  " + 
        	GameMaster.instance().getPlayer(playerIndex) +
        	", do you wish to trade your property?";
    }
    
    /**
     * Sets the amount of money involved in the trade deal.
     * @param amount the amount of money to set for the trade
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    /**
     * Sets the name of the property involved in the trade deal.
     * @param propertyName the property name to set
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    
    /**
     * Sets the index of the player selling the property in the trade deal.
     * @param playerIndex the seller player's index to set
     */
    public void setSellerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }
}
