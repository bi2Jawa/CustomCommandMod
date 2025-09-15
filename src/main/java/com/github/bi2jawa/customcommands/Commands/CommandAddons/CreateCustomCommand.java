package com.github.bi2jawa.customcommands.Commands.CommandAddons;

import com.github.bi2jawa.customcommands.Commands.CommandBases.CustomCommandBase;
import com.github.bi2jawa.customcommands.Config;
import com.github.bi2jawa.customcommands.CustomCommandMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.config.ConfigCategory;

import java.util.Set;

public class CreateCustomCommand {
    public static CustomCommandBase create(String name, String output) {
        CustomCommandBase command = (new CustomCommandBase() {
            @Override
            public void runCommand(String[] args, EntityPlayerSP player, World world, ICommandSender sender) throws CommandException {
                String newOutput = output;
                for (String arg: args){
                    newOutput += arg + " ";
                }
                if (ClientCommandHandler.instance.executeCommand(sender, newOutput) == 1)
                    return;
                player.sendChatMessage(output);
            }

            @Override
            public String getCommandName() {
                return name;
            }
        });

        (new CustomCommandMod()).registerCommand(command);
        return command;
        //ClientCommandHandler.instance.registerCommand(command);
    }
    public static void read(boolean called){
        ConfigCategory commands = Config.config.getCategory("customcommand");
        Set<String> keys = commands.keySet();
        String[] keyArray = keys.toArray(new String[0]);
        boolean test = false;
        if (called) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§aCommands: "));
        }
        for (String command: keyArray) {
            test = true;
            String output = Config.config.get("customcommand", command, "").getString();
            CustomCommandBase commandBase = CreateCustomCommand.create(command, output);
            if (called) {
                String message = "";
                if (commandBase.toggle)
                    message += "§a/";
                else
                    message += "§c/";
                message += command + "§f: " + output;
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
            }
        }
        if (!test && called) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("no commands"));
        }
    }
}
