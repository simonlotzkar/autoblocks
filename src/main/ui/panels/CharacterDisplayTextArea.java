package ui.panels;

import model.statblockfields.Ability;
import model.statblockfields.AbilityScores;
import model.statblockfields.Action;
import model.statblockfields.Title;
import ui.panels.menus.MainMenuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Represents...
public class CharacterDisplayTextArea extends JTextArea {
    private final MainMenuPanel mainMenuPanel;
    private model.Character selectedCharacter;

    // buttons
    private JButton changeHPButton;
    private JButton setCharacterGroupButton;
    private JButton deleteCharacterButton;
    private JButton rollCheckButton;
    private JButton rollInitiativeButton;
    private JButton backButton;

    // EFFECTS: constructs this display panel
    public CharacterDisplayTextArea(MainMenuPanel mainMenuPanel) {
        super();
        this.mainMenuPanel = mainMenuPanel;
        this.setVisible(true);
        this.setEditable(false);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        importButtons();
    }

    // EFFECTS: ...
    public void initializeAll() {
        this.selectedCharacter = mainMenuPanel.getSelectedCharacter();
        this.setText("");
        initializeTitlePanel();
        initializeCombatStatsPanel();
        initializeAbilityScoresPanel();
        initializePeripheralFieldsPanel();
        initializeAbilitiesPanel();
        initializeLegendaryPanel();
        this.revalidate();
        this.repaint();
    }

    // EFFECTS: ...
    private void initializeTitlePanel() {
        Title title = selectedCharacter.getTitle();
        if (title.getGroup() != null) {
            this.append(title.getName() + " (CR " + selectedCharacter.getChallengeRating()
                    + "), Group: " + title.getGroup());
            this.append("\n" + title.getSize() + " " + title.getType() + ", " + title.getAlignment());
            this.append("\n---------------");
        } else {
            this.append(title.getName() + " (CR " + selectedCharacter.getChallengeRating() + ")");
            this.append("\n" + title.getSize() + " " + title.getType() + ", " + title.getAlignment());
            this.append("\n---------------");
        }
    }

    // EFFECTS: ...
    private void initializeCombatStatsPanel() {
        this.append("\nHit Points: " + selectedCharacter.getHPString());
        this.append("\nArmour Class: " + selectedCharacter.getArmour().toString());
        this.append("\nSpeeds: " + selectedCharacter.getSpeeds().toString());
    }

    // EFFECTS: ...
    private void initializeAbilityScoresPanel() {
        AbilityScores abilityScores = selectedCharacter.getAbilityScores();
        this.append("\n---------------");
        this.append("\nSTR " + abilityScores.getStrength()
                + " (" + abilityScores.getModifier("strength") + ")");
        this.append("\nDEX" + abilityScores.getDexterity()
                + " (" + abilityScores.getModifier("dexterity") + ")");
        this.append("\nCON" + abilityScores.getConstitution()
                + " (" + abilityScores.getModifier("constitution") + ")");
        this.append("\nINT" + abilityScores.getIntelligence()
                + " (" + abilityScores.getModifier("intelligence") + ")");
        this.append("\nWIS" + abilityScores.getWisdom()
                + " (" + abilityScores.getModifier("wisdom") + ")");
        this.append("\nCHA" + abilityScores.getCharisma()
                + " (" + abilityScores.getModifier("charisma") + ")");
        this.append("\n---------------");
    }

    // EFFECTS: ...
    private void initializePeripheralFieldsPanel() {
        if (!selectedCharacter.getSavingThrowProficienciesString().isEmpty()) {
            this.append("\nSaving Throws: " + selectedCharacter.getSavingThrowProficienciesString());
        }
        if (!selectedCharacter.getSkillProficienciesString().isEmpty()) {
            this.append("\nSkills: " + selectedCharacter.getSkillProficienciesString());
        }
        if (!selectedCharacter.getResistancesString().isEmpty()) {
            this.append("\nResistances: " + selectedCharacter.getResistancesString());
        }
        if (!selectedCharacter.getConditionImmunitiesString().isEmpty()) {
            this.append("\nImmunities: " + selectedCharacter.getConditionImmunitiesString());
        }
        if (selectedCharacter.getLanguages() != null) {
            this.append("\nLanguages: " + selectedCharacter.getLanguages().toString());
        }
        if (selectedCharacter.getProficiency() != 0) {
            this.append("\nProficiency bonus: " + selectedCharacter.getProficiency());
        }
    }

    // EFFECTS: ...
    private void initializeAbilitiesPanel() {
        if (selectedCharacter.getAbilities() != null) {
            this.append("\n---------------");
            for (Ability a : selectedCharacter.getAbilities()) {
                this.append("\n" + a.toString());
            }
        }
    }

    // EFFECTS: ...
    private void initializeLegendaryPanel() {
        if (selectedCharacter.getLegendaryMechanics() != null) {
            this.append("\n\nLegendary Mechancis");
            this.append("\n---------------");
            for (Ability a : selectedCharacter.getLegendaryMechanics().getLegendaryActions()) {
                this.append("\n" + a.toString());
            }
        }
    }

    // EFFECTS: ...
    private void importButtons() {
        rollCheckButton = mainMenuPanel.getRollCheckButton();
        rollInitiativeButton = mainMenuPanel.getRollInitiativeButton();
        backButton = mainMenuPanel.getBackButton();
        changeHPButton = mainMenuPanel.getChangeHPButton();
        setCharacterGroupButton = mainMenuPanel.getSetCharacterGroupButton();
        deleteCharacterButton = mainMenuPanel.getDeleteCharacterButton();
    }

    // EFFECTS: ...
    public void passAction(ActionEvent e) {
        if (e.getSource() == rollCheckButton) {
            rollCheck();
        } else if (e.getSource() == rollInitiativeButton) {
            mainMenuPanel.getSideDisplayPanel().printToOutputLog(selectedCharacter.rollInitiative());
        } else if (e.getSource() == changeHPButton) {
            changeCharactersHP();
        } else if (e.getSource() == setCharacterGroupButton) {
            setCharactersGroup();
        } else if (e.getSource() == deleteCharacterButton) {
            deleteCharacters();
        } else if (e.getSource() == backButton) {
            mainMenuPanel.setDisplays("encounter");
        }
    }

    // EFFECTS: prompts user for a group name, sets selected characters to the given group name
    private void setCharactersGroup() {
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
                selectedCharacter.getTitle().setGroup(command);
            }
        } catch (NullPointerException exception) {
            // user cancelled so do nothing
        }
        initializeAll();
    }

    // EFFECTS: prompts user for amount of hitpoints to add to selected characters' hp
    private void changeCharactersHP() {
        try {
            int command = Integer.parseInt((JOptionPane.showInputDialog(this,
                    "HitPoints",
                    "User Input Needed",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    null,
                    "")).toString());

            selectedCharacter.changeHP(command);

        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "NumberFormatException caught. Message: "
                    + exception.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
        } catch (NullPointerException exception) {
            // user cancelled so do nothing
        }
        initializeAll();
    }

    // EFFECTS: prompts user for confirmation and number of selected then deletes them from the encounter
    private void deleteCharacters() {
        int command = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this character?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);

        if (command == JOptionPane.YES_OPTION) {
            mainMenuPanel.getMenuManagerPanel().getEncounterListModel().removeElement(selectedCharacter);
        }
        mainMenuPanel.setDisplays("encounter");
    }

    // EFFECTS: ...
    private void rollCheck() {
        try {
            String command = JOptionPane.showInputDialog(this,
                    "Ability for Check",
                    "User Input Needed",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    null,
                    "").toString();

            if (!("strengthconstitutiondexterityintelligencewisdomcharisma").contains(command.toLowerCase())) {
                JOptionPane.showMessageDialog(this, "Invalid check given.", "Failure!",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                mainMenuPanel.getSideDisplayPanel().printToOutputLog(selectedCharacter.rollCheckAsString(command));
            }
        } catch (NullPointerException exception) {
            // user cancelled so do nothing
        }
    }
}
