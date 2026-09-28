package edu.towson.cis.cosc442.project1.monopoly.gui;

import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class TestDiceRollDialog extends JDialog {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnOK, btnCancel;
    private JTextField txtDiceRoll;
    private int[] diceRoll;
    
    /**
     * Constructs a modal dialog attached to the specified parent frame to input a dice roll amount.
     * @param parent the parent frame to which this dialog is modal
     */
    public TestDiceRollDialog(Frame parent) {
        super(parent);
        
        setTitle("Dice Roll Dialog");
        txtDiceRoll = new JTextField(2);
        btnOK = new JButton("OK");
        btnCancel = new JButton("Cancel");
        
        setModal(true);
             
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(2, 2));
        contentPane.add(new JLabel("Amount"));
        contentPane.add(txtDiceRoll);
        contentPane.add(btnOK);
        contentPane.add(btnCancel);
        
        btnCancel.addActionListener(new ActionListener(){
            @SuppressWarnings("deprecation")
			/**
			 * Processes the OK button action by parsing the input amount, updating the dice roll accordingly, and hiding the dialog.
			 * @param e the event triggering this action
			 */
			public void actionPerformed(ActionEvent e) {
                TestDiceRollDialog.this.hide();
                diceRoll = new int[2];
                diceRoll[0] = 0;
                diceRoll[1] = 0;
            }
        });
        
        btnOK.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
			/**
			 * Processes the OK button action by parsing the input amount, updating the dice roll accordingly, and hiding the dialog.
			 * @param e the event triggering this action
			 */
			public void actionPerformed(ActionEvent e) {
                int amount = 0;
                try{
                    amount = Integer.parseInt(txtDiceRoll.getText());
                } catch(NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(TestDiceRollDialog.this,
                            "Amount should be an integer", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(amount > 0) {
	                diceRoll = new int[2];
	                if((amount % 2) == 0) {
	                	diceRoll[0] = amount / 2;
	                	diceRoll[1] = amount / 2;
	                }
	                else {
	                	diceRoll[0] = amount / 2;
	                	diceRoll[1] = (amount / 2) + 1;
	                }
                }
                hide();
            }
        });
        
        this.pack();
    }

    /**
     * Returns the current dice roll values as an array of two integers.
     * @return an int array containing the values of the two dice rolls
     */
    public int[] getDiceRoll() {
        return diceRoll;
    }
}
