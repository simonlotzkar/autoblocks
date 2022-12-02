package model;

import javax.swing.*;

// Represents a list model of statblocks
public class StatBlockLibrary extends DefaultListModel<StatBlock> {

    // MODIFIES: this
    // EFFECTS: constructs the library and calls event log
    public StatBlockLibrary() {
        super();
        EventLog.getInstance().logEvent(new Event("Library constructed."));
    }

    @Override
    // MODIFIES: this
    // EFFECTS: removes all statblocks and calls event log
    public void removeAllElements() {
        super.removeAllElements();
        EventLog.getInstance().logEvent(new Event("Removed all library elements."));
    }

    @Override
    // MODIFIES: this
    // EFFECTS: removes the given statblock and calls event log
    public boolean removeElement(Object obj) {
        EventLog.getInstance().logEvent(new Event("Removed \"" + obj.toString() + "\" from the library."));
        return super.removeElement(obj);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds the given statblock and calls event log
    public void addElement(StatBlock element) {
        super.addElement(element);
        EventLog.getInstance().logEvent(new Event("Added \"" + element.toString() + "\" to the library."));
    }
}
