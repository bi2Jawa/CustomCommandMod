package com.github.bi2jawa.customcommands.Commands.TpCommands;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;


import java.util.Arrays;
import java.util.List;


public class ThroughCommand extends TpCommandBase {
    @Override
    public String getCommandName() {
        return "through";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "Teleports player through the wall they are looking at";
    }


    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;
        World world = mc.theWorld;
        int distance = 5;
        int worldSize = 255;
        boolean sent = false;
        double posX = player.posX;
        double posY = player.posY;
        double posZ = player.posZ;
        double lookX = player.getLookVec().xCoord;
        double lookZ = player.getLookVec().zCoord;
        boolean axisX = Math.abs(lookX) > Math.abs(lookZ);
        for (int i = 0; i < distance; i++) {
            double[] pos = incrementAxis(posX, posZ, axisX, lookX, lookZ);
            posX = pos[0];
            posZ = pos[1];
            if (isSolid(posX, posY, posZ, world) || isSolid(posX, posY + 1, posZ, world)) {
                break;
            }
            if (i == distance - 1) {
                player.addChatMessage(new ChatComponentText("§cYou are not looking at a wall"));
                return;
            }
        }
        for (int i = 0; i < worldSize; i++) {
            double[] pos = incrementAxis(posX, posZ, axisX, lookX, lookZ);
            posX = pos[0];
            posZ = pos[1];
            if (!isSolid(posX, posY, posZ, world) && !isSolid(posX, posY + 1, posZ, world)) {
                if (axisX) {
                    posX = Math.floor(posX);
                    posX = posX + 0.5;
                } else {
                    posZ = Math.floor(posZ);
                    posZ = posZ + 0.5;
                }
                tp(posX, posY, posZ, player);
                player.addChatMessage(new ChatComponentText("§aTeleported through wall"));
                sent = true;
                break;
            }
        }
        if (!sent) {
            player.addChatMessage(new ChatComponentText("§cCould not reach the end in 255 blocks"));
        }
        isSent = sent;
    }


    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }


    @Override
    public List<String> getCommandAliases() {
        return Arrays.asList("thru");
    }

    public double[] incrementAxis(double posX, double posZ, boolean axisX, double lookX, double lookZ) {
        if (axisX) {
            if (lookX > 0) {
                posX++;
            } else {
                posX--;
            }
        } else {
            if (lookZ > 0) {
                posZ++;
            } else {
                posZ--;
            }
        }
        double[] pos = new double[2];
        pos[0] = posX;
        pos[1] = posZ;
        return pos;
    }
}



