package persistence;

import model.LibraryAndEncounter;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes JSON representation of libraries and encounters to file
// CITATION: from JsonWriter.java in JsonSerializationDemo
public class JsonWriter {
    private static final int TAB = 4;
    private final String destination;
    private PrintWriter writer;

    // EFFECTS: constructs writer to write to destination file
    // CITATION: from JsonWriter.java in JsonSerializationDemo
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    // CITATION: from JsonWriter.java in JsonSerializationDemo
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    // CITATION: from JsonWriter.java in JsonSerializationDemo
    public void write(LibraryAndEncounter libraryAndEncounter) {
        JSONObject json = libraryAndEncounter.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    // CITATION: from JsonWriter.java in JsonSerializationDemo
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    // CITATION: from JsonWriter.java in JsonSerializationDemo
    private void saveToFile(String json) {
        writer.print(json);
    }
}
