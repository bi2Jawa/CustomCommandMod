package com.github.bi2jawa.customcommands.Commands;

import com.github.bi2jawa.customcommands.CustomCommandMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.Arrays;
import java.util.List;

public class HelpCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "CustomCommandMod help";
    }

    @Override
    public String getCommandUsage(ICommandSender sender){
        return "Returns all the usages of commands in the Custom Command Mod";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        for (CommandBase command: CustomCommandMod.commands) {
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayerSP player = mc.thePlayer;
            String message = (command.getCommandName() + ": " + command.getCommandUsage(sender));
            player.addChatMessage(new ChatComponentText(message));
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    public List<String> getCommandAliases() {
        return Arrays.asList("ccm");
    }
}
