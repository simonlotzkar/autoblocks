package ui.panels;

import model.Character;
import model.StatBlock;
import ui.panels.menus.MainMenuPanel;
import ui.panels.menus.MenuManagerPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

// Represents...
public class LibraryDisplayPanel extends DisplayPanel {
    // EFFECTS: constructs this display panel
    public LibraryDisplayPanel(MainMenuPanel mainMenuPanel) {
        super(new CardLayout(), mainMenuPanel); // sets the layout manager, mainmenu panel, size, and visibility
    }
//
//    // EFFECTS: constructs this frame
//    public LibraryListPanel(MenuManagerPanel menuManagerPanel) {
//        super(new BorderLayout(), menuManagerPanel);
//        this.libraryJList = new JList<>(menuManagerPanel.getLibraryListModel());
//        initializeAll();
//    }
//
//    // EFFECTS: constructs all components/containers in this panel
//    protected void initializeAll() {
//        initializeScrollPane();
//        initializeNorthBorderPanel(BANNER_IMAGE_ICON);
//        initializeButtonPanel();
//
//        this.add(scrollPane, BorderLayout.CENTER);
//        this.add(northBorderPanel, BorderLayout.NORTH);
//        this.add(new JPanel(), BorderLayout.EAST);
//        this.add(buttonsPanel, BorderLayout.SOUTH);
//        this.add(new JPanel(), BorderLayout.WEST);
//        this.setVisible(true);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: constructs the scroll pane and its components (listmenu's jlist)
//    protected void initializeScrollPane() {
//        libraryJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//        libraryJList.setLayoutOrientation(JList.VERTICAL_WRAP);
//        libraryJList.setVisibleRowCount(-1);
//        libraryJList.addListSelectionListener(this);
//        libraryJList.setFixedCellWidth(720);
//        libraryJList.setVisible(true);
//
//        scrollPane = new JScrollPane(libraryJList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        scrollPane.setVisible(true);
//    }
//
//
//
//    // EFFECTS: prompts user for the number of copies to add then adds them or cancels or catches exceptions
//    private void tryAddSelectedToEncounter() {
//        try {
//            int command = Integer.parseInt((JOptionPane.showInputDialog(this,
//                    "How many copies of the selected statblocks do you want to add?",
//                    "User Input Needed",
//                    JOptionPane.INFORMATION_MESSAGE,
//                    null,
//                    null,
//                    "")).toString());
//            DefaultListModel<Character> newEncounterListModel = addSelectedToEncounter(command);
//            menuCardPanel.setEncounterListModel(newEncounterListModel);
//        } catch (NumberFormatException exception) {
//            JOptionPane.showMessageDialog(this, "NumberFormatException caught. Message: "
//                    + exception.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
//        } catch (NullPointerException exception) {
//            // user cancelled so do nothing
//        }
//    }
//
//    // EFFECTS: adds the selected statblocks to the menucard's encounter list model as new characters,
//    //          repeating each for number equal to the given integer
//    private DefaultListModel<Character> addSelectedToEncounter(int copies) {
//        DefaultListModel<Character> encounterListModel = menuCardPanel.getEncounterListModel();
//        ListModel<StatBlock> libraryListModel = libraryJList.getModel();
//        List<Character> encounterList = new ArrayList<>();
//
//        // converts menucard's encounter list model to an array list
//        for (int i = 0; i < encounterListModel.getSize(); i++) {
//            encounterList.add(encounterListModel.getElementAt(i));
//        }
//
//        for (int i : libraryJList.getSelectedIndices()) {
//            StatBlock statBlock = libraryListModel.getElementAt(i);
//            for (int n = 0; n < copies; n++) {
//                encounterList.add(new Character(statBlock,
//                        Character.generateNameForEncounter(statBlock, encounterList)));
//            }
//        }
//        encounterListModel.removeAllElements();
//        encounterListModel.addAll(encounterList);
//        return encounterListModel;
//    }
//
//
//
//    // EFFECTS: processes button presses from user for the library
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == selectStatBlockButton) {
//            menuCardPanel.changeMenu("statBlockMenu");
//            menuCardPanel.setSelectedStatBlock(libraryJList.getModel()
//                    .getElementAt(libraryJList.getSelectedIndex()));
//        }
//        if (e.getSource() == createNewStatBlockButton) {
//            menuCardPanel.changeMenu("statBlockCreationMenu");
//        }
//        if (e.getSource() == addToEncounterButton) {
//            tryAddSelectedToEncounter();
//        }
//        if (e.getSource() == backButton) {
//            menuCardPanel.changeMenu("mainMenu");
//        }
//    }
//
//    // EFFECTS: disables and enables buttons when user is changing list selection for the library
//    public void valueChanged(ListSelectionEvent e) {
//        if (e.getValueIsAdjusting()) {
//            selectStatBlockButton.setEnabled(false);
//            createNewStatBlockButton.setEnabled(false);
//            addToEncounterButton.setEnabled(false);
//        } else if (!e.getValueIsAdjusting()) {
//            selectStatBlockButton.setEnabled(true);
//            createNewStatBlockButton.setEnabled(true);
//            addToEncounterButton.setEnabled(true);
//        }
//    }
}
