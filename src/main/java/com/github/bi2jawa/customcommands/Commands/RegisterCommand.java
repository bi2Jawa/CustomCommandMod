package com.github.bi2jawa.customcommands.Commands;

import com.github.bi2jawa.customcommands.Commands.CommandAddons.CommandChecker;
import com.github.bi2jawa.customcommands.Config;
import com.github.bi2jawa.customcommands.CustomCommandMod;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.client.ClientCommandHandler;

import java.util.Arrays;

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
        Config.writeConfig("CustomCommand", command, output);
        CustomCommandBase customCommand = createCommand(command, output);
        ClientCommandHandler.instance.registerCommand(customCommand);
    }

    public CustomCommandBase createCommand(String name, String output) {
        return (new CustomCommandBase() {
            @Override
            public void runCommand(String[] args, EntityPlayerSP player, World world, ICommandSender sender) throws CommandException {
                if (CommandChecker.IsClientCommand(sender, output)) {
                    ClientCommandHandler.instance.executeCommand(sender, output);
                    return;
                }
                player.sendChatMessage(output);
            }

            @Override
            public String getCommandName() {
                return name;
            }
        });
    }
}
