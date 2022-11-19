//package ui.menus.librarymenus;
//
//import ui.menus.prompts.ConfirmationPrompt;
//import ui.menus.RollMenu;
//import ui.menus.encountermenus.CharacterMenu;
//import ui.menus.encountermenus.GroupMenu;
//
//import javax.swing.*;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.List;
//
//// represents...
//public class LibraryMenu extends JFrame implements ActionListener, ListSelectionListener {
//    private final JList<model.Character> encounterJList;
//    private final JList<model.StatBlock> libraryJList;
//
//    private static final int WIDTH = 720;
//    private static final int HEIGHT = 480;
//
//    // buttons
//    private final JButton goToCustomRollButton = new JButton("Go To Custom Roll Menu");
//    private final JButton goToSelectedCharacterButton = new JButton("Go To Selected Character's Menu");
//    private final JButton goToSelectedGroupButton = new JButton("Go To Selected Character's Group Menu");
//    private final JButton goToLibraryButton = new JButton("Go To Library");
//    private final JButton quitAutoBlocksButton = new JButton("Quit AutoBlocks");
//    private final List<JButton> buttonList = new ArrayList<>();
//
//    // EFFECTS: constructs...
//    public LibraryMenu(JList<model.StatBlock> libraryListModel) {
//        encounterJList = new JList<>(encounterListModel);
//        libraryJList = new JList<>(libraryListModel);
//
//        this.setTitle("AutoBlocks");
//        this.setSize(WIDTH, HEIGHT);
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        this.setVisible(true);
//        this.setResizable(false);
//
//        initializeEncounterJList();
//        this.add(encounterJList);
//
//        initializeButtons();
//        this.add(goToCustomRollButton);
//        this.add(goToSelectedCharacterButton);
//        this.add(goToSelectedGroupButton);
//        this.add(goToLibraryButton);
//        this.add(quitAutoBlocksButton);
//
//        JLabel mainMenuTitle = new JLabel();
//        mainMenuTitle.setText("Main Menu. Characters in currently loaded encounter:");
//        mainMenuTitle.setHorizontalAlignment(SwingConstants.CENTER);
//        mainMenuTitle.setVerticalAlignment(SwingConstants.TOP);
//        this.add(mainMenuTitle);
//    }
//
//    // EFFECTS: sets encounter jlist's parameters from given encounter list model
//    private void initializeEncounterJList() {
//        encounterJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        encounterJList.setLayoutOrientation(JList.VERTICAL_WRAP);
//        encounterJList.setVisibleRowCount(-1);
//        encounterJList.setVisible(true);
//        encounterJList.setBounds(WIDTH / 4, 20, WIDTH / 2, 260);
//        encounterJList.addListSelectionListener(this);
//    }
//
//    // EFFECTS: sets parameters for all buttons
//    private void initializeButtons() {
//        buttonList.add(goToCustomRollButton);
//        buttonList.add(goToSelectedCharacterButton);
//        buttonList.add(goToSelectedGroupButton);
//        buttonList.add(goToLibraryButton);
//        buttonList.add(quitAutoBlocksButton);
//
//        int height = HEIGHT - 190;
//
//        for (JButton jb : buttonList) {
//            jb.setBounds(0, height, WIDTH, 30);
//            jb.addActionListener(this);
//            jb.setFocusable(false);
//            height += 30;
//        }
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == goToCustomRollButton) {
//            this.dispose();
//            RollMenu rollMenu = new RollMenu();
//        }
//        if (e.getSource() == goToSelectedCharacterButton) {
//            this.dispose();
//            CharacterMenu characterMenu = new CharacterMenu();
//        }
//        if (e.getSource() == goToSelectedGroupButton) {
//            this.dispose();
//            GroupMenu groupMenu = new GroupMenu();
//        }
//        if (e.getSource() == goToLibraryButton) {
//            this.dispose();
//            LibraryMenu libraryMenu = new LibraryMenu(libraryJList);
//        }
//        if (e.getSource() == quitAutoBlocksButton) {
//            ConfirmationPrompt confirmationPrompt = new ConfirmationPrompt();
//        }
//    }
//
//    @Override
//    public void valueChanged(ListSelectionEvent e) {
//        if (e.getValueIsAdjusting() == false) {
//            if (encounterJList.getSelectedIndex() == -1) {
//                goToSelectedCharacterButton.setEnabled(false);
//                goToSelectedGroupButton.setEnabled(false);
//            } else {
//                goToSelectedCharacterButton.setEnabled(true);
//                goToSelectedGroupButton.setEnabled(true);
//            }
//        }
//    }
//}
