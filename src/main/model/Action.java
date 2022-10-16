package model;

public class Action {
    private final String name;
    private final String description;
    private final String damageType;
    private final int reach;
    private final RollFormula hit;
    private final RollFormula damage;

    // EFFECTS: constructs an action with the given parameters
    public Action(String name, String description, String damageType, int reach, RollFormula hit, RollFormula damage) {
        this.name = name;
        this.description = description;
        this.damageType = damageType;
        this.reach = reach;
        this.hit = hit;
        this.damage = damage;
    }

    // EFFECTS: prints the given action with rolled hit and damage
    public void takeAction() {
        //stub TODO
    }

    // getters
    // EFFECTS: get hit formula as a string
    public String getHitString() {
        return hit.getAmountOfDice() + "d"
                + hit.getDieSides() + " + "
                + hit.getModifier();
    }

    // EFFECTS: get damage formula as a string
    public String getDamageString() {
        return damage.getAmountOfDice() + "d"
                + damage.getDieSides() + " + "
                + damage.getModifier();
    }

    // EFFECTS: get action name
    public String getName() {
        return name;
    }

    // EFFECTS: get description
    public String getDescription() {
        return description;
    }

    // EFFECTS: get damage type
    public String getDamageType() {
        return damageType;
    }

    // EFFECTS: get reach
    public int getReach() {
        return reach;
    }

    // EFFECTS: get hit
    public RollFormula getHit() {
        return hit;
    }

    // EFFECTS: get damage
    public RollFormula getDamage() {
        return damage;
    }

}