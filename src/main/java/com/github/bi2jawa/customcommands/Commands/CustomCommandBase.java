package com.github.bi2jawa.customcommands.Commands;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public abstract class CustomCommandBase extends CommandBase {
    public boolean toggle = true;

    public boolean toggleCheck(String[] args) {
        if (args.length > 0) {
            if (args[0].equals("toggle")) {
                toggle = !toggle;
            }
        }
        return toggle;
    }

    public boolean toggleCheck() {
        return toggle;
    }

    public String name(String name) {
        if (toggle) {
            return name;
        }
        return " " + name;
    }

    public boolean stopCheck(String[] args, EntityPlayerSP player) {
        if (toggle != toggleCheck(args)) {
            player.addChatMessage(new ChatComponentText("§aCommand /" + getCommandName() + " has been toggled"));
            return true;
        }
        if (!toggle) {
            player.sendChatMessage("/ " + getCommandName());
            return true;
        }
        return false;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;
        World world = mc.theWorld;
        if (stopCheck(args, player)) {
            return;
        }
        runCommand(args, player, world);
    }

    public abstract void runCommand(String[] args, EntityPlayerSP player, World world) throws CommandException;
}
