package com.github.bi2jawa.customcommands.Commands;

import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class TpCommandBase extends CommandBase {
    boolean isSolid(double posX, double posY, double posZ, World world) {
        int blockX = (int)Math.floor(posX);
        int blockY = (int)Math.floor(posY);
        int blockZ = (int)Math.floor(posZ);
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
        int xPos = (int)Math.floor(x);
        int yPos = (int)Math.floor(y);
        int zPos = (int)Math.floor(z);
        if (difference < 0.7 && difference > 0.3) { //player in the middle of the block
            return isSolid(xPos, yPos, zPos, world);
        }
        if (difference < 0.3) {
            return isSolid(xPos, yPos, zPos, world) || isSolid(xPos - 1, yPos, zPos, world);
        }
        return isSolid(xPos, yPos, zPos, world) || isSolid(xPos + 1, yPos, zPos, world);
    }



    boolean isSolidZ(double x, double y, double z, World world) {
        double difference = Math.abs(z - Math.floor(z));
        int xPos = (int)Math.floor(x);
        int yPos = (int)Math.floor(y);
        int zPos = (int)Math.floor(z);
        if (difference < 0.7 && difference > 0.3) { //player in the middle of the block
            return isSolid(xPos, yPos, zPos, world);
        }
        if (difference < 0.3) {
            return isSolid(xPos, yPos, zPos, world) || isSolid(xPos, yPos, zPos - 1, world);
        }
        return isSolid(xPos, yPos, zPos, world) || isSolid(xPos, yPos, zPos + 1, world);
    }

    public boolean validBlock(double x, double y, double z, World world) {
        if (isSolidX(x, y, z, world) ||  isSolidZ(x, y, z, world)) {
            return isAirGap(x, y, z, world);
        }
        return false;
    }
    public boolean isAirGap(double x, double y, double z, World world){
        if (!isSolidX(x, y + 1, z, world) && !isSolidZ(x, y + 1, z, world)) {
            return (!isSolidX(x, y + 2, z, world) && !isSolidZ(x, y + 2, z, world));
        }
        return false;
    }
}
