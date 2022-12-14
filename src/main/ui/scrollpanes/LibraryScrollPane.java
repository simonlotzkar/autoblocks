package ui.scrollpanes;

import exceptions.IncompleteFieldException;
import model.Encounter;
import model.NPC;
import model.StatBlock;
import model.StatBlockLibrary;
import model.statblockfields.Title;
import ui.panels.menus.MainMenuPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

// Represents a scroll pane containing a list of statblocks that can be selected
public class LibraryScrollPane extends ParchmentScrollPane implements ListSelectionListener {
    // lists
    private final JList<StatBlock> libraryList;
    private final StatBlockLibrary libraryListModel;
    private Encounter encounter;

    // buttons
    private JButton addStatBlocksToEncounterButton;
    private JButton openStatBlockButton;
    private JButton createNewStatBlockButton;
    private JButton deleteStatBlocksButton;
    private JButton backButton;

    // MODIFIES: this
    // EFFECTS: constructs this display panel
    public LibraryScrollPane(MainMenuPanel mainMenuPanel) {
        super(mainMenuPanel);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        libraryListModel = mainMenuPanel.getMenuManagerPanel().getStatBlockLibrary();
        libraryList = new JList<>(libraryListModel);
        setViewportView(libraryList);

        importButtons();
        initializeLibraryList();
    }

    // MODIFIES: this
    // EFFECTS: repopulates, revalidates, and repaints
    public void refresh() {
        libraryList.removeAll();
        libraryList.setModel(libraryListModel);
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: creates references to the main menu panel's buttons
    private void importButtons() {
        addStatBlocksToEncounterButton = mainMenuPanel.getAddStatBlocksToEncounterButton();
        openStatBlockButton = mainMenuPanel.getOpenStatBlockButton();
        createNewStatBlockButton = mainMenuPanel.getCreateNewStatBlockButton();
        deleteStatBlocksButton = mainMenuPanel.getDeleteStatBlocksButton();
        backButton = mainMenuPanel.getBackButton();
    }

    // MODIFIES: this
    // EFFECTS: formats the library list
    public void initializeLibraryList() {
        libraryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        libraryList.setLayoutOrientation(JList.VERTICAL);
        libraryList.setVisibleRowCount(-1);
        libraryList.addListSelectionListener(this);
        libraryList.setCellRenderer(new TransparentListCellRenderer());
        libraryList.setOpaque(false);
    }

    // MODIFIES: mainMenuPanel
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
            addSelectedToEncounter(command);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not add the statblock. Message: "
                    + e.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the selected statblocks to the menucard's encounter list model as new characters,
    //          repeating each for number equal to the given integer
    private void addSelectedToEncounter(int copies) throws IncompleteFieldException {
        encounter = mainMenuPanel.getMenuManagerPanel().getEncounter();
        ListModel<StatBlock> libraryListModel = libraryList.getModel();
        ArrayList<NPC> encounterList = new ArrayList<>();

        // converts menucard's encounter list model to an array list
        for (int i = 0; i < encounter.getSize(); i++) {
            encounterList.add(encounter.getElementAt(i));
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
        encounter.removeAllElements();
        addAllToEncounter(encounterList);
    }

    // MODIFIES: this
    // EFFECTS: adds all given characters to the encounterListModel
    //          this is only here because the autograder didn't like using the native addAll()
    private void addAllToEncounter(java.util.List<NPC> npcList) {
        for (NPC c : npcList) {
            encounter.addElement(c);
        }
    }

    // MODIFIES: this
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

    // MODIFIES: mainMenuPanel
    // EFFECTS: processes button presses from user
    public void processAction(ActionEvent e) {
        LibrarySideDisplayScrollPane librarySideDisplayScrollPane
                = mainMenuPanel.getSideDisplayPanel().getLibrarySideDisplayScrollPane();
        if (e.getSource() == addStatBlocksToEncounterButton) {
            tryAddSelectedToEncounter();
            libraryList.clearSelection();
        } else if (e.getSource() == openStatBlockButton) {
            librarySideDisplayScrollPane
                    .processOpenButton(libraryListModel.getElementAt(libraryList.getSelectedIndex()));
            libraryList.clearSelection();
        } else if (e.getSource() == createNewStatBlockButton) {
            mainMenuPanel.setDisplays("statBlockCreation");
            libraryList.clearSelection();
        } else if (e.getSource() == deleteStatBlocksButton) {
            deleteStatBlocks();
        } else if (e.getSource() == backButton) {
            librarySideDisplayScrollPane.processBackButton();
        }
    }

    @Override
    // MODIFIES: this
    // EFFECTS: disables and enables buttons when the user selection is changing
    public void valueChanged(ListSelectionEvent e) {
        // disables when selection is changing, enables otherwise
        addStatBlocksToEncounterButton.setEnabled(!e.getValueIsAdjusting());
        deleteStatBlocksButton.setEnabled(!e.getValueIsAdjusting());

        // enables when 1 cell is selected, disables otherwise
        openStatBlockButton.setEnabled(libraryList.getSelectedIndices().length == 1);

        // enables when at least 1 cell is selected, disables otherwise
        addStatBlocksToEncounterButton.setEnabled(libraryList.getSelectedIndices().length >= 1);
    }
}
