package model;

import org.json.JSONObject;
import persistence.Writable;

public class LibraryAndEncounter implements Writable {
    private final Library library;
    private final Encounter encounter;

    // EFFECTS: constructs the joined object of library and encounter
    public LibraryAndEncounter(Library library, Encounter encounter) {
        this.library = library;
        this.encounter = encounter;
    }

    // EFFECTS: returns the library
    public Library getLibrary() {
        return library;
    }

    // EFFECTS: returns the encounter
    public Encounter getEncounter() {
        return encounter;
    }

    // converts to a json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("library", library.toJson());
        json.put("encounter", encounter.toJson());
        return json;
    }
}
