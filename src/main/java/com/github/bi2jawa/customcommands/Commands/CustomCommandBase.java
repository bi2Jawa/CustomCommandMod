package com.github.bi2jawa.customcommands.Commands;

import com.github.bi2jawa.customcommands.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.io.File;

public abstract class CustomCommandBase extends CommandBase {
    File file = new File("CustomCommandConfig");
    public boolean toggle = Config.readBool(file, this, "toggle");

    public boolean toggleCheck(String[] args) {
        if (args.length > 0) {
            if (args[0].equals("toggle")) {
                toggle = !toggle;
                Config.writeConfig(this, toggle, "toggle");
            }
        }
        return toggle;
    }

    public boolean stopCheck(String[] args, EntityPlayerSP player) {
        if (toggle != toggleCheck(args)) {
            player.addChatMessage(new ChatComponentText("§aCommand /" + getCommandName() + " has been toggled"));
            return true;
        }
        if (!toggle) {
            player.sendChatMessage("/" + getCommandName());
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

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}
