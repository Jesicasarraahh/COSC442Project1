package edu.towson.cis.cosc442.project1.monopoly.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.OverlayLayout;
import javax.swing.border.BevelBorder;

import edu.towson.cis.cosc442.project1.monopoly.*;

public class PlayerPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnBuyHouse;
    private JButton btnDrawCard;
    private JButton btnEndTurn;
    private JButton btnGetOutOfJail;
    private JButton btnPurchaseProperty;
    private JButton btnRollDice;
    private JButton btnTrade;
    
    private JLabel lblMoney;
    private JLabel lblName;
    
    private Player player;
    
    private JTextArea txtProperty;

    /**
     * Constructs a PlayerPanel GUI component for the given player, initializing buttons, labels, properties display, and their associated action listeners.
     * @param player the Player object whose information and actions are represented in this panel
     */
    public PlayerPanel(Player player) {
        JPanel pnlAction = new JPanel();
        JPanel pnlInfo = new JPanel();
        btnRollDice = new JButton("Roll Dice");
        btnPurchaseProperty = new JButton("Purchase Property");
        btnEndTurn = new JButton("End Turn");
        btnBuyHouse = new JButton("Buy House");
        btnGetOutOfJail = new JButton("Get Out of Jail");
        btnDrawCard = new JButton("Draw Card");
        btnTrade = new JButton("Trade");
        this.player = player;
        lblName = new JLabel();
        lblMoney = new JLabel();
        txtProperty = new JTextArea(30, 70);

        txtProperty.setEnabled(false);

        JPanel pnlName = new JPanel();
        JPanel pnlProperties = new JPanel();

        pnlInfo.setLayout(new BorderLayout());
        pnlInfo.add(pnlName, BorderLayout.NORTH);
        pnlInfo.add(pnlProperties, BorderLayout.CENTER);

        pnlProperties.setLayout(new OverlayLayout(pnlProperties));

        pnlName.add(lblName);
        pnlName.add(lblMoney);
        pnlProperties.add(txtProperty);

        pnlAction.setLayout(new GridLayout(3, 3));
        pnlAction.add(btnBuyHouse);
        pnlAction.add(btnRollDice);
        pnlAction.add(btnPurchaseProperty);
        pnlAction.add(btnGetOutOfJail);
        pnlAction.add(btnEndTurn);
        pnlAction.add(btnDrawCard);
        pnlAction.add(btnTrade);

        pnlAction.doLayout();
        pnlInfo.doLayout();
        pnlName.doLayout();
        pnlProperties.doLayout();
        this.doLayout();

        setLayout(new BorderLayout());
        add(pnlInfo, BorderLayout.CENTER);
        add(pnlAction, BorderLayout.SOUTH);

        btnRollDice.setEnabled(false);
        btnPurchaseProperty.setEnabled(false);
        btnEndTurn.setEnabled(false);
        btnBuyHouse.setEnabled(false);
        btnGetOutOfJail.setEnabled(false);
        btnDrawCard.setEnabled(false);
        btnTrade.setEnabled(false);

        setBorder(new BevelBorder(BevelBorder.RAISED));

        btnRollDice.addActionListener(new ActionListener() {
            /**
             * Handles the trade button click event by notifying the game master to process a trade action for the player.
             * @param e the action event triggered by clicking the trade button
             */
            public void actionPerformed(ActionEvent e) {
                GameMaster.instance().btnRollDiceClicked();
            }
        });

        btnEndTurn.addActionListener(new ActionListener() {
            /**
             * Handles the trade button click event by notifying the game master to process a trade action for the player.
             * @param e the action event triggered by clicking the trade button
             */
            public void actionPerformed(ActionEvent e) {
                GameMaster.instance().btnEndTurnClicked();
            }
        });

        btnPurchaseProperty.addActionListener(new ActionListener() {
            /**
             * Handles the trade button click event by notifying the game master to process a trade action for the player.
             * @param e the action event triggered by clicking the trade button
             */
            public void actionPerformed(ActionEvent e) {
                GameMaster.instance().btnPurchasePropertyClicked();
            }
        });

        btnBuyHouse.addActionListener(new ActionListener() {
            /**
             * Handles the trade button click event by notifying the game master to process a trade action for the player.
             * @param e the action event triggered by clicking the trade button
             */
            public void actionPerformed(ActionEvent e) {
                GameMaster.instance().btnBuyHouseClicked();
            }
        });

        btnGetOutOfJail.addActionListener(new ActionListener() {
            /**
             * Handles the trade button click event by notifying the game master to process a trade action for the player.
             * @param e the action event triggered by clicking the trade button
             */
            public void actionPerformed(ActionEvent e) {
                GameMaster.instance().btnGetOutOfJailClicked();
            }
        });

        btnDrawCard.addActionListener(new ActionListener() {
            /**
             * Handles the trade button click event by notifying the game master to process a trade action for the player.
             * @param e the action event triggered by clicking the trade button
             */
            public void actionPerformed(ActionEvent e) {
                Card card = GameMaster.instance().btnDrawCardClicked();
                JOptionPane
                        .showMessageDialog(PlayerPanel.this, card.getLabel());
                displayInfo();
            }
        });

        btnTrade.addActionListener(new ActionListener() {
            /**
             * Handles the trade button click event by notifying the game master to process a trade action for the player.
             * @param e the action event triggered by clicking the trade button
             */
            public void actionPerformed(ActionEvent e) {
                GameMaster.instance().btnTradeClicked();
            }
        });
    }

    /**
     * Updates the player panel display to show the current player's name, money, and properties.
     */
    public void displayInfo() {
        lblName.setText(player.getName());
        lblMoney.setText("$ " + player.getMoney());
        StringBuffer buf = new StringBuffer();
        Cell[] cells = player.getAllProperties();
        for (int i = 0; i < cells.length; i++) {
            buf.append(cells[i] + "\n");
        }
        txtProperty.setText(buf.toString());
    }
    
    /**
     * Indicates whether the Buy House button is currently enabled.
     * @return true if the Buy House button is enabled; false otherwise
     */
    public boolean isBuyHouseButtonEnabled() {
        return btnBuyHouse.isEnabled();
    }

    /**
     * Indicates whether the Draw Card button is currently enabled.
     * @return true if the Draw Card button is enabled; false otherwise
     */
    public boolean isDrawCardButtonEnabled() {
        return btnDrawCard.isEnabled();
    }

    /**
     * Indicates whether the End Turn button is currently enabled.
     * @return true if the End Turn button is enabled; false otherwise
     */
    public boolean isEndTurnButtonEnabled() {
        return btnEndTurn.isEnabled();
    }
    
    /**
     * Indicates whether the Get Out Of Jail button is currently enabled.
     * @return true if the Get Out Of Jail button is enabled; false otherwise
     */
    public boolean isGetOutOfJailButtonEnabled() {
        return btnGetOutOfJail.isEnabled();
    }
    
    /**
     * Indicates whether the Purchase Property button is currently enabled.
     * @return true if the Purchase Property button is enabled; false otherwise
     */
    public boolean isPurchasePropertyButtonEnabled() {
        return btnPurchaseProperty.isEnabled();
    }
    
    /**
     * Indicates whether the Roll Dice button is currently enabled.
     * @return true if the Roll Dice button is enabled; false otherwise
     */
    public boolean isRollDiceButtonEnabled() {
        return btnRollDice.isEnabled();
    }

    /**
     * Indicates whether the Trade button is currently enabled.
     * @return true if the Trade button is enabled; false otherwise
     */
    public boolean isTradeButtonEnabled() {
        return btnTrade.isEnabled();
    }

    /**
     * Sets whether the Buy House button is enabled or disabled.
     * @param b true to enable the button; false to disable
     */
    public void setBuyHouseEnabled(boolean b) {
        btnBuyHouse.setEnabled(b);
    }

    /**
     * Sets whether the Draw Card button is enabled or disabled.
     * @param b true to enable the button; false to disable
     */
    public void setDrawCardEnabled(boolean b) {
        btnDrawCard.setEnabled(b);
    }

    /**
     * Sets whether the End Turn button is enabled or disabled.
     * @param enabled true to enable the button; false to disable
     */
    public void setEndTurnEnabled(boolean enabled) {
        btnEndTurn.setEnabled(enabled);
    }

    /**
     * Sets whether the Get Out Of Jail button is enabled or disabled.
     * @param b true to enable the button; false to disable
     */
    public void setGetOutOfJailEnabled(boolean b) {
        btnGetOutOfJail.setEnabled(b);
    }

    /**
     * Sets whether the Purchase Property button is enabled or disabled.
     * @param enabled true to enable the button; false to disable
     */
    public void setPurchasePropertyEnabled(boolean enabled) {
        btnPurchaseProperty.setEnabled(enabled);
    }

    /**
     * Sets whether the Roll Dice button is enabled or disabled.
     * @param enabled true to enable the button; false to disable
     */
    public void setRollDiceEnabled(boolean enabled) {
        btnRollDice.setEnabled(enabled);
    }

    /**
     * Sets whether the Trade button is enabled or disabled.
     * @param b true to enable the button; false to disable
     */
    public void setTradeEnabled(boolean b) {
        btnTrade.setEnabled(b);
    }
}