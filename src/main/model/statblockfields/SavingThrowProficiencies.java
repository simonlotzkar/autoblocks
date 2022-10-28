package model.statblockfields;

import model.StatBlock;

public class SavingThrowProficiencies {
    // optional fields
    private final boolean strengthProficiency;
    private final boolean dexterityProficiency;
    private final boolean constitutionProficiency;
    private final boolean intelligenceProficiency;
    private final boolean wisdomProficiency;
    private final boolean charismaProficiency;

    // EFFECTS: constructs a SavingThrowProficiencies using a builder
    public SavingThrowProficiencies(SavingThrowProficienciesBuilder builder) {
        this.strengthProficiency = builder.strengthProficiency;
        this.dexterityProficiency = builder.dexterityProficiency;
        this.constitutionProficiency = builder.constitutionProficiency;
        this.intelligenceProficiency = builder.intelligenceProficiency;
        this.wisdomProficiency = builder.wisdomProficiency;
        this.charismaProficiency = builder.charismaProficiency;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    // EFFECTS: gets a string of all saving throws that exist for given StatBlock
    public String getSavingThrowProficienciesString(StatBlock selected) {
        AbilityScores abilityScores = selected.getAbilityScores();
        int proficiency = selected.getProficiency();
        String savingThrowsString = "";

        savingThrowsString += getStrengthSavingThrowProficiencyString(abilityScores, proficiency);
        savingThrowsString += getDexteritySavingThrowProficiencyString(abilityScores, proficiency);
        savingThrowsString += getConstitutionSavingThrowProficiencyString(abilityScores, proficiency);
        savingThrowsString += getIntelligenceSavingThrowProficiencyString(abilityScores, proficiency);
        savingThrowsString += getWisdomSavingThrowProficiencyString(abilityScores, proficiency);
        savingThrowsString += getCharismaSavingThrowProficiencyString(abilityScores, proficiency);

        return savingThrowsString;
    }

    // EFFECTS: if there is a strength saving throw proficiency, returns a statement including the bonus;
    //          or returns "" if there is none
    public String getStrengthSavingThrowProficiencyString(AbilityScores abilityScores, int proficiency) {
        if (strengthProficiency) {
            return ", strength " + (abilityScores.getStrengthModifier() + proficiency);
        }
        return "";
    }

    // EFFECTS: if there is a dexterity saving throw proficiency, returns a statement including the bonus;
    //          or returns "" if there is none
    public String getDexteritySavingThrowProficiencyString(AbilityScores abilityScores, int proficiency) {
        if (dexterityProficiency) {
            return ", dexterity " + (abilityScores.getDexterityModifier() + proficiency);
        }
        return "";
    }

    // EFFECTS: if there is a constitution saving throw proficiency, returns a statement including the bonus;
    //          or returns "" if there is none
    public String getConstitutionSavingThrowProficiencyString(AbilityScores abilityScores, int proficiency) {
        if (constitutionProficiency) {
            return ", constitution " + (abilityScores.getConstitutionModifier() + proficiency);
        }
        return "";
    }

    // EFFECTS: if there is an intelligence saving throw proficiency, returns a statement including the bonus;
    //          or returns "" if there is none
    public String getIntelligenceSavingThrowProficiencyString(AbilityScores abilityScores, int proficiency) {
        if (intelligenceProficiency) {
            return ", intelligence " + (abilityScores.getIntelligenceModifier() + proficiency);
        }
        return "";
    }

    // EFFECTS: if there is a wisdom saving throw proficiency, returns a statement including the bonus;
    //          or returns "" if there is none
    public String getWisdomSavingThrowProficiencyString(AbilityScores abilityScores, int proficiency) {
        if (wisdomProficiency) {
            return ", wisdom " + (abilityScores.getWisdomModifier() + proficiency);
        }
        return "";
    }

    // EFFECTS: if there is a charisma saving throw proficiency, returns a statement including the bonus;
    //          or returns "" if there is none
    public String getCharismaSavingThrowProficiencyString(AbilityScores abilityScores, int proficiency) {
        if (charismaProficiency) {
            return ", charisma " + (abilityScores.getCharismaModifier() + proficiency);
        }
        return "";
    }

    // EFFECTS: gets whether a strength proficiency exists
    public boolean isStrengthProficiency() {
        return strengthProficiency;
    }

    // EFFECTS: gets whether a dexterity proficiency exists
    public boolean isDexterityProficiency() {
        return dexterityProficiency;
    }

    // EFFECTS: gets whether a constitution proficiency exists
    public boolean isConstitutionProficiency() {
        return constitutionProficiency;
    }

    // EFFECTS: gets whether a intelligence proficiency exists
    public boolean isIntelligenceProficiency() {
        return intelligenceProficiency;
    }

    // EFFECTS: gets whether a wisdom proficiency exists
    public boolean isWisdomProficiency() {
        return wisdomProficiency;
    }

    // EFFECTS: gets whether a charisma proficiency exists
    public boolean isCharismaProficiency() {
        return charismaProficiency;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // builder class
    public static class SavingThrowProficienciesBuilder {
        // optional fields
        private boolean strengthProficiency;
        private boolean dexterityProficiency;
        private boolean constitutionProficiency;
        private boolean intelligenceProficiency;
        private boolean wisdomProficiency;
        private boolean charismaProficiency;

        // EFFECTS: returns a new SavingThrowProficiencies with any optional fields that had their builder called
        public SavingThrowProficiencies build() {
            return new SavingThrowProficiencies(this);
        }

        // EFFECTS: returns a builder that assigns given boolean to
        //          strength proficiency for SavingThrowProficiencies
        public SavingThrowProficienciesBuilder strengthProficiency(boolean strengthProficiency) {
            this.strengthProficiency = strengthProficiency;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to
        //          dexterity proficiency for SavingThrowProficiencies
        public SavingThrowProficienciesBuilder dexterityProficiency(boolean dexterityProficiency) {
            this.dexterityProficiency = dexterityProficiency;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to
        //          constitution proficiency for SavingThrowProficiencies
        public SavingThrowProficienciesBuilder constitutionProficiency(boolean constitutionProficiency) {
            this.constitutionProficiency = constitutionProficiency;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to
        //          intelligence proficiency for SavingThrowProficiencies
        public SavingThrowProficienciesBuilder intelligenceProficiency(boolean intelligenceProficiency) {
            this.intelligenceProficiency = intelligenceProficiency;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to
        //          wisdom proficiency for SavingThrowProficiencies
        public SavingThrowProficienciesBuilder wisdomProficiency(boolean wisdomProficiency) {
            this.wisdomProficiency = wisdomProficiency;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to
        //          charisma proficiency for SavingThrowProficiencies
        public SavingThrowProficienciesBuilder charismaProficiency(boolean charismaProficiency) {
            this.charismaProficiency = charismaProficiency;
            return this;
        }
    }
}
