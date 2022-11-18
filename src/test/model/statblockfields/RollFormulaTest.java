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
    private RollFormula testRollFormula5;
    private RollFormula testRollFormula6;
    private RollFormula testRollFormula7;
    private RollFormula testRollFormula8;
    private RollFormula testRollFormula9;

    @BeforeEach
    public void beforeEach() {
        testRollFormula0 = new RollFormula(0, 0, 0);
        testRollFormula1 = new RollFormula(1, 1, 0);
        testRollFormula2 = new RollFormula(1, 4, 2);
        testRollFormula3 = new RollFormula(1, 7, 0);
        testRollFormula4 = new RollFormula(3, 30, -3);
        testRollFormula5 = new RollFormula(3, 20, -4);
        testRollFormula6 = new RollFormula(2, 12, -20);
        testRollFormula7 = new RollFormula(5, 4, 3);
        testRollFormula8 = new RollFormula(100, 5, 0);
        testRollFormula9 = new RollFormula(7, 3, 4);
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
    }

    @Test
    public void testRoll() {
        int testRoll2 = testRollFormula2.roll();
        int testRoll3  = testRollFormula3.roll();
        int testRoll4 = testRollFormula4.roll();
        int testRoll5 = testRollFormula5.roll();
        int testRoll6 = testRollFormula6.roll();
        int testRoll7 = testRollFormula7.roll();
        int testRoll8 = testRollFormula8.roll();
        int testRoll9 = testRollFormula9.roll();

        assertEquals(0, testRollFormula0.roll());
        assertEquals(1, testRollFormula1.roll());
        assertTrue(3 <= testRoll2 && testRoll2 <= 6);
        assertTrue(1 <= testRoll3 && testRoll3 <= 7);
        assertTrue(0 <= testRoll4 && testRoll4 <= 87);
        assertTrue(-1 <= testRoll5 && testRoll5 <= 117);
        assertTrue(-18 <= testRoll6 && testRoll6 <= 4);
        assertTrue(8 <= testRoll7 && testRoll7 <= 23);
        assertTrue(100 <= testRoll8 && testRoll8 <= 500);
        assertTrue(11 <= testRoll9 && testRoll9 <= 25);
    }

    @Test
    public void testGetString() {
        assertEquals("0d0", testRollFormula0.getString());
        assertEquals("1d1", testRollFormula1.getString());
        assertEquals("1d4+2", testRollFormula2.getString());
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
