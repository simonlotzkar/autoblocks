package model.statblockfields;

import exceptions.IncompleteFieldException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TitleTest {
    private final String testName0 = "test title 0";
    private final String testName1 = "Test Title 1";
    private final String testSize0 = "medium";
    private final String testSize1 = "Small";
    private final String testType0 = "humanoid";
    private final String testType1 = "Dragon";
    private final String testAlignment0 = "lawful good";
    private final String testAlignment1 = "chaotic evil";

    private Title testTitle0;
    private Title testTitle1;

    @BeforeEach
    public void beforeEach() {
        try {
            testTitle0 = new Title(testName0, testSize0, testType0, testAlignment0);
            testTitle1 = new Title(testName1, testSize1, testType1, testAlignment1);
        } catch (IncompleteFieldException e) {
            fail("Should not have thrown an exception!");
        }
    }

    @Test
    public void testConstructor() {
        assertEquals(testName0, testTitle0.getName());
        assertEquals(testSize0, testTitle0.getSize());
        assertEquals(testType0, testTitle0.getType());
        assertEquals(testAlignment0, testTitle0.getAlignment());

        assertEquals(testName1, testTitle1.getName());
        assertEquals(testSize1, testTitle1.getSize());
        assertEquals(testType1, testTitle1.getType());
        assertEquals(testAlignment1, testTitle1.getAlignment());
    }

    @Test
    public void testToJson() {
        JSONObject json0 = testTitle0.toJson();
        JSONObject json1 = testTitle1.toJson();

        assertEquals(testName0, json0.get("name"));
        assertEquals(testSize0, json0.get("size"));
        assertEquals(testType0, json0.get("type"));
        assertEquals(testAlignment0, json0.get("alignment"));

        assertEquals(testName1, json1.get("name"));
        assertEquals(testSize1, json1.get("size"));
        assertEquals(testType1, json1.get("type"));
        assertEquals(testAlignment1, json1.get("alignment"));
    }
}
