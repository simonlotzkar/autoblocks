package model;

import exceptions.IncompleteFieldException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NPCTest extends StatBlockTest {
    private NPC statBlock0NPC0;

    private NPC statBlock1NPC0;
    private NPC statBlock1NPC1;
    private NPC statBlock1NPC2;

    private NPC statBlock2NPC0;
    private NPC statBlock2NPC1;
    private NPC statBlock2NPC2;

    private static final String GROUP_0 = "group-1";

    private final List<NPC> emptyEncounter = new ArrayList<>();
    private final List<NPC> notSequentialEncounter = new ArrayList<>();
    private final List<NPC> sequentialEncounter = new ArrayList<>();

    @Override
    @BeforeEach
    public void beforeEach() {
        initializeStatBlock0();
        initializeStatBlock1();
        initializeStatBlock2();

        try {
            statBlock0NPC0 = new NPC(statBlock0);
            statBlock1NPC0 = new NPC(statBlock1);
            statBlock1NPC1 = new NPC(statBlock1);
            statBlock1NPC2 = new NPC(statBlock1);

            statBlock2NPC0 = new NPC(statBlock2);
            statBlock2NPC1 = new NPC(statBlock2);
            statBlock2NPC2 = new NPC(statBlock2);
        } catch (IncompleteFieldException e) {
            fail("exception!");
        }
        sequentialEncounter.add(statBlock0NPC0);
        sequentialEncounter.add(statBlock1NPC0);
        sequentialEncounter.add(statBlock1NPC1);
        sequentialEncounter.add(statBlock1NPC2);
        sequentialEncounter.add(statBlock2NPC0);
        sequentialEncounter.add(statBlock2NPC1);
        sequentialEncounter.add(statBlock2NPC2);

        notSequentialEncounter.add(statBlock0NPC0);
        notSequentialEncounter.add(statBlock1NPC0);
        notSequentialEncounter.add(statBlock1NPC2);
        notSequentialEncounter.add(statBlock2NPC0);
        notSequentialEncounter.add(statBlock2NPC2);
    }

    @Override
    @Test
    public void testConstructor0() {
        assertEquals(statBlock0, statBlock0NPC0.getParentStatBlock());
        assertEquals(statBlock0NPC0.getHP(), statBlock0NPC0.getMaxHP());

        assertEquals(title0.getName() + " 1", statBlock0NPC0.getTitle().getName());
        assertEquals(title0.getType(), statBlock0NPC0.getTitle().getType());
        assertEquals(title0.getSize(), statBlock0NPC0.getTitle().getSize());
        assertEquals(title0.getAlignment(), statBlock0NPC0.getTitle().getAlignment());

        assertEquals(ROLL_FORMULA_0, statBlock0NPC0.getHPFormula());
        assertEquals(INTEGER_0, statBlock0NPC0.getXP());
        assertEquals(INTEGER_0, statBlock0NPC0.getProficiency());

        assertEquals(ARMOUR_0, statBlock0NPC0.getArmour());
        assertEquals(SPEEDS_0, statBlock0NPC0.getSpeeds());
        assertEquals(SENSES_0, statBlock0NPC0.getSenses());
        assertEquals(ABILITY_SCORES_0, statBlock0NPC0.getAbilityScores());

        assertTrue(statBlock0NPC0.getRollableActions().isEmpty());
    }

    @Override
    @Test
    public void testConstructor1() {
        assertEquals(statBlock1, statBlock1NPC0.getParentStatBlock());
        assertEquals(statBlock1NPC0.getHP(), statBlock1NPC0.getMaxHP());

        assertEquals(title1.getName() + " 1", statBlock1NPC0.getTitle().getName());
        assertEquals(title1.getType(), statBlock1NPC0.getTitle().getType());
        assertEquals(title1.getSize(), statBlock1NPC0.getTitle().getSize());
        assertEquals(title1.getAlignment(), statBlock1NPC0.getTitle().getAlignment());

        assertEquals(HP_FORMULA_0, statBlock1NPC0.getHPFormula());
        assertEquals(XP_1, statBlock1NPC0.getXP());
        assertEquals(PROFICIENCY_1, statBlock1NPC0.getProficiency());

        assertEquals(ARMOUR_1, statBlock1NPC0.getArmour());
        assertEquals(SPEEDS_1, statBlock1NPC0.getSpeeds());
        assertEquals(SENSES_1, statBlock1NPC0.getSenses());
        assertEquals(ABILITY_SCORES_1, statBlock1NPC0.getAbilityScores());

        assertEquals(1, statBlock1NPC0.getRollableActions().size());

        assertEquals(ACTION_NAME_1, statBlock1NPC0.getRollableActions().get(0).getName());
        assertEquals(ACTION_DESCRIPTION_1, statBlock1NPC0.getRollableActions().get(0).getDescription());
        assertEquals(ACTION_REACH_1, statBlock1NPC0.getRollableActions().get(0).getRange());
        assertEquals(HIT_MODIFIER_1, statBlock1NPC0.getRollableActions().get(0).getHitModifier());
        assertEquals(DAMAGE_FORMULA_1, statBlock1NPC0.getRollableActions().get(0).getDamageMap().get(DAMAGE_TYPE_1));
    }

    @Override
    @Test
    public void testConstructor2() {
        assertEquals(statBlock2, statBlock2NPC0.getParentStatBlock());
        assertEquals(statBlock2NPC0.getHP(), statBlock2NPC0.getMaxHP());

        assertEquals(title2.getName() + " 1", statBlock2NPC0.getTitle().getName());
        assertEquals(title2.getType(), statBlock2NPC0.getTitle().getType());
        assertEquals(title2.getSize(), statBlock2NPC0.getTitle().getSize());
        assertEquals(title2.getAlignment(), statBlock2NPC0.getTitle().getAlignment());

        assertEquals(HP_FORMULA_1, statBlock2NPC0.getHPFormula());
        assertEquals(XP_2, statBlock2NPC0.getXP());
        assertEquals(PROFICIENCY_2, statBlock2NPC0.getProficiency());

        assertEquals(armour2, statBlock2NPC0.getArmour());
        assertEquals(SPEEDS_2, statBlock2NPC0.getSpeeds());
        assertEquals(SENSES_2, statBlock2NPC0.getSenses());
        assertEquals(ABILITY_SCORES_2, statBlock2NPC0.getAbilityScores());

        assertEquals(2, statBlock2NPC0.getRollableActions().size());

        assertEquals(ACTION_NAME_2, statBlock2NPC0.getRollableActions().get(0).getName());
        assertEquals(ACTION_DESCRIPTION_2, statBlock2NPC0.getRollableActions().get(0).getDescription());
        assertEquals(ACTION_REACH_2, statBlock2NPC0.getRollableActions().get(0).getRange());
        assertEquals(HIT_MODIFIER_2, statBlock2NPC0.getRollableActions().get(0).getHitModifier());
        assertEquals(DAMAGE_FORMULA_2, statBlock2NPC0.getRollableActions().get(0).getDamageMap().get(DAMAGE_TYPE_2));

        assertEquals(ACTION_NAME_3, statBlock2NPC0.getRollableActions().get(1).getName());
        assertEquals(ACTION_DESCRIPTION_3, statBlock2NPC0.getRollableActions().get(1).getDescription());
        assertEquals(ACTION_REACH_3, statBlock2NPC0.getRollableActions().get(1).getRange());
        assertEquals(HIT_MODIFIER_3, statBlock2NPC0.getRollableActions().get(1).getHitModifier());
        assertEquals(DAMAGE_FORMULA_3, statBlock2NPC0.getRollableActions().get(1).getDamageMap().get(DAMAGE_TYPE_3));

        assertEquals(2, statBlock2NPC0.getLanguages().getLanguagesList().size());
        assertEquals(LANGUAGE_0, statBlock2NPC0.getLanguages().getLanguagesList().get(0));
        assertEquals(LANGUAGE_1, statBlock2NPC0.getLanguages().getLanguagesList().get(1));

        assertEquals(2, statBlock2NPC0.getAbilities().size());
        assertEquals(ability0, statBlock2NPC0.getAbilities().get(0));
        assertEquals(ability1, statBlock2NPC0.getAbilities().get(1));

        assertEquals(18, statBlock2NPC0.getSkillProficiencies().size());
        assertEquals(15, statBlock2NPC0.getConditionImmunities().size());
        assertEquals(6, statBlock2NPC0.getSavingThrowProficiencies().size());
        assertEquals(13, statBlock2NPC0.getResistances().size());

        assertEquals(2, statBlock2NPC0.getLegendaryMechanics().getLegendaryActions().size());
        assertEquals(LEGENDARY_MECHANIC_DESCRIPTION_0, statBlock2NPC0.getLegendaryMechanics().getLegendaryDescription());
        assertEquals(legendaryAction0, statBlock2NPC0.getLegendaryMechanics().getLegendaryActions().get(0));
        assertEquals(legendaryAction1, statBlock2NPC0.getLegendaryMechanics().getLegendaryActions().get(1));
    }

    @Test
    public void testGenerateNameForEncounterBaseCase() {
        assertEquals(statBlock0.getTitle().getName() + " 1",
                NPC.generateNameForEncounter(statBlock0, emptyEncounter));
        assertEquals(statBlock1.getTitle().getName() + " 1",
                NPC.generateNameForEncounter(statBlock1, emptyEncounter));
        assertEquals(statBlock2.getTitle().getName() + " 1",
                NPC.generateNameForEncounter(statBlock2, emptyEncounter));
    }

    @Test
    public void testGenerateNameForEncounterSequentialCase() {
        assertEquals(statBlock0.getTitle().getName() + " 2",
                NPC.generateNameForEncounter(statBlock0, sequentialEncounter));
        assertEquals(statBlock1.getTitle().getName() + " 4",
                NPC.generateNameForEncounter(statBlock1, sequentialEncounter));
        assertEquals(statBlock2.getTitle().getName() + " 4",
                NPC.generateNameForEncounter(statBlock2, sequentialEncounter));
    }

    @Test
    public void testGenerateNameForEncounterNotSequentialCase() {
        assertEquals(statBlock0.getTitle().getName() + " 2",
                NPC.generateNameForEncounter(statBlock0, notSequentialEncounter));
        assertEquals(statBlock1.getTitle().getName() + " 2",
                NPC.generateNameForEncounter(statBlock1, notSequentialEncounter));
        assertEquals(statBlock2.getTitle().getName() + " 2",
                NPC.generateNameForEncounter(statBlock2, notSequentialEncounter));
    }

    @Test
    public void testChangeHPBaseCase() {
        statBlock1NPC0.changeHP(1 - statBlock1NPC0.getHP());
        statBlock2NPC0.changeHP(1 - statBlock2NPC0.getHP());

        assertEquals(1, statBlock1NPC0.getHP());
        assertEquals(1, statBlock2NPC0.getHP());
    }

    @Test
    public void testChangeHPOverMaxCase() {
        int maxHP0 = statBlock1NPC0.getMaxHP();
        int maxHP1 = statBlock2NPC0.getMaxHP();

        statBlock1NPC0.changeHP(maxHP0);
        statBlock2NPC0.changeHP(maxHP1);

        assertEquals(maxHP0, statBlock1NPC0.getHP());
        assertEquals(maxHP1, statBlock2NPC0.getHP());
    }

    @Test
    public void testChangeHPNegativeCase() {
        statBlock1NPC0.changeHP(-5 - statBlock1NPC0.getHP());
        statBlock2NPC0.changeHP(-5 - statBlock2NPC0.getHP());

        assertEquals(-5, statBlock1NPC0.getHP());
        assertEquals(-5, statBlock2NPC0.getHP());
    }

    @Test
    public void testGetHPString() {
        String hpString0 = statBlock0NPC0.getHP() + "/" + statBlock0NPC0.getMaxHP();

        String hpString1 = statBlock1NPC0.getHP() + "/" + statBlock1NPC0.getMaxHP();
        String hpString2 = statBlock1NPC1.getHP() + "/" + statBlock1NPC1.getMaxHP();
        String hpString3 = statBlock1NPC2.getHP() + "/" + statBlock1NPC2.getMaxHP();

        String hpString4 = statBlock2NPC0.getHP() + "/" + statBlock2NPC0.getMaxHP();
        String hpString5 = statBlock2NPC1.getHP() + "/" + statBlock2NPC1.getMaxHP();
        String hpString6 = statBlock2NPC2.getHP() + "/" + statBlock2NPC2.getMaxHP();

        assertEquals(hpString0, statBlock0NPC0.getHPString());

        assertEquals(hpString1, statBlock1NPC0.getHPString());
        assertEquals(hpString2, statBlock1NPC1.getHPString());
        assertEquals(hpString3, statBlock1NPC2.getHPString());

        assertEquals(hpString4, statBlock2NPC0.getHPString());
        assertEquals(hpString5, statBlock2NPC1.getHPString());
        assertEquals(hpString6, statBlock2NPC2.getHPString());
    }

    @Override
    @Test
    public void testToJson0() {
        JSONObject json = statBlock0NPC0.toJson();

        assertTrue(json.has("parentStatBlock"));
        assertEquals(INTEGER_0, json.getInt("hp"));
        assertEquals(INTEGER_0, json.getInt("maxHP"));

        assertEquals(title0.getName() + " 1", json.getJSONObject("title").get("name"));
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
        JSONObject json = statBlock1NPC0.toJson();

        assertTrue(json.has("parentStatBlock"));
        assertEquals(statBlock1NPC0.getHP(), json.getInt("hp"));
        assertEquals(statBlock1NPC0.getMaxHP(), json.getInt("maxHP"));

        assertEquals(statBlock1NPC0.getTitle().getName(), json.getJSONObject("title").get("name"));
        assertEquals(statBlock1NPC0.getTitle().getType(), json.getJSONObject("title").get("type"));
        assertEquals(statBlock1NPC0.getTitle().getSize(), json.getJSONObject("title").get("size"));
        assertEquals(statBlock1NPC0.getTitle().getAlignment(), json.getJSONObject("title").get("alignment"));
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
    }

    @Override
    @Test
    public void testToJson2() {
        JSONObject json = statBlock2NPC0.toJson();

        assertTrue(json.has("parentStatBlock"));
        assertEquals(statBlock2NPC0.getHP(), json.getInt("hp"));
        assertEquals(statBlock2NPC0.getMaxHP(), json.getInt("maxHP"));

        assertEquals(statBlock2NPC0.getTitle().getName(), json.getJSONObject("title").get("name"));
        assertEquals(statBlock2NPC0.getTitle().getType(), json.getJSONObject("title").get("type"));
        assertEquals(statBlock2NPC0.getTitle().getSize(), json.getJSONObject("title").get("size"));
        assertEquals(statBlock2NPC0.getTitle().getAlignment(), json.getJSONObject("title").get("alignment"));

        assertEquals(XP_2, json.get("xp"));
        assertEquals(HP_FORMULA_1.getAmountOfDice(), json.getJSONObject("hpFormula").get("amountOfDice"));
        assertEquals(HP_FORMULA_1.getDieSides(), json.getJSONObject("hpFormula").get("dieSides"));
        assertEquals(HP_FORMULA_1.getModifier(), json.getJSONObject("hpFormula").get("modifier"));
        assertEquals(PROFICIENCY_2, json.get("proficiency"));

        assertEquals(armour2.getAC(), json.getJSONObject("armour").get("ac"));
        assertEquals(SPEEDS_2.getSpeed(), json.getJSONObject("speeds").get("speed"));
        assertEquals(SENSES_2.getPassivePerception(), json.getJSONObject("senses").get("passivePerception"));

        assertEquals(ABILITY_SCORES_2.getStrength(), json.getJSONObject("abilityScores").get("strength"));
        assertEquals(ABILITY_SCORES_2.getDexterity(), json.getJSONObject("abilityScores").get("dexterity"));
        assertEquals(ABILITY_SCORES_2.getConstitution(), json.getJSONObject("abilityScores").get("constitution"));
        assertEquals(ABILITY_SCORES_2.getIntelligence(), json.getJSONObject("abilityScores").get("intelligence"));
        assertEquals(ABILITY_SCORES_2.getWisdom(), json.getJSONObject("abilityScores").get("wisdom"));
        assertEquals(ABILITY_SCORES_2.getCharisma(), json.getJSONObject("abilityScores").get("charisma"));
    }

    @Test
    public void testToString() {
        String statBlock0Character0String = statBlock0NPC0.getTitle().getName() +  ", HP: "
                + statBlock0NPC0.getHPString();
        String statBlock1Character0String = statBlock1NPC0.getTitle().getName() + ", HP: "
                + statBlock1NPC0.getHPString();
        String statBlock2Character0String = statBlock2NPC0.getTitle().getName() + " (Group: "
                + statBlock2NPC0.getTitle() + "), HP: " + statBlock2NPC0.getHPString();

        assertEquals(statBlock0Character0String, statBlock0NPC0.toString());
        assertEquals(statBlock1Character0String, statBlock1NPC0.toString());
        assertEquals(statBlock2Character0String, statBlock2NPC0.toString());
    }
}
