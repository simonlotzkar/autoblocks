package ui.panels;

import ui.panels.MenuCardPanel;

import javax.swing.*;
import java.awt.*;

// Represents a panel that has a parent menucard panel and a parent menu string
public class MenuPanel extends JPanel {
    protected static final String ICON_DIRECTORY = "./data/images/icons/";
    protected final MenuCardPanel menuCardPanel;

    // EFFECTS: sets the manager, menucard, size, and visibility
    public MenuPanel(LayoutManager layoutManager, MenuCardPanel menuCardPanel) {
        super(layoutManager);
        this.menuCardPanel = menuCardPanel;
        this.setSize(menuCardPanel.getSize());
        this.setVisible(true);
    }
}
