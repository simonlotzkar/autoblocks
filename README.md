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
I made this application because I'm a D&D fan, and I couldn't find a program that was made just for the DM to speed up combat.  
This app allows the DM to only exclude repetitive simple rolls and hp management, so they can focus on the rest of the game. 
So in short, I made this for personal use, but also because I think it solves a problem in a unique way.
</p>

### TODO (in order):
- remove group system
- edit custom roll window (make fields above button, parchment, etc)
- custom action window
- make encounter and library alphabetical
    - update npc name generation based on that
- update test suite
- change the display of actions (formulae and rolls)
    - add NPC name tags
    - change from strings to grid panels
- redesign statblock/npc displays 
  - refactor (use inheritance)
  - change text areas to panels with sub grids
- redesign statblock creation display
  - change flow layouts to grids
- add turn order functionality
  - add turn order list in between encounter list and side panel
  - add next turn button at bottom
  - make initiative rolls update turn order
- add skill roll button that takes into account proficiency
- add saving throw button that takes into account proficiency
- redesign buttons
  - add custom text to all
  - create custom image for backgrounds (and maybe redo icons)
- redesign save/load system
    - make default library file and add import functionality (just adds to existing library)
        - come with all default monsters in the 5th edition monsters manual (2/300)
    - allow for multiple save/load files
    - redesign json tests and files

### Current Bugs:
- damage types are stuck capitalized
- statblock creation doesn't reset when restarting creation
- editing npc doesn't update npc display