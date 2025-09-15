package com.github.bi2jawa.customcommands.Commands.TpCommands;

import com.github.bi2jawa.customcommands.Commands.CustomCommandBase;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class TpCommandBase extends CustomCommandBase {
    public boolean isSent = false;
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

    boolean isSolid(double x, double y, double z, World world) {
        double difference = Math.abs(x - Math.floor(x));
        if (difference < 0.7 && difference > 0.3) { //player in the middle of the block
            return isSolidZ(x, y, z, world);
        }
        if (difference < 0.3) {
            return isSolidZ(x, y, z, world) || isSolidZ(x - 1, y, z, world);
        }
        return isSolidZ(x, y, z, world) || isSolidZ(x + 1, y, z, world);
    }

    boolean isSolidY(double x, double y, double z, World world) {
        double difference = Math.abs(y - Math.floor(y));
        if (difference > 0.2) { //player in the middle of the block
            return isSolidBlock(x, y, z, world) || isSolidBlock(x, y + 1, z, world);
        }
        return isSolidBlock(x, y, z, world);
    }


    boolean isSolidZ(double x, double y, double z, World world) {
        double difference = Math.abs(z - Math.floor(z));
        if (difference < 0.7 && difference > 0.3) { //player in the middle of the block
            return isSolidY(x, y, z, world);
        }
        if (difference < 0.3) {
            return isSolidY(x, y, z, world) || isSolidY(x, y, z - 1, world);
        }
        return isSolidY(x, y, z, world) || isSolidY(x, y, z + 1, world);
    }

    public boolean validBlock(double x, double y, double z, World world) {
        if (isSolid(x, y, z, world)) {
            return (!isSolid(x, y + 1, z, world) && !isSolid(x, y + 2, z, world));

        }
        return false;
    }

    public abstract  void tpMessage();
}
