package ui.panels;

import ui.frames.CharacterFrame;
import ui.frames.CustomRollFrame;
import ui.frames.GroupFrame;
import ui.frames.StatBlockCreatorFrame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryMenuPanel extends JPanel implements ActionListener, ListSelectionListener {
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
    private static final ImageIcon QUIT_ICON = new ImageIcon(ICONS_DIRECTORY + "exitIcon.png");

    // buttons
    private final JButton selectStatBlockButton = new JButton("Open statblock's sheet");
    private final JButton createNewStatBlockButton = new JButton("Create a new statblock");
    private final JButton addToEncounterButton = new JButton("Add selected to encounter");
    private final JButton backButton = new JButton("Back");

    // EFFECTS: constructs this panel
    public LibraryMenuPanel(MenuCardPanel menuCardPanel) {
        super();
        this.menuCardPanel = menuCardPanel;
        this.setSize(menuCardPanel.getSize());
        this.setVisible(true);
        this.setLayout(new GridLayout(2, 1));
        this.add(MAIN_BANNER_LABEL);
        initializeButtons();
        this.add(buttonsPanel);
    }

    // EFFECTS: sets encounter jlist's parameters
    private void initializeLibraryList() {
        libraryJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        libraryJList.setLayoutOrientation(JList.VERTICAL_WRAP);
        libraryJList.setVisibleRowCount(-1);
        libraryJList.setVisible(true);
        libraryJList.setBounds(WIDTH / 4, 20, WIDTH / 2, HEIGHT / 4);
        libraryJList.addListSelectionListener(this);
        this.add(libraryJList);
    }

    // EFFECTS: constructs buttons
    public void initializeButtons() {
        //stub
    }

    @Override
    // EFFECTS: processes button presses from user
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectStatBlockButton) {
            new CharacterFrame();
        }
        if (e.getSource() == createNewStatBlockButton) {
            new StatBlockCreatorFrame();
        }
        if (e.getSource() == addToEncounterButton) {
            // prompt for number of copies to add with cancel option
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