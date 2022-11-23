package ui.panels;

import ui.frames.CustomRollFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/*
titleMenuPanel = borderlayout
	NORTH = bannerCardPanel (cardlayout)
		card1 = banner image
		card2 = mainMenuTitleLabel
CENTER = buttonPanel (gridlayout2x3)
 */

public class TitleMenuPanel extends MenuPanel implements ActionListener {
    // panels
    private final JPanel buttonsPanel = new JPanel();
    private final JPanel smallButtonsPanel = new JPanel();
    private final JPanel libraryAndSaveButtonPanel = new JPanel();
    private final JPanel encounterAndLoadButtonPanel = new JPanel();

    // images
    private static final JLabel MAIN_BANNER_LABEL = new JLabel(new ImageIcon(
            "./data/images/banners/tallBanner.jpg"));
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
    public TitleMenuPanel(MenuCardPanel menuCardPanel) {
        super(new BorderLayout(), menuCardPanel);

        initializeButtons();

        this.add(MAIN_BANNER_LABEL, BorderLayout.CENTER);
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

        buttonsPanel.setLayout(new GridLayout(2, 1));
        buttonsPanel.add(customRollButton);
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
            menuCardPanel.changeMenu("encounterMenu");
        }
        if (e.getSource() == libraryButton) {
            menuCardPanel.changeMenu("libraryMenu");
        }
        if (e.getSource() == loadButton) {
            menuCardPanel.tryLoad();
        }
        if (e.getSource() == saveButton) {
            menuCardPanel.trySave();
        }
        if (e.getSource() == quitButton) {
            menuCardPanel.confirmQuit();
        }
    }
}
