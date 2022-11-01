# AutoBlocks
## for Dungeons and Dragons 5th Edition by Wizards of the Coast.
<p>
This is a tool for dungeon and dragons (D&D) dungeon masters (DMs) to automate mechanics for NPCs. This does not 
simulate any form of map, it's intended to be used with an external one.
</p>

- with this application you can do the following for NPCs:
  - **view and create statblocks**
  - **create characters from stablocks**
  - **autoroll for characters (initiative, actions, etc)**
  - **edit character hitpoints**
  - **group characters for multi rolls and multi hp edits**
- this application holds data such as *default monster statblocks*, but also allows the user to *enter custom entries*.
<p>
I made this application because I'm a D&D DM and I think there is no other application like this one. While there are 
some other applications for automating rolls, most try to do automation and map simulation at the same time. I think 
it's better to keep them separate because some people like theatre of the mind (no maps), and some people prefer in 
person. This app allows the DM to only exclude repetitive simple rolls and hp management so they can focus on the rest
of the game. So in short, I made this for personal use, but also because I think it solves a problem in a unique way.
</p>

### User Stories
<p>
As a user, I want to be able to:
</p>

- simulate rolling any number of dice, with any number of sides, and with any modifier
- view a list of statblocks by their names, (aka a 'library') and do the following:
    - select statblock in a library and do the following:
        - display all statblock information
        - add any number of statblock to encounter as new characters
    - add new, custom statblock to a library.
- view a list of characters (aka an 'encounter') by their names, groups, and hp; and do the following:
  - select character in encounter and do the following:
      - display all character stats and actions
      - enter roll menu for character and do the following:
          - roll character action
          - roll character ability check
          - roll character initiative
      - edit character hitpoints
      - change character group
      - delete character from encounter
  - select group in encounter and do the following:
    - display characters in group by all stats
    - enter roll menu for group and do the following: 
      - roll group action
      - roll group ability check
      - roll group initiative
    - edit group hp
    - remove group from characters
    - delete group and its characters from encounter
- chose to save the current library and/or encounter to file (from main menu and when quitting the application), and 
- chose to load the library and/or encounter from file (from main menu and when launching the application)

### Currently added default statblocks:
- Orc
- Ancient Black Dragon

### Current bugs/fixes to be done:
- damage rolls not printing
- no rolls for skills or saving throws
- delete statblocks from a library
- confirm to save on exit
- confirm to load on launch
- group actions print "Actions:" along with actions for each character, replace with no duplicates and one heading

### Future additions:
- save system to allow for multiple save/load files for any amount of encounters and libraries ☐
- edit statblocks in a library ☐
- turn order and automation of turns ☐
- inventory/loot generation ☐
- come with all default monsters in the 5th edition monsters manual (2/300) ☐