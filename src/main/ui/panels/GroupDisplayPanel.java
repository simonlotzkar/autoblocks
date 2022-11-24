package ui.panels;

import ui.panels.menus.MainMenuPanel;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents...
public class GroupDisplayPanel extends DisplayPanel implements ActionListener, ListSelectionListener {
    // EFFECTS: constructs this display panel
    public GroupDisplayPanel(MainMenuPanel mainMenuPanel) {
        super(new CardLayout(), mainMenuPanel); // sets the layout manager, mainmenu panel, size, and visibility
    }

    @Override
    //
    public void actionPerformed(ActionEvent e) {
        //
    }

    @Override
    //
    public void valueChanged(ListSelectionEvent e) {
        //
    }
}
