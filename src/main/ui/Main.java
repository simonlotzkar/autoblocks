package ui;

import ui.menus.MainMenuFrame;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        new MainMenuFrame();
        try {
            new AutoBlocksTerminal();
        } catch (IOException e) {
            System.out.println("Unhandled IO Exception.");
        }
    }
}