package model.statblockfields;

import exceptions.IncompleteFieldException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class LegendaryMechanicsTest {
    String testDescription0 = "bloo blah dee bop";
    String testDescription1 = "This Is A Serious Description";

    Ability testLegendaryAction0;
    Ability testLegendaryAction1;
    Ability testLegendaryAction2;

    List<Ability> testLegendaryActions0 = new ArrayList<>();
    List<Ability> testLegendaryActions1 = new ArrayList<>();

    LegendaryMechanics testLegendaryMechanics0;
    LegendaryMechanics testLegendaryMechanics1;

    @BeforeEach
    public void beforeEach() {
        try {
            testLegendaryAction0 = new Ability("super bloop", "boppity boop hard");
            testLegendaryAction1 = new Ability("Mega Blast", "Super Big Boom");
            testLegendaryAction2 = new Ability("Teleport ME", "makes ME teleport");

            testLegendaryActions0.add(testLegendaryAction0);
            testLegendaryActions1.add(testLegendaryAction1);
            testLegendaryActions1.add(testLegendaryAction2);
        } catch (IncompleteFieldException e) {
            fail("Should not have thrown an exception!");
        } try {
            testLegendaryMechanics0 = new LegendaryMechanics(testDescription0, testLegendaryActions0);
            testLegendaryMechanics1 = new LegendaryMechanics(testDescription1, testLegendaryActions1);
        } catch (IncompleteFieldException e) {
            fail("Should not have thrown an exception!");
        }
    }

    @Test
    public void testConstructor() {
        assertEquals(testDescription0, testLegendaryMechanics0.getLegendaryDescription());
        assertEquals(testLegendaryActions0, testLegendaryMechanics0.getLegendaryActions());

        assertEquals(testDescription1, testLegendaryMechanics1.getLegendaryDescription());
        assertEquals(testLegendaryActions1, testLegendaryMechanics1.getLegendaryActions());
    }

    @Test
    public void testToJson() {
        JSONObject json0 = testLegendaryMechanics0.toJson();
        JSONObject json1 = testLegendaryMechanics1.toJson();

        assertEquals(testDescription0, json0.get("legendaryDescription"));
        assertEquals(1, (json0.getJSONArray("legendaryActions")).length());

        assertEquals(testDescription1, json1.get("legendaryDescription"));
        assertEquals(2, (json1.getJSONArray("legendaryActions")).length());
    }
}
