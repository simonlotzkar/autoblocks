package model.statblockfields;

import model.RollFormula;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ActionTest {
    private final String testName0 = "test action 0";
    private final String testName1 = "Test Action 1";
    private final String testDescription0 = "something something weapon";
    private final String testDescription1 = "Something Something Spell";
    private final String testReach0 = "5";
    private final String testReach1 = "30/120";

    private RollFormula testRollFormula0;
    private RollFormula testRollFormula1;
    private RollFormula testRollFormula2;
    private RollFormula testRollFormula3;

    private HashMap<String, RollFormula> testDamageMap0;
    private HashMap<String, RollFormula> testDamageMap1;
    private HashMap<String, RollFormula> testDamageMap2;

    private Action testAction0;
    private Action testAction1;
    private Action testAction2;

    @BeforeEach
    public void runBeforeEach() {
        testRollFormula0 = new RollFormula(0, 0, 0);
        testRollFormula1 = new RollFormula(1, 1, 1);
        testRollFormula2 = new RollFormula(1, 20, 3);
        testRollFormula3 = new RollFormula(3, 12, -4);

        testDamageMap0 = new HashMap<>();
        testDamageMap0.put("slashing", testRollFormula0);

        testDamageMap1 = new HashMap<>();
        testDamageMap1.put("slashing", testRollFormula1);
        testDamageMap1.put("Fire", testRollFormula1);

        testDamageMap2 = new HashMap<>();
        testDamageMap2.put("slashing", testRollFormula1);
        testDamageMap2.put("Fire", testRollFormula2);

        testAction0 = new Action(testName0, testDescription0, testReach0, testRollFormula0, testDamageMap0);
        testAction1 = new Action(testName1, testDescription1, testReach1, testRollFormula1, testDamageMap1);
        testAction2 = new Action(testName1, testDescription0, testReach0, testRollFormula3, testDamageMap2);
    }

    @Test
    public void testConstructor0() {
        assertEquals(testName0, testAction0.getName());
        assertEquals(testDescription0, testAction0.getDescription());
        assertEquals(testReach0, testAction0.getReach());
        assertEquals(testRollFormula0, testAction0.getHitFormula());
        assertEquals(testDamageMap0, testAction0.getDamageMap());
        assertEquals(1, testAction0.getDamageMap().size());
    }

    @Test
    public void testConstructor1() {
        assertEquals(testName1, testAction1.getName());
        assertEquals(testDescription1, testAction1.getDescription());
        assertEquals(testReach1, testAction1.getReach());
        assertEquals(testRollFormula1, testAction1.getHitFormula());
        assertEquals(testDamageMap1, testAction1.getDamageMap());
        assertEquals(2, testAction1.getDamageMap().size());
    }

    @Test
    public void testConstructor2() {
        assertEquals(testName1, testAction2.getName());
        assertEquals(testDescription0, testAction2.getDescription());
        assertEquals(testReach0, testAction2.getReach());
        assertEquals(testRollFormula3, testAction2.getHitFormula());
        assertEquals(testDamageMap2, testAction2.getDamageMap());
        assertEquals(2, testAction2.getDamageMap().size());
    }

    @Test
    public void testGetStringAction0() {
        String testRollString = "something something weapon, test action 0 (5ft), "
                + "(0d0) to hit and (0d0) slashing damage.";
        assertEquals(testRollString, testAction0.toString());
    }

    @Test
    public void testGetStringAction1() {
        String testRollString = "Something Something Spell, Test Action 1 (30/120ft), "
                + "(1d1+1) to hit and (1d1+1) slashing damage and (1d1+1) Fire damage.";
        assertEquals(testRollString, testAction1.toString());
    }

    @Test
    public void testGetStringAction2() {
        String testRollString = "something something weapon, Test Action 1 (5ft), "
                + "(3d12-4) to hit and (1d1+1) slashing damage and (1d20+3) Fire damage.";
        assertEquals(testRollString, testAction2.toString());
    }

    @Test
    public void testGetRollStringAction0() {
        String testRollString = "something something weapon, test action 0 (5ft), "
                + "did (0) to hit and (0) slashing damage.";
        assertEquals(testRollString, testAction0.getRollString());
    }

    @Test
    public void testGetRollStringAction1() {
        String testRollString = "Something Something Spell, Test Action 1 (30/120ft), "
                + "did (2) to hit and (2) slashing damage and (2) Fire damage.";
        assertEquals(testRollString, testAction1.getRollString());
    }

    @Test
    public void testToJson() {
        JSONObject json0 = testAction0.toJson();
        JSONObject json1 = testAction1.toJson();
        JSONObject json2 = testAction2.toJson();

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
