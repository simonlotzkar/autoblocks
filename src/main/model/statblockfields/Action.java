package model.statblockfields;

import org.json.JSONObject;

import java.util.HashMap;

// REPRESENTS: an action with a name, description, reach, hit roll formula, and a set of damage types with rolls
public class Action extends Ability {
    // required fields
    private final String reach;
    private final RollFormula hitFormula;
    private final HashMap<String, RollFormula> damageMap;

    // REQUIRES: damage map has no duplicate damage types (prebuilt in) and there is at least one damage pair //TODO
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
    // EFFECTS: gets a descriptive string of this action
    public String getString() {
        return getDescription()
                + ", "
                + getName()
                + " ("
                + reach
                + "ft), ("
                + hitFormula.getString()
                + ") to hit"
                + getDamageString()
                + ".";
    }

    // EFFECTS: gets a descriptive string of the damage for this action
    private String getDamageString() {
        StringBuilder damageStringBuilder = new StringBuilder();
        damageMap.forEach((s, rollFormula) -> damageStringBuilder
                .append(" and (")
                .append(rollFormula.getString())
                .append(") ")
                .append(s)
                .append(" damage"));
        return damageStringBuilder.toString();
    }

    // EFFECTS: gets a descriptive string of this action with its hit and damage formulae rolled
    public String getRollString() {
        return getDescription()
                + ", "
                + getName()
                + " ("
                + reach
                + "ft), did ("
                + hitFormula.roll()
                + ") to hit"
                + getDamageRollString()
                + ".";
    }

    // EFFECTS: gets a descriptive string of the damage for this action with its formula rolled
    private String getDamageRollString() {
        StringBuilder damageStringBuilder = new StringBuilder();
        damageMap.forEach((s, rollFormula) -> damageStringBuilder
                .append(" and (")
                .append(rollFormula.roll())
                .append(") ")
                .append(s)
                .append(" damage"));
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