package model;

import org.json.JSONObject;
import persistence.Writable;

public class RollFormula implements Writable {
    private final int amountOfDice;
    private final int dieSides;
    private final int modifier;

    // EFFECTS: constructs a roll with the given parameters
    public RollFormula(int amountOfDice, int dieSides, int modifier) {
        this.amountOfDice = amountOfDice;
        this.dieSides = dieSides;
        this.modifier = modifier;
    }

    // EFFECTS: randomly picks a number between 1 and the given die value as many times as declared, then adds the
    //          modifier to the total and returns it
    public int roll() {
        int total = 0;
        for (int i = 0; i < amountOfDice; i++) {
            total += Math.random() * dieSides;
        }
        return total + modifier;
    }

    // getters
    // EFFECTS: get roll formula as a string, uses + or - appropriately
    public String getRollString() {
        if (modifier >= 0) {
            return amountOfDice + "d" + dieSides + "+" + modifier;
        } else {
            return amountOfDice + "d" + dieSides + "-" + modifier;
        }
    }

    // EFFECTS: get amount of dice
    public int getAmountOfDice() {
        return amountOfDice;
    }

    // EFFECTS: get die sides
    public int getDieSides() {
        return dieSides;
    }

    // EFFECTS: get modifier
    public int getModifier() {
        return modifier;
    }

    // constructs a json object with the fields of the roll formula
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("amountOfDice", amountOfDice);
        json.put("dieSides", dieSides);
        json.put("modifier", modifier);
        return json;
    }
}