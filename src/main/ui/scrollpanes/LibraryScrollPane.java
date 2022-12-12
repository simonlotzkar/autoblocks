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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

// Represents a scrollpane containing a list of statblocks that can be selected
public class LibraryScrollPane extends JScrollPane implements ListSelectionListener {
    private final JList<StatBlock> libraryList;
    private final StatBlockLibrary libraryListModel;
    private Encounter encounterListModel;
    private final MainMenuPanel mainMenuPanel;

    // buttons
    private JButton addStatBlocksToEncounterButton;
    private JButton openStatBlockButton;
    private JButton createNewStatBlockButton;
    private JButton deleteStatBlocksButton;
    private JButton backButton;

    // MODIFIES: this
    // EFFECTS: constructs this display panel
    public LibraryScrollPane(MainMenuPanel mainMenuPanel) {
        super(null);
        this.mainMenuPanel = mainMenuPanel;
        setSize(mainMenuPanel.getSize());
        setVisible(true);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        libraryListModel = mainMenuPanel.getMenuManagerPanel().getStatBlockLibrary();
        libraryList = new JList<>(libraryListModel);
        libraryList.setBackground(new Color(0, 0, 0, 0));
        setViewportView(libraryList);

        importButtons();
        initializeLibraryList();
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
        libraryList.clearSelection();
        revalidate();
        repaint();
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
        encounterListModel = mainMenuPanel.getMenuManagerPanel().getEncounter();
        ListModel<StatBlock> libraryListModel = libraryList.getModel();
        ArrayList<NPC> encounterList = new ArrayList<>();

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
    }

    // MODIFIES: this
    // EFFECTS: adds all given characters to the encounterListModel
    //          this is only here because the autograder didn't like using the native addAll()
    private void addAllToEncounter(java.util.List<NPC> npcList) {
        for (NPC c : npcList) {
            encounterListModel.addElement(c);
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
    // MODIFIES: this
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
