package model;

import model.statblockfields.*;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Represents a statblock with mutable hp and parent statblock stats apart from name
public class Character extends StatBlock {
    // required fields
    private int maxHP;
    private int hp;
    private final StatBlock parentStatBlock;

    // EFFECTS: constructs a character with fields from given parent statblock,
    //          but makes a new unique name for the given encounter context
    public Character(StatBlock parentStatBlock, String name) {
        super(new StatBlockBuilder(
                new Title.TitleBuilder(name, parentStatBlock.title.getType(), parentStatBlock.title.getSize(),
                        parentStatBlock.title.getAlignment()).build(),
                parentStatBlock.xp,
                parentStatBlock.hpFormula,
                parentStatBlock.proficiency,
                parentStatBlock.armour,
                parentStatBlock.speeds,
                parentStatBlock.senses,
                parentStatBlock.abilityScores,
                parentStatBlock.actions)
                .languages(parentStatBlock.languages)
                .abilities(parentStatBlock.abilities)
                .savingThrowProficiencies(parentStatBlock.savingThrowProficiencies)
                .skillProficiencies(parentStatBlock.skillProficiencies)
                .conditionImmunities(parentStatBlock.conditionImmunities)
                .resistances(parentStatBlock.resistances)
                .legendaryMechanics(parentStatBlock.legendaryMechanics));

        this.maxHP = hpFormula.roll();
        this.hp = maxHP;
        this.parentStatBlock = parentStatBlock;
    }

    // EFFECTS: searches encounter for characters named after the parent statblock: if there's none, returns the parent
    //          statblock name with 1 as suffix, otherwise arrays the character suffixes and returns the parent
    //          statblock name with the lowest number not in the array as a suffix
    public static String generateNameForEncounter(StatBlock parentStatBlock, List<Character> encounter) {
        Title title = parentStatBlock.getTitle();
        int lowestNumber = 1;
        List<Integer> suffixes;
        if (checkEncounterForSameParentTitle(title, encounter)) {
            suffixes = generateSuffixes(title, encounter);
            lowestNumber = findFirstIntegerGap(suffixes);
        }
        return title.getName() + " " + lowestNumber;
    }

    // EFFECTS: searches encounter for any characters with a parent statblock of the current character's parent
    //          and returns true if yes or no if not
    private static boolean checkEncounterForSameParentTitle(Title parentTitle, List<Character> encounter) {
        for (Character c : encounter) {
            if (c.getParentStatBlock().getTitle().getName().equals(parentTitle.getName())) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns list of suffixes for characters in encounter with selected statblock name
    private static List<Integer> generateSuffixes(Title parentTitle, List<Character> encounter) {
        List<Integer> suffixes = new ArrayList<>();
        for (Character c : encounter) {
            if (c.getTitle().getName().toLowerCase().contains(parentTitle.getName().toLowerCase())) {
                suffixes.add(Integer.parseInt(c.getTitle().getName().toLowerCase().replaceAll("[^\\d]", "")));
            }
        }
        return suffixes;
    }

    // REQUIRES: given interger list contains at least one integer
    // EFFECTS: returns lowest integer that is not already in the given list, starting with 1
    private static int findFirstIntegerGap(List<Integer> integerList) {
        int firstLowest = 1;
        integerList.sort(Comparator.naturalOrder());
        for (int i : integerList) {
            if (i == firstLowest) {
                firstLowest++;
            } else if (i > firstLowest) {
                return firstLowest;
            }
        }
        return firstLowest;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // setters
    // EFFECTS: sets current hp to sum of given int and hp; if hp is greater than maxhp, sets hp to maxhp.
    public void changeHP(int change) {
        hp += change;
        if (hp > maxHP) {
            hp = maxHP;
        }
    }

    // REQUIRES: given hp cannot be greater than maxHP
    // EFFECTS: sets current hp to given hp
    public void setHP(int hp) {
        this.hp = hp;
    }

    // REQUIRES: given max HP cannot be greater than maxHP
    // EFFECTS: sets current max HP to given max HP
    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    // EFFECTS: returns current hp and max hp as a fraction in a string
    @Override
    public String getHPString() {
        return hp + "/" + maxHP;
    }

    // EFFECTS: get original StatBlock
    public StatBlock getParentStatBlock() {
        return parentStatBlock;
    }

    // EFFECTS: get max hp
    public int getMaxHP() {
        return maxHP;
    }

    // EFFECTS: get current hp
    public int getHP() {
        return hp;
    }

    @Override
    // converts the character to a json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("parentStatBlock", parentStatBlock.toJson());
        json.put("title", title.toJson());
        json.put("xp", xp);
        json.put("hpFormula", hpFormula.toJson());
        json.put("maxHP", maxHP);
        json.put("hp", hp);
        json.put("proficiency", proficiency);
        json.put("armour", armour.toJson());
        json.put("speeds", speeds.toJson());
        json.put("senses", senses.toJson());
        json.put("abilityScores", abilityScores.toJson());
        json.put("actions", actionsToJson());
        return optionalFieldsToJson(json);
    }

    @Override
    // returns a string representation of the character
    public String toString() {
        Title title = getTitle();
        String name = title.getName();
        if (title.getGroup() != null) {
            return (name + " (Group: " + title.getGroup() + "), HP: " + getHPString());
        } else {
            return (name + ", HP: " + getHPString());
        }
    }
}
