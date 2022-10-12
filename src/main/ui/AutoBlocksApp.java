package ui;

import model.*;
import model.Character;

import java.util.*;

public class AutoBlocksApp {
    private final List<StatBlock> library = new ArrayList<StatBlock>();
    private final List<Character> play = new ArrayList<Character>();
    private final Scanner userInput = new Scanner(System.in);

    private StatBlock selectedStatBlock;
    private Character selectedCharacter;

    private String menuLevel = "main";
    private boolean runApp = true;

    // EFFECTS: initializes default statblocks in the library
    private void initializeStatBlocks() {
        RollFormula orcHPFormula = new RollFormula(2,8,6);
        RollFormula orcGreatAxeHit = new RollFormula(1, 20, 5);
        RollFormula orcGreatAxeDamage = new RollFormula(1, 12, 3);
        Action orcGreatAxe = new Action("GreatAxe", "Slashing", 5,
                orcGreatAxeHit, orcGreatAxeDamage, "Melee Weapon Attack");
        List<Action> orcActions = new ArrayList<Action>();
        orcActions.add(orcGreatAxe);
        StatBlock orc = new StatBlock("Orc", "Medium", "Humanoid", orcHPFormula, 13, 30,
                16, 12, 16, 7, 11, 10, orcActions);
        library.add(orc);
    }

    // EFFECTS: constructs the autoblocks app
    public AutoBlocksApp() {
        runAutoBlocks();
    }

    // MODIFIES: this
    // EFFECTS: initializes, displays MainMenu commands, and processes MainMenu commands
    private void runAutoBlocks() {
        initializeStatBlocks();

        while (runApp) {
            switch (menuLevel) {
                case "main":
                    displayMainMenu();
                    processMainCommand(userInput.next().toLowerCase());
                    break;
                case "library":
                    displayLibraryMenu();
                    processLibraryCommand(userInput.next().toLowerCase());
                    break;
                case "character":
                    displayCharacterMenu();
                    processCharacterCommand(userInput.next().toLowerCase());
                    break;
                case "statBlock":
                    displayStatBlockMenu();
                    processStatBlockCommand(userInput.next().toLowerCase());
                    break;
            }
        }
        System.out.println("Quitting to desktop...");
    }

    // EFFECTS: display characters/groups in play and MainMenu commands:
    //          - custom roll,
    //          - select character *CharacterMenu*,
    //          - delete character,
    //          - view library *LibraryMenu*
    //          - quit app
    private void displayMainMenu() {
        System.out.println("\nMain Menu. Current play environment:");
        if (!play.isEmpty()) {
            for (Character c : play) {
                System.out.println("\t" + c.getName() + ": " + c.getHP() + ",");
            }
        } else {
            System.out.println("\tThere are currently no characters in play.");
        }
        System.out.println("\nMain Menu Commands:");
        System.out.println("\troll: Makes a custom roll.");
        System.out.println("\tselect: Select a character or group in play. Take actions and edit hp from here.");
        System.out.println("\tdelete: Remove a character or group from play.");
        System.out.println("\tlibrary: View StatBlock library. Add characters to play from here.");
        System.out.println("\tquit: exit to desktop.");
    }

    // MODIFIES: this
    // EFFECTS: processes commands for MainMenu
    private void processMainCommand(String command) {
        switch (command) {
            case "roll":
                customRoll();
                break;
            case "select":
                selectCharacter();
                break;
            case "delete":
                deleteCharacter();
                break;
            case "library":
                goToLibraryMenu();
                break;
            case "quit":
                goToDesktop();
                break;
            default:
                System.out.println("Command invalid!");
                break;
        }
    }

    // EFFECTS: prompts user for input and makes a custom roll with given input
    private void customRoll() {
        System.out.println("Enter amount of dice to roll.");
        int amountOfDice = userInput.nextInt();
        System.out.println("Enter sides on dice.");
        int die = userInput.nextInt();
        System.out.println("Enter modifier.");
        int modifier = userInput.nextInt();

        RollFormula roll = new RollFormula(amountOfDice, die, modifier);
        System.out.println(roll.roll());
    }

    // EFFECTS: prompts user for character name and selects any in play with given name
    private void selectCharacter() {
        System.out.println("Enter character name.");
        String characterName = userInput.next().toLowerCase();
        boolean foundCharacter = false;

        for (Character c : play) {
            if (characterName.equals(c.getName())) {
                foundCharacter = true;
                selectedCharacter = c;
                menuLevel = "character";
            }
        }

        if (!foundCharacter) {
            System.out.println("Could not find specified character in play.");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user for character name and removes character with given name from play
    private void deleteCharacter() {
        System.out.println("Enter character name to remove from play.");
        String name = userInput.next().toLowerCase();
        boolean foundCharacter = false;

        for (Character c : play) {
            if (c.getName().equals(name)) {
                foundCharacter = true;
                play.remove(c);
                System.out.println("Removed " + name + " from play.");
            }
        }

        if (!foundCharacter) {
            System.out.println("Could not find specified character in play.");
        }
    }

    // EFFECTS: switch to library menu
    private void goToLibraryMenu() {
        displayLibraryMenu();
        menuLevel = "Library";
        selectedCharacter = null;
        selectedStatBlock = null;
    }

    // EFFECTS: quits the app
    private void goToDesktop() {
        runApp = false;
    }

    // EFFECTS: display character/group stats and CharacterMenu commands:
    //          - add character to group
    //          - remove character from group
    //          - edit character/group hitpoints
    //          - roll character/group action
    //          - roll character/group initiative
    //          - roll character/group skill check
    //          - roll character/group saving throw
    //          - go back *MainMenu*
    private void displayCharacterMenu() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: processes commands for CharacterMenu
    private void processCharacterCommand(String command) {
        //stub
    }

    // EFFECTS: display names of statblocks in library, sorted alphabetically, and LibraryMenu commands
    private void displayLibraryMenu() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: processes commands for LibraryMenu:
    //          - select statblock *StatBlockMenu*
    //          - add new StatBlock
    //          - add copy of existing StatBlock with new name
    //          - go back *MainMenu*
    private void processLibraryCommand(String command) {
        //stub
    }

    // EFFECTS: display StatBlock stats and StatBlockMenu commands
    private void displayStatBlockMenu() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: processes commands for StatBlockMenu
    private void processStatBlockCommand(String command) {
        //stub
        //commands: add any number of the statblock to play as character(s) numeral suffixes
        //          go back *to LibraryMenu*
        //          go all the way back *to MainMenu*
    }

}
