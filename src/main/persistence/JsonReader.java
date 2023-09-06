package persistence;

import enums.*;
import exceptions.IncompleteFieldException;
import model.*;
import model.NPC;
import model.statblockfields.*;
import model.statblockfields.RollableAction;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

// Represents a reader that reads libraries and encounters from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads source file as string and returns it
    //          throws an exception if there is any errors when reading
    // CITATION: from JsonReader.java in JsonSerializationDemo
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }
        return contentBuilder.toString();
    }

    // EFFECTS: reads the library from file and returns it;
    //          throws an exception if there is any errors when reading
    public List<StatBlock> readLibrary() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLibrary(jsonObject.getJSONArray("library"));
    }

    // EFFECTS: reads the encounter from file and returns it;
    //          throws an exception if there is any errors when reading
    public List<NPC> readEncounter() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseEncounter(jsonObject.getJSONArray("encounter"));
    }

    // EFFECTS: parses the library from JSON array and returns it
    //          throws an exception if there is any errors when reading
    private List<StatBlock> parseLibrary(JSONArray jsonArray) throws IOException {
        List<StatBlock> library = new ArrayList<>();
        for (Object json : jsonArray) {
            library.add(parseStatBlock((JSONObject) json));
        }
        return library;
    }

    // EFFECTS: converts to statblock from JSON object
    //          throws an exception if there is any errors when reading
    private StatBlock parseStatBlock(JSONObject jsonObject) throws IOException {
        try {
            return new StatBlock.StatBlockBuilder(
                    parseTitle(jsonObject.getJSONObject("title")),
                    jsonObject.getInt("xp"),
                    parseRollFormula(jsonObject.getJSONObject("hpFormula")),
                    jsonObject.getInt("proficiency"),
                    parseArmour(jsonObject.getJSONObject("armour")),
                    parseSpeeds(jsonObject.getJSONObject("speeds")),
                    parseSenses(jsonObject.getJSONObject("senses")),
                    parseAbilityScores(jsonObject.getJSONObject("abilityScores")),
                    parseRollableActions(jsonObject.getJSONArray("rollableActions")))
                    .languages(parseLanguages(jsonObject.optJSONObject("languages")))
                    .abilities(parseAbilities(jsonObject.optJSONArray("abilities")))
                    .savingThrowProficiencies(
                            parseSavingThrowProficiencies(jsonObject.optJSONArray("savingThrowProficiencies")))
                    .skillProficiencies(parseSkillProficiencies(jsonObject.optJSONArray("skillProficiencies")))
                    .conditionImmunities(parseConditionImmunities(jsonObject.optJSONArray("conditionImmunities")))
                    .resistances(parseResistances(jsonObject.optJSONObject("resistances")))
                    .legendaryMechanics(parseLegendaryMechanics(jsonObject.optJSONObject("legendaryMechanics")))
                    .build();
        } catch (IncompleteFieldException e) {
            throw new IOException("incomplete field found reading a statblock");
        }
    }

    // EFFECTS: parses title from JSON object and returns it
    //          throws an exception if there is any errors when reading
    private Title parseTitle(JSONObject jsonObject) throws IncompleteFieldException {
        return new Title(
                jsonObject.getString("name"),
                jsonObject.getString("type"),
                jsonObject.getString("size"),
                jsonObject.getString("alignment"));
    }

    // EFFECTS: parses hp formula from JSON object and returns it
    //          throws an exception if there is any errors when reading
    private RollFormula parseRollFormula(JSONObject jsonObject) throws IncompleteFieldException {
        return new RollFormula(
                jsonObject.getInt("amountOfDice"),
                jsonObject.getInt("dieSides"),
                jsonObject.getInt("modifier"));
    }

    // EFFECTS: parses armour from JSON object and returns it
    //          throws an exception if there is any errors when reading
    private Armour parseArmour(JSONObject jsonObject) throws IncompleteFieldException {
        if (jsonObject.optString("armourName").equalsIgnoreCase("")) {
            return new Armour.ArmourBuilder(
                    jsonObject.getInt("ac"))
                    .magicArmour(jsonObject.getInt("magicArmour"))
                    .build();
        } else {
            return new Armour.ArmourBuilder(
                    jsonObject.getInt("ac"))
                    .armourName(jsonObject.optString("armourName"))
                    .magicArmour(jsonObject.getInt("magicArmour"))
                    .build();
        }
    }

    // EFFECTS: parses speeds from JSON object and returns it
    //          throws an exception if there is any errors when reading
    private Speeds parseSpeeds(JSONObject jsonObject) throws IncompleteFieldException {
        return new Speeds.SpeedsBuilder(
                jsonObject.getInt("speed"))
                .burrow(jsonObject.getInt("burrow"))
                .climb(jsonObject.getInt("climb"))
                .fly(jsonObject.getInt("fly"))
                .swim(jsonObject.getInt("swim"))
                .build();
    }

    // EFFECTS: parses senses from JSON object and returns it
    //          throws an exception if there is any errors when reading
    private Senses parseSenses(JSONObject jsonObject) throws IncompleteFieldException {
        return new Senses.SensesBuilder(
                jsonObject.getInt("passivePerception"))
                .darkVision(jsonObject.getInt("darkVision"))
                .tremorSense(jsonObject.getInt("tremorSense"))
                .trueSight(jsonObject.getInt("trueSight"))
                .blindSight(jsonObject.getInt("blindSight"))
                .build();
    }

    // EFFECTS: parses ability scores from JSON object and returns it
    //          throws an exception if there is any errors when reading
    private AbilityScoreSet parseAbilityScores(JSONObject jsonObject) throws IncompleteFieldException {
        return new AbilityScoreSet(
                jsonObject.getInt("strength"),
                jsonObject.getInt("dexterity"),
                jsonObject.getInt("constitution"),
                jsonObject.getInt("intelligence"),
                jsonObject.getInt("wisdom"),
                jsonObject.getInt("charisma"));
    }

    // EFFECTS: parses abilities from JSON array and returns them as a list
    //          throws an exception if there is any errors when reading
    private List<Ability> parseAbilities(JSONArray jsonArray) throws IncompleteFieldException {
        if (jsonArray == null) {
            return null;
        }

        List<Ability> abilities = new ArrayList<>();
        for (Object json : jsonArray) {
            abilities.add(parseAbility((JSONObject) json));
        }
        return abilities;
    }

    // EFFECTS: parses ability from JSON object and returns it
    //          throws an exception if there is any errors when reading
    private Ability parseAbility(JSONObject jsonObject) throws IncompleteFieldException {
        return new Ability(
                jsonObject.getString("name"),
                jsonObject.getString("description"));
    }

    // EFFECTS: parses actions from JSON array and returns them as a list
    //          throws an exception if there is any errors when reading
    private List<RollableAction> parseRollableActions(JSONArray jsonArray) throws IncompleteFieldException {
        List<RollableAction> rollableActions = new ArrayList<>();
        for (Object json : jsonArray) {
            rollableActions.add(parseRollableAction((JSONObject) json));
        }
        return rollableActions;
    }

    // EFFECTS: parses rollable action from JSON object and returns it
    //          throws an exception if there is any errors when reading
    private RollableAction parseRollableAction(JSONObject jsonObject) throws IncompleteFieldException {
        return new RollableAction(
                jsonObject.getString("name"),
                jsonObject.getString("description"),
                jsonObject.getInt("range"),
                jsonObject.getInt("longRange"),
                jsonObject.getInt("hitModifier"),
                parseDamageMap(jsonObject.getJSONObject("damageMap")));
    }

    // EFFECTS: parses damage rolls from JSON object and returns it
    //          throws an exception if there is any errors when reading
    private HashMap<DamageType, RollFormula> parseDamageMap(JSONObject jsonObject) throws IncompleteFieldException {
        HashMap<DamageType, RollFormula> damageMap = new HashMap<>();
        Iterator<String> keys = jsonObject.keys();

        while (keys.hasNext()) {
            String key = keys.next();
            if (jsonObject.get(key) instanceof JSONObject) {
                for (DamageType dt : DamageType.values()) {
                    if (key.equalsIgnoreCase(dt.toString())) {
                        damageMap.put(dt, parseRollFormula((JSONObject) jsonObject.get(key)));
                    }
                }
            }
        }

        return damageMap;
    }

    // EFFECTS: parses languages from JSON object and returns it
    //          throws an exception if there is any errors when reading
    private Languages parseLanguages(JSONObject jsonObject) throws IncompleteFieldException {
        if (jsonObject == null) {
            return null;
        }

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
    //          throws an exception if there is any errors when reading
    private List<AbilityScore> parseSavingThrowProficiencies(JSONArray jsonArray) throws IncompleteFieldException {
        if (jsonArray == null) {
            return null;
        }
        List<AbilityScore> savingThrows = new ArrayList<>();
        for (Object json : jsonArray) {
            for (AbilityScore as : AbilityScore.values()) {
                if (json.toString().equalsIgnoreCase(as.toString())) {
                    savingThrows.add(as);
                }
            }
        }
        if (savingThrows.isEmpty()) {
            return null;
        } else {
            return savingThrows;
        }
    }

    // EFFECTS: parses condition immunities from JSON object and returns it
    //          throws an exception if there is any errors when reading
    private List<Condition> parseConditionImmunities(JSONArray jsonArray) throws IncompleteFieldException {
        if (jsonArray == null) {
            return null;
        }
        List<Condition> conditionImmunities = new ArrayList<>();
        for (Object json : jsonArray) {
            for (Condition c : Condition.values()) {
                if (json.toString().equalsIgnoreCase(c.toString())) {
                    conditionImmunities.add(c);
                }
            }
        }
        if (conditionImmunities.isEmpty()) {
            return null;
        } else {
            return conditionImmunities;
        }
    }

    // EFFECTS: parses skill proficiencies from JSON object and returns it
    //          throws an exception if there is any errors when reading
    private List<Skill> parseSkillProficiencies(JSONArray jsonArray) throws IncompleteFieldException {
        if (jsonArray == null) {
            return null;
        }
        List<Skill> skillProficiencies = new ArrayList<>();
        for (Object json : jsonArray) {
            for (Skill s : Skill.values()) {
                if (json.toString().equalsIgnoreCase(s.toString())) {
                    skillProficiencies.add(s);
                }
            }
        }
        if (skillProficiencies.isEmpty()) {
            return null;
        } else {
            return skillProficiencies;
        }
    }

    // EFFECTS: parses resistances map from JSON object and returns it
    //          throws an exception if there is any errors when reading
    private HashMap<DamageType, ResistanceType> parseResistances(JSONObject jsonObject)
            throws IncompleteFieldException {
        if (jsonObject == null) {
            return null;
        }
        Map<String, Object> jsonMap = jsonObject.toMap();
        HashMap<DamageType, ResistanceType> resistances = new HashMap<>();

        try {
            jsonMap.forEach((s, o) -> resistances.put(parseDamageType(s), parseResistanceType(o)));
        } catch (IllegalArgumentException e) {
            throw new IncompleteFieldException("damage or resistance type read invalid string(s)");
        }
        return resistances;
    }

    // EFFECTS: returns the corresponding damage type for the given string, throws an exception if the given string is
    //          not a string representation of a damage type
    private DamageType parseDamageType(String s) throws IllegalArgumentException {
        for (DamageType dt : DamageType.values()) {
            if (s.equalsIgnoreCase(dt.toString())) {
                return dt;
            }
        }
        throw new IllegalArgumentException("given damage type string is not valid");
    }

    // EFFECTS: returns the corresponding resistance type for the given string, throws an exception if the given string
    //          is not a string representation of a resistance type
    private ResistanceType parseResistanceType(Object o) throws IllegalArgumentException {
        for (ResistanceType rt : ResistanceType.values()) {
            if (o.toString().equalsIgnoreCase(rt.toString())) {
                return rt;
            }
        }
        throw new IllegalArgumentException("given resistance type string is not valid");
    }

    // EFFECTS: parses legendary mechanics from JSON object and returns it
    //          throws an exception if there is any errors when reading
    private LegendaryMechanics parseLegendaryMechanics(JSONObject jsonObject) throws IncompleteFieldException {
        if (jsonObject == null) {
            return null;
        }
        return new LegendaryMechanics(
                jsonObject.getString("legendaryDescription"),
                parseAbilities(jsonObject.getJSONArray("legendaryActions")));
    }

    // EFFECTS: parses the encounter from JSON array and returns it
    //          throws an exception if there is any errors when reading
    private List<NPC> parseEncounter(JSONArray jsonArray) throws IOException {
        List<NPC> encounter = new ArrayList<>();
        for (Object json : jsonArray) {
            encounter.add(parseNPC((JSONObject) json));
        }
        return encounter;
    }

    // EFFECTS: takes a json object representing an NPC and returns it as an NPC object,
    //          throws an exception if there is any errors when reading
    private NPC parseNPC(JSONObject jsonObject) throws IOException {
        try {
            StatBlock parentStatBlock = parseStatBlock(jsonObject.getJSONObject("parentStatBlock"));
            Title parentTitle = parentStatBlock.getTitle();
            NPC npc = new NPC(parentStatBlock);

            npc.setTitle(new Title(jsonObject.getJSONObject("title").getString("name"), parentTitle.getSize(),
                    parentTitle.getType(), parentTitle.getAlignment()));
            npc.setMaxHP(jsonObject.getInt("maxHP"));
            npc.setHP(jsonObject.getInt("hp"));

            return npc;

        } catch (IncompleteFieldException e) {
            throw new IOException("incomplete field found reading an NPC");
        }
    }
}
