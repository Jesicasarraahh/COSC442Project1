package edu.towson.cis.cosc442.project1.monopoly.gui;

import edu.towson.cis.cosc442.project1.monopoly.Cell;

public class JailCellInfoFormatter implements CellInfoFormatter {

    public static final String JAIL_CELL_LABEL = "<html><b>Jail</b></html>";

    /**
     * Returns a formatted HTML string representing the Jail cell label.
     * @param cell the Cell object to format
     * @return the HTML string label for the Jail cell
     */
    public String format(Cell cell) {
		return JAIL_CELL_LABEL;
	}

}
