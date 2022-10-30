package model.statblockfields;

import org.json.JSONObject;
import persistence.Writable;

// Represents...
public class Ability implements Writable {
    // required fields
    private final String name;
    private final String description;

    // EFFECTS: constructs an ability with the given parameters
    public Ability(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    // EFFECTS: returns ability name
    public String getName() {
        return name;
    }

    // EFFECTS: returns ability description
    public String getDescription() {
        return description;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // constructs a json object with the fields of the ability
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("description", description);
        return json;
    }
}
