package com.github.bi2jawa.customcommands.Commands;

import com.github.bi2jawa.customcommands.utility.PacketReceived;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.world.World;

public class FindCommands extends CustomCommandBase{
    @Override
    public String getCommandName() {
        return "find";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "returns the custom commands in the house, run /find true to reset the default commands filtered out";
    }

    @Override
    public void runCommand(String[] args, EntityPlayerSP player, World world) throws CommandException {
        PacketReceived.intercept = true;
        PacketReceived.reset = (args.length != 0);
        player.sendQueue.addToSendQueue(new C14PacketTabComplete("/"));
    }
}
