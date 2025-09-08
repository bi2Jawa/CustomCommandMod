package com.github.bi2jawa.customcommands.Commands;

import com.github.bi2jawa.customcommands.Commands.CommandAddons.CommandChecker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.ICommandSender;
import net.minecraft.world.World;
import net.minecraftforge.client.ClientCommandHandler;

public class TestCommand extends CustomCommandBase {
    @Override
    public String getCommandName() {
        return "test";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length < 1) {
            return;
        }
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        String command = "";
        for (String arg: args) {
            command = command + " " + arg;
        }
        if (CommandChecker.checkClientCommands(sender, args[0])) {
            ClientCommandHandler.instance.executeCommand(sender, command);
            return;
        }
        //command = "/" + command;
        player.sendChatMessage(command);
    }

    @Override
    public void runCommand(String[] args, EntityPlayerSP player, World world) {
    }
}
