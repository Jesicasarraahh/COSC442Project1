package edu.towson.cis.cosc442.project1.monopoly;

public class MockRespondDialog implements RespondDialog {
    /**
     * Constructs a MockRespondDialog using the specified trade deal for simulation purposes.
     * @param deal the TradeDeal object to be used in this dialog
     */
    public MockRespondDialog(TradeDeal deal) {
    }

    /**
     * Returns a simulated acceptance response for the trade deal.
     * @return the boolean response indicating acceptance (always true)
     */
    public boolean getResponse() {
        return true;
    }
}
