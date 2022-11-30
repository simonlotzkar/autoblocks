package enums;

import java.util.ArrayList;
import java.util.Arrays;

public enum Skill {
    ACROBATICS(AbilityScore.DEXTERITY),
    ANIMAL_HANDLING(AbilityScore.WISDOM),
    ARCANA(AbilityScore.INTELLIGENCE),
    ATHLETICS(AbilityScore.STRENGTH),
    DECEPTION(AbilityScore.CHARISMA),
    HISTORY(AbilityScore.INTELLIGENCE),
    INSIGHT(AbilityScore.WISDOM),
    INTIMIDATION(AbilityScore.CHARISMA),
    INVESTIGATION(AbilityScore.INTELLIGENCE),
    MEDICINE(AbilityScore.WISDOM),
    NATURE(AbilityScore.INTELLIGENCE),
    PERCEPTION(AbilityScore.WISDOM),
    PERFORMANCE(AbilityScore.CHARISMA),
    PERSUASION(AbilityScore.CHARISMA),
    RELIGION(AbilityScore.INTELLIGENCE),
    SLEIGHT_OF_HAND(AbilityScore.DEXTERITY),
    STEALTH(AbilityScore.DEXTERITY),
    SURVIVAL(AbilityScore.WISDOM);

    private final AbilityScore abilityScore;

    // EFFECTS: sets this skill's ability score
    Skill(AbilityScore abilityScore) {
        this.abilityScore = abilityScore;
    }

    // EFFECTS: returns the ability score for the skill
    public AbilityScore getAbilityScore() {
        return abilityScore;
    }

    // EFFECTS: returns a string array list of this enum's values
    //          with the first letter capitalized and the rest uppercase
    public static ArrayList<String> getStringArrayList() {
        ArrayList<String> stringArray = new ArrayList<>();
        for (Skill s : Skill.values()) {
            stringArray.add(s.toString().substring(0, 1).toUpperCase() + s.toString().substring(1));
        }
        return stringArray;
    }
}
