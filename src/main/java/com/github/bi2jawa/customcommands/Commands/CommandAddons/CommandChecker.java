package com.github.bi2jawa.customcommands.Commands.CommandAddons;

import com.github.bi2jawa.customcommands.utility.PacketReceived;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraftforge.client.ClientCommandHandler;

import java.util.List;

public class CommandChecker {

    public static boolean IsClientCommand(ICommandSender sender, String input) {
        List<ICommand> commands = ClientCommandHandler.instance.getPossibleCommands(sender);
        for (ICommand command: commands) {
            if (input.startsWith("/" + command.getCommandName())) {
                return true;
            }
            for (String alias: command.getCommandAliases()) {
                if (input.startsWith("/" + alias)) {
                    return true;
                }
            }
        }
        return false;
    }
}
