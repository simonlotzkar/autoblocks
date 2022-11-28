package ui.frames;

import model.RollFormula;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Represents a custom roll frame that has a log of previous rolls
public class CustomRollFrame extends JFrame implements ActionListener {
    // constants
    private static final String ICON_DIRECTORY = "./data/images/icons/";
    private static final int WIDTH = 480;
    private static final int HEIGHT = 320;

    // panels
    JPanel rollParametersPanel = new JPanel();
    JScrollPane outputLogScrollPane = new JScrollPane();

    // buttons
    JButton rollButton = new JButton("Roll!");

    // text areas
    JTextArea outputLogTextArea;

    // textfields
    ArrayList<JTextField> textFieldList = new ArrayList<>();
    JTextField amountOfDiceTextField = new JTextField();
    JTextField dieSidesTextField = new JTextField();
    JTextField modifierTextField = new JTextField();

    // images
    private static final ImageIcon D20_ICON = new ImageIcon(ICON_DIRECTORY + "d20.png");
    private static final ImageIcon DICE_ICON = new ImageIcon((new ImageIcon(ICON_DIRECTORY + "dice.png"))
            .getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));

    // EFFECTS: constructs a custom roll frame
    public CustomRollFrame() {
        super("Custom Dice Roller");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setIconImage(D20_ICON.getImage());

        initializeParameterPanel();
        initializeOutputLogScrollPane();

        rollButton.addActionListener(this);
        rollButton.setIcon(DICE_ICON);
        rollButton.setPreferredSize(new Dimension(WIDTH, 50));

        JPanel customRollPanel = new JPanel();
        customRollPanel.setLayout(new BorderLayout());
        customRollPanel.add(outputLogScrollPane, BorderLayout.CENTER);
        customRollPanel.add(rollParametersPanel, BorderLayout.NORTH);
        customRollPanel.add(rollButton, BorderLayout.SOUTH);

        this.add(customRollPanel);
        this.setVisible(true);
    }

    // EFFECTS: sets up most of the components for this frame
    private void initializeOutputLogScrollPane() {
        outputLogTextArea = new JTextArea("Custom Dice Roll Output Log:", 10, WIDTH / 12);
        outputLogTextArea.setEditable(false);

        outputLogScrollPane.setViewportView(outputLogTextArea);
        outputLogScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        outputLogScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    // EFFECTS: sets up the components of the roll parameters panel
    private void initializeParameterPanel() {
        textFieldList.add(amountOfDiceTextField);
        textFieldList.add(dieSidesTextField);
        textFieldList.add(modifierTextField);

        for (JTextField tf : textFieldList) {
            tf.setPreferredSize(new Dimension(WIDTH / 8, HEIGHT / 8));
        }

        rollParametersPanel.setLayout(new FlowLayout());
        rollParametersPanel.add(new JLabel("amount:"));
        rollParametersPanel.add(amountOfDiceTextField);
        rollParametersPanel.add(new JLabel("sides:"));
        rollParametersPanel.add(dieSidesTextField);
        rollParametersPanel.add(new JLabel("modifier:"));
        rollParametersPanel.add(modifierTextField);
        rollParametersPanel.setVisible(true);
    }

    @Override
    // EFFECTS: processes button presses from user
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rollButton) {
            rollAndLog();
        }
    }

    // EFFECTS: creates a rollformula from whatever is in the current text fields then rolls it
    //          and appends a representative string to the output log
    private void rollAndLog() {
        try {
            int amountOfDice = Integer.parseInt(amountOfDiceTextField.getText());
            int dieSides = Integer.parseInt(dieSidesTextField.getText());
            int modifier = Integer.parseInt(modifierTextField.getText());

            RollFormula customRoll = new RollFormula(amountOfDice, dieSides, modifier);
            outputLogTextArea.append("\n" + customRoll.roll() + " (" + customRoll + ")");
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "NumberFormatException caught. Message: "
                    + exception.getMessage() + ".", "Failure!", JOptionPane.WARNING_MESSAGE);
        }
    }
}
