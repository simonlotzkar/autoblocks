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

    // MODIFIES: this
    // EFFECTS: constructs a Senses using a builder
    public Senses(SensesBuilder builder) {
        this.passivePerception = builder.passivePerception;
        this.blindSight = builder.blindSight;
        this.darkVision = builder.darkVision;
        this.tremorSense = builder.tremorSense;
        this.trueSight = builder.trueSight;
    }

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

    @Override
    // EFFECTS: constructs a json object with the fields of the senses
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("passivePerception", passivePerception);
        json.put("blindSight", blindSight);
        json.put("darkVision", darkVision);
        json.put("tremorSense", tremorSense);
        json.put("trueSight", trueSight);
        return json;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
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

        // constructs a builder with required fields and throws exception if given sense is negative
        public SensesBuilder(int passivePerception) throws IndexOutOfBoundsException {
            if (passivePerception < 0) {
                throw new IndexOutOfBoundsException("(Senses) passive perception cannot be negative.");
            } else {
                this.passivePerception = passivePerception;
            }
        }

        // EFFECTS: returns a new Senses with required fields,
        //          and any optional fields that had their builder called.
        public Senses build() {
            return new Senses(this);
        }

        // EFFECTS: returns a builder that assigns given distance to blindsight for Senses
        //          and throws exception if given sense is negative
        public SensesBuilder blindSight(int blindSight) throws IndexOutOfBoundsException {
            if (blindSight < 0) {
                throw new IndexOutOfBoundsException("(Senses) blind sight cannot be negative.");
            } else {
                this.blindSight = blindSight;
                return this;
            }
        }

        // EFFECTS: returns a builder that assigns given distance to darkvision for Senses
        //          and throws exception if given sense is negative
        public SensesBuilder darkVision(int darkVision) throws IndexOutOfBoundsException {
            if (darkVision < 0) {
                throw new IndexOutOfBoundsException("(Senses) dark vision cannot be negative.");
            } else {
                this.darkVision = darkVision;
                return this;
            }
        }

        // EFFECTS: returns a builder that assigns given distance to tremorsense for Senses
        //          and throws exception if given sense is negative
        public SensesBuilder tremorSense(int tremorSense) throws IndexOutOfBoundsException {
            if (tremorSense < 0) {
                throw new IndexOutOfBoundsException("(Senses) tremor sense cannot be negative.");
            } else {
                this.tremorSense = tremorSense;
                return this;
            }
        }

        // EFFECTS: returns a builder that assigns given distance to truesight for Senses
        //          and throws exception if given sense is negative
        public SensesBuilder trueSight(int trueSight) throws IndexOutOfBoundsException {
            if (trueSight < 0) {
                throw new IndexOutOfBoundsException("(Senses) true sight cannot be negative.");
            } else {
                this.trueSight = trueSight;
                return this;
            }
        }
    }
}
