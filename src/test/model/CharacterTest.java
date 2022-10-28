package model;

import model.statblockfields.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CharacterTest {
    private StatBlock testStatBlock0;
    private StatBlock testStatBlock1;

    private Character testCharacter0;
    private Character testCharacter1;

    Ability ability0;
    Ability ability1;
    Ability ability2;
    List<Ability> abilities0;
    List<Ability> abilities1;

    String legendaryDescription;

    List<String> languages0;
    List<String> languages1;

    Action action0;
    Action action1;
    Action action2;
    List<Action> actions0;
    List<Action> actions1;

    // EFFECTS: constructs actions0
    private void initializeActions0() {
        action0 = new Action("action", "description", "damage type", "reach",
                new RollFormula(0, 0, 0),  //hit formula
                new RollFormula(0, 0, 0)); //damage formula

        actions0 = new ArrayList<>();
        actions0.add(action0);
    }

    // EFFECTS: constructs actions1
    private void initializeActions1() {
        action1 = new Action("action", "description", "damage type", "reach",
                new RollFormula(2, 4, -3),  //hit formula
                new RollFormula(2, 6, 3)); //damage formula
        action2 = new Action("action", "description", "damage type", "reach",
                new RollFormula(3, 8, -4),  //hit formula
                new RollFormula(3, 12, 4)); //damage formula

        actions1 = new ArrayList<>();
        actions1.add(action1);
        actions1.add(action2);
    }

    // EFFECTS: constructs teststatblock0
    private void initializeStatBlock0() {
        testStatBlock0 = new StatBlock.StatBlockBuilder(
                (new Title.TitleBuilder("Character0", "type", "size", "alignment").build()),
                0, new RollFormula(0, 0, 0), 0,
                (new Armour.ArmourBuilder(0).build()),
                (new Speeds.SpeedsBuilder(0).build()),
                (new Senses.SensesBuilder(0).build()),
                (new AbilityScores(0, 0, 0, 0, 0, 0)),
                (abilities0),
                actions0,
                (new Languages.LanguagesBuilder(languages0).build()))
                .build();
    }

    // EFFECTS: constructs teststatblock0 and testcharacter0
    private void initializeCharacter0() {
        testCharacter0 = new Character.CharacterBuilder(
                testStatBlock0,
                (new Title.TitleBuilder("Character0", "type", "size", "alignment").build()),
                0, new RollFormula(0, 0, 0), 0,
                (new Armour.ArmourBuilder(0).build()),
                (new Speeds.SpeedsBuilder(0).build()),
                (new Senses.SensesBuilder(0).build()),
                (new AbilityScores(0, 0, 0, 0, 0, 0)),
                (abilities0),
                actions0,
                (new Languages.LanguagesBuilder(languages0).build()))
                .build();
    }

    // EFFECTS: constructs teststatblock1
    private void initializeStatBlock1() {
        testStatBlock1 = new StatBlock.StatBlockBuilder(
                (new Title.TitleBuilder("Character1", "type", "size", "alignment").build()),
                1000, new RollFormula(2, 20, 9), 3,
                (new Armour.ArmourBuilder(20).armourName("armour").magicArmour(20).build()),
                (new Speeds.SpeedsBuilder(30).swim(10).fly(30).climb(300).burrow(19).build()),
                (new Senses.SensesBuilder(15).tremorSense(40).darkVision(30).blindSight(20).trueSight(30).build()),
                (new AbilityScores(20, 19, 18, 23, 10, 4)),
                (abilities1),
                actions1,
                (new Languages.LanguagesBuilder(languages1).telepathy(30).build()))
                .savingThrowProficiencies(new SavingThrowProficiencies.SavingThrowProficienciesBuilder()
                        .charismaProficiency(true)
                        .constitutionProficiency(true)
                        .dexterityProficiency(true)
                        .build())
                .skillProficiencies(new SkillProficiencies.SkillProficienciesBuilder()
                        .intimidation(true)
                        .deception(true)
                        .acrobatics(true)
                        .build())
                .conditionImmunities(new ConditionImmunities.ConditionImmunitiesBuilder()
                        .blinded(true)
                        .charmed(true)
                        .deafened(true)
                        .build())
                .resistances(new Resistances.ResistancesBuilder()
                        .radiant("resistant")
                        .slashing("immune")
                        .thunder("resistant")
                        .build())
                .legendaryMechanics(new LegendaryMechanics(legendaryDescription, abilities1))
                .build();
    }

    // EFFECTS: constructs testcharacter1
    private void initializeCharacter1() {
        testCharacter1 = new Character.CharacterBuilder(testStatBlock1,
                (new Title.TitleBuilder("Character1", "type", "size", "alignment").build()),
                1000, new RollFormula(2, 20, 9), 3,
                (new Armour.ArmourBuilder(20).armourName("armour").magicArmour(20).build()),
                (new Speeds.SpeedsBuilder(30).swim(10).fly(30).climb(300).burrow(19).build()),
                (new Senses.SensesBuilder(15).tremorSense(40).darkVision(30).blindSight(20).trueSight(30).build()),
                (new AbilityScores(20, 19, 18, 23, 10, 4)),
                (abilities1),
                actions1,
                (new Languages.LanguagesBuilder(languages1).telepathy(30).build()))
                .build();
    }

    @BeforeEach
    public void beforeEach() {
        languages0 = new ArrayList<>();
        languages0.add("language");
        languages1 = new ArrayList<>();
        languages1.add("language 1");
        languages1.add("language 2");

        ability0 = new Ability("action name", "description");
        abilities0 = new ArrayList<>();
        abilities0.add(ability0);
        ability1 = new Ability("action name 1", "description 1");
        ability2 = new Ability("action name 2", "description 2");
        abilities1 = new ArrayList<>();
        abilities1.add(ability1);
        abilities1.add(ability2);

        initializeActions0();
        initializeStatBlock0();
        initializeCharacter0();

        initializeActions1();
        initializeStatBlock1();
        initializeCharacter1();
    }

    @Test
    public void testConstructor() {
        //stub
    }

    @Test
    public void testChangeHP() {
        //
    }

    @Test
    public void testChangeHPOverMax() {
        //stub
    }

    @Test
    public void testChangeHPNegative() {
        //stub
    }
}
