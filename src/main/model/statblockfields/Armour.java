package model.statblockfields;

import exceptions.IncompleteFieldException;
import org.json.JSONObject;
import persistence.Writable;

// Represents an armour class and optionally an armour's name and/or magic armour armour class where armour classes are
// positive integers
public class Armour implements Writable {
    // required fields
    private final int ac;

    // optional fields
    private final String armourName;
    private final int magicArmour;

    // MODIFIES: this
    // EFFECTS: constructs an Armour using the given builder
    public Armour(ArmourBuilder builder) {
        this.ac = builder.ac;
        this.armourName = builder.armourName;
        this.magicArmour = builder.magicArmour;
    }

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

    @Override
    // EFFECTS: constructs a json object with the fields of the armour
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("ac", ac);
        json.put("armourName", armourName);
        json.put("magicArmour", magicArmour);
        return json;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
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

        // MODIFIES: this
        // EFFECTS: constructs a builder with required fields, throws an exception if the given ac is negative
        public ArmourBuilder(int ac) throws IndexOutOfBoundsException {
            if (ac < 0) {
                throw new IndexOutOfBoundsException("(Armour Class) cannot be negative.");
            } else {
                this.ac = ac;
            }
        }

        // EFFECTS: returns a new Armour with required fields, and any optional fields that had their builder called.
        public Armour build() {
            return new Armour(this);
        }

        // EFFECTS: returns a builder that assigns given armour name to Armour
        public ArmourBuilder armourName(String armourName) throws IncompleteFieldException {
            if (armourName != null && armourName.isEmpty()) {
                throw new IncompleteFieldException("(Armour Class) name must contain at least one character.");
            } else {
                this.armourName = armourName;
                return this;
            }
        }

        // EFFECTS: returns a builder that assigns given magic armour to Armour or throws an exception if the given
        //          magic armour is negative
        public ArmourBuilder magicArmour(int magicArmour) throws IndexOutOfBoundsException {
            if (magicArmour < 0) {
                throw new IndexOutOfBoundsException("Magic (armour class) cannot be negative.");
            } else {
                this.magicArmour = magicArmour;
                return this;
            }
        }
    }
}
