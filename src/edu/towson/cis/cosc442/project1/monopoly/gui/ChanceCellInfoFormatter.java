package edu.towson.cis.cosc442.project1.monopoly.gui;

import edu.towson.cis.cosc442.project1.monopoly.Cell;

public class ChanceCellInfoFormatter implements CellInfoFormatter {
    
    public static final String CHANCE_CELL_LABEL = "<html><font color='teal'><b>Chance</b></font></html>";
    
    /**
     * Returns a formatted HTML string label representing a Chance cell for the given cell.
     * @param cell the Cell object to format, representing a Chance cell
     * @return an HTML formatted string label for a Chance cell
     */
    public String format(Cell cell) {
        return CHANCE_CELL_LABEL;
    }
}
