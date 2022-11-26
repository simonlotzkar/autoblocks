package ui.panels;

import model.StatBlock;
import model.statblockfields.*;
import ui.panels.menus.MainMenuPanel;
import ui.panels.menus.MenuManagerPanel;
import ui.panels.menus.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents...
public class StatBlockDisplayPanel extends DisplayPanel {
    protected model.StatBlock selectedStatBlock;

    // labels
    protected JLabel separatorLabel = new JLabel();
    protected JLabel nameChallengeLabel = new JLabel();
    protected JLabel sizeTypeAlignmentLabel = new JLabel();
    protected JLabel hpLabel = new JLabel();
    protected JLabel armourLabel = new JLabel();
    protected JLabel speedsLabel = new JLabel();

    // panels
    protected JPanel titlePanel = new JPanel();
    protected JPanel combatStatsPanel = new JPanel();

    // EFFECTS: constructs this display panel
    public StatBlockDisplayPanel(MainMenuPanel mainMenuPanel) {
        super(null, mainMenuPanel); // sets the layout manager, mainmenu panel, size, and visibility
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.selectedStatBlock = mainMenuPanel.getSelectedStatBlock();

        initializeAll();

        this.add(titlePanel);
        this.add(separatorLabel);
        this.add(combatStatsPanel);
    }

    // EFFECTS: ...
    private void initializeAll() {
        if (selectedStatBlock != null) {
            separatorLabel.setText("---------------------------");
            initializeTitlePanel();
            initializeCombatStatsPanel();
        } else {
            this.setSize(new Dimension(0, 0));
        }
    }

    // EFFECTS: ...
    private void initializeTitlePanel() {
        Title title = selectedStatBlock.getTitle();

        nameChallengeLabel.setText(title.getName() + " (" + selectedStatBlock.getChallengeRating() + ")");
        sizeTypeAlignmentLabel.setText(title.getSize() + " " + title.getType() + ", " + title.getAlignment());

        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.add(nameChallengeLabel);
        titlePanel.add(sizeTypeAlignmentLabel);
    }

    // EFFECTS: ...
    private void initializeCombatStatsPanel() {
        hpLabel.setText("Hit Points: " + selectedStatBlock.getHPString());
        armourLabel.setText("Armour Class: " + selectedStatBlock.getArmour().toString());
        speedsLabel.setText("Speeds: " + selectedStatBlock.getSpeeds().toString());

        combatStatsPanel.setLayout(new BoxLayout(combatStatsPanel, BoxLayout.Y_AXIS));
        combatStatsPanel.add(hpLabel);
        combatStatsPanel.add(armourLabel);
        combatStatsPanel.add(speedsLabel);
    }

    // EFFECTS: ...
    public void reinitialize() {
        this.selectedStatBlock = mainMenuPanel.getSelectedStatBlock();
        initializeAll();
    }
}
