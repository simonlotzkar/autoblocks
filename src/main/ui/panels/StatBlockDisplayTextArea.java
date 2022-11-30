package ui.panels;

import model.statblockfields.*;
import model.statblockfields.Action;
import ui.panels.menus.MainMenuPanel;

import javax.swing.*;

// Represents...
public class StatBlockDisplayTextArea extends JTextArea {
    private final MainMenuPanel mainMenuPanel;
    private model.StatBlock selectedStatBlock;

    private static final String NONE_SELECTED = "No StatBlock Selected";

    // EFFECTS: constructs this text field
    public StatBlockDisplayTextArea(MainMenuPanel mainMenuPanel) {
        super(NONE_SELECTED);
        this.mainMenuPanel = mainMenuPanel;
        this.setVisible(true);
        this.setEditable(false);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);

        initializeAll();
    }

    // EFFECTS: ...
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
            initializeActionsPanel();
            initializeLegendaryPanel();
            this.revalidate();
            this.repaint();
        } else {
            this.setText(NONE_SELECTED);
        }
    }

    // EFFECTS: ...
    private void initializeTitlePanel() {
        Title title = selectedStatBlock.getTitle();
        this.append(title.getName() + " (CR " + selectedStatBlock.getChallengeRating() + ")");
        this.append("\n" + title.getSize() + " " + title.getType() + ", " + title.getAlignment());
        this.append("\n---------------");
    }

    // EFFECTS: ...
    private void initializeCombatStatsPanel() {
        this.append("\nHit Points: " + selectedStatBlock.getHPString());
        this.append("\nArmour Class: " + selectedStatBlock.getArmour().toString());
        this.append("\nSpeeds: " + selectedStatBlock.getSpeeds().toString());
    }

    // EFFECTS: ...
    private void initializeAbilityScoresPanel() {
        AbilityScores abilityScores = selectedStatBlock.getAbilityScores();
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

    // EFFECTS: ...
    private void initializeAbilitiesPanel() {
        if (selectedStatBlock.getAbilities() != null) {
            this.append("\n---------------");
            for (Ability a : selectedStatBlock.getAbilities()) {
                this.append("\n" + a.toString());
            }
        }
    }

    // EFFECTS: ...
    private void initializeActionsPanel() {
        this.append("\n\nActions");
        this.append("\n---------------");
        for (Action a : selectedStatBlock.getActions()) {
            this.append("\n" + a.toString());
        }
    }

    // EFFECTS: ...
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
