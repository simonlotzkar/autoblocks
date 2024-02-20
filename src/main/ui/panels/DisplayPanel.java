package ui.panels;

import ui.panels.menus.MainMenuPanel;
import javax.swing.*;
import java.awt.*;

// Represents a panel that displays information and has a parent main menu panel
public class DisplayPanel extends JPanel {
    protected static final String ICON_DIRECTORY = "images/icons/";
    protected MainMenuPanel mainMenuPanel;

    // MODIFIES: this
    // EFFECTS: sets the layout manager, mainmenu panel, size, and visibility
    public DisplayPanel(LayoutManager layoutManager, MainMenuPanel mainMenuPanel) {
        super(layoutManager);
        this.mainMenuPanel = mainMenuPanel;
        this.setPreferredSize(mainMenuPanel.getPreferredSize());
        this.setVisible(true);
    }
}
