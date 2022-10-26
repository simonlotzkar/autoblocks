package model;

import model.statblockfields.*;

import java.util.*;

public class StatBlock {
    // required fields
    protected final Title title;

    protected final RollFormula hpFormula;
    protected final Armour armour;
    protected final Speeds speeds;
    protected final Senses senses;
    protected final int proficiency;
    protected final int xp;

    protected final AbilityScores abilityScores;
    protected final List<Ability> abilities;
    protected final List<Action> actions;

    // optional fields
    protected final SavingThrowProficiencies savingThrowProficiencies;
    protected final SkillProficiencies skillProficiencies;
    protected final ConditionImmunities conditionImmunities;
    protected final Resistances resistances;
    protected final Languages languages;
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
    // EFFECTS: returns hp formula as a string
    public String getHPString() {
        return "(" + hpFormula.getAmountOfDice() + "d"
                + hpFormula.getDieSides() + " + "
                + hpFormula.getModifier() + ")";
    }

    // EFFECTS: calculates and returns the challenge ratings from xp (0-2,899)
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
    public String getChallengeRatingPartTwo() {
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
    public String getChallengeRatingPartThree() {
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
    public String getChallengeRatingPartFour() {
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

    // EFFECTS: gets name
    public Title getTitle() {
        return title;
    }

    // EFFECTS: gets xp
    public int getXp() {
        return xp;
    }

    // EFFECTS: gets hp formula
    public RollFormula getHpFormula() {
        return hpFormula;
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
    public SavingThrowProficiencies getSavingThrowProficiencies() {
        return savingThrowProficiencies;
    }

    // EFFECTS: gets skill proficiencies
    public SkillProficiencies getSkillProficiencies() {
        return skillProficiencies;
    }

    // EFFECTS: gets condition immunities
    public ConditionImmunities getConditionImmunities() {
        return conditionImmunities;
    }

    // EFFECTS: gets resistances
    public Resistances getResistances() {
        return resistances;
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
        protected final List<Ability> abilities;
        protected final List<Action> actions;

        // optional fields
        protected SavingThrowProficiencies savingThrowProficiencies;
        protected SkillProficiencies skillProficiencies;
        protected ConditionImmunities conditionImmunities;
        protected Resistances resistances;
        protected Languages languages;
        protected LegendaryMechanics legendaryMechanics;

        // EFFECTS: constructs a builder with required fields
        public StatBlockBuilder(Title title, int xp, RollFormula hpFormula, int proficiency, Armour armour,
                                Speeds speeds, Senses senses, AbilityScores abilityScores, List<Ability> abilities,
                                List<Action> actions, Languages languages) {
            this.title = title;
            this.hpFormula = hpFormula;
            this.armour = armour;
            this.speeds = speeds;
            this.senses = senses;
            this.proficiency = proficiency;
            this.xp = xp;
            this.abilityScores = abilityScores;
            this.abilities = abilities;
            this.actions = actions;
            this.languages = languages;
        }

        // EFFECTS: returns a new StatBlock with required fields,
        //          and any optional fields that had their builder called.
        public StatBlock build() {
            return new StatBlock(this);
        }

        // EFFECTS: returns a builder that assigns saving throw proficiencies to the StatBlock
        public StatBlockBuilder savingThrowProficiencies(SavingThrowProficiencies savingThrowProficiencies) {
            this.savingThrowProficiencies = savingThrowProficiencies;
            return this;
        }

        // EFFECTS: returns a builder that assigns skill proficiencies to the StatBlock
        public StatBlockBuilder skillProficiencies(SkillProficiencies skillProficiencies) {
            this.skillProficiencies = skillProficiencies;
            return this;
        }

        // EFFECTS: returns a builder that assigns condition immunities to the StatBlock
        public StatBlockBuilder conditionImmunities(ConditionImmunities conditionImmunities) {
            this.conditionImmunities = conditionImmunities;
            return this;
        }

        // EFFECTS: returns a builder that assigns resistances to the StatBlock
        public StatBlockBuilder resistances(Resistances resistances) {
            this.resistances = resistances;
            return this;
        }

        // EFFECTS: returns a builder that assigns saving legendary mechanics to the StatBlock
        public StatBlockBuilder legendaryMechanics(LegendaryMechanics legendaryMechanics) {
            this.legendaryMechanics = legendaryMechanics;
            return this;
        }
    }
}