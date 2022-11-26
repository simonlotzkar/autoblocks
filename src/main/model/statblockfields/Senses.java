package model.statblockfields;

import org.json.JSONObject;
import persistence.Writable;

// Represents a collection of senses where passive perception is required and the rest are not
public class Senses implements Writable {
    // required fields
    private final int passivePerception;

    // optional fields
    private final int blindSight;
    private final int darkVision;
    private final int tremorSense;
    private final int trueSight;

    // EFFECTS: constructs a Senses using a builder
    public Senses(SensesBuilder builder) {
        this.passivePerception = builder.passivePerception;
        this.blindSight = builder.blindSight;
        this.darkVision = builder.darkVision;
        this.tremorSense = builder.tremorSense;
        this.trueSight = builder.trueSight;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    @Override
    // EFFECTS: gets all senses as a string, excluding those that are 0
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Passive perception ").append(passivePerception);
        if (blindSight != 0) {
            stringBuilder.append(", blindsight ").append(blindSight).append("ft");
        }

        if (darkVision != 0) {
            stringBuilder.append(", darkvision ").append(darkVision).append("ft");
        }

        if (tremorSense != 0) {
            stringBuilder.append(", tremorSense ").append(tremorSense).append("ft");
        }

        if (trueSight != 0) {
            stringBuilder.append(", truesight ").append(trueSight).append("ft");
        }
        return stringBuilder.toString();
    }

    // EFFECTS: gets passive perception score
    public int getPassivePerception() {
        return passivePerception;
    }

    // EFFECTS: gets blindsight range
    public int getBlindSight() {
        return blindSight;
    }

    // EFFECTS: gets darkvision range
    public int getDarkVision() {
        return darkVision;
    }

    // EFFECTS: gets tremorsense range
    public int getTremorSense() {
        return tremorSense;
    }

    // EFFECTS: gets truesight range
    public int getTrueSight() {
        return trueSight;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // builder class
    public static class SensesBuilder {
        // required fields
        private final int passivePerception;

        // optional fields
        private int blindSight;
        private int darkVision;
        private int tremorSense;
        private int trueSight;

        // constructs a builder with required fields
        public SensesBuilder(int passivePerception) {
            this.passivePerception = passivePerception;
        }

        // EFFECTS: returns a new Senses with required fields,
        //          and any optional fields that had their builder called.
        public Senses build() {
            return new Senses(this);
        }

        // EFFECTS: returns a builder that assigns given distance to blindsight for Senses
        public SensesBuilder blindSight(int blindSight) {
            this.blindSight = blindSight;
            return this;
        }

        // EFFECTS: returns a builder that assigns given distance to darkvision for Senses
        public SensesBuilder darkVision(int darkVision) {
            this.darkVision = darkVision;
            return this;
        }

        // EFFECTS: returns a builder that assigns given distance to tremorsense for Senses
        public SensesBuilder tremorSense(int tremorSense) {
            this.tremorSense = tremorSense;
            return this;
        }

        // EFFECTS: returns a builder that assigns given distance to truesight for Senses
        public SensesBuilder trueSight(int trueSight) {
            this.trueSight = trueSight;
            return this;
        }
    }

    // constructs a json object with the fields of the senses
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("passivePerception", passivePerception);
        json.put("blindSight", blindSight);
        json.put("darkVision", darkVision);
        json.put("tremorSense", tremorSense);
        json.put("trueSight", trueSight);
        return json;
    }
}
