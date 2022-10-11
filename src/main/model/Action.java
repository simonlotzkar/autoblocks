package model;

public class Action {
    private String name;
    private String damageType;
    private int reach;
    private RollFormula hit;
    private RollFormula damage;
    private String description;

    // EFFECTS: constructs an action with the given parameters
    public Action(String name, String damageType, int reach, RollFormula hit, RollFormula damage, String description) {
        this.name = name;
        this.damageType = damageType;
        this.reach = reach;
        this.hit = hit;
        this.damage = damage;
        this.description = description;
    }

    // EFFECTS: prints the given action with rolled hit and damage
    public void execute() {
        //stub TODO
    }

}