package model;

import java.util.*;

public class StatBlock {
    private String name;
    private String size;
    private String type;

    private RollFormula hpFormula;
    private int ac;
    private int speed;

    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    private List<Action> actions;

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

    public String getName() {
        return this.name;
    }

}