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
        return "register";
    }

    @Override
    public void runCommand(String[] args, EntityPlayerSP player, World world, ICommandSender sender) throws CommandException {
        if (args.length < 2) {
            player.addChatMessage(new ChatComponentText("use the format: /register newCommand output (use /output if its meant to be a command)"));
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
