package model.statblockfields;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbilityScoresTest {
    AbilityScores testAbilityScores0;
    AbilityScores testAbilityScores1;
    AbilityScores testAbilityScores2;
    AbilityScores testAbilityScores3;

    @BeforeEach
    public void beforeEach() {
        testAbilityScores0 = new AbilityScores(1, 1, 1, 1, 1, 1);
        testAbilityScores1 = new AbilityScores(10, 10, 10, 10, 10, 10);
        testAbilityScores2 = new AbilityScores(17, 18, 17, 18, 17, 18);
        testAbilityScores3 = new AbilityScores(7, 8, 7, 8, 7, 8);
    }

    @Test
    public void testConstructor() {
        assertEquals(1, testAbilityScores0.getStrength());

        assertEquals(10, testAbilityScores1.getDexterity());

        assertEquals(17, testAbilityScores2.getConstitution());
        assertEquals(18, testAbilityScores2.getIntelligence());

        assertEquals(7, testAbilityScores3.getWisdom());
        assertEquals(8, testAbilityScores3.getCharisma());
    }

    @Test
    public void testConvertToModifier() {
        assertEquals(-5, testAbilityScores0.getModifier("strength"));

        assertEquals(0, testAbilityScores1.getModifier("dexterity"));

        assertEquals(3, testAbilityScores2.getModifier("constitution"));
        assertEquals(4, testAbilityScores2.getModifier("intelligence"));

        assertEquals(-2, testAbilityScores3.getModifier("wisdom"));
        assertEquals(-1, testAbilityScores3.getModifier("charisma"));
    }

    @Test
    public void testToJson() {
        JSONObject json0 = testAbilityScores0.toJson();
        JSONObject json1 = testAbilityScores1.toJson();
        JSONObject json2 = testAbilityScores2.toJson();
        JSONObject json3 = testAbilityScores3.toJson();

        assertEquals(1, json0.get("strength"));

        assertEquals(10, json1.get("dexterity"));

        assertEquals(17, json2.get("constitution"));
        assertEquals(18, json2.get("intelligence"));

        assertEquals(7, json3.get("wisdom"));
        assertEquals(8, json3.get("charisma"));
    }
}
