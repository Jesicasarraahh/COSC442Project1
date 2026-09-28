package edu.towson.cis.cosc442.project1.monopoly;

public class MoneyCard extends Card {
    private int amount;
    private int cardType;
    
    private String label;
    
    /**
     * Constructs a MoneyCard with the specified label, amount, and card type.
     * @param label the descriptive label of the MoneyCard
     * @param amount the monetary amount associated with the card's action
     * @param cardType the integer representing the type of this card
     */
    public MoneyCard(String label, int amount, int cardType){
        this.label = label;
        this.amount = amount;
        this.cardType = cardType;
    }

    /**
     * Applies the card's effect by adjusting the current player's money by the card's amount.
     */
    public void applyAction() {
        Player currentPlayer = GameMaster.instance().getCurrentPlayer();
		currentPlayer.setMoney(currentPlayer.getMoney() + amount);
    }

    /**
     * Returns the type identifier of this MoneyCard.
     * @return the integer representing the card's type
     */
    public int getCardType() {
        return cardType;
    }

    /**
     * Returns the label describing this MoneyCard.
     * @return the label string of the card
     */
    public String getLabel() {
        return label;
    }
}
