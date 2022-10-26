package model;

public class Action extends Ability {
    // required fields
    private final String damageType;
    private final String reach;
    private final RollFormula hit;
    private final RollFormula damage;

    // EFFECTS: constructs an action with the given parameters
    public Action(String name, String description, String damageType, String reach,
                  RollFormula hit, RollFormula damage) {
        super(name, description);
        this.damageType = damageType;
        this.reach = reach;
        this.hit = hit;
        this.damage = damage;
    }

    // EFFECTS: prints the action with rolled hit and damage for given character
    public void displayRollForCharacter(String characterName) {
        System.out.println(characterName + "'s " + super.getDescription() + ", " + super.getName()
                + ", (" + reach + "ft) did " + hit.roll() + " to hit, and " + damage.roll() + " " + damageType
                + " damage.");
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    // EFFECTS: get damage type
    public String getDamageType() {
        return damageType;
    }

    // EFFECTS: get reach
    public String getReach() {
        return reach;
    }

    // EFFECTS: get hit formula
    public RollFormula getHit() {
        return hit;
    }

    // EFFECTS: get hit formula
    public RollFormula getDamage() {
        return damage;
    }
}