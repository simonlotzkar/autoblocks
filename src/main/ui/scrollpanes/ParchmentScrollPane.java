package ui.scrollpanes;

import ui.panels.menus.MainMenuPanel;

import javax.swing.*;
import java.awt.*;

// Represents...
public class ParchmentScrollPane extends JScrollPane {
    protected final MainMenuPanel mainMenuPanel;
    protected static final Image PARCHMENT = Toolkit.getDefaultToolkit()
            .getImage("./data/images/parchment.jpg");

    // MODIFIES: this
    // EFFECTS: constructs this scroll pane with a parchment background
    public ParchmentScrollPane(MainMenuPanel mainMenuPanel) {
        super(null);
        this.mainMenuPanel = mainMenuPanel;
        JViewport viewport = new JViewport() {
            @Override
            // EFFECTS: draws the background image
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(PARCHMENT, 0, 0, getWidth(), getHeight(), this);
            }
        };
        this.setViewport(viewport);
    }
}
