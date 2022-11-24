package ui.panels;

import model.Character;
import ui.panels.menus.MainMenuPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

// Represents...
public class EncounterScrollPane extends JScrollPane implements ListSelectionListener {
    private final JList<model.Character> encounterJList;
    private final DefaultListModel<model.Character> encounterListModel;
    private final MainMenuPanel mainMenuPanel;

    // buttons
    private JButton backButton;
    private JButton openCharacterButton;
    private JButton openGroupButton;
    private JButton changeHPButton;
    private JButton setCharacterGroupButton;
    private JButton deleteCharacterButton;

    // EFFECTS: constructs this display panel
    public EncounterScrollPane(MainMenuPanel mainMenuPanel) {
        super(null);
        this.mainMenuPanel = mainMenuPanel;
        this.setSize(mainMenuPanel.getSize());
        this.setVisible(true);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        encounterListModel = mainMenuPanel.getMenuManagerPanel().getEncounterListModel();
        this.encounterJList = new JList<>(encounterListModel);

        this.setViewportView(encounterJList);

        importButtons();
        initializeJList();
    }

    // EFFECTS: ...
    private void importButtons() {
        backButton = mainMenuPanel.getBackButton();
        openCharacterButton = mainMenuPanel.getOpenCharacterButton();
        openGroupButton = mainMenuPanel.getOpenGroupButton();
        changeHPButton = mainMenuPanel.getChangeHPButton();
        setCharacterGroupButton = mainMenuPanel.getSetCharacterGroupButton();
        deleteCharacterButton = mainMenuPanel.getDeleteCharacterButton();
    }

    // MODIFIES: this
    // EFFECTS: ...
    protected void initializeJList() {
        encounterJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        encounterJList.setLayoutOrientation(JList.VERTICAL_WRAP);
        encounterJList.setVisibleRowCount(-1);
        encounterJList.addListSelectionListener(this);
    }

    // EFFECTS: prompts user for a group name, sets selected characters to the given group name
    private void setCharactersGroup() {
        ListModel<Character> encounterListModel = encounterJList.getModel();
        try {
            String command = JOptionPane.showInputDialog(this,
                    "Group Name",
                    "User Input Needed",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    null,
                    "").toString();

            if (command.isBlank() || command.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invalid group name given.", "Failure!",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                for (int i : encounterJList.getSelectedIndices()) {
                    encounterListModel.getElementAt(i).getTitle().setGroup(command);
                }
            }

        } catch (NullPointerException exception) {
            // user cancelled so do nothing
        }
    }

    // EFFECTS: prompts user for amount of hitpoints to add to selected characters' hp
    private void changeCharactersHP() {
        ListModel<Character> encounterListModel = encounterJList.getModel();
        try {
            int command = Integer.parseInt((JOptionPane.showInputDialog(this,
                    "HitPoints",
                    "User Input Needed",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    null,
                    "")).toString());

            for (int i : encounterJList.getSelectedIndices()) {
                encounterListModel.getElementAt(i).changeHP(command);
            }

        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "NumberFormatException caught. Message: "
                    + exception.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
        } catch (NullPointerException exception) {
            // user cancelled so do nothing
        }
    }

    // EFFECTS: prompts user for confirmation and number of selected then deletes them from the encounter
    private void deleteCharacters() {
        int command = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete " + encounterJList.getSelectedIndices().length
                        + " characters?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);

        if (command == JOptionPane.YES_OPTION) {
            for (Object o : encounterJList.getSelectedValuesList()) {
                encounterListModel.removeElement(o);
            }
        }
    }

    // EFFECTS: processes button presses from user
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            mainMenuPanel.getMenuManagerPanel().setMenu("title");
        } else if (e.getSource() == openCharacterButton) {
            mainMenuPanel.setSelectedCharacter(encounterJList.getModel()
                    .getElementAt(encounterJList.getSelectedIndex()));
            mainMenuPanel.getMainDisplayPanel().setMainDisplay("character");
        } else if (e.getSource() == openGroupButton) {
            mainMenuPanel.setSelectedGroup(encounterJList.getModel()
                    .getElementAt(encounterJList.getSelectedIndex()).getTitle().getGroup());
            mainMenuPanel.getMainDisplayPanel().setMainDisplay("group");
        } else if (e.getSource() == changeHPButton) {
            changeCharactersHP();
            this.revalidate();
            this.repaint();
        } else if (e.getSource() == setCharacterGroupButton) {
            setCharactersGroup();
            this.revalidate();
            this.repaint();
        } else if (e.getSource() == deleteCharacterButton) {
            deleteCharacters();
            this.revalidate();
            this.repaint();
        }
    }

    // EFFECTS: disables and enables multi selection buttons when user is changing list selection
    //          or if just one item is selected enables or disables single selection buttons
    public void valueChanged(ListSelectionEvent e) {
        changeHPButton.setEnabled(!e.getValueIsAdjusting());
        setCharacterGroupButton.setEnabled(!e.getValueIsAdjusting());
        deleteCharacterButton.setEnabled(!e.getValueIsAdjusting());

        mainMenuPanel.getSideDisplayPanel().valueChanged(e);

        openCharacterButton.setEnabled(encounterJList.getSelectedIndices().length == 1);
        openGroupButton.setEnabled(encounterJList.getSelectedIndices().length == 1);
    }
}
