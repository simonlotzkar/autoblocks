package ui.panels;

import ui.panels.menus.MainMenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// Represents a main display that displays one of four panels: encounter, group, character, or library
public class MainDisplayPanel extends DisplayPanel {
    // labels
    private final JLabel mainDisplayTitleLabel = new JLabel("generic display");

    // layouts
    private final CardLayout cardLayout = new CardLayout();

    // panels
    private final JPanel mainDisplayManagerPanel = new JPanel(cardLayout);

    private final LibraryDisplayPanel libraryDisplayPanel = new LibraryDisplayPanel(mainMenuPanel);
    private final EncounterScrollPane encounterScrollPane = new EncounterScrollPane(mainMenuPanel);
    private final GroupDisplayPanel groupDisplayPanel = new GroupDisplayPanel(mainMenuPanel);
    private final CharacterDisplayPanel characterDisplayPanel = new CharacterDisplayPanel(mainMenuPanel);

    // EFFECTS: constructs this panel
    public MainDisplayPanel(MainMenuPanel mainMenuPanel) {
        super(new BorderLayout(), mainMenuPanel); // sets the layout manager, mainmenu panel, size, and visibility

        mainDisplayManagerPanel.add(libraryDisplayPanel, "library");
        mainDisplayManagerPanel.add(encounterScrollPane, "encounter");
        mainDisplayManagerPanel.add(groupDisplayPanel, "group");
        mainDisplayManagerPanel.add(characterDisplayPanel, "character");

        this.add(mainDisplayTitleLabel, BorderLayout.NORTH);
        this.add(mainDisplayManagerPanel, BorderLayout.CENTER);
    }

    // EFFECTS: ...
    public void setMainDisplay(String s) {
        cardLayout.show(mainDisplayManagerPanel, s);
        mainDisplayTitleLabel.setText(s + " display.");
    }

    // EFFECTS: depending on what display is showing, sends the given action event to the corresponding display panel
    public void passAction(ActionEvent e) {
        if (libraryDisplayPanel.isShowing()) {
            //libraryDisplayPanel.actionPerformed(e);
        } else if (encounterScrollPane.isShowing()) {
            encounterScrollPane.actionPerformed(e);
        } else if (groupDisplayPanel.isShowing()) {
            groupDisplayPanel.actionPerformed(e);
        } else if (characterDisplayPanel.isShowing()) {
            //characterDisplayPanel.actionPerformed(e);
        }
    }
}
