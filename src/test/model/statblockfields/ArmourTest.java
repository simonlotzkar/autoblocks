package model.statblockfields;

import exceptions.IncompleteFieldException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArmourTest {
    Integer testAC0 = 10;
    Integer testAC1 = 19;
    String testArmourName0 = "hide armour";
    Integer testMagicArmour0 = 15;

    Armour testArmour0;
    Armour testArmour1;

    @BeforeEach
    public void beforeEach() {
        try {
            testArmour0 = new Armour.ArmourBuilder(testAC0)
                    .armourName(testArmourName0)
                    .magicArmour(testMagicArmour0)
                    .build();
            testArmour1 = new Armour.ArmourBuilder(testAC1)
                    .build();
        } catch (IncompleteFieldException e) {
            fail("Should not have thrown an exception!");
        }
    }

    @Test
    public void testConstructor() {
        assertEquals(testAC0, testArmour0.getAC());
        assertEquals(testMagicArmour0, testArmour0.getMagicArmour());
        assertEquals(testArmourName0, testArmour0.getArmourName());

        assertEquals(testAC1, testArmour1.getAC());
        assertEquals(0, testArmour1.getMagicArmour());
        assertNull(testArmour1.getArmourName());
    }

    @Test
    public void testGetString() {
        String testString0 = "10 (hide armour) or 15 (with Magic Armour)";
        String testString1 = "19";

        assertEquals(testString0, testArmour0.toString());
        assertEquals(testString1, testArmour1.toString());
    }

    @Test
    public void testToJson() {
        JSONObject json0 = testArmour0.toJson();
        JSONObject json1 = testArmour1.toJson();

        assertEquals(10, json0.get("ac"));
        assertEquals("hide armour", json0.get("armourName"));
        assertEquals(15, json0.get("magicArmour"));

        assertEquals(19, json1.get("ac"));
        assertFalse(json1.has("armourName"));
        assertEquals(0, json1.get("magicArmour"));
    }
}
