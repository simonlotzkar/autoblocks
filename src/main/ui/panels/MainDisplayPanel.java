package ui.panels;

import model.Character;
import model.StatBlock;
import ui.panels.menus.MainMenuPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;

// Represents a main display that displays one of four panels: encounter, group, character, or library
public class MainDisplayPanel extends DisplayPanel implements ListSelectionListener {
    // selections
    private model.Character selectedCharacter;
    private String selectedGroupName;

    // labels
    private final JLabel mainDisplayTitleLabel = new JLabel();

    // layouts
    private final CardLayout mainDisplaysCardLayout = new CardLayout();
    private final CardLayout encounterMainDisplayCardLayout = new CardLayout();

    // panels
    private final JPanel mainDisplaysManagerPanel = new JPanel(mainDisplaysCardLayout);
    private final JPanel encounterMainDisplayManagerPanel = new JPanel(encounterMainDisplayCardLayout);

    // lists
    private DefaultListModel<model.Character> encounterListModel
            = mainMenuPanel.getMenuManagerPanel().getEncounterListModel();
    private final JList<model.Character> encounterList = new JList<>(encounterListModel);

    private final DefaultListModel<model.Character> groupListModel = new DefaultListModel<>();
    private final JList<model.Character> groupList = new JList<>(groupListModel);

    // scroll panes
    private final JScrollPane encounterScrollPane = new JScrollPane();
    private final LibraryScrollPane libraryScrollPane = new LibraryScrollPane(mainMenuPanel);

    // text areas
    private final CharacterDisplayTextArea characterDisplayTextArea = new CharacterDisplayTextArea(mainMenuPanel);

    // buttons
    private JButton openCharacterButton;
    private JButton openGroupButton;
    private JButton changeHPButton;
    private JButton setCharacterGroupButton;
    private JButton deleteCharacterButton;
    private JButton rollCheckButton;
    private JButton rollInitiativeButton;
    private JButton backButton;

    // EFFECTS: constructs this panel
    public MainDisplayPanel(MainMenuPanel mainMenuPanel) {
        super(new BorderLayout(), mainMenuPanel); // sets the layout manager, mainmenu panel, size, and visibility
        importButtons();

        initializeEncounterScrollPane();
        mainDisplaysManagerPanel.add(libraryScrollPane, "library");
        mainDisplaysManagerPanel.add(encounterScrollPane, "encounter");

        initializeEncounterList();
        initializeGroupList();
        encounterScrollPane.setViewportView(encounterMainDisplayManagerPanel);
        encounterMainDisplayManagerPanel.add(encounterList, "encounter");
        encounterMainDisplayManagerPanel.add(groupList, "group");
        encounterMainDisplayManagerPanel.add(characterDisplayTextArea, "character");

        this.add(mainDisplayTitleLabel, BorderLayout.NORTH);
        this.add(mainDisplaysManagerPanel, BorderLayout.CENTER);
    }

    // EFFECTS: ...
    private void importButtons() {
        openCharacterButton = mainMenuPanel.getOpenCharacterButton();
        openGroupButton = mainMenuPanel.getOpenGroupButton();
        changeHPButton = mainMenuPanel.getChangeHPButton();
        setCharacterGroupButton = mainMenuPanel.getSetCharacterGroupButton();
        deleteCharacterButton = mainMenuPanel.getDeleteCharacterButton();
        rollCheckButton = mainMenuPanel.getRollCheckButton();
        rollInitiativeButton = mainMenuPanel.getRollInitiativeButton();
        backButton = mainMenuPanel.getBackButton();
    }

    // EFFECTS: ...
    private void initializeEncounterScrollPane() {
        encounterScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        encounterScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        encounterScrollPane.setOpaque(false);
    }

    // EFFECTS: ...
    private void initializeEncounterList() {
        encounterListModel = mainMenuPanel.getMenuManagerPanel().getEncounterListModel();

        encounterList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        encounterList.setLayoutOrientation(JList.VERTICAL);
        encounterList.setVisibleRowCount(-1);
        encounterList.addListSelectionListener(this);
        encounterList.setOpaque(false);
    }

    // EFFECTS: ...
    private void initializeGroupList() {
        groupList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        groupList.setLayoutOrientation(JList.VERTICAL);
        groupList.setVisibleRowCount(-1);
        groupList.addListSelectionListener(this);
        groupList.setOpaque(false);
    }

    // EFFECTS: ...
    public void refreshEncounterScrollPane() {
        refreshGroupList();

        encounterList.removeAll();
        encounterList.setModel(encounterListModel);

        encounterScrollPane.revalidate();
        encounterScrollPane.repaint();
    }

    // EFFECTS: ...
    private void refreshGroupList() {
        groupListModel.removeAllElements();
        if (selectedGroupName != null) {
            for (int i = 0; i < encounterListModel.size(); i++) {
                if (encounterListModel.getElementAt(i).hasGroup() && encounterListModel.getElementAt(i).getTitle()
                        .getGroup().equalsIgnoreCase(selectedGroupName)) {
                    groupListModel.addElement(encounterListModel.getElementAt(i));
                }
            }
        }
    }

    // EFFECTS: ...
    public void setMainDisplay(String s) {
        encounterList.clearSelection();
        libraryScrollPane.clearSelection();
        mainDisplayTitleLabel.setText(s + " display.");

        if (s.equals("library")) {
            mainDisplaysCardLayout.show(mainDisplaysManagerPanel, s);
        } else {
            if (s.equals("character")) {
                encounterMainDisplayCardLayout.show(encounterMainDisplayManagerPanel, s);
                characterDisplayTextArea.initializeAll();
                refreshEncounterScrollPane();
            } else if (s.equals("group")) {
                encounterMainDisplayCardLayout.show(encounterMainDisplayManagerPanel, s);
                refreshEncounterScrollPane();
            } else {
                mainDisplaysCardLayout.show(mainDisplaysManagerPanel, "encounter");
                encounterMainDisplayCardLayout.show(encounterMainDisplayManagerPanel, s);
                selectedCharacter = null;
                selectedGroupName = null;
            }
        }
    }

    // EFFECTS: ...
    private void trySetCharactersGroup() {
        try {
            String newGroupName = JOptionPane.showInputDialog(this,
                    "Group Name",
                    "User Input Needed",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    null,
                    "").toString();

            if (newGroupName.isBlank() || newGroupName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invalid group name given.", "Failure!",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                setCharactersGroup(newGroupName);
            }
        } catch (NullPointerException exception) {
            // user cancelled so do nothing
        }
    }

    // EFFECTS: ...
    private void setCharactersGroup(String newGroupName) {
        if (selectedCharacter != null) {
            selectedCharacter.getTitle().setGroup(newGroupName);
            refreshEncounterScrollPane();
        } else if (selectedGroupName != null) {
            for (int i : groupList.getSelectedIndices()) {
                groupListModel.getElementAt(i).getTitle().setGroup(newGroupName);
                refreshEncounterScrollPane();
            }
        } else if (encounterList.getSelectedIndices().length != 0) {
            for (int i : encounterList.getSelectedIndices()) {
                encounterListModel.getElementAt(i).getTitle().setGroup(newGroupName);
                refreshEncounterScrollPane();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nothing available for hp change.",
                    "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // EFFECTS: ...
    private void tryChangeCharactersHP() {
        try {
            int hpChange = Integer.parseInt((JOptionPane.showInputDialog(this,
                    "HitPoints",
                    "User Input Needed",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    null,
                    "")).toString());

            changeCharactersHP(hpChange);

        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "NumberFormatException caught. Message: "
                    + exception.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
        } catch (NullPointerException exception) {
            // user cancelled so do nothing
        }
    }

    // EFFECTS: ...
    private void changeCharactersHP(int hpChange) {
        if (selectedCharacter != null) {
            selectedCharacter.changeHP(hpChange);
            refreshEncounterScrollPane();
        } else if (selectedGroupName != null) {
            for (int i : groupList.getSelectedIndices()) {
                groupListModel.getElementAt(i).changeHP(hpChange);
                refreshEncounterScrollPane();
            }
        } else if (encounterList.getSelectedIndices().length != 0) {
            for (int i : encounterList.getSelectedIndices()) {
                encounterListModel.getElementAt(i).changeHP(hpChange);
                refreshEncounterScrollPane();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nothing available for hp change.",
                    "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // EFFECTS: ...
    private void deleteCharacters() {
        String warningMessage = generateDeletionWarning();

        int confirmDelete = JOptionPane.showConfirmDialog(this, warningMessage, "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);

        if (confirmDelete == JOptionPane.YES_OPTION) {
            if (selectedCharacter != null) {
                encounterListModel.removeElement(selectedCharacter);
                refreshEncounterScrollPane();
            } else if (selectedGroupName != null) {
                for (Object o : groupList.getSelectedValuesList()) {
                    encounterListModel.removeElement(o);
                    refreshEncounterScrollPane();
                }
            } else if (encounterList.getSelectedIndices().length != 0) {
                for (Object o : encounterList.getSelectedValuesList()) {
                    encounterListModel.removeElement(o);
                    refreshEncounterScrollPane();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nothing available to delete.",
                        "Failure!", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // EFFECTS: ...
    private String generateDeletionWarning() {
        String warningMessage = "Do you want to delete this character?";

        if (selectedGroupName != null) {
            warningMessage = "Do you want to delete " + groupList.getSelectedIndices().length + " characters?";
        } else if (selectedCharacter == null) {
            warningMessage = "Do you want to delete " + encounterList.getSelectedIndices().length + " characters?";
        }

        return warningMessage;
    }

    // EFFECTS: ...
    private void rollInitiative() {
        if (selectedCharacter != null) {
            mainMenuPanel.getSideDisplayPanel().printToOutputLog(selectedCharacter.rollInitiative());
            refreshEncounterScrollPane();
        } else if (selectedGroupName != null) {
            for (int i : groupList.getSelectedIndices()) {
                mainMenuPanel.getSideDisplayPanel()
                        .printToOutputLog(groupListModel.getElementAt(i).rollInitiative());
                refreshEncounterScrollPane();
            }
        } else if (encounterList.getSelectedIndices().length != 0) {
            for (int i : encounterList.getSelectedIndices()) {
                mainMenuPanel.getSideDisplayPanel()
                        .printToOutputLog(encounterListModel.getElementAt(i).rollInitiative());
                refreshEncounterScrollPane();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nothing available for initiative roll.",
                    "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // EFFECTS: ...
    private void tryRollCheck() {
        try {
            String abilityScore = JOptionPane.showInputDialog(this,
                    "Ability for Check",
                    "User Input Needed",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    null,
                    "").toString();

            if (!("strengthconstitutiondexterityintelligencewisdomcharisma").contains(abilityScore.toLowerCase())) {
                JOptionPane.showMessageDialog(this, "Invalid check given.", "Failure!",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                rollCheck(abilityScore);
            }
        } catch (NullPointerException exception) {
            // user cancelled so do nothing
        }
    }

    // EFFECTS: ...
    private void rollCheck(String abilityScore) {
        if (selectedCharacter != null) {
            mainMenuPanel.getSideDisplayPanel().printToOutputLog(selectedCharacter.rollCheckAsString(abilityScore));
            refreshEncounterScrollPane();
        } else if (selectedGroupName != null) {
            for (int i : groupList.getSelectedIndices()) {
                mainMenuPanel.getSideDisplayPanel()
                        .printToOutputLog(groupListModel.getElementAt(i).rollCheckAsString(abilityScore));
                refreshEncounterScrollPane();
            }
        } else if (encounterList.getSelectedIndices().length != 0) {
            for (int i : encounterList.getSelectedIndices()) {
                mainMenuPanel.getSideDisplayPanel()
                        .printToOutputLog(encounterListModel.getElementAt(i).rollCheckAsString(abilityScore));
                refreshEncounterScrollPane();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nothing available for ability check.",
                    "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // EFFECTS: processes button presses from user
    public void passAction(ActionEvent e) {
        if (e.getSource() == openCharacterButton) {
            if (selectedGroupName == null) {
                selectedCharacter = (encounterListModel.getElementAt(encounterList.getSelectedIndex()));
            } else {
                selectedCharacter = (groupListModel.getElementAt(groupList.getSelectedIndex()));
            }
            mainMenuPanel.setDisplays("character");
        } else if (e.getSource() == openGroupButton) {
            trySelectGroup();
        } else if (e.getSource() == changeHPButton) {
            tryChangeCharactersHP();
        } else if (e.getSource() == setCharacterGroupButton) {
            trySetCharactersGroup();
        } else if (e.getSource() == deleteCharacterButton) {
            deleteCharacters();
        } else if (e.getSource() == rollCheckButton) {
            tryRollCheck();
        } else if (e.getSource() == rollInitiativeButton) {
            rollInitiative();
        } else if (e.getSource() == backButton) {
            processBackButton();
        }
    }

    // EFFECTS: ...
    private void trySelectGroup() {
        if (encounterList.getSelectedValue().hasGroup()) {
            selectedGroupName = encounterListModel.getElementAt(encounterList.getSelectedIndex()).getTitle().getGroup();
            mainMenuPanel.setDisplays("group");
        } else {
            JOptionPane.showMessageDialog(this, "Selected character has no group.",
                    "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // EFFECTS: ...
    private void processBackButton() {
        if (characterDisplayTextArea.isShowing()) {
            mainMenuPanel.setDisplays("encounter");
        } else if (groupList.isShowing()) {
            mainMenuPanel.setDisplays("encounter");
        } else {
            mainMenuPanel.getMenuManagerPanel().setMenu("title");
        }
    }

    // EFFECTS: disables and enables multi selection buttons when user is changing list selection
    //          or if just one item is selected enables or disables single selection buttons
    public void valueChanged(ListSelectionEvent e) {
        changeHPButton.setEnabled(!e.getValueIsAdjusting());
        setCharacterGroupButton.setEnabled(!e.getValueIsAdjusting());
        deleteCharacterButton.setEnabled(!e.getValueIsAdjusting());
        rollCheckButton.setEnabled(!e.getValueIsAdjusting());
        rollInitiativeButton.setEnabled(!e.getValueIsAdjusting());

        openCharacterButton.setEnabled(encounterList.getSelectedIndices().length == 1
                || groupList.getSelectedIndices().length == 1);
        openGroupButton.setEnabled(encounterList.getSelectedIndices().length == 1
                && encounterList.getSelectedValue().hasGroup());
    }

    // -----------------------------------------------------------------------
    // getters
    // EFFECTS: ...
    public LibraryScrollPane getLibraryScrollPane() {
        return libraryScrollPane;
    }

    // EFFECTS: ...
    public JList<model.Character> getEncounterScrollPane() {
        return encounterList;
    }

    // EFFECTS: ...
    public JList<model.Character> getGroupDisplayPanel() {
        return groupList;
    }

    // EFFECTS: ...
    public CharacterDisplayTextArea getCharacterDisplayPanel() {
        return characterDisplayTextArea;
    }

    // EFFECTS: ...
    public Character getSelectedCharacter() {
        return selectedCharacter;
    }

    // EFFECTS: ...
    public String getSelectedGroupName() {
        return selectedGroupName;
    }
}
