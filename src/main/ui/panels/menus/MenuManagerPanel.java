package ui.panels.menus;

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
public class MenuManagerPanel extends JPanel {
    private DefaultListModel<Character> encounterListModel = new DefaultListModel<>();
    private DefaultListModel<StatBlock> libraryListModel = new DefaultListModel<>();

    private final TitleMenuPanel titleMenuPanel = new TitleMenuPanel(this);
    private final MainMenuPanel mainMenuPanel = new MainMenuPanel(this);

    private final CardLayout cardLayout = new CardLayout();

    private static final String JSON_DIRECTORY = "./data/autoBlocksApp.json";

    // EFFECTS: constructs this panel
    public MenuManagerPanel(MainFrame mainFrame) {
        super();
        this.setSize(mainFrame.getSize());
        this.setLayout(cardLayout);
        this.add(titleMenuPanel, "title");
        this.add(mainMenuPanel, "main");
        this.setVisible(true);
        cardLayout.show(this, "title");
    }

    // MODIFIES: this
    // EFFECTS: changes menu based on given string or does nothing if given incorrect string
    public void setMenu(String s) {
        cardLayout.show(this, s);
    }

    // MODIFIES: this
    // EFFECTS: sets the encounter and library model lists to that which is saved on file
    private void load() throws IOException {
        JsonReader jsonReader = new JsonReader(JSON_DIRECTORY);

        encounterListModel.removeAllElements();
        encounterListModel.addAll(jsonReader.readEncounter());

        libraryListModel.removeAllElements();
        libraryListModel.addAll(jsonReader.readLibrary());
    }

    // EFFECTS: saves the current library and encounter to file
    private void save() throws FileNotFoundException {
        PrintWriter jsonWriter = new PrintWriter(JSON_DIRECTORY);
        JSONArray jsonLibrary = new JSONArray();
        JSONArray jsonEncounter = new JSONArray();
        JSONObject json = new JSONObject();

        for (int i = 0; i < encounterListModel.getSize(); i++) {
            jsonEncounter.put((encounterListModel.getElementAt(i)).toJson());
        }
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
            JOptionPane.showMessageDialog(this, "Successfully loaded from " + JSON_DIRECTORY + ":"
                            + "\n" + libraryListModel.getSize() + " statblocks and "
                            + encounterListModel.getSize() + " characters.",
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
    // EFFECTS: gets the encounter list model
    public DefaultListModel<Character> getEncounterListModel() {
        return encounterListModel;
    }

    // EFFECTS: gets the library list model
    public DefaultListModel<StatBlock> getLibraryListModel() {
        return libraryListModel;
    }

    // EFFECTS: gets the
    public TitleMenuPanel getTitleMenuPanel() {
        return titleMenuPanel;
    }

    // EFFECTS: gets the
    public MainMenuPanel getMainMenuPanel() {
        return mainMenuPanel;
    }

    // -----------------------------------------------------------------------
    // setters
    // EFFECTS: sets the encounter list model
    public void setEncounterListModel(DefaultListModel<Character> encounterListModel) {
        this.encounterListModel = encounterListModel;
    }

    // EFFECTS: sets the library list model
    public void setLibraryListModel(DefaultListModel<StatBlock> libraryListModel) {
        this.libraryListModel = libraryListModel;
    }
}
