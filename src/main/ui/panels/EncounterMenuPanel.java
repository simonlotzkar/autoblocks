package ui.panels;

import ui.frames.CharacterFrame;
import ui.frames.CustomRollFrame;
import ui.frames.GroupFrame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EncounterMenuPanel extends JPanel implements ActionListener, ListSelectionListener {
    private JList<model.Character> encounterJList = new JList<>();
    private JList<model.StatBlock> libraryJList = new JList<>();

    // panels
    private final MenuCardPanel menuCardPanel;
    private final JPanel buttonsPanel = new JPanel();
    private final JPanel smallButtonsPanel = new JPanel();
    private final JPanel libraryAndSaveButtonPanel = new JPanel();
    private final JPanel encounterAndLoadButtonPanel = new JPanel();

    // constants
    private static final String ICONS_DIRECTORY = "./data/images/icons/";
    private static final String BANNERS_DIRECTORY = "./data/images/banners/";

    // images
    private static final JLabel MAIN_BANNER_LABEL = new JLabel(new ImageIcon(BANNERS_DIRECTORY + "mainMenuBanner.jpg"));
    private static final JLabel DIVIDER_LABEL = new JLabel(new ImageIcon("./data/images/divider.jpg"));
    private static final ImageIcon ROLL_ICON = new ImageIcon(ICONS_DIRECTORY + "d20Icon.png");
    private static final ImageIcon SAVE_ICON = new ImageIcon(ICONS_DIRECTORY + "saveIcon.png");
    private static final ImageIcon LOAD_ICON = new ImageIcon(ICONS_DIRECTORY + "fileIcon.png");
    private static final ImageIcon LIBRARY_ICON = new ImageIcon(ICONS_DIRECTORY + "libraryIcon.png");
    private static final ImageIcon ENCOUNTER_ICON = new ImageIcon(ICONS_DIRECTORY + "crossedSwordsIcon.png");
    private static final ImageIcon QUIT_ICON = new ImageIcon(ICONS_DIRECTORY + "exitIcon.png");

    // buttons
    private final JButton selectCharacterButton = new JButton("Open character's sheet");
    private final JButton selectGroupButton = new JButton("Open character's group");
    private final JButton addToNewGroupButton = new JButton("Add selected to new group");
    private final JButton backButton = new JButton("Back");

    // EFFECTS: constructs this frame
    public EncounterMenuPanel(MenuCardPanel menuCardPanel) {
        super();
        this.menuCardPanel = menuCardPanel;
        this.setSize(menuCardPanel.getSize());
        this.setVisible(true);
        this.setLayout(new GridLayout(2, 1));
        this.add(MAIN_BANNER_LABEL);
        //initializeButtons();
        this.add(buttonsPanel);
    }

    // EFFECTS: sets encounter jlist's parameters
    private void initializeEncounterJList() {
        encounterJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        encounterJList.setLayoutOrientation(JList.VERTICAL_WRAP);
        encounterJList.setVisibleRowCount(-1);
        encounterJList.setVisible(true);
        encounterJList.setBounds(WIDTH / 4, 20, WIDTH / 2, HEIGHT / 4);
        encounterJList.addListSelectionListener(this);
        this.add(encounterJList);
    }

    @Override
    // EFFECTS: processes button presses from user
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectCharacterButton) {
            new CharacterFrame();
        }
        if (e.getSource() == selectGroupButton) {
            new GroupFrame();
        }
        if (e.getSource() == addToNewGroupButton) {
            // prompt for new group name with cancel option
        }
        if (e.getSource() == backButton) {
            menuCardPanel.changeMenu("mainMenu");
        }
    }

    @Override
    // EFFECTS: disables and enables buttons when user is changing list selection
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            //disable list buttons
        } else if (!e.getValueIsAdjusting()) {
            //enable list buttons
        }
    }
}
