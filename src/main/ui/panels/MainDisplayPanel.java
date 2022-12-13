package ui.panels;

import ui.panels.menus.MainMenuPanel;
import ui.scrollpanes.EncounterScrollPane;
import ui.scrollpanes.LibraryScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// Represents a main panel that displays either the encounter or library scroll panes, and their titles
public class MainDisplayPanel extends DisplayPanel {
    // labels
    private final JLabel mainDisplayTitleLabel = new JLabel();

    // layouts
    private final CardLayout mainDisplaysCardLayout = new CardLayout();

    // panels
    private final JPanel mainDisplaysManagerPanel = new JPanel(mainDisplaysCardLayout);

    // scroll panes
    private final EncounterScrollPane encounterScrollPane = new EncounterScrollPane(mainMenuPanel);
    private final LibraryScrollPane libraryScrollPane = new LibraryScrollPane(mainMenuPanel);

    // MODIFIES: this
    // EFFECTS: constructs this panel
    public MainDisplayPanel(MainMenuPanel mainMenuPanel) {
        super(new BorderLayout(), mainMenuPanel); // sets the layout manager, mainmenu panel, size, and visibility

        mainDisplaysManagerPanel.add(libraryScrollPane, "library");
        mainDisplaysManagerPanel.add(encounterScrollPane, "encounter");

        this.add(mainDisplayTitleLabel, BorderLayout.NORTH);
        this.add(mainDisplaysManagerPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: sets the main display to the given string and refreshes the scroll panes
    public void setDisplay(String s) {
        if (encounterScrollPane.isShowing()) {
            mainDisplaysCardLayout.show(mainDisplaysManagerPanel, s);
            encounterScrollPane.setViewportDisplay(s);
        } else {
            mainDisplaysCardLayout.show(mainDisplaysManagerPanel, s);
            libraryScrollPane.refresh();
        }
        mainDisplayTitleLabel.setText(s.substring(0, 1).toUpperCase() + s.substring(1) + " display.");
    }

    // MODIFIES: this
    // EFFECTS: processes the given action event / button press depending on what scroll pane is showing
    public void passAction(ActionEvent e) {
        if (encounterScrollPane.isShowing()) {
            encounterScrollPane.processAction(e);
        } else {
            libraryScrollPane.processAction(e);
        }
    }

    // -----------------------------------------------------------------------
    // getters
    // EFFECTS: gets the library scroll pane
    public LibraryScrollPane getLibraryScrollPane() {
        return libraryScrollPane;
    }

    // EFFECTS: gets the encounter scroll pane
    public EncounterScrollPane getEncounterScrollPane() {
        return encounterScrollPane;
    }
}
