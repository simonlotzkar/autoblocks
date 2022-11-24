package ui.panels;

import ui.panels.menus.MainMenuPanel;
import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {
    protected MainMenuPanel mainMenuPanel;

    // EFFECTS: sets the layout manager, mainmenu panel, size, and visibility
    public DisplayPanel(LayoutManager layoutManager, MainMenuPanel mainMenuPanel) {
        super(layoutManager);
        this.mainMenuPanel = mainMenuPanel;
        this.setSize(mainMenuPanel.getSize());
        this.setVisible(true);
    }
}
