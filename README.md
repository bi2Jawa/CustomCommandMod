# Custom Command Mod
## Teleport Commands:

-/top, teleports the player to the highest block above them

-/bottom, teleports player onto the lowest standable block below them

-/down, teleports the player onto the next standable block below them or down a specified number of blocks

-/up teleports the player onto the next standable block above them or up a specified number of blocks

-/through (/thru) teleports the player through the current wall they are facing

## Utility Commands:

-/find, shows the player all the custom commands in a house. Run /find true in a house without any custom commands to set it up

-/create, creates a custom command to run whatever command is specified: usage /create vis /visibility 0. Then you can run /vis to output the command /visibility 0

-/delete, deletes the custom command specified by the user: /delete vis, deletes the command /vis which was created using /create

-/(command name) toggle, toggles whether the /ccm prefix is required to use the input command: /toggle up, now when running /up nothing happens but you can still use the command with /ccm up

-/exec (command name), allows the player to use a server command even if they have a client command that would usually stop them from using it: /exec top, makes the player say "/top" without the client trying to run the /top command

-/config, shows all the custom commands currently created by the player

-/ccm, shows all the commands available in the mod, not including player made commands
