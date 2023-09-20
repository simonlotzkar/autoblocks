package model.statblockfields;

import exceptions.IncompleteFieldException;
import org.json.JSONObject;
import persistence.Writable;

// Represents an ability or legendary action which has a nonempty name and description
public class Ability implements Writable {
    // required fields
    private final String name;
    private final String description;

    // EFFECTS: constructs this ability with the given name and description, throws an empty name exception if given an
    //          empty name
    public Ability(String name, String description) throws IncompleteFieldException {
        if (name == null || name.isEmpty()) {
            throw new IncompleteFieldException("(ability) has no name or is null");
        } else {
            this.name = name;
            this.description = description;
        }
    }

    @Override
    // returns a json object with the fields of this ability
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("description", description);
        return json;
    }

    @Override
    // EFFECTS: returns a string representation of this ability
    public String toString() {
        return getName() + ". " + getDescription();
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
}
