package model.statblockfields;

import org.json.JSONObject;

import java.util.HashMap;

// REPRESENTS: an action with a name, description, reach, hit roll formula,
//             and at least one damage roll with its damage type
public class Action extends Ability {
    // required fields
    private final String reach;
    private final RollFormula hitFormula;
    private final HashMap<String, RollFormula> damageMap;

    // EFFECTS: constructs an action with the given parameters
    public Action(String name, String description, String reach, RollFormula hitFormula,
                  HashMap<String, RollFormula> damageMap) {
        super(name, description);
        this.reach = reach;
        this.hitFormula = hitFormula;
        this.damageMap = damageMap;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    // EFFECTS: returns string with the action's rolled hit and damage
    public String stringRoll(String name) {
        return (name + "'s " + super.getDescription() + ", " + super.getName() + " (" + reach + "ft), did "
                + hitFormula.roll() + " to hit, and " + stringDamageMapRoll());
    }

    // EFFECTS: returns string of all damage rolls *post roll*
    public String stringDamageMapRoll() {
        StringBuilder damageStringBuilder = new StringBuilder();
        damageMap.forEach((s, rollFormula) ->
                damageStringBuilder.append(rollFormula.roll()).append(" ").append(s).append(" damage, "));
        return damageStringBuilder.toString();
    }

    // EFFECTS: get reach
    public String getReach() {
        return reach;
    }

    // EFFECTS: get hit formula
    public RollFormula getHitFormula() {
        return hitFormula;
    }

    // EFFECTS: get damage map
    public HashMap<String, RollFormula> getDamageMap() {
        return damageMap;
    }

    // EFFECTS: returns string of all damage roll formulae
    public String getDamageMapString() {
        StringBuilder damageStringBuilder = new StringBuilder();
        damageMap.forEach((s, rollFormula) -> damageStringBuilder.append("(").append(rollFormula.getRollString())
                .append(") ").append(s).append(" damage, "));
        return damageStringBuilder.toString();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // constructs a json object with the fields of the action
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", getName());
        json.put("description", getDescription());
        json.put("reach", reach);
        json.put("hitFormula", hitFormula.toJson());
        json.put("damageMap", damageMap);
        return json;
    }
}