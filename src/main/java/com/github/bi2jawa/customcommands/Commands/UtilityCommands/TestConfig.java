package com.github.bi2jawa.customcommands.Commands.UtilityCommands;

import com.github.bi2jawa.customcommands.Commands.CommandAddons.CreateCustomCommand;
import com.github.bi2jawa.customcommands.Commands.CommandBases.CustomCommandBase;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.world.World;

public class TestConfig extends CustomCommandBase {
    @Override
    public String getCommandName() {
        return "config";
    }

    @Override
    public void runCommand(String[] args, EntityPlayerSP player, World world, ICommandSender sender) throws CommandException {
        CreateCustomCommand.read(true);
    }
}
