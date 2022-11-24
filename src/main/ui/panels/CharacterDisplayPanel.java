package ui.panels;

import ui.panels.menus.MainMenuPanel;
import ui.panels.menus.MenuManagerPanel;

import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;

// Represents...
public class CharacterDisplayPanel extends DisplayPanel {
    // EFFECTS: constructs this display panel
    public CharacterDisplayPanel(MainMenuPanel mainMenuPanel) {
        super(new CardLayout(), mainMenuPanel); // sets the layout manager, mainmenu panel, size, and visibility
    }

//    @Override
//    //
//    public void actionPerformed(ActionEvent e) {
//        //
//    }
//
//    @Override
//    //
//    public void valueChanged(ListSelectionEvent e) {
//        //
//    }
}
