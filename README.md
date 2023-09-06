# AutoBlocks
## for Dungeons and Dragons 5th Edition by Wizards of the Coast.
<p>
This is a tool for dungeon and dragons (D&D) dungeon masters (DMs) to automate mechanics for non player characters (NPCs).
This does not simulate any form of map, it's intended to be used with an external one.
</p>

***With this application you can do the following:***
  - **simulate rolling any number of dice, with any number of sides, and with any modifier**
  - **view a list of statblocks by their names:**
    - *view statblock details*
    - *add custom statblocks*
    - *delete statblocks*
  - **view a list of NPCs by their names and hit points:**
    - *roll actions, initiatives, or ability checks*
    - *track and edit hit points*
<p>
I made this application because I'm a D&D fan, and I couldn't find a program that was made just for the DM to only 
automate NPC mechanics. This app allows the DM to only exclude repetitive simple rolls and hp management, so they can 
focus on the rest of the game. This is for personal use, but I think any DM would benefit from this app!
</p>

### TODO (in order):
- update test suite
- custom roll window
    - make fields above button, parchment, etc
- custom action window
- buttons
    - add custom text to all
    - create custom image for backgrounds (and maybe redo icons)
- encounter and library lists
    - convert to: "JTable - Sort Using Column Headers"
    - add "sort by..." combobox next to the main display titles
      - name
      - type
      - size
      - alignment
      - cr
      - hp
    - scale text size
- display of actions (formulae and rolls)
    - add NPC name tags
    - change from strings to grid panels (hit/dmg/etc)
- statblock/npc displays 
  - refactor (use inheritance)
  - change text areas to panels with sub grids
- statblock creation display
  - change flow layouts to grids

### Current Bugs:
- damage types are stuck capitalized
- statblock creation
  - doesn't reset when restarting creation
  - buttons act on library and not creation panel
- editing hp doesn't update npc display

### Future Additions:
- add **advantage / disadvantage system**
  - include option with all non damage rolls
- add **skill system**
  - roll button that takes into account proficiency
- add **saving throw system**
  - button that takes into account proficiency
- add **turn system**
    - add turn order list in between encounter list and side panel
    - add next turn button at bottom
    - make initiative rolls update turn order
- redesign **hp system**
    - turn change hp button into a 'damage' and a 'heal' button
    - have damage type options that take into account resistances
    - add a bloodied display to hp (shows when at or under 50%)
- add **conditions system**
    - track and view conditions for npcs
    - button that adds a condition to the selected npcs but takes immunities into account
      - checkbox for saving throw checks (takes the largest bonus)
- redesign **save/load system**
    - make default library json file
        - come with all default monsters in the 5th edition monsters manual (2/300)
    - add toolbar to top of window for the following:
        - add default library contents to the current library
        - save... -> choice of library, encounter, both, or cancel
        - load... -> choice of library, encounter, both, or cancel
    - update json tests and test files