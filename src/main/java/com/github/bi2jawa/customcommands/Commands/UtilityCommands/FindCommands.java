package com.github.bi2jawa.customcommands.Commands.UtilityCommands;

import com.github.bi2jawa.customcommands.Commands.CommandBases.CustomCommandBase;
import com.github.bi2jawa.customcommands.events.PacketReceived;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.world.World;

public class FindCommands extends CustomCommandBase {
    @Override
    public String getCommandName() {
        return "find";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "returns the custom commands in the house, run /find true to reset the default commands filtered out to the commands in the current house you are in";
    }

    @Override
    public void runCommand(String[] args, EntityPlayerSP player, World world, ICommandSender sender) throws CommandException {
        PacketReceived.intercept = true;
        PacketReceived.reset = (args.length != 0);
        player.sendQueue.addToSendQueue(new C14PacketTabComplete("/"));
    }
}
