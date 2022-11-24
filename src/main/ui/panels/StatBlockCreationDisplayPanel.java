package ui.panels;

import ui.panels.menus.MainMenuPanel;
import ui.panels.menus.MenuManagerPanel;
import ui.panels.menus.MenuPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents...
public class StatBlockCreationDisplayPanel extends DisplayPanel implements ActionListener {
    // EFFECTS: constructs this display panel
    public StatBlockCreationDisplayPanel(MainMenuPanel mainMenuPanel) {
        super(new CardLayout(), mainMenuPanel); // sets the layout manager, mainmenu panel, size, and visibility
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
