package ui.panels;

import model.NPC;
import model.StatBlock;
import model.statblockfields.RollableAction;
import ui.frames.CustomActionFrame;
import ui.frames.CustomRollFrame;
import ui.panels.menus.MainMenuPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Represents...
public class SideDisplayPanel extends DisplayPanel implements ActionListener, ListSelectionListener {
    // selections
    private StatBlock selectedStatBlock;

    // layouts
    private final CardLayout sideDisplayCardLayout = new CardLayout();
    private final CardLayout librarySidePanelCardLayout = new CardLayout();

    // images
    private static final ImageIcon D20_ICON = new ImageIcon(ICON_DIRECTORY + "d20.png");
    private static final ImageIcon DICE_ICON = new ImageIcon(ICON_DIRECTORY + "dice.png");
    private static final ImageIcon SWORD_ICON = new ImageIcon(ICON_DIRECTORY + "sword.png");

    // panels
    private final JPanel encounterSideDisplayPanel = new JPanel(new GridLayout(2, 1));
    private final JPanel librarySideDisplayPanel = new JPanel(new BorderLayout());
    private final JPanel librarySideDisplayCardManagerPanel = new JPanel(librarySidePanelCardLayout);
    private final JPanel outputLogPanel = new JPanel(new BorderLayout());
    private final JPanel rollableActionsPanel = new JPanel(new BorderLayout());
    private final JPanel outputLogTitlePanel = new JPanel(new GridLayout(1, 2));
    private final JPanel rollableActionsTitlePanel = new JPanel(new GridLayout(1, 2));

    private StatBlockDisplayTextArea statBlockDisplayTextArea;
    private StatBlockCreationDisplayPanel statBlockCreationDisplayPanel;

    // scrollpanes
    private final JScrollPane librarySideDisplayScrollPane = new JScrollPane();
    private final JScrollPane outputLogScrollPane = new JScrollPane();
    private final JScrollPane rollableActionsScrollPane = new JScrollPane();

    // buttons
    private final JButton rollRollableActionsButton = new JButton("Roll Selected");
    private final JButton customRollButton = new JButton("Custom Dice Roller");
    private final JButton customRollableActionButton = new JButton("Custom Action Roller");

    // components
    private JTextArea outputLogTextArea;
    private final JList<RollableAction> rollableActionsList;
    private final DefaultListModel<RollableAction> rollableActionsListModel;
    private final DefaultListModel<NPC> encounterListModel;

    // EFFECTS: constructs this display panel
    public SideDisplayPanel(MainMenuPanel mainMenuPanel) {
        super(null, mainMenuPanel); // sets the layout manager, mainmenu panel, size, and visibility
        encounterListModel = mainMenuPanel.getMenuManagerPanel().getEncounterListModel();
        rollableActionsListModel = new DefaultListModel<>();

        initializeButtons();
        initializeRollableActionsListModel();
        rollableActionsList = new JList<>(rollableActionsListModel);

        initializeEncounterSideDisplayPanel();
        initializeLibrarySideDisplayPanel();

        this.setLayout(sideDisplayCardLayout);
        this.add(encounterSideDisplayPanel, "encounter");
        this.add(librarySideDisplayPanel, "library");
    }

    // EFFECTS: ...
    private void initializeButtons() {
        ArrayList<JButton> buttonList = new ArrayList<>();

        buttonList.add(customRollableActionButton);
        buttonList.add(customRollButton);
        buttonList.add(rollRollableActionsButton);

        for (JButton jb : buttonList) {
            jb.addActionListener(this);
        }
        initializeButtonIcons();
    }

    // EFFECTS: ...
    private void initializeButtonIcons() {
        customRollableActionButton.setIcon(scaleIcon(SWORD_ICON));
        customRollButton.setIcon(scaleIcon(D20_ICON));
        rollRollableActionsButton.setIcon(scaleIcon(DICE_ICON));
    }

    // EFFECTS: ...
    private ImageIcon scaleIcon(ImageIcon imageIcon) {
        return new ImageIcon(imageIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
    }

    // EFFECTS: ...
    private void initializeEncounterSideDisplayPanel() {
        initializeOutputLogPanel();
        initializeRollableActionsPanel();

        encounterSideDisplayPanel.add(outputLogPanel);
        encounterSideDisplayPanel.add(rollableActionsPanel);
    }

    // EFFECTS: ...
    private void initializeOutputLogPanel() {
        initializeOutputLogTitlePanel();
        initializeOutputLogScrollPane();

        outputLogPanel.add(outputLogTitlePanel, BorderLayout.NORTH);
        outputLogPanel.add(outputLogScrollPane, BorderLayout.CENTER);
    }

    // EFFECTS: ...
    private void initializeOutputLogTitlePanel() {
        outputLogTitlePanel.add(new JLabel("Roll Output Log:"));
        outputLogTitlePanel.add(customRollButton);
    }

    // EFFECTS: ...
    private void initializeOutputLogScrollPane() {
        outputLogTextArea = new JTextArea();
        outputLogTextArea.setEditable(false);

        outputLogScrollPane.setViewportView(outputLogTextArea);
        outputLogScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        outputLogScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    // EFFECTS: ...
    private void initializeRollableActionsPanel() {
        initializeRollableActionsList();
        initializeRollableActionsTitlePanel();
        initializeRollableActionsScrollPane();

        rollableActionsPanel.add(rollableActionsTitlePanel, BorderLayout.NORTH);
        rollableActionsPanel.add(rollableActionsScrollPane, BorderLayout.CENTER);
        rollableActionsPanel.add(rollRollableActionsButton, BorderLayout.SOUTH);
    }

    // EFFECTS: ...
    private void initializeRollableActionsList() {
        rollableActionsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        rollableActionsList.setLayoutOrientation(JList.VERTICAL);
        rollableActionsList.setVisibleRowCount(-1);
        rollableActionsList.addListSelectionListener(this);
    }

    // EFFECTS: ...
    public void initializeRollableActionsListModel() {
        String selectedGroupName = mainMenuPanel.getMainDisplayPanel().getSelectedGroupName();
        rollableActionsListModel.removeAllElements();

        if (selectedGroupName != null) {
            for (int i = 0; i < encounterListModel.getSize(); i++) {
                if (encounterListModel.getElementAt(i).hasGroup() && encounterListModel.getElementAt(i).getTitle()
                        .getGroup().equals(selectedGroupName)) {
                    addAllRollableActionsToModel(encounterListModel.getElementAt(i).getRollableActions());
                }
            }
        } else if (mainMenuPanel.getMainDisplayPanel().getSelectedNonPlayerCharacter() != null) {
            addAllRollableActionsToModel(mainMenuPanel.getMainDisplayPanel()
                    .getSelectedNonPlayerCharacter().getRollableActions());
        } else {
            for (int i = 0; i < encounterListModel.getSize(); i++) {
                addAllRollableActionsToModel(encounterListModel.getElementAt(i).getRollableActions());
            }
        }
    }

    // EFFECTS: ...
    //          this is only here because the autograder didn't like using the native addAll()
    private void addAllRollableActionsToModel(java.util.List<RollableAction> rollableActionsList) {
        for (RollableAction a : rollableActionsList) {
            rollableActionsListModel.addElement(a);
        }
    }

    // EFFECTS: ...
    private void initializeRollableActionsTitlePanel() {
        rollableActionsTitlePanel.add(new JLabel("Actions in Current Context:"));
        rollableActionsTitlePanel.add(customRollableActionButton);
    }

    // EFFECTS: ...
    private void initializeRollableActionsScrollPane() {
        rollableActionsScrollPane.setViewportView(rollableActionsList);
        rollableActionsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rollableActionsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    // EFFECTS: ...
    private void initializeLibrarySideDisplayPanel() {
        statBlockDisplayTextArea = new StatBlockDisplayTextArea(mainMenuPanel);
        statBlockCreationDisplayPanel = new StatBlockCreationDisplayPanel(mainMenuPanel);

        librarySideDisplayCardManagerPanel.add(statBlockDisplayTextArea, "statBlock");
        librarySideDisplayCardManagerPanel.add(statBlockCreationDisplayPanel, "statBlockCreation");

        librarySideDisplayScrollPane.setViewportView(librarySideDisplayCardManagerPanel);
        librarySideDisplayScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        librarySideDisplayScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        librarySideDisplayPanel.add(new JLabel("Selected:"), BorderLayout.NORTH);
        librarySideDisplayPanel.add(librarySideDisplayScrollPane, BorderLayout.CENTER);
    }

    // EFFECTS: ...
    private void rollRollableActions() {
        for (int i : rollableActionsList.getSelectedIndices()) {
            printToOutputLog(rollableActionsList.getModel().getElementAt(i).generateFullRollString());
        }
    }

    // EFFECTS: ...
    public void printToOutputLog(String s) {
        if (outputLogTextArea.getText().equals("")) {
            outputLogTextArea.append(s);
        } else {
            outputLogTextArea.append("\n" + s);
        }
    }

    // EFFECTS: ...
    public void setSideDisplay(String s) {
        switch (s) {
            case "statBlockCreation":
                initializeLibrarySideDisplayPanel();
                sideDisplayCardLayout.show(this, "library");
                librarySidePanelCardLayout.show(librarySideDisplayCardManagerPanel, s);
                break;
            case "encounter":
                selectedStatBlock = null;
                sideDisplayCardLayout.show(this, s);
                rollableActionsList.removeAll();
                break;
            case "statBlock":
                sideDisplayCardLayout.show(this, "library");
                librarySidePanelCardLayout.show(librarySideDisplayCardManagerPanel, "statBlock");
                statBlockDisplayTextArea.initializeAll();
                break;
            default:
                sideDisplayCardLayout.show(this, "library");
                librarySidePanelCardLayout.show(librarySideDisplayCardManagerPanel, "statBlock");
                selectedStatBlock = null;
                statBlockDisplayTextArea.initializeAll();
                break;
        }
    }

    // EFFECTS: ...
    public StatBlockCreationDisplayPanel getStatBlockCreationDisplayPanel() {
        return statBlockCreationDisplayPanel;
    }

    // EFFECTS: ...
    public StatBlockDisplayTextArea getStatBlockDisplayTextArea() {
        return statBlockDisplayTextArea;
    }

    @Override
    // EFFECTS: ...
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rollRollableActionsButton) {
            rollRollableActions();
        } else if (e.getSource() == customRollButton) {
            new CustomRollFrame();
        } else if (e.getSource() == customRollableActionButton) {
            new CustomActionFrame();
        }
    }

    @Override
    // EFFECTS: ...
    public void valueChanged(ListSelectionEvent e) {
        rollRollableActionsButton.setEnabled(!e.getValueIsAdjusting());
        rollRollableActionsButton.setEnabled(rollableActionsList.getSelectedIndices().length >= 1);
    }

    // EFFECTS: ...
    public StatBlock getSelectedStatBlock() {
        return selectedStatBlock;
    }

    // EFFECTS: ...
    public void setSelectedStatBlock(StatBlock selectedStatBlock) {
        this.selectedStatBlock = selectedStatBlock;
    }
}
