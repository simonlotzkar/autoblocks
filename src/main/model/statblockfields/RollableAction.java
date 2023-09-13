package model.statblockfields;

import enums.DamageType;
import exceptions.IncompleteFieldException;
import model.RollFormula;
import org.json.JSONObject;
import persistence.Writable;

import java.util.HashMap;

// REPRESENTS: an action with a name, description, reach, hit roll formula, and a set of damage types with rolls
public class RollableAction extends Ability implements Writable {
    // required fields
    private final int range;
    private final int longRange;
    private final int hitModifier;
    private final HashMap<DamageType, RollFormula> damageMap;

    // REQUIRES: range must be greater than 0, long range must be greater than range or 0, damage map cannot be empty
    // MODIFIES: this
    // EFFECTS: constructs a rollable action with the given name, description, range, long range, hit modifier, and
    //          damage map
    public RollableAction(String name, String description, int range, int longRange, int hitModifier,
                          HashMap<DamageType, RollFormula> damageMap) throws IncompleteFieldException,
            IndexOutOfBoundsException {
        super(name, description);
        if (range <= 0) {
            throw new IndexOutOfBoundsException("range is less than or equal to zero");
        } else if (longRange != 0) {
            if (range >= longRange) {
                throw new IndexOutOfBoundsException("range is greater than or equal to long range");
            }
        } else if (damageMap == null) {
            throw new IncompleteFieldException("null damage formula given");
        }
        this.range = range;
        this.longRange = longRange;
        this.hitModifier = hitModifier;
        this.damageMap = damageMap;
    }

    // EFFECTS: gets a descriptive string of the damage for this action with its formula
    private String generateDamageFormulaString() {
        StringBuilder damageStringBuilder = new StringBuilder();
        damageMap.forEach((s, rollFormula) -> damageStringBuilder
                .append(" and (")
                .append(rollFormula.toString())
                .append(") ")
                .append(s.toString().toLowerCase())
                .append(" damage"));
        return damageStringBuilder.toString();
    }

    // EFFECTS: gets a descriptive string of this action with its hit and damage formulae rolled
    public String generateFullRollString() {
        String rangeString = String.valueOf(range);
        RollFormula hitFormula = new RollFormula(1, 20, hitModifier);

        if (longRange != 0) {
            rangeString = range + "/" + longRange;
        }

        return getDescription()
                + ", "
                + getName()
                + " ("
                + rangeString
                + "ft), did ("
                + hitFormula.roll()
                + ") to hit"
                + generateDamageRollString()
                + ".";
    }

    // EFFECTS: gets a descriptive string of the damage for this action with its formula rolled
    private String generateDamageRollString() {
        StringBuilder damageStringBuilder = new StringBuilder();
        damageMap.forEach((s, rollFormula) -> damageStringBuilder
                .append(" and (")
                .append(rollFormula.roll())
                .append(") ")
                .append(s.toString().toLowerCase())
                .append(" damage"));
        return damageStringBuilder.toString();
    }

    @Override
    // EFFECTS: returns a json object representation of this rollable action
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", getName());
        json.put("description", getDescription());
        json.put("range", range);
        json.put("longRange", longRange);
        json.put("hitModifier", hitModifier);
        json.put("damageMap", damageMapToJson());
        return json;
    }

    // EFFECTS: returns a json object representation of this rollable action's damage map
    private JSONObject damageMapToJson() {
        JSONObject json = new JSONObject();

        damageMap.forEach((damageType, rollFormula) ->
                json.put(damageType.toString(), rollFormula.toJson()));

        return json;
    }

    @Override
    // EFFECTS: returns a descriptive string of this rollable action
    public String toString() {
        String rangeString = String.valueOf(range);
        String hitModifierString = "+" + hitModifier;

        if (longRange != 0) {
            rangeString = range + "/" + longRange;
        }

        if (hitModifier < 0) {
            hitModifierString = String.valueOf(hitModifier);
        }

        return getDescription()
                + ", "
                + getName()
                + " ("
                + rangeString
                + "ft), ("
                + hitModifierString
                + ") to hit"
                + generateDamageFormulaString();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    // EFFECTS: get range
    public int getRange() {
        return range;
    }

    // EFFECTS: get long range
    public int getLongRange() {
        return longRange;
    }

    // EFFECTS: get hit formula
    public Integer getHitModifier() {
        return hitModifier;
    }

    // EFFECTS: get damage map
    public HashMap<DamageType, RollFormula> getDamageMap() {
        return damageMap;
    }
}