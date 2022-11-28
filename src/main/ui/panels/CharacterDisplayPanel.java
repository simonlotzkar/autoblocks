package ui.panels;

import ui.panels.menus.MainMenuPanel;
import ui.panels.menus.MenuManagerPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;

// Represents...
public class CharacterDisplayPanel extends StatBlockDisplayTextArea {
    // EFFECTS: constructs this display panel
    public CharacterDisplayPanel(MainMenuPanel mainMenuPanel) {
        super(mainMenuPanel); // sets the layout manager, mainmenu panel, size, and visibility
    }

    // EFFECTS: ...
    public void passAction(ActionEvent e) {
        //
    }
}
