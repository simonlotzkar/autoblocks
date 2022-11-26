package ui.panels;

import model.Character;
import model.StatBlock;
import ui.panels.menus.MainMenuPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

// Represents...
public class LibraryScrollPane extends JScrollPane implements ListSelectionListener {
    private final JList<model.StatBlock> libraryJList;
    private final DefaultListModel<model.StatBlock> libraryListModel;
    private final MainMenuPanel mainMenuPanel;

    // buttons
    private JButton addStatBlocksToEncounterButton;
    private JButton openStatBlockButton;
    private JButton createNewStatBlockButton;
    private JButton deleteStatBlocksButton;

    // EFFECTS: constructs this display panel
    public LibraryScrollPane(MainMenuPanel mainMenuPanel) {
        super(null);
        this.mainMenuPanel = mainMenuPanel;
        this.setSize(mainMenuPanel.getSize());
        this.setVisible(true);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        libraryListModel = mainMenuPanel.getMenuManagerPanel().getLibraryListModel();
        this.libraryJList = new JList<>(libraryListModel);
        //libraryJList.setBackground(new Color(0, 0, 0, 0));
        this.setViewportView(libraryJList);

        importButtons();
        initializeJList();
    }

    // EFFECTS: ...
    private void importButtons() {
        addStatBlocksToEncounterButton = mainMenuPanel.getAddStatBlocksToEncounterButton();
        openStatBlockButton = mainMenuPanel.getOpenStatBlockButton();
        createNewStatBlockButton = mainMenuPanel.getCreateNewStatBlockButton();
        deleteStatBlocksButton = mainMenuPanel.getDeleteStatBlocksButton();
    }

    // MODIFIES: this
    // EFFECTS: ...
    protected void initializeJList() {
        libraryJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        libraryJList.setLayoutOrientation(JList.VERTICAL);
        libraryJList.setVisibleRowCount(-1);
        libraryJList.addListSelectionListener(this);
        //libraryJList.setOpaque(false);
    }

    // EFFECTS: prompts user for the number of copies to add then adds them or cancels or catches exceptions
    private void tryAddSelectedToEncounter() {
        try {
            int command = Integer.parseInt((JOptionPane.showInputDialog(this,
                    "How many copies of the selected statblocks do you want to add?",
                    "User Input Needed",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    null,
                    "")).toString());
            DefaultListModel<Character> newEncounterListModel = addSelectedToEncounter(command);
            mainMenuPanel.getMenuManagerPanel().setEncounterListModel(newEncounterListModel);
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "NumberFormatException caught. Message: "
                    + exception.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
        } catch (NullPointerException exception) {
            // user cancelled so do nothing
        }
    }

    // EFFECTS: adds the selected statblocks to the menucard's encounter list model as new characters,
    //          repeating each for number equal to the given integer
    private DefaultListModel<Character> addSelectedToEncounter(int copies) {
        DefaultListModel<Character> encounterListModel = mainMenuPanel.getMenuManagerPanel().getEncounterListModel();
        ListModel<StatBlock> libraryListModel = libraryJList.getModel();
        List<Character> encounterList = new ArrayList<>();

        // converts menucard's encounter list model to an array list
        for (int i = 0; i < encounterListModel.getSize(); i++) {
            encounterList.add(encounterListModel.getElementAt(i));
        }

        for (int i : libraryJList.getSelectedIndices()) {
            StatBlock statBlock = libraryListModel.getElementAt(i);
            for (int n = 0; n < copies; n++) {
                encounterList.add(new Character(statBlock,
                        Character.generateNameForEncounter(statBlock, encounterList)));
            }
        }
        encounterListModel.removeAllElements();
        encounterListModel.addAll(encounterList);
        return encounterListModel;
    }

    // EFFECTS: prompts user for confirmation and number of selected then deletes them from the encounter
    private void deleteStatBlocks() {
        int command = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete " + libraryJList.getSelectedIndices().length
                        + " statblocks?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);

        if (command == JOptionPane.YES_OPTION) {
            for (Object o : libraryJList.getSelectedValuesList()) {
                libraryListModel.removeElement(o);
            }
        }
        this.revalidate();
        this.repaint();
    }

    // EFFECTS: processes button presses from user
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addStatBlocksToEncounterButton) {
            tryAddSelectedToEncounter();
        } else if (e.getSource() == openStatBlockButton) {
            mainMenuPanel.setSelectedStatBlock(libraryListModel.getElementAt(libraryJList.getSelectedIndex()));
            mainMenuPanel.getSideDisplayPanel().setSideDisplay("statBlock");
        } else if (e.getSource() == createNewStatBlockButton) {
            mainMenuPanel.getSideDisplayPanel().setSideDisplay("statBlockCreation");
        } else if (e.getSource() == deleteStatBlocksButton) {
            deleteStatBlocks();
        }
    }

    @Override
    // EFFECTS: disables and enables multi selection buttons when user is changing list selection
    //          or if just one item is selected enables or disables single selection buttons
    public void valueChanged(ListSelectionEvent e) {
        addStatBlocksToEncounterButton.setEnabled(!e.getValueIsAdjusting());
        deleteStatBlocksButton.setEnabled(!e.getValueIsAdjusting());

        mainMenuPanel.getSideDisplayPanel().valueChanged(e);

        openStatBlockButton.setEnabled(libraryJList.getSelectedIndices().length == 1);
    }
}
