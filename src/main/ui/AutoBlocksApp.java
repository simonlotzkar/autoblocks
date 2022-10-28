package ui;

import model.*;
import model.Character;
import model.statblockfields.*;

import java.text.NumberFormat;
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

    private static final String commandInvalid = "Command invalid!";

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
                System.out.println("Custom roll result: " + getRollFormula("custom").roll());
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
                System.out.println(commandInvalid);
                break;
        }
    }

    // EFFECTS: prints all characters in play with their names, groups, and hp
    private void displayPlay() {
        if (!play.isEmpty()) {
            for (Character c : play) {
                Title selectedTitle = c.getTitle();
                if (selectedTitle.getGroup() != null) {
                    System.out.println("\t" + selectedTitle.getName()
                            + " (Group: " + selectedTitle.getGroup() + "), HP: " + c.getHPString());
                } else {
                    System.out.println("\t" + selectedTitle.getName() + ", HP: " + c.getHPString());
                }
            }
        } else {
            System.out.println("\tThere are currently no characters in play.");
        }
    }

    // EFFECTS: prompts user for roll fields based on given name and returns a roll with given input
    private RollFormula getRollFormula(String rollName) {
        System.out.println("Enter amount of dice for " + rollName + " roll.");
        int amountOfDice = userInput.nextInt();
        System.out.println("Enter sides on dice for " + rollName + " roll.");
        int die = userInput.nextInt();
        System.out.println("Enter modifier for " + rollName + " roll.");
        int modifier = userInput.nextInt();

        return new RollFormula(amountOfDice, die, modifier);
    }

    // REQUIRES: selectType is either "character", "group", or "statblock"
    // MODIFIES: this
    // EFFECTS: prompts user for the name of given selectType, searches play and selects them if they exist
    private void searchForAndSelect(String selectType) {
        System.out.println("Enter " + selectType + " name.");
        String select = userInput.next().toLowerCase();

        if ("statblock".equals(selectType)) {
            selectStatBlockByName(select);
        } else if ("character".equals(selectType)) {
            selectCharacterByName(select);
        } else if ("group".equals(selectType)) {
            for (Character c : play) {
                Title selectedTitle = c.getTitle();
                if (selectedTitle.getGroup() != null && select.equals(selectedTitle.getGroup().toLowerCase())) {
                    selectGroupByName(select);
                    break;
                }
            }
        } else {
            System.out.println("Could not find specified " + selectType + ".");
        }
    }

    // EFFECTS: searches library for given statblock name, prints name, selects them, and changes to StatBlockMenu
    private void selectStatBlockByName(String statBlockName) {
        for (StatBlock sb : library) {
            if (statBlockName.equals(sb.getTitle().getName().toLowerCase())) {
                System.out.println("Found " + statBlockName + "!");
                selectedStatBlock = sb;
                menuLevel = "statblock";
            }
        }
    }

    // EFFECTS: searches play for given character name, prints name, selects them, and changes to CharacterMenu
    private void selectCharacterByName(String characterName) {
        for (Character c : play) {
            if (characterName.equals(c.getTitle().getName().toLowerCase())) {
                System.out.println("Found " + characterName + "!");
                selectedCharacter = c;
                menuLevel = "character";
            }
        }
    }

    // EFFECTS: prints given group name, selects it, creates an array, and changes to GroupMenu
    private void selectGroupByName(String groupName) {
        selectedGroupName = groupName;
        System.out.println("Found " + groupName + "!");
        System.out.println("Selecting group members...");
        for (Character c : play) {
            Title selectedTitle = c.getTitle();
            if (selectedTitle.getGroup() != null && selectedGroupName.equals(selectedTitle.getGroup())) {
                selectedGroup.add(c);
            }
        }
        System.out.println("Group members accounted for!");
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
                displayDeleteCharacterMenu();
                break;
            case "exit":
                goToMainMenu();
                break;
            default:
                System.out.println(commandInvalid);
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
        displayIndividualCombat(selected);
        displayIndividualAbilityScores(selected);
        displayIndividualDescriptors(selected);

        if (!selected.getAbilities().isEmpty()) {
            displayIndividualAbilities(selected);
        }

        displayIndividualActions(selected);

        if (selected.getLegendaryMechanics() != null) {
            displayIndividualLegendaryMechanics(selected);
        }
    }

    // EFFECTS: prints the title of the given statblock OR character
    private void displayIndividualTitle(StatBlock selected) {
        Title title = selected.getTitle();
        if (selected instanceof Character) {
            if (title.getGroup() != null) {
                System.out.println("\n" + title.getName() + " (Group: " + title.getGroup() + "): "
                        + title.getSize() + " " + title.getType() + ", " + title.getAlignment() + ":");
            } else {
                System.out.println("\n" + title.getName() + " (Group: None): "
                        + title.getSize() + " " + title.getType() + ", " + title.getAlignment() + ":");
            }
        } else {
            System.out.println("\n" + title.getName() + ": "
                    + title.getSize() + " " + title.getType() + ", " + title.getAlignment() + ":");
        }
    }

    // EFFECTS: prints the selected StatBlock or Character combat related stats
    private void displayIndividualCombat(StatBlock selected) {
        System.out.println("\t" + "Hit Points: " + selected.getHPString()
                + "\tArmour Class: " + selected.getArmour().getArmourString()
                + "\tSpeed: " + selected.getSpeeds().getSpeedsString()
                + "\tSenses: " + selected.getSenses().getSensesString()
                + "\tProficiency: " + selected.getProficiency()
                + "\tChallenge " + selected.getChallengeRating()
                + " (" + NumberFormat.getIntegerInstance().format(selected.getXp()) + "xp)");
    }

    // EFFECTS: prints the selected StatBlock or Character ability scores with modifiers
    private void displayIndividualAbilityScores(StatBlock selected) {
        AbilityScores abilityScores = selected.getAbilityScores();
        System.out.println("\tStr: " + abilityScores.getStrength()
                + "(" + abilityScores.getStrengthModifier() + ")"
                + ", Dex: " + abilityScores.getDexterity()
                + "(" + abilityScores.getDexterityModifier() + ")"
                + ", Con: " + abilityScores.getConstitution()
                + "(" + abilityScores.getConstitutionModifier() + ")"
                + ", Int: " + abilityScores.getIntelligence()
                + "(" + abilityScores.getIntelligenceModifier() + ")"
                + ", Wis: " + abilityScores.getWisdom()
                + "(" + abilityScores.getWisdomModifier() + ")"
                + ", Cha: " + abilityScores.getCharisma()
                + "(" + abilityScores.getCharismaModifier() + ")");
    }

    // EFFECTS: prints the selected StatBlock or Character descriptors that are not empty
    private void displayIndividualDescriptors(StatBlock selected) {
        String descriptors = "";

        SavingThrowProficiencies savingThrows = selected.getSavingThrowProficiencies();
        SkillProficiencies skills = selected.getSkillProficiencies();
        Resistances resistances = selected.getResistances();
        ConditionImmunities conditionImmunities = selected.getConditionImmunities();

        if (savingThrows != null) {
            descriptors += "\tProficient Saving Throws: " + savingThrows.getSavingThrowProficienciesString(selected);
        }

        if (skills != null) {
            descriptors += "\tProficient Skills: " + skills.getSkillProficienciesString(selected);
        }

        if (resistances != null) {
            descriptors += resistances.getResistancesString();
        }

        if (conditionImmunities != null) {
            descriptors += "\tCondition Immunities: " + conditionImmunities.getConditionImmunitiesString();
        }

        descriptors += ("\t" + selected.getLanguages().getLanguagesString());
        System.out.println(descriptors);
    }

    // EFFECTS: prints the selected StatBlock or Character abilities
    private void displayIndividualAbilities(StatBlock selected) {
        System.out.println("\tAbilities:");
        for (Ability a : selected.getAbilities()) {
            System.out.println("\t\t" + a.getName() + ": " + a.getDescription() + ". ");
        }
    }

    // EFFECTS: prints the selected StatBlock or Character actions
    private void displayIndividualActions(StatBlock selected) {
        System.out.println("\tActions:");
        for (Action a : selected.getActions()) {
            System.out.println("\t\t" + a.getName() + ": " + a.getDescription() + ". " + a.getReach() + " reach, "
                    + "(" + a.getHit().getRollString() + ") to hit, (" + a.getDamage().getRollString() + ") "
                    + a.getDamageType().toLowerCase() + " damage. ");
        }
    }

    // EFFECTS: prints the selected StatBlock or Character legendary mechanics
    private void displayIndividualLegendaryMechanics(StatBlock selected) {
        System.out.println("\tLegendary Mechanics:");
        System.out.println(selected.getLegendaryMechanics().getLegendaryDescription());
        for (Ability a : selected.getLegendaryMechanics().getLegendaryActions()) {
            System.out.println("\t" + a.getName() + ": " + a.getDescription() + ". ");
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
        if (selectedCharacter.getTitle().getGroup() != null) {
            System.out.println("Enter the new group for this character (one word), or type rem to remove its group:");
            String newGroup = userInput.next().toLowerCase();
            if (newGroup.equals("rem")) {
                selectedCharacter.getTitle().setGroup(null);
                System.out.println("Removed this character's group.");
            } else {
                selectedCharacter.getTitle().setGroup(newGroup.substring(0, 1).toUpperCase() + newGroup.substring(1));
                System.out.println("Changed this character's group.");
            }
        } else {
            System.out.println("Enter the new group (one word) for this character:");
            String newGroup = userInput.next().toLowerCase();
            selectedCharacter.getTitle().setGroup(newGroup.substring(0, 1).toUpperCase() + newGroup.substring(1));
            System.out.println("Changed this character's group.");
        }
    }

    // EFFECTS: prompts user for confirmation, removes selected character from play, then exits menu *MainMenu*
    private void displayDeleteCharacterMenu() {
        System.out.println("\nAre you sure you want to delete the selected character? Confirm:");
        System.out.println("\ty = yes,");
        System.out.println("\tn = no");
        String command = userInput.next().toLowerCase();
        processDeleteCharacterCommand(command);
    }

    // EFFECTS: processes deletecharactermenu commands
    private void processDeleteCharacterCommand(String command) {
        if (command.equals("y")) {
            play.remove(selectedCharacter);
            System.out.println("Deleted character.");
            goToMainMenu();
        } else if (command.equals("n")) {
            System.out.println("Cancelling deletion...");
        } else {
            System.out.println(commandInvalid);
            displayDeleteCharacterMenu();
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
        System.out.println("\nGroup Menu. Currently selected group: " + selectedGroupName + ". Members:");
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
            case "rem":
                displayReduceOrDeleteGroupMenu("remove");
                break;
            case "del":
                displayReduceOrDeleteGroupMenu("delete");
                break;
            case "exit":
                goToMainMenu();
                break;
            default:
                System.out.println(commandInvalid);
                break;
        }
    }

    // REQUIRES: instruction is either "remove" or "delete"
    // EFFECTS: prompts user for confirmation, removes group from characters in it, then exits menu *MainMenu*;
    //          OR does the same but also deletes characters in groups, depending on given instruction
    private void displayReduceOrDeleteGroupMenu(String instruction) {
        if (instruction.equals("remove")) {
            System.out.println("\nAre you sure you want to remove the selected group from all characters? Confirm:");
        } else {
            System.out.println("\nAre you sure you want to delete the selected group AND all its characters? Confirm:");
        }
        System.out.println("\ty = yes,");
        System.out.println("\tn = no");
        String command = userInput.next().toLowerCase();
        processReduceOrDeleteGroupCommand(instruction, command);
    }

    // EFFECTS: processes ReduceGroupMenu commands based on given instruction and command
    private void processReduceOrDeleteGroupCommand(String instruction, String command) {
        if ("y".equals(command)) {
            if (instruction.equals("remove")) {
                System.out.println("Removing group from characters...");
                removeGroupFromCharacters();
                System.out.println("Removed group from characters.");
                goToMainMenu();
            } else {
                System.out.println("Deleting group and characters...");
                deleteGroupAndCharacters();
                System.out.println("Deleted group and characters.");
                goToMainMenu();
            }
        } else if ("n".equals(command)) {
            System.out.println("Cancelling removal...");
        } else {
            System.out.println(commandInvalid);
            displayDeleteCharacterMenu();
        }
    }

    // EFFECTS: sets every group member in selected group to no group
    private void removeGroupFromCharacters() {
        for (Character c : selectedGroup) {
            c.getTitle().setGroup(null);
        }
    }

    // EFFECTS: removes all group members from selected group from play
    private void deleteGroupAndCharacters() {
        for (Character c : selectedGroup) {
            play.remove(c);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // EFFECTS: displays selected character OR group actions and RollMenu commands:
    //          - roll action
    //          - roll ability check
    //          - roll initiative
    //          - go back *MainMenu*
    private void displayRollMenu() {
        System.out.println("\nRoll Menu. Actions available for currently selected character or group:");
        System.out.println("-------------------------------------------------------------------------");

        displayRollMenuActions();

        System.out.println("\nRoll Commands:");
        System.out.println("\tact: Roll an action.");
        System.out.println("\tcheck: Make an ability check.");
        System.out.println("\tinit: Roll initiative.");
        System.out.println("\tback: Go back to the previous menu.");
        System.out.println("\texit: Exit to the main menu.");
    }

    // MODIFIES: this
    // EFFECTS: processes commands for RollMenu
    private void processRollCommand(String command) {
        switch (command) {
            case "act":
                displayActionMenu();
                break;
            case "check":
                displayAbilityCheckMenu();
                break;
            case "init":
                displayInitiativeMenu();
                break;
            case "back":
                goToPreviousMenu();
                break;
            case "exit":
                goToMainMenu();
                break;
            default:
                System.out.println(commandInvalid);
                break;
        }
    }

    // EFFECTS: checks if a character or group is selected and prints all actions amongst them, but doesn't repeat any
    //          parent statblock actions
    private void displayRollMenuActions() {
        if (selectedCharacter != null) {
            displayIndividualActions(selectedCharacter);
        } else {
            List<StatBlock> displayedStatBlocks = new ArrayList<>();
            for (Character c : selectedGroup) {
                if (!displayedStatBlocks.contains(c.getParentStatBlock())) {
                    displayIndividualActions(c);
                    displayedStatBlocks.add(c.getParentStatBlock());
                }
            }
        }
    }

    // EFFECTS: displays all available actions for either selected character or group, prompts user for an action,
    //          then rolls it once per character with that action
    private void displayActionMenu() {
        System.out.println("\nWhich action do you want to roll?");
        displayRollMenuActions();
        String command = userInput.next().toLowerCase();
        System.out.println("Rolling " + command + " action...");
        processActionCommand(command);
    }

    // EFFECTS: rolls given action for selected character, OR for all characters in selected group
    private void processActionCommand(String command) {
        if (selectedCharacter != null) {
            rollCharacterActionByName(selectedCharacter, command);
        } else {
            for (Character c : selectedGroup) {
                rollCharacterActionByName(c, command);
            }
        }
    }

    // EFFECTS: searches given character's actions for one with given name, then prints its roll
    private void rollCharacterActionByName(Character character, String action) {
        for (Action a : character.getActions()) {
            if (a.getName().equalsIgnoreCase(action)) {
                System.out.println(a.rollAsStringForName(character.getTitle().getName()));
                break;
            }
        }
    }

    // EFFECTS: prompts user for which abilitycheck to make, then rolls abilitycheck for selected character,
    //          OR all characters in selected group
    private void displayAbilityCheckMenu() {
        System.out.println("\nWhich ability check do you want to roll?");
        System.out.println("\tstr = Strength check.");
        System.out.println("\tdex = Dexterity check.");
        System.out.println("\tcon = Constitution check.");
        System.out.println("\tint = Intelligence check.");
        System.out.println("\twis = Wisdom check.");
        System.out.println("\tcha = Charisma check.");
        String command = userInput.next().toLowerCase();
        System.out.println("Rolling " + command + " check...");
        processAbilityCheckCommand(command);
    }

    // EFFECTS: rolls a check depending on the given command
    private void processAbilityCheckCommand(String command) {
        switch (command) {
            case "str":
                displayAbilityCheck("strength");
                break;
            case "dex":
                displayAbilityCheck("dexterity");
                break;
            case "con":
                displayAbilityCheck("constitution");
                break;
            case "int":
                displayAbilityCheck("intelligence");
                break;
            case "wis":
                displayAbilityCheck("wisdom");
                break;
            case "cha":
                displayAbilityCheck("charisma");
                break;
            default:
                System.out.println(commandInvalid);
                break;
        }
    }

    // EFFECTS: prints given check for selected character OR all characters in selected group
    private void displayAbilityCheck(String ability) {
        if (selectedCharacter != null) {
            System.out.println(selectedCharacter.getTitle().getName()
                    + "'s " + ability + "check: " + rollAbilityCheck(selectedCharacter, ability) + ".");
        } else {
            for (Character c : selectedGroup) {
                System.out.println(c.getTitle().getName() + "'s " + ability + "check: " + rollAbilityCheck(c, ability));
            }
        }
    }

    // REQUIRES: given ability is one of: strength, dexterity, constitution, intelligence, wisdom, or charisma
    // EFFECTS: rolls given ability's check for given character
    private int rollAbilityCheck(Character character, String ability) {
        switch (ability) {
            case "strength":
                return rollStrengthCheck(character);
            case "dexterity":
                return rollDexterityCheck(character);
            case "constitution":
                return rollConstitutionCheck(character);
            case "intelligence":
                return rollIntelligenceCheck(character);
            case "wisdom":
                return rollWisdomCheck(character);
            default:
                return rollCharismaCheck(character);
        }
    }

    // EFFECTS: rolls a strength check for the given character
    private int rollStrengthCheck(Character character) {
        RollFormula rollFormula = new RollFormula(1, 20, character.getAbilityScores().getStrengthModifier());
        return rollFormula.roll();
    }

    // EFFECTS: rolls a dexterity check for the given character
    private int rollDexterityCheck(Character character) {
        RollFormula rollFormula = new RollFormula(1, 20, character.getAbilityScores().getDexterityModifier());
        return rollFormula.roll();
    }

    // EFFECTS: rolls a constitution check for the given character
    private int rollConstitutionCheck(Character character) {
        RollFormula rollFormula = new RollFormula(1, 20, character.getAbilityScores().getConstitutionModifier());
        return rollFormula.roll();
    }

    // EFFECTS: rolls a intelligence check for the given character
    private int rollIntelligenceCheck(Character character) {
        RollFormula rollFormula = new RollFormula(1, 20, character.getAbilityScores().getIntelligenceModifier());
        return rollFormula.roll();
    }

    // EFFECTS: rolls a wisdom skill for the given character
    private int rollWisdomCheck(Character character) {
        RollFormula rollFormula = new RollFormula(1, 20, character.getAbilityScores().getWisdomModifier());
        return rollFormula.roll();
    }

    // EFFECTS: rolls a charisma check for the given character
    private int rollCharismaCheck(Character character) {
        RollFormula rollFormula = new RollFormula(1, 20, character.getAbilityScores().getCharismaModifier());
        return rollFormula.roll();
    }

    // EFFECTS: rolls initiative for selected character, OR all characters in selected group
    private void displayInitiativeMenu() {
        System.out.println("Rolling initiative...");
        if (selectedCharacter != null) {
            System.out.println(selectedCharacter.getTitle().getName()
                    + "'s initiative: " + rollDexterityCheck(selectedCharacter) + ".");
        } else {
            for (Character c : selectedGroup) {
                System.out.println(c.getTitle().getName() + "'s initiative: " + rollDexterityCheck(c));
            }
        }
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

    // EFFECTS: prints all statblocks in the library with their names
    private void displayLibrary() {
        for (StatBlock sb : library) {
            System.out.println(sb.getTitle().getName());
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
                System.out.println(commandInvalid);
                break;
        }
    }

    // EFFECTS: prompts user for new statblock parameters, then constructs a new statblock based on userinput and
    //          adds it to the library. if user gives a name already in use, reprompts for different name.
    private void createCustomStatBlock() {
        Title title = getCustomStatBlockTitle();
        int xp = getCustomInteger("statblock xp");
        RollFormula hpFormula = getRollFormula("custom statblock hp formula");
        int proficiency = getCustomInteger("statblock proficiency bonus");
        Armour armour = getCustomStatBlockArmour();
        Speeds speeds = getCustomStatBlockSpeeds();
        Senses senses = getCustomStatBlockSenses();
        AbilityScores abilityScores = getCustomStatBlockAbilityScores();
        List<Ability> abilities = getCustomStatBlockAbilities("abilities");
        List<Action> actions = getCustomStatBlockActions();
        Languages languages = getCustomStatBlockLanguages();

        StatBlock customStatBlock = new StatBlock.StatBlockBuilder(title, xp, hpFormula, proficiency, armour, speeds,
                senses, abilityScores, abilities, actions, languages)
                .savingThrowProficiencies(promptGetCustomStatBlockSavingThrowProficiencies())
                .skillProficiencies(promptGetCustomStatBlockSkillProficiencies())
                .conditionImmunities(promptGetCustomStatBlockConditionImmunities())
                .resistances(promptGetCustomStatBlockResistances())
                .legendaryMechanics(promptGetCustomStatBlockLegendaryMechanics())
                .build();

        library.add(customStatBlock);
    }

    // EFFECTS: prompts user for given string and returns it
    private String getCustomString(String customString) {
        System.out.println("Enter custom " + customString + ": ");
        return userInput.next();
    }

    // EFFECTS: prompts user for given integer and returns it
    private int getCustomInteger(String customInteger) {
        System.out.println("Enter custom " + customInteger + ": ");
        return userInput.nextInt();
    }

    // EFFECTS: prompts user for given statblock string field and returns it
    private String getCustomStatBlockString(String field) {
        return getCustomString("statblock " + field);
    }

    // EFFECTS: prompts user for given statblock integer field and returns it
    private int getCustomStatBlockInteger(String field) {
        return getCustomInteger("statblock " + field);
    }

    // EFFECTS: prompts user for given question and returns true or false based on user input
    private boolean promptConfirmation(String prompt) {
        System.out.println("Confirm: " + prompt + "? Input y for yes or n for no...");
        switch (userInput.next().toLowerCase()) {
            case "y":
                return true;
            case "n":
                return false;
            default:
                System.out.println(commandInvalid);
                return promptConfirmation(prompt);
        }
    }

    // EFFECTS: prompts user for given statblock boolean field and returns it
    private boolean getCustomStatBlockBoolean(String field) {
        return promptConfirmation("Statblock " + field);
    }

    // EFFECTS: prompts user with confirmation to add armour name to the custom statblock;
    //          if yes, prompts user for an armour name and returns it;
    //          if no, returns null.
    private String getCustomStatBlockArmourName() {
        if (promptConfirmation("add custom statblock armour name")) {
            return getCustomString("statblock armour name");
        }
        return null;
    }

    // EFFECTS: prompts user to add an optional integer and gets it if the user confirms it, or 0
    private int getCustomOptionalInteger(String customInteger) {
        if (promptConfirmation("add custom " + customInteger)) {
            return getCustomInteger(customInteger);
        }
        return 0;
    }

    // EFFECTS: prompts user to add an optional statblock integer field and gets it if the user confirms it, or 0
    private int getCustomStatBlockOptionalInteger(String field) {
        return getCustomOptionalInteger("statblock " + field);
    }

    // EFFECTS: prompts user for custom statblock title parameters then constructs a title with them, excluding group;
    //          if user gives a name already in use, reprompts for different name;
    private Title getCustomStatBlockTitle() {
        return new Title.TitleBuilder(
                getCustomStatBlockName(),
                getCustomStatBlockString("size"),
                getCustomStatBlockString("type"),
                getCustomStatBlockString("alignment")).build();
    }

    // EFFECTS: prompts user for new custom statblock name, reasks if the given name belongs to an existing statblock
    //          until a unique name is provided, then returns that name.
    private String getCustomStatBlockName() {
        String name = getCustomStatBlockString("name");
        for (StatBlock sb : library) {
            if ((sb.getTitle().getName().toLowerCase()).equals(name)) {
                System.out.println("This name is already in use. Try again...");
                getCustomStatBlockName();
            }
        }
        return name;
    }

    // EFFECTS: prompts user for custom StatBlock armour parameters then returns it
    private Armour getCustomStatBlockArmour() {
        return new Armour.ArmourBuilder(getCustomStatBlockInteger("armour ac"))
                .armourName(getCustomStatBlockArmourName())
                .magicArmour(getCustomStatBlockOptionalInteger("magic armour ac"))
                .build();
    }

    // EFFECTS: prompts user for custom StatBlock speeds parameters then returns it
    private Speeds getCustomStatBlockSpeeds() {
        return new Speeds.SpeedsBuilder(getCustomStatBlockInteger("speed"))
                .burrow(getCustomStatBlockOptionalInteger("burrowing speed"))
                .climb(getCustomStatBlockOptionalInteger("climbing speed"))
                .fly(getCustomStatBlockOptionalInteger("flying speed"))
                .swim(getCustomStatBlockOptionalInteger("swimming speed"))
                .build();
    }

    // EFFECTS: prompts user for custom StatBlock senses parameters then returns it
    private Senses getCustomStatBlockSenses() {
        return new Senses.SensesBuilder(getCustomStatBlockInteger("passive perception"))
                .blindSight(getCustomStatBlockOptionalInteger("blindsight"))
                .darkVision(getCustomStatBlockOptionalInteger("darkvision"))
                .tremorSense(getCustomStatBlockOptionalInteger("tremorsense"))
                .trueSight(getCustomStatBlockOptionalInteger("truesight"))
                .build();
    }

    // EFFECTS: prompts user for custom StatBlock ability scores then returns it
    private AbilityScores getCustomStatBlockAbilityScores() {
        String as = " ability score";

        return new AbilityScores(getCustomStatBlockInteger("strength" + as),
                getCustomStatBlockInteger("dexterity" + as),
                getCustomStatBlockInteger("constitution" + as),
                getCustomStatBlockInteger("intelligence" + as),
                getCustomStatBlockInteger("wisdom" + as),
                getCustomStatBlockInteger("charisma" + as));
    }

    // EFFECTS: prompts user for number of given ability types to add, then prompts for each field for each ability
    //          and returns them as a list
    private List<Ability> getCustomStatBlockAbilities(String abilityType) {
        List<Ability> abilities =  new ArrayList<>();
        System.out.println("How many " + abilityType + " do you want to give your custom statblock?");
        int numberOfAbilities = userInput.nextInt() + 1;
        for (int i = 1; i < numberOfAbilities; i++) {
            System.out.println("Adding " + abilityType + " " + i + "...");
            abilities.add(getCustomAbility(abilityType));
        }
        return abilities;
    }

    // EFFECTS: prompts user for ability fields of given ability type and constructs an ability with them,
    //          then returns it
    private Ability getCustomAbility(String abilityType) {
        String name = getCustomString(abilityType + " name");
        String description = getCustomString(abilityType + " description");

        return new Ability(name, description);
    }

    // EFFECTS: prompts user for number of actions to add, then prompts for each field for each action
    //          and returns them as a list
    private List<Action> getCustomStatBlockActions() {
        List<Action> actions =  new ArrayList<>();
        System.out.println("How many actions do you want to give your custom statblock?");
        int numberOfActions = userInput.nextInt() + 1;
        for (int i = 1; i < numberOfActions; i++) {
            System.out.println("Adding action " + i + "...");
            actions.add(getCustomAction());
        }
        return actions;
    }

    // EFFECTS: prompts user for action fields and constructs an action with them, then returns it
    private Action getCustomAction() {
        String name = getCustomString("action name");
        String description = getCustomString("action description");
        String damageType = getCustomString("action damage type");
        String reach = getCustomString("action reach");

        RollFormula hit = getRollFormula("action hit");
        RollFormula damage = getRollFormula("action damage");

        return new Action(name, description, damageType, reach, hit, damage);
    }

    // EFFECTS: prompts user for custom StatBlock languages and telepathy then returns them as a Languages
    private Languages getCustomStatBlockLanguages() {
        List<String> listOfLanguages = getCustomStatBlockListOfLanguages();
        int telepathy = getCustomStatBlockOptionalInteger("telepathy range");

        return new Languages.LanguagesBuilder(listOfLanguages).telepathy(telepathy).build();
    }

    // EFFECTS: prompts user for number of languages to add, then prompts for each language and returns them as a list
    private List<String> getCustomStatBlockListOfLanguages() {
        List<String> listOfLanguages =  new ArrayList<>();
        System.out.println("How many languages do you want to give your custom statblock?");
        int numberOfActions = userInput.nextInt() + 1;
        for (int i = 1; i < numberOfActions; i++) {
            System.out.println("Adding language " + i + "...");
            listOfLanguages.add(getCustomStatBlockString("language"));
        }
        return listOfLanguages;
    }

    // EFFECTS: prompts user if they want to add saving throw proficiencies to the custom statblock:
    //          if yes they are prompted for each saving throw and returns them as a SavingThrowProficiencies;
    //          if no, returns a default SavingThrowProficiencies
    private SavingThrowProficiencies promptGetCustomStatBlockSavingThrowProficiencies() {
        if (promptConfirmation("add custom statblock saving throw proficiencies")) {
            return getCustomStatBlockSavingThrowProficiencies();
        } else {
            return new SavingThrowProficiencies.SavingThrowProficienciesBuilder().build();
        }
    }

    // EFFECTS: prompts user for custom StatBlock all saving throw proficiencies,
    //          then returns them as a SavingThrowProficiencies
    private SavingThrowProficiencies getCustomStatBlockSavingThrowProficiencies() {
        String suffix = " saving throw proficiency";

        return new SavingThrowProficiencies.SavingThrowProficienciesBuilder()
                .strengthProficiency(getCustomStatBlockBoolean("strength" + suffix))
                .dexterityProficiency(getCustomStatBlockBoolean("dexterity" + suffix))
                .constitutionProficiency(getCustomStatBlockBoolean("constitution" + suffix))
                .intelligenceProficiency(getCustomStatBlockBoolean("intelligence" + suffix))
                .wisdomProficiency(getCustomStatBlockBoolean("wisdom" + suffix))
                .charismaProficiency(getCustomStatBlockBoolean("charisma" + suffix))
                .build();
    }

    // EFFECTS: prompts user if they want to add skill proficiencies to the custom statblock:
    //          if yes they are prompted for each skill and returns them as a SkillProficiencies;
    //          if no, returns a default SkillProficiencies
    private SkillProficiencies promptGetCustomStatBlockSkillProficiencies() {
        if (promptConfirmation("add custom statblock skill proficiencies")) {
            return getCustomStatBlockSkillProficiencies();
        } else {
            return new SkillProficiencies.SkillProficienciesBuilder().build();
        }
    }

    // EFFECTS: prompts user for custom StatBlock skill proficiencies then returns them as a SkillProficiencies
    private SkillProficiencies getCustomStatBlockSkillProficiencies() {
        String suffix = " skill proficiency";

        return new SkillProficiencies.SkillProficienciesBuilder()
                .acrobatics(getCustomStatBlockBoolean("acrobatics" + suffix))
                .animalHandling(getCustomStatBlockBoolean("animal handling" + suffix))
                .arcana(getCustomStatBlockBoolean("arcana" + suffix))
                .athletics(getCustomStatBlockBoolean("athletics" + suffix))
                .deception(getCustomStatBlockBoolean("deception" + suffix))
                .history(getCustomStatBlockBoolean("history" + suffix))
                .insight(getCustomStatBlockBoolean("insight" + suffix))
                .intimidation(getCustomStatBlockBoolean("intimidation" + suffix))
                .investigation(getCustomStatBlockBoolean("investigation" + suffix))
                .medicine(getCustomStatBlockBoolean("medicine" + suffix))
                .nature(getCustomStatBlockBoolean("nature" + suffix))
                .perception(getCustomStatBlockBoolean("perception" + suffix))
                .performance(getCustomStatBlockBoolean("performance" + suffix))
                .persuasion(getCustomStatBlockBoolean("persuasion" + suffix))
                .religion(getCustomStatBlockBoolean("religion" + suffix))
                .sleightOfHand(getCustomStatBlockBoolean("sleight of hand" + suffix))
                .stealth(getCustomStatBlockBoolean("stealth" + suffix))
                .survival(getCustomStatBlockBoolean("survival" + suffix))
                .build();
    }

    // EFFECTS: prompts user if they want to add condition immunities to the custom statblock:
    //          if yes they are prompted for each condition and returns them as a ConditionImmunities;
    //          if no, returns a default ConditionImmunities
    private ConditionImmunities promptGetCustomStatBlockConditionImmunities() {
        if (promptConfirmation("add custom statblock condition immunities")) {
            return getCustomStatBlockConditionImmunities();
        } else {
            return new ConditionImmunities.ConditionImmunitiesBuilder().build();
        }
    }

    // EFFECTS: prompts user for custom StatBlock condition immunities then returns them as a ConditionImmunities
    private ConditionImmunities getCustomStatBlockConditionImmunities() {
        String suffix = " condition immunity";

        return new ConditionImmunities.ConditionImmunitiesBuilder()
                .blinded(getCustomStatBlockBoolean("blinded" + suffix))
                .charmed(getCustomStatBlockBoolean("charmed" + suffix))
                .deafened(getCustomStatBlockBoolean("deafened" + suffix))
                .frightened(getCustomStatBlockBoolean("frightened" + suffix))
                .grappled(getCustomStatBlockBoolean("grappled" + suffix))
                .incapacitated(getCustomStatBlockBoolean("incapacitated" + suffix))
                .invisible(getCustomStatBlockBoolean("invisible" + suffix))
                .paralyzed(getCustomStatBlockBoolean("paralyzed" + suffix))
                .petrified(getCustomStatBlockBoolean("petrified" + suffix))
                .poisoned(getCustomStatBlockBoolean("poisoned" + suffix))
                .prone(getCustomStatBlockBoolean("prone" + suffix))
                .restrained(getCustomStatBlockBoolean("restrained" + suffix))
                .stunned(getCustomStatBlockBoolean("stunned" + suffix))
                .unconscious(getCustomStatBlockBoolean("unconscious" + suffix))
                .build();
    }

    // EFFECTS: prompts user if they want to add damage type resistances to the custom statblock:
    //          if yes they are prompted for each damage type and returns them as a Resistances;
    //          if no, returns a default Resistances
    private Resistances promptGetCustomStatBlockResistances() {
        if (promptConfirmation("add custom statblock damage type vulnerabilities/resistances/immunities")) {
            return getCustomStatBlockResistances();
        } else {
            return new Resistances.ResistancesBuilder().build();
        }
    }

    // EFFECTS: prompts user for custom StatBlock resistances then returns them as a Resistances
    private Resistances getCustomStatBlockResistances() {
        String suffix = " damage resistance";

        return new Resistances.ResistancesBuilder()
                .acid(getCustomStatBlockOptionalResistance("acid" + suffix))
                .bludgeoning(getCustomStatBlockOptionalResistance("bludgeoning" + suffix))
                .cold(getCustomStatBlockOptionalResistance("cold" + suffix))
                .fire(getCustomStatBlockOptionalResistance("fire" + suffix))
                .force(getCustomStatBlockOptionalResistance("force" + suffix))
                .lightning(getCustomStatBlockOptionalResistance("lightning" + suffix))
                .necrotic(getCustomStatBlockOptionalResistance("necrotic" + suffix))
                .piercing(getCustomStatBlockOptionalResistance("piercing" + suffix))
                .poison(getCustomStatBlockOptionalResistance("poison" + suffix))
                .psychic(getCustomStatBlockOptionalResistance("psychic" + suffix))
                .radiant(getCustomStatBlockOptionalResistance("radiant" + suffix))
                .slashing(getCustomStatBlockOptionalResistance("slashing" + suffix))
                .thunder(getCustomStatBlockOptionalResistance("thunder" + suffix))
                .nonMagical(getCustomStatBlockOptionalResistance("non-magical" + suffix))
                .nonSilver(getCustomStatBlockOptionalResistance("non-silver" + suffix))
                .nonAdamantine(getCustomStatBlockOptionalResistance("non-adamantine" + suffix))
                .build();
    }

    // EFFECTS: prompts user for confirmation to add the given damage type resistance;
    //          if yes, prompts for which type of resistance to assign given damage type and then returns it;
    //          if no, returns null.
    private String getCustomStatBlockOptionalResistance(String customString) {
        if (promptConfirmation("add custom " + customString)) {
            return getCustomResistance(customString);
        }
        return null;
    }

    // EFFECTS: prompts for which type of resistance to assign given damage type and then returns it
    private String getCustomResistance(String customString) {
        System.out.println("Enter resistant, immune, or vulnerable for " + customString + ": ");
        String command = userInput.next();
        switch (command) {
            case "resistant":
                return "resistance";
            case "immune":
                return "immunity";
            case "vulnerable":
                return "vulnerability";
            default:
                System.out.println(commandInvalid);
                return getCustomResistance(customString);
        }
    }

    // EFFECTS: prompts user if they want to add legendary mechanics to the custom statblock:
    //          if yes they are prompted for the parameters and returns them as a LegendaryMechanics;
    //          if no, returns a LegendaryMechanics with null fields
    private LegendaryMechanics promptGetCustomStatBlockLegendaryMechanics() {
        if (promptConfirmation("add custom statblock legendary mechanics")) {
            return getCustomStatBlockLegendaryMechanics();
        } else {
            return new LegendaryMechanics(null, null);
        }
    }

    // EFFECTS: prompts user for custom StatBlock legendary mechanics then returns them as a LegendaryMechanics
    private LegendaryMechanics getCustomStatBlockLegendaryMechanics() {
        return new LegendaryMechanics(getCustomStatBlockString("legendary description"),
                getCustomStatBlockAbilities("legendary actions"));
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
                System.out.println(commandInvalid);
                break;
        }
    }

    // EFFECTS: add any number of the selected statblock to play as characters with no unique names
    private void createCharacters() {
        System.out.println("How many copies of this statblock do you want to add?");
        int numberOfCopies = userInput.nextInt();
        System.out.println("Adding " + numberOfCopies + " copies of " + selectedStatBlock.getTitle().getName()
                + " to play...");
        for (int i = 1; i < (numberOfCopies + 1); i++) {
            Character character = new Character.CharacterBuilder(selectedStatBlock, getNewCharacterTitle(),
                    selectedStatBlock.getXp(), selectedStatBlock.getHpFormula(),selectedStatBlock.getProficiency(),
                    selectedStatBlock.getArmour(), selectedStatBlock.getSpeeds(), selectedStatBlock.getSenses(),
                    selectedStatBlock.getAbilityScores(), selectedStatBlock.getAbilities(),
                    selectedStatBlock.getActions(), selectedStatBlock.getLanguages()).build();
            System.out.println("Added copy " + i + "of " + selectedStatBlock.getTitle().getName() + "!");
            play.add(character);
        }
        System.out.println("Done adding " + numberOfCopies + " statblocks.");
        goToLibraryMenu();
    }

    // EFFECTS: returns a title with the parameters of the selected statblock, except for name which is generated
    //          based on the names of already existing character names (keeps all names unique)
    private Title getNewCharacterTitle() {
        Title parentTitle = selectedStatBlock.getTitle();
        return new Title.TitleBuilder(nameNewCharacter(), parentTitle.getSize(), parentTitle.getType(),
                parentTitle.getAlignment()).build();
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
        return selectedStatBlock.getTitle().getName().toLowerCase() + lowestNumber;
    }

    // EFFECTS: returns true if play contains a character with the selected statblock name, excluding its suffix,
    //          false otherwise
    private boolean playContainsSelectedStatBlockName() {
        for (Character c : play) {
            if (c.getTitle().getName().toLowerCase().contains(selectedStatBlock.getTitle().getName().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns list of suffixes for characters in play with selected statblock name
    private List<Integer> generateSuffixes() {
        List<Integer> suffixes = new ArrayList<>();
        for (Character c : play) {
            if (c.getTitle().getName().toLowerCase().contains(selectedStatBlock.getTitle().getName().toLowerCase())) {
                suffixes.add(Integer.parseInt(c.getTitle().getName().toLowerCase().replaceAll("[^\\d]", "")));
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
        System.out.println("Initializing library...");
        initializeOrcStatBlock();
        System.out.println("All default statblocks added to library!");
    }

    // EFFECTS: adds orc and all its parameters to the library
    private void initializeOrcStatBlock() {
        List<String> orcLanguages = new ArrayList<>();
        orcLanguages.add("Common");
        orcLanguages.add("Orc");
        Ability aggression = new Ability("Aggressive", "As a bonus Action, the orc can move up to its "
                + "speed toward a Hostile creature that it can see.");
        List<Ability> orcAbilities = new ArrayList<>();
        orcAbilities.add(aggression);

        StatBlock orc = new StatBlock.StatBlockBuilder(
                (new Title.TitleBuilder("Orc", "Humanoid (Orc)", "Medium", "Chaotic Evil").build()),
                100, new RollFormula(2, 8, 6), 2,
                (new Armour.ArmourBuilder(13).armourName("Hide Armour").build()),
                (new Speeds.SpeedsBuilder(30).build()),
                (new Senses.SensesBuilder(10).darkVision(60).build()),
                (new AbilityScores(16, 12, 16, 7, 11, 10)),
                (orcAbilities),
                returnOrcActions(),
                (new Languages.LanguagesBuilder(orcLanguages).build()))
                .skillProficiencies(new SkillProficiencies.SkillProficienciesBuilder().intimidation(true).build())
                .build();

        library.add(orc);
    }

    // EFFECTS: returns orc actions
    private List<Action> returnOrcActions() {
        Action orcGreatAxe = new Action("GreatAxe", "Melee Weapon Attack", "Slashing", "5ft",
                new RollFormula(1, 20, 5),  //hit formula
                new RollFormula(1, 12, 3)); //damage formula
        Action orcJavelinMelee = new Action("Javelin", "Melee Weapon Attack", "Piercing", "5ft",
                new RollFormula(1, 20, 5),  //hit formula
                new RollFormula(1, 6, 3)); //damage formula
        Action orcJavelinRanged = new Action("Javelin", "Ranged Weapon Attack", "Piercing", "30/120ft",
                new RollFormula(1, 20, 5),  //hit formula
                new RollFormula(1, 6, 3)); //damage formula

        List<Action> orcActions = new ArrayList<>();
        orcActions.add(orcGreatAxe);
        orcActions.add(orcJavelinMelee);
        orcActions.add(orcJavelinRanged);

        return orcActions;
    }
}
