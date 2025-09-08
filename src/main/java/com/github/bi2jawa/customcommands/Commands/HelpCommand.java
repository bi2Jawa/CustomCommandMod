package com.github.bi2jawa.customcommands.Commands;

import com.github.bi2jawa.customcommands.CustomCommandMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.Arrays;

public class HelpCommand extends CustomCommandBase {

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
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;
        World world = mc.theWorld;
        if (args.length > 0) {
            if (args[0].equals("help")) {
                help(sender);
                return;
            }
            for (CustomCommandBase command: CustomCommandMod.commands) {
                String commandName = command.getCommandName();
                commandName = commandName.replaceAll(" ","");
                if (commandName.equals(args[0])) {
                    String[] newArgs = Arrays.copyOfRange(args, 1, args.length);
                    if (command.toggle != command.toggleCheck(newArgs)) {
                        player.addChatMessage(new ChatComponentText("§aCommand /" + command.getCommandName() + " has been toggled"));
                        return;
                    }
                    command.runCommand(newArgs, player, world);
                    return;
                }
                if (!command.getCommandAliases().isEmpty()) {
                    String[] newArgs = Arrays.copyOfRange(args, 1, args.length);
                    if (command.toggle != command.toggleCheck(newArgs)) {
                        player.addChatMessage(new ChatComponentText("§aCommand /" + command.getCommandName() + " has been toggled"));
                        return;
                    }
                    for (String alias: command.getCommandAliases()) {
                        if (alias.equals(args[1])) {
                            command.runCommand(newArgs, player, world);
                            return;
                        }
                    }
                }
            }
        }
        help(sender);
    }

    public void runCommand(String[] args, EntityPlayerSP player, World world) throws CommandException {
    }

    public void help(ICommandSender sender) {

        for (CustomCommandBase command : CustomCommandMod.commands) {
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayerSP player = mc.thePlayer;
            String message;
            if (command.toggle) {
                message = "§2";
            }
            else {
                message = "§4";
            }
            message = (message + "/");
            if (!command.getCommandName().equals("ccm")) {
                message = (message + "ccm ");
            }
            message = (message +  command.getCommandName());
            message = message + ": §f" + command.getCommandUsage(sender);
            player.addChatMessage(new ChatComponentText(message));
        }
    }
}
