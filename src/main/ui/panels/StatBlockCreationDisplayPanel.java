package ui.panels;

import enums.*;
import model.RollFormula;
import model.StatBlock;
import model.statblockfields.*;
import exceptions.IncompleteFieldException;
import ui.panels.menus.MainMenuPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

// Represents...
public class StatBlockCreationDisplayPanel extends DisplayPanel implements ActionListener, ListSelectionListener {
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

    private final JTextField passivePerceptionTextField = new JTextField();
    private final JTextField blindSightTextField = new JTextField();
    private final JTextField tremorSenseTextField = new JTextField();
    private final JTextField trueSightTextField = new JTextField();
    private final JTextField darkVisionTextField = new JTextField();

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
    private final JTextField actionLongRangeTextField = new JTextField();
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

    private final ArrayList<JComboBox<String>> resistanceComboBoxList = new ArrayList<>();
    private final JComboBox<String> acidResistanceComboBox = new JComboBox<>(RESISTANCE_OPTIONS);
    private final JComboBox<String> bludgeoningResistanceComboBox = new JComboBox<>(RESISTANCE_OPTIONS);
    private final JComboBox<String> coldResistanceComboBox = new JComboBox<>(RESISTANCE_OPTIONS);
    private final JComboBox<String> fireResistanceComboBox = new JComboBox<>(RESISTANCE_OPTIONS);
    private final JComboBox<String> forceResistanceComboBox = new JComboBox<>(RESISTANCE_OPTIONS);
    private final JComboBox<String> lightningResistanceComboBox = new JComboBox<>(RESISTANCE_OPTIONS);
    private final JComboBox<String> necroticResistanceComboBox = new JComboBox<>(RESISTANCE_OPTIONS);
    private final JComboBox<String> piercingResistanceComboBox = new JComboBox<>(RESISTANCE_OPTIONS);
    private final JComboBox<String> poisonResistanceComboBox = new JComboBox<>(RESISTANCE_OPTIONS);
    private final JComboBox<String> psychicResistanceComboBox = new JComboBox<>(RESISTANCE_OPTIONS);
    private final JComboBox<String> radiantResistanceComboBox = new JComboBox<>(RESISTANCE_OPTIONS);
    private final JComboBox<String> slashingResistanceComboBox = new JComboBox<>(RESISTANCE_OPTIONS);
    private final JComboBox<String> thunderResistanceComboBox = new JComboBox<>(RESISTANCE_OPTIONS);

    private final JComboBox<String> actionDescriptionComboBox = new JComboBox<>(ACTION_DESCRIPTIONS);
    private final JComboBox<String> actionDamageTypeComboBox = new JComboBox<>(DamageType
            .getStringArrayList().toArray(new String[0]));

    // check boxes
    private final ArrayList<JCheckBox> savingThrowCheckBoxList = new ArrayList<>();
    private final JCheckBox strengthSavingThrowProficiencyCheckBox = new JCheckBox("strength");
    private final JCheckBox dexteritySavingThrowProficiencyCheckBox = new JCheckBox("dexterity");
    private final JCheckBox constitutionSavingThrowProficiencyCheckBox = new JCheckBox("constitution");
    private final JCheckBox intelligenceSavingThrowProficiencyCheckBox = new JCheckBox("intelligence");
    private final JCheckBox wisdomSavingThrowProficiencyCheckBox = new JCheckBox("wisdom");
    private final JCheckBox charismaSavingThrowProficiencyCheckBox = new JCheckBox("charisma");

    private final ArrayList<JCheckBox> skillCheckBoxList = new ArrayList<>();
    private final JCheckBox acrobaticsProficiencyCheckBox = new JCheckBox("acrobatics");
    private final JCheckBox animalHandlingProficiencyCheckBox = new JCheckBox("animalHandling");
    private final JCheckBox arcanaProficiencyCheckBox = new JCheckBox("arcana");
    private final JCheckBox athleticsProficiencyCheckBox = new JCheckBox("athletics");
    private final JCheckBox deceptionProficiencyCheckBox = new JCheckBox("deception");
    private final JCheckBox historyProficiencyCheckBox = new JCheckBox("history");
    private final JCheckBox insightProficiencyCheckBox = new JCheckBox("insight");
    private final JCheckBox intimidationProficiencyCheckBox = new JCheckBox("intimidation");
    private final JCheckBox investigationProficiencyCheckBox = new JCheckBox("investigation");
    private final JCheckBox medicineProficiencyCheckBox = new JCheckBox("medicine");
    private final JCheckBox natureProficiencyCheckBox = new JCheckBox("nature");
    private final JCheckBox perceptionProficiencyCheckBox = new JCheckBox("perception");
    private final JCheckBox performanceProficiencyCheckBox = new JCheckBox("performance");
    private final JCheckBox persuasionProficiencyCheckBox = new JCheckBox("persuasion");
    private final JCheckBox religionProficiencyCheckBox = new JCheckBox("religion");
    private final JCheckBox sleightOfHandProficiencyCheckBox = new JCheckBox("sleightOfHand");
    private final JCheckBox stealthProficiencyCheckBox = new JCheckBox("stealth");
    private final JCheckBox survivalProficiencyCheckBox = new JCheckBox("survival");

    private final ArrayList<JCheckBox> immunityCheckBoxList = new ArrayList<>();
    private final JCheckBox blindedImmunityCheckBox = new JCheckBox("blinded");
    private final JCheckBox charmedImmunityCheckBox = new JCheckBox("charmed");
    private final JCheckBox deafenedImmunityCheckBox = new JCheckBox("deafened");
    private final JCheckBox frightenedImmunityCheckBox = new JCheckBox("frightened");
    private final JCheckBox grappledImmunityCheckBox = new JCheckBox("grappled");
    private final JCheckBox incapacitatedImmunityCheckBox = new JCheckBox("incapacitated");
    private final JCheckBox invisibleImmunityCheckBox = new JCheckBox("invisible");
    private final JCheckBox paralyzedImmunityCheckBox = new JCheckBox("paralyzed");
    private final JCheckBox petrifiedImmunityCheckBox = new JCheckBox("petrified");
    private final JCheckBox poisonedImmunityCheckBox = new JCheckBox("poisoned");
    private final JCheckBox proneImmunityCheckBox = new JCheckBox("prone");
    private final JCheckBox restrainedImmunityCheckBox = new JCheckBox("restrained");
    private final JCheckBox stunnedImmunityCheckBox = new JCheckBox("stunned");
    private final JCheckBox unconsciousImmunityCheckBox = new JCheckBox("unconscious");
    private final JCheckBox exhaustionImmunityCheckBox = new JCheckBox("exhaustion");

    // panels
    private final JPanel titlePanel = new JPanel();
    private final JPanel nameCRPanel = new JPanel();
    private final JPanel sizeTypeAlignmentPanel = new JPanel();

    private final JPanel combatStatsPanel = new JPanel();
    private final JPanel hpPanel = new JPanel();
    private final JPanel armourPanel = new JPanel();
    private final JPanel speedsPanel = new JPanel();
    private final JPanel sensesPanel = new JPanel();

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
    private JButton backButton;
    private JButton openStatBlockButton;
    private JButton deleteStatBlocksButton;

    private final JButton addLanguageButton = new JButton("Add");
    private final JButton addAbilityButton = new JButton("Add");
    private final JButton addActionDamageButton = new JButton("Add Damage");
    private final JButton addActionButton = new JButton("Add Action");
    private final JButton addLegendaryActionButton = new JButton("Add");

    // lists
    private final DefaultListModel<String> languagesListModel = new DefaultListModel<>();
    private final JList<String> languageList = new JList<>(languagesListModel);

    private final DefaultListModel<Ability> abilitiesListModel = new DefaultListModel<>();
    private final JList<Ability> abilitiesList = new JList<>(abilitiesListModel);

    private final DefaultListModel<RollableAction> rollableActionsListModel = new DefaultListModel<>();
    private final JList<RollableAction> actionsList = new JList<>(rollableActionsListModel);

    private final DefaultListModel<HashMap<DamageType, RollFormula>> actionDamageMapListModel
            = new DefaultListModel<>();
    private final JList<HashMap<DamageType, RollFormula>> actionDamageMapList = new JList<>(actionDamageMapListModel);

    private final DefaultListModel<Ability> legendaryActionsListModel = new DefaultListModel<>();
    private final JList<Ability> legendaryActionsList = new JList<>(legendaryActionsListModel);

    // scroll panes
    private final JScrollPane languagesScrollPane = new JScrollPane(languageList);
    private final JScrollPane abilityDescriptionScrollPane = new JScrollPane(abilityDescriptionTextField);
    private final JScrollPane abilitiesScrollPane = new JScrollPane(abilitiesList);
    private final JScrollPane actionsScrollPane = new JScrollPane(actionsList);
    private final JScrollPane actionDamageScrollPane = new JScrollPane(actionDamageMapList);
    private final JScrollPane legendaryDescriptionScrollPane = new JScrollPane(legendaryDescriptionTextField);
    private final JScrollPane legendaryActionDescriptionScrollPane
            = new JScrollPane(legendaryActionDescriptionTextField);
    private final JScrollPane legendaryActionsScrollPane = new JScrollPane(legendaryActionsList);

    // constants
    private static final String TAB = "    ";
    private static final int FLOW_H_GAP = 5;
    private static final int FLOW_V_GAP = 5;

    private static final String[] SIZES = {"Small", "Medium", "Large", "Huge", "Gargantuan"};
    private static final String[] TYPES = {"Aberration", "Beast", "Celestial", "Construct", "Dragon",
            "Elemental", "Fey", "Fiend", "Giant", "Humanoid", "Monstrosity", "Ooze", "Plant", "Undead"};
    private static final String[] ALIGNMENTS = {"unaligned", "lawful good", "neutral good", "chaotic good",
            "neutral good", "neutral", "neutral evil", "chaotic good", "chaotic neutral", "chaotic evil"};
    private static final String[] RESISTANCE_OPTIONS = {"---", "Resistance", "Vulnerability", "Immunity"};
    private static final String[] ACTION_DESCRIPTIONS = {"Melee Weapon Attack", "Melee Spell Attack",
            "Ranged Weapon Attack", "Ranged Spell Attack", "Melee or Ranged Weapon Attack", "Action"};

    // EFFECTS: constructs this display panel
    public StatBlockCreationDisplayPanel(MainMenuPanel mainMenuPanel) {
        super(null, mainMenuPanel); // sets the layout manager, mainmenu panel, size, and visibility
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension((int) (mainMenuPanel.getPreferredSize().width / 2.5), 1080));

        initializeAll();

        this.add(titlePanel);
        this.add(new JSeparator());
        this.add(combatStatsPanel);
        this.add(new JSeparator());
        this.add(abilityScoresPanel);
        this.add(new JSeparator());
        this.add(peripheralFieldsPanel);
        this.add(new JSeparator());
        this.add(abilityCreationPanel);
        this.add(abilitiesScrollPane);
        this.add(new JSeparator());
        this.add(actionCreationPanel);
        this.add(actionsScrollPane);
        this.add(new JSeparator());
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
        initializeButtons();
        initializeLists();
        initializeCheckBoxLists();
        initializeResistanceComboBoxList();
        this.revalidate();
        this.repaint();
    }

    // EFFECTS: ...
    private void initializeCheckBoxLists() {
        initializeSavingThrowCheckBoxList();
        initializeSkillCheckBoxList();
        initializeImmunityCheckBoxList();
    }

    // EFFECTS: ...
    private void initializeSavingThrowCheckBoxList() {
        savingThrowCheckBoxList.add(strengthSavingThrowProficiencyCheckBox);
        savingThrowCheckBoxList.add(dexteritySavingThrowProficiencyCheckBox);
        savingThrowCheckBoxList.add(constitutionSavingThrowProficiencyCheckBox);
        savingThrowCheckBoxList.add(intelligenceSavingThrowProficiencyCheckBox);
        savingThrowCheckBoxList.add(wisdomSavingThrowProficiencyCheckBox);
        savingThrowCheckBoxList.add(charismaSavingThrowProficiencyCheckBox);
    }

    // EFFECTS: ...
    private void initializeSkillCheckBoxList() {
        skillCheckBoxList.add(acrobaticsProficiencyCheckBox);
        skillCheckBoxList.add(animalHandlingProficiencyCheckBox);
        skillCheckBoxList.add(arcanaProficiencyCheckBox);
        skillCheckBoxList.add(athleticsProficiencyCheckBox);
        skillCheckBoxList.add(deceptionProficiencyCheckBox);
        skillCheckBoxList.add(historyProficiencyCheckBox);
        skillCheckBoxList.add(insightProficiencyCheckBox);
        skillCheckBoxList.add(intimidationProficiencyCheckBox);
        skillCheckBoxList.add(investigationProficiencyCheckBox);
        skillCheckBoxList.add(medicineProficiencyCheckBox);
        skillCheckBoxList.add(natureProficiencyCheckBox);
        skillCheckBoxList.add(perceptionProficiencyCheckBox);
        skillCheckBoxList.add(performanceProficiencyCheckBox);
        skillCheckBoxList.add(persuasionProficiencyCheckBox);
        skillCheckBoxList.add(religionProficiencyCheckBox);
        skillCheckBoxList.add(sleightOfHandProficiencyCheckBox);
        skillCheckBoxList.add(stealthProficiencyCheckBox);
        skillCheckBoxList.add(survivalProficiencyCheckBox);
    }

    // EFFECTS: ...
    private void initializeImmunityCheckBoxList() {
        immunityCheckBoxList.add(blindedImmunityCheckBox);
        immunityCheckBoxList.add(charmedImmunityCheckBox);
        immunityCheckBoxList.add(deafenedImmunityCheckBox);
        immunityCheckBoxList.add(frightenedImmunityCheckBox);
        immunityCheckBoxList.add(grappledImmunityCheckBox);
        immunityCheckBoxList.add(incapacitatedImmunityCheckBox);
        immunityCheckBoxList.add(invisibleImmunityCheckBox);
        immunityCheckBoxList.add(paralyzedImmunityCheckBox);
        immunityCheckBoxList.add(petrifiedImmunityCheckBox);
        immunityCheckBoxList.add(poisonedImmunityCheckBox);
        immunityCheckBoxList.add(proneImmunityCheckBox);
        immunityCheckBoxList.add(restrainedImmunityCheckBox);
        immunityCheckBoxList.add(stunnedImmunityCheckBox);
        immunityCheckBoxList.add(unconsciousImmunityCheckBox);
        immunityCheckBoxList.add(exhaustionImmunityCheckBox);
    }

    // EFFECTS: ...
    private void initializeResistanceComboBoxList() {
        resistanceComboBoxList.add(acidResistanceComboBox);
        resistanceComboBoxList.add(bludgeoningResistanceComboBox);
        resistanceComboBoxList.add(coldResistanceComboBox);
        resistanceComboBoxList.add(fireResistanceComboBox);
        resistanceComboBoxList.add(forceResistanceComboBox);
        resistanceComboBoxList.add(lightningResistanceComboBox);
        resistanceComboBoxList.add(necroticResistanceComboBox);
        resistanceComboBoxList.add(piercingResistanceComboBox);
        resistanceComboBoxList.add(poisonResistanceComboBox);
        resistanceComboBoxList.add(psychicResistanceComboBox);
        resistanceComboBoxList.add(radiantResistanceComboBox);
        resistanceComboBoxList.add(slashingResistanceComboBox);
        resistanceComboBoxList.add(thunderResistanceComboBox);
    }

    // EFFECTS: ...
    private void initializeButtons() {
        importButtons();

        ArrayList<JButton> buttonList = new ArrayList<>();

        buttonList.add(backButton);
        buttonList.add(openStatBlockButton);
        buttonList.add(deleteStatBlocksButton);
        buttonList.add(addLanguageButton);
        buttonList.add(addAbilityButton);
        buttonList.add(addActionDamageButton);
        buttonList.add(addActionButton);
        buttonList.add(addLegendaryActionButton);

        for (JButton jb : buttonList) {
            jb.addActionListener(this);
        }
        //initializeButtonIcons();
    }

    // EFFECTS: ...
    private void importButtons() {
        backButton = mainMenuPanel.getBackButton();
        openStatBlockButton = mainMenuPanel.getOpenStatBlockButton();
        deleteStatBlocksButton = mainMenuPanel.getDeleteStatBlocksButton();
    }

    // EFFECTS: ...
    private void initializeLists() {
        languageList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        languageList.setLayoutOrientation(JList.VERTICAL);
        languageList.setVisibleRowCount(-1);
        languageList.addListSelectionListener(this);

        abilitiesList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        abilitiesList.setLayoutOrientation(JList.VERTICAL);
        abilitiesList.setVisibleRowCount(-1);
        abilitiesList.addListSelectionListener(this);

        actionsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        actionsList.setLayoutOrientation(JList.VERTICAL);
        actionsList.setVisibleRowCount(-1);
        actionsList.addListSelectionListener(this);

        actionDamageMapList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        actionDamageMapList.setLayoutOrientation(JList.VERTICAL);
        actionDamageMapList.setVisibleRowCount(-1);
        actionDamageMapList.addListSelectionListener(this);

        legendaryActionsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        legendaryActionsList.setLayoutOrientation(JList.VERTICAL);
        legendaryActionsList.setVisibleRowCount(-1);
        legendaryActionsList.addListSelectionListener(this);
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
            sp.setPreferredSize(new Dimension(this.getPreferredSize().width, 100));
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
        initializeSensesPanel();

        combatStatsPanel.setLayout(new BoxLayout(combatStatsPanel, BoxLayout.Y_AXIS));
        combatStatsPanel.add(hpPanel);
        combatStatsPanel.add(armourPanel);
        combatStatsPanel.add(speedsPanel);
        combatStatsPanel.add(sensesPanel);
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
        speedsPanel.add(new JLabel("Climb:"));
        speedsPanel.add(climbTextField);
        speedsPanel.add(new JLabel("Burrow:"));
        speedsPanel.add(burrowTextField);
    }

    // EFFECTS: ...
    private void initializeSensesPanel() {
        requiredTextFieldsList.add(passivePerceptionTextField);
        numberTextFieldList.add(passivePerceptionTextField);
        numberTextFieldList.add(tremorSenseTextField);
        numberTextFieldList.add(blindSightTextField);
        numberTextFieldList.add(trueSightTextField);
        numberTextFieldList.add(darkVisionTextField);

        sensesPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        sensesPanel.add(new JLabel("PassivePerception:"));
        sensesPanel.add(passivePerceptionTextField);
        sensesPanel.add(new JLabel("DarkVision:"));
        sensesPanel.add(darkVisionTextField);
        sensesPanel.add(new JLabel("TrueSight:"));
        sensesPanel.add(trueSightTextField);
        sensesPanel.add(new JLabel("BlindSight:"));
        sensesPanel.add(blindSightTextField);
        sensesPanel.add(new JLabel("TremorSense:"));
        sensesPanel.add(tremorSenseTextField);
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
        savingThrowsPanel.add(strengthSavingThrowProficiencyCheckBox);
        savingThrowsPanel.add(dexteritySavingThrowProficiencyCheckBox);
        savingThrowsPanel.add(constitutionSavingThrowProficiencyCheckBox);
        savingThrowsPanel.add(intelligenceSavingThrowProficiencyCheckBox);
        savingThrowsPanel.add(wisdomSavingThrowProficiencyCheckBox);
        savingThrowsPanel.add(charismaSavingThrowProficiencyCheckBox);
    }

    // EFFECTS: ...
    private void initializeSkillsPanel() {
        skillsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        skillsPanel.add(new JLabel("Skill Proficiencies:"));
        skillsPanel.add(acrobaticsProficiencyCheckBox);
        skillsPanel.add(animalHandlingProficiencyCheckBox);
        skillsPanel.add(arcanaProficiencyCheckBox);
        skillsPanel.add(athleticsProficiencyCheckBox);
        skillsPanel.add(deceptionProficiencyCheckBox);
        skillsPanel.add(historyProficiencyCheckBox);
        skillsPanel.add(insightProficiencyCheckBox);
        skillsPanel.add(intimidationProficiencyCheckBox);
        skillsPanel.add(investigationProficiencyCheckBox);
        skillsPanel.add(medicineProficiencyCheckBox);
        skillsPanel.add(natureProficiencyCheckBox);
        skillsPanel.add(perceptionProficiencyCheckBox);
        skillsPanel.add(performanceProficiencyCheckBox);
        skillsPanel.add(persuasionProficiencyCheckBox);
        skillsPanel.add(religionProficiencyCheckBox);
        skillsPanel.add(sleightOfHandProficiencyCheckBox);
        skillsPanel.add(stealthProficiencyCheckBox);
        skillsPanel.add(survivalProficiencyCheckBox);
    }

    // EFFECTS: ...
    private void initializeResistancesPanel() {
        resistancesPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        resistancesPanel.add(new JLabel("Resistances:"));
        resistancesPanel.add(new JLabel("Acid"));
        resistancesPanel.add(acidResistanceComboBox);
        resistancesPanel.add(new JLabel("Bludgeoning"));
        resistancesPanel.add(bludgeoningResistanceComboBox);
        resistancesPanel.add(new JLabel("Cold"));
        resistancesPanel.add(coldResistanceComboBox);
        resistancesPanel.add(new JLabel("Fire"));
        resistancesPanel.add(fireResistanceComboBox);
        resistancesPanel.add(new JLabel("Force"));
        resistancesPanel.add(forceResistanceComboBox);
        resistancesPanel.add(new JLabel("Lightning"));
        resistancesPanel.add(lightningResistanceComboBox);
        initializeResistancesPanelPartTwo();
    }

    // EFFECTS: ...
    private void initializeResistancesPanelPartTwo() {
        resistancesPanel.add(new JLabel("Necrotic"));
        resistancesPanel.add(necroticResistanceComboBox);
        resistancesPanel.add(new JLabel("Piercing"));
        resistancesPanel.add(piercingResistanceComboBox);
        resistancesPanel.add(new JLabel("Poison"));
        resistancesPanel.add(poisonResistanceComboBox);
        resistancesPanel.add(new JLabel("Psychic"));
        resistancesPanel.add(psychicResistanceComboBox);
        resistancesPanel.add(new JLabel("Radiant"));
        resistancesPanel.add(radiantResistanceComboBox);
        resistancesPanel.add(new JLabel("Slashing"));
        resistancesPanel.add(slashingResistanceComboBox);
        resistancesPanel.add(new JLabel("Thunder"));
        resistancesPanel.add(thunderResistanceComboBox);
    }

    // EFFECTS: ...
    private void initializeImmunitiesPanel() {
        immunitiesPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        immunitiesPanel.add(new JLabel("Condition Immunities:"));
        immunitiesPanel.add(blindedImmunityCheckBox);
        immunitiesPanel.add(charmedImmunityCheckBox);
        immunitiesPanel.add(deafenedImmunityCheckBox);
        immunitiesPanel.add(frightenedImmunityCheckBox);
        immunitiesPanel.add(grappledImmunityCheckBox);
        immunitiesPanel.add(incapacitatedImmunityCheckBox);
        immunitiesPanel.add(invisibleImmunityCheckBox);
        immunitiesPanel.add(paralyzedImmunityCheckBox);
        immunitiesPanel.add(petrifiedImmunityCheckBox);
        immunitiesPanel.add(poisonedImmunityCheckBox);
        immunitiesPanel.add(proneImmunityCheckBox);
        immunitiesPanel.add(restrainedImmunityCheckBox);
        immunitiesPanel.add(stunnedImmunityCheckBox);
        immunitiesPanel.add(unconsciousImmunityCheckBox);
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
        numberTextFieldList.add(actionRangeTextField);
        numberTextFieldList.add(actionLongRangeTextField);

        JPanel nameRangePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        nameRangePanel.add(new JLabel("Name:"));
        nameRangePanel.add(actionNameTextField);
        nameRangePanel.add(new JLabel("Range:"));
        nameRangePanel.add(actionRangeTextField);
        nameRangePanel.add(new JLabel("Long Range (Optional):"));
        nameRangePanel.add(actionLongRangeTextField);
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
        numberTextFieldList.add(actionHitModifierTextField);

        hitDescriptionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        hitDescriptionPanel.add(new JLabel("Hit Modifier:"));
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

    // EFFECTS: ...
    public void passAction(ActionEvent e) {
        if (e.getSource() == backButton) {
            mainMenuPanel.setDisplays("library");
        } else if (e.getSource() == openStatBlockButton) {
            tryCreatingStatBlock();
        } else if (e.getSource() == deleteStatBlocksButton) {
            deleteSelection();
        }
    }

    @Override
    // EFFECTS: ...
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addLanguageButton) {
            tryAddLanguage();
        } else if (e.getSource() == addAbilityButton) {
            tryAddAbility();
        } else if (e.getSource() == addActionDamageButton) {
            tryAddActionDamage();
        } else if (e.getSource() == addActionButton) {
            tryAddRollableAction();
        } else if (e.getSource() == addLegendaryActionButton) {
            tryAddLegendaryAction();
        }
    }

    // EFFECTS: ...
    private void tryCreatingStatBlock() {
        try {
            int amountOfDice = Integer.parseInt(actionDamageAmountOfDiceTextField.getText());
            int dieSides = Integer.parseInt(actionDamageDieSidesTextField.getText());
            int modifier = Integer.parseInt(actionDamageModifierTextField.getText());

            StatBlock statBlock = new StatBlock.StatBlockBuilder(getTitle(),
                    Integer.parseInt(xpTextField.getText()), new RollFormula(amountOfDice,
                            dieSides, modifier), Integer.parseInt(proficiencyTextField.getText()),
                            getArmour(), getSpeeds(), getSenses(), getAbilityScores(), getRollableActions())
                    .skillProficiencies(getSkillProficiencies())
                    .savingThrowProficiencies(getSavingThrowProficiencies())
                    .languages(getLanguages())
                    .conditionImmunities(getConditionImmunities())
                    .resistances(getResistances())
                    .legendaryMechanics(getLegendaryMechanics())
                    .build();

            mainMenuPanel.getMenuManagerPanel().getLibraryListModel().addElement(statBlock);
            mainMenuPanel.setDisplays("library");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not build StatBlock: "
                    + e.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // EFFECTS: ...
    private Title getTitle() throws IncompleteFieldException {
        if (nameTextField.getText().matches(".*\\d+.*")) {
            throw new IndexOutOfBoundsException("title cannot have numbers in it");
        }
        return new Title(nameTextField.getText(), typeComboBox.getSelectedItem().toString(),
                sizeComboBox.getSelectedItem().toString(), alignmentComboBox.getSelectedItem().toString(), null);
    }

    // EFFECTS: ...
    private Armour getArmour() throws NumberFormatException, IncompleteFieldException {
        String armourName = armourNameTextField.getText();
        int magicArmour = 0;

        if (armourName.equals("")) {
            armourName = null;
        }

        if (!magicArmourTextField.getText().isEmpty()) {
            magicArmour = Integer.parseInt(magicArmourTextField.getText());
        }

        return new Armour.ArmourBuilder(Integer.parseInt(armourTextField.getText()))
                .magicArmour(magicArmour)
                .armourName(armourName)
                .build();
    }

    // EFFECTS: ...
    private Speeds getSpeeds() throws NumberFormatException, IndexOutOfBoundsException {
        int fly = 0;
        int swim = 0;
        int burrow = 0;
        int climb = 0;

        if (!flyTextField.getText().isEmpty()) {
            fly = Integer.parseInt(flyTextField.getText());
        }

        if (!flyTextField.getText().isEmpty()) {
            swim = Integer.parseInt(swimTextField.getText());
        }

        if (!flyTextField.getText().isEmpty()) {
            burrow = Integer.parseInt(burrowTextField.getText());
        }

        if (!flyTextField.getText().isEmpty()) {
            climb = Integer.parseInt(climbTextField.getText());
        }

        return new Speeds.SpeedsBuilder(Integer.parseInt(speedTextField.getText()))
                .swim(swim)
                .fly(fly)
                .burrow(burrow)
                .climb(climb)
                .build();
    }

    // EFFECTS: ...
    private Senses getSenses() throws NumberFormatException, IndexOutOfBoundsException {
        int tremorSense = 0;
        int trueSight = 0;
        int blindSight = 0;
        int darkVision = 0;

        if (!tremorSenseTextField.getText().isEmpty()) {
            tremorSense = Integer.parseInt(tremorSenseTextField.getText());
        }

        if (!trueSightTextField.getText().isEmpty()) {
            trueSight = Integer.parseInt(trueSightTextField.getText());
        }

        if (!blindSightTextField.getText().isEmpty()) {
            blindSight = Integer.parseInt(blindSightTextField.getText());
        }

        if (!darkVisionTextField.getText().isEmpty()) {
            darkVision = Integer.parseInt(darkVisionTextField.getText());
        }

        return new Senses.SensesBuilder(Integer.parseInt(passivePerceptionTextField.getText()))
                .tremorSense(tremorSense)
                .trueSight(trueSight)
                .blindSight(blindSight)
                .darkVision(darkVision)
                .build();
    }

    // EFFECTS: ...
    private AbilityScoreSet getAbilityScores() throws NumberFormatException, IndexOutOfBoundsException {
        return new AbilityScoreSet(Integer.parseInt(strengthTextField.getText()),
                Integer.parseInt(dexterityTextField.getText()),
                Integer.parseInt(constitutionTextField.getText()),
                Integer.parseInt(intelligenceTextField.getText()),
                Integer.parseInt(wisdomTextField.getText()),
                Integer.parseInt(charismaTextField.getText()));
    }

    // EFFECTS: ...
    private ArrayList<RollableAction> getRollableActions() throws IncompleteFieldException {
        ArrayList<RollableAction> rollableActions = new ArrayList<>();

        if (rollableActionsListModel.isEmpty()) {
            throw new IncompleteFieldException("given rollable actions is empty");
        } else {
            for (int i = 0; i < rollableActionsListModel.getSize(); i++) {
                rollableActions.add(rollableActionsListModel.getElementAt(i));
            }
        }

        return rollableActions;
    }

    // EFFECTS: ...
    private ArrayList<AbilityScore> getSavingThrowProficiencies() {
        ArrayList<AbilityScore> savingThrows = new ArrayList<>();

        for (JCheckBox cb : savingThrowCheckBoxList) {
            if (cb.isSelected()) {
                for (AbilityScore as : AbilityScore.values()) {
                    if (cb.getText().equalsIgnoreCase(as.toString())) {
                        savingThrows.add(as);
                    }
                }
            }
        }
        return savingThrows;
    }

    // EFFECTS: ...
    private ArrayList<Skill> getSkillProficiencies() {
        ArrayList<Skill> skills = new ArrayList<>();

        for (JCheckBox cb : skillCheckBoxList) {
            if (cb.isSelected()) {
                for (Skill s : Skill.values()) {
                    if (cb.getText().equalsIgnoreCase(s.toString())) {
                        skills.add(s);
                    }
                }
            }
        }

        return skills;
    }

    // EFFECTS: ...
    private ArrayList<Condition> getConditionImmunities() {
        ArrayList<Condition> immunities = new ArrayList<>();

        for (JCheckBox cb : immunityCheckBoxList) {
            if (cb.isSelected()) {
                for (Condition c : Condition.values()) {
                    if (cb.getText().equalsIgnoreCase(c.toString())) {
                        immunities.add(c);
                    }
                }
            }
        }

        return immunities;
    }

    // EFFECTS: ...
    private HashMap<DamageType, ResistanceType> getResistances() {
        HashMap<DamageType, ResistanceType> resistances = new HashMap<>();

        for (ResistanceType rt : ResistanceType.values()) {
            if (Objects.requireNonNull(acidResistanceComboBox.getSelectedItem()).toString()
                    .equalsIgnoreCase(rt.toString())) {
                resistances.put(DamageType.ACID, rt);
            }
        }

        for (ResistanceType rt : ResistanceType.values()) {
            if (Objects.requireNonNull(bludgeoningResistanceComboBox.getSelectedItem()).toString()
                    .equalsIgnoreCase(rt.toString())) {
                resistances.put(DamageType.BLUDGEONING, rt);
            }
        }

        for (ResistanceType rt : ResistanceType.values()) {
            if (Objects.requireNonNull(coldResistanceComboBox.getSelectedItem()).toString()
                    .equalsIgnoreCase(rt.toString())) {
                resistances.put(DamageType.COLD, rt);
            }
        }
        return getResistancesHelperOne(resistances);
    }

    // EFFECTS: ...
    private HashMap<DamageType, ResistanceType> getResistancesHelperOne(
            HashMap<DamageType, ResistanceType> resistances) {
        for (ResistanceType rt : ResistanceType.values()) {
            if (Objects.requireNonNull(fireResistanceComboBox.getSelectedItem()).toString()
                    .equalsIgnoreCase(rt.toString())) {
                resistances.put(DamageType.FIRE, rt);
            }
        }

        for (ResistanceType rt : ResistanceType.values()) {
            if (Objects.requireNonNull(forceResistanceComboBox.getSelectedItem()).toString()
                    .equalsIgnoreCase(rt.toString())) {
                resistances.put(DamageType.FORCE, rt);
            }
        }

        for (ResistanceType rt : ResistanceType.values()) {
            if (Objects.requireNonNull(lightningResistanceComboBox.getSelectedItem()).toString()
                    .equalsIgnoreCase(rt.toString())) {
                resistances.put(DamageType.LIGHTNING, rt);
            }
        }

        return getResistancesHelperTwo(resistances);
    }

    // EFFECTS: ...
    private HashMap<DamageType, ResistanceType> getResistancesHelperTwo(
            HashMap<DamageType, ResistanceType> resistances) {
        for (ResistanceType rt : ResistanceType.values()) {
            if (Objects.requireNonNull(necroticResistanceComboBox.getSelectedItem()).toString()
                    .equalsIgnoreCase(rt.toString())) {
                resistances.put(DamageType.NECROTIC, rt);
            }
        }

        for (ResistanceType rt : ResistanceType.values()) {
            if (Objects.requireNonNull(piercingResistanceComboBox.getSelectedItem()).toString()
                    .equalsIgnoreCase(rt.toString())) {
                resistances.put(DamageType.PIERCING, rt);
            }
        }

        for (ResistanceType rt : ResistanceType.values()) {
            if (Objects.requireNonNull(poisonResistanceComboBox.getSelectedItem()).toString()
                    .equalsIgnoreCase(rt.toString())) {
                resistances.put(DamageType.POISON, rt);
            }
        }

        return getResistancesHelperThree(resistances);
    }

    // EFFECTS: ...
    private HashMap<DamageType, ResistanceType> getResistancesHelperThree(
            HashMap<DamageType, ResistanceType> resistances) {
        for (ResistanceType rt : ResistanceType.values()) {
            if (Objects.requireNonNull(psychicResistanceComboBox.getSelectedItem()).toString()
                    .equalsIgnoreCase(rt.toString())) {
                resistances.put(DamageType.PSYCHIC, rt);
            }
        }

        for (ResistanceType rt : ResistanceType.values()) {
            if (Objects.requireNonNull(radiantResistanceComboBox.getSelectedItem()).toString()
                    .equalsIgnoreCase(rt.toString())) {
                resistances.put(DamageType.RADIANT, rt);
            }
        }

        for (ResistanceType rt : ResistanceType.values()) {
            if (Objects.requireNonNull(slashingResistanceComboBox.getSelectedItem()).toString()
                    .equalsIgnoreCase(rt.toString())) {
                resistances.put(DamageType.SLASHING, rt);
            }
        }

        return getResistancesHelperFour(resistances);
    }

    // EFFECTS: ...
    private HashMap<DamageType, ResistanceType> getResistancesHelperFour(
            HashMap<DamageType, ResistanceType> resistances) {
        for (ResistanceType rt : ResistanceType.values()) {
            if (Objects.requireNonNull(thunderResistanceComboBox.getSelectedItem()).toString()
                    .equalsIgnoreCase(rt.toString())) {
                resistances.put(DamageType.THUNDER, rt);
            }
        }

        return resistances;
    }

    // EFFECTS: ...
    private Languages getLanguages() throws NumberFormatException, IncompleteFieldException {
        ArrayList<String> newLanguageList = new ArrayList<>();
        int telepathy = 0;

        if (!languagesListModel.isEmpty()) {
            if (!telepathyTextField.getText().isEmpty()) {
                telepathy = Integer.parseInt(telepathyTextField.getText());
            }
            for (int i = 0; i < languagesListModel.getSize(); i++) {
                newLanguageList.add(languagesListModel.getElementAt(i));
            }

            return new Languages.LanguagesBuilder(newLanguageList)
                    .telepathy(telepathy)
                    .build();
        } else {
            return null;
        }
    }

    // EFFECTS: ...
    private LegendaryMechanics getLegendaryMechanics() throws IncompleteFieldException {
        ArrayList<Ability> newLegendaryActions = new ArrayList<>();

        for (int i = 0; i < legendaryActionsListModel.getSize(); i++) {
            newLegendaryActions.add(legendaryActionsListModel.getElementAt(i));
        }

        if (!legendaryActionsListModel.isEmpty() && legendaryDescriptionTextField.getText().isEmpty()) {
            throw new IncompleteFieldException();
        } else if (legendaryActionsListModel.isEmpty() && legendaryDescriptionTextField.getText().isEmpty()) {
            return null;
        } else {
            return new LegendaryMechanics(legendaryDescriptionTextField.getText(), newLegendaryActions);
        }
    }

    // EFFECTS: ...
    private void deleteSelection() {
        for (Object o : languageList.getSelectedValuesList()) {
            languagesListModel.removeElement(o);
        }
        for (Object o : abilitiesList.getSelectedValuesList()) {
            abilitiesListModel.removeElement(o);
        }
        for (Object o : actionsList.getSelectedValuesList()) {
            rollableActionsListModel.removeElement(o);
        }
        for (Object o : actionDamageMapList.getSelectedValuesList()) {
            actionDamageMapListModel.removeElement(o);
        }
        for (Object o : legendaryActionsList.getSelectedValuesList()) {
            legendaryActionsListModel.removeElement(o);
        }
    }

    // EFFECTS: ...
    private void tryAddLanguage() {
        try {
            languagesListModel.addElement(languageTextField.getText());
            languageList.setModel(languagesListModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not add language. Message: "
                    + e.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // EFFECTS: ...
    private void tryAddAbility() {
        try {
            Ability newAbility = new Ability(abilityNameTextField.getText(), abilityDescriptionTextField.getText());
            abilitiesListModel.addElement(newAbility);
            abilitiesList.setModel(abilitiesListModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not add ability. Message: "
                    + e.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // EFFECTS: ...
    private void tryAddActionDamage() {
        try {
            int amountOfDice = Integer.parseInt(actionDamageAmountOfDiceTextField.getText());
            int dieSides = Integer.parseInt(actionDamageDieSidesTextField.getText());
            int modifier = Integer.parseInt(actionDamageModifierTextField.getText());

            RollFormula newRollFormula = new RollFormula(amountOfDice, dieSides, modifier);
            HashMap<DamageType, RollFormula> newDamageMap = new HashMap<>();

            for (DamageType dt : DamageType.values()) {
                if (Objects.requireNonNull(actionDamageTypeComboBox.getSelectedItem()).toString()
                        .equalsIgnoreCase(dt.toString())) {
                    newDamageMap.put(dt, newRollFormula);
                }
            }

            actionDamageMapListModel.addElement(newDamageMap);
            actionDamageMapList.setModel(actionDamageMapListModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "NumberFormatException caught. Message: "
                    + e.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // EFFECTS: ...
    private void tryAddRollableAction() {
        try {
            int modifier = Integer.parseInt(actionHitModifierTextField.getText());
            int range = Integer.parseInt(actionRangeTextField.getText());
            int longRange = 0;
            if (!actionLongRangeTextField.getText().isEmpty()) {
                longRange = Integer.parseInt(actionLongRangeTextField.getText());
            }

            HashMap<DamageType, RollFormula> damageMap = new HashMap<>();

            for (int i = 0; i < actionDamageMapListModel.getSize(); i++) {
                damageMap.putAll(actionDamageMapListModel.getElementAt(i));
            }

            RollableAction newRollableAction = new RollableAction(actionNameTextField.getText(),
                    Objects.requireNonNull(actionDescriptionComboBox.getSelectedItem()).toString(),
                    range, longRange, modifier, damageMap);

            rollableActionsListModel.addElement(newRollableAction);
            actionsList.setModel(rollableActionsListModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not add action. Message: "
                    + e.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // EFFECTS: ...
    private void tryAddLegendaryAction() {
        try {
            legendaryActionsListModel.addElement(new Ability(legendaryActionNameTextField.getText(),
                    legendaryActionDescriptionTextField.getText()));
            legendaryActionsList.setModel(legendaryActionsListModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not add legendary action. Message: "
                    + e.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    // EFFECTS: ...
    public void valueChanged(ListSelectionEvent e) {
        deleteStatBlocksButton.setEnabled(!e.getValueIsAdjusting());
        addLanguageButton.setEnabled(!e.getValueIsAdjusting());
        addAbilityButton.setEnabled(!e.getValueIsAdjusting());
        addActionDamageButton.setEnabled(!e.getValueIsAdjusting());
        addActionButton.setEnabled(!e.getValueIsAdjusting());
        addLegendaryActionButton.setEnabled(!e.getValueIsAdjusting());
    }
}
