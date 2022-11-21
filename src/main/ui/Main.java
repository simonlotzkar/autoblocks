package ui;

import ui.frames.MainFrame;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        new MainFrame();
        try {
            new AutoBlocksTerminal();
        } catch (IOException e) {
            System.out.println("Unhandled IO Exception.");
        }
    }
}