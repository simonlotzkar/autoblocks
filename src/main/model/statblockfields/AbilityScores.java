package model.statblockfields;

import org.json.JSONObject;
import persistence.Writable;

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
    // EFFECTS: gets strength
    public int getStrength() {
        return strength;
    }

    // EFFECTS: gets strength modifier
    public int getStrengthModifier() {
        return convertToModifier(strength);
    }

    // EFFECTS: gets dexterity
    public int getDexterity() {
        return dexterity;
    }

    // EFFECTS: gets dexterity modifier
    public int getDexterityModifier() {
        return convertToModifier(dexterity);
    }

    // EFFECTS: gets constitution
    public int getConstitution() {
        return constitution;
    }

    // EFFECTS: gets constitution modifier
    public int getConstitutionModifier() {
        return convertToModifier(constitution);
    }

    // EFFECTS: gets intelligence
    public int getIntelligence() {
        return intelligence;
    }

    // EFFECTS: gets intelligence modifier
    public int getIntelligenceModifier() {
        return convertToModifier(intelligence);
    }

    // EFFECTS: gets wisdom
    public int getWisdom() {
        return wisdom;
    }

    // EFFECTS: gets wisdom modifier
    public int getWisdomModifier() {
        return convertToModifier(wisdom);
    }

    // EFFECTS: gets charisma
    public int getCharisma() {
        return charisma;
    }

    // EFFECTS: gets charisma modifier
    public int getCharismaModifier() {
        return convertToModifier(charisma);
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
