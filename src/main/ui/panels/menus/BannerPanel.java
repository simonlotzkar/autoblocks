package ui.panels.menus;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// Represents a banner panel that contains an image behind title text
public class BannerPanel extends JPanel {
    private final String title;

    // labels
    private final JLabel encounterTitleLabel = new JLabel("Encounter");
    private final JLabel libraryTitleLabel = new JLabel("Library");

    // images
    private static final Image LIBRARY_BANNER = Toolkit.getDefaultToolkit()
            .getImage("./data/images/libraryBanner.jpg");
    private static final Image ENCOUNTER_BANNER = Toolkit.getDefaultToolkit()
            .getImage("./data/images/encounterBanner.jpg");

    // MODIFIES: this
    // EFFECTS: constructs this panel
    public BannerPanel(String title) {
        super(new BorderLayout());
        this.title = title;
        initializeTitleLabel();
    }

    // MODIFIES: this
    // EFFECTS: formats title label and imports font or sends error message if font cant be loaded
    public void initializeTitleLabel() {
        try {
            float fontSize = 100f;

            Font dungeonFont = Font.createFont(Font.TRUETYPE_FONT, new File("./data/dungeon(BySaesarezNovandito).TTF"));

            encounterTitleLabel.setFont(dungeonFont.deriveFont(fontSize));
            libraryTitleLabel.setFont(dungeonFont.deriveFont(fontSize));

        } catch (IOException | FontFormatException e) {
            JOptionPane.showMessageDialog(this, "Could not load dungeon font! ("
                    + e.getMessage() + ")", "Failure!", JOptionPane.WARNING_MESSAGE);
        }

        if (title.equals("encounter")) {
            encounterTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            encounterTitleLabel.setForeground(Color.WHITE);
            add(encounterTitleLabel, BorderLayout.CENTER);
        } else {
            libraryTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            libraryTitleLabel.setForeground(Color.WHITE);
            add(libraryTitleLabel, BorderLayout.CENTER);
        }
    }

    @Override
    // MODIFIES: this
    // EFFECTS: draws the background image depending on what title is displayed
    public void paintComponent(Graphics g) {
        if ("encounter".equals(title)) {
            g.drawImage(ENCOUNTER_BANNER, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.drawImage(LIBRARY_BANNER, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
