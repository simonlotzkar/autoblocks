package model.statblockfields;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpeedsTest {
    Integer testSpeed0 = 30;
    Integer testSpeed1 = 45;
    Integer testBurrowSpeed0 = 28;
    Integer testClimbSpeed0 = 34;
    Integer testFlySpeed0 = 59;
    Integer testSwimSpeed0 = 55;

    Speeds testSpeeds0;
    Speeds testSpeeds1;

    @BeforeEach
    public void beforeEach() {
        testSpeeds0 = new Speeds.SpeedsBuilder(testSpeed0)
                .burrow(testBurrowSpeed0)
                .climb(testClimbSpeed0)
                .fly(testFlySpeed0)
                .swim(testSwimSpeed0)
                .build();
        testSpeeds1 = new Speeds.SpeedsBuilder(testSpeed1)
                .build();
    }

    @Test
    public void testConstructor() {
        assertEquals(testSpeed0, testSpeeds0.getSpeed());
        assertEquals(testBurrowSpeed0, testSpeeds0.getBurrow());
        assertEquals(testClimbSpeed0, testSpeeds0.getClimb());
        assertEquals(testFlySpeed0, testSpeeds0.getFly());
        assertEquals(testSwimSpeed0, testSpeeds0.getSwim());

        assertEquals(testSpeed1, testSpeeds1.getSpeed());
        assertEquals(0, testSpeeds1.getBurrow());
        assertEquals(0, testSpeeds1.getClimb());
        assertEquals(0, testSpeeds1.getFly());
        assertEquals(0, testSpeeds1.getSwim());
    }

    @Test
    public void testGetSpeedsString() {
        String testString0 = testSpeed0 + "ft, "
                + "burrow " + testBurrowSpeed0 + "ft, "
                + "climb " + testClimbSpeed0 + "ft, "
                + "fly " + testFlySpeed0 + "ft, "
                + "swim " + testSwimSpeed0 + "ft.";
        String testString1 = testSpeed1 + "ft.";

        assertEquals(testString0, testSpeeds0.getString());
        assertEquals(testString1, testSpeeds1.getString());
    }

    @Test
    public void testToJson() {
        JSONObject json0 = testSpeeds0.toJson();
        JSONObject json1 = testSpeeds1.toJson();

        assertEquals(testSpeed0, json0.get("speed"));
        assertEquals(testBurrowSpeed0, json0.get("burrow"));
        assertEquals(testClimbSpeed0, json0.get("climb"));
        assertEquals(testFlySpeed0, json0.get("fly"));
        assertEquals(testSwimSpeed0, json0.get("swim"));

        assertEquals(testSpeed1, json1.get("speed"));
        assertEquals(0, json1.get("burrow"));
        assertEquals(0, json1.get("climb"));
        assertEquals(0, json1.get("fly"));
        assertEquals(0, json1.get("swim"));
    }
}
