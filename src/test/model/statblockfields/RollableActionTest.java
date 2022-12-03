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
    private final int testReach0 = 0;
    private final int testReach1 = 5;
    private final int testReach2 = 15;

    private RollFormula testRollFormula0;
    private RollFormula testRollFormula1;
    private RollFormula testRollFormula2;
    private RollFormula testRollFormula3;

    private HashMap<DamageType, RollFormula> testDamageMap0;
    private HashMap<DamageType, RollFormula> testDamageMap1;
    private HashMap<DamageType, RollFormula> testDamageMap2;

    private RollableAction testRollableAction0;
    private RollableAction testRollableAction1;
    private RollableAction testRollableAction2;

    @BeforeEach
    public void runBeforeEach() {
        testRollFormula0 = new RollFormula(0, 0, 0);
        testRollFormula1 = new RollFormula(1, 1, 1);
        testRollFormula2 = new RollFormula(1, 20, 3);
        testRollFormula3 = new RollFormula(3, 12, -4);

        testDamageMap0 = new HashMap<>();
        testDamageMap0.put(DamageType.SLASHING, testRollFormula0);

        testDamageMap1 = new HashMap<>();
        testDamageMap1.put(DamageType.SLASHING, testRollFormula1);
        testDamageMap1.put(DamageType.FIRE, testRollFormula1);

        testDamageMap2 = new HashMap<>();
        testDamageMap2.put(DamageType.SLASHING, testRollFormula1);
        testDamageMap2.put(DamageType.FIRE, testRollFormula2);
        try {
            testRollableAction0 = new RollableAction(
                    testName0, testDescription0, testReach0, 0, 0, testDamageMap0);
            testRollableAction1 = new RollableAction(
                    testName1, testDescription1, testReach1, 0, 1, testDamageMap1);
            testRollableAction2 = new RollableAction(
                    testName1, testDescription0, testReach1, testReach2, -4, testDamageMap2);
        } catch (IncompleteFieldException e) {
            fail("Should not have thrown an exception!");
        }
    }

    @Test
    public void testConstructor0() {
        assertEquals(testName0, testRollableAction0.getName());
        assertEquals(testDescription0, testRollableAction0.getDescription());
        assertEquals(testReach0, testRollableAction0.getRange());
        assertEquals(testRollFormula0, testRollableAction0.getHitFormula());
        assertEquals(testDamageMap0, testRollableAction0.getDamageMap());
        assertEquals(1, testRollableAction0.getDamageMap().size());
    }

    @Test
    public void testConstructor1() {
        assertEquals(testName1, testRollableAction1.getName());
        assertEquals(testDescription1, testRollableAction1.getDescription());
        assertEquals(testReach1, testRollableAction1.getRange());
        assertEquals(testRollFormula1, testRollableAction1.getHitFormula());
        assertEquals(testDamageMap1, testRollableAction1.getDamageMap());
        assertEquals(2, testRollableAction1.getDamageMap().size());
    }

    @Test
    public void testConstructor2() {
        assertEquals(testName1, testRollableAction2.getName());
        assertEquals(testDescription0, testRollableAction2.getDescription());
        assertEquals(testReach0, testRollableAction2.getRange());
        assertEquals(testRollFormula3, testRollableAction2.getHitFormula());
        assertEquals(testDamageMap2, testRollableAction2.getDamageMap());
        assertEquals(2, testRollableAction2.getDamageMap().size());
    }

    @Test
    public void testGetStringAction0() {
        String testRollString = "something something weapon, test action 0 (5ft), "
                + "(0d0) to hit and (0d0) slashing damage.";
        assertEquals(testRollString, testRollableAction0.toString());
    }

    @Test
    public void testGetStringAction1() {
        String testRollString = "Something Something Spell, Test Action 1 (30/120ft), "
                + "(1d1+1) to hit and (1d1+1) slashing damage and (1d1+1) Fire damage.";
        assertEquals(testRollString, testRollableAction1.toString());
    }

    @Test
    public void testGetStringAction2() {
        String testRollString = "something something weapon, Test Action 1 (5ft), "
                + "(3d12-4) to hit and (1d1+1) slashing damage and (1d20+3) Fire damage.";
        assertEquals(testRollString, testRollableAction2.toString());
    }

    @Test
    public void testGetRollStringAction0() {
        String testRollString = "something something weapon, test action 0 (5ft), "
                + "did (0) to hit and (0) slashing damage.";
        assertEquals(testRollString, testRollableAction0.generateFullRollString());
    }

    @Test
    public void testGetRollStringAction1() {
        String testRollString = "Something Something Spell, Test Action 1 (30/120ft), "
                + "did (2) to hit and (2) slashing damage and (2) Fire damage.";
        assertEquals(testRollString, testRollableAction1.generateFullRollString());
    }

    @Test
    public void testToJson() {
        JSONObject json0 = testRollableAction0.toJson();
        JSONObject json1 = testRollableAction1.toJson();
        JSONObject json2 = testRollableAction2.toJson();

        assertEquals(testName0, json0.get("name"));
        assertEquals(testName1, json1.get("name"));
        assertEquals(testName1, json2.get("name"));

        assertEquals(testDescription0, json0.get("description"));
        assertEquals(testDescription1, json1.get("description"));
        assertEquals(testDescription0, json2.get("description"));

        assertEquals(testReach0, json0.get("reach"));
        assertEquals(testReach1, json1.get("reach"));
        assertEquals(testReach0, json2.get("reach"));

        assertTrue(json0.has("hitFormula"));
        assertTrue(json1.has("hitFormula"));
        assertTrue(json2.has("hitFormula"));

        assertTrue(json0.has("damageMap"));
        assertTrue(json1.has("damageMap"));
        assertTrue(json2.has("damageMap"));
    }
}
