package limite;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;

class RowRenderer extends JLabel implements ListCellRenderer{

	public RowRenderer(JTable table) {
		JTableHeader header = table.getTableHeader();
		setOpaque(true);
		setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		setHorizontalAlignment(CENTER);
		setForeground(header.getForeground());
		setBackground(header.getBackground());
		setFont(header.getFont());
	}
	@Override
	public Component getListCellRendererComponent(JList list, Object obj,
			int index, boolean selected, boolean focused) {
		setText((obj==null) ? "" : obj.toString());
		return this;
	}
	
}