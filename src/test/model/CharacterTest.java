package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CharacterTest extends StatBlockTest {
    private Character character0;
    private Character character1;
    private Character character2;

    private List<Character> encounter0 = new ArrayList<>();

    // EFFECTS: constructs testcharacter0
    private void initializeCharacter0() {
        character0 = new Character(statBlock0, "s");
    }

    // EFFECTS: constructs testcharacter1
    private void initializeCharacter1() {
        //stub
    }

    // EFFECTS: constructs testcharacter2
    private void initializeCharacter2() {
        //stub
    }

    @BeforeEach
    public void beforeEach() {
        //stub
    }

    @Test
    public void testConstructor0() {
        //stub
    }

    @Test
    public void testConstructor1() {
        //stub
    }

    @Test
    public void testConstructor2() {
        //stub
    }

    @Test
    public void testChangeHP() {
        //stub
    }

    @Test
    public void testChangeHPOverMax() {
        //stub
    }

    @Test
    public void testChangeHPNegative() {
        //stub
    }

    @Test
    public void testGetHPString() {
        //stub
    }

    @Test
    public void testToJson() {
        //JSONObject json0 = X.toJson();
    }

    @Test
    public void testToString() {
        //stub
    }
}
