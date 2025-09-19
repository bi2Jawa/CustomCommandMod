package com.github.bi2jawa.customcommands.Commands.TpCommands;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class DownCommand extends TpCommandBase {
    @Override
    public String getCommandName() {
        return "down";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "Teleports player to the next block directly below";
    }

    @Override
    public void runCommand(String[] args, EntityPlayerSP player, World world, ICommandSender sender) throws CommandException {
        boolean sent = false;
        double posX = player.posX;
        int posY = (int) Math.floor(player.posY);
        double posZ = player.posZ;

        if (args.length != 0) {
            if (isNumber(args[0])) {
                int height = parseInt(args[0]);
                tp(posX, posY-height, posZ, player);
                isSent = true;
                return;
            }
        }
        for (int i = posY - 2; i >= 0; i--) {
            if (validBlock(posX, i, posZ, world)) {
                tp(posX, i+1, posZ, player);
                sent = true;
                break;
            }
        }
        if (!sent) {
            player.addChatMessage(new ChatComponentText("§cYou are already at the lowest block"));
        }
        isSent = sent;
    }

    public boolean isNumber(String str) {
        try {
            parseInt(str);
            return true;
        }
        catch (NumberInvalidException e) {
            return false;
        }
    }
    @Override
    public void tpMessage() {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§aTeleported down"));
    }
}