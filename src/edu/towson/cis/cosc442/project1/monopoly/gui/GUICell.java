package edu.towson.cis.cosc442.project1.monopoly.gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import edu.towson.cis.cosc442.project1.monopoly.*;

public class GUICell extends JPanel {

	
	private static final long serialVersionUID = 1L;
	private Cell cell;
	private JLabel lblInfo;
	private JLabel[] lblPlayers = new JLabel[GameMaster.MAX_PLAYER];
	
    /**
     * Constructs a GUICell component to visually represent the specified Cell object.
     * @param cell The Cell object to be represented by this GUI component.
     */
    public GUICell(Cell cell) {
        this.cell = cell;
        setLayout(new OverlayLayout(this));
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        JPanel pnlPlayer = new JPanel();
        pnlPlayer.setLayout(new GridLayout(2, 4));
        pnlPlayer.setOpaque(false);
        createPlayerLabels(pnlPlayer);
        add(pnlPlayer);
        setPreferredSize(new Dimension(100,100));
        addCellInfo();
        this.doLayout();
	}
	
	/**
	 * Adds the GUI components that display the cell's information to this panel.
	 */
	private void addCellInfo() {
        lblInfo = new JLabel();
		displayInfo();
        JPanel pnlInfo = new JPanel();
        pnlInfo.setLayout(new GridLayout(1, 1));
        pnlInfo.add(lblInfo);
        add(pnlInfo);
    }
	
	/**
	 * Adds a player's initial to the player label corresponding to the given index and makes it visible.
	 * @param index The index of the player to add visually on the cell.
	 */
	public void addPlayer(int index) {
		Player player = GameMaster.instance().getPlayer(index);
		lblPlayers[index].setText(player.getName().substring(0, 1));
		lblPlayers[index].setOpaque(true);
	}

    /**
     * Initializes player labels and adds them to the given panel for displaying player presence.
     * @param pnlPlayer The JPanel to which player labels are added.
     */
    private void createPlayerLabels(JPanel pnlPlayer) {
		for (int i = 0; i < GameMaster.MAX_PLAYER; i++) {
			lblPlayers[i] = new JLabel();
			lblPlayers[i].setBackground(Color.GREEN);
			pnlPlayer.add(lblPlayers[i]);
		}
	}

	/**
	 * Updates the cell information label with the current details of the underlying cell and repaints the component.
	 */
	public void displayInfo() {
		lblInfo.setText(InfoFormatter.cellInfo(cell));
        this.invalidate();
		this.repaint();
	}

	/**
	 * Returns the Cell object associated with this GUICell component.
	 * @return The Cell instance represented by this GUI component.
	 */
	public Cell getCell() {
		return cell;
	}
	
	/**
	 * Removes the player's initial from the label at the specified index and makes it invisible.
	 * @param index The index of the player to remove from this cell's display.
	 */
	public void removePlayer(int index) {
		lblPlayers[index].setText("");
		lblPlayers[index].setOpaque(false);
        this.repaint();
	}
}
