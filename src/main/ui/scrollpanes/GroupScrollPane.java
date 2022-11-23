package ui.scrollpanes;

import ui.panels.MenuCardPanel;
import ui.panels.MenuPanel;

import java.awt.*;

// Represents...
public class GroupScrollPane extends MenuPanel {
    private final String groupName;

    // constructs this panel
    public GroupScrollPane(MenuCardPanel menuCardPanel) {
        super(new GridLayout(3, 1), menuCardPanel);
        this.groupName = menuCardPanel.getSelectedGroup();
    }
}
