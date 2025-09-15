package com.github.bi2jawa.customcommands.Commands;

import com.github.bi2jawa.customcommands.Commands.CommandAddons.CommandChecker;
import com.github.bi2jawa.customcommands.Commands.CommandAddons.CreateCommand;
import com.github.bi2jawa.customcommands.Config;
import com.github.bi2jawa.customcommands.CustomCommandMod;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.client.ClientCommandHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterCommand extends CustomCommandBase{
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
        CreateCommand.create(command, output);
    }
}
