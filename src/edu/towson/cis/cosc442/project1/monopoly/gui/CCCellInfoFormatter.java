package edu.towson.cis.cosc442.project1.monopoly.gui;

import edu.towson.cis.cosc442.project1.monopoly.Cell;

public class CCCellInfoFormatter implements CellInfoFormatter {
    /**
     * Formats the name of the given cell into an HTML string with white bold font.
     * @param cell the Cell object whose name is to be formatted
     * @return an HTML-formatted string representing the cell's name in white bold font
     */
    public String format(Cell cell) {
        return "<html><font color='white'><b>" + cell.getName() + "</b></font></html>";
    }
}
