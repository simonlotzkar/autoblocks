package model.statblockfields;

import model.Ability;

import java.util.List;

public class LegendaryMechanics {
    // required fields
    private final String legendaryDescription;
    private final List<Ability> legendaryActions;

    // constructs a LegendaryMechanics with given fields
    public LegendaryMechanics(String legendaryDescription, List<Ability> legendaryActions) {
        this.legendaryDescription = legendaryDescription;
        this.legendaryActions = legendaryActions;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    // EFFECTS: gets the legendary mechanics description
    public String getLegendaryDescription() {
        return legendaryDescription;
    }

    // EFFECTS: gets the legendary actions list
    public List<Ability> getLegendaryActions() {
        return legendaryActions;
    }
}
