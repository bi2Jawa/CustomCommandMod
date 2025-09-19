package com.github.bi2jawa.customcommands.Commands.UtilityCommands;

import com.github.bi2jawa.customcommands.Commands.CommandBases.CustomCommandBase;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.ICommandSender;
import net.minecraft.world.World;

public class TestCommand extends CustomCommandBase {
    @Override
    public String getCommandName() {
        return "exec";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "Allows the user to bypass any commands on their client to send a command to the server: e.g. /exec top runs the server command /top even if they have a client command /top";
    }

    /*
    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length < 1) {
            return;
        }
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        String command = "";
        for (String arg: args) {
            command = command + " " + arg;
        }
        if (CommandChecker.IsClientCommand(sender, args[0])) {
            ClientCommandHandler.instance.executeCommand(sender, command);
            return;
        }
        //command = "/" + command;
        player.sendChatMessage(command);
    }

     */

    @Override
    public void runCommand(String[] args, EntityPlayerSP player, World world, ICommandSender sender) {
        String message = "/";
        for (String arg: args){
            message += arg + " ";
        }
        player.sendChatMessage(message);
    }
}
