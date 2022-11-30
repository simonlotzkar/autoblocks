package enums;

import java.util.ArrayList;
import java.util.Arrays;

public enum DamageType {
    ACID,
    BLUDGEONING,
    COLD,
    FIRE,
    FORCE,
    LIGHTNING,
    NECROTIC,
    PIERCING,
    POISON,
    PSYCHIC,
    RADIANT,
    SLASHING,
    THUNDER;

    // EFFECTS: returns a string array list of this enum's values
    //          with the first letter capitalized and the rest uppercase
    public static ArrayList<String> getStringArrayList() {
        ArrayList<String> stringArray = new ArrayList<>();
        for (DamageType dt : DamageType.values()) {
            stringArray.add(dt.toString().substring(0, 1).toUpperCase() + dt.toString().substring(1));
        }
        return stringArray;
    }
}
