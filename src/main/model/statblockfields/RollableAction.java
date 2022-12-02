package model.statblockfields;

import enums.DamageType;
import exceptions.IncompleteFieldException;
import model.RollFormula;
import org.json.JSONObject;

import java.util.HashMap;

// REPRESENTS: an action with a name, description, reach, hit roll formula, and a set of damage types with rolls
public class RollableAction extends Ability {
    // required fields
    private final int range;
    private final int longRange;
    private final RollFormula hitFormula;
    private final HashMap<DamageType, RollFormula> damageMap;

    // MODIFIES: this
    // EFFECTS: constructs a rollable action with the given range/long range, hit modifier, and hashmap of damage type
    //          and roll formulae key value pairs. throws exceptions for incomplete fields and if the ranges are out of
    //          bounds
    public RollableAction(String name, String description, int range, int longRange, int hitModifier,
                          HashMap<DamageType, RollFormula> damageMap) throws IncompleteFieldException,
            IndexOutOfBoundsException {
        super(name, description);
        if (range <= 0) {
            throw new IndexOutOfBoundsException("given range is less than or equal to zero");
        } else if (longRange != 0) {
            if (range >= longRange) {
                throw new IndexOutOfBoundsException("given long range is less than or equal to the given normal range");
            }
        } else if (damageMap == null) {
            throw new IncompleteFieldException("null damage formula given");
        }
        this.range = range;
        this.longRange = longRange;
        this.hitFormula = new RollFormula(1, 20, hitModifier);
        this.damageMap = damageMap;
    }

    // EFFECTS: gets a descriptive string of the damage for this action with its formula
    private String generateDamageFormulaString() {
        StringBuilder damageStringBuilder = new StringBuilder();
        damageMap.forEach((s, rollFormula) -> damageStringBuilder
                .append(" and (")
                .append(rollFormula.toString())
                .append(") ")
                .append(s)
                .append(" damage"));
        return damageStringBuilder.toString();
    }

    // EFFECTS: gets a descriptive string of this action with its hit and damage formulae rolled
    public String generateFullRollString() {
        String rangeString = String.valueOf(range);

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
                .append(s)
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
        json.put("hitModifier", hitFormula.getModifier());
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

        if (longRange != 0) {
            rangeString = range + "/" + longRange;
        }

        String hitModifier;

        if (hitFormula.getModifier() < 0) {
            hitModifier = String.valueOf(hitFormula.getModifier());
        } else {
            hitModifier = "+" + hitFormula.getModifier();
        }

        return getDescription()
                + ", "
                + getName()
                + " ("
                + rangeString
                + "ft), ("
                + hitModifier
                + ") to hit"
                + generateDamageFormulaString()
                + ".";
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    // EFFECTS: get range
    public int getRange() {
        return range;
    }

    // EFFECTS: get long range
    public int getLongRange() {
        return range;
    }

    // EFFECTS: get hit formula
    public RollFormula getHitFormula() {
        return hitFormula;
    }

    // EFFECTS: get damage map
    public HashMap<DamageType, RollFormula> getDamageMap() {
        return damageMap;
    }
}