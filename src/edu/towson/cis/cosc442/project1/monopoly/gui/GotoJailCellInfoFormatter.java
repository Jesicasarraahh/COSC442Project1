package edu.towson.cis.cosc442.project1.monopoly.gui;

import edu.towson.cis.cosc442.project1.monopoly.Cell;

public class GotoJailCellInfoFormatter implements CellInfoFormatter {

    public static final String GOTO_JAIL_LABEL = "<html><b>Go to Jail</b></html>";

    /**
     * Returns a formatted HTML string representing the 'Go to Jail' cell.
     * @param cell the Cell instance to be formatted
     * @return a HTML string label for the Go to Jail cell
     */
    public String format(Cell cell) {
    	return GOTO_JAIL_LABEL;
	}
}
