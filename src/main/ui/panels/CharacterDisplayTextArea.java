package ui.panels;

import model.statblockfields.Ability;
import model.statblockfields.AbilityScores;
import model.statblockfields.Action;
import model.statblockfields.Title;
import ui.panels.menus.MainMenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// Represents...
public class CharacterDisplayTextArea extends JTextArea {
    private final MainMenuPanel mainMenuPanel;
    private model.Character selectedCharacter;

    // EFFECTS: constructs this display panel
    public CharacterDisplayTextArea(MainMenuPanel mainMenuPanel) {
        super();
        this.mainMenuPanel = mainMenuPanel;
        setVisible(true);
        setEditable(false);
        setLineWrap(true);
        setWrapStyleWord(true);

        setOpaque(false);
        repaint();
    }

    // EFFECTS: ...
    public void initializeAll() {
        this.selectedCharacter = mainMenuPanel.getMainDisplayPanel().getSelectedCharacter();
        this.setText("");
        initializeTitlePanel();
        initializeCombatStatsPanel();
        initializeAbilityScoresPanel();
        initializePeripheralFieldsPanel();
        initializeAbilitiesPanel();
        initializeLegendaryPanel();
        this.revalidate();
        this.repaint();
        this.setOpaque(false);
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
}
