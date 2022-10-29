package ui;

import java.io.FileNotFoundException;

// CITATION: trycatch from JsonSerializationDemo
public class Main {
    public static void main(String[] args) {
        try {
            new AutoBlocksApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}