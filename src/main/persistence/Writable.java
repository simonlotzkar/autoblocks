package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns this as JSON object
    // CITATION: from Writable.java in JsonSerializationDemo
    JSONObject toJson();
}
