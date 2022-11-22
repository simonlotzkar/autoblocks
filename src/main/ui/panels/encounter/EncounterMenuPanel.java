//package ui.panels.encounter;
//
//import model.Character;
//import ui.frames.MainFrame;
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
//public class EncounterMenuPanel extends JPanel implements ActionListener, ListSelectionListener {
//    private final JList<model.Character> encounterJList;
//
//    // panels
//    private final MenuCardPanel menuCardPanel;
//    private final JPanel buttonsPanel = new JPanel();
//
//    // images
//    private static final JLabel BANNER_LABEL = new JLabel(new ImageIcon(
//            "./data/images/banners/encounterMenuBanner.gif"));
//
//    // buttons
//    private final JButton selectCharacterButton = new JButton("Open character's sheet");
//    private final JButton selectGroupButton = new JButton("Open character's group");
//    private final JButton addToGroupButton = new JButton("Add selected to group");
//    private final JButton backButton = new JButton("Back");
//
//    // EFFECTS: constructs this frame
//    public EncounterMenuPanel(MenuCardPanel menuCardPanel) {
//        super(new BorderLayout());
//        this.menuCardPanel = menuCardPanel;
//        this.encounterJList = new JList<>(menuCardPanel.getEncounterListModel());
//        this.setSize(menuCardPanel.getSize());
//
//        JScrollPane scrollPane = new JScrollPane(encounterJList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
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
//        encounterJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//        encounterJList.setLayoutOrientation(JList.VERTICAL_WRAP);
//        encounterJList.setVisibleRowCount(-1);
//        encounterJList.addListSelectionListener(this);
//        encounterJList.setFixedCellWidth(720);
//        //((DefaultListCellRenderer) jList.getCellRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
//        encounterJList.setVisible(true);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: sets parameters for all buttons
//    private void initializeButtons() {
//        List<JButton> buttonList = new ArrayList<>();
//
//        buttonList.add(selectCharacterButton);
//        buttonList.add(selectGroupButton);
//        buttonList.add(addToGroupButton);
//        buttonList.add(backButton);
//
//        for (JButton jb : buttonList) {
//            jb.addActionListener(this);
//            jb.setVisible(true);
//        }
//
//        buttonsPanel.setLayout(new FlowLayout());
//        buttonsPanel.add(selectCharacterButton);
//        buttonsPanel.add(selectGroupButton);
//        buttonsPanel.add(addToGroupButton);
//        buttonsPanel.add(backButton);
//        buttonsPanel.setVisible(true);
//    }
//
//    @Override
//    // EFFECTS: processes button presses from user
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == selectCharacterButton) {
//            MainFrame.addCharacterTab(encounterJList.getModel().getElementAt(encounterJList.getSelectedIndex()));
//        }
//        if (e.getSource() == selectGroupButton) {
//            MainFrame.addGroupTab(encounterJList.getSelectedIndex());
//        }
//        if (e.getSource() == addToGroupButton) {
//            addSelectedToGroup();
//            this.revalidate();
//            this.repaint();
//        }
//        if (e.getSource() == backButton) {
//            menuCardPanel.changeMenu("mainMenu");
//        }
//    }
//
//    // EFFECTS: prompts user for a group name, sets selected characters to the given group name
//    //          or if nothing is given does nothing
//    private void addSelectedToGroup() {
//        ListModel<Character> encounterListModel = encounterJList.getModel();
//        try {
//            String command = JOptionPane.showInputDialog(this,
//                    "What do you want to name this group?",
//                    "User Input Needed",
//                    JOptionPane.INFORMATION_MESSAGE,
//                    null,
//                    null,
//                    "").toString();
//
//            if (command.isBlank() || command.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Invalid group name given.", "Failure!",
//                        JOptionPane.WARNING_MESSAGE);
//            } else {
//                for (int i : encounterJList.getSelectedIndices()) {
//                    encounterListModel.getElementAt(i).getTitle().setGroup(command);
//                }
//            }
//        } catch (NullPointerException exception) {
//            // user cancelled so do nothing
//        }
//    }
//
//    @Override
//    // EFFECTS: disables and enables buttons when user is changing list selection or if just one item is selected
//    public void valueChanged(ListSelectionEvent e) {
//        if (e.getValueIsAdjusting()) {
//            selectCharacterButton.setEnabled(false);
//            selectGroupButton.setEnabled(false);
//            addToGroupButton.setEnabled(false);
//        } else if (!e.getValueIsAdjusting()) {
//            selectCharacterButton.setEnabled(true);
//            selectGroupButton.setEnabled(true);
//            addToGroupButton.setEnabled(true);
//        }
//
//        if (e.getLastIndex() > 0) {
//            selectCharacterButton.setEnabled(false);
//            selectGroupButton.setEnabled(false);
//        } else {
//            selectCharacterButton.setEnabled(true);
//            selectGroupButton.setEnabled(true);
//        }
//    }
//}
