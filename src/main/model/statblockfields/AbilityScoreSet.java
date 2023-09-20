package model.statblockfields;

import enums.AbilityScore;
import model.RollFormula;
import org.json.JSONObject;
import persistence.Writable;

// Represents a set of number values that range from 1 to 30 for each ability score
public class AbilityScoreSet implements Writable {
    // required fields
    private final int strength;
    private final int dexterity;
    private final int constitution;
    private final int intelligence;
    private final int wisdom;
    private final int charisma;

    // MODIFIES: this
    // EFFECTS: constructs this ability score set from the given six numbers, or throws an exception if one or any of
    //          them is out of bounds of [1,30]
    public AbilityScoreSet(int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma)
            throws IndexOutOfBoundsException {

        boolean oneIsLow = (strength < 1) || (dexterity < 1) || (constitution < 1) || (intelligence < 1)
                || (wisdom < 1) || (charisma < 1);
        boolean oneIsHigh = (strength > 30) || (dexterity > 30) || (constitution > 30) || (intelligence > 30)
                || (wisdom > 30) || (charisma > 30);

        if (oneIsLow || oneIsHigh) {
            throw new IndexOutOfBoundsException("(ability scores) one or more is greater than 30 or less than 1");
        } else {
            this.strength = strength;
            this.dexterity = dexterity;
            this.constitution = constitution;
            this.intelligence = intelligence;
            this.wisdom = wisdom;
            this.charisma = charisma;
        }
    }

    // EFFECTS: rolls a check for the given ability score or throws an exception if the string is invalid
    public int rollCheckAsInt(AbilityScore abilityScore) {
        return new RollFormula(1, 20, toModifier(abilityScore)).roll();
    }

    // EFFECTS: returns modifier for given ability score or throws an exception if the string is invalid
    public int toModifier(AbilityScore abilityScore) throws IndexOutOfBoundsException {
        switch (abilityScore) {
            case STRENGTH:
                return Math.floorDiv((strength - 10), 2);
            case DEXTERITY:
                return Math.floorDiv((dexterity - 10), 2);
            case CONSTITUTION:
                return Math.floorDiv((constitution - 10), 2);
            case INTELLIGENCE:
                return Math.floorDiv((intelligence - 10), 2);
            case WISDOM:
                return Math.floorDiv((wisdom - 10), 2);
            default:
                return Math.floorDiv((charisma - 10), 2);
        }
    }

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

    // -----------------------------------------------------------------------------------------------------------------
    // getters
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
}
