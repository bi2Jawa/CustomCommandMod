package com.github.bi2jawa.customcommands.Commands.TpCommands;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class TopCommand extends TpCommandBase {
    @Override
    public String getCommandName() {
        return "top";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "Teleports player to the highest block directly up";
    }

    @Override
    public void runCommand(String[] args, EntityPlayerSP player, World world, ICommandSender sender) throws CommandException {
        boolean sent = false;
        int worldSize = 255;
        double posX = player.posX;
        int posY = (int)Math.floor(player.posY);
        double posZ = player.posZ;

        for (int i = worldSize; i > posY; i--) {
            if (validBlock(posX, i, posZ, world)) {
                tp(posX, i+1, posZ, player);
                sent = true;
                break;
            }
        }
        if (!sent)
        {
            player.addChatMessage(new ChatComponentText("§cYou are already at the highest block"));
        }
        isSent = sent;
    }

    @Override
    public void tpMessage() {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§aTeleported to the top of the map"));
    }
}
