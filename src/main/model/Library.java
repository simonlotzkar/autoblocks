package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

public class Library implements Writable {
    private String name;
    private List<StatBlock> statBlocks;

    public Library(String name, List<StatBlock> statBlocks) {
        this.name = name;
        this.statBlocks = statBlocks;
    }

    // EFFECTS: gets the library list
    public List<StatBlock> getStatBlocks() {
        return statBlocks;
    }

    // EFFECTS: gets the library name
    public String getName() {
        return name;
    }

    // EFFECTS: adds the given statblock to the library list
    public void addStatBlock(StatBlock statBlock) {
        statBlocks.add(statBlock);
    }

    // CITATION: from JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("statBlocks", statBlocksToJson());
        return json;
    }

    // EFFECTS: returns statblocks in this library as a JSON array
    // CITATION: from JsonSerializationDemo
    private JSONArray statBlocksToJson() {
        JSONArray jsonArray = new JSONArray();
        for (StatBlock sb : statBlocks) {
            jsonArray.put(sb.toJson());
        }
        return jsonArray;
    }
}
