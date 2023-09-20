package model;

import enums.*;
import exceptions.IncompleteFieldException;
import model.statblockfields.*;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// Represents a statblock with a title, hp formula, armour, speeds, senses, proficiency bonus, xp, ability score set,
// rollable actions, and optionally a languages, abilities, saving throw proficiencies, skill proficiencies, condition
// immunities, resistances, and/or legendary mechanics
public class StatBlock implements Writable {
    // required fields
    protected Title title;
    protected final RollFormula hpFormula;
    protected final Armour armour;
    protected final Speeds speeds;
    protected final Senses senses;
    protected final int proficiency;
    protected final int xp;
    protected final AbilityScoreSet abilityScoreSet;
    protected final List<RollableAction> rollableActions;

    // optional fields
    protected final Languages languages;
    protected final List<Ability> abilities;
    protected final List<AbilityScore> savingThrowProficiencies;
    protected final List<Skill> skillProficiencies;
    protected final List<Condition> conditionImmunities;
    protected final HashMap<DamageType, ResistanceType> resistances;
    protected final LegendaryMechanics legendaryMechanics;

    // EFFECTS: constructs a StatBlock using a builder
    public StatBlock(StatBlockBuilder builder) {
        this.title = builder.title;
        this.xp = builder.xp;
        this.hpFormula = builder.hpFormula;
        this.proficiency = builder.proficiency;
        this.armour = builder.armour;
        this.speeds = builder.speeds;
        this.abilityScoreSet = builder.abilityScoreSet;
        this.abilities = builder.abilities;
        this.rollableActions = builder.rollableActions;
        this.savingThrowProficiencies = builder.savingThrowProficiencies;
        this.skillProficiencies = builder.skillProficiencies;
        this.conditionImmunities = builder.conditionImmunities;
        this.resistances = builder.resistances;
        this.languages = builder.languages;
        this.senses = builder.senses;
        this.legendaryMechanics = builder.legendaryMechanics;
    }

    @Override
    // EFFECTS: converts the statblock to a json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title.toJson());
        json.put("xp", xp);
        json.put("hpFormula", hpFormula.toJson());
        json.put("proficiency", proficiency);
        json.put("armour", armour.toJson());
        json.put("speeds", speeds.toJson());
        json.put("senses", senses.toJson());
        json.put("abilityScores", abilityScoreSet.toJson());
        json.put("rollableActions", actionsToJson());
        return optionalFieldsToJson(json);
    }

    // EFFECTS: constructs a json array with the abilities
    protected JSONArray abilitiesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Ability a : abilities) {
            jsonArray.put(a.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: constructs a json array with the actions
    protected JSONArray actionsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (RollableAction a : rollableActions) {
            jsonArray.put(a.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: adds optional fields that exist to the given json object and returns it
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
    // EFFECTS: returns a string representation of the character
    public String toString() {
        return this.title.getName() + ". CR: " + this.getChallengeRating();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters:
    // EFFECTS: calculates and returns the challenge ratings from this statblock's xp
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
    public AbilityScoreSet getAbilityScores() {
        return abilityScoreSet;
    }

    // EFFECTS: gets abilities
    public List<Ability> getAbilities() {
        return abilities;
    }

    // EFFECTS: gets actions
    public List<RollableAction> getRollableActions() {
        return rollableActions;
    }

    // EFFECTS: gets saving throw proficiencies
    public List<AbilityScore> getSavingThrowProficiencies() {
        return savingThrowProficiencies;
    }

    // EFFECTS: returns all saving throw proficiencies as a string, or "" if none
    public String getSavingThrowProficienciesString() {
        if (savingThrowProficiencies == null) {
            return "";
        }
        StringBuilder savingThrowProficienciesString = new StringBuilder();
        for (AbilityScore as : AbilityScore.values()) {
            if (savingThrowProficiencies.contains(as)) {
                savingThrowProficienciesString.append(as.toString().toLowerCase())
                        .append(" ")
                        .append(abilityScoreSet.toModifier(as) + proficiency)
                        .append(", ");
            }
        }
        return savingThrowProficienciesString.toString();
    }

    // EFFECTS: gets skill proficiencies
    public List<Skill> getSkillProficiencies() {
        return skillProficiencies;
    }

    // REQUIRES: skill is in skill proficiencies TODO throw IndexOutOfBoundsException
    // EFFECTS: returns all skill proficiencies as a string, or "" if none
    public String getSkillProficienciesString() {
        if (skillProficiencies == null) {
            return "";
        }
        StringBuilder skillProficienciesString = new StringBuilder();
        for (Skill s : Skill.values()) {
            if (skillProficiencies.contains(s)) {
                skillProficienciesString.append(s.toString().toLowerCase())
                        .append(" ")
                        .append(proficiency + abilityScoreSet.toModifier(s.getAbilityScore()))
                        .append(", ");
            }
        }
        return skillProficienciesString.toString();
    }

    // EFFECTS: gets condition immunities
    public List<Condition> getConditionImmunities() {
        return conditionImmunities;
    }

    // EFFECTS: returns all condition immunities as a string, or "" if none
    public String getConditionImmunitiesString() {
        if (conditionImmunities == null) {
            return "";
        }
        StringBuilder conditionImmunitiesString = new StringBuilder();
        for (Condition c : Condition.values()) {
            if (conditionImmunities.contains(c)) {
                conditionImmunitiesString.append(c).append(", ");
            }
        }
        return conditionImmunitiesString.toString().toLowerCase();
    }

    // EFFECTS: gets resistances
    public HashMap<DamageType, ResistanceType> getResistances() {
        return resistances;
    }

    // EFFECTS: returns all resistances as a string, or "" if none
    public String getResistancesString() {
        if (resistances == null) {
            return "";
        }
        StringBuilder resistancesString = new StringBuilder();

        for (DamageType dt : DamageType.values()) {
            if (resistances.containsKey(dt)) {
                resistancesString.append(dt.toString().toLowerCase())
                        .append(" ")
                        .append(resistances.get(dt).toString().toLowerCase())
                        .append(", ");
            }
        }
        return resistancesString.toString();
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
        protected final AbilityScoreSet abilityScoreSet;
        protected final List<RollableAction> rollableActions;

        // optional fields
        protected Languages languages;
        protected List<Ability> abilities;
        protected List<AbilityScore> savingThrowProficiencies;
        protected List<Skill> skillProficiencies;
        protected List<Condition> conditionImmunities;
        protected HashMap<DamageType, ResistanceType> resistances;
        protected LegendaryMechanics legendaryMechanics;

        // MODIFIES: this
        // EFFECTS: constructs a builder with required fields
        public StatBlockBuilder(Title title, int xp, RollFormula hpFormula, int proficiency, Armour armour,
                                Speeds speeds, Senses senses, AbilityScoreSet abilityScoreSet,
                                List<RollableAction> rollableActions) throws IndexOutOfBoundsException {
            if (xp < 0) {
                throw new IndexOutOfBoundsException("(statBlock) xp is negative");
            } else if (proficiency < 0) {
                throw new IndexOutOfBoundsException("(statBlock) proficiency bonus is negative");
            } else {
                this.title = title;
                this.hpFormula = hpFormula;
                this.armour = armour;
                this.speeds = speeds;
                this.senses = senses;
                this.proficiency = proficiency;
                this.xp = xp;
                this.abilityScoreSet = abilityScoreSet;
                this.rollableActions = rollableActions;
            }
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
        public StatBlockBuilder savingThrowProficiencies(List<AbilityScore> savingThrowProficiencies) {
            this.savingThrowProficiencies = savingThrowProficiencies;
            return this;
        }

        // EFFECTS: returns a builder that assigns the given skill proficiencies to the StatBlock
        public StatBlockBuilder skillProficiencies(List<Skill> skillProficiencies) {
            this.skillProficiencies = skillProficiencies;
            return this;
        }

        // EFFECTS: returns a builder that assigns the given condition immunities to the StatBlock
        public StatBlockBuilder conditionImmunities(List<Condition> conditionImmunities) {
            this.conditionImmunities = conditionImmunities;
            return this;
        }

        // EFFECTS: returns a builder that assigns the given resistances to the StatBlock
        public StatBlockBuilder resistances(HashMap<DamageType, ResistanceType> resistances) {
            this.resistances = resistances;
            return this;
        }

        // EFFECTS: returns a builder that assigns the given legendary mechanics to the StatBlock
        public StatBlockBuilder legendaryMechanics(LegendaryMechanics legendaryMechanics) {
            this.legendaryMechanics = legendaryMechanics;
            return this;
        }
    }
}