package ui.panels.menus;

import ui.frames.CustomRollFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TitleMenuPanel extends MenuPanel implements ActionListener {
    // panels
    private final JPanel buttonsPanel = new JPanel(new GridLayout(2, 1));

    // images
    private static final JLabel MAIN_BANNER_LABEL = new JLabel(new ImageIcon(
            "./data/images/banners/banner0.jpg"));
    private static final ImageIcon D20_IMAGE_ICON = new ImageIcon(ICON_DIRECTORY + "d20Icon.png");
    private static final ImageIcon EXIT_IMAGE_ICON = new ImageIcon(ICON_DIRECTORY + "exitIcon.png");
    private static final ImageIcon LIBRARY_IMAGE_ICON = new ImageIcon(ICON_DIRECTORY + "libraryIcon.png");
    private static final ImageIcon ENCOUNTER_IMAGE_ICON = new ImageIcon(ICON_DIRECTORY + "crossedSwordsIcon.png");
    private static final ImageIcon LOAD_IMAGE_ICON = new ImageIcon(ICON_DIRECTORY + "fileIcon.png");
    private static final ImageIcon SAVE_IMAGE_ICON = new ImageIcon(ICON_DIRECTORY + "saveIcon.png");

    // buttons
    private final JButton customRollButton = new JButton("Open Custom Roll Window");
    private final JButton quitButton = new JButton("Quit AutoBlocks");
    private final JButton libraryButton = new JButton("Library Menu");
    private final JButton encounterButton = new JButton("Encounter Menu");
    private final JButton loadButton = new JButton("Load encounter and library from file");
    private final JButton saveButton = new JButton("Save encounter and library to file");

    // EFFECTS: constructs this panel
    public TitleMenuPanel(MenuManagerPanel menuManagerPanel) {
        super(new BorderLayout(), menuManagerPanel); // sets layout and menu managers, visibility, and size

        initializeButtons();
        initializeButtonPanel();

        JPanel tempLabel = new JPanel();
        tempLabel.setBackground(Color.BLUE);

        this.add(tempLabel, BorderLayout.CENTER);
        //this.add(MAIN_BANNER_LABEL, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: sets parameters for all buttons
    private void initializeButtons() {
        List<JButton> buttonList = new ArrayList<>();

        customRollButton.setIcon(D20_IMAGE_ICON);
        quitButton.setIcon(EXIT_IMAGE_ICON);
        encounterButton.setIcon(ENCOUNTER_IMAGE_ICON);
        libraryButton.setIcon(LIBRARY_IMAGE_ICON);
        loadButton.setIcon(LOAD_IMAGE_ICON);
        saveButton.setIcon(SAVE_IMAGE_ICON);

        buttonList.add(customRollButton);
        buttonList.add(quitButton);
        buttonList.add(encounterButton);
        buttonList.add(libraryButton);
        buttonList.add(loadButton);
        buttonList.add(saveButton);

        for (JButton jb : buttonList) {
            jb.addActionListener(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up button panels with buttons and layouts
    private void initializeButtonPanel() {
        JPanel libraryAndSaveButtonPanel = new JPanel(new GridLayout(2, 1));
        libraryAndSaveButtonPanel.add(libraryButton);
        libraryAndSaveButtonPanel.add(saveButton);

        JPanel encounterAndLoadButtonPanel = new JPanel(new GridLayout(2, 1));
        encounterAndLoadButtonPanel.add(encounterButton);
        encounterAndLoadButtonPanel.add(loadButton);

        JPanel smallButtonsPanel = new JPanel(new GridLayout(1, 3));
        smallButtonsPanel.add(libraryAndSaveButtonPanel);
        smallButtonsPanel.add(encounterAndLoadButtonPanel);
        smallButtonsPanel.add(quitButton);

        buttonsPanel.add(customRollButton);
        buttonsPanel.add(smallButtonsPanel);
    }

    @Override
    // EFFECTS: processes button presses from user
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == customRollButton) {
            new CustomRollFrame();
        } else if (e.getSource() == encounterButton) {
            menuManagerPanel.setMenu("main");
            menuManagerPanel.getMainMenuPanel().setState("encounter");
        } else if (e.getSource() == libraryButton) {
            menuManagerPanel.setMenu("main");
            menuManagerPanel.getMainMenuPanel().setState("library");
        } else if (e.getSource() == loadButton) {
            menuManagerPanel.tryLoad();
        } else if (e.getSource() == saveButton) {
            menuManagerPanel.trySave();
        } else if (e.getSource() == quitButton) {
            menuManagerPanel.confirmQuit();
        }
    }
}
