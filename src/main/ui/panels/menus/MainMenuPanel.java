package ui.panels.menus;

import ui.panels.BannerPanel;
import ui.panels.MainDisplayPanel;
import ui.panels.SideDisplayPanel;
import ui.panels.StatBlockCreationDisplayPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Represents a main menu panel that manages switching between the title menu and main menu
public class MainMenuPanel extends MenuPanel implements ActionListener {
    // images
    private static final ImageIcon RETURN_ICON = new ImageIcon(ICON_DIRECTORY + "return.png");
    private static final ImageIcon CHECK_ICON = new ImageIcon(ICON_DIRECTORY + "check.png");
    private static final ImageIcon PLUS_ICON = new ImageIcon(ICON_DIRECTORY + "plus.png");
    private static final ImageIcon TRASH_ICON = new ImageIcon(ICON_DIRECTORY + "trash.png");
    private static final ImageIcon JUMP_ICON = new ImageIcon(ICON_DIRECTORY + "jumpingGuy.png");
    private static final ImageIcon CLOCK_ICON = new ImageIcon(ICON_DIRECTORY + "clock.png");
    private static final ImageIcon HEART_ICON = new ImageIcon(ICON_DIRECTORY + "heart.png");
    private static final ImageIcon X_ICON = new ImageIcon(ICON_DIRECTORY + "x.png");
    private static final ImageIcon MINUS_ICON = new ImageIcon(ICON_DIRECTORY + "minus.png");

    // layouts
    private final CardLayout titleCardLayout = new CardLayout();
    private final CardLayout buttonCardLayout = new CardLayout();

    // panels
    private final MainDisplayPanel mainDisplayPanel;
    private final SideDisplayPanel sideDisplayPanel;

    private final JPanel titlePanel = new JPanel(titleCardLayout);
    private final JPanel buttonPanel = new JPanel(buttonCardLayout);

    private final JPanel encounterButtonPanel = new JPanel(new GridLayout(3, 1));
    private final JPanel encounterButtonSubPanel0 = new JPanel(new GridLayout(1, 2));
    private final JPanel encounterButtonSubPanel1 = new JPanel(new GridLayout(1, 3));
    private final JPanel encounterButtonSubPanel2 = new JPanel(new GridLayout(1, 3));

    private final JPanel libraryButtonPanel = new JPanel(new GridLayout(2, 1));
    private final JPanel libraryButtonSubPanel0 = new JPanel(new GridLayout(1, 2));
    private final JPanel libraryButtonSubPanel1 = new JPanel(new GridLayout(1, 3));

    private final JPanel npcButtonPanel = new JPanel(new GridLayout(2, 1));

    // buttons
    private final JButton backButton = new JButton("Back");

    private final JButton addStatBlocksToEncounterButton = new JButton("Add to Encounter");
    private final JButton openStatBlockButton = new JButton("Open");
    private final JButton createNewStatBlockButton = new JButton("Create New");
    private final JButton deleteStatBlocksButton = new JButton("Delete");

    private final JButton openNonPlayerCharacterButton = new JButton("Open");
    private final JButton rollCheckButton = new JButton("Roll Ability Check");
    private final JButton rollInitiativeButton = new JButton("Roll Initiative");
    private final JButton changeHPButton = new JButton("Change HP");
    private final JButton deleteNonPlayerCharacterButton = new JButton("Delete");

    // MODIFIES: this
    // EFFECTS: constructs this panel
    public MainMenuPanel(MenuManagerPanel menuManagerPanel) {
        super(new BorderLayout(), menuManagerPanel); // sets layout and menu managers, visibility, and size

        initializeButtons();
        initializeButtonPanel();
        initializeTitlePanel();

        mainDisplayPanel = new MainDisplayPanel(this);
        sideDisplayPanel = new SideDisplayPanel(this);

        JPanel displaysPanel = new JPanel(new GridLayout(1, 2));
        displaysPanel.add(mainDisplayPanel);
        displaysPanel.add(sideDisplayPanel);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(displaysPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
        mainPanel.setBorder(new EmptyBorder(0, 10, 10, 10));

        add(titlePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: paints as normal but changes the titlepanel's preferred size
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        titlePanel.setPreferredSize(new Dimension(getWidth(), getHeight() / 5));
    }

    // MODIFIES: this
    // EFFECTS: formats title panel
    private void initializeTitlePanel() {
        BannerPanel encounterBannerPanel = new BannerPanel("encounter");
        BannerPanel libraryBannerPanel = new BannerPanel("library");

        titlePanel.add(encounterBannerPanel, "encounter");
        titlePanel.add(libraryBannerPanel, "library");
    }

    // MODIFIES: this
    // EFFECTS: adds fields to buttons
    private void initializeButtons() {
        ArrayList<JButton> buttonList = new ArrayList<>();

        buttonList.add(backButton);
        buttonList.add(addStatBlocksToEncounterButton);
        buttonList.add(openStatBlockButton);
        buttonList.add(createNewStatBlockButton);
        buttonList.add(deleteStatBlocksButton);
        buttonList.add(openNonPlayerCharacterButton);
        buttonList.add(rollCheckButton);
        buttonList.add(rollInitiativeButton);
        buttonList.add(changeHPButton);
        buttonList.add(deleteNonPlayerCharacterButton);

        for (JButton jb : buttonList) {
            jb.removeActionListener(this);
            jb.addActionListener(this);
        }
        initializeButtonIcons();
    }

    // MODIFIES: this
    // EFFECTS: adds icon to buttons
    private void initializeButtonIcons() {
        backButton.setIcon(scaleIcon(RETURN_ICON));
        addStatBlocksToEncounterButton.setIcon(scaleIcon(PLUS_ICON));
        openStatBlockButton.setIcon(scaleIcon(CHECK_ICON));
        createNewStatBlockButton.setIcon(scaleIcon(PLUS_ICON));
        deleteStatBlocksButton.setIcon(scaleIcon(TRASH_ICON));
        openNonPlayerCharacterButton.setIcon(scaleIcon(CHECK_ICON));
        rollCheckButton.setIcon(scaleIcon(JUMP_ICON));
        rollInitiativeButton.setIcon(scaleIcon(CLOCK_ICON));
        changeHPButton.setIcon(scaleIcon(HEART_ICON));
        deleteNonPlayerCharacterButton.setIcon(scaleIcon(TRASH_ICON));
    }

    // EFFECTS: returns a scaled image icon of the given image icon
    private ImageIcon scaleIcon(ImageIcon imageIcon) {
        return new ImageIcon(imageIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
    }

    // MODIFIES: this
    // EFFECTS: adds button sub panels to the main button panel and adds fields to buttons
    private void initializeButtonPanel() {
        initializeButtons();

        buttonPanel.add(libraryButtonPanel, "library");
        buttonPanel.add(encounterButtonPanel, "encounter");
        buttonPanel.add(npcButtonPanel, "npc");
        buttonPanel.setPreferredSize(new Dimension(1080, 144));
    }

    // MODIFIES: this
    // EFFECTS: adds button sub panels to library button panel and sets button fields to the library context
    private void initializeLibraryButtonPanel() {
        buttonCardLayout.show(buttonPanel, "library");

        backButton.setIcon(scaleIcon(RETURN_ICON));
        openStatBlockButton.setIcon(scaleIcon(CHECK_ICON));
        deleteStatBlocksButton.setIcon(scaleIcon(TRASH_ICON));

        backButton.setText("Back");
        deleteStatBlocksButton.setText("Delete");
        openStatBlockButton.setText("Open");
        openStatBlockButton.setEnabled(false);
        addStatBlocksToEncounterButton.setEnabled(false);
        deleteStatBlocksButton.setEnabled(false);

        libraryButtonSubPanel0.add(addStatBlocksToEncounterButton);
        libraryButtonSubPanel0.add(openStatBlockButton);

        libraryButtonSubPanel1.add(createNewStatBlockButton);
        libraryButtonSubPanel1.add(deleteStatBlocksButton);
        libraryButtonSubPanel1.add(backButton);

        libraryButtonPanel.add(libraryButtonSubPanel0);
        libraryButtonPanel.add(libraryButtonSubPanel1);
    }

    // MODIFIES: this
    // EFFECTS: adds button sub panels to library button panel and sets button fields to the statblock creation context
    private void initializeStatBlockCreationButtonPanel() {
        initializeLibraryButtonPanel();

        backButton.setIcon(scaleIcon(X_ICON));
        openStatBlockButton.setIcon(scaleIcon(CHECK_ICON));
        deleteStatBlocksButton.setIcon(scaleIcon(MINUS_ICON));

        backButton.setText("Cancel");
        deleteStatBlocksButton.setText("Remove");
        openStatBlockButton.setText("Finish");
        openStatBlockButton.setEnabled(true);
    }

    // MODIFIES: this
    // EFFECTS: adds button sub panels to encounter button panel and sets button fields to the encounter context
    private void initializeEncounterButtonPanel() {
        openNonPlayerCharacterButton.setEnabled(false);
        rollCheckButton.setEnabled(false);
        rollInitiativeButton.setEnabled(false);
        changeHPButton.setEnabled(false);
        deleteNonPlayerCharacterButton.setEnabled(false);

        buttonCardLayout.show(buttonPanel, "encounter");

        encounterButtonSubPanel0.add(openNonPlayerCharacterButton);

        encounterButtonSubPanel1.add(rollCheckButton);
        encounterButtonSubPanel1.add(rollInitiativeButton);
        encounterButtonSubPanel1.add(changeHPButton);

        encounterButtonSubPanel2.add(deleteNonPlayerCharacterButton);
        encounterButtonSubPanel2.add(backButton);

        encounterButtonPanel.add(encounterButtonSubPanel0);
        encounterButtonPanel.add(encounterButtonSubPanel1);
        encounterButtonPanel.add(encounterButtonSubPanel2);
    }

    // MODIFIES: this
    // EFFECTS: adds button sub panels to npc button panel and sets button fields to the npc context
    private void initializeNonPlayerCharacterButtonPanel() {
        buttonCardLayout.show(buttonPanel, "npc");

        npcButtonPanel.add(encounterButtonSubPanel1);
        npcButtonPanel.add(encounterButtonSubPanel2);
    }

    // MODIFIES: this
    // EFFECTS: sets the main and side displays to the given context
    public void setDisplays(String s) {
        switch (s) {
            case "library":
                setLibraryFullDisplay();
                break;
            case "statBlock":
                setLibraryStatBlockDisplay();
                break;
            case "statBlockCreation":
                setLibraryStatBlockCreationDisplay();
                break;
            case "npc":
                setEncounterNonPlayerCharacterDisplay();
                break;
            default:
                setEncounterFullDisplay();
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the main and side displays to the library context
    private void setLibraryFullDisplay() {
        initializeLibraryButtonPanel();

        mainDisplayPanel.setDisplay("library");
        sideDisplayPanel.setDisplay("library");
        titleCardLayout.show(titlePanel, "library");
    }

    // MODIFIES: this
    // EFFECTS: sets the main display to the library context and the side display to the statblock context
    private void setLibraryStatBlockDisplay() {
        initializeLibraryButtonPanel();

        titleCardLayout.show(titlePanel, "library");
        mainDisplayPanel.setDisplay("library");
        sideDisplayPanel.setDisplay("statBlock");
    }

    // MODIFIES: this
    // EFFECTS: sets the main display to the library context and the side display to the statblock creation context
    private void setLibraryStatBlockCreationDisplay() {
        initializeStatBlockCreationButtonPanel();

        titleCardLayout.show(titlePanel, "library");
        mainDisplayPanel.setDisplay("library");
        sideDisplayPanel.setDisplay("statBlockCreation");
    }

    // MODIFIES: this
    // EFFECTS: sets the main and side displays to the encounter context
    private void setEncounterFullDisplay() {
        initializeEncounterButtonPanel();

        titleCardLayout.show(titlePanel, "encounter");
        mainDisplayPanel.setDisplay("encounter");
        sideDisplayPanel.setDisplay("encounter");
        sideDisplayPanel.refresh();
    }

    // MODIFIES: this
    // EFFECTS: sets the main display to the npc context and the side display to the encounter context
    private void setEncounterNonPlayerCharacterDisplay() {
        initializeNonPlayerCharacterButtonPanel();
        mainDisplayPanel.setDisplay("npc");
        sideDisplayPanel.setDisplay("encounter");
        sideDisplayPanel.refresh();
    }

    @Override
    // EFFECTS: processes button presses from user
    public void actionPerformed(ActionEvent e) {
        if (mainDisplayPanel.getLibraryScrollPane().isShowing()) {
            StatBlockCreationDisplayPanel creationPanel
                    = sideDisplayPanel.getLibrarySideDisplayScrollPane().getStatBlockCreationDisplayPanel();
            if (creationPanel.isShowing()) {
                creationPanel.passAction(e);
            } else {
                mainDisplayPanel.getLibraryScrollPane().processAction(e);
            }
        } else {
            mainDisplayPanel.passAction(e);
        }
    }

    // -----------------------------------------------------------------------
    // getters
    // EFFECTS: gets the main display panel
    public MainDisplayPanel getMainDisplayPanel() {
        return mainDisplayPanel;
    }

    // EFFECTS: gets the side display panel
    public SideDisplayPanel getSideDisplayPanel() {
        return sideDisplayPanel;
    }

    // EFFECTS: gets the back button
    public JButton getBackButton() {
        return backButton;
    }

    // EFFECTS: gets the add statblocks to encounter button
    public JButton getAddStatBlocksToEncounterButton() {
        return addStatBlocksToEncounterButton;
    }

    // EFFECTS: gets the open statblock button
    public JButton getOpenStatBlockButton() {
        return openStatBlockButton;
    }

    // EFFECTS: gets the create new statblock button
    public JButton getCreateNewStatBlockButton() {
        return createNewStatBlockButton;
    }

    // EFFECTS: gets delete statblocks button
    public JButton getDeleteStatBlocksButton() {
        return deleteStatBlocksButton;
    }

    // EFFECTS: gets the open npc button
    public JButton getOpenNonPlayerCharacterButton() {
        return openNonPlayerCharacterButton;
    }

    // EFFECTS: gets the roll check button
    public JButton getRollCheckButton() {
        return rollCheckButton;
    }

    // EFFECTS: gets the roll initiative button
    public JButton getRollInitiativeButton() {
        return rollInitiativeButton;
    }

    // EFFECTS: gets the change hp button
    public JButton getChangeHPButton() {
        return changeHPButton;
    }

    // EFFECTS: gets the delete npc button
    public JButton getDeleteNonPlayerCharacterButton() {
        return deleteNonPlayerCharacterButton;
    }
}
