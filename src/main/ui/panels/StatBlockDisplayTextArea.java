package ui.panels;

import enums.AbilityScore;
import model.statblockfields.*;
import model.statblockfields.RollableAction;
import ui.panels.menus.MainMenuPanel;

import javax.swing.*;

// Represents a text area that displays a text representation of the selected statblock
public class StatBlockDisplayTextArea extends JTextArea {
    private final MainMenuPanel mainMenuPanel;
    private model.StatBlock selectedStatBlock;

    private static final String NONE_SELECTED = "No StatBlock Selected";

    // MODIFIES: this
    // EFFECTS: constructs this text area
    public StatBlockDisplayTextArea(MainMenuPanel mainMenuPanel) {
        super(NONE_SELECTED);
        this.mainMenuPanel = mainMenuPanel;
        this.setVisible(true);
        this.setEditable(false);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);

        initializeAll();
    }

    // MODIFIES: this
    // EFFECTS: selects statblock and adds all of the selected statblock's data to the text area
    public void initializeAll() {
        if (mainMenuPanel.getSideDisplayPanel() == null) {
            selectedStatBlock = null;
        } else {
            selectedStatBlock = mainMenuPanel.getSideDisplayPanel().getSelectedStatBlock();
        }

        if (selectedStatBlock != null) {
            this.setText("");
            initializeTitlePanel();
            initializeCombatStatsPanel();
            initializeAbilityScoresPanel();
            initializePeripheralFieldsPanel();
            initializeAbilitiesPanel();
            initializeRollableActionsPanel();
            initializeLegendaryPanel();
            revalidate();
            repaint();
        } else {
            this.setText(NONE_SELECTED);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the selected statblock's title data to the text area
    private void initializeTitlePanel() {
        Title title = selectedStatBlock.getTitle();
        this.append(title.getName() + " (CR " + selectedStatBlock.getChallengeRating() + ")");
        this.append("\n" + title.getSize() + " " + title.getType() + ", " + title.getAlignment());
        this.append("\n---------------");
    }

    // MODIFIES: this
    // EFFECTS: adds the selected statblock's combat stats data to the text area
    private void initializeCombatStatsPanel() {
        this.append("\nHit Points: " + selectedStatBlock.getHPString());
        this.append("\nArmour Class: " + selectedStatBlock.getArmour().toString());
        this.append("\nSpeeds: " + selectedStatBlock.getSpeeds().toString());
    }

    // MODIFIES: this
    // EFFECTS: adds the selected statblock's ability score set data to the text area
    private void initializeAbilityScoresPanel() {
        AbilityScoreSet abilityScoreSet = selectedStatBlock.getAbilityScores();
        this.append("\n---------------");
        this.append("\nSTR " + abilityScoreSet.getStrength()
                + " (" + abilityScoreSet.toModifier(AbilityScore.STRENGTH) + ")");
        this.append("\nDEX" + abilityScoreSet.getDexterity()
                + " (" + abilityScoreSet.toModifier(AbilityScore.DEXTERITY) + ")");
        this.append("\nCON" + abilityScoreSet.getConstitution()
                + " (" + abilityScoreSet.toModifier(AbilityScore.CONSTITUTION) + ")");
        this.append("\nINT" + abilityScoreSet.getIntelligence()
                + " (" + abilityScoreSet.toModifier(AbilityScore.INTELLIGENCE) + ")");
        this.append("\nWIS" + abilityScoreSet.getWisdom()
                + " (" + abilityScoreSet.toModifier(AbilityScore.WISDOM) + ")");
        this.append("\nCHA" + abilityScoreSet.getCharisma()
                + " (" + abilityScoreSet.toModifier(AbilityScore.CHARISMA) + ")");
        this.append("\n---------------");
    }

    // MODIFIES: this
    // EFFECTS: adds the selected statblock's optional fields data to the text area
    private void initializePeripheralFieldsPanel() {
        if (!selectedStatBlock.getSavingThrowProficienciesString().isEmpty()) {
            this.append("\nSaving Throws: " + selectedStatBlock.getSavingThrowProficienciesString());
        }
        if (!selectedStatBlock.getSkillProficienciesString().isEmpty()) {
            this.append("\nSkills: " + selectedStatBlock.getSkillProficienciesString());
        }
        if (!selectedStatBlock.getResistancesString().isEmpty()) {
            this.append("\nResistances: " + selectedStatBlock.getResistancesString());
        }
        if (!selectedStatBlock.getConditionImmunitiesString().isEmpty()) {
            this.append("\nImmunities: " + selectedStatBlock.getConditionImmunitiesString());
        }
        if (selectedStatBlock.getLanguages() != null) {
            this.append("\nLanguages: " + selectedStatBlock.getLanguages().toString());
        }
        if (selectedStatBlock.getProficiency() != 0) {
            this.append("\nProficiency bonus: " + selectedStatBlock.getProficiency());
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the selected statblock's abilities data to the text area
    private void initializeAbilitiesPanel() {
        if (selectedStatBlock.getAbilities() != null) {
            this.append("\n---------------");
            for (Ability a : selectedStatBlock.getAbilities()) {
                this.append("\n" + a.toString());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the selected statblock's rollable actions data to the text area
    private void initializeRollableActionsPanel() {
        this.append("\n\nActions");
        this.append("\n---------------");
        for (RollableAction a : selectedStatBlock.getRollableActions()) {
            this.append("\n" + a.toString());
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the selected statblock's legendary data to the text area
    private void initializeLegendaryPanel() {
        if (selectedStatBlock.getLegendaryMechanics() != null) {
            this.append("\n\nLegendary Mechancis");
            this.append("\n---------------");
            for (Ability a : selectedStatBlock.getLegendaryMechanics().getLegendaryActions()) {
                this.append("\n" + a.toString());
            }
        }
    }
}
