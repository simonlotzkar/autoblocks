package model.statblockfields;

import exceptions.IncompleteFieldException;
import org.json.JSONObject;
import persistence.Writable;

// Represents a title with a name, size, type, alignment, and group which can be null or empty
public class Title implements Writable {
    // required fields
    private final String name;
    private final String size;
    private final String type;
    private final String alignment;
    private String group;

    // MODIFIES: this
    // EFFECTS: constructs Title using the given fields and throws an exception if any of the required fields are empty
    public Title(String name, String size, String type, String alignment, String group)
            throws IncompleteFieldException {
        if (name == null || name.isEmpty()) {
            throw new IncompleteFieldException("given name is empty or null");
        } else if (size == null || size.isEmpty()) {
            throw new IncompleteFieldException("given size is empty or null");
        } else if (type == null || type.isEmpty()) {
            throw new IncompleteFieldException("given type is empty or null");
        } else if (alignment == null || alignment.isEmpty()) {
            throw new IncompleteFieldException("given alignment is empty or null");
        } else {
            this.name = name;
            this.type = type;
            this.size = size;
            this.alignment = alignment;
            this.group = group;
        }
    }

    @Override
    // EFFECTS: constructs a json object with the fields of the title
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("type", type);
        json.put("size", size);
        json.put("alignment", alignment);
        if (group != null && !group.isEmpty()) {
            json.put("group", group);
        }
        return json;
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
}
