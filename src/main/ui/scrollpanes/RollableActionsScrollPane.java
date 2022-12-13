package ui.scrollpanes;

import model.Encounter;
import model.NPC;
import model.statblockfields.RollableAction;
import ui.panels.menus.MainMenuPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

// Represents a scroll pane of rollable actions for the NPCs that the encounter main display is showing
public class RollableActionsScrollPane extends JScrollPane implements ListSelectionListener {
    private final MainMenuPanel mainMenuPanel;
    private final Encounter encounter;

    private final JList<RollableAction> rollableActionsList;
    private final DefaultListModel<RollableAction> rollableActionsListModel;

    // MODIFIES: this
    // EFFECTS: constructs this scroll pane
    public RollableActionsScrollPane(MainMenuPanel mainMenuPanel) {
        super();
        this.mainMenuPanel = mainMenuPanel;

        encounter = mainMenuPanel.getMenuManagerPanel().getEncounter();
        rollableActionsListModel = new DefaultListModel<>();
        rollableActionsList = new JList<>(rollableActionsListModel);

        initializeRollableActionsList();
        initializeRollableActionsListModel();

        setViewportView(rollableActionsList);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    // MODIFIES: this
    // EFFECTS: clears selection, revalidates, and repaints
    public void refresh() {
        rollableActionsList.clearSelection();
        initializeRollableActionsListModel();
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: sets up the rollable actions list
    private void initializeRollableActionsList() {
        rollableActionsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        rollableActionsList.setLayoutOrientation(JList.VERTICAL);
        rollableActionsList.setVisibleRowCount(-1);
        rollableActionsList.addListSelectionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: sets up the rollable actions list model based on what the main display is showing
    private void initializeRollableActionsListModel() {
        NPC selectedNPC = mainMenuPanel.getMainDisplayPanel().getEncounterScrollPane().getSelectedNPC();
        String selectedGroupName = mainMenuPanel.getMainDisplayPanel().getEncounterScrollPane().getSelectedGroupName();
        rollableActionsListModel.removeAllElements();

        if (selectedGroupName != null) {
            for (int i = 0; i < encounter.getSize(); i++) {
                if (encounter.getElementAt(i).hasGroup() && encounter.getElementAt(i).getTitle()
                        .getGroup().equals(selectedGroupName)) {
                    addAllRollableActionsToModel(encounter.getElementAt(i).getRollableActions());
                }
            }
        } else if (selectedNPC != null) {
            addAllRollableActionsToModel(selectedNPC.getRollableActions());
        } else {
            for (int i = 0; i < encounter.getSize(); i++) {
                addAllRollableActionsToModel(encounter.getElementAt(i).getRollableActions());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds all rollable actions to the list model
    //          *this is only here because the autograder didn't like using the native addAll()
    private void addAllRollableActionsToModel(java.util.List<RollableAction> rollableActionsList) {
        for (RollableAction a : rollableActionsList) {
            rollableActionsListModel.addElement(a);
        }
    }

    // MODIFIES: this
    // EFFECTS: prints all selected actions to the roll log then refreshes
    public void rollSelectedRollableActions() {
        for (int i : rollableActionsList.getSelectedIndices()) {
            RollLogScrollPane rollLogScrollPane = mainMenuPanel.getSideDisplayPanel().getRollLogScrollPane();
            rollLogScrollPane.printToRollLog(rollableActionsList.getModel().getElementAt(i).generateFullRollString());
        }
        refresh();
    }

    @Override
    // MODIFIES: mainMenuPanel.sideDisplayPanel
    // EFFECTS: passes the list selection event to the side display panel
    public void valueChanged(ListSelectionEvent e) {
        mainMenuPanel.getSideDisplayPanel().passListSelectionEvent(e);
    }
}
