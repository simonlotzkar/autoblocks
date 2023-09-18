package model.statblockfields;

import enums.DamageType;
import exceptions.IncompleteFieldException;
import model.RollFormula;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class RollableActionTest {
    private final String testName0 = "test action 0";
    private final String testName1 = "Test Action 1";
    private final String testDescription0 = "something something weapon";
    private final String testDescription1 = "Something Something Spell";

    private final int testRange0 = 1;
    private final int testRange1 = 60;
    private final int testLongRange0 = 120;
    private final int testHitModifier0 = 0;
    private final int testHitModifier1 = -5;

    private HashMap<DamageType, RollFormula> testDamageMap0;
    private HashMap<DamageType, RollFormula> testDamageMap1;

    private RollableAction testRollableAction0;
    private RollableAction testRollableAction1;

    @BeforeEach
    public void runBeforeEach() {
        RollFormula testRollFormula0 = new RollFormula(0, 0, 0);
        RollFormula testRollFormula1 = new RollFormula(1, 1, 1);

        testDamageMap0 = new HashMap<>();
        testDamageMap0.put(DamageType.SLASHING, testRollFormula0);

        testDamageMap1 = new HashMap<>();
        testDamageMap1.put(DamageType.FIRE, testRollFormula1);
        testDamageMap1.put(DamageType.SLASHING, testRollFormula1);

        try {
            testRollableAction0 = new RollableAction(
                    testName0, testDescription0, testRange0, 0, testHitModifier0, testDamageMap0);
            testRollableAction1 = new RollableAction(
                    testName1, testDescription1, testRange1, testLongRange0, testHitModifier1, testDamageMap1);
        } catch (Exception e) {
            fail("Should not have thrown an exception! Message: " + e.getMessage());
        }
    }

    @Test
    public void testConstructor0() {
        assertEquals(testName0, testRollableAction0.getName());
        assertEquals(testDescription0, testRollableAction0.getDescription());
        assertEquals(testRange0, testRollableAction0.getRange());
        assertEquals(testHitModifier0, testRollableAction0.getHitModifier());
        assertEquals(testDamageMap0, testRollableAction0.getDamageMap());
        assertEquals(1, testRollableAction0.getDamageMap().size());
    }

    @Test
    public void testConstructor1() {
        assertEquals(testName1, testRollableAction1.getName());
        assertEquals(testDescription1, testRollableAction1.getDescription());
        assertEquals(testRange1, testRollableAction1.getRange());
        assertEquals(testHitModifier1, testRollableAction1.getHitModifier());
        assertEquals(testDamageMap1, testRollableAction1.getDamageMap());
        assertEquals(2, testRollableAction1.getDamageMap().size());
    }

    @Test
    public void testGetStringAction0() {
        String testRollString = "something something weapon, test action 0 (1ft), "
                + "(+0) to hit and (0d0) slashing damage";
        assertEquals(testRollString, testRollableAction0.toString());
    }

    @Test
    public void testGetStringAction1() {
        String testRollString = "Something Something Spell, Test Action 1 (60/120ft), "
                + "(-5) to hit and (1d1+1) slashing damage and (1d1+1) fire damage";
        assertEquals(testRollString, testRollableAction1.toString());
    }

//    @Test
//    public void testGetRollStringAction0() {
//        String testRollString = "something something weapon, test action 0 (5ft), "
//                + "did (0) to hit and (0) slashing damage.";
//        assertEquals(testRollString, testRollableAction0.generateFullRollString());
//    }
//
//    @Test
//    public void testGetRollStringAction1() {
//        String testRollString = "Something Something Spell, Test Action 1 (30/120ft), "
//                + "did (2) to hit and (2) slashing damage and (2) Fire damage.";
//        assertEquals(testRollString, testRollableAction1.generateFullRollString());
//    }

    @Test
    public void testToJson() {
        JSONObject json0 = testRollableAction0.toJson();
        JSONObject json1 = testRollableAction1.toJson();

        assertEquals(testName0, json0.get("name"));
        assertEquals(testName1, json1.get("name"));

        assertEquals(testDescription0, json0.get("description"));
        assertEquals(testDescription1, json1.get("description"));

        assertEquals(testRange0, json0.get("range"));
        assertEquals(testRange1, json1.get("range"));

        assertEquals(testLongRange0, json1.get("longRange"));

        assertEquals(testHitModifier0, json0.get("hitModifier"));
        assertEquals(testHitModifier1, json1.get("hitModifier"));

        assertTrue(json0.has("damageMap"));
        assertTrue(json1.has("damageMap"));
    }
}
