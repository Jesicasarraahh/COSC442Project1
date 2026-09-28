package edu.towson.cis.cosc442.project1.monopoly;

public abstract class Card {

    public static int TYPE_CHANCE = 1;
    public static int TYPE_CC = 2;

    /**
     * Returns the label text of the card.
     * @return the label text of the card
     */
    public abstract String getLabel();
    /**
     * Executes the action associated with this card.
     */
    public abstract void applyAction();
    /**
     * Retrieves the type identifier of the card.
     * @return the integer representing the card type
     */
    public abstract int getCardType();
}
