package com.github.bi2jawa.customcommands.Commands;

import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class TpCommandBase extends CommandBase {
    boolean isSolidBlock(double posX, double posY, double posZ, World world) {
        int blockX = (int) Math.floor(posX);
        int blockY = (int) Math.floor(posY);
        int blockZ = (int) Math.floor(posZ);
        BlockPos blockPos = new BlockPos(blockX, blockY, blockZ);
        Block block = world.getBlockState(blockPos).getBlock();
        return block.getMaterial().isSolid();
    }

    void tp(double x, double y, double z, EntityPlayerSP player) {
        String command = "/tp " + x + " " + y + " " + z;
        player.sendChatMessage(command);
    }

    boolean isSolidX(double x, double y, double z, World world) {
        double difference = Math.abs(x - Math.floor(x));
        int xPos = (int) Math.floor(x);
        int yPos = (int) Math.floor(y);
        int zPos = (int) Math.floor(z);
        if (difference < 0.7 && difference > 0.3) { //player in the middle of the block
            return isSolidBlock(xPos, yPos, zPos, world);
        }
        if (difference < 0.3) {
            return isSolidBlock(xPos, yPos, zPos, world) || isSolidBlock(xPos - 1, yPos, zPos, world);
        }
        return isSolidBlock(xPos, yPos, zPos, world) || isSolidBlock(xPos + 1, yPos, zPos, world);
    }

    boolean isSolidZ(double x, double y, double z, World world) {
        double difference = Math.abs(z - Math.floor(z));
        int xPos = (int) Math.floor(x);
        int yPos = (int) Math.floor(y);
        int zPos = (int) Math.floor(z);
        if (difference < 0.7 && difference > 0.3) { //player in the middle of the block
            return isSolidBlock(xPos, yPos, zPos, world);
        }
        if (difference < 0.3) {
            return isSolidBlock(xPos, yPos, zPos, world) || isSolidBlock(xPos, yPos, zPos - 1, world);
        }
        return isSolidBlock(xPos, yPos, zPos, world) || isSolidBlock(xPos, yPos, zPos + 1, world);
    }

    public boolean isSolid(double x, double y, double z, World world) {
        double differenceX = Math.abs(x - Math.floor(x));
        double differenceZ = Math.abs(z - Math.floor(z));
        int xPos = (int) Math.floor(x);
        int yPos = (int) Math.floor(y);
        int zPos = (int) Math.floor(z);
        if (differenceZ < 0.7 && differenceZ > 0.3) { //player in the middle of the block
            return isSolidX(x, y, z, world);
        }
        if (differenceZ < 0.3) {
            return isSolidX(xPos, yPos, zPos, world) || isSolidX(xPos, yPos, zPos - 1, world);
        }
        if (differenceX < 0.7 && differenceX > 0.3) { //player in the middle of the block
            return isSolidBlock(xPos, yPos, zPos, world);
        }
        if (differenceX < 0.3) {
            return isSolidBlock(xPos, yPos, zPos, world) || isSolidBlock(xPos - 1, yPos, zPos, world);
        }
        return isSolidBlock(xPos, yPos, zPos, world) || isSolidBlock(xPos + 1, yPos, zPos, world);
    }

    public boolean validBlock(double x, double y, double z, World world) {
        if (isSolid(x, y, z, world)) {
            return (!isSolid(x, y + 1, z, world) && !isSolid(x, y + 2, z, world));

        }
        return false;
    }
}
