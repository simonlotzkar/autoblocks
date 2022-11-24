package ui.panels;

import ui.frames.CustomRollFrame;
import ui.panels.menus.MainMenuPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents...
public class SideDisplayPanel extends DisplayPanel implements ActionListener, ListSelectionListener {
    // layouts
    private final CardLayout sideDisplayCardLayout = new CardLayout();
    private final CardLayout librarySidePanelCardLayout = new CardLayout();

    // panels
    private final JPanel encounterSidePanel = new JPanel(new GridLayout(2, 1));
    private final JPanel librarySidePanel = new JPanel(new BorderLayout());
    private final JPanel librarySidePanelScrollPanePanel = new JPanel(librarySidePanelCardLayout);
    private final JPanel outputLogPanel = new JPanel(new BorderLayout());
    private final JPanel actionsPanel = new JPanel(new BorderLayout());
    private final JPanel outputLogTitlePanel = new JPanel(new GridLayout(1, 2));
    private final JPanel actionsTitlePanel = new JPanel(new GridLayout(1, 2));

    // buttons
    private final JButton rollActionsButton = new JButton("Roll Selected");
    private final JButton customRollButton = new JButton("Custom Roll");
    private final JButton customActionButton = new JButton("Custom Action");
    private JButton rollCheckButton;
    private JButton rollInitiativeButton;

    // components
    private final StatBlockDisplayPanel statBlockDisplayPanel;
    private final StatBlockCreationDisplayPanel statBlockCreationDisplayPanel;
    private final JList<model.statblockfields.Action> actionsJList;
    private final JScrollPane outputLogScrollPane = new JScrollPane();
    private final JScrollPane actionsScrollPane = new JScrollPane();
    private JTextArea outputLogTextArea;

    // EFFECTS: constructs this display panel
    public SideDisplayPanel(MainMenuPanel mainMenuPanel) {
        super(null, mainMenuPanel); // sets the layout manager, mainmenu panel, size, and visibility

        statBlockDisplayPanel = new StatBlockDisplayPanel(mainMenuPanel);
        statBlockCreationDisplayPanel = new StatBlockCreationDisplayPanel(mainMenuPanel);
        actionsJList = new JList<>(getActionsListModel());

        importButtons();
        initializeEncounterSidePanel();
        initializeLibrarySidePanel();

        this.setLayout(sideDisplayCardLayout);
        this.add(encounterSidePanel, "encounter");
        this.add(librarySidePanel, "library");
    }

    // EFFECTS: ...
    private void importButtons() {
        rollCheckButton = mainMenuPanel.getRollCheckButton();
        rollInitiativeButton = mainMenuPanel.getRollInitiativeButton();
    }

    // EFFECTS: ...
    public void setSideDisplay(String s) {
        sideDisplayCardLayout.show(this, s);
    }

    // EFFECTS: ...
    private void initializeEncounterSidePanel() {
        initializeOutputLogPanel();
        initializeActionsPanel();

        encounterSidePanel.add(outputLogPanel);
        encounterSidePanel.add(actionsPanel);
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
        outputLogTextArea = new JTextArea(10, 10);
        outputLogTextArea.setEditable(false);

        outputLogScrollPane.setViewportView(outputLogTextArea);
        outputLogScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        outputLogScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    // EFFECTS: ...
    private void initializeActionsPanel() {
        initializeActionsJList();
        initializeActionsTitlePanel();
        initializeActionsScrollPane();

        actionsPanel.add(actionsTitlePanel, BorderLayout.NORTH);
        actionsPanel.add(actionsScrollPane, BorderLayout.CENTER);
        actionsPanel.add(rollActionsButton, BorderLayout.SOUTH);
    }

    // EFFECTS: ...
    private void initializeActionsJList() {
        actionsJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        actionsJList.setLayoutOrientation(JList.VERTICAL_WRAP);
        actionsJList.setVisibleRowCount(-1);
        actionsJList.addListSelectionListener(this);
    }

    // EFFECTS: ...
    private DefaultListModel<model.statblockfields.Action> getActionsListModel() {
        DefaultListModel<model.statblockfields.Action> actionsListModel = new DefaultListModel<>();
        DefaultListModel<model.Character> encounterListModel
                = mainMenuPanel.getMenuManagerPanel().getEncounterListModel();
        String selectedGroupName = mainMenuPanel.getSelectedGroup();

        if (selectedGroupName != null) {
            for (int i = 0; i < encounterListModel.getSize(); i++) {
                if (encounterListModel.getElementAt(i).getTitle().getGroup().equals(selectedGroupName)) {
                    actionsListModel.addAll(encounterListModel.getElementAt(i).getActions());
                }
            }
        } else if (mainMenuPanel.getSelectedCharacter() != null) {
            actionsListModel.addAll(mainMenuPanel.getSelectedCharacter().getActions());
        } else {
            for (int i = 0; i < encounterListModel.getSize(); i++) {
                actionsListModel.addAll(encounterListModel.getElementAt(i).getActions());
            }
        }
        return actionsListModel;
    }

    // EFFECTS: ...
    private void initializeActionsTitlePanel() {
        actionsTitlePanel.add(new JLabel("Actions in Current Context:"));
        actionsTitlePanel.add(customActionButton);
    }

    // EFFECTS: ...
    private void initializeActionsScrollPane() {
        actionsScrollPane.setViewportView(actionsJList);
        actionsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        actionsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    // EFFECTS: ...
    private void initializeLibrarySidePanel() {
        librarySidePanelScrollPanePanel.add(statBlockDisplayPanel, "statBlock");
        librarySidePanelScrollPanePanel.add(statBlockCreationDisplayPanel, "statBlockCreation");

        ScrollPane librarySidePanelScrollPane = new ScrollPane();
        librarySidePanelScrollPane.add(librarySidePanelScrollPanePanel);

        librarySidePanel.add(new JLabel("Selected:"), BorderLayout.NORTH);
        librarySidePanel.add(librarySidePanelScrollPane, BorderLayout.CENTER);
    }

    // EFFECTS: depending on what display is showing, sends the given action event to the corresponding display panel
    public void passAction(ActionEvent e) {
        if (statBlockDisplayPanel.isShowing()) {
            statBlockDisplayPanel.actionPerformed(e);
        }
        if (statBlockCreationDisplayPanel.isShowing()) {
            statBlockCreationDisplayPanel.actionPerformed(e);
        }
    }

    // EFFECTS: ...
    private void rollActions() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i : actionsJList.getSelectedIndices()) {
            stringBuilder.append("\n");
            stringBuilder.append(actionsJList.getModel().getElementAt(i).getRollString());
        }

        outputLogTextArea.append(stringBuilder.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rollCheckButton) {
//            rollCheck();
        } else if (e.getSource() == rollInitiativeButton) {
//            rollInitiative();
        } else if (e.getSource() == rollActionsButton) {
            rollActions();
        } else if (e.getSource() == customRollButton) {
            new CustomRollFrame();
        } else if (e.getSource() == customActionButton) {
//            new CustomActionFrame();
        }
    }

    @Override
    // EFFECTS: ...
    public void valueChanged(ListSelectionEvent e) {
        rollActionsButton.setEnabled(!e.getValueIsAdjusting());
        rollCheckButton.setEnabled(!e.getValueIsAdjusting());
        rollInitiativeButton.setEnabled(!e.getValueIsAdjusting());
        rollActionsButton.setEnabled(actionsJList.getSelectedIndices().length >= 1);
    }
}
