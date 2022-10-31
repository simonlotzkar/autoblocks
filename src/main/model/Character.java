package model;

import model.statblockfields.*;
import org.json.JSONObject;

import java.util.List;

// Represents...
public class Character extends StatBlock {
    // required fields
    private int maxHP;
    private int hp;
    private final StatBlock parentStatBlock;

    // EFFECTS: constructs a Character using a builder
    public Character(CharacterBuilder builder) {
        super(builder);
        this.maxHP = builder.maxHP;
        this.hp = builder.hp;
        this.parentStatBlock = builder.parentStatBlock;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // setters
    // EFFECTS: sets current hp to sum of given int and hp; if hp is greater than maxhp, sets hp to maxhp.
    public void changeHP(int change) {
        hp += change;
        if (hp > maxHP) {
            hp = maxHP;
        }
    }

    // REQUIRES: given hp cannot be greater than maxHP
    // EFFECTS: sets current hp to given hp
    public void setHP(int hp) {
        this.hp = hp;
    }

    // REQUIRES: given max HP cannot be greater than maxHP
    // EFFECTS: sets current max HP to given max HP
    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    // EFFECTS: returns current hp and max hp as a fraction in a string
    @Override
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

            this.maxHP = hpFormula.roll();
            this.hp = maxHP;
            this.parentStatBlock = parentStatBlock;

            super.savingThrowProficiencies(parentStatBlock.getSavingThrowProficiencies());
            super.skillProficiencies(parentStatBlock.getSkillProficiencies());
            super.conditionImmunities(parentStatBlock.getConditionImmunities());
            super.resistances(parentStatBlock.getResistances());
            super.legendaryMechanics(parentStatBlock.getLegendaryMechanics());
        }

        // EFFECTS: returns a new Character with required fields,
        //          and any optional fields that had their builder called.
        public Character build() {
            return new Character(this);
        }
    }

    // converts the character to a json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("parentStatBlock", parentStatBlock.toJson());
        json.put("title", title.toJson());
        json.put("xp", xp);
        json.put("hpFormula", hpFormula.toJson());
        json.put("maxHP", maxHP);
        json.put("hp", hp);
        json.put("proficiency", proficiency);
        json.put("armour", armour.toJson());
        json.put("speeds", speeds.toJson());
        json.put("senses", senses.toJson());
        json.put("abilityScores", abilityScores.toJson());
        json.put("abilities", abilitiesToJson());
        json.put("actions", actionsToJson());
        json.put("languages", languages.toJson());
        return optionalFieldsToJson(json);
    }
}
