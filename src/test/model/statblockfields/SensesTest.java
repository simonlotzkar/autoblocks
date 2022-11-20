package model.statblockfields;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SensesTest {
    Integer testPassivePerception0 = 12;
    Integer testPassivePerception1 = 4;
    Integer testBlindSight0 = 50;
    Integer testTrueSight0 = 100;
    Integer testDarkVision0 = 40;
    Integer testTremorSense0 = 200;

    Senses testSenses0;
    Senses testSenses1;

    @BeforeEach
    public void beforeEach() {
        testSenses0 = new Senses.SensesBuilder(testPassivePerception0)
                .trueSight(testTrueSight0)
                .blindSight(testBlindSight0)
                .darkVision(testDarkVision0)
                .tremorSense(testTremorSense0)
                .build();
        testSenses1 = new Senses.SensesBuilder(testPassivePerception1).build();
    }

    @Test
    public void testConstructor() {
        assertEquals(testPassivePerception0, testSenses0.getPassivePerception());
        assertEquals(testTrueSight0, testSenses0.getTrueSight());
        assertEquals(testBlindSight0, testSenses0.getBlindSight());
        assertEquals(testDarkVision0, testSenses0.getDarkVision());
        assertEquals(testTremorSense0, testSenses0.getTremorSense());

        assertEquals(testPassivePerception1, testSenses1.getPassivePerception());
        assertEquals(0, testSenses1.getTrueSight());
        assertEquals(0, testSenses1.getBlindSight());
        assertEquals(0, testSenses1.getDarkVision());
        assertEquals(0, testSenses1.getTremorSense());
    }

    @Test
    public void testGetString() {
        String string0 = "Passive perception " + testPassivePerception0
                + ", blindsight " + testBlindSight0
                + "ft, darkvision " + testDarkVision0
                + "ft, tremorSense " + testTremorSense0
                + "ft, truesight " + testTrueSight0
                + "ft";
        String string1 = "Passive perception " + testPassivePerception1;

        assertEquals(string0, testSenses0.getString());
        assertEquals(string1, testSenses1.getString());
    }

    @Test
    public void testToJson() {
        JSONObject json0 = testSenses0.toJson();
        JSONObject json1 = testSenses1.toJson();

        assertEquals(testPassivePerception0, json0.get("passivePerception"));
        assertEquals(testTrueSight0, json0.get("trueSight"));
        assertEquals(testBlindSight0, json0.get("blindSight"));
        assertEquals(testDarkVision0, json0.get("darkVision"));
        assertEquals(testTremorSense0, json0.get("tremorSense"));

        assertEquals(testPassivePerception1, json1.get("passivePerception"));
        assertEquals(0, json1.get("blindSight"));
        assertEquals(0, json1.get("trueSight"));
        assertEquals(0, json1.get("darkVision"));
        assertEquals(0, json1.get("tremorSense"));
    }
}
