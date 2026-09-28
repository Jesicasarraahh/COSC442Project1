package edu.towson.cis.cosc442.project1.monopoly.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import edu.towson.cis.cosc442.project1.monopoly.RespondDialog;
import edu.towson.cis.cosc442.project1.monopoly.TradeDeal;


public class GUIRespondDialog extends JDialog implements RespondDialog {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean response;
    JTextArea txtMessage = new JTextArea();
    
    /**
     * Constructs a modal dialog with Yes and No buttons for user response and a text area to display messages.
     */
    public GUIRespondDialog() {
        JButton btnYes = new JButton("Yes");
        JButton btnNo = new JButton("No");
        txtMessage.setPreferredSize(new Dimension(300, 200));
        txtMessage.setEditable(false);
        txtMessage.setLineWrap(true);
        
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(txtMessage, BorderLayout.CENTER);
        JPanel pnlButtons = new JPanel();
        pnlButtons.add(btnYes);
        pnlButtons.add(btnNo);
        contentPane.add(pnlButtons, BorderLayout.SOUTH);
        
        btnYes.addActionListener(new ActionListener(){
            @SuppressWarnings("deprecation")
			/**
			 * Handles the action event triggered by clicking the No button, records a negative response, and hides the dialog.
			 * @param e the action event triggered by the No button click
			 */
			public void actionPerformed(ActionEvent e) {
                response = true;
                hide();
            }
        });

        btnNo.addActionListener(new ActionListener(){
            @SuppressWarnings("deprecation")
			/**
			 * Handles the action event triggered by clicking the No button, records a negative response, and hides the dialog.
			 * @param e the action event triggered by the No button click
			 */
			public void actionPerformed(ActionEvent e) {
                response = false;
                hide();
            }
        });
    
        setModal(true);
        pack();
    }

    /**
     * Returns the user's response as a boolean indicating Yes (true) or No (false).
     * @return the user's response
     */
    public boolean getResponse() {
        return response;
    }
    
    /**
     * Sets the trade deal message to be displayed in the dialog's text area.
     * @param deal the trade deal whose message is to be displayed
     */
    public void setDeal(TradeDeal deal) {
        txtMessage.setText(deal.makeMessage());
    }

}
