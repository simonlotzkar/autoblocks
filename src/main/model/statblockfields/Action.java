package model.statblockfields;

import org.json.JSONObject;

// Represents...
public class Action extends Ability {
    // required fields
    private final String damageType;
    private final String reach;
    private final RollFormula hitFormula;
    private final RollFormula damageFormula;

    // EFFECTS: constructs an action with the given parameters
    public Action(String name, String description, String damageType, String reach,
                  RollFormula hitFormula, RollFormula damageFormula) {
        super(name, description);
        this.damageType = damageType;
        this.reach = reach;
        this.hitFormula = hitFormula;
        this.damageFormula = damageFormula;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    // EFFECTS: prints the action with rolled hit and damage for given name
    public String rollAsStringForName(String name) {
        return (name + "'s " + super.getDescription() + ", " + super.getName() + " (" + reach + "ft), did "
                + hitFormula.roll() + " to hit, and " + damageFormula.roll() + " " + damageType + " damage.");
    }

    // EFFECTS: get damage type
    public String getDamageType() {
        return damageType;
    }

    // EFFECTS: get reach
    public String getReach() {
        return reach;
    }

    // EFFECTS: get hit formula
    public RollFormula getHitFormula() {
        return hitFormula;
    }

    // EFFECTS: get hit formula
    public RollFormula getDamageFormula() {
        return damageFormula;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // constructs a json object with the fields of the action
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", getName());
        json.put("description", getDescription());
        json.put("damageType", damageType);
        json.put("reach", reach);
        json.put("hitFormula", hitFormula.toJson());
        json.put("damageFormula", hitFormula.toJson());
        return json;
    }
}