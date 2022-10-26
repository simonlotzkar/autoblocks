package model.statblockfields;

import model.StatBlock;

public class SkillProficiencies {
    // optional fields
    private final boolean acrobatics; //dex
    private final boolean animalHandling; //wis
    private final boolean arcana; //int
    private final boolean athletics; //wis
    private final boolean deception; //cha
    private final boolean history; //int
    private final boolean insight; //wis
    private final boolean intimidation; //cha
    private final boolean investigation; //int
    private final boolean medicine; //wis
    private final boolean nature; //int
    private final boolean perception; //wis
    private final boolean performance; //cha
    private final boolean persuasion; //cha
    private final boolean religion; //int
    private final boolean sleightOfHand; //dex
    private final boolean stealth; //wis
    private final boolean survival; //wis

    // EFFECTS: constructs SkillProficiencies using a builder
    public SkillProficiencies(SkillProficienciesBuilder builder) {
        this.acrobatics = builder.acrobatics;
        this.animalHandling = builder.animalHandling;
        this.arcana = builder.arcana;
        this.athletics = builder.athletics;
        this.deception = builder.deception;
        this.history = builder.history;
        this.insight = builder.insight;
        this.intimidation = builder.intimidation;
        this.investigation = builder.investigation;
        this.medicine = builder.medicine;
        this.nature = builder.nature;
        this.perception = builder.perception;
        this.performance = builder.performance;
        this.persuasion = builder.persuasion;
        this.religion = builder.religion;
        this.sleightOfHand = builder.sleightOfHand;
        this.stealth = builder.stealth;
        this.survival = builder.survival;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    // EFFECTS: gets a string of all skill proficiencies that exist for given StatBlock
    public String getSkillProficienciesString(StatBlock selected) {
        AbilityScores abilityScores = selected.getAbilityScores();
        int proficiency = selected.getProficiency();
        String savingThrowsString = "";

        savingThrowsString += getAcrobaticsSkillProficiencyString(abilityScores, proficiency);
        savingThrowsString += getAnimalHandlingSkillProficiencyString(abilityScores, proficiency);
        savingThrowsString += getArcanaSkillProficiencyString(abilityScores, proficiency);
        savingThrowsString += getAthleticsSkillProficiencyString(abilityScores, proficiency);
        savingThrowsString += getDeceptionSkillProficiencyString(abilityScores, proficiency);
        savingThrowsString += getHistorySkillProficiencyString(abilityScores, proficiency);
        savingThrowsString += getInsightSkillProficiencyString(abilityScores, proficiency);
        savingThrowsString += getIntimidationSkillProficiencyString(abilityScores, proficiency);
        savingThrowsString += getInvestigationSkillProficiencyString(abilityScores, proficiency);
        savingThrowsString += getMedicineSkillProficiencyString(abilityScores, proficiency);
        savingThrowsString += getNatureSkillProficiencyString(abilityScores, proficiency);
        savingThrowsString += getPerceptionSkillProficiencyString(abilityScores, proficiency);
        savingThrowsString += getPerformanceSkillProficiencyString(abilityScores, proficiency);
        savingThrowsString += getPersuasionSkillProficiencyString(abilityScores, proficiency);
        savingThrowsString += getReligionSkillProficiencyString(abilityScores, proficiency);
        savingThrowsString += getSleightOfHandSkillProficiencyString(abilityScores, proficiency);
        savingThrowsString += getStealthSkillProficiencyString(abilityScores, proficiency);
        savingThrowsString += getSurvivalSkillProficiencyString(abilityScores, proficiency);

        return savingThrowsString;
    }

    // EFFECTS: if there is an acrobatics skill proficiency, returns a statement including the bonus;
    //          or returns "" if there is none
    public String getAcrobaticsSkillProficiencyString(AbilityScores abilityScores, int proficiency) {
        if (acrobatics) {
            return ", acrobatics " + (abilityScores.getDexterityModifier() + proficiency);
        }
        return "";
    }

    // EFFECTS: if there is an animal handling skill proficiency, returns a statement including the bonus;
    //          or returns "" if there is none
    public String getAnimalHandlingSkillProficiencyString(AbilityScores abilityScores, int proficiency) {
        if (animalHandling) {
            return ", animal handling " + (abilityScores.getWisdomModifier() + proficiency);
        }
        return "";
    }

    // EFFECTS: if there is an arcana skill proficiency, returns a statement including the bonus;
    //          or returns "" if there is none
    public String getArcanaSkillProficiencyString(AbilityScores abilityScores, int proficiency) {
        if (arcana) {
            return ", arcana " + (abilityScores.getIntelligenceModifier() + proficiency);
        }
        return "";
    }

    // EFFECTS: if there is an athletics skill proficiency, returns a statement including the bonus;
    //          or returns "" if there is none
    public String getAthleticsSkillProficiencyString(AbilityScores abilityScores, int proficiency) {
        if (athletics) {
            return ", athletics " + (abilityScores.getStrengthModifier() + proficiency);
        }
        return "";
    }

    // EFFECTS: if there is a deception skill proficiency, returns a statement including the bonus;
    //          or returns "" if there is none
    public String getDeceptionSkillProficiencyString(AbilityScores abilityScores, int proficiency) {
        if (deception) {
            return ", deception " + (abilityScores.getCharismaModifier() + proficiency);
        }
        return "";
    }

    // EFFECTS: if there is a history skill proficiency, returns a statement including the bonus;
    //          or returns "" if there is none
    public String getHistorySkillProficiencyString(AbilityScores abilityScores, int proficiency) {
        if (history) {
            return ", history " + (abilityScores.getIntelligenceModifier() + proficiency);
        }
        return "";
    }

    // EFFECTS: if there is an insight skill proficiency, returns a statement including the bonus;
    //          or returns "" if there is none
    public String getInsightSkillProficiencyString(AbilityScores abilityScores, int proficiency) {
        if (insight) {
            return ", insight " + (abilityScores.getWisdomModifier() + proficiency);
        }
        return "";
    }

    // EFFECTS: if there is an intimidation skill proficiency, returns a statement including the bonus;
    //          or returns "" if there is none
    public String getIntimidationSkillProficiencyString(AbilityScores abilityScores, int proficiency) {
        if (intimidation) {
            return ", intimidation " + (abilityScores.getCharismaModifier() + proficiency);
        }
        return "";
    }

    // EFFECTS: if there is an investigation skill proficiency, returns a statement including the bonus;
    //          or returns "" if there is none
    public String getInvestigationSkillProficiencyString(AbilityScores abilityScores, int proficiency) {
        if (investigation) {
            return ", investigation " + (abilityScores.getIntelligenceModifier() + proficiency);
        }
        return "";
    }

    // EFFECTS: if there is a medicine skill proficiency, returns a statement including the bonus;
    //          or returns "" if there is none
    public String getMedicineSkillProficiencyString(AbilityScores abilityScores, int proficiency) {
        if (medicine) {
            return ", medicine " + (abilityScores.getWisdomModifier() + proficiency);
        }
        return "";
    }

    // EFFECTS: if there is a nature skill proficiency, returns a statement including the bonus;
    //          or returns "" if there is none
    public String getNatureSkillProficiencyString(AbilityScores abilityScores, int proficiency) {
        if (nature) {
            return ", nature " + (abilityScores.getIntelligenceModifier() + proficiency);
        }
        return "";
    }

    // EFFECTS: if there is a perception skill proficiency, returns a statement including the bonus;
    //          or returns "" if there is none
    public String getPerceptionSkillProficiencyString(AbilityScores abilityScores, int proficiency) {
        if (perception) {
            return ", perception " + (abilityScores.getWisdomModifier() + proficiency);
        }
        return "";
    }

    // EFFECTS: if there is a performance skill proficiency, returns a statement including the bonus;
    //          or returns "" if there is none
    public String getPerformanceSkillProficiencyString(AbilityScores abilityScores, int proficiency) {
        if (performance) {
            return ", performance " + (abilityScores.getCharismaModifier() + proficiency);
        }
        return "";
    }

    // EFFECTS: if there is a persuasion skill proficiency, returns a statement including the bonus;
    //          or returns "" if there is none
    public String getPersuasionSkillProficiencyString(AbilityScores abilityScores, int proficiency) {
        if (persuasion) {
            return ", persuasion " + (abilityScores.getCharismaModifier() + proficiency);
        }
        return "";
    }

    // EFFECTS: if there is a religion skill proficiency, returns a statement including the bonus;
    //          or returns "" if there is none
    public String getReligionSkillProficiencyString(AbilityScores abilityScores, int proficiency) {
        if (religion) {
            return ", religion " + (abilityScores.getIntelligenceModifier() + proficiency);
        }
        return "";
    }

    // EFFECTS: if there is a sleight of hand skill proficiency, returns a statement including the bonus;
    //          or returns "" if there is none
    public String getSleightOfHandSkillProficiencyString(AbilityScores abilityScores, int proficiency) {
        if (sleightOfHand) {
            return ", sleightOfHand " + (abilityScores.getDexterityModifier() + proficiency);
        }
        return "";
    }

    // EFFECTS: if there is a stealth skill proficiency, returns a statement including the bonus;
    //          or returns "" if there is none
    public String getStealthSkillProficiencyString(AbilityScores abilityScores, int proficiency) {
        if (stealth) {
            return ", stealth " + (abilityScores.getDexterityModifier() + proficiency);
        }
        return "";
    }

    // EFFECTS: if there is a survival skill proficiency, returns a statement including the bonus;
    //          or returns "" if there is none
    public String getSurvivalSkillProficiencyString(AbilityScores abilityScores, int proficiency) {
        if (survival) {
            return ", survival " + (abilityScores.getWisdomModifier() + proficiency);
        }
        return "";
    }

    // EFFECTS: gets whether an acrobatics proficiency exists
    public boolean isAcrobatics() {
        return acrobatics;
    }

    // EFFECTS: gets whether an animal handling proficiency exists
    public boolean isAnimalHandling() {
        return animalHandling;
    }

    // EFFECTS: gets whether an arcana proficiency exists
    public boolean isArcana() {
        return arcana;
    }

    // EFFECTS: gets whether an athletics proficiency exists
    public boolean isAthletics() {
        return athletics;
    }

    // EFFECTS: gets whether a deception proficiency exists
    public boolean isDeception() {
        return deception;
    }

    // EFFECTS: gets whether a history proficiency exists
    public boolean isHistory() {
        return history;
    }

    // EFFECTS: gets whether an insight proficiency exists
    public boolean isInsight() {
        return insight;
    }

    // EFFECTS: gets whether an intimidation proficiency exists
    public boolean isIntimidation() {
        return intimidation;
    }

    // EFFECTS: gets whether an investigation proficiency exists
    public boolean isInvestigation() {
        return investigation;
    }

    // EFFECTS: gets whether a medicine proficiency exists
    public boolean isMedicine() {
        return medicine;
    }

    // EFFECTS: gets whether a nature proficiency exists
    public boolean isNature() {
        return nature;
    }

    // EFFECTS: gets whether a perception proficiency exists
    public boolean isPerception() {
        return perception;
    }

    // EFFECTS: gets whether a performance proficiency exists
    public boolean isPerformance() {
        return performance;
    }

    // EFFECTS: gets whether a persuasion proficiency exists
    public boolean isPersuasion() {
        return persuasion;
    }

    // EFFECTS: gets whether a religion proficiency exists
    public boolean isReligion() {
        return religion;
    }

    // EFFECTS: gets whether a sleight of hand proficiency exists
    public boolean isSleightOfHand() {
        return sleightOfHand;
    }

    // EFFECTS: gets whether a stealth proficiency exists
    public boolean isStealth() {
        return stealth;
    }

    // EFFECTS: gets whether a survival proficiency exists
    public boolean isSurvival() {
        return survival;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // builder class
    public static class SkillProficienciesBuilder {
        // optional fields
        private boolean acrobatics; //dex
        private boolean animalHandling; //wis
        private boolean arcana; //int
        private boolean athletics; //wis
        private boolean deception; //cha
        private boolean history; //int
        private boolean insight; //wis
        private boolean intimidation; //cha
        private boolean investigation; //int
        private boolean medicine; //wis
        private boolean nature; //int
        private boolean perception; //wis
        private boolean performance; //cha
        private boolean persuasion; //cha
        private boolean religion; //int
        private boolean sleightOfHand; //dex
        private boolean stealth; //wis
        private boolean survival; //wis

        // EFFECTS: returns a new SkillProficiencies with any optional fields that had their builder called
        public SkillProficiencies build() {
            return new SkillProficiencies(this);
        }

        // EFFECTS: returns a builder that assigns given boolean to acrobatics for SkillProficiencies
        public SkillProficienciesBuilder acrobatics(boolean acrobatics) {
            this.acrobatics = acrobatics;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to animal handling for SkillProficiencies
        public SkillProficienciesBuilder animalHandling(boolean animalHandling) {
            this.animalHandling = animalHandling;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to arcana for SkillProficiencies
        public SkillProficienciesBuilder arcana(boolean arcana) {
            this.arcana = arcana;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to athletics for SkillProficiencies
        public SkillProficienciesBuilder athletics(boolean athletics) {
            this.athletics = athletics;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to deception for SkillProficiencies
        public SkillProficienciesBuilder deception(boolean deception) {
            this.deception = deception;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to history for SkillProficiencies
        public SkillProficienciesBuilder history(boolean history) {
            this.history = history;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to insight for SkillProficiencies
        public SkillProficienciesBuilder insight(boolean insight) {
            this.insight = insight;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to intimidation for SkillProficiencies
        public SkillProficienciesBuilder intimidation(boolean intimidation) {
            this.intimidation = intimidation;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to investigation for SkillProficiencies
        public SkillProficienciesBuilder investigation(boolean investigation) {
            this.investigation = investigation;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to medicine for SkillProficiencies
        public SkillProficienciesBuilder medicine(boolean medicine) {
            this.medicine = medicine;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to nature for SkillProficiencies
        public SkillProficienciesBuilder nature(boolean nature) {
            this.nature = nature;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to perception for SkillProficiencies
        public SkillProficienciesBuilder perception(boolean perception) {
            this.perception = perception;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to performance for SkillProficiencies
        public SkillProficienciesBuilder performance(boolean performance) {
            this.performance = performance;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to persuasion for SkillProficiencies
        public SkillProficienciesBuilder persuasion(boolean persuasion) {
            this.persuasion = persuasion;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to religion for SkillProficiencies
        public SkillProficienciesBuilder religion(boolean religion) {
            this.religion = religion;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to sleight of hand for SkillProficiencies
        public SkillProficienciesBuilder sleightOfHand(boolean sleightOfHand) {
            this.sleightOfHand = sleightOfHand;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to stealth for SkillProficiencies
        public SkillProficienciesBuilder stealth(boolean stealth) {
            this.stealth = stealth;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to survival for SkillProficiencies
        public SkillProficienciesBuilder survival(boolean survival) {
            this.survival = survival;
            return this;
        }
    }
}