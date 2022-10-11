package model;

import java.util.List;

public class Character extends StatBlock {
    private int hp;

    // constructs a character with the given parameters and rolls their hp
    public Character(String name, String size, String type,
                     RollFormula hpFormula, int ac, int speed,
                     int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma,
                     List<Action> actions) {
        super(name, size, type,
                hpFormula, ac, speed,
                strength, dexterity, constitution, intelligence, wisdom, charisma,
                actions);
        this.hp = hpFormula.roll();
    }

}
