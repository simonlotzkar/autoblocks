package ui.scrollpanes;

import javax.swing.*;

// Represents a scroll pane of a text area that contains roll results
public class RollLogScrollPane extends ParchmentScrollPane {
    private final JTextArea rollLogTextArea;

    // MODIFIES: this
    // EFFECTS: constructs this scroll pane
    public RollLogScrollPane() {
        super(null);

        rollLogTextArea = new JTextArea();
        rollLogTextArea.setEditable(false);
        rollLogTextArea.setOpaque(false);

        setViewportView(rollLogTextArea);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    // MODIFIES: this
    // EFFECTS: prints the given string to the roll output log;
    //          adds a line break after the string if the log is not empty
    public void printToRollLog(String s) {
        if (rollLogTextArea.getText().equals("")) {
            rollLogTextArea.append(s);
        } else {
            rollLogTextArea.append("\n" + s);
        }
    }
}
