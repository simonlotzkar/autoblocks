package ui.textareas;

import enums.AbilityScore;
import model.NPC;
import model.statblockfields.Ability;
import model.statblockfields.AbilityScoreSet;
import model.statblockfields.Title;
import ui.panels.menus.MainMenuPanel;

import javax.swing.*;
import java.awt.*;

// Represents a text area that displays a text representation of the selected npc
public class NonPlayerCharacterDisplayTextArea extends JTextArea {
    private final MainMenuPanel mainMenuPanel;
    private NPC selectedNPC;

    // MODIFIES: this
    // EFFECTS: constructs this text area
    public NonPlayerCharacterDisplayTextArea(MainMenuPanel mainMenuPanel) {
        super();
        this.mainMenuPanel = mainMenuPanel;
        setVisible(true);
        setEditable(false);
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(false);
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: selects npc and adds all of the selected npc's data to the text area
    public void initializeAll() {
        selectedNPC = mainMenuPanel.getMainDisplayPanel().getEncounterScrollPane().getSelectedNPC();
        setText("");

        initializeTitlePanel();
        initializeCombatStatsPanel();
        initializeAbilityScoreSetPanel();
        initializePeripheralFieldsPanel();
        initializeAbilitiesPanel();
        initializeLegendaryPanel();

        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: adds the selected npc's title data to the text area
    private void initializeTitlePanel() {
        Title title = selectedNPC.getTitle();
        append(title.getName() + " (CR " + selectedNPC.getChallengeRating() + ")");
        append("\n" + title.getSize() + " " + title.getType() + ", " + title.getAlignment());
        append("\n---------------");
    }

    // MODIFIES: this
    // EFFECTS: adds the selected npc's combat stats data to the text area
    private void initializeCombatStatsPanel() {
        append("\nHit Points: " + selectedNPC.getHPString());
        append("\nArmour Class: " + selectedNPC.getArmour().toString());
        append("\nSpeeds: " + selectedNPC.getSpeeds().toString());
    }

    // MODIFIES: this
    // EFFECTS: adds the selected npc's ability score set data to the text area
    private void initializeAbilityScoreSetPanel() {
        AbilityScoreSet abilityScoreSet = selectedNPC.getAbilityScores();
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
    // EFFECTS: adds the selected npc's optional fields data to the text area
    private void initializePeripheralFieldsPanel() {
        if (!selectedNPC.getSavingThrowProficienciesString().isEmpty()) {
            this.append("\nSaving Throws: " + selectedNPC.getSavingThrowProficienciesString());
        }
        if (!selectedNPC.getSkillProficienciesString().isEmpty()) {
            this.append("\nSkills: " + selectedNPC.getSkillProficienciesString());
        }
        if (!selectedNPC.getResistancesString().isEmpty()) {
            this.append("\nResistances: " + selectedNPC.getResistancesString());
        }
        if (!selectedNPC.getConditionImmunitiesString().isEmpty()) {
            this.append("\nImmunities: " + selectedNPC.getConditionImmunitiesString());
        }
        if (selectedNPC.getLanguages() != null) {
            this.append("\nLanguages: " + selectedNPC.getLanguages().toString());
        }
        if (selectedNPC.getProficiency() != 0) {
            this.append("\nProficiency bonus: " + selectedNPC.getProficiency());
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the selected npc's abilities data to the text area
    private void initializeAbilitiesPanel() {
        if (selectedNPC.getAbilities() != null) {
            this.append("\n---------------");
            for (Ability a : selectedNPC.getAbilities()) {
                this.append("\n" + a.toString());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the selected npc's legendary data to the text area
    private void initializeLegendaryPanel() {
        if (selectedNPC.getLegendaryMechanics() != null) {
            this.append("\n\nLegendary Mechancis");
            this.append("\n---------------");
            for (Ability a : selectedNPC.getLegendaryMechanics().getLegendaryActions()) {
                this.append("\n" + a.toString());
            }
        }
    }
}
