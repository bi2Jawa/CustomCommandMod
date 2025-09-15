package com.github.bi2jawa.customcommands.Commands;

import com.github.bi2jawa.customcommands.Config;
import com.github.bi2jawa.customcommands.CustomCommandMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.client.ClientCommandHandler;

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
        /*if (stopCheck(args, player)) {
            return;
        }
         */
        if (args.length == 0 || !args[0].equals("toggle")) {
            runCommand(args, player, world, sender);
            return;
        }
        player.addChatMessage(new ChatComponentText("§aCommand /" + this.getCommandName() + " has been toggled"));
        if (ClientCommandHandler.instance.getCommands().containsKey(this.getCommandName())) {
            //ClientCommandHandler.instance.getPossibleCommands(sender).remove(this);
            ClientCommandHandler.instance.getCommands().remove(this.getCommandName());
            return;
        }
        ClientCommandHandler.instance.registerCommand(this);
    }

    public abstract void runCommand(String[] args, EntityPlayerSP player, World world, ICommandSender sender) throws CommandException;

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "";
    }
}
