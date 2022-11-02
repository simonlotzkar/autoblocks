package model.statblockfields;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RollFormulaTest {
    private RollFormula testRollFormula0;
    private RollFormula testRollFormula1;
    private RollFormula testRollFormula2;
    private RollFormula testRollFormula3;
    private RollFormula testRollFormula4;

    private int testRoll0;
    private int testRoll1;
    private int testRoll2;
    private int testRoll3;
    private int testRoll4;

    @BeforeEach
    public void beforeEach() {
        testRollFormula0 = new RollFormula(0, 0, 0);
        testRollFormula1 = new RollFormula(1, 1, 0);
        testRollFormula2 = new RollFormula(1, 4, 2);
        testRollFormula3 = new RollFormula(1, 7, 0);
        testRollFormula4 = new RollFormula(3, 30, -3);

        testRoll0 = testRollFormula0.roll();
        testRoll1 = testRollFormula1.roll();
        testRoll2 = testRollFormula2.roll();
        testRoll3 = testRollFormula3.roll();
        testRoll4 = testRollFormula4.roll();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testRollFormula0.getAmountOfDice());
        assertEquals(0, testRollFormula0.getDieSides());
        assertEquals(0, testRollFormula0.getModifier());

        assertEquals(1, testRollFormula1.getAmountOfDice());
        assertEquals(1, testRollFormula1.getDieSides());
        assertEquals(0, testRollFormula1.getModifier());

        assertEquals(1, testRollFormula2.getAmountOfDice());
        assertEquals(4, testRollFormula2.getDieSides());
        assertEquals(2, testRollFormula2.getModifier());

        assertEquals(1, testRollFormula3.getAmountOfDice());
        assertEquals(7, testRollFormula3.getDieSides());
        assertEquals(0, testRollFormula3.getModifier());

        assertEquals(3, testRollFormula4.getAmountOfDice());
        assertEquals(30, testRollFormula4.getDieSides());
        assertEquals(-3, testRollFormula4.getModifier());
    }

    @Test
    public void testRoll() {
        assertEquals(0, testRoll0);
        assertEquals(1, testRoll1);
        assertTrue(3 <= testRoll2 && testRoll2 <= 6);
        assertTrue(1 <= testRoll3 && testRoll3 <= 7);
        assertTrue(0 <= testRoll4 && testRoll4 <= 87);
    }

    @Test
    public void testGetString() {
        assertEquals("0d0", testRollFormula0.getString());
        assertEquals("1d1", testRollFormula1.getString());
        assertEquals("1d4+2", testRollFormula2.getString());
        assertEquals("1d7", testRollFormula3.getString());
        assertEquals("3d30-3", testRollFormula4.getString());
    }

    @Test
    public void testToJson() {
        JSONObject json0 = testRollFormula0.toJson();
        JSONObject json1 = testRollFormula1.toJson();
        JSONObject json2 = testRollFormula2.toJson();

        assertEquals(0, json0.get("amountOfDice"));
        assertEquals(0, json0.get("dieSides"));
        assertEquals(0, json0.get("modifier"));

        assertEquals(1, json1.get("amountOfDice"));
        assertEquals(1, json1.get("dieSides"));
        assertEquals(0, json1.get("modifier"));

        assertEquals(1, json2.get("amountOfDice"));
        assertEquals(4, json2.get("dieSides"));
        assertEquals(2, json2.get("modifier"));
    }
}
