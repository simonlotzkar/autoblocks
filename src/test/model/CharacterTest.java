package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest extends StatBlockTest {
    private Character statBlock0Character0;

    private Character statBlock1Character0;
    private Character statBlock1Character1;
    private Character statBlock1Character2;

    private Character statBlock2Character0;
    private Character statBlock2Character1;
    private Character statBlock2Character2;

    private static final String GROUP_0 = "group-1";

    private final List<Character> emptyEncounter = new ArrayList<>();
    private final List<Character> notSequentialEncounter = new ArrayList<>();
    private final List<Character> sequentialEncounter = new ArrayList<>();

    @Override
    @BeforeEach
    public void beforeEach() {
        initializeStatBlock0();
        initializeStatBlock1();
        initializeStatBlock2();

        statBlock0Character0 = new Character(statBlock0, TITLE_0.getName() + " 1");

        statBlock1Character0 = new Character(statBlock1, TITLE_1.getName() + " 1");
        statBlock1Character1 = new Character(statBlock1, TITLE_1.getName() + " 2");
        statBlock1Character2 = new Character(statBlock1, TITLE_1.getName() + " 3");

        statBlock2Character0 = new Character(statBlock2, TITLE_2.getName() + " 1");
        statBlock2Character0.getTitle().setGroup(GROUP_0);
        statBlock2Character1 = new Character(statBlock2, TITLE_2.getName() + " 2");
        statBlock2Character1.getTitle().setGroup(GROUP_0);
        statBlock2Character2 = new Character(statBlock2, TITLE_2.getName() + " 3");
        statBlock2Character2.getTitle().setGroup(GROUP_0);

        sequentialEncounter.add(statBlock0Character0);
        sequentialEncounter.add(statBlock1Character0);
        sequentialEncounter.add(statBlock1Character1);
        sequentialEncounter.add(statBlock1Character2);
        sequentialEncounter.add(statBlock2Character0);
        sequentialEncounter.add(statBlock2Character1);
        sequentialEncounter.add(statBlock2Character2);

        notSequentialEncounter.add(statBlock0Character0);
        notSequentialEncounter.add(statBlock1Character0);
        notSequentialEncounter.add(statBlock1Character2);
        notSequentialEncounter.add(statBlock2Character0);
        notSequentialEncounter.add(statBlock2Character2);
    }

    @Override
    @Test
    public void testConstructor0() {
        assertEquals(statBlock0, statBlock0Character0.getParentStatBlock());
        assertEquals(statBlock0Character0.getHP(), statBlock0Character0.getMaxHP());

        assertEquals(TITLE_0.getName() + " 1", statBlock0Character0.getTitle().getName());
        assertEquals(TITLE_0.getType(), statBlock0Character0.getTitle().getType());
        assertEquals(TITLE_0.getSize(), statBlock0Character0.getTitle().getSize());
        assertEquals(TITLE_0.getAlignment(), statBlock0Character0.getTitle().getAlignment());
        assertNull(statBlock0Character0.getTitle().getGroup());

        assertEquals(ROLL_FORMULA_0, statBlock0Character0.getHPFormula());
        assertEquals(INTEGER_0, statBlock0Character0.getXP());
        assertEquals(INTEGER_0, statBlock0Character0.getProficiency());

        assertEquals(ARMOUR_0, statBlock0Character0.getArmour());
        assertEquals(SPEEDS_0, statBlock0Character0.getSpeeds());
        assertEquals(SENSES_0, statBlock0Character0.getSenses());
        assertEquals(ABILITY_SCORES_0, statBlock0Character0.getAbilityScores());

        assertTrue(statBlock0Character0.getActions().isEmpty());
    }

    @Override
    @Test
    public void testConstructor1() {
        assertEquals(statBlock1, statBlock1Character0.getParentStatBlock());
        assertEquals(statBlock1Character0.getHP(), statBlock1Character0.getMaxHP());

        assertEquals(TITLE_1.getName() + " 1", statBlock1Character0.getTitle().getName());
        assertEquals(TITLE_1.getType(), statBlock1Character0.getTitle().getType());
        assertEquals(TITLE_1.getSize(), statBlock1Character0.getTitle().getSize());
        assertEquals(TITLE_1.getAlignment(), statBlock1Character0.getTitle().getAlignment());
        assertNull(statBlock1Character0.getTitle().getGroup());

        assertEquals(HP_FORMULA_0, statBlock1Character0.getHPFormula());
        assertEquals(XP_1, statBlock1Character0.getXP());
        assertEquals(PROFICIENCY_1, statBlock1Character0.getProficiency());

        assertEquals(ARMOUR_1, statBlock1Character0.getArmour());
        assertEquals(SPEEDS_1, statBlock1Character0.getSpeeds());
        assertEquals(SENSES_1, statBlock1Character0.getSenses());
        assertEquals(ABILITY_SCORES_1, statBlock1Character0.getAbilityScores());

        assertEquals(1, statBlock1Character0.getActions().size());

        assertEquals(ACTION_NAME_1, statBlock1Character0.getActions().get(0).getName());
        assertEquals(ACTION_DESCRIPTION_1, statBlock1Character0.getActions().get(0).getDescription());
        assertEquals(ACTION_REACH_1, statBlock1Character0.getActions().get(0).getReach());
        assertEquals(HIT_FORMULA_1, statBlock1Character0.getActions().get(0).getHitFormula());
        assertEquals(DAMAGE_FORMULA_1, statBlock1Character0.getActions().get(0).getDamageMap().get(DAMAGE_TYPE_1));
    }

    @Override
    @Test
    public void testConstructor2() {
        assertEquals(statBlock2, statBlock2Character0.getParentStatBlock());
        assertEquals(statBlock2Character0.getHP(), statBlock2Character0.getMaxHP());

        assertEquals(TITLE_2.getName() + " 1", statBlock2Character0.getTitle().getName());
        assertEquals(TITLE_2.getType(), statBlock2Character0.getTitle().getType());
        assertEquals(TITLE_2.getSize(), statBlock2Character0.getTitle().getSize());
        assertEquals(TITLE_2.getAlignment(), statBlock2Character0.getTitle().getAlignment());
        assertEquals(GROUP_0, statBlock2Character0.getTitle().getGroup());

        assertEquals(HP_FORMULA_1, statBlock2Character0.getHPFormula());
        assertEquals(XP_2, statBlock2Character0.getXP());
        assertEquals(PROFICIENCY_2, statBlock2Character0.getProficiency());

        assertEquals(ARMOUR_2, statBlock2Character0.getArmour());
        assertEquals(SPEEDS_2, statBlock2Character0.getSpeeds());
        assertEquals(SENSES_2, statBlock2Character0.getSenses());
        assertEquals(ABILITY_SCORES_2, statBlock2Character0.getAbilityScores());

        assertEquals(2, statBlock2Character0.getActions().size());

        assertEquals(ACTION_NAME_2, statBlock2Character0.getActions().get(0).getName());
        assertEquals(ACTION_DESCRIPTION_2, statBlock2Character0.getActions().get(0).getDescription());
        assertEquals(ACTION_REACH_2, statBlock2Character0.getActions().get(0).getReach());
        assertEquals(HIT_FORMULA_2, statBlock2Character0.getActions().get(0).getHitFormula());
        assertEquals(DAMAGE_FORMULA_2, statBlock2Character0.getActions().get(0).getDamageMap().get(DAMAGE_TYPE_2));

        assertEquals(ACTION_NAME_3, statBlock2Character0.getActions().get(1).getName());
        assertEquals(ACTION_DESCRIPTION_3, statBlock2Character0.getActions().get(1).getDescription());
        assertEquals(ACTION_REACH_3, statBlock2Character0.getActions().get(1).getReach());
        assertEquals(HIT_FORMULA_3, statBlock2Character0.getActions().get(1).getHitFormula());
        assertEquals(DAMAGE_FORMULA_3, statBlock2Character0.getActions().get(1).getDamageMap().get(DAMAGE_TYPE_3));

        assertEquals(2, statBlock2Character0.getLanguages().getLanguagesList().size());
        assertEquals(LANGUAGE_0, statBlock2Character0.getLanguages().getLanguagesList().get(0));
        assertEquals(LANGUAGE_1, statBlock2Character0.getLanguages().getLanguagesList().get(1));

        assertEquals(2, statBlock2Character0.getAbilities().size());
        assertEquals(ABILITY_0, statBlock2Character0.getAbilities().get(0));
        assertEquals(ABILITY_1, statBlock2Character0.getAbilities().get(1));

        assertEquals(18, statBlock2Character0.getSkillProficiencies().size());
        assertEquals(15, statBlock2Character0.getConditionImmunities().size());
        assertEquals(6, statBlock2Character0.getSavingThrowProficiencies().size());
        assertEquals(13, statBlock2Character0.getResistances().size());

        assertEquals(2, statBlock2Character0.getLegendaryMechanics().getLegendaryActions().size());
        assertEquals(LEGENDARY_MECHANIC_DESCRIPTION_0, statBlock2Character0.getLegendaryMechanics().getLegendaryDescription());
        assertEquals(LEGENDARY_ACTION_0, statBlock2Character0.getLegendaryMechanics().getLegendaryActions().get(0));
        assertEquals(LEGENDARY_ACTION_1, statBlock2Character0.getLegendaryMechanics().getLegendaryActions().get(1));
    }

    @Test
    public void testGenerateNameForEncounterBaseCase() {
        assertEquals(statBlock0.getTitle().getName() + " 1",
                Character.generateNameForEncounter(statBlock0, emptyEncounter));
        assertEquals(statBlock1.getTitle().getName() + " 1",
                Character.generateNameForEncounter(statBlock1, emptyEncounter));
        assertEquals(statBlock2.getTitle().getName() + " 1",
                Character.generateNameForEncounter(statBlock2, emptyEncounter));
    }

    @Test
    public void testGenerateNameForEncounterSequentialCase() {
        assertEquals(statBlock0.getTitle().getName() + " 2",
                Character.generateNameForEncounter(statBlock0, sequentialEncounter));
        assertEquals(statBlock1.getTitle().getName() + " 4",
                Character.generateNameForEncounter(statBlock1, sequentialEncounter));
        assertEquals(statBlock2.getTitle().getName() + " 4",
                Character.generateNameForEncounter(statBlock2, sequentialEncounter));
    }

    @Test
    public void testGenerateNameForEncounterNotSequentialCase() {
        assertEquals(statBlock0.getTitle().getName() + " 2",
                Character.generateNameForEncounter(statBlock0, notSequentialEncounter));
        assertEquals(statBlock1.getTitle().getName() + " 2",
                Character.generateNameForEncounter(statBlock1, notSequentialEncounter));
        assertEquals(statBlock2.getTitle().getName() + " 2",
                Character.generateNameForEncounter(statBlock2, notSequentialEncounter));
    }

    @Test
    public void testChangeHPBaseCase() {
        statBlock1Character0.changeHP(1 - statBlock1Character0.getHP());
        statBlock2Character0.changeHP(1 - statBlock2Character0.getHP());

        assertEquals(1, statBlock1Character0.getHP());
        assertEquals(1, statBlock2Character0.getHP());
    }

    @Test
    public void testChangeHPOverMaxCase() {
        int maxHP0 = statBlock1Character0.getMaxHP();
        int maxHP1 = statBlock2Character0.getMaxHP();

        statBlock1Character0.changeHP(maxHP0);
        statBlock2Character0.changeHP(maxHP1);

        assertEquals(maxHP0, statBlock1Character0.getHP());
        assertEquals(maxHP1, statBlock2Character0.getHP());
    }

    @Test
    public void testChangeHPNegativeCase() {
        statBlock1Character0.changeHP(-5 - statBlock1Character0.getHP());
        statBlock2Character0.changeHP(-5 - statBlock2Character0.getHP());

        assertEquals(-5, statBlock1Character0.getHP());
        assertEquals(-5, statBlock2Character0.getHP());
    }

    @Test
    public void testGetHPString() {
        String hpString0 = statBlock0Character0.getHP() + "/" + statBlock0Character0.getMaxHP();

        String hpString1 = statBlock1Character0.getHP() + "/" + statBlock1Character0.getMaxHP();
        String hpString2 = statBlock1Character1.getHP() + "/" + statBlock1Character1.getMaxHP();
        String hpString3 = statBlock1Character2.getHP() + "/" + statBlock1Character2.getMaxHP();

        String hpString4 = statBlock2Character0.getHP() + "/" + statBlock2Character0.getMaxHP();
        String hpString5 = statBlock2Character1.getHP() + "/" + statBlock2Character1.getMaxHP();
        String hpString6 = statBlock2Character2.getHP() + "/" + statBlock2Character2.getMaxHP();

        assertEquals(hpString0, statBlock0Character0.getHPString());

        assertEquals(hpString1, statBlock1Character0.getHPString());
        assertEquals(hpString2, statBlock1Character1.getHPString());
        assertEquals(hpString3, statBlock1Character2.getHPString());

        assertEquals(hpString4, statBlock2Character0.getHPString());
        assertEquals(hpString5, statBlock2Character1.getHPString());
        assertEquals(hpString6, statBlock2Character2.getHPString());
    }

    @Override
    @Test
    public void testToJson0() {
        JSONObject json = statBlock0Character0.toJson();

        assertTrue(json.has("parentStatBlock"));
        assertEquals(INTEGER_0, json.getInt("hp"));
        assertEquals(INTEGER_0, json.getInt("maxHP"));

        assertEquals(TITLE_0.getName() + " 1", json.getJSONObject("title").get("name"));
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

    @Override
    @Test
    public void testToJson1() {
        JSONObject json = statBlock1Character0.toJson();

        assertTrue(json.has("parentStatBlock"));
        assertEquals(statBlock1Character0.getHP(), json.getInt("hp"));
        assertEquals(statBlock1Character0.getMaxHP(), json.getInt("maxHP"));

        assertEquals(statBlock1Character0.getTitle().getName(), json.getJSONObject("title").get("name"));
        assertEquals(statBlock1Character0.getTitle().getType(), json.getJSONObject("title").get("type"));
        assertEquals(statBlock1Character0.getTitle().getSize(), json.getJSONObject("title").get("size"));
        assertEquals(statBlock1Character0.getTitle().getAlignment(), json.getJSONObject("title").get("alignment"));
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

    @Override
    @Test
    public void testToJson2() {
        JSONObject json = statBlock2Character0.toJson();

        assertTrue(json.has("parentStatBlock"));
        assertEquals(statBlock2Character0.getHP(), json.getInt("hp"));
        assertEquals(statBlock2Character0.getMaxHP(), json.getInt("maxHP"));

        assertEquals(statBlock2Character0.getTitle().getName(), json.getJSONObject("title").get("name"));
        assertEquals(statBlock2Character0.getTitle().getType(), json.getJSONObject("title").get("type"));
        assertEquals(statBlock2Character0.getTitle().getSize(), json.getJSONObject("title").get("size"));
        assertEquals(statBlock2Character0.getTitle().getAlignment(), json.getJSONObject("title").get("alignment"));
        assertEquals(statBlock2Character0.getTitle().getGroup(), json.getJSONObject("title").get("group"));

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

    @Test
    public void testToString() {
        String statBlock0Character0String = statBlock0Character0.getTitle().getName() +  ", HP: "
                + statBlock0Character0.getHPString();
        String statBlock1Character0String = statBlock1Character0.getTitle().getName() + ", HP: "
                + statBlock1Character0.getHPString();
        String statBlock2Character0String = statBlock2Character0.getTitle().getName() + " (Group: "
                + statBlock2Character0.getTitle().getGroup() + "), HP: " + statBlock2Character0.getHPString();

        assertEquals(statBlock0Character0String, statBlock0Character0.toString());
        assertEquals(statBlock1Character0String, statBlock1Character0.toString());
        assertEquals(statBlock2Character0String, statBlock2Character0.toString());
    }
}
