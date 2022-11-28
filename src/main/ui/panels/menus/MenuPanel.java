package ui.panels.menus;

import javax.swing.*;
import java.awt.*;

// Represents a panel that has a layout manager and a parent menucard panel
public class MenuPanel extends JPanel {
    protected static final String ICON_DIRECTORY = "./data/images/icons/";
    protected final MenuManagerPanel menuManagerPanel;

    // EFFECTS: sets the manager, size, and visibility
    public MenuPanel(LayoutManager layoutManager, MenuManagerPanel menuManagerPanel) {
        super(layoutManager);
        this.menuManagerPanel = menuManagerPanel;
        this.setPreferredSize(menuManagerPanel.getPreferredSize());
        this.setVisible(true);
    }

    // EFFECTS: ...
    public MenuManagerPanel getMenuManagerPanel() {
        return menuManagerPanel;
    }
}
