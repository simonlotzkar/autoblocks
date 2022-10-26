package model;

import model.statblockfields.*;

import java.util.List;

public class Character extends StatBlock {
    // required fields
    private final int maxHP;
    private int hp;
    private final StatBlock parentStatBlock;
    private String group;

    // EFFECTS: constructs a Character using a builder
    public Character(CharacterBuilder builder) {
        super(builder);
        this.maxHP = builder.maxHP;
        this.hp = builder.hp;
        this.parentStatBlock = builder.parentStatBlock;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // setters
    // REQUIRES: hp cannot exceed max hp
    // EFFECTS: sets current hp to sum of given int and hp
    public void changeHP(int change) {
        hp += change;
        if (hp > maxHP) {
            hp = maxHP;
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    // EFFECTS: returns current hp and max hp as a fraction in a string
    public String getHPString() {
        return hp + "/" + maxHP;
    }

    // EFFECTS: get original StatBlock
    public StatBlock getParentStatBlock() {
        return parentStatBlock;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // builder class
    public static class CharacterBuilder extends StatBlockBuilder {
        // required fields
        private final int maxHP;
        private final StatBlock parentStatBlock;
        private final int hp;

        // EFFECTS: constructs a builder with required fields,
        //          and ALL the parent StatBlock's fields whether they were built or not.
        public CharacterBuilder(StatBlock parentStatBlock, Title title, int xp, RollFormula hpFormula, int proficiency,
                                Armour armour, Speeds speeds, Senses senses, AbilityScores abilityScores,
                                List<Ability> abilities, List<Action> actions, Languages languages) {
            super(title, xp, hpFormula, proficiency, armour, speeds, senses, abilityScores,
                    abilities, actions, languages);
            super.savingThrowProficiencies(parentStatBlock.getSavingThrowProficiencies());
            super.skillProficiencies(parentStatBlock.getSkillProficiencies());
            super.conditionImmunities(parentStatBlock.getConditionImmunities());
            super.resistances(parentStatBlock.getResistances());
            super.legendaryMechanics(parentStatBlock.getLegendaryMechanics());

            this.maxHP = hpFormula.roll();
            this.hp = maxHP;
            this.parentStatBlock = parentStatBlock;
        }

        // EFFECTS: returns a new Character with required fields,
        //          and any optional fields that had their builder called.
        public Character build() {
            return new Character(this);
        }
    }
}
