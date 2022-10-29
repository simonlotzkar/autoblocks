package persistence;

import model.*;
import model.statblockfields.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Represents a reader that reads statblock library from JSON data stored in file
// CITATION: based on JsonReader.java in JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    // CITATION: from JsonReader.java in JsonSerializationDemo
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads the library (list of statblocks) from file and returns them;
    // throws IOException if an error occurs reading data from file
    // CITATION: from JsonReader.java in JsonSerializationDemo
    public Library read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLibrary(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    // CITATION: from JsonReader.java in JsonSerializationDemo
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses the library (list of statblocks) from JSON array and returns it
    // CITATION: from JsonReader.java in JsonSerializationDemo
    private Library parseLibrary(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Library library = new Library(name, new ArrayList<>());
        addStatBlocks(library, jsonObject);
        return library;
    }

    // MODIFIES: library
    // EFFECTS: adds statblocks to library
    // CITATION: from JsonReader.java in JsonSerializationDemo
    private void addStatBlocks(Library library, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("statBlocks");
        for (Object json : jsonArray) {
            JSONObject statBlock = (JSONObject) json;
            addStatBlock(library, statBlock);
        }
    }

    // MODIFIES: library
    // EFFECTS: adds statblock from JSON object to library
    private void addStatBlock(Library library, JSONObject jsonObject) {
        StatBlock statBlock = new StatBlock.StatBlockBuilder(
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
//                .savingThrowProficiencies(
//                        parseSavingThrowProficiencies(jsonObject.getJSONObject("savingThrowProficiencies")))
//                .skillProficiencies(parseSkillProficiencies(jsonObject.getJSONObject("skillProficiencies")))
//                .conditionImmunities(parseConditionImmunities(jsonObject.getJSONObject("conditionImmunities")))
//                .resistances(parseResistances(jsonObject.getJSONObject("resistances")))
//                .legendaryMechanics(parseLegendaryMechanics(jsonObject.getJSONObject("legendaryMechanics")))
                .build();
        library.addStatBlock(statBlock);
    }

    // EFFECTS: parses title from JSON object and returns it
    private Title parseTitle(JSONObject jsonObject) {
        return new Title.TitleBuilder(
                jsonObject.getString("name"),
                jsonObject.getString("type"),
                jsonObject.getString("size"),
                jsonObject.getString("alignment")).build();
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
                jsonObject.getString("damageType"),
                jsonObject.getString("reach"),
                parseRollFormula(jsonObject.getJSONObject("hitFormula")),
                parseRollFormula(jsonObject.getJSONObject("damageFormula")));
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
    private SavingThrowProficiencies parseSavingThrowProficiencies(JSONObject jsonObject) {
        return new SavingThrowProficiencies.SavingThrowProficienciesBuilder()
                .strengthProficiency(jsonObject.getBoolean("strengthProficiency"))
                .dexterityProficiency(jsonObject.getBoolean("dexterityProficiency"))
                .constitutionProficiency(jsonObject.getBoolean("constitutionProficiency"))
                .intelligenceProficiency(jsonObject.getBoolean("intelligenceProficiency"))
                .wisdomProficiency(jsonObject.getBoolean("wisdomProficiency"))
                .charismaProficiency(jsonObject.getBoolean("charismaProficiency"))
                .build();
    }

    // EFFECTS: parses resistances from JSON object and returns it
    private Resistances parseResistances(JSONObject jsonObject) {
        return new Resistances.ResistancesBuilder()
                .acid(jsonObject.getString("acid"))
                .bludgeoning(jsonObject.getString("bludgeoning"))
                .cold(jsonObject.getString("cold"))
                .fire(jsonObject.getString("fire"))
                .force(jsonObject.getString("force"))
                .lightning(jsonObject.getString("lightning"))
                .necrotic(jsonObject.getString("necrotic"))
                .piercing(jsonObject.getString("piercing"))
                .poison(jsonObject.getString("poison"))
                .psychic(jsonObject.getString("psychic"))
                .radiant(jsonObject.getString("radiant"))
                .slashing(jsonObject.getString("slashing"))
                .thunder(jsonObject.getString("thunder"))
                .nonMagical(jsonObject.getString("nonMagical"))
                .nonSilver(jsonObject.getString("nonSilver"))
                .nonAdamantine(jsonObject.getString("nonAdamantine"))
                .build();
    }

    // EFFECTS: parses condition immunities from JSON object and returns it
    private ConditionImmunities parseConditionImmunities(JSONObject jsonObject) {
        return new ConditionImmunities.ConditionImmunitiesBuilder()
                .blinded(jsonObject.getBoolean("blinded"))
                .charmed(jsonObject.getBoolean("charmed"))
                .deafened(jsonObject.getBoolean("deafened"))
                .frightened(jsonObject.getBoolean("frightened"))
                .grappled(jsonObject.getBoolean("grappled"))
                .incapacitated(jsonObject.getBoolean("incapacitated"))
                .invisible(jsonObject.getBoolean("invisible"))
                .paralyzed(jsonObject.getBoolean("paralyzed"))
                .petrified(jsonObject.getBoolean("petrified"))
                .poisoned(jsonObject.getBoolean("poisoned"))
                .prone(jsonObject.getBoolean("prone"))
                .restrained(jsonObject.getBoolean("restrained"))
                .stunned(jsonObject.getBoolean("stunned"))
                .unconscious(jsonObject.getBoolean("unconscious"))
                .build();
    }

    // EFFECTS: parses skill proficiencies from JSON object and returns it
    private SkillProficiencies parseSkillProficiencies(JSONObject jsonObject) {
        return new SkillProficiencies.SkillProficienciesBuilder()
                .acrobatics(jsonObject.getBoolean("acrobatics"))
                .animalHandling(jsonObject.getBoolean("animalHandling"))
                .arcana(jsonObject.getBoolean("arcana"))
                .athletics(jsonObject.getBoolean("athletics"))
                .deception(jsonObject.getBoolean("deception"))
                .history(jsonObject.getBoolean("history"))
                .insight(jsonObject.getBoolean("insight"))
                .intimidation(jsonObject.getBoolean("intimidation"))
                .investigation(jsonObject.getBoolean("investigation"))
                .medicine(jsonObject.getBoolean("medicine"))
                .nature(jsonObject.getBoolean("nature"))
                .perception(jsonObject.getBoolean("perception"))
                .performance(jsonObject.getBoolean("performance"))
                .persuasion(jsonObject.getBoolean("persuasion"))
                .religion(jsonObject.getBoolean("religion"))
                .sleightOfHand(jsonObject.getBoolean("sleightOfHand"))
                .stealth(jsonObject.getBoolean("stealth"))
                .survival(jsonObject.getBoolean("survival"))
                .build();
    }

    // EFFECTS: parses legendary mechanics from JSON object and returns it
    private LegendaryMechanics parseLegendaryMechanics(JSONObject jsonObject) {
        return new LegendaryMechanics(
                jsonObject.getString("legendaryDescription"),
                parseAbilities(jsonObject.getJSONArray("legendaryAbilities")));
    }
}
