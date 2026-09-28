package edu.towson.cis.cosc442.project1.monopoly;

import junit.framework.TestCase;

public class CardsTest extends TestCase {
    Card ccCard, chanceCard;
    
    GameMaster gameMaster;

    /**
     * Executes setUp.
     */
    protected void setUp() {
        gameMaster = GameMaster.instance();
        gameMaster.setGameBoard(new GameBoardCCGainMoney());
        gameMaster.setNumberOfPlayers(1);
        gameMaster.reset();
        gameMaster.setGUI(new MockGUI());
        ccCard = new MoneyCard("Get 50 dollars", 50, Card.TYPE_CC);
        chanceCard = new MoneyCard("Lose 50 dollars", -50, Card.TYPE_CHANCE);
        gameMaster.getGameBoard().addCard(ccCard);
    }
    
    /**
     * Executes testCardType.
     */
    public void testCardType() {
        gameMaster.drawCCCard();
        assertEquals(Card.TYPE_CC, ccCard.getCardType());
        gameMaster.drawChanceCard();
        //according to Task 8 changing TYPE_CHANCE to TYPE_CC in MoneyCard constructor, so this test will fail 
        //changing it back to TYPE_CHANCE to make the test pass
        assertEquals(Card.TYPE_CHANCE, chanceCard.getCardType());
    }
}
