package model;

import java.util.List;

public class StatBlockTest {
}
//    // REQUIRES: name is unique
//    // EFFECTS: constructs a statblock with the given parameters
//    public StatBlock(String name, String size, String type,
//                     RollFormula hpFormula, int ac, int speed, int initiativeBonus,
//                     int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma,
//                     List<Action> actions) {
//
//        this.name = name;
//        this.size = size;
//        this.type = type;
//
//        this.hpFormula = hpFormula;
//        this.ac = ac;
//        this.speed = speed;
//        this.initiativeBonus = initiativeBonus;
//
//        this.strength = strength;
//        this.dexterity = dexterity;
//        this.constitution = constitution;
//        this.intelligence = intelligence;
//        this.wisdom = wisdom;
//        this.charisma = charisma;
//
//        this.actions = actions;
//    }
//    // EFFECTS: calculates modifier from given ability score
//    protected int convertToModifier(int modifier) {
//        return (modifier - 10) / 2;
//    }
//
//    // getters:
//    // EFFECTS: gets statblock hp formula as a string
//    public String getHPString() {
//        return hpFormula.getAmountOfDice() + "d"
//                + hpFormula.getDieSides() + " + "
//                + hpFormula.getModifier();
//    }
