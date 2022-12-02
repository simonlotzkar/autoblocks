package model;

import javax.swing.*;
import java.util.ArrayList;

// Represents a list model of NPCs
public class Encounter extends DefaultListModel<NPC> {

    // MODIFIES: this
    // EFFECTS: constructs the encounter and calls event log
    public Encounter() {
        super();
        EventLog.getInstance().logEvent(new Event("Encounter constructed."));
    }

    @Override
    // MODIFIES: this
    // EFFECTS: removes all NPCs and calls event log
    public void removeAllElements() {
        super.removeAllElements();
        EventLog.getInstance().logEvent(new Event("Removed all encounter elements."));
    }

    @Override
    // MODIFIES: this
    // EFFECTS: removes the given NPC and calls event log
    public boolean removeElement(Object obj) {
        EventLog.getInstance().logEvent(new Event("Removed \"" + obj.toString() + "\" from the encounter."));
        return super.removeElement(obj);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds the given NPC and calls event log
    public void addElement(NPC element) {
        super.addElement(element);
        EventLog.getInstance().logEvent(new Event("Added \"" + element.toString() + "\" to the encounter."));
    }

}
