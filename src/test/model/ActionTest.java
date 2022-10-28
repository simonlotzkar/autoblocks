package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActionTest {
    private final String testActionName0 = "test action 0";
    private final String testActionName1 = "test action 1";
    private final String testActionDescription0 = "something something weapon";
    private final String testActionDescription1 = "Something Something Spell";
    private final String testActionDamageType0 = "slashing";
    private final String testActionDamageType1 = "Fire";
    private final String testActionReach0 = "5";
    private final String testActionReach1 = "30/120";
    private String rollString0;

    private RollFormula testRollFormula0;
    private RollFormula testRollFormula1;
    private RollFormula testRollFormula2;
    private RollFormula testRollFormula3;

    private Action testAction0;
    private Action testAction1;
    private Action testAction2;

    @BeforeEach
    public void runBeforeEach() {
        testRollFormula0 = new RollFormula(0, 6, 0);
        testRollFormula1 = new RollFormula(4, 6, -2);
        testRollFormula2 = new RollFormula(1, 12, 3);
        testRollFormula3 = new RollFormula(3, 4, 0);

        testAction0 = new Action(testActionName0, testActionDescription0, testActionDamageType0, testActionReach0,
                testRollFormula0, testRollFormula0);
        testAction1 = new Action(testActionName1, testActionDescription1, testActionDamageType1, testActionReach1,
                testRollFormula1, testRollFormula1);
        testAction2 = new Action(testActionName1, testActionDescription1, testActionDamageType1, testActionReach1,
                testRollFormula2, testRollFormula3);

        rollString0 = (testActionDescription0 + ", " + testActionName0 + " (" + testActionReach0
                + "ft), did 0 to hit, and 0 " + testActionDamageType0 + " damage.");
    }

    @Test
    public void testConstructor() {
        assertEquals(testActionName0, testAction0.getName());
        assertEquals(testActionDescription0, testAction0.getDescription());
        assertEquals(testActionDamageType0, testAction0.getDamageType());
        assertEquals(testActionReach0, testAction0.getReach());
        assertEquals(testRollFormula0, testAction0.getHit());
        assertEquals(testRollFormula0, testAction0.getDamage());

        assertEquals(testActionName1, testAction1.getName());
        assertEquals(testActionDescription1, testAction1.getDescription());
        assertEquals(testActionDamageType1, testAction1.getDamageType());
        assertEquals(testActionReach1, testAction1.getReach());
        assertEquals(testRollFormula1, testAction1.getHit());
        assertEquals(testRollFormula1, testAction1.getDamage());

        assertEquals(testRollFormula2, testAction2.getHit());
        assertEquals(testRollFormula3, testAction2.getDamage());
    }

    @Test
    public void testRollAsStringForName() {
        assertEquals("character0's " + rollString0, testAction0.rollAsStringForName("character0"));
    }
}