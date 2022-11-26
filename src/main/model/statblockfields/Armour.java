package model.statblockfields;

import org.json.JSONObject;
import persistence.Writable;

// Represents...
public class Armour implements Writable {
    // required fields
    private final int ac;

    // optional fields
    private final String armourName;
    private final int magicArmour;

    // EFFECTS: constructs an Armour using the given builder
    public Armour(ArmourBuilder builder) {
        this.ac = builder.ac;
        this.armourName = builder.armourName;
        this.magicArmour = builder.magicArmour;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    @Override
    // EFFECTS: gets string representation of Armour depending on what fields exist
    public String toString() {
        StringBuilder armourStringBuilder = new StringBuilder();
        armourStringBuilder.append(ac);
        if (armourName != null) {
            armourStringBuilder
                    .append(" (")
                    .append(armourName)
                    .append(")");
        }

        if (magicArmour != 0) {
            armourStringBuilder
                    .append(" or ")
                    .append(magicArmour)
                    .append(" (with Magic Armour)");
        }

        return armourStringBuilder.toString();
    }

    // EFFECTS: get ac
    public int getAC() {
        return ac;
    }

    // EFFECTS: get armour name
    public String getArmourName() {
        return armourName;
    }

    // EFFECTS: get magic armour
    public int getMagicArmour() {
        return magicArmour;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // builder class
    public static class ArmourBuilder {
        // required fields
        private final int ac;

        // optional fields
        private String armourName;
        private int magicArmour;

        // constructs a builder with required fields
        public ArmourBuilder(int ac) {
            this.ac = ac;
        }

        // EFFECTS: returns a new Armour with required fields,
        //          and any optional fields that had their builder called.
        public Armour build() {
            return new Armour(this);
        }

        // EFFECTS: returns a builder that assigns given armour name to Armour
        public ArmourBuilder armourName(String armourName) {
            this.armourName = armourName;
            return this;
        }

        // EFFECTS: returns a builder that assigns given magic armour to Armour
        public ArmourBuilder magicArmour(int magicArmour) {
            this.magicArmour = magicArmour;
            return this;
        }
    }

    // constructs a json object with the fields of the armour
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("ac", ac);
        json.put("armourName", armourName);
        json.put("magicArmour", magicArmour);
        return json;
    }
}
