package model;

import model.statblockfields.*;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StatBlockTest {
    // --------------------------------------------------------------------
    // statblock0 (base case)
    protected StatBlock statBlock0;

    protected final static int INTEGER_0 = 0;
    protected final static String STRING_0 = "";
    protected final static RollFormula ROLL_FORMULA_0 = new RollFormula(INTEGER_0, INTEGER_0, INTEGER_0);

    protected final static Title TITLE_0 = new Title.TitleBuilder("statblock zero", STRING_0, STRING_0, STRING_0).build();
    protected final static Armour ARMOUR_0 = new Armour.ArmourBuilder(INTEGER_0).build();
    protected final static Speeds SPEEDS_0 = new Speeds.SpeedsBuilder(INTEGER_0).build();
    protected final static Senses SENSES_0 = new Senses.SensesBuilder(INTEGER_0).build();
    protected final static AbilityScoreSet ABILITY_SCORES_0 = new AbilityScoreSet(INTEGER_0, INTEGER_0, INTEGER_0, INTEGER_0, INTEGER_0, INTEGER_0);

    protected List<RollableAction> actions0 = new ArrayList<>();

    // ---------------------------------------------- ----------------------
    // statblock1 (Deer case)
    protected StatBlock statBlock1;

    protected final static int XP_1 = 10;
    protected final static int PROFICIENCY_1 = 2;

    protected final static String ACTION_NAME_1 = "Bite";
    protected final static String ACTION_DESCRIPTION_1 = "Melee Weapon Attack";
    protected final static String ACTION_REACH_1 = "5";
    protected final static String DAMAGE_TYPE_1 = "Piercing";

    protected final static Title TITLE_1 = new Title.TitleBuilder("Deer", "Beast", "Medium", "unaligned").build();
    protected final static RollFormula HP_FORMULA_0 = new RollFormula(1, 8, 0);
    protected final static RollFormula HIT_FORMULA_1 = new RollFormula(1, 20, 2);
    protected final static RollFormula DAMAGE_FORMULA_1 = new RollFormula(1, 4, 0);
    protected final static Armour ARMOUR_1 = new Armour.ArmourBuilder(13).build();
    protected final static Speeds SPEEDS_1 = new Speeds.SpeedsBuilder(50).build();
    protected final static Senses SENSES_1 = new Senses.SensesBuilder(12).build();
    protected final static AbilityScoreSet ABILITY_SCORES_1 = new AbilityScoreSet(11, 16, 11, 2, 14, 5);

    protected RollableAction RollableAction1;
    protected List<RollableAction> actions1 = new ArrayList<>();
    protected HashMap<String, RollFormula> damageMap1 = new HashMap<>();

    // --------------------------------------------------------------------
    // statblock2 (max case)
    protected StatBlock statBlock2;

    protected final static int XP_2 = 160000;
    protected final static int PROFICIENCY_2 = 50;

    protected final static String ACTION_NAME_2 = "testAction2";
    protected final static String ACTION_DESCRIPTION_2 = "testActionDescription2";
    protected final static String ACTION_REACH_2 = "100/300";
    protected final static String DAMAGE_TYPE_2 = "testDamageType2";

    protected final static String ACTION_NAME_3 = "testAction3";
    protected final static String ACTION_DESCRIPTION_3 = "testActionDescription3";
    protected final static String ACTION_REACH_3 = "300/1000";
    protected final static String DAMAGE_TYPE_3 = "testDamageType3";

    protected final static String LANGUAGE_0 = "language0";
    protected final static String LANGUAGE_1 = "language1";

    protected final static String LEGENDARY_MECHANIC_DESCRIPTION_0 = "legendary mechanic description";

    protected final static Title TITLE_2 = new Title.TitleBuilder("statblock two", "type two", "size two", "alignment two").build();
    protected final static RollFormula HP_FORMULA_1 = new RollFormula(10, 40, 30);
    protected final static RollFormula HIT_FORMULA_2 = new RollFormula(30, 50, -30);
    protected final static RollFormula DAMAGE_FORMULA_2 = new RollFormula(12, 23, 24);
    protected final static RollFormula HIT_FORMULA_3 = new RollFormula(45, 2, -3);
    protected final static RollFormula DAMAGE_FORMULA_3 = new RollFormula(13, 8, 100);
    protected final static Armour ARMOUR_2 = new Armour
            .ArmourBuilder(200)
            .armourName("ridiculous armour")
            .magicArmour(900)
            .build();
    protected final static Speeds SPEEDS_2 = new Speeds
            .SpeedsBuilder(1000)
            .burrow(20)
            .climb(239)
            .fly(22)
            .swim(9)
            .build();
    protected final static Senses SENSES_2 = new Senses
            .SensesBuilder(200)
            .blindSight(499)
            .trueSight(990)
            .tremorSense(40)
            .darkVision(33)
            .build();
    protected final static AbilityScoreSet ABILITY_SCORES_2 = new AbilityScoreSet(30, 30, 30, 30, 30, 30);
    protected final static Ability ABILITY_0 = new Ability("ability zero", "ability description zero");
    protected final static Ability ABILITY_1 = new Ability("ability one", "ability description one");
    protected final static Ability LEGENDARY_ACTION_0 = new Ability("legendary action zero", "legendary action description zero");
    protected final static Ability LEGENDARY_ACTION_1 = new Ability("legendary action one", "legendary action description one");

    protected RollableAction RollableAction2;
    protected RollableAction RollableAction3;
    protected LegendaryMechanics legendaryMechanics0;

    protected List<RollableAction> actions2 = new ArrayList<>();
    protected List<String> languagesList0 = new ArrayList<>();
    protected List<Ability> abilities0 = new ArrayList<>();
    protected List<Ability> legendaryActions0 = new ArrayList<>();

    protected HashMap<String, RollFormula> damageMap2 = new HashMap<>();
    protected HashMap<String, RollFormula> damageMap3 = new HashMap<>();

    @BeforeEach
    public void beforeEach() {
        initializeStatBlock0();
        initializeStatBlock1();
        initializeStatBlock2();
    }

    // EFFECTS: builds statblock0 from constants
    protected void initializeStatBlock0() {
        statBlock0 = new StatBlock.StatBlockBuilder(
                TITLE_0,
                INTEGER_0, ROLL_FORMULA_0, INTEGER_0,
                ARMOUR_0, SPEEDS_0, SENSES_0,
                ABILITY_SCORES_0,
                actions0)
                .build();
    }

    // EFFECTS: builds statblock1 from constants
    protected void initializeStatBlock1() {
        damageMap1.put(DAMAGE_TYPE_1, DAMAGE_FORMULA_1);
        RollableAction1 = new RollableAction(ACTION_NAME_1, ACTION_DESCRIPTION_1, ACTION_REACH_1, HIT_FORMULA_1, damageMap1);
        actions1.add(RollableAction1);

        statBlock1 = new StatBlock.StatBlockBuilder(
                TITLE_1,
                XP_1, HP_FORMULA_0, PROFICIENCY_1,
                ARMOUR_1, SPEEDS_1, SENSES_1,
                ABILITY_SCORES_1,
                actions1)
                .build();
    }

    // EFFECTS: builds statblock2 from constants
    protected void initializeStatBlock2() {
        damageMap2.put(DAMAGE_TYPE_2, DAMAGE_FORMULA_2);
        damageMap3.put(DAMAGE_TYPE_3, DAMAGE_FORMULA_3);
        RollableAction2 = new RollableAction(ACTION_NAME_2, ACTION_DESCRIPTION_2, ACTION_REACH_2, HIT_FORMULA_2, damageMap2);
        RollableAction3 = new RollableAction(ACTION_NAME_3, ACTION_DESCRIPTION_3, ACTION_REACH_3, HIT_FORMULA_3, damageMap3);
        actions2.add(RollableAction2);
        actions2.add(RollableAction3);

        languagesList0.add(LANGUAGE_0);
        languagesList0.add(LANGUAGE_1);

        abilities0.add(ABILITY_0);
        abilities0.add(ABILITY_1);

        legendaryActions0.add(LEGENDARY_ACTION_0);
        legendaryActions0.add(LEGENDARY_ACTION_1);
        legendaryMechanics0 = new LegendaryMechanics(LEGENDARY_MECHANIC_DESCRIPTION_0, legendaryActions0);

        statBlock2 = new StatBlock.StatBlockBuilder(
                TITLE_2,
                XP_2, HP_FORMULA_1, PROFICIENCY_2,
                ARMOUR_2, SPEEDS_2, SENSES_2,
                ABILITY_SCORES_2,
                actions2)
                .languages(new Languages.LanguagesBuilder(languagesList0).build())
                .abilities(abilities0)
                .skillProficiencies(getAllSkillProficiencies())
                .savingThrowProficiencies(getAllSavingThrowProficiencies())
                .conditionImmunities(getAllConditionImmunities())
                .resistances(getAllResistances())
                .legendaryMechanics(legendaryMechanics0)
                .build();
    }

    // EFFECTS: returns a hashmap representing all possible resistance types
    protected HashMap<String, String> getAllResistances() {
        HashMap<String, String> resistances = new HashMap<>();
        resistances.put("acid", "resistance");
        resistances.put("bludgeoning", "resistance");
        resistances.put("cold", "resistance");
        resistances.put("fire", "immunity");
        resistances.put("force", "immunity");
        resistances.put("lightning", "vulnerability");
        resistances.put("necrotic", "resistance");
        resistances.put("piercing", "resistance");
        resistances.put("poison", "vulnerability");
        resistances.put("psychic", "resistance");
        resistances.put("radiant", "resistance");
        resistances.put("slashing", "immunity");
        resistances.put("thunder", "resistance");
        return resistances;
    }

    // EFFECTS: returns a list representing all possible saving throw proficiencies
    protected List<String> getAllSavingThrowProficiencies() {
        List<String> savingThrows = new ArrayList<>();
        savingThrows.add("Strength");
        savingThrows.add("Dexterity");
        savingThrows.add("Constitution");
        savingThrows.add("Intelligence");
        savingThrows.add("Wisdom");
        savingThrows.add("Charisma");
        return savingThrows;
    }

    // EFFECTS: returns a list representing all possible skill proficiencies
    protected List<String> getAllSkillProficiencies() {
        List<String> skillProficiencies = new ArrayList<>();
        skillProficiencies.add("acrobatics");
        skillProficiencies.add("sleightOfHand");
        skillProficiencies.add("stealth");
        skillProficiencies.add("arcana");
        skillProficiencies.add("history");
        skillProficiencies.add("religion");
        skillProficiencies.add("investigation");
        skillProficiencies.add("nature");
        skillProficiencies.add("athletics");
        skillProficiencies.add("deception");
        skillProficiencies.add("intimidation");
        skillProficiencies.add("performance");
        skillProficiencies.add("persuasion");
        skillProficiencies.add("animalHandling");
        skillProficiencies.add("insight");
        skillProficiencies.add("medicine");
        skillProficiencies.add("perception");
        skillProficiencies.add("survival");
        return skillProficiencies;
    }

    // EFFECTS: returns a list representing all possible condition immunities
    protected List<String> getAllConditionImmunities() {
        List<String> conditionImmunities = new ArrayList<>();
        conditionImmunities.add("blinded");
        conditionImmunities.add("charmed");
        conditionImmunities.add("deafened");
        conditionImmunities.add("exhaustion");
        conditionImmunities.add("frightened");
        conditionImmunities.add("grappled");
        conditionImmunities.add("incapacitated");
        conditionImmunities.add("invisible");
        conditionImmunities.add("paralyzed");
        conditionImmunities.add("petrified");
        conditionImmunities.add("poisoned");
        conditionImmunities.add("prone");
        conditionImmunities.add("restrained");
        conditionImmunities.add("stunned");
        conditionImmunities.add("unconscious");
        return conditionImmunities;
    }

    @Test
    public void testConstructor0() {
        assertEquals(TITLE_0, statBlock0.getTitle());
        assertEquals(ROLL_FORMULA_0, statBlock0.getHPFormula());
        assertEquals(INTEGER_0, statBlock0.getXP());
        assertEquals(INTEGER_0, statBlock0.getProficiency());

        assertEquals(ARMOUR_0, statBlock0.getArmour());
        assertEquals(SPEEDS_0, statBlock0.getSpeeds());
        assertEquals(SENSES_0, statBlock0.getSenses());
        assertEquals(ABILITY_SCORES_0, statBlock0.getAbilityScores());

        assertTrue(statBlock0.getRollableActions().isEmpty());
    }

    @Test
    public void testConstructor1() {
        assertEquals(TITLE_1, statBlock1.getTitle());
        assertEquals(HP_FORMULA_0, statBlock1.getHPFormula());
        assertEquals(XP_1, statBlock1.getXP());
        assertEquals(PROFICIENCY_1, statBlock1.getProficiency());

        assertEquals(ARMOUR_1, statBlock1.getArmour());
        assertEquals(SPEEDS_1, statBlock1.getSpeeds());
        assertEquals(SENSES_1, statBlock1.getSenses());
        assertEquals(ABILITY_SCORES_1, statBlock1.getAbilityScores());

        assertEquals(1, statBlock1.getRollableActions().size());

        assertEquals(ACTION_NAME_1, statBlock1.getRollableActions().get(0).getName());
        assertEquals(ACTION_DESCRIPTION_1, statBlock1.getRollableActions().get(0).getDescription());
        assertEquals(ACTION_REACH_1, statBlock1.getRollableActions().get(0).getRange());
        assertEquals(HIT_FORMULA_1, statBlock1.getRollableActions().get(0).getHitFormula());
        assertEquals(DAMAGE_FORMULA_1, statBlock1.getRollableActions().get(0).getDamageMap().get(DAMAGE_TYPE_1));
    }

    @Test
    public void testConstructor2() {
        assertEquals(TITLE_2, statBlock2.getTitle());
        assertEquals(HP_FORMULA_1, statBlock2.getHPFormula());
        assertEquals(XP_2, statBlock2.getXP());
        assertEquals(PROFICIENCY_2, statBlock2.getProficiency());

        assertEquals(ARMOUR_2, statBlock2.getArmour());
        assertEquals(SPEEDS_2, statBlock2.getSpeeds());
        assertEquals(SENSES_2, statBlock2.getSenses());
        assertEquals(ABILITY_SCORES_2, statBlock2.getAbilityScores());

        assertEquals(2, statBlock2.getRollableActions().size());

        assertEquals(ACTION_NAME_2, statBlock2.getRollableActions().get(0).getName());
        assertEquals(ACTION_DESCRIPTION_2, statBlock2.getRollableActions().get(0).getDescription());
        assertEquals(ACTION_REACH_2, statBlock2.getRollableActions().get(0).getRange());
        assertEquals(HIT_FORMULA_2, statBlock2.getRollableActions().get(0).getHitFormula());
        assertEquals(DAMAGE_FORMULA_2, statBlock2.getRollableActions().get(0).getDamageMap().get(DAMAGE_TYPE_2));

        assertEquals(ACTION_NAME_3, statBlock2.getRollableActions().get(1).getName());
        assertEquals(ACTION_DESCRIPTION_3, statBlock2.getRollableActions().get(1).getDescription());
        assertEquals(ACTION_REACH_3, statBlock2.getRollableActions().get(1).getRange());
        assertEquals(HIT_FORMULA_3, statBlock2.getRollableActions().get(1).getHitFormula());
        assertEquals(DAMAGE_FORMULA_3, statBlock2.getRollableActions().get(1).getDamageMap().get(DAMAGE_TYPE_3));

        assertEquals(2, statBlock2.getLanguages().getLanguagesList().size());
        assertEquals(LANGUAGE_0, statBlock2.getLanguages().getLanguagesList().get(0));
        assertEquals(LANGUAGE_1, statBlock2.getLanguages().getLanguagesList().get(1));

        assertEquals(2, statBlock2.getAbilities().size());
        assertEquals(ABILITY_0, statBlock2.getAbilities().get(0));
        assertEquals(ABILITY_1, statBlock2.getAbilities().get(1));

        assertEquals(18, statBlock2.getSkillProficiencies().size());
        assertEquals(15, statBlock2.getConditionImmunities().size());
        assertEquals(6, statBlock2.getSavingThrowProficiencies().size());
        assertEquals(13, statBlock2.getResistances().size());

        assertEquals(2, statBlock2.getLegendaryMechanics().getLegendaryActions().size());
        assertEquals(LEGENDARY_MECHANIC_DESCRIPTION_0, statBlock2.getLegendaryMechanics().getLegendaryDescription());
        assertEquals(LEGENDARY_ACTION_0, statBlock2.getLegendaryMechanics().getLegendaryActions().get(0));
        assertEquals(LEGENDARY_ACTION_1, statBlock2.getLegendaryMechanics().getLegendaryActions().get(1));
    }

    @Test
    public void testGetChallengeRating() {
        assertEquals("0", statBlock0.getChallengeRating());
        assertEquals("0", statBlock1.getChallengeRating());
        assertEquals("30+", statBlock2.getChallengeRating());
    }

    @Test
    public void testGetSavingThrowProficienciesString() {
        String testString =  "Strength " + (statBlock2.getAbilityScores().toModifier("Strength") + PROFICIENCY_2)
                + ", Dexterity " + (statBlock2.getAbilityScores().toModifier("Dexterity") + PROFICIENCY_2)
                + ", Constitution " + (statBlock2.getAbilityScores().toModifier("Constitution") + PROFICIENCY_2)
                + ", Intelligence " + (statBlock2.getAbilityScores().toModifier("Intelligence") + PROFICIENCY_2)
                + ", Wisdom " + (statBlock2.getAbilityScores().toModifier("Wisdom") + PROFICIENCY_2)
                + ", Charisma " + (statBlock2.getAbilityScores().toModifier("Charisma") + PROFICIENCY_2)
                + ", ";

        assertEquals("", statBlock0.getSavingThrowProficienciesString());
        assertEquals("", statBlock1.getSavingThrowProficienciesString());
        assertEquals(testString, statBlock2.getSavingThrowProficienciesString());
    }

    @Test
    public void testGetSkillProficienciesString() {
        String testString = "acrobatics " + (statBlock2.getAbilityScores().toModifier("dexterity") + PROFICIENCY_2)
                + ", sleightOfHand " + (statBlock2.getAbilityScores().toModifier("Dexterity") + PROFICIENCY_2)
                + ", stealth " + (statBlock2.getAbilityScores().toModifier("Dexterity") + PROFICIENCY_2)
                + ", arcana " + (statBlock2.getAbilityScores().toModifier("intelligence") + PROFICIENCY_2)
                + ", history " + (statBlock2.getAbilityScores().toModifier("intelligence") + PROFICIENCY_2)
                + ", religion " + (statBlock2.getAbilityScores().toModifier("intelligence") + PROFICIENCY_2)
                + ", investigation " + (statBlock2.getAbilityScores().toModifier("intelligence") + PROFICIENCY_2)
                + ", nature " + (statBlock2.getAbilityScores().toModifier("intelligence") + PROFICIENCY_2)
                + ", athletics " + (statBlock2.getAbilityScores().toModifier("strength") + PROFICIENCY_2)
                + ", deception " + (statBlock2.getAbilityScores().toModifier("charisma") + PROFICIENCY_2)
                + ", intimidation " + (statBlock2.getAbilityScores().toModifier("charisma") + PROFICIENCY_2)
                + ", performance " + (statBlock2.getAbilityScores().toModifier("charisma") + PROFICIENCY_2)
                + ", persuasion " + (statBlock2.getAbilityScores().toModifier("charisma") + PROFICIENCY_2)
                + ", animalHandling " + (statBlock2.getAbilityScores().toModifier("wisdom") + PROFICIENCY_2)
                + ", insight " + (statBlock2.getAbilityScores().toModifier("wisdom") + PROFICIENCY_2)
                + ", medicine " + (statBlock2.getAbilityScores().toModifier("wisdom") + PROFICIENCY_2)
                + ", perception " + (statBlock2.getAbilityScores().toModifier("wisdom") + PROFICIENCY_2)
                + ", survival " + (statBlock2.getAbilityScores().toModifier("wisdom") + PROFICIENCY_2)
                + ", ";

        assertEquals("", statBlock0.getSkillProficienciesString());
        assertEquals("", statBlock1.getSkillProficienciesString());
        assertEquals(testString, statBlock2.getSkillProficienciesString());
    }

    @Test
    public void testGetConditionImmunitiesString() {
        String testString = "blinded, charmed, deafened, exhaustion, frightened, grappled, incapacitated, invisible, "
                + "paralyzed, petrified, poisoned, prone, restrained, stunned, unconscious, ";

        assertEquals("", statBlock0.getConditionImmunitiesString());
        assertEquals("", statBlock1.getConditionImmunitiesString());
        assertEquals(testString, statBlock2.getConditionImmunitiesString());
    }

    @Test
    public void testGetResistancesString() {
        String testString = statBlock2.getResistancesString();

        assertEquals("", statBlock0.getResistancesString());
        assertEquals("", statBlock1.getResistancesString());
        
        assertTrue(testString.contains("acid resistance, "));
        assertTrue(testString.contains("bludgeoning resistance, "));
        assertTrue(testString.contains("fire immunity, "));
        assertTrue(testString.contains("force immunity, "));
        assertTrue(testString.contains("lightning vulnerability, "));
        assertTrue(testString.contains("necrotic resistance, "));
        assertTrue(testString.contains("piercing resistance, "));
        assertTrue(testString.contains("poison vulnerability, "));
        assertTrue(testString.contains("psychic resistance, "));
        assertTrue(testString.contains("radiant resistance, "));
        assertTrue(testString.contains("slashing immunity, "));
        assertTrue(testString.contains("thunder resistance, "));
    }

    @Test
    public void testToJson0() {
        JSONObject json = statBlock0.toJson();

        assertEquals(TITLE_0.getName(), json.getJSONObject("title").get("name"));
        assertEquals(STRING_0, json.getJSONObject("title").get("type"));
        assertEquals(STRING_0, json.getJSONObject("title").get("size"));
        assertEquals(STRING_0, json.getJSONObject("title").get("alignment"));
        assertFalse(json.getJSONObject("title").has("group"));

        assertEquals(INTEGER_0, json.get("xp"));
        assertEquals(INTEGER_0, json.getJSONObject("hpFormula").get("amountOfDice"));
        assertEquals(INTEGER_0, json.getJSONObject("hpFormula").get("dieSides"));
        assertEquals(INTEGER_0, json.getJSONObject("hpFormula").get("modifier"));
        assertEquals(INTEGER_0, json.get("proficiency"));

        assertEquals(INTEGER_0, json.getJSONObject("armour").get("ac"));
        assertEquals(INTEGER_0, json.getJSONObject("speeds").get("speed"));
        assertEquals(INTEGER_0, json.getJSONObject("senses").get("passivePerception"));

        assertEquals(INTEGER_0, json.getJSONObject("abilityScores").get("strength"));
        assertEquals(INTEGER_0, json.getJSONObject("abilityScores").get("dexterity"));
        assertEquals(INTEGER_0, json.getJSONObject("abilityScores").get("constitution"));
        assertEquals(INTEGER_0, json.getJSONObject("abilityScores").get("intelligence"));
        assertEquals(INTEGER_0, json.getJSONObject("abilityScores").get("wisdom"));
        assertEquals(INTEGER_0, json.getJSONObject("abilityScores").get("charisma"));

        assertTrue(json.getJSONArray("actions").isEmpty());
    }

    @Test
    public void testToJson1() {
        JSONObject json = statBlock1.toJson();

        assertEquals(TITLE_1.getName(), json.getJSONObject("title").get("name"));
        assertEquals(TITLE_1.getType(), json.getJSONObject("title").get("type"));
        assertEquals(TITLE_1.getSize(), json.getJSONObject("title").get("size"));
        assertEquals(TITLE_1.getAlignment(), json.getJSONObject("title").get("alignment"));
        assertFalse(json.getJSONObject("title").has("group"));

        assertEquals(XP_1, json.get("xp"));
        assertEquals(HP_FORMULA_0.getAmountOfDice(), json.getJSONObject("hpFormula").get("amountOfDice"));
        assertEquals(HP_FORMULA_0.getDieSides(), json.getJSONObject("hpFormula").get("dieSides"));
        assertEquals(HP_FORMULA_0.getModifier(), json.getJSONObject("hpFormula").get("modifier"));
        assertEquals(PROFICIENCY_1, json.get("proficiency"));

        assertEquals(ARMOUR_1.getAC(), json.getJSONObject("armour").get("ac"));
        assertEquals(SPEEDS_1.getSpeed(), json.getJSONObject("speeds").get("speed"));
        assertEquals(SENSES_1.getPassivePerception(), json.getJSONObject("senses").get("passivePerception"));

        assertEquals(ABILITY_SCORES_1.getStrength(), json.getJSONObject("abilityScores").get("strength"));
        assertEquals(ABILITY_SCORES_1.getDexterity(), json.getJSONObject("abilityScores").get("dexterity"));
        assertEquals(ABILITY_SCORES_1.getConstitution(), json.getJSONObject("abilityScores").get("constitution"));
        assertEquals(ABILITY_SCORES_1.getIntelligence(), json.getJSONObject("abilityScores").get("intelligence"));
        assertEquals(ABILITY_SCORES_1.getWisdom(), json.getJSONObject("abilityScores").get("wisdom"));
        assertEquals(ABILITY_SCORES_1.getCharisma(), json.getJSONObject("abilityScores").get("charisma"));

        assertEquals(1, json.getJSONArray("actions").length());
        assertEquals(HIT_FORMULA_1.getAmountOfDice(), json.getJSONArray("actions").getJSONObject(0)
                .getJSONObject("hitFormula").get("amountOfDice"));
        assertEquals(HIT_FORMULA_1.getDieSides(), json.getJSONArray("actions").getJSONObject(0)
                .getJSONObject("hitFormula").get("dieSides"));
        assertEquals(HIT_FORMULA_1.getModifier(), json.getJSONArray("actions").getJSONObject(0)
                .getJSONObject("hitFormula").get("modifier"));
        assertEquals(DAMAGE_FORMULA_1.getAmountOfDice(), json.getJSONArray("actions").getJSONObject(0)
                .getJSONObject("damageMap").getJSONObject(DAMAGE_TYPE_1).get("amountOfDice"));
        assertEquals(DAMAGE_FORMULA_1.getDieSides(), json.getJSONArray("actions").getJSONObject(0)
                .getJSONObject("damageMap").getJSONObject(DAMAGE_TYPE_1).get("dieSides"));
        assertEquals(DAMAGE_FORMULA_1.getModifier(), json.getJSONArray("actions").getJSONObject(0)
                .getJSONObject("damageMap").getJSONObject(DAMAGE_TYPE_1).get("modifier"));
    }

    @Test
    public void testToJson2() {
        JSONObject json = statBlock2.toJson();

        assertEquals(TITLE_2.getName(), json.getJSONObject("title").get("name"));
        assertEquals(TITLE_2.getType(), json.getJSONObject("title").get("type"));
        assertEquals(TITLE_2.getSize(), json.getJSONObject("title").get("size"));
        assertEquals(TITLE_2.getAlignment(), json.getJSONObject("title").get("alignment"));
        assertFalse(json.getJSONObject("title").has("group"));

        assertEquals(XP_2, json.get("xp"));
        assertEquals(HP_FORMULA_1.getAmountOfDice(), json.getJSONObject("hpFormula").get("amountOfDice"));
        assertEquals(HP_FORMULA_1.getDieSides(), json.getJSONObject("hpFormula").get("dieSides"));
        assertEquals(HP_FORMULA_1.getModifier(), json.getJSONObject("hpFormula").get("modifier"));
        assertEquals(PROFICIENCY_2, json.get("proficiency"));

        assertEquals(ARMOUR_2.getAC(), json.getJSONObject("armour").get("ac"));
        assertEquals(SPEEDS_2.getSpeed(), json.getJSONObject("speeds").get("speed"));
        assertEquals(SENSES_2.getPassivePerception(), json.getJSONObject("senses").get("passivePerception"));

        assertEquals(ABILITY_SCORES_2.getStrength(), json.getJSONObject("abilityScores").get("strength"));
        assertEquals(ABILITY_SCORES_2.getDexterity(), json.getJSONObject("abilityScores").get("dexterity"));
        assertEquals(ABILITY_SCORES_2.getConstitution(), json.getJSONObject("abilityScores").get("constitution"));
        assertEquals(ABILITY_SCORES_2.getIntelligence(), json.getJSONObject("abilityScores").get("intelligence"));
        assertEquals(ABILITY_SCORES_2.getWisdom(), json.getJSONObject("abilityScores").get("wisdom"));
        assertEquals(ABILITY_SCORES_2.getCharisma(), json.getJSONObject("abilityScores").get("charisma"));

        assertEquals(2, json.getJSONArray("actions").length());
        assertEquals(HIT_FORMULA_2.getAmountOfDice(), json.getJSONArray("actions").getJSONObject(0)
                .getJSONObject("hitFormula").get("amountOfDice"));
        assertEquals(HIT_FORMULA_2.getDieSides(), json.getJSONArray("actions").getJSONObject(0)
                .getJSONObject("hitFormula").get("dieSides"));
        assertEquals(HIT_FORMULA_2.getModifier(), json.getJSONArray("actions").getJSONObject(0)
                .getJSONObject("hitFormula").get("modifier"));
        assertEquals(DAMAGE_FORMULA_2.getAmountOfDice(), json.getJSONArray("actions").getJSONObject(0)
                .getJSONObject("damageMap").getJSONObject(DAMAGE_TYPE_2).get("amountOfDice"));
        assertEquals(DAMAGE_FORMULA_2.getDieSides(), json.getJSONArray("actions").getJSONObject(0)
                .getJSONObject("damageMap").getJSONObject(DAMAGE_TYPE_2).get("dieSides"));
        assertEquals(DAMAGE_FORMULA_2.getModifier(), json.getJSONArray("actions").getJSONObject(0)
                .getJSONObject("damageMap").getJSONObject(DAMAGE_TYPE_2).get("modifier"));

        assertEquals(HIT_FORMULA_3.getAmountOfDice(), json.getJSONArray("actions").getJSONObject(1)
                .getJSONObject("hitFormula").get("amountOfDice"));
        assertEquals(HIT_FORMULA_3.getDieSides(), json.getJSONArray("actions").getJSONObject(1)
                .getJSONObject("hitFormula").get("dieSides"));
        assertEquals(HIT_FORMULA_3.getModifier(), json.getJSONArray("actions").getJSONObject(1)
                .getJSONObject("hitFormula").get("modifier"));
        assertEquals(DAMAGE_FORMULA_3.getAmountOfDice(), json.getJSONArray("actions").getJSONObject(1)
                .getJSONObject("damageMap").getJSONObject(DAMAGE_TYPE_3).get("amountOfDice"));
        assertEquals(DAMAGE_FORMULA_3.getDieSides(), json.getJSONArray("actions").getJSONObject(1)
                .getJSONObject("damageMap").getJSONObject(DAMAGE_TYPE_3).get("dieSides"));
        assertEquals(DAMAGE_FORMULA_3.getModifier(), json.getJSONArray("actions").getJSONObject(1)
                .getJSONObject("damageMap").getJSONObject(DAMAGE_TYPE_3).get("modifier"));
    }
}
