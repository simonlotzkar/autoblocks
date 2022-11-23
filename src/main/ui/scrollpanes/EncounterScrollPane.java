package ui.scrollpanes;

import model.Character;
import ui.panels.ListMenuPanelDELETE;
import ui.panels.MenuCardPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EncounterScrollPane extends ListMenuPanelDELETE {
    private final JList<model.Character> encounterJList;
    private JScrollPane scrollPane;

    // images
    private static final ImageIcon BANNER_IMAGE_ICON = new ImageIcon("./data/images/banners/banner1.jpg");
    private static final ImageIcon PLUS_IMAGE_ICON = new ImageIcon(ICON_DIRECTORY + "plusIcon.png");

    // buttons
    private final JButton selectCharacterButton = new JButton("Open character's sheet");
    private final JButton selectGroupButton = new JButton("Open character's group");
    private final JButton addToGroupButton = new JButton("Add selected to group");

    // EFFECTS: constructs this frame
    public EncounterScrollPane(MenuCardPanel menuCardPanel) {
        super(new BorderLayout(), menuCardPanel);
        this.encounterJList = new JList<>(menuCardPanel.getEncounterListModel());
        initializeAll();
    }

    // EFFECTS: constructs all components/containers in this panel
    protected void initializeAll() {
        initializeScrollPane();
        initializeNorthBorderPanel(BANNER_IMAGE_ICON);
        initializeButtonPanel();

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(northBorderPanel, BorderLayout.NORTH);
        this.add(new JPanel(), BorderLayout.EAST);
        this.add(buttonsPanel, BorderLayout.SOUTH);
        this.add(new JPanel(), BorderLayout.WEST);
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: constructs the scroll pane and its components (listmenu's jlist)
    protected void initializeScrollPane() {
        encounterJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        encounterJList.setLayoutOrientation(JList.VERTICAL_WRAP);
        encounterJList.setVisibleRowCount(-1);
        encounterJList.addListSelectionListener(this);
        encounterJList.setFixedCellWidth(720);
        encounterJList.setVisible(true);

        scrollPane = new JScrollPane(encounterJList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVisible(true);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: sets parameters for all buttons
    protected void initializeButtons() {
        super.initializeButtons();

        selectCharacterButton.setIcon(PLUS_IMAGE_ICON);
        selectGroupButton.setIcon(PLUS_IMAGE_ICON);
        addToGroupButton.setIcon(PLUS_IMAGE_ICON);

        buttonList.add(selectCharacterButton);
        buttonList.add(selectGroupButton);
        buttonList.add(addToGroupButton);
    }

    // EFFECTS: prompts user for a group name, sets selected characters to the given group name
    //          or if nothing is given does nothing
    private void addSelectedToGroup() {
        ListModel<Character> encounterListModel = encounterJList.getModel();
        try {
            String command = JOptionPane.showInputDialog(this,
                    "What do you want to name this group?",
                    "User Input Needed",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    null,
                    "").toString();

            if (command.isBlank() || command.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invalid group name given.", "Failure!",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                for (int i : encounterJList.getSelectedIndices()) {
                    encounterListModel.getElementAt(i).getTitle().setGroup(command);
                }
            }
        } catch (NullPointerException exception) {
            // user cancelled so do nothing
        }
    }

    @Override
    // EFFECTS: processes button presses from user
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectCharacterButton) {
            menuCardPanel.changeMenu("characterMenu");
            menuCardPanel.setSelectedCharacter(encounterJList.getModel()
                    .getElementAt(encounterJList.getSelectedIndex()));
        }
        if (e.getSource() == selectGroupButton) {
            menuCardPanel.changeMenu("groupMenu");
            menuCardPanel.setSelectedGroup(encounterJList.getModel()
                    .getElementAt(encounterJList.getSelectedIndex()).getTitle().getGroup());
        }
        if (e.getSource() == addToGroupButton) {
            addSelectedToGroup();
            this.revalidate();
            this.repaint();
        }
        if (e.getSource() == backButton) {
            menuCardPanel.changeMenu("mainMenu");
        }
    }

    @Override
    // EFFECTS: disables and enables buttons when user is changing list selection or if just one item is selected
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            selectCharacterButton.setEnabled(false);
            selectGroupButton.setEnabled(false);
            addToGroupButton.setEnabled(false);
        } else if (!e.getValueIsAdjusting()) {
            selectCharacterButton.setEnabled(true);
            selectGroupButton.setEnabled(true);
            addToGroupButton.setEnabled(true);
        }

        if (e.getLastIndex() > 0) {
            selectCharacterButton.setEnabled(false);
            selectGroupButton.setEnabled(false);
        } else {
            selectCharacterButton.setEnabled(true);
            selectGroupButton.setEnabled(true);
        }
    }
}
