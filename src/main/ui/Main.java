package ui;

import persistence.JsonReader;
import ui.menus.MainMenu;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        JsonReader jsonReader = new JsonReader("./data/autoBlocksApp.json");
        DefaultListModel<model.Character> defaultListModel = new DefaultListModel<model.Character>();
        try {
            defaultListModel.addAll(jsonReader.readEncounter());
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("IO exception!");
        }
        new MainMenu(defaultListModel);
    }
}