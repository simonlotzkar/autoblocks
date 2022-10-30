package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

// Represents...
public class Encounter implements Writable {
    private final String name;
    private final List<Character> characters;

    // EFFECTS: constructs an encounter with the given parameters
    public Encounter(String name, List<Character> characters) {
        this.name = name;
        this.characters = characters;
    }

    // EFFECTS: gets the encounter characters list
    public List<Character> getList() {
        return characters;
    }

    // EFFECTS: gets the encounter name
    public String getName() {
        return name;
    }

    // EFFECTS: adds the given character to the encounter list
    public void add(Character character) {
        characters.add(character);
    }

    // EFFECTS: adds the given character to the encounter list
    public void remove(Character character) {
        characters.remove(character);
    }

    // EFFECTS: returns true if encounter contains a character with the given statblock name, excluding its suffix,
    //          false otherwise
    public boolean contains(StatBlock statBlock) {
        for (Character c : characters) {
            if (c.getTitle().getName().toLowerCase().contains(statBlock.getTitle().getName().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("characters", charactersToJson());
        return json;
    }

    // EFFECTS: returns characters in this encounter as a JSON array
    private JSONArray charactersToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Character c : characters) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }
}
