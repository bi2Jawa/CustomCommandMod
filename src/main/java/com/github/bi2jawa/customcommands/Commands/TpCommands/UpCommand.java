package com.github.bi2jawa.customcommands.Commands.TpCommands;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class UpCommand extends TpCommandBase {
    @Override
    public String getCommandName() {
        return "up";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "Teleports player to the next block directly above them";
    }

    @Override
    public void runCommand(String[] args, EntityPlayerSP player, World world, ICommandSender sender) throws CommandException {
        boolean sent = false;
        int worldSize = 255;
        double posX = player.posX;
        int posY = (int)Math.floor(player.posY);
        double posZ = player.posZ;

        if (args.length != 0) {
            if (isNumber(args[0])) {
                int height = parseInt(args[0]);
                isSent = true;
                tp(posX, posY + height, posZ, player);
                return;
            }
        }

        for (int i = posY; i < worldSize; i++) {
            if (validBlock(posX, i, posZ, world)) {
                    tp(posX, i+1, posZ, player);
                    isSent = sent = true;
                    break;
                }
        }
        if (!sent)
        {
            player.addChatMessage(new ChatComponentText("§cYou are already at the highest block"));
        }
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
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§aTeleported up"));
    }
}
