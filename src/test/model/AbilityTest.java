package model;

import model.statblockfields.Ability;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbilityTest {
    private String testAbilityName0;
    private String testAbilityName1;
    private String testAbilityDescription0;
    private String testAbilityDescription1;

    private Ability testAbility0;
    private Ability testAbility1;

    @BeforeEach
    public void beforeEach() {
        testAbilityName0 = "test ability 0";
        testAbilityName1 = "test ability 1";
        testAbilityDescription0 = "this is a test description! (form 0)";
        testAbilityDescription1 = "this is a test description! (form 1)";

        testAbility0 = new Ability(testAbilityName0, testAbilityDescription0);
        testAbility1 = new Ability(testAbilityName1, testAbilityDescription1);
    }

    @Test
    public void testConstructor() {
        assertEquals(testAbilityName0, testAbility0.getName());
        assertEquals(testAbilityName1, testAbility1.getName());
        assertEquals(testAbilityDescription0, testAbility0.getDescription());
        assertEquals(testAbilityDescription1, testAbility1.getDescription());
    }
}
