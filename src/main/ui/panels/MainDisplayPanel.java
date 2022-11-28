package ui.panels;

import ui.panels.menus.MainMenuPanel;

import javax.swing.*;
import java.awt.*;

// Represents a main display that displays one of four panels: encounter, group, character, or library
public class MainDisplayPanel extends DisplayPanel {
    // labels
    private final JLabel mainDisplayTitleLabel = new JLabel("generic display");

    // layouts
    private final CardLayout cardLayout = new CardLayout();

    // panels
    private final JPanel mainDisplayManagerPanel = new JPanel(cardLayout);

    private final LibraryScrollPane libraryScrollPane = new LibraryScrollPane(mainMenuPanel);
    private final EncounterScrollPane encounterScrollPane = new EncounterScrollPane(mainMenuPanel);
    private final GroupDisplayPanel groupDisplayPanel = new GroupDisplayPanel(mainMenuPanel);
    private final CharacterDisplayTextArea characterDisplayTextArea = new CharacterDisplayTextArea(mainMenuPanel);

    // EFFECTS: constructs this panel
    public MainDisplayPanel(MainMenuPanel mainMenuPanel) {
        super(new BorderLayout(), mainMenuPanel); // sets the layout manager, mainmenu panel, size, and visibility

        mainDisplayManagerPanel.add(libraryScrollPane, "library");
        mainDisplayManagerPanel.add(encounterScrollPane, "encounter");
        mainDisplayManagerPanel.add(groupDisplayPanel, "group");
        mainDisplayManagerPanel.add(characterDisplayTextArea, "character");

        this.add(mainDisplayTitleLabel, BorderLayout.NORTH);
        this.add(mainDisplayManagerPanel, BorderLayout.CENTER);
    }

    // EFFECTS: ...
    public void setMainDisplay(String s) {
        encounterScrollPane.clearSelection();
        libraryScrollPane.clearSelection();

        if (s.equals("character")) {
            characterDisplayTextArea.initializeAll();
        }

        cardLayout.show(mainDisplayManagerPanel, s);
        mainDisplayTitleLabel.setText(s + " display.");
    }

    // EFFECTS: ...
    public LibraryScrollPane getLibraryScrollPane() {
        return libraryScrollPane;
    }

    // EFFECTS: ...
    public EncounterScrollPane getEncounterScrollPane() {
        return encounterScrollPane;
    }

    // EFFECTS: ...
    public GroupDisplayPanel getGroupDisplayPanel() {
        return groupDisplayPanel;
    }

    // EFFECTS: ...
    public CharacterDisplayTextArea getCharacterDisplayPanel() {
        return characterDisplayTextArea;
    }
}
