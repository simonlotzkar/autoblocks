package ui.scrollpanes;

import model.StatBlock;
import ui.panels.menus.MainMenuPanel;
import ui.textareas.StatBlockDisplayTextArea;
import javax.swing.*;

// Represents a library side display's scroll pane and its contents
public class LibrarySideDisplayScrollPane extends ParchmentScrollPane {
    private final StatBlockDisplayTextArea statBlockDisplayTextArea = new StatBlockDisplayTextArea();

    // MODIFIES: this
    // EFFECTS: constructs this scroll pane
    public LibrarySideDisplayScrollPane(MainMenuPanel mainMenuPanel) {
        super(mainMenuPanel);
        setViewportView(statBlockDisplayTextArea);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    // MODIFIES: this
    // EFFECTS: refreshes subcomponents, revalidates, and repaints
    public void refresh() {
        statBlockDisplayTextArea.populateTextArea();
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: if there is no selected statblock or the creation panel is showing, reverts to the full library display,
    //          otherwise reverts to the title screen
    public void processBackButton() {
        if (statBlockDisplayTextArea.getSelectedStatBlock() != null) {
            mainMenuPanel.setDisplays("library");
        } else {
            mainMenuPanel.getMenuManagerPanel().setMenu("title");
        }
    }

    // MODIFIES: mainMenuPanel
    // EFFECTS: processes button presses from user
    public void processOpenButton(StatBlock statBlock) {
        statBlockDisplayTextArea.setSelectedStatBlock(statBlock);
        mainMenuPanel.setDisplays("statBlock");
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    // EFFECTS: returns the statblock display panel
    public StatBlockDisplayTextArea getStatBlockDisplayTextArea() {
        return statBlockDisplayTextArea;
    }
}
