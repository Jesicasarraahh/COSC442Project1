package edu.towson.cis.cosc442.project1.monopoly.gui;

import edu.towson.cis.cosc442.project1.monopoly.Cell;
import edu.towson.cis.cosc442.project1.monopoly.Player;
import edu.towson.cis.cosc442.project1.monopoly.UtilityCell;

public class UtilCellInfoFormatter implements CellInfoFormatter {

	/**
	 * Formats the given Cell object into an HTML string representation specific to a UtilityCell including its name, price, and owner.
	 * @param cell the Cell instance to format, expected to be a UtilityCell
	 * @return an HTML-formatted string with the cell's name, price, and owner information
	 */
	public String format(Cell cell) {
        UtilityCell c = (UtilityCell)cell;
        StringBuffer buf = new StringBuffer();
        Player owner = cell.getTheOwner();
        String ownerName = "";
        if(owner != null) {
        	ownerName = owner.getName();
        }
        buf.append("<html><b><font color='olive'>")
                .append(cell.getName())
                .append("</font></b><br>")
                .append("$").append(c.getPrice())
				.append("<br>Owner: ").append(ownerName)
                .append("</html>");
        return buf.toString();
	}
}
