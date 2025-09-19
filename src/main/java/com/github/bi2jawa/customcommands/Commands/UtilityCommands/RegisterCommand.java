package com.github.bi2jawa.customcommands.Commands.UtilityCommands;

import com.github.bi2jawa.customcommands.Commands.CommandAddons.CreateCustomCommand;
import com.github.bi2jawa.customcommands.Commands.CommandBases.CustomCommandBase;
import com.github.bi2jawa.customcommands.Config;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.Arrays;

public class RegisterCommand extends CustomCommandBase {
    @Override
    public String getCommandName() {
        return "create";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "creates a new command that runs whatever command entered: e.g. /create vis /visibility runs /visibility when using /vis";
    }

    @Override
    public void runCommand(String[] args, EntityPlayerSP player, World world, ICommandSender sender) throws CommandException {
        if (args.length < 2) {
            player.addChatMessage(new ChatComponentText("use the format: /create newCommand output (use /output if its meant to be a command)"));
            return;
        }
        String command = args[0];
        String output = "";
        for (String arg: Arrays.copyOfRange(args, 1, args.length)) {
            output += " " + arg;
        }
        Config.config.get("customcommand", command, "").setValue(output);
        Config.config.save();
        CreateCustomCommand.create(command, output);
    }
}
