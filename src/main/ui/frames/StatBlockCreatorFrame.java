package ui.frames;

import enums.*;
import model.RollFormula;
import model.StatBlock;
import model.statblockfields.*;
import exceptions.IncompleteFieldException;
import ui.panels.menus.MainMenuPanel;
import ui.scrollpanes.ParchmentScrollPane;
import ui.scrollpanes.TransparentListCellRenderer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.HashMap;
import java.util.Objects;

// Represents a window that allows the user to create a new statblock
public class StatBlockCreatorFrame extends JFrame implements ActionListener, ListSelectionListener {
    // text fields
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

    private final JComboBox<Integer> acrobaticsProficiencyComboBox = new JComboBox<>(SKILL_OPTIONS);
    private final JComboBox<Integer> animalHandlingProficiencyComboBox = new JComboBox<>(SKILL_OPTIONS);
    private final JComboBox<Integer> arcanaProficiencyComboBox = new JComboBox<>(SKILL_OPTIONS);
    private final JComboBox<Integer> athleticsProficiencyComboBox = new JComboBox<>(SKILL_OPTIONS);
    private final JComboBox<Integer> deceptionProficiencyComboBox = new JComboBox<>(SKILL_OPTIONS);
    private final JComboBox<Integer> historyProficiencyComboBox = new JComboBox<>(SKILL_OPTIONS);
    private final JComboBox<Integer> insightProficiencyComboBox = new JComboBox<>(SKILL_OPTIONS);
    private final JComboBox<Integer> intimidationProficiencyComboBox = new JComboBox<>(SKILL_OPTIONS);
    private final JComboBox<Integer> investigationProficiencyComboBox = new JComboBox<>(SKILL_OPTIONS);
    private final JComboBox<Integer> medicineProficiencyComboBox = new JComboBox<>(SKILL_OPTIONS);
    private final JComboBox<Integer> natureProficiencyComboBox = new JComboBox<>(SKILL_OPTIONS);
    private final JComboBox<Integer> perceptionProficiencyComboBox = new JComboBox<>(SKILL_OPTIONS);
    private final JComboBox<Integer> performanceProficiencyComboBox = new JComboBox<>(SKILL_OPTIONS);
    private final JComboBox<Integer> persuasionProficiencyComboBox = new JComboBox<>(SKILL_OPTIONS);
    private final JComboBox<Integer> religionProficiencyComboBox = new JComboBox<>(SKILL_OPTIONS);
    private final JComboBox<Integer> sleightOfHandProficiencyComboBox = new JComboBox<>(SKILL_OPTIONS);
    private final JComboBox<Integer> stealthProficiencyComboBox = new JComboBox<>(SKILL_OPTIONS);
    private final JComboBox<Integer> survivalProficiencyComboBox = new JComboBox<>(SKILL_OPTIONS);

    // check boxes
    private final ArrayList<JCheckBox> savingThrowCheckBoxList = new ArrayList<>();
    private final JCheckBox strengthSavingThrowProficiencyCheckBox = new JCheckBox("strength");
    private final JCheckBox dexteritySavingThrowProficiencyCheckBox = new JCheckBox("dexterity");
    private final JCheckBox constitutionSavingThrowProficiencyCheckBox = new JCheckBox("constitution");
    private final JCheckBox intelligenceSavingThrowProficiencyCheckBox = new JCheckBox("intelligence");
    private final JCheckBox wisdomSavingThrowProficiencyCheckBox = new JCheckBox("wisdom");
    private final JCheckBox charismaSavingThrowProficiencyCheckBox = new JCheckBox("charisma");

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
    private final JPanel inputPanel = new JPanel();
    private final JPanel buttonPanel = new JPanel();

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
    private final JButton cancelButton = new JButton("Cancel");
    private final JButton finishButton = new JButton("Finish");
    private final JButton deleteButton = new JButton("Delete");

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
    private final ParchmentScrollPane inputScrollPane = new ParchmentScrollPane(null);

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
    private static final String ICON_DIRECTORY = "images/icons/";
    private static final int WIDTH = 540;
    private static final int HEIGHT = 720;

    private final MainMenuPanel mainMenuPanel;

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
    private static final Integer[] SKILL_OPTIONS = {0,1,2};

    // images
    private static final ImageIcon D20_ICON = new ImageIcon(ClassLoader.getSystemResource(ICON_DIRECTORY + "d20.png"));
    // parsing integers
    private int parsedXP;
    private int parsedHPAmountOfDice;
    private int parsedHPDieSides;
    private int parsedHPModifier;
    private int parsedProficiency;

    // ------------------------------------------------------------------------------
    // MODIFIES: this
    // EFFECTS: constructs this frame
    public StatBlockCreatorFrame(MainMenuPanel mainMenuPanel) {
        super("StatBlock Creator");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setIconImage(D20_ICON.getImage());
        this.mainMenuPanel = mainMenuPanel;

        initializeAll();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(inputScrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates every component and container for this panel
    private void initializeAll() {
        initializeInputPanel();
        initializeInputScrollPane();
        initializeButtonPanel();

        initializeButtons();
        initializeTitlePanel();
        initializeCombatStatsPanel();
        initializeAbilityScoresPanel();
        initializePeripheralFieldsPanel();
        initializeAbilityCreationPanel();
        initializeActionCreationPanel();
        initializeLegendaryCreationPanel();

        buildSavingThrowCheckBoxList();
        buildImmunityCheckBoxList();

        formatTextFields();
        formatScrollPanes();
        formatCheckBoxes();
        formatLists();
    }

    // EFFECTS: TODO effects
    private void initializeInputPanel() {
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setPreferredSize(new Dimension(WIDTH, 2500));
        inputPanel.setOpaque(false);

        inputPanel.add(titlePanel);
        inputPanel.add(combatStatsPanel);
        inputPanel.add(abilityScoresPanel);
        inputPanel.add(peripheralFieldsPanel);
        inputPanel.add(abilityCreationPanel);
        inputPanel.add(abilitiesScrollPane);
        inputPanel.add(actionCreationPanel);
        inputPanel.add(actionsScrollPane);
        inputPanel.add(legendaryCreationPanel);
        inputPanel.add(legendaryActionsScrollPane);
    }

    // EFFECTS: TODO effects
    private void initializeInputScrollPane() {
        inputScrollPane.setViewportView(inputPanel);
        inputScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        inputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    // EFFECTS: TODO effects
    private void initializeButtonPanel() {
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.setPreferredSize(new Dimension(WIDTH, (HEIGHT / 10)));
        buttonPanel.add(finishButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(cancelButton);
        buttonPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats title panel
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
        titlePanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the combat stats panel
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
        combatStatsPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: adds all saving throw check boxes to its list
    private void buildSavingThrowCheckBoxList() {
        savingThrowCheckBoxList.add(strengthSavingThrowProficiencyCheckBox);
        savingThrowCheckBoxList.add(dexteritySavingThrowProficiencyCheckBox);
        savingThrowCheckBoxList.add(constitutionSavingThrowProficiencyCheckBox);
        savingThrowCheckBoxList.add(intelligenceSavingThrowProficiencyCheckBox);
        savingThrowCheckBoxList.add(wisdomSavingThrowProficiencyCheckBox);
        savingThrowCheckBoxList.add(charismaSavingThrowProficiencyCheckBox);
    }

    // MODIFIES: this
    // EFFECTS: adds all immunity check boxes to its list
    private void buildImmunityCheckBoxList() {
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

    // MODIFIES: this
    // EFFECTS: imports and formats all buttons
    private void initializeButtons() {
        ArrayList<JButton> buttonList = new ArrayList<>();

        buttonList.add(cancelButton);
        buttonList.add(finishButton);
        buttonList.add(deleteButton);

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

    // MODIFIES: this
    // EFFECTS: formats all lists
    private void formatLists() {
        ArrayList<JList<?>> listOfLists = new ArrayList<>();
        listOfLists.add(languageList);
        listOfLists.add(abilitiesList);
        listOfLists.add(actionsList);
        listOfLists.add(actionDamageMapList);
        listOfLists.add(legendaryActionsList);

        for (JList<?> l : listOfLists) {
            l.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            l.setLayoutOrientation(JList.VERTICAL);
            l.setVisibleRowCount(-1);
            l.addListSelectionListener(this);
            l.setCellRenderer(new TransparentListCellRenderer());
            l.setOpaque(false);
        }
    }

    // MODIFIES: this
    // EFFECTS: formats all check boxes
    private void formatCheckBoxes() {
        for (JCheckBox cb : savingThrowCheckBoxList) {
            cb.setOpaque(false);
        }
        for (JCheckBox cb : immunityCheckBoxList) {
            cb.setOpaque(false);
        }
    }

    // MODIFIES: this
    // EFFECTS: formats text field lists
    private void formatTextFields() {
        for (JTextField tf : numberTextFieldList) {
            tf.setPreferredSize(new Dimension(50, 25));
            tf.setOpaque(false);
        }
        for (JTextField tf : wordTextFieldList) {
            tf.setPreferredSize(new Dimension(150, 25));
            tf.setOpaque(false);
        }
        for (JTextField tf : requiredTextFieldsList) {
            tf.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            tf.setOpaque(false);
        }
    }

    // MODIFIES: this
    // EFFECTS: formats all scroll panes
    private void formatScrollPanes() {
        ArrayList<JScrollPane> longScrollPanesList = new ArrayList<>();
        longScrollPanesList.add(languagesScrollPane);
        longScrollPanesList.add(abilitiesScrollPane);
        longScrollPanesList.add(actionsScrollPane);
        longScrollPanesList.add(legendaryActionsScrollPane);
        longScrollPanesList.add(actionDamageScrollPane);

        ArrayList<JScrollPane> shortScrollPanesList = new ArrayList<>();
        shortScrollPanesList.add(abilityDescriptionScrollPane);
        shortScrollPanesList.add(legendaryDescriptionScrollPane);
        shortScrollPanesList.add(legendaryActionDescriptionScrollPane);

        for (JScrollPane sp : longScrollPanesList) {
            sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            sp.setPreferredSize(new Dimension(this.getPreferredSize().width, 75));
            sp.setOpaque(false);
        }

        for (JScrollPane sp : shortScrollPanesList) {
            sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            sp.setPreferredSize(new Dimension(this.getPreferredSize().width, 50));
            sp.setOpaque(false);
        }
    }

    // MODIFIES: this
    // EFFECTS: formats the name-cr sub panel
    private void initializeNameCRPanel() {
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        namePanel.add(new JLabel("Name:"));
        namePanel.add(nameTextField);
        namePanel.setOpaque(false);

        JPanel xpPanel = new JPanel();
        xpPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        xpPanel.add(new JLabel("XP:"));
        xpPanel.add(xpTextField);
        xpPanel.setOpaque(false);

        nameCRPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        nameCRPanel.setPreferredSize(new Dimension(this.getWidth(), 5));
        nameCRPanel.add(namePanel);
        nameCRPanel.add(xpPanel);
        nameCRPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the size-type-alignment sub panel
    private void initializeSizeTypeAlignmentPanel() {
        JPanel sizePanel = new JPanel();
        sizePanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        sizePanel.add(new JLabel("Size:"));
        sizePanel.add(sizeComboBox);
        sizePanel.setOpaque(false);

        JPanel typePanel = new JPanel();
        typePanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        typePanel.add(new JLabel("Type:"));
        typePanel.add(typeComboBox);
        typePanel.setOpaque(false);

        JPanel alignmentPanel = new JPanel();
        alignmentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        alignmentPanel.add(new JLabel("Alignment:"));
        alignmentPanel.add(alignmentComboBox);
        alignmentPanel.setOpaque(false);

        sizeTypeAlignmentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        sizeTypeAlignmentPanel.setPreferredSize(new Dimension(this.getWidth(), 5));
        sizeTypeAlignmentPanel.add(sizePanel);
        sizeTypeAlignmentPanel.add(typePanel);
        sizeTypeAlignmentPanel.add(alignmentPanel);
        sizeTypeAlignmentPanel.setOpaque(false);
        sizeTypeAlignmentPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the hp panel
    private void initializeHPPanel() {
        initializeProficiencyPanel();

        numberTextFieldList.add(hpAmountOfDiceTextField);
        numberTextFieldList.add(hpDieSidesTextField);
        numberTextFieldList.add(hpModifierTextField);
        requiredTextFieldsList.add(hpAmountOfDiceTextField);
        requiredTextFieldsList.add(hpDieSidesTextField);
        requiredTextFieldsList.add(hpModifierTextField);

        hpPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        hpPanel.setPreferredSize(new Dimension(this.getWidth(), 5));
        hpPanel.add(new JLabel("Hit Point Formula:" + TAB));
        hpPanel.add(hpAmountOfDiceTextField);
        hpPanel.add(new JLabel("d"));
        hpPanel.add(hpDieSidesTextField);
        hpPanel.add(new JLabel("+"));
        hpPanel.add(hpModifierTextField);
        hpPanel.add(proficiencyPanel);
        hpPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the proficiency panel
    private void initializeProficiencyPanel() {
        numberTextFieldList.add(proficiencyTextField);
        requiredTextFieldsList.add(proficiencyTextField);

        proficiencyPanel.add(new JLabel("Proficiency Bonus:"));
        proficiencyPanel.add(proficiencyTextField);
        proficiencyPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the armour panel
    private void initializeArmourPanel() {
        numberTextFieldList.add(armourTextField);
        wordTextFieldList.add(armourNameTextField);
        numberTextFieldList.add(magicArmourTextField);
        requiredTextFieldsList.add(armourTextField);

        armourPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        armourPanel.setPreferredSize(new Dimension(this.getWidth(), 25));
        armourPanel.add(new JLabel("Armour Class:"));
        armourPanel.add(armourTextField);
        armourPanel.add(new JLabel("Armour Name:"));
        armourPanel.add(armourNameTextField);
        armourPanel.add(new JLabel("Magic Armour Class:"));
        armourPanel.add(magicArmourTextField);
        armourPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the speeds panel
    private void initializeSpeedsPanel() {
        numberTextFieldList.add(speedTextField);
        numberTextFieldList.add(burrowTextField);
        numberTextFieldList.add(swimTextField);
        numberTextFieldList.add(flyTextField);
        numberTextFieldList.add(climbTextField);
        requiredTextFieldsList.add(speedTextField);

        speedsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        speedsPanel.setPreferredSize(new Dimension(this.getWidth(), 5));
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
        speedsPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the senses panel
    private void initializeSensesPanel() {
        requiredTextFieldsList.add(passivePerceptionTextField);
        numberTextFieldList.add(passivePerceptionTextField);
        numberTextFieldList.add(tremorSenseTextField);
        numberTextFieldList.add(blindSightTextField);
        numberTextFieldList.add(trueSightTextField);
        numberTextFieldList.add(darkVisionTextField);

        sensesPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        sensesPanel.setPreferredSize(new Dimension(this.getWidth(), 20));
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
        sensesPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the ability scores panel
    private void initializeAbilityScoresPanel() {
        initializeStrengthPanel();
        initializeDexterityPanel();
        initializeConstitutionPanel();
        initializeIntelligencePanel();
        initializeWisdomPanel();
        initializeCharismaPanel();

        abilityScoresPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        abilityScoresPanel.setPreferredSize(new Dimension(this.getWidth(), 40));
        abilityScoresPanel.add(new JLabel("Ability Scores:" + TAB));
        abilityScoresPanel.add(strengthPanel);
        abilityScoresPanel.add(dexterityPanel);
        abilityScoresPanel.add(constitutionPanel);
        abilityScoresPanel.add(intelligencePanel);
        abilityScoresPanel.add(wisdomPanel);
        abilityScoresPanel.add(charismaPanel);
        abilityScoresPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the strength panel
    private void initializeStrengthPanel() {
        numberTextFieldList.add(strengthTextField);
        requiredTextFieldsList.add(strengthTextField);

        strengthPanel.add(new JLabel("STR:"));
        strengthPanel.add(strengthTextField);
        strengthPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the dexterity panel
    private void initializeDexterityPanel() {
        numberTextFieldList.add(dexterityTextField);
        requiredTextFieldsList.add(dexterityTextField);

        dexterityPanel.add(new JLabel("DEX:"));
        dexterityPanel.add(dexterityTextField);
        dexterityPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the constitution panel
    private void initializeConstitutionPanel() {
        numberTextFieldList.add(constitutionTextField);
        requiredTextFieldsList.add(constitutionTextField);

        constitutionPanel.add(new JLabel("CON:"));
        constitutionPanel.add(constitutionTextField);
        constitutionPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the intelligence panel
    private void initializeIntelligencePanel() {
        numberTextFieldList.add(intelligenceTextField);
        requiredTextFieldsList.add(intelligenceTextField);

        intelligencePanel.add(new JLabel("INT:"));
        intelligencePanel.add(intelligenceTextField);
        intelligencePanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the wisdom panel
    private void initializeWisdomPanel() {
        numberTextFieldList.add(wisdomTextField);
        requiredTextFieldsList.add(wisdomTextField);

        wisdomPanel.add(new JLabel("WIS:"));
        wisdomPanel.add(wisdomTextField);
        wisdomPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the charisma panel
    private void initializeCharismaPanel() {
        numberTextFieldList.add(charismaTextField);
        requiredTextFieldsList.add(charismaTextField);

        charismaPanel.add(new JLabel("CHA:"));
        charismaPanel.add(charismaTextField);
        charismaPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the peripheral fields panel
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
        peripheralFieldsPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the saving throws panel
    private void initializeSavingThrowsPanel() {
        savingThrowsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        savingThrowsPanel.setPreferredSize(new Dimension(this.getWidth(), 20));
        savingThrowsPanel.add(new JLabel("Saving Throw Proficiencies:" + TAB));
        savingThrowsPanel.add(strengthSavingThrowProficiencyCheckBox);
        savingThrowsPanel.add(dexteritySavingThrowProficiencyCheckBox);
        savingThrowsPanel.add(constitutionSavingThrowProficiencyCheckBox);
        savingThrowsPanel.add(intelligenceSavingThrowProficiencyCheckBox);
        savingThrowsPanel.add(wisdomSavingThrowProficiencyCheckBox);
        savingThrowsPanel.add(charismaSavingThrowProficiencyCheckBox);
        savingThrowsPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the skills panel
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void initializeSkillsPanel() {
        skillsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        skillsPanel.setPreferredSize(new Dimension(this.getWidth(), 80));
        skillsPanel.add(new JLabel("Skill Proficiencies:"));
        skillsPanel.add(new JLabel("Acrobatics"));
        skillsPanel.add(acrobaticsProficiencyComboBox);
        skillsPanel.add(new JLabel("Animal Handling"));
        skillsPanel.add(animalHandlingProficiencyComboBox);
        skillsPanel.add(new JLabel("Arcana"));
        skillsPanel.add(arcanaProficiencyComboBox);
        skillsPanel.add(new JLabel("Athletics"));
        skillsPanel.add(athleticsProficiencyComboBox);
        skillsPanel.add(new JLabel("Deception"));
        skillsPanel.add(deceptionProficiencyComboBox);
        skillsPanel.add(new JLabel("History"));
        skillsPanel.add(historyProficiencyComboBox);
        skillsPanel.add(new JLabel("Insight"));
        skillsPanel.add(insightProficiencyComboBox);
        skillsPanel.add(new JLabel("Intimidation"));
        skillsPanel.add(intimidationProficiencyComboBox);
        skillsPanel.add(new JLabel("Investigation"));
        skillsPanel.add(investigationProficiencyComboBox);
        skillsPanel.add(new JLabel("Medicine"));
        skillsPanel.add(medicineProficiencyComboBox);
        skillsPanel.add(new JLabel("Nature"));
        skillsPanel.add(natureProficiencyComboBox);
        skillsPanel.add(new JLabel("Perception"));
        skillsPanel.add(perceptionProficiencyComboBox);
        skillsPanel.add(new JLabel("Performance"));
        skillsPanel.add(performanceProficiencyComboBox);
        skillsPanel.add(new JLabel("Persuasion"));
        skillsPanel.add(persuasionProficiencyComboBox);
        skillsPanel.add(new JLabel("Religion"));
        skillsPanel.add(religionProficiencyComboBox);
        skillsPanel.add(new JLabel("Sleight Of Hand"));
        skillsPanel.add(sleightOfHandProficiencyComboBox);
        skillsPanel.add(new JLabel("Stealth"));
        skillsPanel.add(stealthProficiencyComboBox);
        skillsPanel.add(new JLabel("Survival"));
        skillsPanel.add(survivalProficiencyComboBox);
        skillsPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the resistances panel
    private void initializeResistancesPanel() {
        resistancesPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        resistancesPanel.setPreferredSize(new Dimension(this.getWidth(), 115));
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
        resistancesPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the 2nd half of the resistances panel
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

    // MODIFIES: this
    // EFFECTS: formats the immunities panel
    private void initializeImmunitiesPanel() {
        immunitiesPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        immunitiesPanel.setPreferredSize(new Dimension(this.getWidth(), 70));
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
        immunitiesPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the language creation panel
    private void initializeLanguageCreationPanel() {
        numberTextFieldList.add(telepathyTextField);
        wordTextFieldList.add(languageTextField);

        languageCreationPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        languageCreationPanel.setPreferredSize(new Dimension(this.getWidth(), 5));
        languageCreationPanel.add(new JLabel("Language:"));
        languageCreationPanel.add(languageTextField);
        languageCreationPanel.add(addLanguageButton);
        languageCreationPanel.add(new JLabel("Telepathy Range:"));
        languageCreationPanel.add(telepathyTextField);
        languageCreationPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the ability creation panel
    private void initializeAbilityCreationPanel() {
        wordTextFieldList.add(abilityNameTextField);

        JPanel setNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        setNamePanel.add(addAbilityButton);
        setNamePanel.add(new JLabel("Ability Name:"));
        setNamePanel.add(abilityNameTextField);
        setNamePanel.add(new JLabel("Ability Description:" + TAB));
        setNamePanel.setOpaque(false);

        abilityCreationPanel.setLayout(new BoxLayout(abilityCreationPanel, BoxLayout.Y_AXIS));
        abilityCreationPanel.setPreferredSize(new Dimension(this.getWidth(), 5));
        abilityCreationPanel.add(setNamePanel);
        abilityCreationPanel.add(abilityDescriptionScrollPane);
        abilityCreationPanel.add(new JLabel("Abilities:"));
        abilityCreationPanel.add(abilitiesScrollPane);
        abilityCreationPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the action creation panel
    private void initializeActionCreationPanel() {
        wordTextFieldList.add(actionNameTextField);
        numberTextFieldList.add(actionRangeTextField);
        numberTextFieldList.add(actionLongRangeTextField);

        JPanel nameRangePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        nameRangePanel.setPreferredSize(new Dimension(this.getWidth(), 15));
        nameRangePanel.add(addActionButton);
        nameRangePanel.add(new JLabel("Name:"));
        nameRangePanel.add(actionNameTextField);
        nameRangePanel.add(new JLabel("Range:"));
        nameRangePanel.add(actionRangeTextField);
        nameRangePanel.add(new JLabel("Long Range (Optional):"));
        nameRangePanel.add(actionLongRangeTextField);
        nameRangePanel.setOpaque(false);

        initializeActionHitDescriptionPanel();
        initializeActionAddDamageEntryPanel();

        actionCreationPanel.setLayout(new BoxLayout(actionCreationPanel, BoxLayout.Y_AXIS));
        actionCreationPanel.add(nameRangePanel);
        actionCreationPanel.add(hitDescriptionPanel);
        actionCreationPanel.add(addDamageEntryPanel);
        actionCreationPanel.add(new JLabel("Damage Rolls for this Action:"));
        actionCreationPanel.add(actionDamageScrollPane);
        actionCreationPanel.add(new JLabel("Actions:"));
        actionCreationPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the action hit-description panel
    private void initializeActionHitDescriptionPanel() {
        numberTextFieldList.add(actionHitModifierTextField);

        hitDescriptionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        hitDescriptionPanel.setPreferredSize(new Dimension(this.getWidth(), 5));
        hitDescriptionPanel.add(new JLabel("Hit Modifier:"));
        hitDescriptionPanel.add(actionHitModifierTextField);
        hitDescriptionPanel.add(new JLabel("Description:"));
        hitDescriptionPanel.add(actionDescriptionComboBox);
        hitDescriptionPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the action damage panel
    private void initializeActionAddDamageEntryPanel() {
        numberTextFieldList.add(actionDamageAmountOfDiceTextField);
        numberTextFieldList.add(actionDamageDieSidesTextField);
        numberTextFieldList.add(actionDamageModifierTextField);

        addDamageEntryPanel.setLayout(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        addDamageEntryPanel.setPreferredSize(new Dimension(this.getWidth(), 10));
        addDamageEntryPanel.add(addActionDamageButton);
        addDamageEntryPanel.add(new JLabel("Damage:"));
        addDamageEntryPanel.add(actionDamageAmountOfDiceTextField);
        addDamageEntryPanel.add(new JLabel("d"));
        addDamageEntryPanel.add(actionDamageDieSidesTextField);
        addDamageEntryPanel.add(new JLabel("+"));
        addDamageEntryPanel.add(actionDamageModifierTextField);
        addDamageEntryPanel.add(new JLabel("Type:"));
        addDamageEntryPanel.add(actionDamageTypeComboBox);
        addDamageEntryPanel.setOpaque(false);
    }

    // MODIFIES: this
    // EFFECTS: formats the legendary creation panel
    private void initializeLegendaryCreationPanel() {
        wordTextFieldList.add(legendaryActionNameTextField);
        wordTextFieldList.add(legendaryActionDescriptionTextField);

        JPanel legendaryActionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, FLOW_H_GAP, FLOW_V_GAP));
        legendaryActionPanel.add(addLegendaryActionButton);
        legendaryActionPanel.add(new JLabel("Legendary Action Name:"));
        legendaryActionPanel.add(legendaryActionNameTextField);
        legendaryActionPanel.add(new JLabel("Legendary Action Description:"));
        legendaryActionPanel.setOpaque(false);

        legendaryCreationPanel.setLayout(new BoxLayout(legendaryCreationPanel, BoxLayout.Y_AXIS));
        legendaryCreationPanel.setPreferredSize(new Dimension(this.getWidth(), 50));
        legendaryCreationPanel.add(new JLabel("Legendary Description:"));
        legendaryCreationPanel.add(legendaryDescriptionScrollPane);
        legendaryCreationPanel.add(legendaryActionPanel);
        legendaryCreationPanel.add(legendaryActionDescriptionScrollPane);
        legendaryCreationPanel.add(new JLabel("Legendary Actions:"));
        legendaryCreationPanel.add(legendaryActionsScrollPane);
        legendaryCreationPanel.setOpaque(false);
    }

    // MODIFIES: mainMenuPanel, this
    // EFFECTS: tries to create a statblock from the given fields then reset to the library menu;
    //          and creates an error message if any exceptions are caught
    private void tryCreatingStatBlock() {
        try {
            parseStatBlockSingleFields();

            StatBlock statBlock = new StatBlock.StatBlockBuilder(getStatBlockTitle(), parsedXP,
                    new RollFormula(parsedHPAmountOfDice, parsedHPDieSides, parsedHPModifier), parsedProficiency,
                    getArmour(), getSpeeds(), getSenses(), getAbilityScores())
                    .rollableActions(getRollableActions())
                    .skillProficiencies(getSkillProficiencies())
                    .savingThrowProficiencies(getSavingThrowProficiencies())
                    .languages(getLanguages())
                    .conditionImmunities(getConditionImmunities())
                    .resistances(getResistances())
                    .legendaryMechanics(getLegendaryMechanics())
                    .build();

            mainMenuPanel.getMenuManagerPanel().getStatBlockLibrary().addElement(statBlock);
            this.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not build StatBlock: "
                    + e.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // REQUIRES: StatBlock single fields are not empty
    // EFFECTS: parses the single StatBlock fields into integers and assigns them
    private void parseStatBlockSingleFields() throws IncompleteFieldException {
        try {
            parsedXP = Integer.parseInt(xpTextField.getText());
            parsedHPAmountOfDice = Integer.parseInt(hpAmountOfDiceTextField.getText());
            parsedHPDieSides = Integer.parseInt(hpDieSidesTextField.getText());
            parsedHPModifier = Integer.parseInt(hpModifierTextField.getText());
            parsedProficiency = Integer.parseInt(proficiencyTextField.getText());
        } catch (NumberFormatException e) {
            throw new IncompleteFieldException("Required single field (xp, hp formula, or proficiency) "
                    + "is empty or invalid.");
        }
    }

    // EFFECTS: returns a title from the given user fields and boxes but if any numbers are in the title or any
    //          other exceptions are thrown, it throws an exception
    private Title getStatBlockTitle() throws IncompleteFieldException, IndexOutOfBoundsException {
        if (nameTextField.getText().matches(".*\\d+.*")) {
            throw new IndexOutOfBoundsException("title fields cannot contain numbers");
        }
        try {
            return new Title(nameTextField.getText(),
                    Objects.requireNonNull(typeComboBox.getSelectedItem()).toString(),
                    Objects.requireNonNull(sizeComboBox.getSelectedItem()).toString(),
                    Objects.requireNonNull(alignmentComboBox.getSelectedItem()).toString());
        } catch (IncompleteFieldException e) {
            String newMessage = "TITLE: " + e.getMessage();
            throw new IncompleteFieldException(newMessage);
        }
    }

    // EFFECTS: returns an armour from the given user fields and throws an exception if any issues are found
    private Armour getArmour() throws NumberFormatException, IncompleteFieldException {
        String armourName = armourNameTextField.getText();
        int magicArmour = 0;

        try {
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
        } catch (NumberFormatException e) {
            String newMessage = "ARMOR: " + e.getMessage();
            throw new NumberFormatException(newMessage);
        } catch (IncompleteFieldException e) {
            String newMessage = "ARMOR: " + e.getMessage();
            throw new IncompleteFieldException(newMessage);
        }
    }

    // EFFECTS: returns a speeds from the given user fields and throws an exception if any issues are found
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private Speeds getSpeeds() throws NumberFormatException, IndexOutOfBoundsException {
        int fly = 0;
        int swim = 0;
        int burrow = 0;
        int climb = 0;

        try {
            if (!flyTextField.getText().isEmpty()) {
                fly = Integer.parseInt(flyTextField.getText());
            }

            if (!swimTextField.getText().isEmpty()) {
                swim = Integer.parseInt(swimTextField.getText());
            }

            if (!burrowTextField.getText().isEmpty()) {
                burrow = Integer.parseInt(burrowTextField.getText());
            }

            if (!climbTextField.getText().isEmpty()) {
                climb = Integer.parseInt(climbTextField.getText());
            }
        } catch (NumberFormatException e) {
            String newMessage = "SPEEDS: " + e.getMessage();
            throw new NumberFormatException(newMessage);
        }

        return new Speeds.SpeedsBuilder(Integer.parseInt(speedTextField.getText()))
                .swim(swim)
                .fly(fly)
                .burrow(burrow)
                .climb(climb)
                .build();
    }

    // EFFECTS: returns a senses from the given user fields and throws an exception if any issues are found
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private Senses getSenses() throws NumberFormatException, IndexOutOfBoundsException {
        int tremorSense = 0;
        int trueSight = 0;
        int blindSight = 0;
        int darkVision = 0;

        try {
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
        } catch (NumberFormatException e) {
            String newMessage = "SENSES: " + e.getMessage();
            throw new NumberFormatException(newMessage);
        }

        return new Senses.SensesBuilder(Integer.parseInt(passivePerceptionTextField.getText()))
                .tremorSense(tremorSense)
                .trueSight(trueSight)
                .blindSight(blindSight)
                .darkVision(darkVision)
                .build();
    }

    // EFFECTS: returns an ability score set from the given user fields and throws an exception if any issues are found
    private AbilityScoreSet getAbilityScores() throws NumberFormatException, IndexOutOfBoundsException {
        try {
            return new AbilityScoreSet(Integer.parseInt(strengthTextField.getText()),
                    Integer.parseInt(dexterityTextField.getText()),
                    Integer.parseInt(constitutionTextField.getText()),
                    Integer.parseInt(intelligenceTextField.getText()),
                    Integer.parseInt(wisdomTextField.getText()),
                    Integer.parseInt(charismaTextField.getText()));
        } catch (NumberFormatException e) {
            String newMessage = "ABILITIES: " + e.getMessage();
            throw new NumberFormatException(newMessage);
        } catch (IndexOutOfBoundsException e) {
            String newMessage = "ABILITIES: " + e.getMessage();
            throw new IndexOutOfBoundsException(newMessage);
        }
    }

    // EFFECTS: returns all rollable actions from the given user fields and throws an exception if any issues are found
    private ArrayList<RollableAction> getRollableActions() {
        ArrayList<RollableAction> rollableActions = new ArrayList<>();

        for (int i = 0; i < rollableActionsListModel.getSize(); i++) {
            rollableActions.add(rollableActionsListModel.getElementAt(i));
        }

        return rollableActions;
    }

    // EFFECTS: returns all saving throws from the given user fields and throws an exception if any issues are found
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

    // EFFECTS: returns all skill proficiencies from the given user fields and throws an exception if any issues are
    //          found TODO exception
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private HashMap<Skill, Integer> getSkillProficiencies() {
        HashMap<Skill, Integer> skills = new HashMap<>();

        if (acrobaticsProficiencyComboBox.getSelectedIndex() > 0) {
            skills.put(Skill.ACROBATICS, acrobaticsProficiencyComboBox.getSelectedIndex());
        }

        if (animalHandlingProficiencyComboBox.getSelectedIndex() > 0) {
            skills.put(Skill.ANIMAL_HANDLING, animalHandlingProficiencyComboBox.getSelectedIndex());
        }

        if (arcanaProficiencyComboBox.getSelectedIndex() > 0) {
            skills.put(Skill.ARCANA, arcanaProficiencyComboBox.getSelectedIndex());
        }

        if (athleticsProficiencyComboBox.getSelectedIndex() > 0) {
            skills.put(Skill.ATHLETICS, athleticsProficiencyComboBox.getSelectedIndex());
        }

        if (deceptionProficiencyComboBox.getSelectedIndex() > 0) {
            skills.put(Skill.DECEPTION, deceptionProficiencyComboBox.getSelectedIndex());
        }

        if (historyProficiencyComboBox.getSelectedIndex() > 0) {
            skills.put(Skill.HISTORY, historyProficiencyComboBox.getSelectedIndex());
        }

        if (insightProficiencyComboBox.getSelectedIndex() > 0) {
            skills.put(Skill.INSIGHT, insightProficiencyComboBox.getSelectedIndex());
        }

        if (intimidationProficiencyComboBox.getSelectedIndex() > 0) {
            skills.put(Skill.INTIMIDATION, intimidationProficiencyComboBox.getSelectedIndex());
        }

        if (investigationProficiencyComboBox.getSelectedIndex() > 0) {
            skills.put(Skill.INVESTIGATION, investigationProficiencyComboBox.getSelectedIndex());
        }

        if (medicineProficiencyComboBox.getSelectedIndex() > 0) {
            skills.put(Skill.MEDICINE, medicineProficiencyComboBox.getSelectedIndex());
        }

        if (natureProficiencyComboBox.getSelectedIndex() > 0) {
            skills.put(Skill.NATURE, natureProficiencyComboBox.getSelectedIndex());
        }

        if (perceptionProficiencyComboBox.getSelectedIndex() > 0) {
            skills.put(Skill.PERCEPTION, perceptionProficiencyComboBox.getSelectedIndex());
        }

        if (performanceProficiencyComboBox.getSelectedIndex() > 0) {
            skills.put(Skill.PERFORMANCE, performanceProficiencyComboBox.getSelectedIndex());
        }

        if (persuasionProficiencyComboBox.getSelectedIndex() > 0) {
            skills.put(Skill.PERSUASION, persuasionProficiencyComboBox.getSelectedIndex());
        }

        if (religionProficiencyComboBox.getSelectedIndex() > 0) {
            skills.put(Skill.RELIGION, religionProficiencyComboBox.getSelectedIndex());
        }

        if (sleightOfHandProficiencyComboBox.getSelectedIndex() > 0) {
            skills.put(Skill.SLEIGHT_OF_HAND, sleightOfHandProficiencyComboBox.getSelectedIndex());
        }

        if (stealthProficiencyComboBox.getSelectedIndex() > 0) {
            skills.put(Skill.STEALTH, stealthProficiencyComboBox.getSelectedIndex());
        }

        if (survivalProficiencyComboBox.getSelectedIndex() > 0) {
            skills.put(Skill.SURVIVAL, survivalProficiencyComboBox.getSelectedIndex());
        }

        return skills;
    }

    // EFFECTS: returns all immunities from the given user fields and throws an exception if any issues are found
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

    // EFFECTS: returns all resistances from the given user fields and throws an exception if any issues are found
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
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

        for (ResistanceType rt : ResistanceType.values()) {
            if (Objects.requireNonNull(thunderResistanceComboBox.getSelectedItem()).toString()
                    .equalsIgnoreCase(rt.toString())) {
                resistances.put(DamageType.THUNDER, rt);
            }
        }

        return resistances;
    }

    // EFFECTS: returns a languages from the given user fields and throws an exception if any issues are found
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

    // EFFECTS: returns a legendary mechanics from the given user fields and throws an exception if any issues are found
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

    // MODIFIES: this
    // EFFECTS: tries to add the text in the language field to the list of languages,
    //          if successful clears the text field; if failure sends an error message
    private void tryAddLanguage() {
        try {
            languagesListModel.addElement(languageTextField.getText());
            languageTextField.setText(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not add language. Message: "
                    + e.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: tries to add the ability in the appropriate text fields to the list of abilities,
    //          if successful clears the text fields; if failure sends an error message
    private void tryAddAbility() {
        try {
            Ability newAbility = new Ability(abilityNameTextField.getText(), abilityDescriptionTextField.getText());
            abilitiesListModel.addElement(newAbility);
            abilityNameTextField.setText(null);
            abilityDescriptionTextField.setText(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not add ability. Message: "
                    + e.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: tries to add the action damage in the appropriate text fields and box to the list of damage,
    //          if successful clears the text fields; if failure sends an error message
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
            actionDamageAmountOfDiceTextField.setText(null);
            actionDamageDieSidesTextField.setText(null);
            actionDamageModifierTextField.setText(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "NumberFormatException caught. Message: "
                    + e.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: tries to add the action in the appropriate text fields to the list of actions,
    //          if successful clears the text fields and damage list; if failure sends an error message
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
            resetRollableActionFields();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not add action. Message: "
                    + e.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: clears all fields relating to making a new action
    private void resetRollableActionFields() {
        actionNameTextField.setText(null);
        actionHitModifierTextField.setText(null);
        actionRangeTextField.setText(null);
        actionLongRangeTextField.setText(null);
        actionDamageMapListModel.removeAllElements();
    }

    // MODIFIES: this
    // EFFECTS: tries to add the legendary action in the appropriate text fields to the list of leg' actions,
    //          if successful clears the text fields; if failure sends an error message
    private void tryAddLegendaryAction() {
        try {
            legendaryActionsListModel.addElement(new Ability(legendaryActionNameTextField.getText(),
                    legendaryActionDescriptionTextField.getText()));
            legendaryActionNameTextField.setText(null);
            legendaryActionDescriptionTextField.setText(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not add legendary action. Message: "
                    + e.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    // MODIFIES: this
    // EFFECTS: processes this panel's button presses
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            this.dispose();
        } else if (e.getSource() == finishButton) {
            tryCreatingStatBlock();
        } else if (e.getSource() == deleteButton) {
            deleteSelection();
        } else if (e.getSource() == addLanguageButton) {
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
        clearSelections();
    }

    // MODIFIES: this
    // EFFECTS: deletes whatever is selected from this panel's jlists
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

    // MODIFIES: this
    // EFFECTS: clears all selections for all jlists in this panel
    private void clearSelections() {
        languageList.clearSelection();
        abilitiesList.clearSelection();
        actionsList.clearSelection();
        actionDamageMapList.clearSelection();
        legendaryActionsList.clearSelection();
    }

    @Override
    // MODIFIES: this
    // EFFECTS: disables buttons for jlists when the jlist selection is changing
    public void valueChanged(ListSelectionEvent e) {
        deleteButton.setEnabled(!e.getValueIsAdjusting());
        addLanguageButton.setEnabled(!e.getValueIsAdjusting());
        addAbilityButton.setEnabled(!e.getValueIsAdjusting());
        addActionDamageButton.setEnabled(!e.getValueIsAdjusting());
        addActionButton.setEnabled(!e.getValueIsAdjusting());
        addLegendaryActionButton.setEnabled(!e.getValueIsAdjusting());
    }
}
