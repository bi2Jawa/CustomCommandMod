package com.github.bi2jawa.customcommands.Commands.CommandAddons;

import com.github.bi2jawa.customcommands.Commands.CustomCommandBase;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.world.World;
import net.minecraftforge.client.ClientCommandHandler;

import java.util.List;

public class CreateCommand {
    public static void create(String name, String output) {
        CustomCommandBase command = (new CustomCommandBase() {
            @Override
            public void runCommand(String[] args, EntityPlayerSP player, World world, ICommandSender sender) throws CommandException {
                if (ClientCommandHandler.instance.executeCommand(sender, output) == 1)
                    return;
                player.sendChatMessage(output);
            }

            @Override
            public String getCommandName() {
                return name;
            }
        });

        ClientCommandHandler.instance.registerCommand(command);
    }
}
