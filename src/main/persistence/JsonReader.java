package persistence;

import model.*;
import model.Character;
import model.statblockfields.*;
import model.statblockfields.Action;
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
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads source file as string and returns it
    // CITATION: from JsonReader.java in JsonSerializationDemo
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }
        return contentBuilder.toString();
    }

    // EFFECTS: reads the library from file and returns it;
    // throws IOException if an error occurs reading data from file
    public List<StatBlock> readLibrary() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLibrary(jsonObject.getJSONArray("library"));
    }

    // EFFECTS: reads the encounter from file and returns it;
    // throws IOException if an error occurs reading data from file
    public List<Character> readEncounter() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseEncounter(jsonObject.getJSONArray("encounter"));
    }

    // EFFECTS: parses the library from JSON array and returns it
    private List<StatBlock> parseLibrary(JSONArray jsonArray) {
        List<StatBlock> library = new ArrayList<>();
        for (Object json : jsonArray) {
            library.add(parseStatBlock((JSONObject) json));
        }
        return library;
    }

    // EFFECTS: converts to statblock from JSON object
    private StatBlock parseStatBlock(JSONObject jsonObject) {
        return new StatBlock.StatBlockBuilder(
                parseTitle(jsonObject.getJSONObject("title")),
                jsonObject.getInt("xp"),
                parseRollFormula(jsonObject.getJSONObject("hpFormula")),
                jsonObject.getInt("proficiency"),
                parseArmour(jsonObject.getJSONObject("armour")),
                parseSpeeds(jsonObject.getJSONObject("speeds")),
                parseSenses(jsonObject.getJSONObject("senses")),
                parseAbilityScores(jsonObject.getJSONObject("abilityScores")),
                parseActions(jsonObject.getJSONArray("actions")))
                .languages(parseLanguages(jsonObject.getJSONObject("languages")))
                .abilities(parseAbilities(jsonObject.getJSONArray("abilities")))
                .savingThrowProficiencies(
                        parseSavingThrowProficiencies(jsonObject.optJSONArray("savingThrowProficiencies")))
                .skillProficiencies(parseSkillProficiencies(jsonObject.optJSONArray("skillProficiencies")))
                .conditionImmunities(parseConditionImmunities(jsonObject.optJSONArray("conditionImmunities")))
                .resistances(parseResistances(jsonObject.optJSONObject("resistances")))
                .legendaryMechanics(parseLegendaryMechanics(jsonObject.optJSONObject("legendaryMechanics")))
                .build();
    }

    // EFFECTS: parses title from JSON object and returns it
    private Title parseTitle(JSONObject jsonObject) {
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
    private RollFormula parseRollFormula(JSONObject jsonObject) {
        return new RollFormula(
                jsonObject.getInt("amountOfDice"),
                jsonObject.getInt("dieSides"),
                jsonObject.getInt("modifier"));
    }

    // EFFECTS: parses armour from JSON object and returns it
    private Armour parseArmour(JSONObject jsonObject) {
        return new Armour.ArmourBuilder(
                jsonObject.getInt("ac"))
                .armourName(jsonObject.getString("armourName"))
                .magicArmour(jsonObject.getInt("magicArmour"))
                .build();
    }

    // EFFECTS: parses speeds from JSON object and returns it
    private Speeds parseSpeeds(JSONObject jsonObject) {
        return new Speeds.SpeedsBuilder(
                jsonObject.getInt("speed"))
                .burrow(jsonObject.getInt("burrow"))
                .climb(jsonObject.getInt("climb"))
                .fly(jsonObject.getInt("fly"))
                .swim(jsonObject.getInt("swim"))
                .build();
    }

    // EFFECTS: parses senses from JSON object and returns it
    private Senses parseSenses(JSONObject jsonObject) {
        return new Senses.SensesBuilder(
                jsonObject.getInt("passivePerception"))
                .darkVision(jsonObject.getInt("darkVision"))
                .tremorSense(jsonObject.getInt("tremorSense"))
                .trueSight(jsonObject.getInt("trueSight"))
                .blindSight(jsonObject.getInt("blindSight"))
                .build();
    }

    // EFFECTS: parses ability scores from JSON object and returns it
    private AbilityScores parseAbilityScores(JSONObject jsonObject) {
        return new AbilityScores(
                jsonObject.getInt("strength"),
                jsonObject.getInt("dexterity"),
                jsonObject.getInt("constitution"),
                jsonObject.getInt("intelligence"),
                jsonObject.getInt("wisdom"),
                jsonObject.getInt("charisma"));
    }

    // EFFECTS: parses abilities from JSON array and returns them as a list
    private List<Ability> parseAbilities(JSONArray jsonArray) {
        List<Ability> abilities = new ArrayList<>();
        for (Object json : jsonArray) {
            abilities.add(parseAbility((JSONObject) json));
        }
        return abilities;
    }

    // EFFECTS: parses ability from JSON object and returns it
    private Ability parseAbility(JSONObject jsonObject) {
        return new Ability(
                jsonObject.getString("name"),
                jsonObject.getString("description"));
    }

    // EFFECTS: parses actions from JSON array and returns them as a list
    private List<Action> parseActions(JSONArray jsonArray) {
        List<Action> actions = new ArrayList<>();
        for (Object json : jsonArray) {
            actions.add(parseAction((JSONObject) json));
        }
        return actions;
    }

    // EFFECTS: parses action from JSON object and returns it
    private Action parseAction(JSONObject jsonObject) {
        return new Action(
                jsonObject.getString("name"),
                jsonObject.getString("description"),
                jsonObject.getString("reach"),
                parseRollFormula(jsonObject.getJSONObject("hitFormula")),
                parseDamageMap(jsonObject.getJSONObject("damageMap")));
    }

    // EFFECTS: parses damage rolls from JSON object and returns it
    private HashMap<String, RollFormula> parseDamageMap(JSONObject jsonObject) {
        Map<String, Object> jsonMap = jsonObject.toMap();
        HashMap<String, RollFormula> damageMap = new HashMap<>();
        jsonMap.putAll(damageMap);
        return damageMap;
    }

    // EFFECTS: parses languages from JSON object and returns it
    private Languages parseLanguages(JSONObject jsonObject) {
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
    private List<String> parseSavingThrowProficiencies(JSONArray jsonArray) {
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
    private List<String> parseConditionImmunities(JSONArray jsonArray) {
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
    private List<String> parseSkillProficiencies(JSONArray jsonArray) {
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
    private HashMap<String, String> parseResistances(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        Map<String, Object> jsonMap = jsonObject.toMap();
        HashMap<String, String> resistances = new HashMap<>();

        jsonMap.putAll(resistances);
        return resistances;
    }

    // EFFECTS: parses legendary mechanics from JSON object and returns it
    private LegendaryMechanics parseLegendaryMechanics(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        return new LegendaryMechanics(
                jsonObject.getString("legendaryDescription"),
                parseAbilities(jsonObject.getJSONArray("legendaryActions")));
    }

    // EFFECTS: parses the encounter from JSON array and returns it
    // CITATION: from JsonReader.java in JsonSerializationDemo
    private List<Character> parseEncounter(JSONArray jsonArray) {
        List<Character> encounter = new ArrayList<>();
        for (Object json : jsonArray) {
            encounter.add(parseCharacter((JSONObject) json));
        }
        return encounter;
    }

    // EFFECTS: takes a json object representing a character and returns it as a character object
    private Character parseCharacter(JSONObject jsonObject) {
        Character character = new Character(parseStatBlock(jsonObject.getJSONObject("parentStatBlock")),
                jsonObject.getJSONObject("title").getString("name"));

        character.setMaxHP(jsonObject.getInt("maxHP"));
        character.setHP(jsonObject.getInt("hp"));

        return character;
    }
}
