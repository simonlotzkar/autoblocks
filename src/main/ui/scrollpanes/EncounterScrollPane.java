package ui.scrollpanes;

import enums.AbilityScore;
import model.Encounter;
import model.NPC;
import ui.panels.menus.MainMenuPanel;
import ui.textareas.NonPlayerCharacterDisplayTextArea;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;

// Represents...
public class EncounterScrollPane extends JScrollPane implements ListSelectionListener {
    private final MainMenuPanel mainMenuPanel;
    private String selectedGroupName;
    private NPC selectedNPC;

    // lists
    private Encounter encounter;
    private final JList<NPC> encounterList = new JList<>();

    private final Encounter groupEncounter = new Encounter();
    private final JList<NPC> groupList = new JList<>(groupEncounter);

    // layouts
    private final CardLayout encounterScrollPaneCardLayout = new CardLayout();

    // panels
    private final JPanel viewportDisplayManagerPanel = new JPanel(encounterScrollPaneCardLayout);

    // text areas
    private final NonPlayerCharacterDisplayTextArea npcDisplayTextArea;

    // buttons
    private JButton openNonPlayerCharacterButton;
    private JButton openGroupButton;
    private JButton changeHPButton;
    private JButton setNonPlayerCharacterGroupButton;
    private JButton deleteNonPlayerCharacterButton;
    private JButton rollCheckButton;
    private JButton rollInitiativeButton;
    private JButton backButton;

    // MODIFIES: this
    // EFFECTS: constructs the scroll pane
    public EncounterScrollPane(MainMenuPanel mainMenuPanel) {
        super(null);
        this.mainMenuPanel = mainMenuPanel;
        npcDisplayTextArea = new NonPlayerCharacterDisplayTextArea(mainMenuPanel);

        importButtons();
        initializeEncounterList();
        initializeGroupList();

        viewportDisplayManagerPanel.add(encounterList, "encounter");
        viewportDisplayManagerPanel.add(groupList, "group");
        viewportDisplayManagerPanel.add(npcDisplayTextArea, "npc");

        setViewportView(viewportDisplayManagerPanel);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    // MODIFIES: this
    // EFFECTS: sets the viewport display manager panel to the given string then refreshes
    public void setViewportDisplay(String s) {
        if (s.equals("npc")) {
            encounterScrollPaneCardLayout.show(viewportDisplayManagerPanel, s);
            npcDisplayTextArea.initializeAll();
        } else if (s.equals("group")) {
            encounterScrollPaneCardLayout.show(viewportDisplayManagerPanel, s);
        } else {
            encounterScrollPaneCardLayout.show(viewportDisplayManagerPanel, s);
            selectedNPC = null;
            selectedGroupName = null;
        }
        refresh();
    }

    // MODIFIES: this
    // EFFECTS: repopulates, revalidates, and repaints
    public void refresh() {
        encounterList.removeAll();
        encounterList.setModel(encounter);

        groupEncounter.removeAllElements();
        if (selectedGroupName != null) {
            for (int i = 0; i < encounter.size(); i++) {
                if (encounter.getElementAt(i).hasGroup() && encounter.getElementAt(i).getTitle()
                        .getGroup().equalsIgnoreCase(selectedGroupName)) {
                    groupEncounter.addElement(encounter.getElementAt(i));
                }
            }
        }
        revalidate();
        repaint();
    }

    // MODIFIES: mainMenuPanel, this
    // EFFECTS: imports buttons from the main menu panel
    private void importButtons() {
        openNonPlayerCharacterButton = mainMenuPanel.getOpenNonPlayerCharacterButton();
        openGroupButton = mainMenuPanel.getOpenGroupButton();
        changeHPButton = mainMenuPanel.getChangeHPButton();
        setNonPlayerCharacterGroupButton = mainMenuPanel.getSetNonPlayerCharacterGroupButton();
        deleteNonPlayerCharacterButton = mainMenuPanel.getDeleteNonPlayerCharacterButton();
        rollCheckButton = mainMenuPanel.getRollCheckButton();
        rollInitiativeButton = mainMenuPanel.getRollInitiativeButton();
        backButton = mainMenuPanel.getBackButton();
    }

    // MODIFIES: this
    // EFFECTS: creates the encounter list
    private void initializeEncounterList() {
        encounter = mainMenuPanel.getMenuManagerPanel().getEncounter();
        encounterList.setModel(encounter);
        encounterList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        encounterList.setLayoutOrientation(JList.VERTICAL);
        encounterList.setVisibleRowCount(-1);
        encounterList.addListSelectionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: creates the group list
    private void initializeGroupList() {
        groupList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        groupList.setLayoutOrientation(JList.VERTICAL);
        groupList.setVisibleRowCount(-1);
        groupList.addListSelectionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: prompts the user for group name to change selected NPCs to then changes them
    private void trySetNonPlayerCharacterGroup() {
        try {
            String newGroupName = JOptionPane.showInputDialog(this,
                    "Group Name",
                    "User Input Needed",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    null,
                    "").toString();

            if (newGroupName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invalid group name given.", "Failure!",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                setNonPlayerCharacterGroup(newGroupName);
            }
        } catch (NullPointerException exception) {
            // user cancelled so do nothing
        }
    }

    // MODIFIES: this
    // EFFECTS: set the selected NPCs groups to the given string
    private void setNonPlayerCharacterGroup(String newGroupName) {
        if (selectedNPC != null) {
            selectedNPC.getTitle().setGroup(newGroupName);
            refresh();
        } else if (selectedGroupName != null) {
            for (int i : groupList.getSelectedIndices()) {
                groupEncounter.getElementAt(i).getTitle().setGroup(newGroupName);
                refresh();
            }
        } else if (encounterList.getSelectedIndices().length != 0) {
            for (int i : encounterList.getSelectedIndices()) {
                encounter.getElementAt(i).getTitle().setGroup(newGroupName);
                refresh();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nothing available for hp change.",
                    "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts the user for amount of hp to change then changes the selected NPCs by that amount
    private void tryChangeNonPlayerCharacterHP() {
        try {
            int hpChange = Integer.parseInt((JOptionPane.showInputDialog(this,
                    "HitPoints",
                    "User Input Needed",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    null,
                    "")).toString());

            changeNonPlayerCharacterHP(hpChange);

        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "NumberFormatException caught. Message: "
                    + exception.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
        } catch (NullPointerException exception) {
            // user cancelled so do nothing
        }
    }

    // MODIFIES: this
    // EFFECTS: changes the HP for the selected NPCs or throws an error if unable
    private void changeNonPlayerCharacterHP(int hpChange) {
        if (selectedNPC != null) {
            selectedNPC.changeHP(hpChange);
            refresh();
        } else if (selectedGroupName != null) {
            for (int i : groupList.getSelectedIndices()) {
                groupEncounter.getElementAt(i).changeHP(hpChange);
                refresh();
            }
        } else if (encounterList.getSelectedIndices().length != 0) {
            for (int i : encounterList.getSelectedIndices()) {
                encounter.getElementAt(i).changeHP(hpChange);
                refresh();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nothing available for hp change.",
                    "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to delete their selected NPCs then deletes them if they confirm
    private void deleteNonPlayerCharacter() {
        String warningMessage = generateDeletionWarning();

        int confirmDelete = JOptionPane.showConfirmDialog(this, warningMessage, "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);

        if (confirmDelete == JOptionPane.YES_OPTION) {
            if (selectedNPC != null) {
                encounter.removeElement(selectedNPC);
                refresh();
            } else if (selectedGroupName != null) {
                for (Object o : groupList.getSelectedValuesList()) {
                    encounter.removeElement(o);
                    refresh();
                }
            } else if (encounterList.getSelectedIndices().length != 0) {
                for (Object o : encounterList.getSelectedValuesList()) {
                    encounter.removeElement(o);
                    refresh();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nothing available to delete.",
                        "Failure!", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: returns the deletion warning depending on how many NPCs are selected
    private String generateDeletionWarning() {
        String warningMessage = "Do you want to delete this NPC?";

        if (selectedGroupName != null) {
            warningMessage = "Do you want to delete " + groupList.getSelectedIndices().length + " NPCs?";
        } else if (selectedNPC == null) {
            warningMessage = "Do you want to delete " + encounterList.getSelectedIndices().length + " NPCs?";
        }

        return warningMessage;
    }

    // MODIFIES: this
    // EFFECTS: rolls initiative depending on what's selected
    private void rollInitiative() {
        RollLogScrollPane rollLogScrollPane = mainMenuPanel.getSideDisplayPanel().getRollLogScrollPane();
        if (selectedNPC != null) {
            rollLogScrollPane.printToRollLog(selectedNPC.rollInitiative());
            refresh();
        } else if (selectedGroupName != null) {
            for (int i : groupList.getSelectedIndices()) {
                rollLogScrollPane.printToRollLog(groupEncounter.getElementAt(i).rollInitiative());
                refresh();
            }
        } else if (encounterList.getSelectedIndices().length != 0) {
            for (int i : encounterList.getSelectedIndices()) {
                rollLogScrollPane.printToRollLog(encounter.getElementAt(i).rollInitiative());
                refresh();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nothing available for initiative roll.",
                    "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user for which ability check to roll, if they dont cancel rolls to the output log
    private void tryRollCheck() {
        try {
            AbilityScore[] abilityScores = {AbilityScore.STRENGTH, AbilityScore.DEXTERITY, AbilityScore.CONSTITUTION,
                    AbilityScore.INTELLIGENCE, AbilityScore.WISDOM, AbilityScore.CHARISMA};
            int index = JOptionPane.showOptionDialog(this,
                    "Ability for Check",
                    "User Input Needed",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    abilityScores,
                    "");

            rollCheck(abilityScores[index]);
        } catch (NullPointerException exception) {
            // user cancelled so do nothing
        }
    }

    // MODIFIES: this
    // EFFECTS: rolls the given ability score check depending on what's selected
    private void rollCheck(AbilityScore abilityScore) {
        RollLogScrollPane rollLogScrollPane = mainMenuPanel.getSideDisplayPanel().getRollLogScrollPane();
        if (selectedNPC != null) {
            rollLogScrollPane.printToRollLog(selectedNPC.rollCheckAsString(abilityScore));
            refresh();
        } else if (selectedGroupName != null) {
            for (int i : groupList.getSelectedIndices()) {
                rollLogScrollPane.printToRollLog(groupEncounter.getElementAt(i).rollCheckAsString(abilityScore));
                refresh();
            }
        } else if (encounterList.getSelectedIndices().length != 0) {
            for (int i : encounterList.getSelectedIndices()) {
                rollLogScrollPane.printToRollLog(encounter.getElementAt(i).rollCheckAsString(abilityScore));
                refresh();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nothing available for ability check.",
                    "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: selects the group of the selected NPC or displays an error
    private void trySelectGroup() {
        if (encounterList.getSelectedValue().hasGroup()) {
            selectedGroupName = encounter.getElementAt(encounterList.getSelectedIndex()).getTitle().getGroup();
            mainMenuPanel.setDisplays("group");
        } else {
            JOptionPane.showMessageDialog(this, "Selected NPC has no group.",
                    "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // MODIFIES: menuManagerPanel, mainMenuPanel
    // EFFECTS: processes the back button press
    private void processBackButton() {
        if (npcDisplayTextArea.isShowing()) {
            mainMenuPanel.setDisplays("encounter");
        } else if (groupList.isShowing()) {
            mainMenuPanel.setDisplays("encounter");
        } else {
            mainMenuPanel.getMenuManagerPanel().setMenu("title");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes the given action event / button press
    public void processAction(ActionEvent e) {
        if (e.getSource() == openNonPlayerCharacterButton) {
            if (selectedGroupName == null) {
                selectedNPC = (encounter.getElementAt(encounterList.getSelectedIndex()));
            } else {
                selectedNPC = (groupEncounter.getElementAt(groupList.getSelectedIndex()));
            }
            mainMenuPanel.setDisplays("npc");
        } else if (e.getSource() == openGroupButton) {
            trySelectGroup();
        } else if (e.getSource() == changeHPButton) {
            tryChangeNonPlayerCharacterHP();
        } else if (e.getSource() == setNonPlayerCharacterGroupButton) {
            trySetNonPlayerCharacterGroup();
        } else if (e.getSource() == deleteNonPlayerCharacterButton) {
            deleteNonPlayerCharacter();
        } else if (e.getSource() == rollCheckButton) {
            tryRollCheck();
        } else if (e.getSource() == rollInitiativeButton) {
            rollInitiative();
        } else if (e.getSource() == backButton) {
            processBackButton();
        }
    }

    // MODIFIES: this
    // EFFECTS: disables and enables multi selection buttons when user is changing list selection
    //          or if just one item is selected enables or disables single selection buttons
    public void valueChanged(ListSelectionEvent e) {
        changeHPButton.setEnabled(!e.getValueIsAdjusting());
        setNonPlayerCharacterGroupButton.setEnabled(!e.getValueIsAdjusting());
        deleteNonPlayerCharacterButton.setEnabled(!e.getValueIsAdjusting());
        rollCheckButton.setEnabled(!e.getValueIsAdjusting());
        rollInitiativeButton.setEnabled(!e.getValueIsAdjusting());

        openNonPlayerCharacterButton.setEnabled(encounterList.getSelectedIndices().length == 1
                || groupList.getSelectedIndices().length == 1);
        openGroupButton.setEnabled(encounterList.getSelectedIndices().length == 1
                && encounterList.getSelectedValue().hasGroup());
    }

    // -----------------------------------------------------------------------
    // getters
    // EFFECTS: gets the selected NPC
    public NPC getSelectedNPC() {
        return selectedNPC;
    }

    // EFFECTS: gets the selected group name
    public String getSelectedGroupName() {
        return selectedGroupName;
    }
}
