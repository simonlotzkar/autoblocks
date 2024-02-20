package ui.panels.menus;

import model.*;
import model.Event;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import ui.frames.MainFrame;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

// Represents a panel that manages the menu panels by switching between them
public class MenuManagerPanel extends JPanel {
    private final Encounter encounter = new Encounter();
    private final StatBlockLibrary statBlockLibrary = new StatBlockLibrary();

    private final MainMenuPanel mainMenuPanel = new MainMenuPanel(this);

    private final CardLayout cardLayout = new CardLayout();

    // MODIFIES: this
    // EFFECTS: constructs this panel
    public MenuManagerPanel(MainFrame mainFrame) {
        super();
        setPreferredSize(mainFrame.getPreferredSize());
        setLayout(cardLayout);
        setVisible(true);

        TitleMenuPanel titleMenuPanel = new TitleMenuPanel(this);
        add(titleMenuPanel, "title");
        add(mainMenuPanel, "main");
        cardLayout.show(this, "title");
    }

    // MODIFIES: this
    // EFFECTS: changes menu based on given string or does nothing if given incorrect string
    public void setMenu(String s) {
        cardLayout.show(this, s);
    }

    // MODIFIES: this
    // EFFECTS: sets the encounter and library model lists to that which is saved on file
    private void load(String filepath) throws IOException {
        JsonReader jsonReader = new JsonReader(filepath);

        encounter.removeAllElements();
        addAllToEncounter(jsonReader.readEncounter());

        statBlockLibrary.removeAllElements();
        addAllToLibrary(jsonReader.readLibrary());
    }

    // MODIFIES: this
    // EFFECTS: adds all given statblocks to the libraryListModel
    private void addAllToLibrary(java.util.List<StatBlock> statBlockList) {
        for (StatBlock sb : statBlockList) {
            statBlockLibrary.addElement(sb);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds all given characters to the encounterListModel
    private void addAllToEncounter(java.util.List<NPC> npcList) {
        for (NPC c : npcList) {
            encounter.addElement(c);
        }
    }

    // MODIFIES: json file
    // EFFECTS: saves the current library and encounter to file
    private void save(String filepath) throws FileNotFoundException {
        PrintWriter jsonWriter = new PrintWriter(filepath);
        JSONArray jsonLibrary = new JSONArray();
        JSONArray jsonEncounter = new JSONArray();
        JSONObject json = new JSONObject();

        for (int i = 0; i < encounter.getSize(); i++) {
            jsonEncounter.put((encounter.getElementAt(i)).toJson());
        }
        for (int i = 0; i < statBlockLibrary.getSize(); i++) {
            jsonLibrary.put((statBlockLibrary.getElementAt(i)).toJson());
        }

        json.put("library", jsonLibrary);
        json.put("encounter", jsonEncounter);
        jsonWriter.print(json.toString(4));
        jsonWriter.close();
    }

    // MODIFIES: this
    // EFFECTS: prompts the user to set the encounter and library model lists to that which is saved on file,
    //          displays an error message if it fails
    public void tryLoad() {
        int command = JOptionPane.showConfirmDialog(this,
                "Do you want to load? The current data will be overwritten!", "Confirmation Needed",
                JOptionPane.YES_NO_OPTION);
        if (command == JOptionPane.YES_OPTION) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON files", "json");
            fileChooser.setFileFilter(filter);

            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    File selectedFile = fileChooser.getSelectedFile();
                    load(selectedFile.getAbsolutePath());
                    JOptionPane.showMessageDialog(this, "Successfully loaded from "
                                    + selectedFile.getAbsolutePath() + ":"
                                    + "\n" + statBlockLibrary.getSize() + " statblocks and "
                                    + encounter.getSize() + " characters.",
                            "Success!", JOptionPane.PLAIN_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(this, "IOException Caught. Message: "
                            + exception.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: attempts to load from file, displays an error message if it fails
    public void trySave() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            // Check if the file name ends with ".json", if not, append it
            if (!fileToSave.getName().toLowerCase().endsWith(".json")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".json");
            }
            try {
                save(fileToSave.getAbsolutePath());
                JOptionPane.showMessageDialog(this, "Successfully saved to " + fileToSave.getAbsolutePath(),
                        "Success!", JOptionPane.PLAIN_MESSAGE);
            } catch (IOException exception) {
                JOptionPane.showMessageDialog(this, "IOException Caught. Message: "
                        + exception.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user with confirmation dialog and terminates the application if they confirm
    public void confirmQuit() {
        int command = JOptionPane.showConfirmDialog(this,
                "Do you want to quit? Unsaved progress will be lost!",
                "Confirmation Needed",
                JOptionPane.YES_NO_OPTION);
        if (command == JOptionPane.YES_OPTION) {
            Iterator<Event> i = EventLog.getInstance().iterator();
            System.out.println("Start of EventLog:");

            while (i.hasNext()) {
                System.out.println("\t" + i.next().toString());
            }

            System.out.println("End of EventLog.");
            System.exit(0);
        }
    }

    // -----------------------------------------------------------------------
    // getters
    // EFFECTS: gets the encounter
    public Encounter getEncounter() {
        return encounter;
    }

    // EFFECTS: gets the library
    public StatBlockLibrary getStatBlockLibrary() {
        return statBlockLibrary;
    }

    // EFFECTS: gets the main menu panel
    public MainMenuPanel getMainMenuPanel() {
        return mainMenuPanel;
    }
}
