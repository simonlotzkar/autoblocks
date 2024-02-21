# AutoBlocks
## for Dungeons and Dragons 5th Edition by Wizards of the Coast.
<p>
This is a tool for dungeon and dragons (D&D) dungeon masters (DMs) to automate mechanics for non player characters (NPCs).
This does not simulate any form of map, it's intended to be used with an external one.
</p>

***With this application you can do the following:***
  - **simulate rolling any number of dice, with any number of sides, and with any modifier**
  - **view a list of statblocks by their names (library):**
    - *view statblock details*
    - *add custom statblocks*
    - *delete statblocks*
  - **view a list of NPCs by their names and hit points (encounter):**
    - *auto roll actions, initiatives, or ability checks*
    - *track and edit hit points*
  - **save and load your libraries and encounters to a file of your choosing**

<p>
I made this application because I'm a D&D fan, and I couldn't find a program that was made just for the DM to only 
automate NPC mechanics. This app allows the DM to only exclude repetitive simple rolls and hp management, so they can 
focus on the rest of the game. This is for personal use, but I think any DM would benefit from this app!
</p>

### Usage:
Download the jar. From this page (/main) -> "out" -> "artifacts/AutoBlocks_jar" -> "AutoBlocks.jar" -> on the top right, click the download icon ("download raw file"). 
Or you can fork and compile the code from an IDE/command line.
<br/>
Once in the app, save and load to any directory. I have an example file that you can download and use. From this page (/main) -> "autoBlocksApp.json" -> on the top right, click the download icon ("download raw file"). 

### Known Limitations/Bugs:
- custom action window is unimplemented (button does nothing)

### 2.0 Improvements:
In the future I want to rebuild this project as a web application so it's more accessible. Here are some features I'd like to include as expansions:
- polish **ux/ui** design
    - custom roll window
        - make fields above roll button and add parchment background
    - buttons
        - add custom text to all
        - create custom image for backgrounds (and maybe redo icons)
    - display of actions (formulae and rolls)
        - change from strings to grid panels (hit/dmg/etc)
    - statblock/npc displays
        - change text areas to panels with sub grid
    - encounter and library lists
      - convert to: "JTable - Sort Using Column Headers"
      - add "sort by..." combobox next to the main display titles
          - name
          - type
          - size
          - alignment
          - cr
          - hp
- automate **skills**
- automate **saving throws**
- automate **conditions**
  - track and view conditions for npcs
  - button that adds a condition to the selected npcs but takes immunities into account
- add **advantage and disadvantage**
  - include toggle that applies to all non damage rolls
- add **critical hits**
  - display if a roll was a "critical success" (rolled d20 was a 20 before modifiers)
  - display if a roll was a "critical fail" (rolled d20 was a 1 before modifiers)
- add **turn tracking**
  - add turn order list in between encounter list and side panel
  - add next turn button at bottom
  - make initiative rolls update turn order
- redesign **hp tracking**
  - turn change hp button into a 'damage' and a 'heal' button
    - damage button includes damage type and auto calculates with resistances
  - have damage type options that take into account resistances
  - add a bloodied display to hp (shows when at or under 50%)
  - add temp hp system
- redesign **save and load**
  - make default library json file
    - come with all default monsters in the 5th edition monsters manual (2/300)
  - add toolbar to top of window for the following:
    - add default library contents to the current library
    - save... -> choice of library, encounter, both, or cancel
    - load... -> choice of library, encounter, both, or cancel
  - update json tests and test files