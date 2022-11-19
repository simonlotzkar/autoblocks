package ui.menus;

import ui.menus.encountermenus.CharacterMenu;
import ui.menus.encountermenus.GroupMenu;
import ui.menus.librarymenus.LibraryMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents...
public class MainMenu extends JFrame implements ActionListener {
    private JList encounterJList;

    private static final int WIDTH = 720;
    private static final int HEIGHT = 480;

    // buttons
    private final JButton goToCustomRollButton = new JButton();
    private final JButton goToSelectedCharacterButton = new JButton();
    private final JButton goToSelectedGroupButton = new JButton();
    private final JButton goToLibraryButton = new JButton();
    private final JButton quitAutoBlocksButton = new JButton();

    // EFFECTS: constructs...
    public MainMenu(DefaultListModel encounterListModel) {
        initializeEncounterJList(encounterListModel);
        this.add(encounterJList);
        initializeButtons();
        this.add(goToCustomRollButton);

        this.add(goToSelectedCharacterButton);
        this.add(goToSelectedGroupButton);
        this.add(goToLibraryButton);
        this.add(quitAutoBlocksButton);

        JLabel mainMenuTitle = new JLabel();
        mainMenuTitle.setText("Main Menu. Characters in currently loaded encounter:");
        mainMenuTitle.setHorizontalAlignment(SwingConstants.CENTER);
        mainMenuTitle.setVerticalAlignment(SwingConstants.TOP);
        this.add(mainMenuTitle);

        this.setTitle("AutoBlocks");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

    // EFFECTS: sets encounter jlist's parameters from given encounter list model
    private void initializeEncounterJList(DefaultListModel encounterListModel) {
        encounterJList = new JList(encounterListModel);
        encounterJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        encounterJList.setLayoutOrientation(JList.VERTICAL_WRAP);
        encounterJList.setVisibleRowCount(-1);
        encounterJList.setVisible(true);
        encounterJList.setBounds(0, 20, WIDTH, 260);
        JScrollPane listScroller = new JScrollPane(encounterJList);
    }

    // EFFECTS: sets parameters for all buttons
    private void initializeButtons() {
        goToCustomRollButton.setBounds(0, HEIGHT - 190, WIDTH, 30);
        goToCustomRollButton.addActionListener(this);
        goToCustomRollButton.setText("Go To Custom Roll Menu");
        goToCustomRollButton.setFocusable(false);

        goToSelectedCharacterButton.setBounds(0, HEIGHT - 160, WIDTH, 30);
        goToSelectedCharacterButton.addActionListener(this);
        goToSelectedCharacterButton.setText("Go To Selected Character's Menu");
        goToSelectedCharacterButton.setFocusable(false);

        goToSelectedGroupButton.setBounds(0, HEIGHT - 130, WIDTH, 30);
        goToSelectedGroupButton.addActionListener(this);
        goToSelectedGroupButton.setText("Go To Selected Character's Group Menu");
        goToSelectedGroupButton.setFocusable(false);

        goToLibraryButton.setBounds(0, HEIGHT - 100, WIDTH, 30);
        goToLibraryButton.addActionListener(this);
        goToLibraryButton.setText("Go To Library");
        goToLibraryButton.setFocusable(false);

        quitAutoBlocksButton.setBounds(0, HEIGHT - 70, WIDTH, 30);
        quitAutoBlocksButton.addActionListener(this);
        quitAutoBlocksButton.setText("Quit AutoBlocks");
        quitAutoBlocksButton.setFocusable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == goToCustomRollButton) {
            this.dispose();
            RollMenu rollMenu = new RollMenu();
        }
        if (e.getSource() == goToSelectedCharacterButton) {
            this.dispose();
            CharacterMenu characterMenu = new CharacterMenu();
        }
        if (e.getSource() == goToSelectedGroupButton) {
            this.dispose();
            GroupMenu groupMenu = new GroupMenu();
        }
        if (e.getSource() == goToLibraryButton) {
            this.dispose();
            LibraryMenu libraryMenu = new LibraryMenu();
        }
        if (e.getSource() == quitAutoBlocksButton) {
            this.dispose();
            QuitMenu quitMenu = new QuitMenu();
        }
    }
}
