package model;

import java.util.List;

public class Character extends StatBlock {
    private final int maxHP;
    private final String parentStatBlockName;

    private int hp;
    private String group;

    // EFFECTS: constructs a character with the same parameters of the statblock its generated from, but adds a max hp
    //          and current hp based on a roll from the given hprollformula. also has a group that is set to null.
    public Character(String name, String size, String type,
                     RollFormula hpFormula, int ac, int speed, int initiativeBonus,
                     int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma,
                     List<Action> actions, String parentStatBlockName) {
        super(name, size, type,
                hpFormula, ac, speed, initiativeBonus,
                strength, dexterity, constitution, intelligence, wisdom, charisma,
                actions);
        maxHP = hpFormula.roll();
        hp = maxHP;
        this.parentStatBlockName = parentStatBlockName;
    }

    // setters
    // EFFECTS: set group
    public void setGroup(String group) {
        this.group = group;
    }

    // REQUIRES: hp cannot exceed max hp
    // EFFECTS: sets current hp to sum of given int and hp
    public void changeHP(int change) {
        hp += change;
        if (hp > maxHP) {
            hp = maxHP;
        }
    }

    // getters
    // EFFECTS: get current hp and max hp as a fraction in a string
    public String getHPString() {
        return hp + "/" + maxHP;
    }

    // EFFECTS: get group
    public String getGroup() {
        return group;
    }

    // EFFECTS: get original statblock name
    public String getParentStatBlockName() {
        return parentStatBlockName;
    }
}
