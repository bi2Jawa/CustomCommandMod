package com.github.bi2jawa.customcommands.Commands;

import com.github.bi2jawa.customcommands.Config;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.client.ClientCommandHandler;

public class RemoveCommand extends CustomCommandBase{
    @Override
    public String getCommandName() {
        return "remove";
    }

    @Override
    public void runCommand(String[] args, EntityPlayerSP player, World world, ICommandSender sender) throws CommandException {
        if (args[0].isEmpty()){
            player.addChatMessage(new ChatComponentText("please add a command to remove"));
            return;
        }
        Config.config.getCategory("customcommand").remove(args[0]);
        Config.config.save();
        ClientCommandHandler.instance.getCommands().remove(args[0]);
    }
}
