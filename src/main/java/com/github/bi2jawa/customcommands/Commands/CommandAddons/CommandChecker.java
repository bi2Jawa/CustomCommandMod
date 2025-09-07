package com.github.bi2jawa.customcommands.Commands.CommandAddons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.network.play.server.S3APacketTabComplete;
import net.minecraftforge.client.ClientCommandHandler;

import java.util.List;

public class CommandChecker {

    public static boolean checkClientCommands(ICommandSender sender, String input) {
        Minecraft mc = Minecraft.getMinecraft();
        List<ICommand> commands = ClientCommandHandler.instance.getPossibleCommands(sender);
//        EntityPlayerSP player = mc.thePlayer;
//        if (input.equals("test")) {
//            for (ICommand command: commands) {
//                player.addChatMessage(new ChatComponentText(command.getCommandName()));
//                for (String alias: command.getCommandAliases()) {
//                    player.addChatMessage(new ChatComponentText(alias));
//                }
//            }
//            return;
//        }
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

    public static void checkServerCommands(ICommandSender sender) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;
        String[] message = new String[1];
        message[0] = "/";
        S3APacketTabComplete packet = (new S3APacketTabComplete(message));
        player.sendQueue.handleTabComplete(packet);
    }
}
