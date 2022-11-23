package ui.panels;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Represents a panel that's either an encounter or library jlist
public class ListMenuPanelDELETE extends MenuPanel implements ActionListener, ListSelectionListener {
    // lists
    protected final List<JButton> buttonList = new ArrayList<>();

    // panel components
    protected final JPanel northBorderPanel = new JPanel();
    protected final JPanel buttonsPanel = new JPanel();

    // buttons
    protected final JButton backButton = new JButton("Back");

    // images
    protected static final ImageIcon RETURN_IMAGE_ICON = new ImageIcon(ICON_DIRECTORY + "returnIcon.png");

    // EFFECTS: constructs this panel
    public ListMenuPanelDELETE(LayoutManager layoutManager, MenuCardPanel menuCardPanel) {
        super(layoutManager, menuCardPanel);
    }

    // MODIFIES: this
    // EFFECTS: constructs the north border panel and its components with given banner image
    protected void initializeNorthBorderPanel(ImageIcon banner) {
        JLabel bannerLabel = new JLabel(banner);
        bannerLabel.setPreferredSize(new Dimension(menuCardPanel.getWidth(), 144));

        northBorderPanel.setLayout(new BorderLayout());
        northBorderPanel.add(bannerLabel, BorderLayout.CENTER);
        northBorderPanel.add(new JPanel(), BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: sets parameters for the button panel and its components
    protected void initializeButtonPanel() {
        initializeButtons();

        for (JButton jb : buttonList) {
            jb.addActionListener(this);
            jb.setVisible(true);
            buttonsPanel.add(jb);
        }

        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets up parameters for buttons
    protected void initializeButtons() {
        backButton.setIcon(RETURN_IMAGE_ICON);
        buttonList.add(backButton);
    }

    // EFFECTS: processes button presses from user
    public void actionPerformed(ActionEvent e) {
        // this is meant to be overridden, does nothing
    }

    @Override
    // EFFECTS: disables and enables buttons when user is changing list selection
    public void valueChanged(ListSelectionEvent e) {
        // this is meant to be overridden, does nothing
    }
}
