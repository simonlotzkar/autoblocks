package model;

import java.util.List;

public class Character extends StatBlock {
    private int hp;
    private final int maxHP;
    private String group;

    // EFFECTS: constructs a character with the given parameters and rolls max hp
    public Character(String name, String size, String type,
                     RollFormula hpFormula, int ac, int speed,
                     int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma,
                     List<Action> actions) {
        super(name, size, type,
                hpFormula, ac, speed,
                strength, dexterity, constitution, intelligence, wisdom, charisma,
                actions);
        this.maxHP = hpFormula.roll();
        this.hp = maxHP;
    }

    // setters
    // EFFECTS: set group
    public void setGroup(String group) {
        this.group = group;
    }

    // REQUIRES: hp cannot exceed max hp
    // EFFECTS: set current hp
    public void setHP(int newHP) {
        hp = newHP;
    }

    // REQUIRES: hp cannot exceed max hp
    // EFFECTS: sets current hp to sum of given int and hp
    public void changeHP(int change) {
        hp += change;
    }

    // getters
    // EFFECTS: get current hp and max hp as a fraction in a string
    public String getHPString() {
        return hp + "/" + maxHP;
    }

    // EFFECTS: get current hp
    public int getCurrentHP() {
        return hp;
    }

    // EFFECTS: get max hp
    public int getMaxHP() {
        return maxHP;
    }

    // EFFECTS: get group
    public String getGroup() {
        return group;
    }
}
