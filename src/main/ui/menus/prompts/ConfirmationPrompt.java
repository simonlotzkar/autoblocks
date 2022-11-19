package ui.menus.prompts;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents...
public class ConfirmationPrompt extends JFrame implements ActionListener {
    protected static final int WIDTH = 360;
    protected static final int HEIGHT = 120;

    // buttons
    protected final JButton confirmButton = new JButton("Confirm");
    protected final JButton denyButton = new JButton("Deny");

    // EFFECTS: constructs...
    public ConfirmationPrompt() {
        this.setTitle("Confirmation Window");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        confirmButton.setBounds(0, 20, WIDTH / 2, HEIGHT / 2);
        confirmButton.addActionListener(this);
        confirmButton.setVisible(true);
        confirmButton.setFocusable(false);
        this.add(confirmButton);
        denyButton.setBounds(WIDTH / 2, 20, WIDTH / 2, HEIGHT / 2);
        denyButton.addActionListener(this);
        denyButton.setVisible(true);
        denyButton.setFocusable(false);
        this.add(denyButton);
        JLabel confirmTitle = new JLabel(returnPrompt());
        confirmTitle.setHorizontalAlignment(SwingConstants.CENTER);
        confirmTitle.setVerticalAlignment(SwingConstants.TOP);
        this.add(confirmTitle);
    }

    // EFFECTS: returns the prompt for this prompt
    protected String returnPrompt() {
        return "Confirm.";
    }

    @Override
    // EFFECTS: ...
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            this.dispose();
        }
        if (e.getSource() == denyButton) {
            this.dispose();
        }
    }
}