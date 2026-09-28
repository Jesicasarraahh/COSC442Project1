package edu.towson.cis.cosc442.project1.monopoly.gui;

import edu.towson.cis.cosc442.project1.monopoly.Cell;

public interface CellInfoFormatter {
    /**
     * Formats the given Cell object into a corresponding String representation.
     * @param cell the Cell object to be formatted
     * @return a String representing the formatted Cell information
     */
    public String format(Cell cell);
}
