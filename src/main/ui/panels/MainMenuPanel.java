package ui.panels;

import ui.frames.CustomRollFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainMenuPanel extends JPanel implements ActionListener {
    private JList<model.Character> encounterJList = new JList<>();
    private JList<model.StatBlock> libraryJList = new JList<>();

    // panels
    private final MenuCardPanel menuCardMenu;
    private final JPanel buttonsPanel = new JPanel();
    private final JPanel smallButtonsPanel = new JPanel();
    private final JPanel libraryAndSaveButtonPanel = new JPanel();
    private final JPanel encounterAndLoadButtonPanel = new JPanel();

    // constants
    private static final String ICONS_DIRECTORY = "./data/images/icons/";
    private static final String BANNERS_DIRECTORY = "./data/images/banners/";

    // images
    private static final JLabel MAIN_BANNER_LABEL = new JLabel(new ImageIcon(BANNERS_DIRECTORY + "mainMenuBanner.gif"));
    private static final JLabel DIVIDER_LABEL = new JLabel(new ImageIcon("./data/images/divider.gif"));
    private static final ImageIcon ROLL_ICON = new ImageIcon(ICONS_DIRECTORY + "d20Icon.gif");
    private static final ImageIcon SAVE_ICON = new ImageIcon(ICONS_DIRECTORY + "saveIcon.gif");
    private static final ImageIcon LOAD_ICON = new ImageIcon(ICONS_DIRECTORY + "fileIcon.gif");
    private static final ImageIcon LIBRARY_ICON = new ImageIcon(ICONS_DIRECTORY + "libraryIcon.gif");
    private static final ImageIcon ENCOUNTER_ICON = new ImageIcon(ICONS_DIRECTORY + "crossedSwordsIcon.gif");
    private static final ImageIcon QUIT_ICON = new ImageIcon(ICONS_DIRECTORY + "exitIcon.gif");

    // buttons
    private final JButton customRollButton = new JButton("Open Custom Roll Window");
    private final JButton quitButton = new JButton("Quit AutoBlocks");
    private final JButton libraryButton = new JButton("Library Menu");
    private final JButton encounterButton = new JButton("Encounter Menu");
    private final JButton loadButton = new JButton("Load encounter and library from file");
    private final JButton saveButton = new JButton("Save encounter and library to file");

    // EFFECTS: constructs this panel
    public MainMenuPanel(MenuCardPanel menuCardPanel) {
        super();
        this.menuCardMenu = menuCardPanel;
        this.setSize(menuCardPanel.getSize());
        this.setVisible(true);
        this.setLayout(new GridLayout(2, 1));
        this.add(MAIN_BANNER_LABEL);
        initializeButtons();
        this.add(buttonsPanel);
    }

    // MODIFIES: this
    // EFFECTS: sets parameters for all buttons
    private void initializeButtons() {
        customRollButton.setIcon(ROLL_ICON);
        quitButton.setIcon(QUIT_ICON);
        encounterButton.setIcon(ENCOUNTER_ICON);
        libraryButton.setIcon(LIBRARY_ICON);
        loadButton.setIcon(LOAD_ICON);
        saveButton.setIcon(SAVE_ICON);

        List<JButton> buttonList = new ArrayList<>();

        buttonList.add(customRollButton);
        buttonList.add(quitButton);
        buttonList.add(encounterButton);
        buttonList.add(libraryButton);
        buttonList.add(loadButton);
        buttonList.add(saveButton);

        for (JButton jb : buttonList) {
            jb.addActionListener(this);
            jb.setFocusable(false);
            jb.setVisible(true);
        }
        initializeButtonPanels();
    }

    // MODIFIES: this
    // EFFECTS: sets up button panels with buttons and layouts
    private void initializeButtonPanels() {
        libraryAndSaveButtonPanel.setLayout(new GridLayout(2, 1));
        libraryAndSaveButtonPanel.add(libraryButton);
        libraryAndSaveButtonPanel.add(saveButton);
        libraryAndSaveButtonPanel.setVisible(true);

        encounterAndLoadButtonPanel.setLayout(new GridLayout(2, 1));
        encounterAndLoadButtonPanel.add(encounterButton);
        encounterAndLoadButtonPanel.add(loadButton);
        encounterAndLoadButtonPanel.setVisible(true);

        smallButtonsPanel.setLayout(new GridLayout(1, 3));
        smallButtonsPanel.add(libraryAndSaveButtonPanel);
        smallButtonsPanel.add(encounterAndLoadButtonPanel);
        smallButtonsPanel.add(quitButton);
        smallButtonsPanel.setVisible(true);

        buttonsPanel.setLayout(new GridLayout(3, 1));
        buttonsPanel.add(customRollButton);
        buttonsPanel.add(DIVIDER_LABEL);
        buttonsPanel.add(smallButtonsPanel);
        buttonsPanel.setVisible(true);
    }

    @Override
    // EFFECTS: processes button presses from user
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == customRollButton) {
            new CustomRollFrame();
        }
        if (e.getSource() == encounterButton) {
            menuCardMenu.changeMenu("encounterMenu");
        }
        if (e.getSource() == libraryButton) {
            menuCardMenu.changeMenu("libraryMenu");
        }
        if (e.getSource() == loadButton) {
            menuCardMenu.tryLoad();
        }
        if (e.getSource() == saveButton) {
            menuCardMenu.trySave();
        }
        if (e.getSource() == quitButton) {
            menuCardMenu.confirmQuit();
        }
    }
}
