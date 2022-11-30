package enums;

import java.util.ArrayList;
import java.util.Arrays;

public enum AbilityScore {
    STRENGTH,
    DEXTERITY,
    CONSTITUTION,
    INTELLIGENCE,
    WISDOM,
    CHARISMA;

    // EFFECTS: returns a string array list of this enum's values
    //          with the first letter capitalized and the rest uppercase
    public static ArrayList<String> getStringArrayList() {
        ArrayList<String> stringArray = new ArrayList<>();
        for (AbilityScore as : AbilityScore.values()) {
            stringArray.add(as.toString().substring(0, 1).toUpperCase() + as.toString().substring(1));
        }
        return stringArray;
    }
}
