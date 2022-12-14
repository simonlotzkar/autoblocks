package ui.scrollpanes;

import javax.swing.*;
import java.awt.*;

// Represents a cell renderer that works as a default list cell renderer does but makes the cells transparent
public class TransparentListCellRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
                                                  boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        setOpaque(cellHasFocus || isSelected);
        return this;
    }
}