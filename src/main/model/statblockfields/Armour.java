package model.statblockfields;

public class Armour {
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
    // EFFECTS: gets string representation of Armour depending on what fields exist
    public String getArmourString() {
        String armourString = String.valueOf(ac);
        if (armourName != null) {
            armourString += " (" + armourName + ")";
        }

        if (magicArmour != 0) {
            return armourString += " or " + magicArmour + " (with Magic Armour)";
        }

        return armourString;
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
}
