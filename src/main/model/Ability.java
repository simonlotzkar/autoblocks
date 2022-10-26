package model;

public class Ability {
    // required fields
    private final String name;
    private final String description;

    // EFFECTS: constructs an ability with the given parameters
    public Ability(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    // EFFECTS: returns ability name
    public String getName() {
        return name;
    }

    // EFFECTS: returns ability description
    public String getDescription() {
        return description;
    }
}
