package ui.textareas;

import enums.AbilityScore;
import model.StatBlock;
import model.statblockfields.*;
import model.statblockfields.RollableAction;

import javax.swing.*;

// Represents a text area that displays a text representation of the selected statblock or lack thereof
public class StatBlockDisplayTextArea extends JTextArea {
    private StatBlock selectedStatBlock;
    private static final String NONE_SELECTED = "No StatBlock Selected";

    // MODIFIES: this
    // EFFECTS: constructs this text area
    public StatBlockDisplayTextArea() {
        super(NONE_SELECTED);
        setVisible(true);
        setEditable(false);
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(false);

        populateTextArea();
    }

    // MODIFIES: this
    // EFFECTS: adds the selected statblock's stats to the text area,
    //          or if none is selected displays that none is selected
    public void populateTextArea() {
        if (selectedStatBlock != null) {
            this.setText("");
            populateTitlePanel();
            populateCombatStatsPanel();
            populateAbilityScoresPanel();
            populatePeripheralFieldsPanel();
            populateAbilitiesPanel();
            populateRollableActionsPanel();
            populateLegendaryPanel();
        } else {
            this.setText(NONE_SELECTED);
        }
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: adds the selected statblock's title data to the text area
    private void populateTitlePanel() {
        Title title = selectedStatBlock.getTitle();
        this.append(title.getName() + " (CR " + selectedStatBlock.getChallengeRating() + ")");
        this.append("\n" + title.getSize() + " " + title.getType() + ", " + title.getAlignment());
        this.append("\n---------------");
    }

    // MODIFIES: this
    // EFFECTS: adds the selected statblock's combat stats data to the text area
    private void populateCombatStatsPanel() {
        this.append("\nHit Points: " + selectedStatBlock.getHPString());
        this.append("\nArmour Class: " + selectedStatBlock.getArmour().toString());
        this.append("\nSpeeds: " + selectedStatBlock.getSpeeds().toString());
    }

    // MODIFIES: this
    // EFFECTS: adds the selected statblock's ability score set data to the text area
    private void populateAbilityScoresPanel() {
        AbilityScoreSet abilityScoreSet = selectedStatBlock.getAbilityScores();
        this.append("\n---------------");
        this.append("\nSTR " + abilityScoreSet.getStrength()
                + " (" + integerToDescriptiveString(abilityScoreSet.toModifier(AbilityScore.STRENGTH)) + ")");
        this.append("\nDEX " + abilityScoreSet.getDexterity()
                + " (" + integerToDescriptiveString(abilityScoreSet.toModifier(AbilityScore.DEXTERITY)) + ")");
        this.append("\nCON " + abilityScoreSet.getConstitution()
                + " (" + integerToDescriptiveString(abilityScoreSet.toModifier(AbilityScore.CONSTITUTION)) + ")");
        this.append("\nINT " + abilityScoreSet.getIntelligence()
                + " (" + integerToDescriptiveString(abilityScoreSet.toModifier(AbilityScore.INTELLIGENCE)) + ")");
        this.append("\nWIS " + abilityScoreSet.getWisdom()
                + " (" + integerToDescriptiveString(abilityScoreSet.toModifier(AbilityScore.WISDOM)) + ")");
        this.append("\nCHA " + abilityScoreSet.getCharisma()
                + " (" + integerToDescriptiveString(abilityScoreSet.toModifier(AbilityScore.CHARISMA)) + ")");
        this.append("\n---------------");
    }

    // EFFECTS: adds a positive sign to the given number if it's positive, then returns it as a string
    private String integerToDescriptiveString(int number) {
        if (number < 0) {
            return String.valueOf(number);
        } else {
            return "+" + number;
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the selected statblock's optional fields data to the text area
    private void populatePeripheralFieldsPanel() {
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
    private void populateAbilitiesPanel() {
        if (selectedStatBlock.getAbilities() != null) {
            this.append("\n---------------");
            for (Ability a : selectedStatBlock.getAbilities()) {
                this.append("\n" + a.toString());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the selected statblock's rollable actions data to the text area
    private void populateRollableActionsPanel() {
        this.append("\n\nActions");
        this.append("\n---------------");
        for (RollableAction a : selectedStatBlock.getRollableActions()) {
            this.append("\n" + a.toString());
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the selected statblock's legendary data to the text area
    private void populateLegendaryPanel() {
        if (selectedStatBlock.getLegendaryMechanics() != null) {
            this.append("\n\nLegendary Mechancis");
            this.append("\n---------------");
            for (Ability a : selectedStatBlock.getLegendaryMechanics().getLegendaryActions()) {
                this.append("\n" + a.toString());
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    // EFFECTS: returns the selected statblock
    public StatBlock getSelectedStatBlock() {
        return selectedStatBlock;
    }

    // MODIFIES: this
    // EFFECTS: sets the selected statblock to the given statblock
    public void setSelectedStatBlock(StatBlock selectedStatBlock) {
        this.selectedStatBlock = selectedStatBlock;
    }
}
