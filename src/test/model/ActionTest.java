package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActionTest {
    private Action testAction;
    private RollFormula testHitFormula;
    private RollFormula testDamageFormula;

    @BeforeEach
    public void runBeforeEach() {
        testHitFormula = new RollFormula(1, 20, 5);
        testDamageFormula = new RollFormula(1, 12, 3);
        testAction = new Action("OrcGreatAxe", "Melee Weapon Attack", "Slashing",
                "5ft", testHitFormula, testDamageFormula);
    }

    @Test
    public void testConstructor() {
        assertEquals("OrcGreatAxe", testAction.getName());
        assertEquals("Melee Weapon Attack", testAction.getDescription());
        assertEquals("Slashing", testAction.getDamageType());
        assertEquals("5ft", testAction.getReach());
        assertEquals(testHitFormula, testAction.getHit());
        assertEquals(testDamageFormula, testAction.getDamage());
    }

    @Test
    public void testGetHitString() {
        assertEquals("1d20+5", testAction.getHitString());
    }

    @Test
    public void testGetDamageString() {
        assertEquals("1d12+3", testAction.getDamageString());
    }
}