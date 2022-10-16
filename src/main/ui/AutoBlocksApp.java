package ui;

import model.*;
import model.Character;

import java.util.*;

public class AutoBlocksApp {
    private final List<StatBlock> library = new ArrayList<>();
    private final List<Character> play = new ArrayList<>();
    private final Scanner userInput = new Scanner(System.in);

    private StatBlock selectedStatBlock;
    private Character selectedCharacter;
    private String selectedGroupName;
    private List<Character> selectedGroup;

    private String menuLevel = "main";
    private boolean runApp = true;

    // EFFECTS: constructs the autoblocks app
    public AutoBlocksApp() {
        runAutoBlocks();
    }

    // MODIFIES: this
    // EFFECTS: initializes, displays MainMenu commands, and processes MainMenu commands
    private void runAutoBlocks() {
        initializeLibrary();

        while (runApp) {
            if ("main".equals(menuLevel)) {
                displayMainMenu();
                processMainCommand(userInput.next().toLowerCase());
            } else if ("library".equals(menuLevel)) {
                displayLibraryMenu();
                processLibraryCommand(userInput.next().toLowerCase());
            } else if ("character".equals(menuLevel)) {
                displayCharacterMenu();
                processCharacterCommand(userInput.next().toLowerCase());
            } else if ("roll".equals(menuLevel)) {
                displayRollMenu();
                processRollCommand(userInput.next().toLowerCase());
            } else if ("group".equals(menuLevel)) {
                displayGroupMenu();
                processGroupCommand(userInput.next().toLowerCase());
            } else if ("statblock".equals(menuLevel)) {
                displayStatBlockMenu();
                processStatBlockCommand(userInput.next().toLowerCase());
            }
        }
        System.out.println("Quitting to desktop...");
    }

    // -----------------------------------------------------------------------------------------------------------------
    // EFFECTS: display characters in play and MainMenu commands:
    //          - custom roll,
    //          - select character *CharacterMenu*,
    //          - select group *GroupMenu*
    //          - view library *LibraryMenu*
    //          - quit app
    private void displayMainMenu() {
        System.out.println("\nMain Menu. Current characters in play:");
        System.out.println("----------------------------------------");

        displayPlay();

        System.out.println("\nMain Menu Commands:");
        System.out.println("\troll: Make a custom roll with any parameter.");
        System.out.println("\tchar: Select a character in play. Take individual actions and edit hp from here.");
        System.out.println("\tgroup: Select a group in play. Take group actions and edit group hp from here.");
        System.out.println("\tlib: View statblock library. Add characters to play from here.");
        System.out.println("\tquit: exit to desktop.");
    }

    // MODIFIES: this
    // EFFECTS: processes commands for MainMenu
    private void processMainCommand(String command) {
        switch (command) {
            case "roll":
                customRoll();
                break;
            case "char":
                searchForAndSelect("character");
                break;
            case "group":
                searchForAndSelect("group");
                break;
            case "lib":
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

    // EFFECTS: prints all characters in play with their names, groups, and hp
    private void displayPlay() {
        if (!play.isEmpty()) {
            for (Character c : play) {
                if (c.getGroup() != null) {
                    System.out.println("\t" + c.getName() + " (Group: " + c.getGroup() + "), HP: " + c.getHPString());
                } else {
                    System.out.println("\t" + c.getName() + ", HP: " + c.getHPString());
                }
            }
        } else {
            System.out.println("\tThere are currently no characters in play.");
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

    // REQUIRES: selectType is either "character", "group", or "statblock"
    // MODIFIES: this
    // EFFECTS: prompts user for the name of given selectType, searches play and selects them if they exist
    private void searchForAndSelect(String selectType) {
        System.out.println("Enter " + selectType + " name.");
        String select = userInput.next().toLowerCase();

        if (selectType.equals("statblock")) {
            selectStatBlockByName(select);
        } else if (selectType.equals("character")) {
            selectCharacterByName(select);
        } else {
            for (Character c : play) {
                if (select.equals(c.getGroup().toLowerCase())) {
                    selectGroupByName(select);
                }
            }
        }

        if (selectedGroupName == null && selectedStatBlock == null && selectedCharacter == null) {
            System.out.println("Could not find specified " + selectType + ".");
        }
    }

    // EFFECTS: searches library for given statblock name, prints name, selects them, and changes to StatBlockMenu
    private void selectStatBlockByName(String statBlockName) {
        for (StatBlock sb : library) {
            if (statBlockName.equals(sb.getName().toLowerCase())) {
                System.out.println("Found " + statBlockName + "!");
                selectedStatBlock = sb;
                menuLevel = "statblock";
            }
        }
    }

    // EFFECTS: searches play for given character name, prints name, selects them, and changes to CharacterMenu
    private void selectCharacterByName(String characterName) {
        for (Character c : play) {
            if (characterName.equals(c.getName().toLowerCase())) {
                System.out.println("Found " + characterName + "!");
                selectedCharacter = c;
                menuLevel = "character";
            }
        }
    }

    // EFFECTS: prints given group name, selects it, creates an array, and changes to GroupMenu
    private void selectGroupByName(String groupName) {
        System.out.println("Found " + groupName + "!");
        System.out.println("Selecting group members...");
        for (Character c : play) {
            if (c.getGroup() != null && selectedGroupName.equals(c.getGroup())) {
                selectedGroup.add(c);
            }
        }
        System.out.println("Group members accounted for!");
        selectedGroupName = groupName;
        menuLevel = "group";
    }

    // EFFECTS: switch to library menu and unselects everything
    private void goToLibraryMenu() {
        menuLevel = "library";
        selectedCharacter = null;
        selectedGroupName = null;
        selectedStatBlock = null;
        selectedGroup = new ArrayList<>();
    }

    // EFFECTS: quits the app
    private void goToDesktop() {
        runApp = false;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // EFFECTS: display character stats and CharacterMenu commands:
    //          - roll for character
    //          - edit character hp
    //          - change character group
    //          - delete character from play
    //          - go back *MainMenu*
    private void displayCharacterMenu() {
        System.out.println("\nCharacter Menu. Current character selected:");
        System.out.println("---------------------------------------------");

        displayStats();

        System.out.println("\nCharacter Commands:");
        System.out.println("\troll: Roll an action, initiative, skill check, or saving throw for this character.");
        System.out.println("\thp: Edit this character's hitpoints");
        System.out.println("\tgroup: Change this character's group");
        System.out.println("\tdel: Delete this character from play.");
        System.out.println("\texit: Go back to the main menu.");
    }

    // MODIFIES: this
    // EFFECTS: processes commands for CharacterMenu
    private void processCharacterCommand(String command) {
        switch (command) {
            case "roll":
                goToRollMenu();
                break;
            case "hp":
                editHP();
                break;
            case "group":
                changeCharacterGroup();
                break;
            case "del":
                deleteSelectedCharacter();
                break;
            case "exit":
                goToMainMenu();
                break;
            default:
                System.out.println("Command invalid!");
                break;
        }
    }

    // REQUIRES: there must be either a selected character, group, or statblock
    // EFFECTS: prints the selected character OR statblock stats, OR all characters in selected group
    private void displayStats() {
        if (selectedGroupName != null) {
            for (Character c : selectedGroup) {
                displayIndividualStats(c);
            }
        } else if (selectedCharacter != null) {
            displayIndividualStats(selectedCharacter);
        } else {
            displayIndividualStats(selectedStatBlock);
        }
    }

    // EFFECTS: prints given statblock OR character stats
    private void displayIndividualStats(StatBlock selected) {
        displayIndividualTitle(selected);
        System.out.println("\t" + "HP = " + "(" + selected.getHPString() + ")"
                + ", AC = " + selected.getAC()
                + ", Speed = " + selected.getSpeed());
        System.out.println("\t" + "Str: " + selected.getStrength() + "(" + selected.getStrengthModifier() + ")"
                + ", Dex: " + selected.getDexterity() + "(" + selected.getDexterityModifier() + ")"
                + ", Con: " + selected.getConstitution() + "(" + selected.getConstitutionModifier() + ")"
                + ", Int: " + selected.getIntelligence() + "(" + selected.getIntelligenceModifier() + ")"
                + ", Wis: " + selected.getWisdom() + "(" + selected.getWisdomModifier() + ")"
                + ", Cha: " + selected.getCharisma() + "(" + selected.getCharismaModifier() + ")");
        displayActions(selected);
    }

    // EFFECTS: prints the title of the given statblock OR character
    private void displayIndividualTitle(StatBlock selected) {
        if (selected instanceof Character) {
            if (selected.getGroup() != null) {
                System.out.println("\n" + selected.getName() + " (Group: " + selected.getGroup() + ")" + ": "
                        + selected.getSize() + " " + selected.getType() + ".");
            } else {
                System.out.println("\n" + selected.getName() + " (Group: None): "
                        + selected.getSize() + " " + selected.getType() + ".");
            }
        } else {
            System.out.println("\n" + selected.getName() + ": " + selected.getSize() + " " + selected.getType() + ".");
        }
    }

    // EFFECTS: prints all actions for the given statblock or character
    private void displayActions(StatBlock statBlock) {
        System.out.println("\tActions:");
        for (Action a : statBlock.getActions()) {
            System.out.println("\t" + a.getName() + ": " + a.getHitString() + " to hit, " + a.getDamageString()
                    + " " + a.getDamageType().toLowerCase() + " damage. " + a.getDescription());
        }
    }

    // EFFECTS: switches to roll menu for a character OR group
    private void goToRollMenu() {
        menuLevel = "roll";
    }

    // REQUIRES: hp cannot exceed max hp, either a character or group is selected
    // MODIFIES: this
    // EFFECTS: increase or reduce hp for a character OR group
    private void editHP() {
        if (selectedCharacter != null) {
            System.out.println("Enter amount of hp to change this character's current hp by:");
            int hpDifference = userInput.nextInt();
            selectedCharacter.changeHP(hpDifference);
        } else {
            System.out.println("Enter amount of hp to change this group's current hp by:");
            int hpDifference = userInput.nextInt();
            for (Character c : selectedGroup) {
                c.changeHP(hpDifference);
            }
        }
    }

    // EFFECTS: prompts user to either change or remove group then executes choice
    private void changeCharacterGroup() {
        if (selectedCharacter.getGroup() != null) {
            System.out.println("Enter the new group for this character (one word), or type rem to remove its group:");
            String newGroup = userInput.next().toLowerCase();
            if (newGroup.equals("rem")) {
                selectedCharacter.setGroup(null);
                System.out.println("Removed this character's group.");
            } else {
                selectedCharacter.setGroup(newGroup.substring(0, 1).toUpperCase() + newGroup.substring(1));
                System.out.println("Changed this character's group.");
            }
        } else {
            System.out.println("Enter the new group (one word) for this character:");
            String newGroup = userInput.next().toLowerCase();
            selectedCharacter.setGroup(newGroup.substring(0, 1).toUpperCase() + newGroup.substring(1));
            System.out.println("Changed this character's group.");
        }
    }

    // EFFECTS: prompts user for confirmation, removes selected character from play, then exits menu *MainMenu*
    private void deleteSelectedCharacter() {
        System.out.println("\nAre you sure you want to delete the selected character? Confirm:");
        System.out.println("\ty = yes,");
        System.out.println("\tn = no");
        String confirmation = userInput.next().toLowerCase();
        if (confirmation.equals("y")) {
            play.remove(selectedCharacter);
            System.out.println("Deleted character.");
            goToMainMenu();
        } else if (confirmation.equals("n")) {
            System.out.println("Cancelling deletion...");
        } else {
            System.out.println("Command invalid!");
            deleteSelectedCharacter();
        }
    }

    // EFFECTS: exits to main menu and unselects everything
    private void goToMainMenu() {
        menuLevel = "main";
        selectedCharacter = null;
        selectedGroupName = null;
        selectedStatBlock = null;
        selectedGroup = new ArrayList<>();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // EFFECTS: display all characters in group by stats and GroupMenu commands:
    //          - roll for group
    //          - edit group hp
    //          - remove group only
    //          - delete group and characters in it
    //          - go back *MainMenu*
    private void displayGroupMenu() {
        System.out.println("\nGroup Menu. Characters in currently selected group:");
        System.out.println("-----------------------------------------------------");

        displayStats();

        System.out.println("\nGroup Commands:");
        System.out.println("\troll: Multi-roll an action, initiative, skill check, or saving throw for this group.");
        System.out.println("\thp: Multi-edit this group's hitpoints.");
        System.out.println("\trem: Remove this group. Keeps the characters in it.");
        System.out.println("\tdel: Delete this group **and** the characters in it.");
        System.out.println("\texit: Go back to the main menu.");
    }

    // MODIFIES: this
    // EFFECTS: processes commands for GroupMenu
    private void processGroupCommand(String command) {
        switch (command) {
            case "roll":
                goToRollMenu();
                break;
            case "hp":
                editHP();
                break;
            case "remove":
                removeGroup();
                break;
            case "delete":
                deleteGroup();
                break;
            case "exit":
                goToMainMenu();
                break;
            default:
                System.out.println("Command invalid!");
                break;
        }
    }

    // EFFECTS: prompts user for confirmation, removes group from characters in it, then exits menu *MainMenu*
    private void removeGroup() {
        //stub TODO
    }

    // EFFECTS: prompts user for confirmation, deletes group and its characters from play, then exits menu *MainMenu*
    private void deleteGroup() {
        //stub TODO
    }

    // -----------------------------------------------------------------------------------------------------------------
    // EFFECTS: displays selected character OR group actions and RollMenu commands:
    //          - roll action
    //          - roll initiative
    //          - make skill check
    //          - make saving throw
    //          - go back *MainMenu*
    private void displayRollMenu() {
        System.out.println("\nRoll Menu. Actions available for currently selected character or group:");
        System.out.println("-------------------------------------------------------------------------");

        displayGroupOrCharacterActions();

        System.out.println("\nRoll Commands:");
        System.out.println("\tact: Roll an action.");
        System.out.println("\tinit: Roll initiative.");
        System.out.println("\tskill: Make a skill check.");
        System.out.println("\tsave: Make a saving throw.");
        System.out.println("\tback: Go back to the previous menu.");
        System.out.println("\texit: Exit to the main menu.");
    }

    // MODIFIES: this
    // EFFECTS: processes commands for RollMenu
    private void processRollCommand(String command) {
        switch (command) {
            case "act":
                rollAction();
                break;
            case "init":
                rollInitiative();
                break;
            case "skill":
                rollSkillCheck();
                break;
            case "save":
                rollSavingThrow();
                break;
            case "back":
                goToPreviousMenu();
                break;
            case "exit":
                goToMainMenu();
                break;
            default:
                System.out.println("Command invalid!");
                break;
        }
    }

    // REQUIRES: either a character or group is selected
    // EFFECTS: checks if a character or group is selected and prints all actions amongst them
    private void displayGroupOrCharacterActions() {
        if (selectedCharacter != null) {
            displayActions(selectedCharacter);
        } else {
            for (Character c : selectedGroup) {
                displayActions(c);
            }
        }
    }

    // EFFECTS:
    private void rollAction() {
        //stub TODO
    }

    // EFFECTS:
    private void rollInitiative() {
        //stub TODO
    }

    // EFFECTS:
    private void rollSkillCheck() {
        //stub TODO
    }

    // EFFECTS:
    private void rollSavingThrow() {
        //stub TODO
    }

    // REQUIRES: either a character or group is selected
    // MODIFIES: this
    // EFFECTS: goes to character or group menu depending on what's selected
    private void goToPreviousMenu() {
        if (selectedCharacter != null) {
            menuLevel = "character";
        } else {
            menuLevel = "group";
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // EFFECTS: display names of statblocks in library, sorted alphabetically, and LibraryMenu commands:
    //          - select statblock *StatBlockMenu*
    //          - add new StatBlocks
    //          - go back *MainMenu*
    private void displayLibraryMenu() {
        System.out.println("\nLibrary Menu. Current statblocks in the library:");
        System.out.println("--------------------------------------------------");

        displayLibrary();

        System.out.println("\nLibrary Commands:");
        System.out.println("\tsel: Select a statblock from the library.");
        System.out.println("\tnew: Add a new, custom, statblock to the library.");
        System.out.println("\texit: Go back to the main menu.");
    }

    // EFFECTS: prints all statblocks in the library with their names, sorted alphabetically
    private void displayLibrary() {
        for (StatBlock sb : library) {
            System.out.println(sb.getName());
        }
    }

    // MODIFIES: this
    // EFFECTS: processes commands for LibraryMenu
    private void processLibraryCommand(String command) {
        switch (command) {
            case "sel":
                searchForAndSelect("statblock");
                break;
            case "new":
                createCustomStatBlock();
                break;
            case "exit":
                goToMainMenu();
                break;
            default:
                System.out.println("Command invalid!");
                break;
        }
    }

    // EFFECTS: add new custom Statblock to the library
    private void createCustomStatBlock() {
        //stub TODO
    }

    // -----------------------------------------------------------------------------------------------------------------
    // EFFECTS: display statblock stats and StatBlockMenu commands:
    //          - add any number of the statblock to play as characters
    //          - go back *LibraryMenu*
    private void displayStatBlockMenu() {
        System.out.println("\nStatblock Menu. Currently selected statblock:");
        System.out.println("-----------------------------------------------");

        displayStats();

        System.out.println("\nStatblock Commands:");
        System.out.println("\tadd: Add any number of this statblock to play as (a) character(s).");
        System.out.println("\tback: Go back to the library menu.");
        System.out.println("\texit: Exit to the main menu.");
    }

    // MODIFIES: this
    // EFFECTS: processes commands for StatBlockMenu
    private void processStatBlockCommand(String command) {
        switch (command) {
            case "add":
                createCharacters();
                break;
            case "back":
                goToLibraryMenu();
                break;
            case "exit":
                goToMainMenu();
                break;
            default:
                System.out.println("Command invalid!");
                break;
        }
    }

    // EFFECTS: add any number of the selected statblock to play as characters with no unique names
    private void createCharacters() {
        System.out.println("How many copies of this statblock do you want to add?");
        int numberOfCopies = userInput.nextInt();
        System.out.println("Adding " + numberOfCopies + " " + selectedStatBlock.getName()
                + "s to play...");
        for (int i = 1; i < (numberOfCopies + 1); i++) {
            Character character = new Character(nameNewCharacter(),
                    selectedStatBlock.getSize(), selectedStatBlock.getType(), selectedStatBlock.getHpFormula(),
                    selectedStatBlock.getAC(), selectedStatBlock.getSpeed(), selectedStatBlock.getStrength(),
                    selectedStatBlock.getDexterity(), selectedStatBlock.getConstitution(),
                    selectedStatBlock.getIntelligence(), selectedStatBlock.getWisdom(),
                    selectedStatBlock.getCharisma(), selectedStatBlock.getActions());
            System.out.println("Added copy " + i + "!");
            play.add(character);
        }
        System.out.println("Done adding " + numberOfCopies + " statblocks.");
    }

    // EFFECTS: searches play for characters named after selectedStatBlock: if there's none, returns selected statblock
    //          name with 1 as suffix, otherwise arrays the character suffixes and returns selected statblock name with
    //          the lowest number not in the array as a suffix
    private String nameNewCharacter() {
        int lowestNumber = 1;
        List<Integer> suffixes;
        if (playContainsSelectedStatBlockName()) {
            suffixes = generateSuffixes();
            lowestNumber = findFirstIntegerGap(suffixes);
        }
        return selectedStatBlock.getName().toLowerCase() + lowestNumber;
    }

    // EFFECTS: returns true if play contains a character with the selected statblock name, excluding its suffix,
    //          false otherwise
    private boolean playContainsSelectedStatBlockName() {
        for (Character c : play) {
            if (c.getName().toLowerCase().contains(selectedStatBlock.getName().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns list of suffixes for characters in play with selected statblock name
    private List<Integer> generateSuffixes() {
        List<Integer> suffixes = new ArrayList<>();
        for (Character c : play) {
            if (c.getName().toLowerCase().contains(selectedStatBlock.getName().toLowerCase())) {
                suffixes.add(Integer.parseInt(c.getName().toLowerCase().replaceAll("[^\\d]", "")));
            }
        }
        return suffixes;
    }

    // REQUIRES: given interger list contains at least one integer
    // EFFECTS: returns lowest integer that is not already in the given list, starting with 1
    private int findFirstIntegerGap(List<Integer> integerList) {
        int firstLowest = 1;
        integerList.sort(Comparator.naturalOrder());
        for (int i : integerList) {
            if (i == firstLowest) {
                firstLowest++;
            } else if (i > firstLowest) {
                return firstLowest;
            }
        }
        return firstLowest;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // EFFECTS: initializes default statblocks in the library
    private void initializeLibrary() {
        RollFormula orcHPFormula = new RollFormula(2,8,6);
        RollFormula orcGreatAxeHit = new RollFormula(1, 20, 5);
        RollFormula orcGreatAxeDamage = new RollFormula(1, 12, 3);

        Action orcGreatAxe = new Action("GreatAxe", "Melee Weapon Attack.", "Slashing",
                5, orcGreatAxeHit, orcGreatAxeDamage);

        List<Action> orcActions = new ArrayList<>();
        orcActions.add(orcGreatAxe);

        StatBlock orc = new StatBlock("Orc", "Medium", "Humanoid", orcHPFormula, 13, 30,
                16, 12, 16, 7, 11, 10, orcActions);

        library.add(orc);

        //TODO library
    }

}
