package com.github.bi2jawa.customcommands.Commands.TpCommands;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class BottomCommand extends TpCommandBase {
    @Override
    public String getCommandName() {
        return "bottom";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "Teleports player to the lowest block directly below";
    }
/*
    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;
        World world = mc.theWorld;
        if (stopCheck(args, player)) {
            return;
        }
        runCommand(args, player, world, sender);
    }

 */

    @Override
    public void runCommand(String[] args, EntityPlayerSP player, World world, ICommandSender sender) throws CommandException {
        boolean sent = false;
        double posX = player.posX;
        int posY = (int)Math.floor(player.posY);
        double posZ = player.posZ;
        for (int i = 0; i < posY; i++) {
            if (validBlock(posX, i, posZ, world)) {
                tp(posX, i+1, posZ, player);
                sent = true;
                break;
            }
        }
        if (!sent)
        {
            player.addChatMessage(new ChatComponentText("§cYou are already at the lowest block"));
        }
        isSent = sent;
    }

    @Override
    public void tpMessage() {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§aTeleported to the bottom of the map"));
    }
}
