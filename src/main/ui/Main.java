package ui;

import ui.frames.CustomRollFrame;
import ui.frames.MainFrame;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        new MainFrame();
        try {
            new AutoBlocksConsole();
        } catch (IOException e) {
            System.out.println("Unhandled IO Exception.");
        }
    }
}