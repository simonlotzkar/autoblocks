package ui.panels.menus;

import ui.frames.CustomRollFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Represents the title menu where the application launches to and terminates from as well as other options
public class TitleMenuPanel extends MenuPanel implements ActionListener {
    private Font dungeonFont;
    private final JPanel buttonsPanel = new JPanel(new GridLayout(2, 1));

    // images
    private static final JLabel MAIN_BANNER_LABEL = new JLabel("AUTOBLOCKS");
    private static final ImageIcon D20_IMAGE_ICON = new ImageIcon(ICON_DIRECTORY + "d20.png");
    private static final ImageIcon EXIT_IMAGE_ICON = new ImageIcon(ICON_DIRECTORY + "exit.png");
    private static final ImageIcon LIBRARY_IMAGE_ICON = new ImageIcon(ICON_DIRECTORY + "books.png");
    private static final ImageIcon ENCOUNTER_IMAGE_ICON = new ImageIcon(ICON_DIRECTORY + "crossedSwords.png");
    private static final ImageIcon LOAD_IMAGE_ICON = new ImageIcon(ICON_DIRECTORY + "folder.png");
    private static final ImageIcon SAVE_IMAGE_ICON = new ImageIcon(ICON_DIRECTORY + "save.png");
    private static final Image BANNER = Toolkit.getDefaultToolkit().getImage("./data/images/titleBanner.jpg");

    // buttons
    private final JButton customRollButton = new JButton("Open Custom Roller");
    private final JButton quitButton = new JButton("Quit");
    private final JButton libraryButton = new JButton("Library");
    private final JButton encounterButton = new JButton("Encounter");
    private final JButton loadButton = new JButton("Load");
    private final JButton saveButton = new JButton("Save");

    // MODIFIES: this
    // EFFECTS: constructs this panel
    public TitleMenuPanel(MenuManagerPanel menuManagerPanel) {
        super(new BorderLayout(), menuManagerPanel); // sets layout and menu managers, visibility, and size

        initializeButtons();
        initializeButtonPanel();

        try {
            dungeonFont = Font.createFont(Font.TRUETYPE_FONT, new File("./data/dungeon(BySaesarezNovandito).TTF"));
            MAIN_BANNER_LABEL.setFont(dungeonFont.deriveFont(150f));
        } catch (IOException | FontFormatException e) {
            JOptionPane.showMessageDialog(this, "Could not load dungeon font! ("
                    + e.getMessage() + ")", "Failure!", JOptionPane.WARNING_MESSAGE);
        }

        MAIN_BANNER_LABEL.setHorizontalAlignment(SwingConstants.CENTER);
        MAIN_BANNER_LABEL.setForeground(Color.WHITE);

        add(MAIN_BANNER_LABEL, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: draws the background image
    public void paintComponent(Graphics g) {
        g.drawImage(BANNER, 0, 0, MAIN_BANNER_LABEL.getWidth(), MAIN_BANNER_LABEL.getHeight(), this);
    }

    // MODIFIES: this
    // EFFECTS: sets parameters for all buttons
    private void initializeButtons() {
        List<JButton> buttonList = new ArrayList<>();

        buttonList.add(customRollButton);
        buttonList.add(quitButton);
        buttonList.add(encounterButton);
        buttonList.add(libraryButton);
        buttonList.add(loadButton);
        buttonList.add(saveButton);

        for (JButton jb : buttonList) {
            jb.addActionListener(this);
        }
        initializeButtonIcons();
    }

    // EFFECTS: sets buttons icon parameter
    private void initializeButtonIcons() {
        customRollButton.setIcon(scaleIcon(D20_IMAGE_ICON));
        quitButton.setIcon(scaleIcon(EXIT_IMAGE_ICON));
        encounterButton.setIcon(scaleIcon(ENCOUNTER_IMAGE_ICON));
        libraryButton.setIcon(scaleIcon(LIBRARY_IMAGE_ICON));
        loadButton.setIcon(scaleIcon(LOAD_IMAGE_ICON));
        saveButton.setIcon(scaleIcon(SAVE_IMAGE_ICON));
    }

    // EFFECTS: returns scaled given image icon
    private ImageIcon scaleIcon(ImageIcon imageIcon) {
        return new ImageIcon(imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
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
        buttonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    @Override
    // MODIFIES: menuManagerPanel
    // EFFECTS: processes button presses from user
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == customRollButton) {
            new CustomRollFrame();
        } else if (e.getSource() == encounterButton) {
            menuManagerPanel.getMainMenuPanel().setDisplays("encounter");
            menuManagerPanel.setMenu("main");
        } else if (e.getSource() == libraryButton) {
            menuManagerPanel.getMainMenuPanel().setDisplays("library");
            menuManagerPanel.setMenu("main");
        } else if (e.getSource() == loadButton) {
            menuManagerPanel.tryLoad();
        } else if (e.getSource() == saveButton) {
            menuManagerPanel.trySave();
        } else if (e.getSource() == quitButton) {
            menuManagerPanel.confirmQuit();
        }
    }
}
