package ui.frames;

import javax.swing.*;

import ui.panels.menus.MenuManagerPanel;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// represents the main frame that contains the main panel
public class MainFrame extends JFrame {
    private static final int WIDTH = 1080;
    private static final int HEIGHT = 720;

    private static final ImageIcon DND_ICON = new ImageIcon(ClassLoader.getSystemResource("images/icons/dndLogo.png"));

    // EFFECTS: constructs this frame
    public MainFrame() {
        super("AutoBlocks");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconImage(DND_ICON.getImage());
        MenuManagerPanel menuManagerPanel = new MenuManagerPanel(this);
        add(menuManagerPanel);
        pack();
        setVisible(true);

        WindowListener exitListener = new WindowAdapter() {
            @Override
            // EFFECTS: handles window close from user
            public void windowClosing(WindowEvent e) {
                menuManagerPanel.confirmQuit();
            }
        };
        addWindowListener(exitListener);
    }
}
