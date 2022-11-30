package ui.panels;

import exceptions.IncompleteFieldException;
import model.NPC;
import model.StatBlock;
import model.statblockfields.Title;
import ui.panels.menus.MainMenuPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

// Represents...
public class LibraryScrollPane extends JScrollPane implements ListSelectionListener {
    private final JList<model.StatBlock> libraryList;
    private final DefaultListModel<model.StatBlock> libraryListModel;
    private DefaultListModel<NPC> encounterListModel;
    private final MainMenuPanel mainMenuPanel;

    // buttons
    private JButton addStatBlocksToEncounterButton;
    private JButton openStatBlockButton;
    private JButton createNewStatBlockButton;
    private JButton deleteStatBlocksButton;
    private JButton backButton;

    // EFFECTS: constructs this display panel
    public LibraryScrollPane(MainMenuPanel mainMenuPanel) {
        super(null);
        this.mainMenuPanel = mainMenuPanel;
        this.setSize(mainMenuPanel.getSize());
        this.setVisible(true);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        libraryListModel = mainMenuPanel.getMenuManagerPanel().getLibraryListModel();
        this.libraryList = new JList<>(libraryListModel);
        //libraryJList.setBackground(new Color(0, 0, 0, 0));
        this.setViewportView(libraryList);

        importButtons();
        initializeList();
    }

    // EFFECTS: ...
    private void importButtons() {
        addStatBlocksToEncounterButton = mainMenuPanel.getAddStatBlocksToEncounterButton();
        openStatBlockButton = mainMenuPanel.getOpenStatBlockButton();
        createNewStatBlockButton = mainMenuPanel.getCreateNewStatBlockButton();
        deleteStatBlocksButton = mainMenuPanel.getDeleteStatBlocksButton();
        backButton = mainMenuPanel.getBackButton();
    }

    // MODIFIES: this
    // EFFECTS: ...
    protected void initializeList() {
        libraryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        libraryList.setLayoutOrientation(JList.VERTICAL);
        libraryList.setVisibleRowCount(-1);
        libraryList.addListSelectionListener(this);
        libraryList.setOpaque(false);
    }

    // EFFECTS: ...
    public void clearSelection() {
        libraryList.clearSelection();
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
            DefaultListModel<NPC> newEncounterListModel = addSelectedToEncounter(command);
            mainMenuPanel.getMenuManagerPanel().setEncounterListModel(newEncounterListModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not add the statblock. Message: "
                    + e.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // EFFECTS: adds the selected statblocks to the menucard's encounter list model as new characters,
    //          repeating each for number equal to the given integer
    private DefaultListModel<NPC> addSelectedToEncounter(int copies) throws IncompleteFieldException {
        encounterListModel = mainMenuPanel.getMenuManagerPanel().getEncounterListModel();
        ListModel<StatBlock> libraryListModel = libraryList.getModel();
        List<NPC> encounterList = new ArrayList<>();

        // converts menucard's encounter list model to an array list
        for (int i = 0; i < encounterListModel.getSize(); i++) {
            encounterList.add(encounterListModel.getElementAt(i));
        }

        for (int i : libraryList.getSelectedIndices()) {
            StatBlock statBlock = libraryListModel.getElementAt(i);
            Title statBlockTitle = statBlock.getTitle();
            for (int n = 0; n < copies; n++) {
                NPC newNPC = new NPC(statBlock);
                newNPC.setTitle(new Title(NPC.generateNameForEncounter(statBlock, encounterList),
                        statBlockTitle.getSize(), statBlockTitle.getType(), statBlockTitle.getAlignment(), null));
                encounterList.add(newNPC);
            }
        }
        encounterListModel.removeAllElements();
        addAllToEncounter(encounterList);
        return encounterListModel;
    }

    // EFFECTS: adds all given characters to the encounterListModel
    //          this is only here because the autograder didn't like using the native addAll()
    private void addAllToEncounter(java.util.List<NPC> npcList) {
        for (NPC c : npcList) {
            encounterListModel.addElement(c);
        }
    }

    // EFFECTS: prompts user for confirmation and number of selected then deletes them from the encounter
    private void deleteStatBlocks() {
        int command = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete " + libraryList.getSelectedIndices().length
                        + " statblocks?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);

        if (command == JOptionPane.YES_OPTION) {
            for (Object o : libraryList.getSelectedValuesList()) {
                libraryListModel.removeElement(o);
            }
        }
        this.revalidate();
        this.repaint();
    }

    // EFFECTS: processes button presses from user
    public void passAction(ActionEvent e) {
        if (e.getSource() == addStatBlocksToEncounterButton) {
            tryAddSelectedToEncounter();
            libraryList.clearSelection();
        } else if (e.getSource() == openStatBlockButton) {
            mainMenuPanel.getSideDisplayPanel()
                    .setSelectedStatBlock(libraryListModel.getElementAt(libraryList.getSelectedIndex()));
            mainMenuPanel.setDisplays("statBlock");
            libraryList.clearSelection();
        } else if (e.getSource() == createNewStatBlockButton) {
            mainMenuPanel.setDisplays("statBlockCreation");
            libraryList.clearSelection();
        } else if (e.getSource() == deleteStatBlocksButton) {
            deleteStatBlocks();
        } else if (e.getSource() == backButton) {
            if (mainMenuPanel.getSideDisplayPanel().getSelectedStatBlock() != null) {
                mainMenuPanel.setDisplays("library");
            } else {
                mainMenuPanel.getMenuManagerPanel().setMenu("title");
            }
        }
    }

    @Override
    // EFFECTS: disables and enables multi selection buttons when user is changing list selection
    //          or if just one item is selected enables or disables single selection buttons
    public void valueChanged(ListSelectionEvent e) {
        addStatBlocksToEncounterButton.setEnabled(!e.getValueIsAdjusting());
        if (libraryList.getSelectedIndices().length < 1) {
            addStatBlocksToEncounterButton.setEnabled(false);
        }
        deleteStatBlocksButton.setEnabled(!e.getValueIsAdjusting());

        mainMenuPanel.getSideDisplayPanel().valueChanged(e);

        openStatBlockButton.setEnabled(libraryList.getSelectedIndices().length == 1);
    }
}
