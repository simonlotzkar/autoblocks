package model.statblockfields;

import exceptions.IncompleteFieldException;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

// Represents an optional telepathy range and list of languages
public class Languages implements Writable {
    // required fields
    private final List<String> languagesList;

    // optional fields
    private final int telepathy;

    // MODIFIES: this
    // EFFECTS: constructs Languages using a builder
    public Languages(LanguagesBuilder builder) {
        this.languagesList = builder.languagesList;
        this.telepathy = builder.telepathy;
    }

    @Override
    // EFFECTS: returns a string comprised of all languages that exist and telepathy if it exists,
    //          or the empty string if it does not
    public String toString() {
        return "" + getListString() + getTelepathyString();
    }

    // EFFECTS: returns all languages that exist as a string, or the empty string if it does not
    private String getListString() {
        StringBuilder languagesListStringBuilder = new StringBuilder();
        for (String l : languagesList) {
            languagesListStringBuilder
                    .append(l)
                    .append(", ");
        }
        return languagesListStringBuilder.toString();
    }

    // EFFECTS: returns the telepathy range as a string if it exists, or the empty string if it does not
    private String getTelepathyString() {
        if (telepathy != 0) {
            return "telepathy " + telepathy + "ft";
        }
        return "";
    }

    @Override
    // EFFECTS: returns a json object representation of this languages
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("languagesList", languagesList);
        json.put("telepathy", telepathy);
        return json;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getters
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

        // EFFECTS: returns a builder with required fields and throws an exception if the given list is empty
        public LanguagesBuilder(List<String> languagesList) throws IncompleteFieldException {
            if (languagesList == null || languagesList.isEmpty()) {
                throw new IncompleteFieldException("(Languages) list must contain at least one entry.");
            } else {
                this.languagesList = languagesList;
            }
        }

        // EFFECTS: returns a new Languages with required fields,
        //          and any optional fields that had their builder called.
        public Languages build() {
            return new Languages(this);
        }

        // EFFECTS: returns a builder that assigns given telepathy range to Languages, and throws exception if the given
        //          range is negative
        public LanguagesBuilder telepathy(int telepathy) throws IndexOutOfBoundsException {
            if (telepathy < 0) {
                throw new IndexOutOfBoundsException("(Languages) telepathy cannot be negative.");
            } else {
                this.telepathy = telepathy;
                return this;
            }
        }
    }
}
