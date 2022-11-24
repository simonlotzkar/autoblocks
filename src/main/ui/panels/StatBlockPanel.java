package ui.panels;

import model.StatBlock;
import ui.panels.menus.MenuManagerPanel;
import ui.panels.menus.MenuPanel;

import java.awt.*;

// Represents...
public class StatBlockPanel extends MenuPanel {
    private final StatBlock statBlock;

    // constructs this panel
    public StatBlockPanel(MenuManagerPanel menuManagerPanel) {
        super(new BorderLayout(), menuManagerPanel);
        this.statBlock = menuManagerPanel.getSelectedStatBlock();
    }
}
