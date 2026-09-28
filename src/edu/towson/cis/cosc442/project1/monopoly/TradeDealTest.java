package edu.towson.cis.cosc442.project1.monopoly;

import junit.framework.TestCase;

public class TradeDealTest extends TestCase {
    
    /**
     * Initializes the game state before each test by resetting the game and setting up two players with predefined names.
     */
    public void setUp() {
        GameMaster gameMaster = GameMaster.instance();
        gameMaster.reset();
        gameMaster.setNumberOfPlayers(2);
        gameMaster.getPlayer(0).setName("Buyer");
        gameMaster.getPlayer(1).setName("Seller");
    }

    /**
     * Tests the makeMessage method of TradeDeal to ensure it generates the correct trade offer message string.
     */
    public void testMakeMessage() {
        TradeDeal deal = new TradeDeal();
        deal.setAmount(200);
        deal.setPropertyName("Blue 1");
        deal.setSellerIndex(1);
        GameMaster.instance().getPlayer(0);
        String message = "Buyer wishes to purchase Blue 1 from Seller" +
        		" for 200.  Seller, do you wish to trade your property?";
        assertEquals(message, deal.makeMessage());
    }

}
