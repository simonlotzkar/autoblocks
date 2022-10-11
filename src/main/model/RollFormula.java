package model;

public class RollFormula {
    private int amountOfDice;
    private int die;
    private int modifier;

    // EFFECTS: constructs a roll with the given parameters
    public RollFormula(int amountOfDice, int die, int modifier) {
        this.amountOfDice = amountOfDice;
        this.die = die;
        this.modifier = modifier;
    }

    // EFFECTS: randomly picks a number between 1 and the given die value as many times as declared, then adds the
    //          modifier to the total
    public int roll() {
        return 0; //stub TODO
    }

}