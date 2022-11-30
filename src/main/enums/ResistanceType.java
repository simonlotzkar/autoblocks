package enums;

import java.util.ArrayList;

public enum ResistanceType {
    RESISTANCE,
    VULNERABILITY,
    IMMUNITY;

    // EFFECTS: returns a string array list of this enum's values
    //          with the first letter capitalized and the rest uppercase
    public static ArrayList<String> getStringArrayList() {
        ArrayList<String> stringArray = new ArrayList<>();
        for (ResistanceType rt : ResistanceType.values()) {
            stringArray.add(rt.toString().substring(0, 1).toUpperCase() + rt.toString().substring(1));
        }
        return stringArray;
    }
}
