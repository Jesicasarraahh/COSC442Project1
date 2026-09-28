package edu.towson.cis.cosc442.project1.monopoly.gui;

import edu.towson.cis.cosc442.project1.monopoly.Cell;

public class GoCellInfoFormatter implements CellInfoFormatter {
    
    public static final String GO_CELL_LABEL = "<html><b>Go</b></html>";
    
    /**
     * Returns a formatted HTML label specifically for the 'Go' cell regardless of the input cell.
     * @param cell the Cell object to be formatted, ignored in this implementation
     * @return a constant HTML string that represents the 'Go' cell label
     */
    public String format(Cell cell) {
        return GO_CELL_LABEL;
    }
}
