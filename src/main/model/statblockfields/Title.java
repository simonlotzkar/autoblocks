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

    // MODIFIES: this
    // EFFECTS: constructs Title using the given fields and throws an exception if any of the required fields are empty
    public Title(String name, String size, String type, String alignment)
            throws IncompleteFieldException {
        if (name == null || name.isEmpty()) {
            throw new IncompleteFieldException("(title) name is empty or null");
        } else if (size == null || size.isEmpty()) {
            throw new IncompleteFieldException("(title) size is empty or null");
        } else if (type == null || type.isEmpty()) {
            throw new IncompleteFieldException("(title) type is empty or null");
        } else if (alignment == null || alignment.isEmpty()) {
            throw new IncompleteFieldException("(title) alignment is empty or null");
        } else {
            this.name = name;
            this.type = type;
            this.size = size;
            this.alignment = alignment;
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
        return json;
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
}
