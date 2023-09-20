package ui.scrollpanes;

import model.StatBlock;
import ui.panels.StatBlockCreationDisplayPanel;
import ui.panels.menus.MainMenuPanel;
import ui.textareas.StatBlockDisplayTextArea;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;

// Represents a library side display's scroll pane and its contents
public class LibrarySideDisplayScrollPane extends ParchmentScrollPane {
    boolean statBlockCreationDisplayPanelIsShowing = false;
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardManagerPanel = new JPanel(cardLayout);
    private final StatBlockDisplayTextArea statBlockDisplayTextArea;
    private StatBlockCreationDisplayPanel statBlockCreationDisplayPanel;

    // MODIFIES: this
    // EFFECTS: constructs this scroll pane
    public LibrarySideDisplayScrollPane(MainMenuPanel mainMenuPanel) {
        super(mainMenuPanel);

        statBlockDisplayTextArea = new StatBlockDisplayTextArea();
        statBlockCreationDisplayPanel = new StatBlockCreationDisplayPanel(mainMenuPanel);

        cardManagerPanel.add(statBlockDisplayTextArea, "statBlock");
        cardManagerPanel.add(statBlockCreationDisplayPanel, "statBlockCreation");
        cardManagerPanel.setOpaque(false);

        setViewportView(cardManagerPanel);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    // MODIFIES: this
    // EFFECTS: sets the side display to show whatever the given string is then refreshes
    public void setDisplay(String s) {
        if ("statBlockCreation".equals(s)) {
            statBlockCreationDisplayPanel = new StatBlockCreationDisplayPanel(mainMenuPanel);
            cardLayout.show(cardManagerPanel, "statBlockCreation");
            statBlockCreationDisplayPanelIsShowing = true;
        } else {
            cardLayout.show(cardManagerPanel, "statBlock");
            statBlockCreationDisplayPanelIsShowing = false;
        }
        refresh();
    }

    // EFFECTS: returns true if the current side display is statblock creation
    public Boolean statblockCreationIsDisplayed() {
        return statBlockCreationDisplayPanelIsShowing;
    }

    // MODIFIES: this
    // EFFECTS: refreshes subcomponents, revalidates, and repaints
    public void refresh() {
        statBlockDisplayTextArea.populateTextArea();
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: if there is no selected statblock or the creation panel is showing, reverts to the full library display,
    //          otherwise reverts to the title screen
    public void processBackButton() {
        if (statBlockDisplayTextArea.getSelectedStatBlock() != null || statBlockCreationDisplayPanelIsShowing) {
            mainMenuPanel.setDisplays("library");
        } else {
            mainMenuPanel.getMenuManagerPanel().setMenu("title");
        }
    }

    // MODIFIES: mainMenuPanel
    // EFFECTS: processes button presses from user
    public void processOpenButton(StatBlock statBlock) {
        statBlockDisplayTextArea.setSelectedStatBlock(statBlock);
        mainMenuPanel.setDisplays("statBlock");
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    // EFFECTS: returns the statblock creation panel
    public StatBlockCreationDisplayPanel getStatBlockCreationDisplayPanel() {
        return statBlockCreationDisplayPanel;
    }

    // EFFECTS: returns the statblock display panel
    public StatBlockDisplayTextArea getStatBlockDisplayTextArea() {
        return statBlockDisplayTextArea;
    }
}
