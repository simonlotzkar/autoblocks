package ui.panels;

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

    // images
    private static final Image PARCHMENT = Toolkit.getDefaultToolkit()
            .getImage("./data/images/parchment.jpg");

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
        selectedNPC = mainMenuPanel.getMainDisplayPanel().getSelectedNonPlayerCharacter();
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
        if (title.getGroup() != null) {
            this.append(title.getName() + " (CR " + selectedNPC.getChallengeRating()
                    + "), Group: " + title.getGroup());
            this.append("\n" + title.getSize() + " " + title.getType() + ", " + title.getAlignment());
            this.append("\n---------------");
        } else {
            this.append(title.getName() + " (CR " + selectedNPC.getChallengeRating() + ")");
            this.append("\n" + title.getSize() + " " + title.getType() + ", " + title.getAlignment());
            this.append("\n---------------");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the selected npc's combat stats data to the text area
    private void initializeCombatStatsPanel() {
        this.append("\nHit Points: " + selectedNPC.getHPString());
        this.append("\nArmour Class: " + selectedNPC.getArmour().toString());
        this.append("\nSpeeds: " + selectedNPC.getSpeeds().toString());
    }

    // MODIFIES: this
    // EFFECTS: adds the selected npc's ability score set data to the text area
    private void initializeAbilityScoreSetPanel() {
        AbilityScoreSet abilityScoreSet = selectedNPC.getAbilityScores();
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
