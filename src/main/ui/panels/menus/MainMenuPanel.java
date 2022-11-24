package ui.panels.menus;

import model.Character;
import model.StatBlock;
import ui.panels.listpanels.EncounterListPanel;

import javax.swing.*;
import java.awt.*;

// Represents...
public class MainMenuPanel extends MenuPanel {
    // selections
    private model.Character selectedCharacter;
    private StatBlock selectedStatBlock;
    private String selectedGroup;

    // images

    // labels
    private JLabel mainDisplayTitleLabel;
    private JLabel rollOutputLogTitleLabel;
    private JPanel statBlockTitleLabel;

    // panels
    private JPanel titlePanel;
    private JPanel mainPanel;
    private JPanel mainButtonPanel;
    private JPanel scrollPanesTitlePanel;
    private JPanel scrollPanesPanel;
    private JPanel sideDisplayTitlePanel;
    private JPanel rollOutputLogTitlePanel;
    private JPanel mainDisplayPanel;
    private JPanel libraryButtonPanel;
    private JPanel encounterButtonPanel;
    private JPanel characterButtonPanel;
    private JPanel groupButtonPanel;

    private JPanel encounterButtonSubPanel1;
    private JPanel encounterButtonSubPanel2;

    // buttons
    private final JButton backButton = new JButton("Back");

    private final JButton customRollButton = new JButton("Custom Dice Roller");
    private final JButton addStatBlocksToEncounterButton = new JButton("Add StatBlock(s) to Encounter");
    private final JButton openStatBlockButton = new JButton("Open StatBlock");
    private final JButton createNewStatBlockButton = new JButton("Create New StatBlock");
    private final JButton deleteStatBlocksButton = new JButton("Delete StatBlock(s)");

    private final JButton openCharacterButton = new JButton("Open Character's Sheet");
    private final JButton openGroupButton = new JButton("Open Character's Group");
    private final JButton rollCheckButton = new JButton("Roll Ability Check for StatBlock(s)");
    private final JButton rollInitiativeButton = new JButton("Roll Initiative for StatBlock(s)");
    private final JButton changeHPButton = new JButton("Change HP for StatBlock(s)");
    private final JButton editCharacterGroupButton = new JButton("Change Group for StatBlock(s)");
    private final JButton deleteCharacterButton = new JButton("Back");

    // EFFECTS: constructs this panel
    public MainMenuPanel(MenuManagerPanel menuManagerPanel) {
        super(new BorderLayout(), menuManagerPanel); // sets layout and menu managers, visibility, and size

        initializeButtons();
        initializeTitlePanel();
        initializeMainPanel();

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    // EFFECTS: ...
    private void initializeTitlePanel() {
        //stub
    }

    // EFFECTS: ...
    private void initializeMainPanel() {
        initializeScrollPanesTitlePanel();
        initializeScrollPanesPanel();
        initializeMainButtonPanel();

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPanesTitlePanel, BorderLayout.NORTH);
        mainPanel.add(scrollPanesPanel, BorderLayout.CENTER);
        mainPanel.add(mainButtonPanel, BorderLayout.SOUTH);
    }

    // EFFECTS: ...
    private void initializeScrollPanesTitlePanel() {
        initializeSideDisplayTitlePanel();

        scrollPanesTitlePanel = new JPanel(new GridLayout(2, 1));
        scrollPanesTitlePanel.add(mainDisplayTitleLabel);
        scrollPanesTitlePanel.add(sideDisplayTitlePanel);
    }

    // EFFECTS: ...
    private void initializeSideDisplayTitlePanel() {
        initializeRollOutputLogTitlePanel();

        sideDisplayTitlePanel = new JPanel(new CardLayout());
        sideDisplayTitlePanel.add(rollOutputLogTitlePanel, "rollOutputLog");
        sideDisplayTitlePanel.add(statBlockTitleLabel, "statBlock");
    }

    // EFFECTS: ...
    private void initializeRollOutputLogTitlePanel() {
        rollOutputLogTitlePanel = new JPanel(new GridLayout(1, 2));
        scrollPanesPanel.add(rollOutputLogTitleLabel);
        scrollPanesPanel.add(customRollButton);
    }

    // EFFECTS: ...
    private void initializeScrollPanesPanel() {
        initializeMainDisplayPanel();

        scrollPanesPanel = new JPanel(new GridLayout(1, 2));
        scrollPanesPanel.add(mainDisplayPanel);
        scrollPanesPanel.add(new SidePanel(this.menuManagerPanel));
    }

    // EFFECTS: ...
    private void initializeMainDisplayPanel() {
        mainDisplayPanel = new JPanel(new CardLayout());
        mainDisplayPanel.add(new LibraryListPanel(this.menuManagerPanel), "library");
        mainDisplayPanel.add(new EncounterListPanel(this.menuManagerPanel), "encounter");
        mainDisplayPanel.add(new GroupListPanel(this.menuManagerPanel), "group");
        mainDisplayPanel.add(new CharacterPanel(this.menuManagerPanel), "character");
    }

    // EFFECTS: ...
    private void initializeMainButtonPanel() {
        initializeLibraryButtonPanel();
        initializeEncounterButtonPanel();
        initializeCharacterButtonPanel();
        initializeGroupButtonPanel();

        mainButtonPanel = new JPanel(new CardLayout());
        mainButtonPanel.add(libraryButtonPanel, "library");
        mainButtonPanel.add(encounterButtonPanel, "encounter");
        mainButtonPanel.add(characterButtonPanel, "character");
        mainButtonPanel.add(groupButtonPanel, "group");
    }

    // EFFECTS: ...
    private void initializeLibraryButtonPanel() {
        JPanel libraryButtonSubPanel0 = new JPanel(new GridLayout(1, 2));
        libraryButtonSubPanel0.add(addStatBlocksToEncounterButton);
        libraryButtonSubPanel0.add(openStatBlockButton);

        JPanel libraryButtonSubPanel1 = new JPanel(new GridLayout(1, 3));
        libraryButtonSubPanel1.add(createNewStatBlockButton);
        libraryButtonSubPanel1.add(deleteStatBlocksButton);
        libraryButtonSubPanel1.add(backButton);

        libraryButtonPanel = new JPanel(new GridLayout(2, 1));
        libraryButtonPanel.add(libraryButtonSubPanel0);
        libraryButtonPanel.add(libraryButtonSubPanel1);
    }

    // EFFECTS: ...
    private void initializeEncounterButtonPanel() {
        JPanel encounterButtonSubPanel0 = new JPanel(new GridLayout(1, 2));
        encounterButtonSubPanel0.add(openCharacterButton);
        encounterButtonSubPanel0.add(openGroupButton);

        encounterButtonSubPanel1 = new JPanel(new GridLayout(1, 3));
        encounterButtonSubPanel1.add(rollCheckButton);
        encounterButtonSubPanel1.add(rollInitiativeButton);
        encounterButtonSubPanel1.add(changeHPButton);

        encounterButtonSubPanel2 = new JPanel(new GridLayout(1, 3));
        encounterButtonSubPanel2.add(editCharacterGroupButton);
        encounterButtonSubPanel2.add(deleteCharacterButton);
        encounterButtonSubPanel2.add(backButton);

        encounterButtonPanel = new JPanel(new GridLayout(3, 1));
        encounterButtonPanel.add(encounterButtonSubPanel0);
        encounterButtonPanel.add(encounterButtonSubPanel1);
        encounterButtonPanel.add(encounterButtonSubPanel2);
    }

    // EFFECTS: ...
    private void initializeCharacterButtonPanel() {
        characterButtonPanel = new JPanel(new GridLayout(2, 1));
        characterButtonPanel.add(encounterButtonSubPanel1);
        characterButtonPanel.add(encounterButtonSubPanel2);
    }

    // EFFECTS: ...
    private void initializeGroupButtonPanel() {
        groupButtonPanel = new JPanel(new GridLayout(3, 1));
        groupButtonPanel.add(openCharacterButton);
        groupButtonPanel.add(encounterButtonSubPanel1);
        groupButtonPanel.add(encounterButtonSubPanel2);
    }

    // EFFECTS: ...
    private void initializeButtons() {
        //stub
    }

    // -----------------------------------------------------------------------
    // getters
    // EFFECTS: gets the selected character
    public model.Character getSelectedCharacter() {
        return selectedCharacter;
    }

    // EFFECTS: gets the selected statblock
    public StatBlock getSelectedStatBlock() {
        return selectedStatBlock;
    }

    // EFFECTS: gets the selected group name
    public String getSelectedGroup() {
        return selectedGroup;
    }

    // -----------------------------------------------------------------------
    // setters
    // EFFECTS: sets the selected character
    public void setSelectedCharacter(Character selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
    }

    // EFFECTS: sets the selected statblock
    public void setSelectedStatBlock(StatBlock selectedStatBlock) {
        this.selectedStatBlock = selectedStatBlock;
    }

    // EFFECTS: sets the selected group name
    public void setSelectedGroup(String selectedGroup) {
        this.selectedGroup = selectedGroup;
    }
}
