package edu.towson.cis.cosc442.project1.monopoly;


public class JailCard extends Card {
    int type;
    
    /**
     * Constructs a JailCard with a specified card type identifier.
     * @param cardType the integer representing the type of the jail card
     */
    public JailCard(int cardType) {
        type = cardType;
    }

    /**
     * Executes the action of sending the current player to jail without collecting $200.
     */
    public void applyAction() {
        Player currentPlayer = GameMaster.instance().getCurrentPlayer();
		GameMaster.instance().getGameBoard().queryCell("Jail");
		GameMaster.instance().sendToJail(currentPlayer);
    }

    /**
     * Retrieves the integer type identifier of this jail card.
     * @return the integer representing the card's type
     */
    public int getCardType() {
        return type;
    }

    /**
     * Provides the descriptive label of the jail card action.
     * @return a string describing the jail card's instructions
     */
    public String getLabel() {
        return "Go to Jail immediately without collecting" +
        		" $200 when passing the GO cell";
    }
}
