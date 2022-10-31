package persistence;

import model.*;
import model.Character;
import model.statblockfields.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

// Represents a reader that reads libraries and encounters from JSON data stored in file
// CITATION: from JsonReader.java in JsonSerializationDemo
public class JsonReader {
    protected String source;

    // EFFECTS: constructs reader to read from source file
    // CITATION: from JsonReader.java in JsonSerializationDemo
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads the library from file and returns them;
    // throws IOException if an error occurs reading data from file
    // CITATION: from JsonReader.java in JsonSerializationDemo
    public LibraryAndEncounter read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLibraryAndEncounter(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    // CITATION: from JsonReader.java in JsonSerializationDemo
    protected String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses the given json object for library and encounter objects then returns their joined object
    private LibraryAndEncounter parseLibraryAndEncounter(JSONObject jsonObject) {
        return new LibraryAndEncounter(
                parseLibrary(jsonObject.getJSONObject("library")),
                parseEncounter(jsonObject.getJSONObject("encounter")));
    }

    // EFFECTS: parses the library from JSON array and returns it
    protected Library parseLibrary(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Library library = new Library(name, new ArrayList<>());
        addStatBlocks(library, jsonObject);
        return library;
    }

    // MODIFIES: library
    // EFFECTS: adds statblocks to library
    protected void addStatBlocks(Library library, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("statBlocks");
        for (Object json : jsonArray) {
            library.add(parseStatBlock((JSONObject) json));
        }
    }

    // EFFECTS: converts to statblock from JSON object
    protected StatBlock parseStatBlock(JSONObject jsonObject) {
        return new StatBlock.StatBlockBuilder(
                parseTitle(jsonObject.getJSONObject("title")),
                jsonObject.getInt("xp"),
                parseRollFormula(jsonObject.getJSONObject("hpFormula")),
                jsonObject.getInt("proficiency"),
                parseArmour(jsonObject.getJSONObject("armour")),
                parseSpeeds(jsonObject.getJSONObject("speeds")),
                parseSenses(jsonObject.getJSONObject("senses")),
                parseAbilityScores(jsonObject.getJSONObject("abilityScores")),
                parseAbilities(jsonObject.getJSONArray("abilities")),
                parseActions(jsonObject.getJSONArray("actions")),
                parseLanguages(jsonObject.getJSONObject("languages")))

                .savingThrowProficiencies(
                        parseSavingThrowProficiencies(jsonObject.optJSONArray("savingThrowProficiencies")))
                .skillProficiencies(parseSkillProficiencies(jsonObject.optJSONArray("skillProficiencies")))
                .conditionImmunities(parseConditionImmunities(jsonObject.optJSONArray("conditionImmunities")))
                .resistances(parseResistances(jsonObject.optJSONObject("resistances")))
                .legendaryMechanics(parseLegendaryMechanics(jsonObject.optJSONObject("legendaryMechanics")))
                .build();
    }

    // EFFECTS: parses title from JSON object and returns it
    protected Title parseTitle(JSONObject jsonObject) {
        if (jsonObject.optString("group").equals("")) {
            return new Title.TitleBuilder(
                    jsonObject.getString("name"),
                    jsonObject.getString("type"),
                    jsonObject.getString("size"),
                    jsonObject.getString("alignment"))
                    .build();
        } else {
            return new Title.TitleBuilder(
                    jsonObject.getString("name"),
                    jsonObject.getString("type"),
                    jsonObject.getString("size"),
                    jsonObject.getString("alignment"))
                    .group(jsonObject.getString("group"))
                    .build();
        }
    }

    // EFFECTS: parses hp formula from JSON object and returns it
    protected RollFormula parseRollFormula(JSONObject jsonObject) {
        return new RollFormula(
                jsonObject.getInt("amountOfDice"),
                jsonObject.getInt("dieSides"),
                jsonObject.getInt("modifier"));
    }

    // EFFECTS: parses armour from JSON object and returns it
    protected Armour parseArmour(JSONObject jsonObject) {
        return new Armour.ArmourBuilder(
                jsonObject.getInt("ac"))
                .armourName(jsonObject.getString("armourName"))
                .magicArmour(jsonObject.getInt("magicArmour"))
                .build();
    }

    // EFFECTS: parses speeds from JSON object and returns it
    protected Speeds parseSpeeds(JSONObject jsonObject) {
        return new Speeds.SpeedsBuilder(
                jsonObject.getInt("speed"))
                .burrow(jsonObject.getInt("burrow"))
                .climb(jsonObject.getInt("climb"))
                .fly(jsonObject.getInt("fly"))
                .swim(jsonObject.getInt("swim"))
                .build();
    }

    // EFFECTS: parses senses from JSON object and returns it
    protected Senses parseSenses(JSONObject jsonObject) {
        return new Senses.SensesBuilder(
                jsonObject.getInt("passivePerception"))
                .darkVision(jsonObject.getInt("darkVision"))
                .tremorSense(jsonObject.getInt("tremorSense"))
                .trueSight(jsonObject.getInt("trueSight"))
                .blindSight(jsonObject.getInt("blindSight"))
                .build();
    }

    // EFFECTS: parses ability scores from JSON object and returns it
    protected AbilityScores parseAbilityScores(JSONObject jsonObject) {
        return new AbilityScores(
                jsonObject.getInt("strength"),
                jsonObject.getInt("dexterity"),
                jsonObject.getInt("constitution"),
                jsonObject.getInt("intelligence"),
                jsonObject.getInt("wisdom"),
                jsonObject.getInt("charisma"));
    }

    // EFFECTS: parses abilities from JSON array and returns them as a list
    protected List<Ability> parseAbilities(JSONArray jsonArray) {
        List<Ability> abilities = new ArrayList<>();
        for (Object json : jsonArray) {
            abilities.add(parseAbility((JSONObject) json));
        }
        return abilities;
    }

    // EFFECTS: parses ability from JSON object and returns it
    protected Ability parseAbility(JSONObject jsonObject) {
        return new Ability(
                jsonObject.getString("name"),
                jsonObject.getString("description"));
    }

    // EFFECTS: parses actions from JSON array and returns them as a list
    protected List<Action> parseActions(JSONArray jsonArray) {
        List<Action> actions = new ArrayList<>();
        for (Object json : jsonArray) {
            actions.add(parseAction((JSONObject) json));
        }
        return actions;
    }

    // EFFECTS: parses action from JSON object and returns it
    protected Action parseAction(JSONObject jsonObject) {
        return new Action(
                jsonObject.getString("name"),
                jsonObject.getString("description"),
                jsonObject.getString("damageType"),
                jsonObject.getString("reach"),
                parseRollFormula(jsonObject.getJSONObject("hitFormula")),
                parseRollFormula(jsonObject.getJSONObject("damageFormula")));
    }

    // EFFECTS: parses languages from JSON object and returns it
    protected Languages parseLanguages(JSONObject jsonObject) {
        List<String> languageList = new ArrayList<>();
        JSONArray languageListJsonArray = jsonObject.getJSONArray("languagesList");
        for (Object json : languageListJsonArray) {
            languageList.add(json.toString());
        }

        return new Languages.LanguagesBuilder(languageList)
                .telepathy(jsonObject.getInt("telepathy"))
                .build();
    }

    // EFFECTS: parses saving throw proficiencies from JSON object and returns it
    protected List<String> parseSavingThrowProficiencies(JSONArray jsonArray) {
        if (jsonArray == null) {
            return null;
        }
        List<String> savingThrows = new ArrayList<>();
        for (Object json : jsonArray) {
            savingThrows.add(json.toString());
        }
        if (savingThrows.isEmpty()) {
            return null;
        } else {
            return savingThrows;
        }
    }

    // EFFECTS: parses condition immunities from JSON object and returns it
    protected List<String> parseConditionImmunities(JSONArray jsonArray) {
        if (jsonArray == null) {
            return null;
        }
        List<String> conditionImmunities = new ArrayList<>();
        for (Object json : jsonArray) {
            conditionImmunities.add(json.toString());
        }
        if (conditionImmunities.isEmpty()) {
            return null;
        } else {
            return conditionImmunities;
        }
    }

    // EFFECTS: parses skill proficiencies from JSON object and returns it
    protected List<String> parseSkillProficiencies(JSONArray jsonArray) {
        if (jsonArray == null) {
            return null;
        }
        List<String> skillProficiencies = new ArrayList<>();
        for (Object json : jsonArray) {
            skillProficiencies.add(json.toString());
        }
        if (skillProficiencies.isEmpty()) {
            return null;
        } else {
            return skillProficiencies;
        }
    }

    // EFFECTS: parses resistances from JSON object and returns it
    protected HashMap<String, String> parseResistances(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        Map<String, Object> jsonMap = jsonObject.toMap();
        HashMap<String, String> resistances = new HashMap<>();

        jsonMap.forEach((s, o) -> resistances.put(s, o.toString()));

        if (!resistances.isEmpty()) {
            return resistances;
        }
        return null;
    }

    // EFFECTS: parses legendary mechanics from JSON object and returns it
    protected LegendaryMechanics parseLegendaryMechanics(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        return new LegendaryMechanics(
                jsonObject.getString("legendaryDescription"),
                parseAbilities(jsonObject.getJSONArray("legendaryActions")));
    }

    // EFFECTS: parses the encounter from JSON array and returns it
    // CITATION: from JsonReader.java in JsonSerializationDemo
    private Encounter parseEncounter(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Encounter encounter = new Encounter(name, new ArrayList<>());
        addCharacters(encounter, jsonObject);
        return encounter;
    }

    // MODIFIES: encounter
    // EFFECTS: adds characters to encounter
    // CITATION: from JsonReader.java in JsonSerializationDemo
    private void addCharacters(Encounter encounter, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("characters");
        for (Object json : jsonArray) {
            encounter.add(parseCharacter((JSONObject) json));
        }
    }

    // MODIFIES: encounter
    // EFFECTS: adds character from JSON object to encounter
    private Character parseCharacter(JSONObject jsonObject) {
        Character character = (Character) new Character.CharacterBuilder(
                parseStatBlock(jsonObject.getJSONObject("parentStatBlock")),
                parseTitle(jsonObject.getJSONObject("title")),
                jsonObject.getInt("xp"),
                parseRollFormula(jsonObject.getJSONObject("hpFormula")),
                jsonObject.getInt("proficiency"),
                parseArmour(jsonObject.getJSONObject("armour")),
                parseSpeeds(jsonObject.getJSONObject("speeds")),
                parseSenses(jsonObject.getJSONObject("senses")),
                parseAbilityScores(jsonObject.getJSONObject("abilityScores")),
                parseAbilities(jsonObject.getJSONArray("abilities")),
                parseActions(jsonObject.getJSONArray("actions")),
                parseLanguages(jsonObject.getJSONObject("languages")))

                .savingThrowProficiencies(
                        parseSavingThrowProficiencies(jsonObject.optJSONArray("savingThrowProficiencies")))
                .skillProficiencies(parseSkillProficiencies(jsonObject.optJSONArray("skillProficiencies")))
                .conditionImmunities(parseConditionImmunities(jsonObject.optJSONArray("conditionImmunities")))
                .resistances(parseResistances(jsonObject.optJSONObject("resistances")))
                .legendaryMechanics(parseLegendaryMechanics(jsonObject.optJSONObject("legendaryMechanics")))
                .build();

        character.setMaxHP(jsonObject.getInt("maxHP"));
        character.setHP(jsonObject.getInt("hp"));

        return character;
    }
}
