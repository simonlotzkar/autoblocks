package model;

import model.statblockfields.*;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// Represents all stats and details of an NPC
public class StatBlock implements Writable {
    // required fields
    protected final Title title;
    protected final RollFormula hpFormula;
    protected final Armour armour;
    protected final Speeds speeds;
    protected final Senses senses;
    protected final int proficiency;
    protected final int xp;
    protected final AbilityScores abilityScores;
    protected final List<Action> actions;

    // optional fields
    protected final Languages languages;
    protected final List<Ability> abilities;
    protected final List<String> savingThrowProficiencies;
    protected final List<String> skillProficiencies;
    protected final List<String> conditionImmunities;
    protected final HashMap<String, String> resistances;
    protected final LegendaryMechanics legendaryMechanics;

    // EFFECTS: constructs a StatBlock using a builder
    public StatBlock(StatBlockBuilder builder) {
        this.title = builder.title;
        this.xp = builder.xp;
        this.hpFormula = builder.hpFormula;
        this.proficiency = builder.proficiency;
        this.armour = builder.armour;
        this.speeds = builder.speeds;
        this.abilityScores = builder.abilityScores;
        this.abilities = builder.abilities;
        this.actions = builder.actions;
        this.savingThrowProficiencies = builder.savingThrowProficiencies;
        this.skillProficiencies = builder.skillProficiencies;
        this.conditionImmunities = builder.conditionImmunities;
        this.resistances = builder.resistances;
        this.languages = builder.languages;
        this.senses = builder.senses;
        this.legendaryMechanics = builder.legendaryMechanics;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters:
    // EFFECTS: calculates and returns the challenge ratings from xp
    public String getChallengeRating() {
        if (xp < 24) {
            return "0";
        } else if (xp < 49) {
            return "1/8";
        } else if (xp < 99) {
            return "1/4";
        } else if (xp < 199) {
            return "1/2";
        } else if (xp < 449) {
            return "1";
        } else if (xp < 699) {
            return "2";
        } else if (xp < 1099) {
            return "3";
        } else if (xp < 1799) {
            return "4";
        } else if (xp < 2499) {
            return "5";
        } else if (xp < 2899) {
            return "6";
        } else {
            return getChallengeRatingPartTwo();
        }
    }

    // EFFECTS: calculates and returns the challenge rating ratings from xp (3,400-19,000)
    protected String getChallengeRatingPartTwo() {
        if (xp < 3899) {
            return "7";
        } else if (xp < 4999) {
            return "8";
        } else if (xp < 5899) {
            return "9";
        } else if (xp < 7199) {
            return "10";
        } else if (xp < 8399) {
            return "11";
        } else if (xp < 9999) {
            return "12";
        } else if (xp < 11499) {
            return "13";
        } else if (xp < 12999) {
            return "14";
        } else if (xp < 14999) {
            return "15";
        } else if (xp < 18999) {
            return "16";
        } else {
            return getChallengeRatingPartThree();
        }
    }

    // EFFECTS: calculates and returns the challenge rating ratings from xp (20,000-inf)
    protected String getChallengeRatingPartThree() {
        if (xp < 19999) {
            return "17";
        } else if (xp < 21999) {
            return "18";
        } else if (xp < 24999) {
            return "19";
        } else if (xp < 32999) {
            return "20";
        } else if (xp < 40999) {
            return "21";
        } else if (xp < 49999) {
            return "22";
        } else if (xp < 61999) {
            return "23";
        } else if (xp < 74999) {
            return "24";
        } else if (xp < 89999) {
            return "25";
        } else if (xp < 104999) {
            return "26";
        } else {
            return getChallengeRatingPartFour();
        }
    }

    // EFFECTS: calculates and returns the challenge rating from xp, third helper
    protected String getChallengeRatingPartFour() {
        if (xp < 119999) {
            return "27";
        } else if (xp < 134999) {
            return "28";
        } else if (xp < 154999) {
            return "29";
        } else {
            return "30+";
        }
    }

    // EFFECTS: gets title
    public Title getTitle() {
        return title;
    }

    // EFFECTS: gets xp
    public int getXP() {
        return xp;
    }

    // EFFECTS: gets hp formula
    public RollFormula getHPFormula() {
        return hpFormula;
    }

    // EFFECTS: returns hp formula as a string
    public String getHPString() {
        return hpFormula.toString();
    }

    // EFFECTS: gets proficiency
    public int getProficiency() {
        return proficiency;
    }

    // EFFECTS: gets armour
    public Armour getArmour() {
        return armour;
    }

    // EFFECTS: gets speeds
    public Speeds getSpeeds() {
        return speeds;
    }

    // EFFECTS: gets senses
    public Senses getSenses() {
        return senses;
    }

    // EFFECTS: gets ability scores
    public AbilityScores getAbilityScores() {
        return abilityScores;
    }

    // EFFECTS: gets abilities
    public List<Ability> getAbilities() {
        return abilities;
    }

    // EFFECTS: gets actions
    public List<Action> getActions() {
        return actions;
    }

    // EFFECTS: gets saving throw proficiencies
    public List<String> getSavingThrowProficiencies() {
        return savingThrowProficiencies;
    }

    // EFFECTS: returns all saving throw proficiencies as a string, or "" if none
    public String getSavingThrowProficienciesString() {
        if (savingThrowProficiencies == null) {
            return "";
        }
        StringBuilder savingThrowProficienciesString = new StringBuilder();
        for (String savingThrow : savingThrowProficiencies) {
            savingThrowProficienciesString.append(getSavingThrowProficiencyString(savingThrow)).append(", ");
        }
        return savingThrowProficienciesString.toString();
    }

    // REQUIRES: skill is in skill proficiencies
    // EFFECTS: returns a statement of the given skill including the bonus;
    protected String getSavingThrowProficiencyString(String savingThrow) {
        return savingThrow + " " + (abilityScores.getModifier(savingThrow.toLowerCase()) + proficiency);
    }

    // EFFECTS: gets skill proficiencies
    public List<String> getSkillProficiencies() {
        return skillProficiencies;
    }

    // EFFECTS: returns all skill proficiencies as a string, or "" if none
    public String getSkillProficienciesString() {
        if (skillProficiencies == null) {
            return "";
        }
        StringBuilder skillProficienciesString = new StringBuilder();
        for (String skill : skillProficiencies) {
            skillProficienciesString.append(getSkillProficiencyString(skill)).append(", ");
        }
        return skillProficienciesString.toString();
    }

    // REQUIRES: skill is in skill proficiencies
    // EFFECTS: returns a statement of the given skill including the bonus;
    protected String getSkillProficiencyString(String skill) {
        return skill + " " + (abilityScores.getModifier(getSkillAbilityScore(skill)) + proficiency);
    }

    // EFFECTS: returns the ability score for the given skill
    protected String getSkillAbilityScore(String skill) {
        if ("acrobatics".equalsIgnoreCase(skill)
                || "sleightOfHand".equalsIgnoreCase(skill)
                || "stealth".equalsIgnoreCase(skill)) {
            return "dexterity";
        } else if ("arcana".equalsIgnoreCase(skill)
                || "history".equalsIgnoreCase(skill)
                || "religion".equalsIgnoreCase(skill)
                || "investigation".equalsIgnoreCase(skill)
                || "nature".equalsIgnoreCase(skill)) {
            return "intelligence";
        } else if ("athletics".equalsIgnoreCase(skill)) {
            return "strength";
        } else if ("deception".equalsIgnoreCase(skill)
                || "intimidation".equalsIgnoreCase(skill)
                || "performance".equalsIgnoreCase(skill)
                || "persuasion".equalsIgnoreCase(skill)) {
            return "charisma";
        } else {
            return "wisdom";
        }
    }

    // EFFECTS: gets condition immunities
    public List<String> getConditionImmunities() {
        return conditionImmunities;
    }

    // EFFECTS: returns all condition immunities as a string, or "" if none
    public String getConditionImmunitiesString() {
        if (conditionImmunities == null) {
            return "";
        }
        StringBuilder conditionImmunitiesString = new StringBuilder();
        for (String conditionImmunity : conditionImmunities) {
            conditionImmunitiesString.append(conditionImmunity).append(", ");
        }
        return conditionImmunitiesString.toString();
    }

    // EFFECTS: gets resistances
    public HashMap<String, String> getResistances() {
        return resistances;
    }

    // EFFECTS: returns all resistances as a string, or "" if none
    public String getResistancesString() {
        if (resistances == null) {
            return "";
        }
        StringBuilder resistancesString = new StringBuilder();
        resistances.forEach((String damageType, String resistanceType) -> resistancesString
                .append(getResistanceString(damageType)).append(", "));
        return resistancesString.toString();
    }

    // REQUIRES: damage type and resistance type are in resistances
    // EFFECTS: returns a statement of the damage type and resistance type
    public String getResistanceString(String damageType) {
        return damageType + " " + resistances.get(damageType);
    }

    // EFFECTS: gets languages
    public Languages getLanguages() {
        return languages;
    }

    // EFFECTS: gets legendary mechanics
    public LegendaryMechanics getLegendaryMechanics() {
        return legendaryMechanics;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // builder class
    public static class StatBlockBuilder {
        // required fields
        protected final Title title;
        protected final RollFormula hpFormula;
        protected final Armour armour;
        protected final Speeds speeds;
        protected final Senses senses;
        protected final int proficiency;
        protected final int xp;
        protected final AbilityScores abilityScores;
        protected List<Action> actions;

        // optional fields
        protected Languages languages;
        protected List<Ability> abilities;
        protected List<String> savingThrowProficiencies;
        protected List<String> skillProficiencies;
        protected List<String> conditionImmunities;
        protected HashMap<String, String> resistances;
        protected LegendaryMechanics legendaryMechanics;

        // EFFECTS: constructs a builder with required fields
        public StatBlockBuilder(Title title, int xp, RollFormula hpFormula, int proficiency, Armour armour,
                                Speeds speeds, Senses senses, AbilityScores abilityScores, List<Action> actions) {
            this.title = title;
            this.hpFormula = hpFormula;
            this.armour = armour;
            this.speeds = speeds;
            this.senses = senses;
            this.proficiency = proficiency;
            this.xp = xp;
            this.abilityScores = abilityScores;
            this.actions = actions;
        }

        // EFFECTS: returns a new StatBlock with required fields,
        //          and any optional fields that had their builder called.
        public StatBlock build() {
            return new StatBlock(this);
        }

        // EFFECTS: returns a builder that assigns the given abilities to the StatBlock
        public StatBlockBuilder abilities(List<Ability> abilities) {
            this.abilities = abilities;
            return this;
        }

        // EFFECTS: returns a builder that assigns the given languages to the StatBlock
        public StatBlockBuilder languages(Languages languages) {
            this.languages = languages;
            return this;
        }

        // EFFECTS: returns a builder that assigns the given saving throw proficiencies to the StatBlock
        public StatBlockBuilder savingThrowProficiencies(List<String> savingThrowProficiencies) {
            this.savingThrowProficiencies = savingThrowProficiencies;
            return this;
        }

        // EFFECTS: returns a builder that assigns the given skill proficiencies to the StatBlock
        public StatBlockBuilder skillProficiencies(List<String> skillProficiencies) {
            this.skillProficiencies = skillProficiencies;
            return this;
        }

        // EFFECTS: returns a builder that assigns the given condition immunities to the StatBlock
        public StatBlockBuilder conditionImmunities(List<String> conditionImmunities) {
            this.conditionImmunities = conditionImmunities;
            return this;
        }

        // EFFECTS: returns a builder that assigns the given resistances to the StatBlock
        public StatBlockBuilder resistances(HashMap<String, String> resistances) {
            this.resistances = resistances;
            return this;
        }

        // EFFECTS: returns a builder that assigns the given legendary mechanics to the StatBlock
        public StatBlockBuilder legendaryMechanics(LegendaryMechanics legendaryMechanics) {
            this.legendaryMechanics = legendaryMechanics;
            return this;
        }
    }

    // converts the statblock to a json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title.toJson());
        json.put("xp", xp);
        json.put("hpFormula", hpFormula.toJson());
        json.put("proficiency", proficiency);
        json.put("armour", armour.toJson());
        json.put("speeds", speeds.toJson());
        json.put("senses", senses.toJson());
        json.put("abilityScores", abilityScores.toJson());
        json.put("actions", actionsToJson());
        return optionalFieldsToJson(json);
    }

    // constructs a json array with the abilities
    protected JSONArray abilitiesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Ability a : abilities) {
            jsonArray.put(a.toJson());
        }
        return jsonArray;
    }

    // constructs a json array with the actions
    protected JSONArray actionsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Action a : actions) {
            jsonArray.put(a.toJson());
        }
        return jsonArray;
    }

    // adds optional fields that exist to the given json object and returns it
    protected JSONObject optionalFieldsToJson(JSONObject json) {
        if (languages != null) {
            json.put("languages", languages.toJson());
        }
        if (abilities != null) {
            json.put("abilities", abilitiesToJson());
        }
        if (savingThrowProficiencies != null) {
            json.put("savingThrowProficiencies", savingThrowProficiencies);
        }
        if (skillProficiencies != null) {
            json.put("skillProficiencies", skillProficiencies);
        }
        if (conditionImmunities != null) {
            json.put("conditionImmunities", conditionImmunities);
        }
        if (resistances != null) {
            json.put("resistances", resistances);
        }
        if (legendaryMechanics != null) {
            json.put("legendaryMechanics", legendaryMechanics.toJson());
        }
        return json;
    }

    @Override
    // returns a string representation of the character
    public String toString() {
        return this.title.getName() + ". CR: " + this.getChallengeRating();
    }
}