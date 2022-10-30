package model.statblockfields;

import org.json.JSONObject;
import persistence.Writable;

// Represents...
public class AbilityScores implements Writable {
    // required fields
    private final int strength;
    private final int dexterity;
    private final int constitution;
    private final int intelligence;
    private final int wisdom;
    private final int charisma;

    // EFFECTS: constructs ability scores from given required fields
    public AbilityScores(int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
    }

    // EFFECTS: calculates modifier from given ability score
    protected int convertToModifier(int modifier) {
        return (modifier - 10) / 2;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    // REQUIRES: given string is an ability score
    // EFFECTS: gets modifier for given ability score
    public int getModifier(String abilityScore) {
        switch (abilityScore) {
            case "strength":
                return convertToModifier(strength);
            case "dexterity":
                return convertToModifier(dexterity);
            case "constitution":
                return convertToModifier(constitution);
            case "intelligence":
                return convertToModifier(intelligence);
            case "wisdom":
                return convertToModifier(wisdom);
            default:
                return convertToModifier(charisma);
        }
    }

    // EFFECTS: gets strength
    public int getStrength() {
        return strength;
    }

    // EFFECTS: gets dexterity
    public int getDexterity() {
        return dexterity;
    }

    // EFFECTS: gets constitution
    public int getConstitution() {
        return constitution;
    }

    // EFFECTS: gets intelligence
    public int getIntelligence() {
        return intelligence;
    }

    // EFFECTS: gets wisdom
    public int getWisdom() {
        return wisdom;
    }

    // EFFECTS: gets charisma
    public int getCharisma() {
        return charisma;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // constructs a json object with the fields of the ability scores
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("strength", strength);
        json.put("dexterity", dexterity);
        json.put("constitution", constitution);
        json.put("intelligence", intelligence);
        json.put("wisdom", wisdom);
        json.put("charisma", charisma);
        return json;
    }
}
