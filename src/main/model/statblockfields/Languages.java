package model.statblockfields;

import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

// Represents...
public class Languages implements Writable {
    // required fields
    private final List<String> languagesList;

    // optional fields
    private final int telepathy;

    // EFFECTS: constructs Languages using a builder
    public Languages(LanguagesBuilder builder) {
        this.languagesList = builder.languagesList;
        this.telepathy = builder.telepathy;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
    @Override
    // EFFECTS: returns a string comprised of all languages that exist and telepathy if it exists
    public String toString() {
        return "" + getListString() + getTelepathyString();
    }

    // EFFECTS: returns all languages that exist as a string
    private String getListString() {
        StringBuilder languagesListStringBuilder = new StringBuilder();
        for (String l : languagesList) {
            languagesListStringBuilder
                    .append(l)
                    .append(", ");
        }
        return languagesListStringBuilder.toString();
    }

    // EFFECTS: returns the telepathy range as a string if it exists
    private String getTelepathyString() {
        if (telepathy != 0) {
            return "telepathy " + telepathy + "ft";
        }
        return "";
    }

    // EFFECTS: gets list of languages
    public List<String> getLanguagesList() {
        return languagesList;
    }

    // EFFECTS: gets telepathy range
    public int getTelepathy() {
        return telepathy;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // builder class
    public static class LanguagesBuilder {
        // required fields
        private final List<String> languagesList;

        // optional fields
        private int telepathy;

        // constructs a builder with required fields
        public LanguagesBuilder(List<String> languagesList) {
            this.languagesList = languagesList;
        }

        // EFFECTS: returns a new Languages with required fields,
        //          and any optional fields that had their builder called.
        public Languages build() {
            return new Languages(this);
        }

        // EFFECTS: returns a builder that assigns given telepathy range to Languages
        public LanguagesBuilder telepathy(int telepathy) {
            this.telepathy = telepathy;
            return this;
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // constructs a json object with the fields of the languages
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("languagesList", languagesList);
        json.put("telepathy", telepathy);
        return json;
    }
}
