//package ui.panels.library;
//
//import model.Character;
//import model.StatBlock;
//import ui.panels.MenuCardPanel;
//
//import javax.swing.*;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.List;
//
//public class LibraryMenuPanel extends JPanel implements ActionListener, ListSelectionListener {
//    private final JList<model.StatBlock> libraryJList;
//
//    // panels
//    private final MenuCardPanel menuCardPanel;
//    private final JPanel buttonsPanel = new JPanel();
//
//    // images
//    private static final JLabel BANNER_LABEL = new JLabel(new ImageIcon(
//            "./data/images/banners/libraryMenuBanner.gif"));
//
//    // buttons
//    private final JButton selectStatBlockButton = new JButton("Open statblock's sheet");
//    private final JButton createNewStatBlockButton = new JButton("Create a new statblock");
//    private final JButton addToEncounterButton = new JButton("Add selected to encounter");
//    private final JButton backButton = new JButton("Back");
//
//    // EFFECTS: constructs this frame
//    public LibraryMenuPanel(MenuCardPanel menuCardPanel) {
//        super(new BorderLayout());
//        this.menuCardPanel = menuCardPanel;
//        this.libraryJList = new JList<>(menuCardPanel.getLibraryListModel());
//        this.setSize(menuCardPanel.getSize());
//
//        JScrollPane scrollPane = new JScrollPane(libraryJList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//
//        initializeButtons();
//        initializeJList();
//
//        JPanel northBorderPanel = new JPanel();
//        northBorderPanel.setLayout(new BorderLayout());
//        northBorderPanel.add(BANNER_LABEL, BorderLayout.CENTER);
//        northBorderPanel.add(new JPanel(), BorderLayout.SOUTH);
//        BANNER_LABEL.setPreferredSize(new Dimension(menuCardPanel.getWidth(), 144));
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
//    // EFFECTS: sets the jlist's parameters
//    private void initializeJList() {
//        libraryJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//        libraryJList.setLayoutOrientation(JList.VERTICAL_WRAP);
//        libraryJList.setVisibleRowCount(-1);
//        libraryJList.addListSelectionListener(this);
//        libraryJList.setFixedCellWidth(720);
//        //((DefaultListCellRenderer) jList.getCellRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
//        libraryJList.setVisible(true);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: sets parameters for all buttons
//    private void initializeButtons() {
//        List<JButton> buttonList = new ArrayList<>();
//
//        buttonList.add(selectStatBlockButton);
//        buttonList.add(createNewStatBlockButton);
//        buttonList.add(addToEncounterButton);
//        buttonList.add(backButton);
//
//        for (JButton jb : buttonList) {
//            jb.addActionListener(this);
//            jb.setVisible(true);
//        }
//
//        buttonsPanel.setLayout(new FlowLayout());
//        buttonsPanel.add(selectStatBlockButton);
//        buttonsPanel.add(createNewStatBlockButton);
//        buttonsPanel.add(addToEncounterButton);
//        buttonsPanel.add(backButton);
//        buttonsPanel.setVisible(true);
//    }
//
//    @Override
//    // EFFECTS: processes button presses from user
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == selectStatBlockButton) {
//            new StatBlockMenuPanel();
//        }
//        if (e.getSource() == createNewStatBlockButton) {
//            new StatBlockCreationMenuPanel();
//        }
//        if (e.getSource() == addToEncounterButton) {
//            tryAddSelectedToEncounter();
//        }
//        if (e.getSource() == backButton) {
//            menuCardPanel.changeMenu("mainMenu");
//        }
//    }
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
//    @Override
//    // EFFECTS: disables and enables buttons when user is changing list selection
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
//}