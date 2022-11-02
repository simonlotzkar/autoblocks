package model.statblockfields;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TitleTest {
    private final String testName0 = "test title 0";
    private final String testName1 = "Test Title 1";
    private final String testType0 = "humanoid";
    private final String testType1 = "Dragon";
    private final String testSize0 = "medium";
    private final String testSize1 = "Small";
    private final String testAlignment0 = "lawful good";
    private final String testAlignment1 = "chaotic evil";
    private final String testGroup0 = "test group";

    private Title testTitle0;
    private Title testTitle1;

    @BeforeEach
    public void beforeEach() {
        testTitle0 = new Title.TitleBuilder(testName0, testType0, testSize0, testAlignment0)
                .group(testGroup0)
                .build();

        testTitle1 = new Title.TitleBuilder(testName1, testType1, testSize1, testAlignment1)
                .build();
    }

    @Test
    public void testConstructor() {
        assertEquals(testName0, testTitle0.getName());
        assertEquals(testType0, testTitle0.getType());
        assertEquals(testSize0, testTitle0.getSize());
        assertEquals(testAlignment0, testTitle0.getAlignment());
        assertEquals(testGroup0, testTitle0.getGroup());

        assertEquals(testName1, testTitle1.getName());
        assertEquals(testType1, testTitle1.getType());
        assertEquals(testSize1, testTitle1.getSize());
        assertEquals(testAlignment1, testTitle1.getAlignment());
        assertNull(testTitle1.getGroup());
    }

    @Test
    public void testSetGroup() {
        assertEquals(testGroup0, testTitle0.getGroup());
        testTitle0.setGroup("orcs");
        assertEquals("orcs", testTitle0.getGroup());

        testTitle1.setGroup("dogs");
        assertEquals("dogs", testTitle1.getGroup());
    }

    @Test
    public void testToJson() {
        JSONObject json0 = testTitle0.toJson();
        JSONObject json1 = testTitle1.toJson();

        assertEquals(testName0, json0.get("name"));
        assertEquals(testType0, json0.get("type"));
        assertEquals(testSize0, json0.get("size"));
        assertEquals(testAlignment0, json0.get("alignment"));
        assertEquals(testGroup0, json0.get("group"));

        assertEquals(testName1, json1.get("name"));
        assertEquals(testType1, json1.get("type"));
        assertEquals(testSize1, json1.get("size"));
        assertEquals(testAlignment1, json1.get("alignment"));
        assertFalse(json1.has("group"));
    }
}
