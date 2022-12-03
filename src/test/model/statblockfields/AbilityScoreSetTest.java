package model.statblockfields;

import enums.AbilityScore;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbilityScoreSetTest {
    AbilityScoreSet testAbilityScoreSet0;
    AbilityScoreSet testAbilityScoreSet1;
    AbilityScoreSet testAbilityScoreSet2;
    AbilityScoreSet testAbilityScoreSet3;

    @BeforeEach
    public void beforeEach() {
        testAbilityScoreSet0 = new AbilityScoreSet(1, 1, 1, 1, 1, 1);
        testAbilityScoreSet1 = new AbilityScoreSet(10, 10, 10, 10, 10, 10);
        testAbilityScoreSet2 = new AbilityScoreSet(17, 18, 17, 18, 17, 18);
        testAbilityScoreSet3 = new AbilityScoreSet(7, 8, 7, 8, 7, 8);
    }

    @Test
    public void testConstructor() {
        assertEquals(1, testAbilityScoreSet0.getStrength());

        assertEquals(10, testAbilityScoreSet1.getDexterity());

        assertEquals(17, testAbilityScoreSet2.getConstitution());
        assertEquals(18, testAbilityScoreSet2.getIntelligence());

        assertEquals(7, testAbilityScoreSet3.getWisdom());
        assertEquals(8, testAbilityScoreSet3.getCharisma());
    }

    @Test
    public void testConvertToModifier() {
        assertEquals(-5, testAbilityScoreSet0.toModifier(AbilityScore.STRENGTH));

        assertEquals(0, testAbilityScoreSet1.toModifier(AbilityScore.DEXTERITY));

        assertEquals(3, testAbilityScoreSet2.toModifier(AbilityScore.CONSTITUTION));
        assertEquals(4, testAbilityScoreSet2.toModifier(AbilityScore.INTELLIGENCE));

        assertEquals(-2, testAbilityScoreSet3.toModifier(AbilityScore.WISDOM));
        assertEquals(-1, testAbilityScoreSet3.toModifier(AbilityScore.CHARISMA));
    }

    @Test
    public void testToJson() {
        JSONObject json0 = testAbilityScoreSet0.toJson();
        JSONObject json1 = testAbilityScoreSet1.toJson();
        JSONObject json2 = testAbilityScoreSet2.toJson();
        JSONObject json3 = testAbilityScoreSet3.toJson();

        assertEquals(1, json0.get("strength"));

        assertEquals(10, json1.get("dexterity"));

        assertEquals(17, json2.get("constitution"));
        assertEquals(18, json2.get("intelligence"));

        assertEquals(7, json3.get("wisdom"));
        assertEquals(8, json3.get("charisma"));
    }
}
