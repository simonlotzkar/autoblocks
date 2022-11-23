package ui.panels;

import java.awt.*;

// Represents...
public class CharacterPanel extends MenuPanel {
    private final model.Character character;

    // constructs this panel
    public CharacterPanel(MenuCardPanel menuCardPanel) {
        super(new BorderLayout(), menuCardPanel);
        this.character = menuCardPanel.getSelectedCharacter();
    }
}
