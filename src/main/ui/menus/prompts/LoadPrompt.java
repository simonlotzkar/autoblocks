package ui.menus.prompts;

import model.Character;
import model.StatBlock;
import persistence.JsonReader;
import ui.menus.MainMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

// Represents...
public class LoadPrompt extends ConfirmationPrompt {
    private final MainMenu mainMenu;

    // EFFECTS: constructs a load prompt...
    public LoadPrompt(MainMenu mainMenu) {
        super();
        this.mainMenu = mainMenu;
    }

    // EFFECTS: sets the mainmenu's encounter and library jlists to that which is saved on file
    private void load() {
        JsonReader jsonReader = new JsonReader("./data/autoBlocksApp.json");
        DefaultListModel<Character> encounterListModel = new DefaultListModel<>();
        DefaultListModel<StatBlock> libraryListModel = new DefaultListModel<>();

        try {
            encounterListModel.addAll(jsonReader.readEncounter());
            libraryListModel.addAll(jsonReader.readLibrary());
            mainMenu.setEncounterJList(new JList<>(encounterListModel));
            mainMenu.setLibraryJList(new JList<>(libraryListModel));
        } catch (IOException e) {
            System.out.println("Unable to read from file");
        }
    }

    @Override
    // EFFECTS: returns the prompt for this prompt
    protected String returnPrompt() {
        return "Save the current Encounter and Library?";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            load();
            this.dispose();
        }
        if (e.getSource() == denyButton) {
            this.dispose();
        }
    }
}