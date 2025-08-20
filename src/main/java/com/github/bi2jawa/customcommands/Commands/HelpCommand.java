package com.github.bi2jawa.customcommands.Commands;

import com.github.bi2jawa.customcommands.CustomCommandMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HelpCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "ccm";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "Returns all the usages of commands in the Custom Command Mod";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 0) {
            if (args[0].equals("help")) {
                help(sender);
                return;
            }
            for (CommandBase command: CustomCommandMod.commands) {
                if (command.getCommandName().equals(args[0])) {
                    processNewCommand(sender, args, command);
                    return;
                }
                if (!command.getCommandAliases().isEmpty()) {
                    for (String alias: command.getCommandAliases()) {
                        if (alias.equals(args[1])) {
                            processNewCommand(sender, args, command);
                            return;
                        }
                    }
                }
            }
        }
        help(sender);
    }

    public void help(ICommandSender sender) {
        for (CommandBase command : CustomCommandMod.commands) {
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayerSP player = mc.thePlayer;
            String message = ("§2ccm " + command.getCommandName() + ": §f" + command.getCommandUsage(sender));
            player.addChatMessage(new ChatComponentText(message));
        }
    }

    public void processNewCommand(ICommandSender sender, String[] args, CommandBase command) throws CommandException {
        String[] newArgs = Arrays.copyOfRange(args, 1, args.length);
        command.processCommand(sender, newArgs);
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    public List<String> getCommandAliases() {
        return Collections.singletonList("ccm");
    }
}
