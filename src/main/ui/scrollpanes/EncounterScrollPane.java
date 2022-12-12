package ui.scrollpanes;

import model.Encounter;
import model.NPC;
import ui.panels.DisplayPanel;
import ui.panels.menus.MainMenuPanel;

import javax.swing.*;

// Represents...
public class EncounterScrollPane extends DisplayScrollPane {
    // lists
    private Encounter encounter
            = mainMenuPanel.getMenuManagerPanel().getEncounter();
    private final JList<NPC> encounterList = new JList<>(encounter);

    private final Encounter groupEncounter = new Encounter();
    private final JList<NPC> groupList = new JList<>(groupEncounter);

    public EncounterScrollPane(MainMenuPanel mainMenuPanel) {
        super(null);
    }
}
