package ui.frames.prompts;

import java.awt.event.ActionEvent;

public class QuitPrompt extends ConfirmationPrompt {
    // EFFECTS: constructs...
    public QuitPrompt() {
        super();
    }

    @Override
    // EFFECTS: returns the prompt for this prompt
    protected String returnPrompt() {
        return "Quit AutoBlocks?";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            this.dispose();
            System.exit(0);
        }
        if (e.getSource() == denyButton) {
            this.dispose();
        }
    }
}
