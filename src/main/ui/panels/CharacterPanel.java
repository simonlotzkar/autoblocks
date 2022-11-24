package ui.panels;

import ui.panels.menus.MenuManagerPanel;

import java.awt.*;

// Represents...
public class CharacterPanel {
    private final model.Character character;

    // constructs this panel
    public CharacterPanel(MenuManagerPanel menuManagerPanel) {
        super(new BorderLayout(), menuManagerPanel);
        this.character = menuManagerPanel.getSelectedCharacter();
    }
}
