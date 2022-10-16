package model;

public class RollFormula {
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
    //          modifier to the total
    public int roll() {
        return 0; //stub TODO
    }

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