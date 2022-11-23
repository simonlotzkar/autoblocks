package ui.panels;

import model.StatBlock;

import java.awt.*;

// Represents...
public class StatBlockPanel extends MenuPanel {
    private final StatBlock statBlock;

    // constructs this panel
    public StatBlockPanel(MenuCardPanel menuCardPanel) {
        super(new BorderLayout(), menuCardPanel);
        this.statBlock = menuCardPanel.getSelectedStatBlock();
    }
}
