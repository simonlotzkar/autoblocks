package persistence;

import model.Ability;
import model.Action;
import model.RollFormula;
import model.StatBlock;
import model.statblockfields.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// CITATION: based on JsonSerializationDemo
public interface JsonTest {
    // CITATION: from JsonSerializationDemo
    private void checkStatBlock(StatBlock statBlock, Title title, int xp, RollFormula hpFormula, int proficiency,
                                  Armour armour, Speeds speeds, Senses senses, AbilityScores abilityScores,
                                  List<Ability> abilities, List<Action> actions, Languages languages,
                                  SavingThrowProficiencies savingThrowProficiencies,
                                  SkillProficiencies skillProficiencies, ConditionImmunities conditionImmunities,
                                  Resistances resistances, LegendaryMechanics legendaryMechanics) {
        assertEquals(title, statBlock.getTitle());
        assertEquals(xp, statBlock.getXP());
        assertEquals(hpFormula, statBlock.getHpFormula());
        assertEquals(proficiency, statBlock.getProficiency());
        assertEquals(armour, statBlock.getArmour());
        assertEquals(speeds, statBlock.getSpeeds());
        assertEquals(senses, statBlock.getSenses());
        assertEquals(abilityScores, statBlock.getAbilityScores());
        assertEquals(abilities, statBlock.getAbilities());
        assertEquals(actions, statBlock.getActions());
        assertEquals(languages, statBlock.getLanguages());
        assertEquals(savingThrowProficiencies, statBlock.getSavingThrowProficiencies());
        assertEquals(skillProficiencies, statBlock.getSkillProficiencies());
        assertEquals(conditionImmunities, statBlock.getConditionImmunities());
        assertEquals(resistances, statBlock.getResistances());
        assertEquals(legendaryMechanics, statBlock.getLegendaryMechanics());
    }
}