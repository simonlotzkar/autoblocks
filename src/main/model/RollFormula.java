package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Random;

// Represents a die roll by the number of times to roll it, the sides for the roll, and a modifier to the total
public class RollFormula implements Writable {
    private final int amountOfDice;
    private final int dieSides;
    private final int modifier;

    // MODIFIES: this
    // EFFECTS: constructs a roll with the given parameters and throws an exception if the dies sides or amount of dice
    //          are negative
    public RollFormula(int amountOfDice, int dieSides, int modifier) throws IndexOutOfBoundsException {
        if (amountOfDice < 0) {
            throw new IndexOutOfBoundsException("given amount of dice is negative");
        } else if (dieSides < 0) {
            throw new IndexOutOfBoundsException("given die sides is negative");
        } else {
            this.amountOfDice = amountOfDice;
            this.dieSides = dieSides;
            this.modifier = modifier;
        }
    }

    // EFFECTS: randomly picks a number between 1 and the given die value as many times as declared, then adds the
    //          modifier to the total and returns it;
    //          but if given dice sides of 1 just returns 1 * amount of dice + modifier
    public int roll() {
        Random random = new Random();
        int total = 0;
        for (int i = 0; i < amountOfDice; i++) {
            total += (random.nextInt(dieSides) + 1);
        }
        return total + modifier;
    }

    @Override
    // EFFECTS: get roll formula as a string, uses + or - appropriately
    public String toString() {
        if (modifier > 0) {
            return amountOfDice + "d" + dieSides + "+" + modifier;
        } else if (modifier < 0) {
            return amountOfDice + "d" + dieSides + modifier;
        } else {
            return amountOfDice + "d" + dieSides;
        }
    }

    @Override
    // EFFECTS: constructs a json object with the fields of the roll formula
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("amountOfDice", amountOfDice);
        json.put("dieSides", dieSides);
        json.put("modifier", modifier);
        return json;
    }

    // ------------------------------------------------------------------------------------------
    // getters
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
}