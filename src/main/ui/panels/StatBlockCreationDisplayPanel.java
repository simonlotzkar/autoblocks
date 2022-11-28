package ui.panels;

import ui.panels.menus.MainMenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Represents...
public class StatBlockCreationDisplayPanel extends DisplayPanel implements ActionListener {
    // textfields
    private final ArrayList<JTextField> requiredTextFieldsList = new ArrayList<>();
    private final ArrayList<JTextField> numberTextFieldList = new ArrayList<>();
    private final ArrayList<JTextField> wordTextFieldList = new ArrayList<>();
    private final JTextField nameTextField = new JTextField();
    private final JTextField xpTextField = new JTextField();

    private final JTextField hpAmountOfDiceTextField = new JTextField();
    private final JTextField hpDieSidesTextField = new JTextField();
    private final JTextField hpModifierTextField = new JTextField();

    private final JTextField armourTextField = new JTextField();
    private final JTextField armourNameTextField = new JTextField();
    private final JTextField magicArmourTextField = new JTextField();

    private final JTextField speedTextField = new JTextField();
    private final JTextField burrowTextField = new JTextField();
    private final JTextField swimTextField = new JTextField();
    private final JTextField flyTextField = new JTextField();
    private final JTextField climbTextField = new JTextField();

    private final JTextField strengthTextField = new JTextField();
    private final JTextField dexterityTextField = new JTextField();
    private final JTextField constitutionTextField = new JTextField();
    private final JTextField intelligenceTextField = new JTextField();
    private final JTextField wisdomTextField = new JTextField();
    private final JTextField charismaTextField = new JTextField();

    private final JTextField telepathyTextField = new JTextField();
    private final JTextField languageTextField = new JTextField();
    private final JTextField proficiencyTextField = new JTextField();

    private final JTextField abilityNameTextField = new JTextField();
    private final JTextField abilityDescriptionTextField = new JTextField();

    private final JTextField actionNameTextField = new JTextField();
    private final JTextField actionRangeTextField = new JTextField();
    private final JTextField actionHitAmountOfDiceTextField = new JTextField();
    private final JTextField actionHitDieSidesTextField = new JTextField();
    private final JTextField actionHitModifierTextField = new JTextField();
    private final JTextField actionDamageAmountOfDiceTextField = new JTextField();
    private final JTextField actionDamageDieSidesTextField = new JTextField();
    private final JTextField actionDamageModifierTextField = new JTextField();

    private final JTextField legendaryDescriptionTextField = new JTextField();
    private final JTextField legendaryActionNameTextField = new JTextField();
    private final JTextField legendaryActionDescriptionTextField = new JTextField();

    // combo boxes
    private final JComboBox<String> sizeComboBox = new JComboBox<>(SIZES);
    private final JComboBox<String> typeComboBox = new JComboBox<>(TYPES);
    private final JComboBox<String> alignmentComboBox = new JComboBox<>(ALIGNMENTS);

    private final JComboBox<String> acidResistanceComboBox = new JComboBox<>(RESISTANCE_TYPES);
    private final JComboBox<String> bludgeoningResistanceComboBox = new JComboBox<>(RESISTANCE_TYPES);
    private final JComboBox<String> coldResistanceComboBox = new JComboBox<>(RESISTANCE_TYPES);
    private final JComboBox<String> fireResistanceComboBox = new JComboBox<>(RESISTANCE_TYPES);
    private final JComboBox<String> forceResistanceComboBox = new JComboBox<>(RESISTANCE_TYPES);
    private final JComboBox<String> lightningResistanceComboBox = new JComboBox<>(RESISTANCE_TYPES);
    private final JComboBox<String> necroticResistanceComboBox = new JComboBox<>(RESISTANCE_TYPES);
    private final JComboBox<String> piercingResistanceComboBox = new JComboBox<>(RESISTANCE_TYPES);
    private final JComboBox<String> poisonResistanceComboBox = new JComboBox<>(RESISTANCE_TYPES);
    private final JComboBox<String> psychicResistanceComboBox = new JComboBox<>(RESISTANCE_TYPES);
    private final JComboBox<String> radiantResistanceComboBox = new JComboBox<>(RESISTANCE_TYPES);
    private final JComboBox<String> slashingResistanceComboBox = new JComboBox<>(RESISTANCE_TYPES);
    private final JComboBox<String> thunderResistanceComboBox = new JComboBox<>(RESISTANCE_TYPES);

    private final JComboBox<String> actionDescriptionComboBox = new JComboBox<>(ACTION_DESCRIPTIONS);
    private final JComboBox<String> actionDamageTypeComboBox = new JComboBox<>(DAMAGE_TYPES);

    // check boxes
    private final JCheckBox strengthSavingThrowProficiencyCheckBox = new JCheckBox();
    private final JCheckBox dexteritySavingThrowProficiencyCheckBox = new JCheckBox();
    private final JCheckBox constitutionSavingThrowProficiencyCheckBox = new JCheckBox();
    private final JCheckBox intelligenceSavingThrowProficiencyCheckBox = new JCheckBox();
    private final JCheckBox wisdomSavingThrowProficiencyCheckBox = new JCheckBox();
    private final JCheckBox charismaSavingThrowProficiencyCheckBox = new JCheckBox();

    private final JCheckBox acrobaticsProficiencyCheckBox = new JCheckBox();
    private final JCheckBox animalHandlingProficiencyCheckBox = new JCheckBox();
    private final JCheckBox arcanaProficiencyCheckBox = new JCheckBox();
    private final JCheckBox athleticsProficiencyCheckBox = new JCheckBox();
    private final JCheckBox deceptionProficiencyCheckBox = new JCheckBox();
    private final JCheckBox historyProficiencyCheckBox = new JCheckBox();
    private final JCheckBox insightProficiencyCheckBox = new JCheckBox();
    private final JCheckBox intimidationProficiencyCheckBox = new JCheckBox();
    private final JCheckBox investigationProficiencyCheckBox = new JCheckBox();
    private final JCheckBox medicineProficiencyCheckBox = new JCheckBox();
    private final JCheckBox natureProficiencyCheckBox = new JCheckBox();
    private final JCheckBox perceptionProficiencyCheckBox = new JCheckBox();
    private final JCheckBox performanceProficiencyCheckBox = new JCheckBox();
    private final JCheckBox persuasionProficiencyCheckBox = new JCheckBox();
    private final JCheckBox religionProficiencyCheckBox = new JCheckBox();
    private final JCheckBox sleightOfHandProficiencyCheckBox = new JCheckBox();
    private final JCheckBox stealthProficiencyCheckBox = new JCheckBox();
    private final JCheckBox survivalProficiencyCheckBox = new JCheckBox();

    private final JCheckBox blindedImmunityCheckBox = new JCheckBox();
    private final JCheckBox charmedImmunityCheckBox = new JCheckBox();
    private final JCheckBox deafenedImmunityCheckBox = new JCheckBox();
    private final JCheckBox frightenedImmunityCheckBox = new JCheckBox();
    private final JCheckBox grappledImmunityCheckBox = new JCheckBox();
    private final JCheckBox incapacitatedImmunityCheckBox = new JCheckBox();
    private final JCheckBox invisibleImmunityCheckBox = new JCheckBox();
    private final JCheckBox paralyzedImmunityCheckBox = new JCheckBox();
    private final JCheckBox petrifiedImmunityCheckBox = new JCheckBox();
    private final JCheckBox poisonedImmunityCheckBox = new JCheckBox();
    private final JCheckBox proneImmunityCheckBox = new JCheckBox();
    private final JCheckBox restrainedImmunityCheckBox = new JCheckBox();
    private final JCheckBox stunnedImmunityCheckBox = new JCheckBox();
    private final JCheckBox unconsciousImmunityCheckBox = new JCheckBox();
    private final JCheckBox exhaustionImmunityCheckBox = new JCheckBox();

    // panels
    private final JPanel titlePanel = new JPanel();
    private final JPanel nameCRPanel = new JPanel();
    private final JPanel sizeTypeAlignmentPanel = new JPanel();

    private final JPanel combatStatsPanel = new JPanel();
    private final JPanel hpPanel = new JPanel();
    private final JPanel armourPanel = new JPanel();
    private final JPanel speedsPanel = new JPanel();

    private final JPanel abilityScoresPanel = new JPanel();
    private final JPanel strengthPanel = new JPanel();
    private final JPanel dexterityPanel = new JPanel();
    private final JPanel constitutionPanel = new JPanel();
    private final JPanel intelligencePanel = new JPanel();
    private final JPanel wisdomPanel = new JPanel();
    private final JPanel charismaPanel = new JPanel();

    private final JPanel peripheralFieldsPanel = new JPanel();
    private final JPanel savingThrowsPanel = new JPanel();
    private final JPanel skillsPanel = new JPanel();
    private final JPanel resistancesPanel = new JPanel();
    private final JPanel immunitiesPanel = new JPanel();
    private final JPanel languageCreationPanel = new JPanel();
    private final JPanel proficiencyPanel = new JPanel();

    private final JPanel abilityCreationPanel = new JPanel();
    private final JPanel actionCreationPanel = new JPanel();
    private final JPanel hitDescriptionPanel = new JPanel();
    private final JPanel addDamageEntryPanel = new JPanel();
    private final JPanel legendaryCreationPanel = new JPanel();

    // buttons
    private final JButton addLanguageButton = new JButton("Add");
    private final JButton addAbilityButton = new JButton("Add");
    private final JButton addActionDamageButton = new JButton("Add Damage");
    private final JButton addActionButton = new JButton("Add Action");
    private final JButton addLegendaryActionButton = new JButton("Add");

    // lists
    private JList<String> languagesList = new JList<>();
    private JList<String> abilitiesList = new JList<>();
    private JList<String> actionsList = new JList<>();
    private JList<String> actionDamageList = new JList<>();
    private JList<String> legendaryActionsList = new JList<>();

    // scroll panes
    private final JScrollPane languagesScrollPane = new JScrollPane(languagesList);
    private final JScrollPane abilityDescriptionScrollPane = new JScrollPane(abilityDescriptionTextField);
    private final JScrollPane abilitiesScrollPane = new JScrollPane(abilitiesList);
    private final JScrollPane actionsScrollPane = new JScrollPane(actionsList);
    private final JScrollPane actionDamageScrollPane = new JScrollPane(actionDamageList);
    private final JScrollPane legendaryDescriptionScrollPane = new JScrollPane(legendaryDescriptionTextField);
    private final JScrollPane legendaryActionDescriptionScrollPane
            = new JScrollPane(legendaryActionDescriptionTextField);
    private final JScrollPane legendaryActionsScrollPane = new JScrollPane(legendaryActionsList);

    // constants
    private static final String SEPARATOR = "=====================================";
    private static final String TAB = "    ";
    private static final int FLOW_H_GAP = 5;
    private static final int FLOW_V_GAP = 5;

    private static final String[] SIZES = {"Small", "Medium", "Large", "Huge", "Gargantuan"};
    private static final String[] TYPES = {"Aberration", "Beast", "Celestial", "Construct", "Dragon",
            "Elemental", "Fey", "Fiend", "Giant", "Humanoid", "Monstrosity", "Ooze", "Plant", "Undead"};
    private static final String[] ALIGNMENTS = {"unaligned", "lawful good", "neutral good", "chaotic good",
            "neutral good", "neutral", "neutral evil", "chaotic good", "chaotic neutral", "chaotic evil"};
    private static final String[] RESISTANCE_TYPES = {"---", "resistant", "vulnerable", "immune"};
    private static final String[] ACTION_DESCRIPTIONS = {"Melee Weapon Attack", "Melee Spell Attack",
            "Ranged Weapon Attack", "Ranged Spell Attack", "Melee or Ranged Weapon Attack", "Action"};
    private static final String[] DAMAGE_TYPES = {"acid", "bludgeoning", "cold", "fire", "force", "lightning",
            "necrotic", "piercing", "poison", "psychic", "radiant", "slashing", "thunder"};

    // EFFECTS: constructs this display panel
    public StatBlockCreationDisplayPanel(MainMenuPanel mainMenuPanel) {
        super(null, mainMenuPanel); // sets the layout manager, mainmenu panel, size, and visibility
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension((int) (mainMenuPanel.getPreferredSize().width / 2.5), 1080));

        initializeAll();

        this.add(titlePanel);
        this.add(new JLabel(SEPARATOR));
        this.add(combatStatsPanel);
        this.add(new JLabel(SEPARATOR));
        this.add(abilityScoresPanel);
        this.add(new JLabel(SEPARATOR));
        this.add(peripheralFieldsPanel);
        this.add(new JLabel(SEPARATOR));
        this.add(abilityCreationPanel);
        this.add(abilitiesScrollPane);
        this.add(new JLabel(SEPARATOR));
        this.add(actionCreationPanel);
        this.add(actionsScrollPane);
        this.add(new JLabel(SEPARATOR));
        this.add(legendaryCreationPanel);
        this.add(legendaryActionsScrollPane);
    }

    // EFFECTS: ...
    private void initializeAll() {
        initializeTitlePanel();
        initializeCombatStatsPanel();
        initializeAbilityScoresPanel();
        initializePeripheralFieldsPanel();
        initializeAbilityCreationPanel();
        initializeActionCreationPanel();
        initializeLegendaryCreationPanel();
        formatTextFields();
        formatScrollPanes();
        this.revalidate();
        this.repaint();
    }

    // EFFECTS: ...
    private void formatTextFields() {
        for (JTextField tf : numberTextFieldList) {
            tf.setPreferredSize(new Dimension(50, 25));
        }
        for (JTextField tf : wordTextFieldList) {
            tf.setPreferredSize(new Dimension(150, 25));
        }
        for (JTextField tf : requiredTextFieldsList) {
            tf.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        }
    }

    // EFFECTS: ...
    private void formatScrollPanes() {
        ArrayList<JScrollPane> scrollPanesList = new ArrayList<>();
        scrollPanesList.add(languagesScrollPane);
        scrollPanesList.add(abilityDescriptionScrollPane);
        scrollPanesList.add(abilitiesScrollPane);
        scrollPanesList.add(actionsScrollPane);
        scrollPanesList.add(actionDamageScrollPane);
        scrollPanesList.add(legendaryDescriptionScrollPane);
        scrollPanesList.add(legendaryActionDescriptionScrollPane);
        scrollPanesList.add(legendaryActionsScrollPane);

        for (JScrollPane sp : scrollPanesList) {
            sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            //sp.setPreferredSize(new Dimension(this.getPreferredSize().width, 50));
        }
    }

    // EFFECTS: ...
    private void initializeTitlePanel() {
        wordTextFieldList.add(nameTextField);
        numberTextFieldList.add(xpTextField);

        requiredTextFieldsList.add(nameTextField);
        requiredTextFieldsList.add(xpTextField);

        initializeNameCRPanel();
        initializeSizeTypeAlignmentPanel();

        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.add(nameCRPanel);
        titlePanel.add(sizeTypeAlignmentPanel);
    }

    // EFFECTS: ...
    private void initializeNameCRPanel() {
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        namePanel.add(new JLabel("Name:"));
        namePanel.add(nameTextField);

        JPanel xpPanel = new JPanel();
        xpPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        xpPanel.add(new JLabel("XP:"));
        xpPanel.add(xpTextField);

        nameCRPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        nameCRPanel.add(namePanel);
        nameCRPanel.add(xpPanel);
    }

    // EFFECTS: ...
    private void initializeSizeTypeAlignmentPanel() {
        JPanel sizePanel = new JPanel();
        sizePanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        sizePanel.add(new JLabel("Size:"));
        sizePanel.add(sizeComboBox);

        JPanel typePanel = new JPanel();
        typePanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        typePanel.add(new JLabel("Type:"));
        typePanel.add(typeComboBox);

        JPanel alignmentPanel = new JPanel();
        alignmentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        alignmentPanel.add(new JLabel("Alignment:"));
        alignmentPanel.add(alignmentComboBox);

        sizeTypeAlignmentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        sizeTypeAlignmentPanel.add(sizePanel);
        sizeTypeAlignmentPanel.add(typePanel);
        sizeTypeAlignmentPanel.add(alignmentPanel);
    }


    // EFFECTS: ...
    private void initializeCombatStatsPanel() {
        initializeHPPanel();
        initializeArmourPanel();
        initializeSpeedsPanel();

        combatStatsPanel.setLayout(new BoxLayout(combatStatsPanel, BoxLayout.Y_AXIS));
        combatStatsPanel.add(hpPanel);
        combatStatsPanel.add(armourPanel);
        combatStatsPanel.add(speedsPanel);
    }

    // EFFECTS: ...
    private void initializeHPPanel() {
        initializeProficiencyPanel();

        numberTextFieldList.add(hpAmountOfDiceTextField);
        numberTextFieldList.add(hpDieSidesTextField);
        numberTextFieldList.add(hpModifierTextField);
        requiredTextFieldsList.add(hpAmountOfDiceTextField);
        requiredTextFieldsList.add(hpDieSidesTextField);
        requiredTextFieldsList.add(hpModifierTextField);

        hpPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        hpPanel.add(new JLabel("Hit Point Formula:" + TAB));
        hpPanel.add(hpAmountOfDiceTextField);
        hpPanel.add(new JLabel("d"));
        hpPanel.add(hpDieSidesTextField);
        hpPanel.add(new JLabel("+"));
        hpPanel.add(hpModifierTextField);
        hpPanel.add(proficiencyPanel);
    }

    // EFFECTS: ...
    private void initializeProficiencyPanel() {
        numberTextFieldList.add(proficiencyTextField);
        requiredTextFieldsList.add(proficiencyTextField);

        proficiencyPanel.add(new JLabel("Proficiency Bonus:"));
        proficiencyPanel.add(proficiencyTextField);
    }

    // EFFECTS: ...
    private void initializeArmourPanel() {
        numberTextFieldList.add(armourTextField);
        wordTextFieldList.add(armourNameTextField);
        numberTextFieldList.add(magicArmourTextField);
        requiredTextFieldsList.add(armourTextField);

        armourPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        armourPanel.add(new JLabel("Armour Class:"));
        armourPanel.add(armourTextField);
        armourPanel.add(new JLabel("Armour Name:"));
        armourPanel.add(armourNameTextField);
        armourPanel.add(new JLabel("Magic Armour Class:"));
        armourPanel.add(magicArmourTextField);
    }

    // EFFECTS: ...
    private void initializeSpeedsPanel() {
        numberTextFieldList.add(speedTextField);
        numberTextFieldList.add(burrowTextField);
        numberTextFieldList.add(swimTextField);
        numberTextFieldList.add(flyTextField);
        numberTextFieldList.add(climbTextField);
        requiredTextFieldsList.add(speedTextField);

        speedsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        speedsPanel.add(new JLabel("Speed:"));
        speedsPanel.add(speedTextField);
        speedsPanel.add(new JLabel("Fly:"));
        speedsPanel.add(flyTextField);
        speedsPanel.add(new JLabel("Swim:"));
        speedsPanel.add(swimTextField);
        speedsPanel.add(new JLabel("Burrow:"));
        speedsPanel.add(burrowTextField);
        speedsPanel.add(new JLabel("Climb:"));
        speedsPanel.add(climbTextField);
    }

    // EFFECTS: ...
    private void initializeAbilityScoresPanel() {
        initializeStrengthPanel();
        initializeDexterityPanel();
        initializeConstitutionPanel();
        initializeIntelligencePanel();
        initializeWisdomPanel();
        initializeCharismaPanel();

        abilityScoresPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        abilityScoresPanel.add(new JLabel("Ability Scores:" + TAB));
        abilityScoresPanel.add(strengthPanel);
        abilityScoresPanel.add(dexterityPanel);
        abilityScoresPanel.add(constitutionPanel);
        abilityScoresPanel.add(intelligencePanel);
        abilityScoresPanel.add(wisdomPanel);
        abilityScoresPanel.add(charismaPanel);
    }

    // EFFECTS: ...
    private void initializeStrengthPanel() {
        numberTextFieldList.add(strengthTextField);
        requiredTextFieldsList.add(strengthTextField);
        strengthPanel.add(new JLabel("STR:"));
        strengthPanel.add(strengthTextField);
    }

    // EFFECTS: ...
    private void initializeDexterityPanel() {
        numberTextFieldList.add(dexterityTextField);
        requiredTextFieldsList.add(dexterityTextField);
        dexterityPanel.add(new JLabel("DEX:"));
        dexterityPanel.add(dexterityTextField);
    }

    // EFFECTS: ...
    private void initializeConstitutionPanel() {
        numberTextFieldList.add(constitutionTextField);
        requiredTextFieldsList.add(constitutionTextField);
        constitutionPanel.add(new JLabel("CON:"));
        constitutionPanel.add(constitutionTextField);
    }

    // EFFECTS: ...
    private void initializeIntelligencePanel() {
        numberTextFieldList.add(intelligenceTextField);
        requiredTextFieldsList.add(intelligenceTextField);
        intelligencePanel.add(new JLabel("INT:"));
        intelligencePanel.add(intelligenceTextField);
    }

    // EFFECTS: ...
    private void initializeWisdomPanel() {
        numberTextFieldList.add(wisdomTextField);
        requiredTextFieldsList.add(wisdomTextField);
        wisdomPanel.add(new JLabel("WIS:"));
        wisdomPanel.add(wisdomTextField);
    }

    // EFFECTS: ...
    private void initializeCharismaPanel() {
        numberTextFieldList.add(charismaTextField);
        requiredTextFieldsList.add(charismaTextField);
        charismaPanel.add(new JLabel("CHA:"));
        charismaPanel.add(charismaTextField);
    }

    // EFFECTS: ...
    private void initializePeripheralFieldsPanel() {
        initializeSavingThrowsPanel();
        initializeSkillsPanel();
        initializeResistancesPanel();
        initializeImmunitiesPanel();
        initializeLanguageCreationPanel();

        peripheralFieldsPanel.setLayout(new BoxLayout(peripheralFieldsPanel, BoxLayout.Y_AXIS));
        peripheralFieldsPanel.add(savingThrowsPanel);
        peripheralFieldsPanel.add(skillsPanel);
        peripheralFieldsPanel.add(resistancesPanel);
        peripheralFieldsPanel.add(immunitiesPanel);
        peripheralFieldsPanel.add(languageCreationPanel);
        peripheralFieldsPanel.add(languagesScrollPane);
    }

    // EFFECTS: ...
    private void initializeSavingThrowsPanel() {
        savingThrowsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        savingThrowsPanel.add(new JLabel("Saving Throw Proficiencies:" + TAB));
        savingThrowsPanel.add(new JLabel("STR:"));
        savingThrowsPanel.add(strengthSavingThrowProficiencyCheckBox);
        savingThrowsPanel.add(new JLabel("DEX:"));
        savingThrowsPanel.add(dexteritySavingThrowProficiencyCheckBox);
        savingThrowsPanel.add(new JLabel("CON:"));
        savingThrowsPanel.add(constitutionSavingThrowProficiencyCheckBox);
        savingThrowsPanel.add(new JLabel("INT:"));
        savingThrowsPanel.add(intelligenceSavingThrowProficiencyCheckBox);
        savingThrowsPanel.add(new JLabel("WIS:"));
        savingThrowsPanel.add(wisdomSavingThrowProficiencyCheckBox);
        savingThrowsPanel.add(new JLabel("CHA:"));
        savingThrowsPanel.add(charismaSavingThrowProficiencyCheckBox);
    }

    // EFFECTS: ...
    private void initializeSkillsPanel() {
        skillsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        skillsPanel.add(new JLabel("Skill Proficiencies:" + TAB));
        skillsPanel.add(new JLabel("Acrobatics (Dex):"));
        skillsPanel.add(acrobaticsProficiencyCheckBox);
        skillsPanel.add(new JLabel("Animal Handling (Wis):"));
        skillsPanel.add(animalHandlingProficiencyCheckBox);
        skillsPanel.add(new JLabel("Arcana (Int):"));
        skillsPanel.add(arcanaProficiencyCheckBox);
        skillsPanel.add(new JLabel("Athletics (Str):"));
        skillsPanel.add(athleticsProficiencyCheckBox);
        skillsPanel.add(new JLabel("Deception (Cha):"));
        skillsPanel.add(deceptionProficiencyCheckBox);
        skillsPanel.add(new JLabel("History (Int):"));
        skillsPanel.add(historyProficiencyCheckBox);
        skillsPanel.add(new JLabel("Insight (Dex):"));
        skillsPanel.add(insightProficiencyCheckBox);
        skillsPanel.add(new JLabel("Intimidation (Cha):"));
        skillsPanel.add(intimidationProficiencyCheckBox);
        skillsPanel.add(new JLabel("Investigation (Int):"));
        skillsPanel.add(investigationProficiencyCheckBox);
        initializeSkillsPanelPartTwo();
    }

    // EFFECTS: ...
    private void initializeSkillsPanelPartTwo() {
        skillsPanel.add(new JLabel("Medicine (Wis):"));
        skillsPanel.add(medicineProficiencyCheckBox);
        skillsPanel.add(new JLabel("Nature (Int):"));
        skillsPanel.add(natureProficiencyCheckBox);
        skillsPanel.add(new JLabel("Perception (Wis):"));
        skillsPanel.add(perceptionProficiencyCheckBox);
        skillsPanel.add(new JLabel("Performance (Cha):"));
        skillsPanel.add(performanceProficiencyCheckBox);
        skillsPanel.add(new JLabel("Persuasion (Cha):"));
        skillsPanel.add(persuasionProficiencyCheckBox);
        skillsPanel.add(new JLabel("Religion (Int):"));
        skillsPanel.add(religionProficiencyCheckBox);
        skillsPanel.add(new JLabel("Sleight of Hand (Dex):"));
        skillsPanel.add(sleightOfHandProficiencyCheckBox);
        skillsPanel.add(new JLabel("Stealth (Dex):"));
        skillsPanel.add(stealthProficiencyCheckBox);
        skillsPanel.add(new JLabel("Survival (Wis):"));
        skillsPanel.add(survivalProficiencyCheckBox);
    }

    // EFFECTS: ...
    private void initializeResistancesPanel() {
        resistancesPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        resistancesPanel.add(new JLabel("Resistances:" + TAB));
        resistancesPanel.add(new JLabel("Acid:"));
        resistancesPanel.add(acidResistanceComboBox);
        resistancesPanel.add(new JLabel("Bludgeoning:"));
        resistancesPanel.add(bludgeoningResistanceComboBox);
        resistancesPanel.add(new JLabel("Cold:"));
        resistancesPanel.add(coldResistanceComboBox);
        resistancesPanel.add(new JLabel("Fire:"));
        resistancesPanel.add(fireResistanceComboBox);
        resistancesPanel.add(new JLabel("Force:"));
        resistancesPanel.add(forceResistanceComboBox);
        resistancesPanel.add(new JLabel("Lightning:"));
        resistancesPanel.add(lightningResistanceComboBox);
        initializeResistancesPanelPartTwo();
    }

    // EFFECTS: ...
    private void initializeResistancesPanelPartTwo() {
        resistancesPanel.add(new JLabel("Necrotic:"));
        resistancesPanel.add(necroticResistanceComboBox);
        resistancesPanel.add(new JLabel("Piercing:"));
        resistancesPanel.add(piercingResistanceComboBox);
        resistancesPanel.add(new JLabel("Poison:"));
        resistancesPanel.add(poisonResistanceComboBox);
        resistancesPanel.add(new JLabel("Psychic:"));
        resistancesPanel.add(psychicResistanceComboBox);
        resistancesPanel.add(new JLabel("Radiant:"));
        resistancesPanel.add(radiantResistanceComboBox);
        resistancesPanel.add(new JLabel("Slashing:"));
        resistancesPanel.add(slashingResistanceComboBox);
        resistancesPanel.add(new JLabel("Thunder:"));
        resistancesPanel.add(thunderResistanceComboBox);
    }

    // EFFECTS: ...
    private void initializeImmunitiesPanel() {
        immunitiesPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        immunitiesPanel.add(new JLabel("Condition Immunities:" + TAB));
        immunitiesPanel.add(new JLabel("Blinded:"));
        immunitiesPanel.add(blindedImmunityCheckBox);
        immunitiesPanel.add(new JLabel("Charmed:"));
        immunitiesPanel.add(charmedImmunityCheckBox);
        immunitiesPanel.add(new JLabel("Deafened:"));
        immunitiesPanel.add(deafenedImmunityCheckBox);
        immunitiesPanel.add(new JLabel("Frightened:"));
        immunitiesPanel.add(frightenedImmunityCheckBox);
        immunitiesPanel.add(new JLabel("Grappled:"));
        immunitiesPanel.add(grappledImmunityCheckBox);
        immunitiesPanel.add(new JLabel("Incapacitated:"));
        immunitiesPanel.add(incapacitatedImmunityCheckBox);
        immunitiesPanel.add(new JLabel("Invisible:"));
        immunitiesPanel.add(invisibleImmunityCheckBox);
        initializeImmunitiesPanelPartTwo();
    }

    // EFFECTS: ...
    private void initializeImmunitiesPanelPartTwo() {
        immunitiesPanel.add(new JLabel("Paralyzed:"));
        immunitiesPanel.add(paralyzedImmunityCheckBox);
        immunitiesPanel.add(new JLabel("Petrified:"));
        immunitiesPanel.add(petrifiedImmunityCheckBox);
        immunitiesPanel.add(new JLabel("Poisoned:"));
        immunitiesPanel.add(poisonedImmunityCheckBox);
        immunitiesPanel.add(new JLabel("Prone:"));
        immunitiesPanel.add(proneImmunityCheckBox);
        immunitiesPanel.add(new JLabel("Restrained:"));
        immunitiesPanel.add(restrainedImmunityCheckBox);
        immunitiesPanel.add(new JLabel("Stunned:"));
        immunitiesPanel.add(stunnedImmunityCheckBox);
        immunitiesPanel.add(new JLabel("Unconscious:"));
        immunitiesPanel.add(unconsciousImmunityCheckBox);
        immunitiesPanel.add(new JLabel("Exhaustion:"));
        immunitiesPanel.add(exhaustionImmunityCheckBox);
    }

    // EFFECTS: ...
    private void initializeLanguageCreationPanel() {
        numberTextFieldList.add(telepathyTextField);
        wordTextFieldList.add(languageTextField);

        languageCreationPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        languageCreationPanel.add(new JLabel("Language:"));
        languageCreationPanel.add(languageTextField);
        languageCreationPanel.add(addLanguageButton);
        languageCreationPanel.add(new JLabel("Telepathy Range:"));
        languageCreationPanel.add(telepathyTextField);
    }

    // EFFECTS: ...
    private void initializeAbilityCreationPanel() {
        wordTextFieldList.add(abilityNameTextField);

        JPanel setNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        setNamePanel.add(new JLabel("Ability Name:"));
        setNamePanel.add(abilityNameTextField);
        setNamePanel.add(new JLabel("Ability Description:" + TAB));

        abilityCreationPanel.setLayout(new BoxLayout(abilityCreationPanel, BoxLayout.Y_AXIS));
        abilityCreationPanel.add(setNamePanel);
        abilityCreationPanel.add(abilityDescriptionScrollPane);
        abilityCreationPanel.add(addAbilityButton);
        abilityCreationPanel.add(new JLabel("Abilities:"));
        abilityCreationPanel.add(abilitiesScrollPane);
    }

    // EFFECTS: ...
    private void initializeActionCreationPanel() {
        wordTextFieldList.add(actionNameTextField);
        wordTextFieldList.add(actionRangeTextField);

        JPanel nameRangePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        nameRangePanel.add(new JLabel("Name:"));
        nameRangePanel.add(actionNameTextField);
        nameRangePanel.add(new JLabel("Range:"));
        nameRangePanel.add(actionRangeTextField);
        nameRangePanel.add(addActionButton);

        initializeActionHitDescriptionPanel();
        initializeActionAddDamageEntryPanel();

        actionCreationPanel.setLayout(new BoxLayout(actionCreationPanel, BoxLayout.Y_AXIS));
        actionCreationPanel.add(nameRangePanel);
        actionCreationPanel.add(hitDescriptionPanel);
        actionCreationPanel.add(addDamageEntryPanel);
        actionCreationPanel.add(new JLabel("Damage Rolls for this Action:"));
        actionCreationPanel.add(actionDamageScrollPane);
        actionCreationPanel.add(new JLabel("Actions:"));
    }

    // EFFECTS: ...
    private void initializeActionHitDescriptionPanel() {
        numberTextFieldList.add(actionHitAmountOfDiceTextField);
        numberTextFieldList.add(actionHitDieSidesTextField);
        numberTextFieldList.add(actionHitModifierTextField);

        hitDescriptionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        hitDescriptionPanel.add(new JLabel("Hit:"));
        hitDescriptionPanel.add(actionHitAmountOfDiceTextField);
        hitDescriptionPanel.add(new JLabel("d"));
        hitDescriptionPanel.add(actionHitDieSidesTextField);
        hitDescriptionPanel.add(new JLabel("+"));
        hitDescriptionPanel.add(actionHitModifierTextField);
        hitDescriptionPanel.add(new JLabel("Description:"));
        hitDescriptionPanel.add(actionDescriptionComboBox);
    }

    // EFFECTS: ...
    private void initializeActionAddDamageEntryPanel() {
        numberTextFieldList.add(actionDamageAmountOfDiceTextField);
        numberTextFieldList.add(actionDamageDieSidesTextField);
        numberTextFieldList.add(actionDamageModifierTextField);

        addDamageEntryPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        addDamageEntryPanel.add(new JLabel("Damage:"));
        addDamageEntryPanel.add(actionDamageAmountOfDiceTextField);
        addDamageEntryPanel.add(new JLabel("d"));
        addDamageEntryPanel.add(actionDamageDieSidesTextField);
        addDamageEntryPanel.add(new JLabel("+"));
        addDamageEntryPanel.add(actionDamageModifierTextField);
        addDamageEntryPanel.add(new JLabel("Type:"));
        addDamageEntryPanel.add(actionDamageTypeComboBox);
        addDamageEntryPanel.add(addActionDamageButton);
    }

    // EFFECTS: ...
    private void initializeLegendaryCreationPanel() {
        wordTextFieldList.add(legendaryActionNameTextField);
        wordTextFieldList.add(legendaryActionDescriptionTextField);

        JPanel legendaryActionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        legendaryActionPanel.add(new JLabel("Legendary Action Name:"));
        legendaryActionPanel.add(legendaryActionNameTextField);
        legendaryActionPanel.add(new JLabel("Legendary Action Description:"));

        legendaryCreationPanel.setLayout(new BoxLayout(legendaryCreationPanel, BoxLayout.Y_AXIS));
        legendaryCreationPanel.add(new JLabel("Legendary Description:"));
        legendaryCreationPanel.add(legendaryDescriptionScrollPane);
        legendaryCreationPanel.add(legendaryActionPanel);
        legendaryCreationPanel.add(legendaryActionDescriptionScrollPane);
        legendaryCreationPanel.add(addLegendaryActionButton);
        legendaryCreationPanel.add(new JLabel("Legendary Actions:"));
        legendaryCreationPanel.add(legendaryActionsScrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
