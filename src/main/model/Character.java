package model;

import model.statblockfields.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class Character extends StatBlock {
    // required fields
    private final int maxHP;
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

//    @Override
//    public JSONObject toJson() {
//        JSONObject json = new JSONObject();
//        json.put("title", title.toJson());
//        json.put("xp", xp);
//        json.put("hpFormula", hpFormula.toJson());
//        json.put("proficiency", proficiency);
//        json.put("armour", armour.toJson());
//        json.put("speeds", speeds.toJson());
//        json.put("senses", senses.toJson());
//        json.put("abilityScores", abilityScores.toJson());
//        json.put("abilities", abilitiesToJson());
//        json.put("actions", actionsToJson());
//        json.put("languages", languages.toJson());
//        json.put("savingThrowProficiencies", savingThrowProficiencies.toJson());
//        json.put("skillProficiencies", skillProficiencies.toJson());
//        json.put("conditionImmunities", conditionImmunities.toJson());
//        json.put("resistances", resistances.toJson());
//        json.put("legendaryMechanics", legendaryMechanics.toJson());
//
//        json.put("hp", hp);
//        json.put("maxHP", maxHP);
//        json.put("parentStatBlock", parentStatBlock.toJson());
//        return json;
//    }
//
//    // constructs a json array with the abilities
//    public JSONArray abilitiesToJson() {
//        JSONArray jsonArray = new JSONArray();
//        for (Ability a : abilities) {
//            jsonArray.put(a.toJson());
//        }
//        return jsonArray;
//    }
//
//    // constructs a json array with the actions
//    public JSONArray actionsToJson() {
//        JSONArray jsonArray = new JSONArray();
//        for (Action a : actions) {
//            jsonArray.put(a.toJson());
//        }
//        return jsonArray;
//    }
}
