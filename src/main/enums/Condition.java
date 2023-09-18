package enums;

import java.util.ArrayList;

public enum Condition {
    BLINDED,
    CHARMED,
    DEAFENED,
    EXHAUSTED,
    FRIGHTENED,
    GRAPPLED,
    INCAPACITATED,
    INVISIBLE,
    PARALYZED,
    PETRIFIED,
    POISONED,
    PRONE,
    RESTRAINED,
    STUNNED,
    UNCONSCIOUS;

    // EFFECTS: returns a string array list of this enum's values
    //          with the first letter capitalized and the rest uppercase
    public static ArrayList<String> getStringArrayList() {
        ArrayList<String> stringArray = new ArrayList<>();
        for (Condition c : Condition.values()) {
            stringArray.add(c.toString().substring(0, 1).toUpperCase() + c.toString().substring(1));
        }
        return stringArray;
    }
}
