package ui.frames.prompts;

import model.Character;
import model.StatBlock;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SavePrompt extends ConfirmationPrompt {
    private final List<Character> encounter = new ArrayList<>();
    private final List<StatBlock> library = new ArrayList<>();

    // EFFECTS: constructs a save prompt that takes an encounter and library as jlists then converts them to arraylists
    public SavePrompt(JList<Character> encounterJList,
                      JList<StatBlock> libraryJList) {
        super();
        ListModel<Character> encounterListModel = encounterJList.getModel();
        for (int i = 0; i < encounterListModel.getSize(); i++) {
            encounter.add(encounterListModel.getElementAt(i));
        }
        ListModel<StatBlock> libraryListModel = libraryJList.getModel();
        for (int i = 0; i < libraryListModel.getSize(); i++) {
            library.add(libraryListModel.getElementAt(i));
        }
    }

    // EFFECTS: saves the current library and encounter to file
    private void save() throws FileNotFoundException {
        PrintWriter jsonWriter = new PrintWriter("./data/autoBlocksApp.json");
        JSONArray jsonLibrary = new JSONArray();
        JSONArray jsonEncounter = new JSONArray();
        JSONObject json = new JSONObject();
        for (Character c : encounter) {
            jsonEncounter.put(c.toJson());
        }
        for (StatBlock sb : library) {
            jsonLibrary.put(sb.toJson());
        }

        json.put("library", jsonLibrary);
        json.put("encounter", jsonEncounter);
        jsonWriter.print(json.toString(4));
        jsonWriter.close();
    }

    @Override
    // EFFECTS: returns the prompt for this prompt
    protected String returnPrompt() {
        return "Save the current Encounter and Library?";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            this.dispose();
            try {
                save();
            } catch (FileNotFoundException exception) {
                // TODO exception not handled
            }
            new QuitPrompt();
        }
        if (e.getSource() == denyButton) {
            this.dispose();
            new QuitPrompt();
        }
    }
}