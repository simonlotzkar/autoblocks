package model.statblockfields;

import model.Character;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Represents...
public class Title implements Writable {
    // required fields
    private final String name;
    private final String type;
    private final String size;
    private final String alignment;

    // optional fields
    private String group;

    // EFFECTS: constructs Title using a builder
    public Title(TitleBuilder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.size = builder.size;
        this.alignment = builder.alignment;
        this.group = builder.group;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // setters:
    // EFFECTS: set group
    public void setGroup(String group) {
        this.group = group;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters:
    // EFFECTS: gets name
    public String getName() {
        return name;
    }

    // EFFECTS: gets type
    public String getType() {
        return type;
    }

    // EFFECTS: gets size
    public String getSize() {
        return size;
    }

    // EFFECTS: gets alignment
    public String getAlignment() {
        return alignment;
    }

    // EFFECTS: get group
    public String getGroup() {
        return group;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // builder class
    public static class TitleBuilder {
        // required fields
        private final String name;
        private final String type;
        private final String size;
        private final String alignment;

        // optional fields
        private String group;

        // constructs a builder with required fields
        public TitleBuilder(String name, String type, String size, String alignment) {
            this.name = name;
            this.type = type;
            this.size = size;
            this.alignment = alignment;
        }

        // EFFECTS: returns a new Title with required fields,
        //          and any optional fields that had their builder called.
        public Title build() {
            return new Title(this);
        }

        // EFFECTS: returns a builder that assigns given group to Title
        public TitleBuilder group(String group) {
            this.group = group;
            return this;
        }
    }

    // constructs a json object with the fields of the title
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("type", type);
        json.put("size", size);
        json.put("alignment", alignment);
        if (group != null) {
            json.put("group", group);
        }
        return json;
    }
}
