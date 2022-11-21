package ui.panels;

import model.Character;
import model.StatBlock;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import ui.frames.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

// represents a panel that manages the menu panels by switching between them
public class MenuCardPanel extends JPanel {
    private JList<model.Character> encounterJList = new JList<>();
    private JList<model.StatBlock> libraryJList = new JList<>();

    private final CardLayout cardLayout = new CardLayout();

    private static final String JSON_DIRECTORY = "./data/autoBlocksApp.json";

    // EFFECTS: constructs this panel
    public MenuCardPanel(MainFrame mainFrame) {
        super();
        this.setSize(mainFrame.getSize());
        this.setVisible(true);
        this.setLayout(cardLayout);
        this.add(new MainMenuPanel(this), "mainMenu");
        this.add(new LibraryMenuPanel(this), "libraryMenu");
        this.add(new EncounterMenuPanel(this), "encounterMenu");
        cardLayout.show(this, "mainMenu");
    }

    // MODIFIES: this
    // EFFECTS: changes menu based on given string or does nothing if given incorrect string
    public void changeMenu(String s) {
        switch (s) {
            case "mainMenu":
                cardLayout.show(this, "mainMenu");
                break;
            case "libraryMenu":
                cardLayout.show(this, "libraryMenu");
                break;
            case "encounterMenu":
                cardLayout.show(this, "encounterMenu");
                break;
            default:
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the encounter and library jlists to that which is saved on file
    private void load() throws IOException {
        JsonReader jsonReader = new JsonReader(JSON_DIRECTORY);
        DefaultListModel<Character> encounterListModel = new DefaultListModel<>();
        DefaultListModel<StatBlock> libraryListModel = new DefaultListModel<>();

        encounterListModel.addAll(jsonReader.readEncounter());
        libraryListModel.addAll(jsonReader.readLibrary());

        this.encounterJList = new JList<>(encounterListModel);
        this.libraryJList = new JList<>(libraryListModel);
    }

    // EFFECTS: saves the current library and encounter to file
    private void save() throws FileNotFoundException {
        PrintWriter jsonWriter = new PrintWriter(JSON_DIRECTORY);
        JSONArray jsonLibrary = new JSONArray();
        JSONArray jsonEncounter = new JSONArray();
        JSONObject json = new JSONObject();

        ListModel<Character> encounterListModel = encounterJList.getModel();
        for (int i = 0; i < encounterListModel.getSize(); i++) {
            jsonEncounter.put((encounterListModel.getElementAt(i)).toJson());
        }
        ListModel<StatBlock> libraryListModel = libraryJList.getModel();
        for (int i = 0; i < libraryListModel.getSize(); i++) {
            jsonLibrary.put((libraryListModel.getElementAt(i)).toJson());
        }

        json.put("library", jsonLibrary);
        json.put("encounter", jsonEncounter);
        jsonWriter.print(json.toString(4));
        jsonWriter.close();
    }

    // MODIFIES: this
    // EFFECTS: prompts user to load library and/or encounter then attempts to load from file,
    //          displays an error message if it fails
    public void tryLoad() {
        try {
            load();
            JOptionPane.showMessageDialog(this, "Successfully loaded from " + JSON_DIRECTORY,
                    "Success!", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(this, "IOException Caught. Message: "
                    + exception.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to save library and/or encounter then attempts to load from file,
    //          displays an error message if it fails
    public void trySave() {
        try {
            save();
            JOptionPane.showMessageDialog(this, "Successfully saved to " + JSON_DIRECTORY,
                    "Success!", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(this, "IOException Caught. Message: "
                    + exception.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }

    // EFFECTS: prompts user with confirmation dialog and terminates the application if they confirm
    public void confirmQuit() {
        int command = JOptionPane.showConfirmDialog(this,
                "Do you want to quit? Unsaved progress will be lost!",
                "Confirmation Needed",
                JOptionPane.YES_NO_OPTION);
        if (command == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // -----------------------------------------------------------------------
    // getters
    // EFFECTS: gets the encounter jlist
    public JList<Character> getEncounterJList() {
        return encounterJList;
    }

    // EFFECTS: gets the library jlist
    public JList<StatBlock> getLibraryJList() {
        return libraryJList;
    }

    // -----------------------------------------------------------------------
    // setters
    // EFFECTS: sets the encounter jlist
    public void setEncounterJList(JList<Character> encounterJList) {
        this.encounterJList = encounterJList;
    }

    // EFFECTS: sets the library jlist
    public void setLibraryJList(JList<StatBlock> libraryJList) {
        this.libraryJList = libraryJList;
    }
}
