package ui.menus;

import model.Character;
import model.StatBlock;
import ui.menus.encountermenus.CharacterMenuFrame;
import ui.menus.encountermenus.GroupMenu;
import ui.menus.prompts.LoadPrompt;
import ui.menus.prompts.SavePrompt;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// represents...
public class MainMenuFrame extends JFrame implements ActionListener, ListSelectionListener {
    private JList<model.Character> encounterJList = new JList<>();
    private JList<model.StatBlock> libraryJList = new JList<>();

    private static final int WIDTH = 720;
    private static final int HEIGHT = 480;

    private final List<JButton> buttonList = new ArrayList<>();
    private final JButton goToCustomRollButton = new JButton("Go To Custom Roll Menu");
    private final JButton goToSelectedCharacterButton = new JButton("Go To Selected Character's Menu");
    private final JButton goToSelectedGroupButton = new JButton("Go To Selected Character's Group Menu");
    private final JButton goToLibraryButton = new JButton("Go To Library");
    private final JButton loadButton = new JButton("Load encounter and library from file");
    private final JButton quitAutoBlocksButton = new JButton("Quit AutoBlocks");

    // EFFECTS: constructs...
    public MainMenuFrame() {
        initializeEncounterJList();
        initializeButtons();

        this.setTitle("AutoBlocks");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        JLabel title = new JLabel();
        title.setText("Main Menu. Characters in currently loaded encounter:");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.TOP);
        this.add(title);
    }

    // encounter jlist setter
    public void setEncounterJList(JList<Character> encounterJList) {
        this.encounterJList = encounterJList;
    }

    // library jlist setter
    public void setLibraryJList(JList<StatBlock> libraryJList) {
        this.libraryJList = libraryJList;
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

    // EFFECTS: sets parameters for all buttons
    private void initializeButtons() {
        buttonList.add(goToCustomRollButton);
        buttonList.add(goToSelectedCharacterButton);
        buttonList.add(goToSelectedGroupButton);
        buttonList.add(goToLibraryButton);
        buttonList.add(loadButton);
        buttonList.add(quitAutoBlocksButton);

        int height = HEIGHT - 220;

        for (JButton jb : buttonList) {
            jb.setBounds(0, height, WIDTH, 30);
            jb.addActionListener(this);
            jb.setFocusable(false);
            this.add(jb);
            height += 30;
        }
    }

    @Override
    // EFFECTS: ...
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == goToCustomRollButton) {
            this.dispose();
            new CustomRollFrame();
        }
        if (e.getSource() == goToSelectedCharacterButton) {
            this.dispose();
            new CharacterMenuFrame();
        }
        if (e.getSource() == goToSelectedGroupButton) {
            this.dispose();
            new GroupMenu();
        }
        if (e.getSource() == goToLibraryButton) {
            this.dispose();
            //new LibraryMenu(libraryJList);
        }
        if (e.getSource() == loadButton) {
            new LoadPrompt(this);
        }
        if (e.getSource() == quitAutoBlocksButton) {
            new SavePrompt(encounterJList, libraryJList);
        }
    }

    @Override
    // EFFECTS: ...
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if (encounterJList.getSelectedIndex() == -1) {
                goToSelectedCharacterButton.setEnabled(false);
                goToSelectedGroupButton.setEnabled(false);
            } else {
                goToSelectedCharacterButton.setEnabled(true);
                goToSelectedGroupButton.setEnabled(true);
            }
        }
    }
}
