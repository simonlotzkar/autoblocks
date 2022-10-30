package persistence;

import org.json.JSONObject;

// CITATION: from Writable.java in JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    // CITATION: from Writable.java in JsonSerializationDemo
    JSONObject toJson();
}
