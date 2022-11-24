package ui.panels.listpanels;

import ui.panels.menus.MenuManagerPanel;
import ui.panels.menus.MenuPanel;

import java.awt.*;

// Represents...
public class GroupListPanel extends MenuPanel {
    private final String groupName;

    // constructs this panel
    public GroupListPanel(MenuManagerPanel menuManagerPanel) {
        super(new GridLayout(3, 1), menuManagerPanel);
        this.groupName = menuManagerPanel.getSelectedGroup();
    }
}
