package model.statblockfields;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LanguagesTest {
    String testLanguage0 = "elvish";
    String testLanguage1 = "Dwarvish";
    String testLanguage2 = "Common";

    List<String> testLanguagesList0 = new ArrayList<>();
    List<String> testLanguagesList1 = new ArrayList<>();

    Integer testTelepathy0 = 30;

    Languages testLanguages0;
    Languages testLanguages1;

    @BeforeEach
    public void beforeEach() {
        testLanguagesList0.add(testLanguage0);
        testLanguagesList1.add(testLanguage1);
        testLanguagesList1.add(testLanguage2);
        testLanguages0 = new Languages.LanguagesBuilder(testLanguagesList0)
                .telepathy(testTelepathy0)
                .build();
        testLanguages1 = new Languages.LanguagesBuilder(testLanguagesList1)
                .build();
    }

    @Test
    public void testConstructor() {
        assertEquals(testLanguagesList0, testLanguages0.getLanguagesList());
        assertEquals(testTelepathy0, testLanguages0.getTelepathy());

        assertEquals(testLanguagesList1, testLanguages1.getLanguagesList());
        assertEquals(0, testLanguages1.getTelepathy());
    }

    @Test
    public void testGetString() {
        String testString0 = "elvish, telepathy " + testTelepathy0 + "ft";
        String testString1 = "Dwarvish, Common, ";

        assertEquals(testString0, testLanguages0.getString());
        assertEquals(testString1, testLanguages1.getString());
    }

    @Test
    public void testToJson() {
        JSONObject json0 = testLanguages0.toJson();
        JSONObject json1 = testLanguages1.toJson();

        assertEquals(2, json0.length());
        assertEquals(testTelepathy0, json0.get("telepathy"));

        assertEquals(2, json1.length());
        assertEquals(0, json1.get("telepathy"));
    }
}
