package model.statblockfields;

import org.json.JSONObject;
import persistence.Writable;

public class ConditionImmunities implements Writable {
    // optional fields
    private final boolean blinded;
    private final boolean charmed;
    private final boolean deafened;
    private final boolean frightened;
    private final boolean grappled;
    private final boolean incapacitated;
    private final boolean invisible;
    private final boolean paralyzed;
    private final boolean petrified;
    private final boolean poisoned;
    private final boolean prone;
    private final boolean restrained;
    private final boolean stunned;
    private final boolean unconscious;

    // EFFECTS: constructs condition immunities using a builder
    public ConditionImmunities(ConditionImmunitiesBuilder builder) {
        this.blinded = builder.blinded;
        this.charmed = builder.charmed;
        this.deafened = builder.deafened;
        this.frightened = builder.frightened;
        this.grappled = builder.grappled;
        this.incapacitated = builder.incapacitated;
        this.invisible = builder.invisible;
        this.paralyzed = builder.paralyzed;
        this.petrified = builder.petrified;
        this.poisoned = builder.poisoned;
        this.prone = builder.prone;
        this.restrained = builder.restrained;
        this.stunned = builder.stunned;
        this.unconscious = builder.unconscious;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    // EFFECTS: gets a string of all condition immunities that exist for given StatBlock
    public String getConditionImmunitiesString() {
        String conditionImmunitiesString = "";

        conditionImmunitiesString += getBlindedString();
        conditionImmunitiesString += getCharmedString();
        conditionImmunitiesString += getDeafenedString();
        conditionImmunitiesString += getFrightenedString();
        conditionImmunitiesString += getGrappledString();
        conditionImmunitiesString += getIncapacitatedString();
        conditionImmunitiesString += getInvisibleString();
        conditionImmunitiesString += getParalyzedString();
        conditionImmunitiesString += getPetrifiedString();
        conditionImmunitiesString += getPoisonedString();
        conditionImmunitiesString += getProneString();
        conditionImmunitiesString += getRestrainedString();
        conditionImmunitiesString += getStunnedString();
        conditionImmunitiesString += getUnconsciousString();

        return conditionImmunitiesString;
    }

    // EFFECTS: if there is a blinded immunity, returns a statement; or returns "" if there is none
    public String getBlindedString() {
        if (blinded) {
            return ", blinded ";
        }
        return "";
    }

    // EFFECTS: if there is a charmed immunity, returns a statement; or returns "" if there is none
    public String getCharmedString() {
        if (charmed) {
            return ", charmed ";
        }
        return "";
    }

    // EFFECTS: if there is a deafened immunity, returns a statement; or returns "" if there is none
    public String getDeafenedString() {
        if (deafened) {
            return ", deafened ";
        }
        return "";
    }

    // EFFECTS: if there is a frightened immunity, returns a statement; or returns "" if there is none
    public String getFrightenedString() {
        if (frightened) {
            return ", frightened ";
        }
        return "";
    }

    // EFFECTS: if there is a grappled immunity, returns a statement; or returns "" if there is none
    public String getGrappledString() {
        if (grappled) {
            return ", grappled ";
        }
        return "";
    }

    // EFFECTS: if there is a incapacitated immunity, returns a statement; or returns "" if there is none
    public String getIncapacitatedString() {
        if (incapacitated) {
            return ", incapacitated ";
        }
        return "";
    }

    // EFFECTS: if there is a invisible immunity, returns a statement; or returns "" if there is none
    public String getInvisibleString() {
        if (invisible) {
            return ", invisible ";
        }
        return "";
    }

    // EFFECTS: if there is a paralyzed immunity, returns a statement; or returns "" if there is none
    public String getParalyzedString() {
        if (paralyzed) {
            return ", paralyzed ";
        }
        return "";
    }

    // EFFECTS: if there is a petrified immunity, returns a statement; or returns "" if there is none
    public String getPetrifiedString() {
        if (petrified) {
            return ", petrified ";
        }
        return "";
    }

    // EFFECTS: if there is a poisoned immunity, returns a statement; or returns "" if there is none
    public String getPoisonedString() {
        if (poisoned) {
            return ", poisoned ";
        }
        return "";
    }

    // EFFECTS: if there is a prone immunity, returns a statement; or returns "" if there is none
    public String getProneString() {
        if (prone) {
            return ", prone ";
        }
        return "";
    }

    // EFFECTS: if there is a restrained immunity, returns a statement; or returns "" if there is none
    public String getRestrainedString() {
        if (restrained) {
            return ", restrained ";
        }
        return "";
    }

    // EFFECTS: if there is a stunned immunity, returns a statement; or returns "" if there is none
    public String getStunnedString() {
        if (stunned) {
            return ", stunned ";
        }
        return "";
    }

    // EFFECTS: if there is a unconscious immunity, returns a statement; or returns "" if there is none
    public String getUnconsciousString() {
        if (unconscious) {
            return ", unconscious ";
        }
        return "";
    }

    // EFFECTS: gets whether a blinded immunity exists
    public boolean isBlinded() {
        return blinded;
    }

    // EFFECTS: gets whether a charmed immunity exists
    public boolean isCharmed() {
        return charmed;
    }

    // EFFECTS: gets whether a deafened immunity exists
    public boolean isDeafened() {
        return deafened;
    }

    // EFFECTS: gets whether a frightened immunity exists
    public boolean isFrightened() {
        return frightened;
    }

    // EFFECTS: gets whether a grappled immunity exists
    public boolean isGrappled() {
        return grappled;
    }

    // EFFECTS: gets whether an incapacitated immunity exists
    public boolean isIncapacitated() {
        return incapacitated;
    }

    // EFFECTS: gets whether an invisible immunity exists
    public boolean isInvisible() {
        return invisible;
    }

    // EFFECTS: gets whether a paralyzed immunity exists
    public boolean isParalyzed() {
        return paralyzed;
    }

    // EFFECTS: gets whether a petrified immunity exists
    public boolean isPetrified() {
        return petrified;
    }

    // EFFECTS: gets whether a poisoned immunity exists
    public boolean isPoisoned() {
        return poisoned;
    }

    // EFFECTS: gets whether a prone immunity exists
    public boolean isProne() {
        return prone;
    }

    // EFFECTS: gets whether a restrained immunity exists
    public boolean isRestrained() {
        return restrained;
    }

    // EFFECTS: gets whether a stunned immunity exists
    public boolean isStunned() {
        return stunned;
    }

    // EFFECTS: gets whether an unconscious immunity exists
    public boolean isUnconscious() {
        return unconscious;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // builder class
    public static class ConditionImmunitiesBuilder {
        // optional fields
        private boolean blinded;
        private boolean charmed;
        private boolean deafened;
        private boolean frightened;
        private boolean grappled;
        private boolean incapacitated;
        private boolean invisible;
        private boolean paralyzed;
        private boolean petrified;
        private boolean poisoned;
        private boolean prone;
        private boolean restrained;
        private boolean stunned;
        private boolean unconscious;

        // EFFECTS: returns a new ConditionImmunities with required fields,
        //          and any optional fields that had their builder called.
        public ConditionImmunities build() {
            return new ConditionImmunities(this);
        }

        // EFFECTS: returns a builder that assigns given boolean to blinded for ConditionImmunities
        public ConditionImmunitiesBuilder blinded(boolean blinded) {
            this.blinded = blinded;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to charmed for ConditionImmunities
        public ConditionImmunitiesBuilder charmed(boolean charmed) {
            this.charmed = charmed;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to deafened for ConditionImmunities
        public ConditionImmunitiesBuilder deafened(boolean deafened) {
            this.deafened = deafened;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to frightened for ConditionImmunities
        public ConditionImmunitiesBuilder frightened(boolean frightened) {
            this.frightened = frightened;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to grappled for ConditionImmunities
        public ConditionImmunitiesBuilder grappled(boolean grappled) {
            this.grappled = grappled;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to incapacitated for ConditionImmunities
        public ConditionImmunitiesBuilder incapacitated(boolean incapacitated) {
            this.incapacitated = incapacitated;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to invisible for ConditionImmunities
        public ConditionImmunitiesBuilder invisible(boolean invisible) {
            this.invisible = invisible;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to paralyzed for ConditionImmunities
        public ConditionImmunitiesBuilder paralyzed(boolean paralyzed) {
            this.paralyzed = paralyzed;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to petrified for ConditionImmunities
        public ConditionImmunitiesBuilder petrified(boolean petrified) {
            this.petrified = petrified;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to poisoned for ConditionImmunities
        public ConditionImmunitiesBuilder poisoned(boolean poisoned) {
            this.poisoned = poisoned;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to prone for ConditionImmunities
        public ConditionImmunitiesBuilder prone(boolean prone) {
            this.prone = prone;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to restrained for ConditionImmunities
        public ConditionImmunitiesBuilder restrained(boolean restrained) {
            this.restrained = restrained;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to stunned for ConditionImmunities
        public ConditionImmunitiesBuilder stunned(boolean stunned) {
            this.stunned = stunned;
            return this;
        }

        // EFFECTS: returns a builder that assigns given boolean to unconscious for ConditionImmunities
        public ConditionImmunitiesBuilder unconscious(boolean unconscious) {
            this.unconscious = unconscious;
            return this;
        }
    }

    // constructs a json object with the fields of the condition immunities
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("blinded", blinded);
        json.put("charmed", charmed);
        json.put("deafened", deafened);
        json.put("frightened", frightened);
        json.put("grappled", grappled);
        json.put("incapacitated", incapacitated);
        json.put("invisible", invisible);
        json.put("paralyzed", paralyzed);
        json.put("petrified", petrified);
        json.put("poisoned", poisoned);
        json.put("prone", prone);
        json.put("restrained", restrained);
        json.put("stunned", stunned);
        json.put("unconscious", unconscious);
        return json;
    }
}
