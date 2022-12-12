package ui.scrollpanes;

import ui.panels.menus.MainMenuPanel;

import javax.swing.*;
import java.awt.*;

// Represents a scroll pane that displays information and has a parent main menu panel
public class DisplayScrollPane extends JPanel {
    protected static final String ICON_DIRECTORY = "./data/images/icons/";
    protected MainMenuPanel mainMenuPanel;

    // MODIFIES: this
    // EFFECTS: sets the layout manager, mainmenu panel, size, and visibility
    public DisplayScrollPane(LayoutManager layoutManager, MainMenuPanel mainMenuPanel) {
        super(layoutManager);
        this.mainMenuPanel = mainMenuPanel;
        this.setPreferredSize(mainMenuPanel.getPreferredSize());
        this.setVisible(true);
    }
}
