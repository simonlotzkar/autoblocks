package model.statblockfields;

import exceptions.IncompleteFieldException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

// Represents the legendary rules for a character with a description and list of legendary actions which are abilities
public class LegendaryMechanics implements Writable {
    // required fields
    private final String legendaryDescription;
    private final List<Ability> legendaryActions;

    // EFFECTS: constructs a LegendaryMechanics with given fields and throws an exception if either field is left empty
    public LegendaryMechanics(String legendaryDescription, List<Ability> legendaryActions)
            throws IncompleteFieldException {
        if (legendaryActions.isEmpty()) {
            throw new IncompleteFieldException("(legendary actions) list is empty");
        } else if (legendaryDescription.isEmpty()) {
            throw new IncompleteFieldException("(legendary actions) description is empty");
        } else {
            this.legendaryDescription = legendaryDescription;
            this.legendaryActions = legendaryActions;
        }
    }

    @Override
    // EFFECTS: returns a json object representation of this legendary mechanics
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("legendaryDescription", legendaryDescription);
        json.put("legendaryActions", legendaryActionsToJson());
        return json;
    }

    // EFFECTS: returns a json object representation of this legendary mechanics' legendary actions
    private JSONArray legendaryActionsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Ability a : legendaryActions) {
            jsonArray.put(a.toJson());
        }
        return jsonArray;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    // EFFECTS: gets the legendary mechanics description
    public String getLegendaryDescription() {
        return legendaryDescription;
    }

    // EFFECTS: gets the legendary actions list
    public List<Ability> getLegendaryActions() {
        return legendaryActions;
    }
}
