package model;

import java.util.List;

public class CharacterTest {
}
//    // EFFECTS: constructs a character with the same parameters of the statblock its generated from, but adds a max hp
//    //          and current hp based on a roll from the given hprollformula. also has a group that is set to null.
//    public Character(String name, String size, String type,
//                     RollFormula hpFormula, int ac, int speed, int initiativeBonus,
//                     int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma,
//                     List<Action> actions) {
//        super(name, size, type,
//                hpFormula, ac, speed, initiativeBonus,
//                strength, dexterity, constitution, intelligence, wisdom, charisma,
//                actions);
//        maxHP = hpFormula.roll();
//        hp = maxHP;
//        parentStatBlockName = super.getName();
//    }