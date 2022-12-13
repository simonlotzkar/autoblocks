package ui.panels;

import ui.scrollpanes.LibrarySideDisplayScrollPane;
import ui.scrollpanes.RollLogScrollPane;
import ui.scrollpanes.RollableActionsScrollPane;
import ui.frames.CustomActionFrame;
import ui.frames.CustomRollFrame;
import ui.panels.menus.MainMenuPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Represents a side panel that can be an encounter, statblock display, or statblock creation display
public class SideDisplayPanel extends DisplayPanel implements ActionListener {
    private final CardLayout sideDisplayCardLayout = new CardLayout();

    // images
    private static final ImageIcon D20_ICON = new ImageIcon(ICON_DIRECTORY + "d20.png");
    private static final ImageIcon DICE_ICON = new ImageIcon(ICON_DIRECTORY + "dice.png");
    private static final ImageIcon SWORD_ICON = new ImageIcon(ICON_DIRECTORY + "sword.png");

    // panels
    private final JPanel encounterSideDisplayPanel = new JPanel(new GridLayout(2, 1));
    private final JPanel rollLogPanel = new JPanel(new BorderLayout());
    private final JPanel rollLogTitlePanel = new JPanel(new GridLayout(1, 2));
    private final JPanel rollableActionsPanel = new JPanel(new BorderLayout());
    private final JPanel rollableActionsTitlePanel = new JPanel(new GridLayout(1, 2));

    // scroll panes
    private final LibrarySideDisplayScrollPane librarySideDisplayScrollPane;
    private final RollLogScrollPane rollLogScrollPane;
    private final RollableActionsScrollPane rollableActionsScrollPane;

    // buttons
    private final JButton rollRollableActionsButton = new JButton("Roll Selected Actions");
    private final JButton customRollButton = new JButton("Open Custom Dice Roller");
    private final JButton customRollableActionButton = new JButton("Open Custom Action Roller");

    // MODIFIES: this
    // EFFECTS: constructs this display panel
    public SideDisplayPanel(MainMenuPanel mainMenuPanel) {
        super(null, mainMenuPanel); // sets the layout manager, mainmenu panel, size, and visibility

        librarySideDisplayScrollPane = new LibrarySideDisplayScrollPane(mainMenuPanel);
        rollLogScrollPane = new RollLogScrollPane();
        rollableActionsScrollPane = new RollableActionsScrollPane(mainMenuPanel);

        initializeEncounterSideDisplayPanel();

        JPanel librarySideDisplayPanel = new JPanel(new BorderLayout());
        librarySideDisplayPanel.add(new JLabel("Selected:"), BorderLayout.NORTH);
        librarySideDisplayPanel.add(librarySideDisplayScrollPane, BorderLayout.CENTER);

        setLayout(sideDisplayCardLayout);
        add(encounterSideDisplayPanel, "encounter");
        add(librarySideDisplayPanel, "library");
    }

    // MODIFIES: this
    // EFFECTS: sets the side display to show whatever the given string is then refreshes
    public void setDisplay(String s) {
        if ("statBlock".equals(s) || "statBlockCreation".equals(s)) {
            sideDisplayCardLayout.show(this, "library");
            librarySideDisplayScrollPane.setDisplay(s);
        } else if ("library".equals(s)) {
            sideDisplayCardLayout.show(this, "library");
            librarySideDisplayScrollPane.setDisplay("statBlock");
            librarySideDisplayScrollPane.getStatBlockDisplayTextArea().setSelectedStatBlock(null);
        } else {
            sideDisplayCardLayout.show(this, "encounter");
        }
        refresh();
    }

    // MODIFIES: this
    // EFFECTS: refreshes subcomponents, revalidates, and repaints
    public void refresh() {
        rollableActionsScrollPane.refresh();
        librarySideDisplayScrollPane.refresh();
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: sets up the encounter side display sub panels
    private void initializeEncounterSideDisplayPanel() {
        initializeEncounterSideDisplayButtons();
        initializeRollLogPanel();
        initializeRollableActionsPanel();

        encounterSideDisplayPanel.add(rollLogPanel);
        encounterSideDisplayPanel.add(rollableActionsPanel);
    }

    // MODIFIES: this
    // EFFECTS: formats all buttons
    private void initializeEncounterSideDisplayButtons() {
        ArrayList<JButton> buttonList = new ArrayList<>();

        buttonList.add(customRollableActionButton);
        buttonList.add(customRollButton);
        buttonList.add(rollRollableActionsButton);

        for (JButton jb : buttonList) {
            jb.addActionListener(this);
        }
        initializeButtonIcons();
    }

    // MODIFIES: this
    // EFFECTS: sets the button icons
    private void initializeButtonIcons() {
        customRollableActionButton.setIcon(scaleIcon(SWORD_ICON));
        customRollButton.setIcon(scaleIcon(D20_ICON));
        rollRollableActionsButton.setIcon(scaleIcon(DICE_ICON));
    }

    // MODIFIES: this
    // EFFECTS: returns a scaled version of the given icon
    private ImageIcon scaleIcon(ImageIcon imageIcon) {
        return new ImageIcon(imageIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
    }

    // MODIFIES: this
    // EFFECTS: sets up the roll log panel
    private void initializeRollLogPanel() {
        rollLogTitlePanel.add(new JLabel("Roll Log:"));
        rollLogTitlePanel.add(customRollButton);

        rollLogPanel.add(rollLogTitlePanel, BorderLayout.NORTH);
        rollLogPanel.add(rollLogScrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: sets up the rollable actions panel
    private void initializeRollableActionsPanel() {
        rollableActionsTitlePanel.add(new JLabel("Actions in Current Context:"));
        rollableActionsTitlePanel.add(customRollableActionButton);

        rollableActionsPanel.add(rollableActionsTitlePanel, BorderLayout.NORTH);
        rollableActionsPanel.add(rollableActionsScrollPane, BorderLayout.CENTER);
        rollableActionsPanel.add(rollRollableActionsButton, BorderLayout.SOUTH);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: processes button presses for this frame
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rollRollableActionsButton) {
            rollableActionsScrollPane.rollSelectedRollableActions();
        } else if (e.getSource() == customRollButton) {
            new CustomRollFrame();
        } else if (e.getSource() == customRollableActionButton) {
            new CustomActionFrame();
        }
    }

    // MODIFIES: this
    // EFFECTS: enables or disables the roll rollable actions button if the list selection in the rollable actions
    //          scroll pane is stable or changing, respectively
    public void passListSelectionEvent(ListSelectionEvent e) {
        rollRollableActionsButton.setEnabled(!e.getValueIsAdjusting());
    }

   // -----------------------------------------------------------------------
   // getters
   // EFFECTS: returns the library side display scroll pane
    public LibrarySideDisplayScrollPane getLibrarySideDisplayScrollPane() {
        return librarySideDisplayScrollPane;
    }

    // EFFECTS: returns the roll log scroll pane
    public RollLogScrollPane getRollLogScrollPane() {
        return rollLogScrollPane;
    }
}
