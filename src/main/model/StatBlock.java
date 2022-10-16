package model;

import java.util.*;

public class StatBlock {
    protected final String name;
    protected final String size;
    protected final String type;

    protected final RollFormula hpFormula;
    protected final int ac;
    protected final int speed;

    protected final int strength;
    protected final int dexterity;
    protected final int constitution;
    protected final int intelligence;
    protected final int wisdom;
    protected final int charisma;

    protected final List<Action> actions;

    // REQUIRES: name is unique
    // EFFECTS: constructs a statblock with the given parameters
    public StatBlock(String name, String size, String type,
                     RollFormula hpFormula, int ac, int speed,
                     int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma,
                     List<Action> actions) {

        this.name = name;
        this.size = size;
        this.type = type;

        this.hpFormula = hpFormula;
        this.ac = ac;
        this.speed = speed;

        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;

        this.actions = actions;
    }

    // EFFECTS: calculates modifier from given ability score
    protected int convertToModifier(int modifier) {
        return (modifier - 10) / 2;
    }

    // getters:
    // EFFECTS: gets hp formula as a string
    public String getHPString() {
        return hpFormula.getAmountOfDice() + "d"
                + hpFormula.getDieSides() + " + "
                + hpFormula.getModifier();
    }

    // EFFECTS: gets character name with first letter capitalized
    public String getName() {
        return name;
    }

    // EFFECTS: gets character size
    public String getSize() {
        return size;
    }

    // EFFECTS: gets character type
    public String getType() {
        return type;
    }

    // EFFECTS: gets hp formula
    public RollFormula getHpFormula() {
        return hpFormula;
    }

    // EFFECTS: gets character ac
    public int getAC() {
        return ac;
    }

    // EFFECTS: gets character speed
    public int getSpeed() {
        return speed;
    }

    // EFFECTS: gets character strength
    public int getStrength() {
        return strength;
    }

    // EFFECTS: gets character strength modifier
    public int getStrengthModifier() {
        return convertToModifier(strength);
    }

    // EFFECTS: gets character dexterity
    public int getDexterity() {
        return dexterity;
    }

    // EFFECTS: gets character dexterity modifier
    public int getDexterityModifier() {
        return convertToModifier(dexterity);
    }

    // EFFECTS: gets character constitution
    public int getConstitution() {
        return constitution;
    }

    // EFFECTS: gets character constitution modifier
    public int getConstitutionModifier() {
        return convertToModifier(constitution);
    }

    // EFFECTS: gets character intelligence
    public int getIntelligence() {
        return intelligence;
    }

    // EFFECTS: gets character intelligence modifier
    public int getIntelligenceModifier() {
        return convertToModifier(intelligence);
    }

    // EFFECTS: gets character wisdom
    public int getWisdom() {
        return wisdom;
    }

    // EFFECTS: gets character wisdom modifier
    public int getWisdomModifier() {
        return convertToModifier(wisdom);
    }

    // EFFECTS: gets character charisma
    public int getCharisma() {
        return charisma;
    }

    // EFFECTS: gets character charisma modifier
    public int getCharismaModifier() {
        return convertToModifier(charisma);
    }

    // EFFECTS: gets character actions
    public List<Action> getActions() {
        return actions;
    }

    // EFFECTS: gets an error string, only meant for overriding with character
    public String getGroup() {
        return "ERROR: GET STATBLOCK GROUP";
    }
}