# AutoBlocks
## for Dungeons and Dragons 5th Edition by Wizards of the Coast.
<p>
This is a tool for dungeon and dragons (D&D) dungeon masters (DMs) to automate mechanics for non player characters (NPCs).
This does not simulate any form of map, it's intended to be used with an external one.
</p>

- with this application you can do the following:
  - **view and create statblocks**
  - **create NPCs from statblocks**
  - **make single or multi-rolls for NPCs (initiative, actions, etc)**
  - **track and edit NPC hit points**
  - **group characters together**
<p>
I made this application because I'm a D&D DM and I think there is no other application like this one. While there are 
some other applications for automating rolls, most try to do automation and map simulation at the same time. I think 
it's better to keep them separate because some people like theatre of the mind (no maps), and some people prefer in 
person. This app allows the DM to only exclude repetitive simple rolls and hp management so they can focus on the rest
of the game. So in short, I made this for personal use, but also because I think it solves a problem in a unique way.
</p>

## User Stories
<p>
As a user, I want to be able to:
</p>

- simulate rolling any number of dice, with any number of sides, and with any modifier
- view a list of statblocks by their names, (aka the 'library') and do the following:
    - select statblock in the library and do the following:
        - display all statblock information
        - add any number of statblock to encounter as new NPCs
    - add new, custom statblock to the library
    - remove statblocks from the library
- view a list of NPCs (aka the 'encounter') by their names, groups, and hp; and view all rollable actions. functions:
- select any number of NPCs in the encounter and do the following:
- roll ability check
- roll initiative
- change hitpoints by custom amount
- change group
- delete from the encounter
- select a single NPC in the encounter and do the following:
- open NPC's stats sheet and take all the normal functions; updates rollable actions
- open NPC's group and take all the normal functions; updates rollable actions;
- chose to save the current library and/or encounter to file (from title menu), and 
- chose to load the library and/or encounter from file (from title menu)

### Future Additions:
- come with all default monsters in the 5th edition monsters manual
- add NPC name tags to the actions display and roll outputs
- save system to allow for multiple save/load files
- automated rolls for skills and saving throws
- turn order and automation of turns
- inventory/loot generation
- custom action window

# Instructions for grader
- You can generate multiple events related to adding StatBlocks (Xs) to the Library (Y) by selecting "Create New" after
navigating to the library menu from the title screen. You can also generate multiple events relating to adding NPCs 
(additional Xs) to the Encounter (additional Y) by selecting a statblock from the library and clicking "Add to Encounter".
- You can locate my visual component by opening the app!
- You can save the state of my application by selecting the save option from the title screen.
- You can reload the state of my application by selecting the load option from the title screen.

## Phase 4: Task 2
- Start of EventLog:
- Fri Dec 02 11:47:38 PST 2022:
- Encounter constructed.
- Fri Dec 02 11:47:38 PST 2022:
- Library constructed.
- Fri Dec 02 11:47:41 PST 2022:
- Removed all encounter elements.
- Fri Dec 02 11:47:41 PST 2022:
- Added "Orc 1 (Group: Orcs), HP: 9/9" to the encounter.
- Fri Dec 02 11:47:41 PST 2022:
- Added "Orc 2 (Group: Orcs), HP: 4/4" to the encounter.
- Fri Dec 02 11:47:41 PST 2022:
- Added "Orc 3 (Group: Orcs), HP: 6/6" to the encounter.
- Fri Dec 02 11:47:41 PST 2022:
- Added "Goblin 1 (Group: Goblins), HP: 5/5" to the encounter.
- Fri Dec 02 11:47:41 PST 2022:
- Added "Goblin 2 (Group: Goblins), HP: 3/3" to the encounter.
- Fri Dec 02 11:47:41 PST 2022:
- Removed all library elements.
- Fri Dec 02 11:47:41 PST 2022:
- Added "Orc. CR: 1/2" to the library.
- Fri Dec 02 11:47:41 PST 2022:
- Added "Goblin. CR: 1/4" to the library.
- Fri Dec 02 11:47:46 PST 2022:
- Removed all encounter elements.
- Fri Dec 02 11:47:46 PST 2022:
- Added "Orc 1 (Group: Orcs), HP: 9/9" to the encounter.
- Fri Dec 02 11:47:46 PST 2022:
- Added "Orc 2 (Group: Orcs), HP: 4/4" to the encounter.
- Fri Dec 02 11:47:46 PST 2022:
- Added "Orc 3 (Group: Orcs), HP: 6/6" to the encounter.
- Fri Dec 02 11:47:46 PST 2022:
- Added "Goblin 1 (Group: Goblins), HP: 5/5" to the encounter.
- Fri Dec 02 11:47:46 PST 2022:
- Added "Goblin 2 (Group: Goblins), HP: 3/3" to the encounter.
- Fri Dec 02 11:47:46 PST 2022:
- Added "Goblin 3, HP: 4/4" to the encounter.
- Fri Dec 02 11:47:46 PST 2022:
- Added "Goblin 4, HP: 3/3" to the encounter.
- Fri Dec 02 11:47:53 PST 2022:
- Removed "Goblin 1 (Group: Goblins), HP: 5/5" from the encounter.
- Fri Dec 02 11:47:53 PST 2022:
- Removed "Goblin 2 (Group: Goblins), HP: 3/3" from the encounter.
- End of EventLog.