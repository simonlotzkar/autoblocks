package ui.frames;

import javax.swing.*;
import ui.panels.menus.MenuManagerPanel;

import java.awt.*;

// represents the main frame that contains the main panel
public class MainFrame extends JFrame {
    private static final int WIDTH = 1080;
    private static final int HEIGHT = 720;

    private static final ImageIcon DND_ICON = new ImageIcon("./data/images/icons/dndLogo.png");

    // EFFECTS: constructs this frame
    public MainFrame() {
        super("AutoBlocks");
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setIconImage(DND_ICON.getImage());
        this.add(new MenuManagerPanel(this));
        this.pack();
        this.setVisible(true);
    }
}
