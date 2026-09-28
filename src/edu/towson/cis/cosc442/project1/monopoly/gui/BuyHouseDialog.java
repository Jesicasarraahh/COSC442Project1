
package edu.towson.cis.cosc442.project1.monopoly.gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import edu.towson.cis.cosc442.project1.monopoly.Player;


public class BuyHouseDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<?> cboMonopoly;
	private JComboBox<?> cboNumber;

	private Player player;

	/**
	 * Constructs a dialog for a player to buy houses, initializing UI components accordingly.
	 * @param player the player who wants to buy houses
	 */
	public BuyHouseDialog(Player player) {
		this.player = player;
		Container c = this.getContentPane();
		c.setLayout(new GridLayout(3, 2));
		c.add(new JLabel("Select monopoly"));
		c.add(buildMonopolyComboBox());
		c.add(new JLabel("Number of houses"));
		c.add(buildNumberComboBox());
		c.add(buildOKButton());
		c.add(buildCancelButton());
		c.doLayout();
		this.pack();
	}

	/**
	 * Builds and returns the Cancel button that closes the dialog without action.
	 * @return the Cancel JButton component
	 */
	private JButton buildCancelButton() {
		JButton btn = new JButton("Cancel");
		btn.addActionListener(new ActionListener(){
			/**
			 * Handles action events triggered by the OK button to confirm and process house purchase.
			 * @param e the event triggered by the OK button
			 */
			public void actionPerformed(ActionEvent e) {
				cancelClicked();
			}
		});
		return btn;
	}

	/**
	 * Creates and returns a combo box populated with the player's monopolies for selection.
	 * @return the JComboBox component listing the player's monopolies
	 */
	private JComboBox<?> buildMonopolyComboBox() {
		cboMonopoly = new JComboBox<Object>(player.getMonopolies());
		return cboMonopoly;
	}
	
	/**
	 * Creates and returns a combo box allowing selection of the number of houses to purchase.
	 * @return the JComboBox component with numbers 1 to 5 as options
	 */
	private JComboBox<?> buildNumberComboBox() {
		cboNumber = new JComboBox<Object>(new Integer[]{
				new Integer(1),
				new Integer(2),
				new Integer(3),
				new Integer(4),
				new Integer(5)});
		return cboNumber;
	}

	/**
	 * Builds and returns the OK button that initiates house purchase confirmation.
	 * @return the OK JButton component
	 */
	private JButton buildOKButton() {
		JButton btn = new JButton("OK");
		btn.addActionListener(new ActionListener(){
			/**
			 * Handles action events triggered by the OK button to confirm and process house purchase.
			 * @param e the event triggered by the OK button
			 */
			public void actionPerformed(ActionEvent e) {
				okClicked();
			}
		});
		return btn;
	}
	
	/**
	 * Disposes of the dialog when the cancel action is invoked, closing the purchase window.
	 */
	private void cancelClicked() {
		this.dispose();
	}
	
	/**
	 * Processes the selected monopoly and house number to purchase houses for the player, then closes the dialog.
	 */
	private void okClicked() {
		String monopoly = (String)cboMonopoly.getSelectedItem();
		int number = cboNumber.getSelectedIndex() + 1;
		player.purchaseHouse(monopoly, number);
		this.dispose();
	}
}
