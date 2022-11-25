package ui.panels.menus;

import model.Character;
import model.StatBlock;
import ui.panels.MainDisplayPanel;
import ui.panels.SideDisplayPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Represents...
public class MainMenuPanel extends MenuPanel implements ActionListener {
    // selections
    private model.Character selectedCharacter;
    private StatBlock selectedStatBlock;
    private String selectedGroup;

    // images
    private static final ImageIcon RETURN_ICON = new ImageIcon(ICON_DIRECTORY + "return.png");

    // labels
    private final JLabel encounterTitleLabel = new JLabel("Main Menu: Encounter Loaded");
    private final JLabel libraryTitleLabel = new JLabel("Main Menu: Library Loaded");
    private final JLabel encounterBanner = new JLabel(new ImageIcon("./data/images/banner1"));
    private final JLabel libraryBanner = new JLabel(new ImageIcon("./data/images/banner2"));

    // layouts
    private final CardLayout titleCardLayout = new CardLayout();
    private final CardLayout buttonCardLayout = new CardLayout();

    // panels
    private final MainDisplayPanel mainDisplayPanel;
    private final SideDisplayPanel sideDisplayPanel;

    //private final JPanel titlePanel = new JPanel(titleCardLayout);
    private final JPanel titlePanel = new JPanel();
    private final JPanel buttonPanel = new JPanel(buttonCardLayout);

    private final JPanel encounterButtonPanel = new JPanel(new GridLayout(3, 1));
    private final JPanel encounterButtonSubPanel0 = new JPanel(new GridLayout(1, 2));
    private final JPanel encounterButtonSubPanel1 = new JPanel(new GridLayout(1, 3));
    private final JPanel encounterButtonSubPanel2 = new JPanel(new GridLayout(1, 3));

    private final JPanel libraryButtonPanel = new JPanel(new GridLayout(2, 1));
    private final JPanel characterButtonPanel = new JPanel(new GridLayout(2, 1));
    private final JPanel groupButtonPanel = new JPanel(new GridLayout(3, 1));

    // buttons
    private final JButton backButton = new JButton("Back");

    private final JButton addStatBlocksToEncounterButton = new JButton("Add to Encounter");
    private final JButton openStatBlockButton = new JButton("Open");
    private final JButton createNewStatBlockButton = new JButton("Create New");
    private final JButton deleteStatBlocksButton = new JButton("Delete");

    private final JButton openCharacterButton = new JButton("Open");
    private final JButton openGroupButton = new JButton("Open Character's Group");
    private final JButton rollCheckButton = new JButton("Roll Ability Check");
    private final JButton rollInitiativeButton = new JButton("Roll Initiative");
    private final JButton changeHPButton = new JButton("Change HP");
    private final JButton setCharacterGroupButton = new JButton("Change Group");
    private final JButton deleteCharacterButton = new JButton("Delete");

    // EFFECTS: constructs this panel
    public MainMenuPanel(MenuManagerPanel menuManagerPanel) {
        super(new BorderLayout(), menuManagerPanel); // sets layout and menu managers, visibility, and size

        initializeButtons();
        initializeButtonPanel();

        this.mainDisplayPanel = new MainDisplayPanel(this);
        this.sideDisplayPanel = new SideDisplayPanel(this);

//        titlePanel.add(libraryBanner, "library");
//        titlePanel.add(encounterBanner, "encounter");
        titlePanel.setBackground(Color.BLUE);
        titlePanel.setPreferredSize(new Dimension(1080, 144));

        JPanel displaysPanel = new JPanel(new GridLayout(1, 2));
        displaysPanel.add(mainDisplayPanel);
        displaysPanel.add(sideDisplayPanel);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(displaysPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    // EFFECTS: ...
    private void initializeButtons() {
        ArrayList<JButton> buttonList = new ArrayList<>();

        buttonList.add(backButton);
        buttonList.add(addStatBlocksToEncounterButton);
        buttonList.add(openStatBlockButton);
        buttonList.add(createNewStatBlockButton);
        buttonList.add(deleteStatBlocksButton);
        buttonList.add(openCharacterButton);
        buttonList.add(openGroupButton);
        buttonList.add(rollCheckButton);
        buttonList.add(rollInitiativeButton);
        buttonList.add(changeHPButton);
        buttonList.add(setCharacterGroupButton);
        buttonList.add(deleteCharacterButton);

        for (JButton jb : buttonList) {
            jb.addActionListener(this);
        }
//        initializeButtonIcons();
    }

//    // EFFECTS: ...
//    private void initializeButtonIcons() {
//        backButton.setIcon();
//        addStatBlocksToEncounterButton.setIcon();
//        openStatBlockButton.setIcon();
//        createNewStatBlockButton.setIcon();
//        deleteStatBlocksButton.setIcon();
//        openCharacterButton.setIcon();
//        openGroupButton.setIcon();
//        rollCheckButton.setIcon();
//        rollInitiativeButton.setIcon();
//        changeHPButton.setIcon();
//        editCharacterGroupButton.setIcon();
//        deleteCharacterButton.setIcon();
//    }

    // EFFECTS: ...
    protected void setState(String s) {
        buttonCardLayout.show(buttonPanel, s);
        mainDisplayPanel.setMainDisplay(s);
        switch (s) {
            case "library":
                //titleCardLayout.show(titlePanel, "library");
                initializeLibraryButtonPanel();
                sideDisplayPanel.setSideDisplay("library");
                break;
            case "group":
                initializeGroupButtonPanel();
                break;
            case "character":
                initializeCharacterButtonPanel();
                break;
            default:
                //titleCardLayout.show(titlePanel, "encounter");
                initializeEncounterButtonPanel();
                sideDisplayPanel.setSideDisplay("encounter");
                break;
        }
    }

    // EFFECTS: ...
    private void initializeButtonPanel() {
        buttonPanel.add(libraryButtonPanel, "library");
        buttonPanel.add(encounterButtonPanel, "encounter");
        buttonPanel.add(characterButtonPanel, "character");
        buttonPanel.add(groupButtonPanel, "group");
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

        libraryButtonPanel.add(libraryButtonSubPanel0);
        libraryButtonPanel.add(libraryButtonSubPanel1);
    }

    // EFFECTS: ...
    private void initializeEncounterButtonPanel() {
        encounterButtonSubPanel0.add(openGroupButton);
        encounterButtonSubPanel0.add(openCharacterButton);

        encounterButtonSubPanel1.add(rollCheckButton);
        encounterButtonSubPanel1.add(rollInitiativeButton);
        encounterButtonSubPanel1.add(changeHPButton);

        encounterButtonSubPanel2.add(setCharacterGroupButton);
        encounterButtonSubPanel2.add(deleteCharacterButton);
        encounterButtonSubPanel2.add(backButton);

        encounterButtonPanel.add(encounterButtonSubPanel0);
        encounterButtonPanel.add(encounterButtonSubPanel1);
        encounterButtonPanel.add(encounterButtonSubPanel2);
    }

    // EFFECTS: ...
    private void initializeCharacterButtonPanel() {
        characterButtonPanel.add(encounterButtonSubPanel1);
        characterButtonPanel.add(encounterButtonSubPanel2);
    }

    // EFFECTS: ...
    private void initializeGroupButtonPanel() {
        groupButtonPanel.add(openCharacterButton);
        groupButtonPanel.add(encounterButtonSubPanel1);
        groupButtonPanel.add(encounterButtonSubPanel2);
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

    // EFFECTS: gets the main display panel
    public MainDisplayPanel getMainDisplayPanel() {
        return mainDisplayPanel;
    }

    // EFFECTS: gets the side display panel
    public SideDisplayPanel getSideDisplayPanel() {
        return sideDisplayPanel;
    }

    // EFFECTS: gets...
    public JButton getBackButton() {
        return backButton;
    }

    // EFFECTS: gets...
    public JButton getAddStatBlocksToEncounterButton() {
        return addStatBlocksToEncounterButton;
    }

    // EFFECTS: gets...
    public JButton getOpenStatBlockButton() {
        return openStatBlockButton;
    }

    // EFFECTS: gets...
    public JButton getCreateNewStatBlockButton() {
        return createNewStatBlockButton;
    }

    // EFFECTS: gets...
    public JButton getDeleteStatBlocksButton() {
        return deleteStatBlocksButton;
    }

    // EFFECTS: gets...
    public JButton getOpenCharacterButton() {
        return openCharacterButton;
    }

    // EFFECTS: gets...
    public JButton getOpenGroupButton() {
        return openGroupButton;
    }

    // EFFECTS: gets...
    public JButton getRollCheckButton() {
        return rollCheckButton;
    }

    // EFFECTS: gets...
    public JButton getRollInitiativeButton() {
        return rollInitiativeButton;
    }

    // EFFECTS: gets...
    public JButton getChangeHPButton() {
        return changeHPButton;
    }

    // EFFECTS: gets...
    public JButton getSetCharacterGroupButton() {
        return setCharacterGroupButton;
    }

    // EFFECTS: gets...
    public JButton getDeleteCharacterButton() {
        return deleteCharacterButton;
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

    // -----------------------------------------------------------------------
    // overrides
    @Override
    // EFFECTS: processes button presses from user
    public void actionPerformed(ActionEvent e) {
        mainDisplayPanel.passAction(e);
        sideDisplayPanel.passAction(e);
    }
}
