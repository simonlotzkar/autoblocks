package model.statblockfields;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

// Represents the legendary rules for a character with a description and list of legendary actions which have no rolls
public class LegendaryMechanics implements Writable {
    // required fields
    private final String legendaryDescription;
    private final List<Ability> legendaryActions;

    // constructs a LegendaryMechanics with given fields
    public LegendaryMechanics(String legendaryDescription, List<Ability> legendaryActions) {
        this.legendaryDescription = legendaryDescription;
        this.legendaryActions = legendaryActions;
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

    // -----------------------------------------------------------------------------------------------------------------
    // constructs a json object with the fields of the legendary mechanics
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("legendaryDescription", legendaryDescription);
        json.put("legendaryActions", legendaryActionsToJson());
        return json;
    }

    // constructs a json array with the legendary actions
    private JSONArray legendaryActionsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Ability a : legendaryActions) {
            jsonArray.put(a.toJson());
        }
        return jsonArray;
    }
}
