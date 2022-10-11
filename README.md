# AutoBlocks
## for Dungeons and Dragons 5th Edition by Wizards of the Coast.
<p>
This is a tool for dungeon and dragons (D&D) dungeon masters (DMs) to automate mechanics for NPCs and/or players. This 
does not simulate any form of map, it's intended to be used with an external one. Although this is intended for just 
the DM, players can use this application to automate their characters' mechanics (but D&D Beyond has more features for 
players).
</p>

- with this application you can do the following for NPCs and/or players:
  - **view statblocks**
  - **edit and store hitpoints**
  - **autoroll anything (initiative, spells, actions, etc)**
  - **grouping for multiple rolls at once**
- this application holds data such as *default monster statblocks*, but also allows the user to *enter custom entries 
- and clone/edit existing ones*.
<p>
I made this application because I'm a D&D DM and combat is by far the slowest and most tedious part of play. There are 
many online tools for simulating combat, but most try to do automation and map simulation at the same time. I think 
it's better to keep them separate because some people like theatre of the mind (no maps), and some people prefer in 
person. This allows those not playing online to automate mechanics at a large scale. So in short, I made this for 
personal use and any others who don't want online maps but want large scale automation. 
</p>

### User Stories
<p>
As a user, I want to be able to:
</p>

- simulate rolling a d20, d12, d10, d8, d6, or d4 with or without modifiers

- view a list of statblocks, aka the 'library', which have the following fields:
  - name
  - size
  - type
  - hitpoints
  - armour class
  - speed
  - ability scores
  - actions
- input custom statblocks to the library.

- view a list of characters, aka 'play', which are instantiations of statblocks that can be edited and deleted without 
affecting their entry in the library.
- add and remove characters to play from statblocks in the library.
- group characters in play.
- view and edit hitpoints for characters/groups in play.
- roll the following for characters/groups in play:
  - actions
  - skill checks
  - saving throws
  - initiative

### Future additions:
- skills
- equipment
- inventory
- copy and edit existing statblocks in the library. 