package model.statblockfields;

import org.json.JSONObject;
import persistence.Writable;

public class Resistances implements Writable {
    // optional fields
    private final String acid;
    private final String bludgeoning;
    private final String cold;
    private final String fire;
    private final String force;
    private final String lightning;
    private final String necrotic;
    private final String piercing;
    private final String poison;
    private final String psychic;
    private final String radiant;
    private final String slashing;
    private final String thunder;
    private final String nonMagical;
    private final String nonSilver;
    private final String nonAdamantine;

    // EFFECTS: constructs a Resistances using a builder
    public Resistances(ResistancesBuilder builder) {
        this.acid = builder.acid;
        this.bludgeoning = builder.bludgeoning;
        this.cold = builder.cold;
        this.fire = builder.fire;
        this.force = builder.force;
        this.lightning = builder.lightning;
        this.necrotic = builder.necrotic;
        this.piercing = builder.piercing;
        this.poison = builder.poison;
        this.psychic = builder.psychic;
        this.radiant = builder.radiant;
        this.slashing = builder.slashing;
        this.thunder = builder.thunder;
        this.nonMagical = builder.nonMagical;
        this.nonSilver = builder.nonSilver;
        this.nonAdamantine = builder.nonAdamantine;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    // EFFECTS: gets a string of all resistances that exist for given StatBlock
    public String getResistancesString() {
        String conditionImmunitiesString = "";

        conditionImmunitiesString += getAcidResistancesString();
        conditionImmunitiesString += getBludgeoningResistancesString();
        conditionImmunitiesString += getColdResistancesString();
        conditionImmunitiesString += getFireResistancesString();
        conditionImmunitiesString += getForceResistancesString();
        conditionImmunitiesString += getLightningResistancesString();
        conditionImmunitiesString += getNecroticResistancesString();
        conditionImmunitiesString += getPiercingResistancesString();
        conditionImmunitiesString += getPoisonResistancesString();
        conditionImmunitiesString += getPsychicResistancesString();
        conditionImmunitiesString += getRadiantResistancesString();
        conditionImmunitiesString += getSlashingResistancesString();
        conditionImmunitiesString += getThunderResistancesString();
        conditionImmunitiesString += getNonMagicalResistancesString();
        conditionImmunitiesString += getNonSilverResistancesString();
        conditionImmunitiesString += getNonAdamantineResistancesString();

        return conditionImmunitiesString;
    }

    // EFFECTS: if there is an acid resistance, returns a statement; or returns "" if there is none
    public String getAcidResistancesString() {
        if (acid != null) {
            return ", acid " + acid;
        }
        return "";
    }

    // EFFECTS: if there is a bludgeoning resistance, returns a statement; or returns "" if there is none
    public String getBludgeoningResistancesString() {
        if (bludgeoning != null) {
            return ", bludgeoning " + bludgeoning;
        }
        return "";
    }

    // EFFECTS: if there is a cold resistance, returns a statement; or returns "" if there is none
    public String getColdResistancesString() {
        if (cold != null) {
            return ", cold " + cold;
        }
        return "";
    }

    // EFFECTS: if there is a fire resistance, returns a statement; or returns "" if there is none
    public String getFireResistancesString() {
        if (fire != null) {
            return ", fire " + fire;
        }
        return "";
    }

    // EFFECTS: if there is a force resistance, returns a statement; or returns "" if there is none
    public String getForceResistancesString() {
        if (force != null) {
            return ", force " + force;
        }
        return "";
    }

    // EFFECTS: if there is a lightning resistance, returns a statement; or returns "" if there is none
    public String getLightningResistancesString() {
        if (lightning != null) {
            return ", lightning " + lightning;
        }
        return "";
    }

    // EFFECTS: if there is a necrotic resistance, returns a statement; or returns "" if there is none
    public String getNecroticResistancesString() {
        if (necrotic != null) {
            return ", necrotic " + necrotic;
        }
        return "";
    }

    // EFFECTS: if there is a piercing resistance, returns a statement; or returns "" if there is none
    public String getPiercingResistancesString() {
        if (piercing != null) {
            return ", piercing " + piercing;
        }
        return "";
    }

    // EFFECTS: if there is a poison resistance, returns a statement; or returns "" if there is none
    public String getPoisonResistancesString() {
        if (poison != null) {
            return ", poison " + poison;
        }
        return "";
    }

    // EFFECTS: if there is a psychic resistance, returns a statement; or returns "" if there is none
    public String getPsychicResistancesString() {
        if (psychic != null) {
            return ", psychic " + psychic;
        }
        return "";
    }

    // EFFECTS: if there is a radiant resistance, returns a statement; or returns "" if there is none
    public String getRadiantResistancesString() {
        if (radiant != null) {
            return ", radiant " + radiant;
        }
        return "";
    }

    // EFFECTS: if there is a slashing resistance, returns a statement; or returns "" if there is none
    public String getSlashingResistancesString() {
        if (slashing != null) {
            return ", slashing " + slashing;
        }
        return "";
    }

    // EFFECTS: if there is a thunder resistance, returns a statement; or returns "" if there is none
    public String getThunderResistancesString() {
        if (thunder != null) {
            return ", thunder " + thunder;
        }
        return "";
    }

    // EFFECTS: if there is a non-magical resistance, returns a statement; or returns "" if there is none
    public String getNonMagicalResistancesString() {
        if (nonMagical != null) {
            return ", non-magical " + nonMagical;
        }
        return "";
    }

    // EFFECTS: if there is a non-silver resistance, returns a statement; or returns "" if there is none
    public String getNonSilverResistancesString() {
        if (nonSilver != null) {
            return ", non-silver " + nonSilver;
        }
        return "";
    }

    // EFFECTS: if there is a non-adamantine resistance, returns a statement; or returns "" if there is none
    public String getNonAdamantineResistancesString() {
        if (nonAdamantine != null) {
            return ", non-adamantine " + nonAdamantine;
        }
        return "";
    }

    // EFFECTS: gets the status of acid for resistances
    public String getAcid() {
        return acid;
    }

    // EFFECTS: gets the status of bludgeoning for resistances
    public String getBludgeoning() {
        return bludgeoning;
    }

    // EFFECTS: gets the status of cold for resistances
    public String getCold() {
        return cold;
    }

    // EFFECTS: gets the status of fire for resistances
    public String getFire() {
        return fire;
    }

    // EFFECTS: gets the status of force for resistances
    public String getForce() {
        return force;
    }

    // EFFECTS: gets the status of lightning for resistances
    public String getLightning() {
        return lightning;
    }

    // EFFECTS: gets the status of necrotic for resistances
    public String getNecrotic() {
        return necrotic;
    }

    // EFFECTS: gets the status of piercing for resistances
    public String getPiercing() {
        return piercing;
    }

    // EFFECTS: gets the status of poison for resistances
    public String getPoison() {
        return poison;
    }

    // EFFECTS: gets the status of psychic for resistances
    public String getPsychic() {
        return psychic;
    }

    // EFFECTS: gets the status of radiant for resistances
    public String getRadiant() {
        return radiant;
    }

    // EFFECTS: gets the status of slashing for resistances
    public String getSlashing() {
        return slashing;
    }

    // EFFECTS: gets the status of thunder for resistances
    public String getThunder() {
        return thunder;
    }

    // EFFECTS: gets the status of non magical for resistances
    public String getNonMagical() {
        return nonMagical;
    }

    // EFFECTS: gets the status of non silver for resistances
    public String getNonSilver() {
        return nonSilver;
    }

    // EFFECTS: gets the status of non adamantine for resistances
    public String getNonAdamantine() {
        return nonAdamantine;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // builder class
    public static class ResistancesBuilder {
        // optional fields
        private String acid;
        private String bludgeoning;
        private String cold;
        private String fire;
        private String force;
        private String lightning;
        private String necrotic;
        private String piercing;
        private String poison;
        private String psychic;
        private String radiant;
        private String slashing;
        private String thunder;
        private String nonMagical;
        private String nonSilver;
        private String nonAdamantine;

        // EFFECTS: returns a new Resistances with any optional fields that had their builder called
        public Resistances build() {
            return new Resistances(this);
        }

        // EFFECTS: returns a builder that assigns given resistance to acid for Resistances
        public ResistancesBuilder acid(String acid) {
            this.acid = acid;
            return this;
        }

        // EFFECTS: returns a builder that assigns given resistance to bludgeoning for Resistances
        public ResistancesBuilder bludgeoning(String bludgeoning) {
            this.bludgeoning = bludgeoning;
            return this;
        }

        // EFFECTS: returns a builder that assigns given resistance to cold for Resistances
        public ResistancesBuilder cold(String cold) {
            this.cold = cold;
            return this;
        }

        // EFFECTS: returns a builder that assigns given resistance to fire for Resistances
        public ResistancesBuilder fire(String fire) {
            this.fire = fire;
            return this;
        }

        // EFFECTS: returns a builder that assigns given resistance to force for Resistances
        public ResistancesBuilder force(String force) {
            this.force = force;
            return this;
        }

        // EFFECTS: returns a builder that assigns given resistance to lightning for Resistances
        public ResistancesBuilder lightning(String lightning) {
            this.lightning = lightning;
            return this;
        }

        // EFFECTS: returns a builder that assigns given resistance to necrotic for Resistances
        public ResistancesBuilder necrotic(String necrotic) {
            this.necrotic = necrotic;
            return this;
        }

        // EFFECTS: returns a builder that assigns given resistance to piercing for Resistances
        public ResistancesBuilder piercing(String piercing) {
            this.piercing = piercing;
            return this;
        }

        // EFFECTS: returns a builder that assigns given resistance to poison for Resistances
        public ResistancesBuilder poison(String poison) {
            this.poison = poison;
            return this;
        }

        // EFFECTS: returns a builder that assigns given resistance to psychic for Resistances
        public ResistancesBuilder psychic(String psychic) {
            this.psychic = psychic;
            return this;
        }

        // EFFECTS: returns a builder that assigns given resistance to radiant for Resistances
        public ResistancesBuilder radiant(String radiant) {
            this.radiant = radiant;
            return this;
        }

        // EFFECTS: returns a builder that assigns given resistance to slashing for Resistances
        public ResistancesBuilder slashing(String slashing) {
            this.slashing = slashing;
            return this;
        }

        // EFFECTS: returns a builder that assigns given resistance to thunder for Resistances
        public ResistancesBuilder thunder(String thunder) {
            this.thunder = thunder;
            return this;
        }

        // EFFECTS: returns a builder that assigns given resistance to nonMagical for Resistances
        public ResistancesBuilder nonMagical(String nonMagical) {
            this.nonMagical = nonMagical;
            return this;
        }

        // EFFECTS: returns a builder that assigns given resistance to nonSilver for Resistances
        public ResistancesBuilder nonSilver(String nonSilver) {
            this.nonSilver = nonSilver;
            return this;
        }

        // EFFECTS: returns a builder that assigns given resistance to nonAdamantine for Resistances
        public ResistancesBuilder nonAdamantine(String nonAdamantine) {
            this.nonAdamantine = nonAdamantine;
            return this;
        }
    }

    // constructs a json object with the fields of the resistances
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("acid", acid);
        json.put("bludgeoning", bludgeoning);
        json.put("cold", cold);
        json.put("fire", fire);
        json.put("force", force);
        json.put("lightning", lightning);
        json.put("necrotic", necrotic);
        json.put("piercing", piercing);
        json.put("poison", poison);
        json.put("psychic", psychic);
        json.put("radiant", radiant);
        json.put("slashing", slashing);
        json.put("thunder", thunder);
        json.put("nonMagical", nonMagical);
        json.put("nonSilver", nonSilver);
        json.put("nonAdamantine", nonAdamantine);
        return json;
    }
}
