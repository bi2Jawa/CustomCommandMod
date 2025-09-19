package com.github.bi2jawa.customcommands.Commands.UtilityCommands;

import com.github.bi2jawa.customcommands.Commands.CommandBases.CustomCommandBase;
import com.github.bi2jawa.customcommands.Config;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.client.ClientCommandHandler;

import java.util.Objects;

public class RemoveCommand extends CustomCommandBase {
    @Override
    public String getCommandName() {
        return "delete";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "Deletes user made commands made using /create";
    }

    @Override
    public void runCommand(String[] args, EntityPlayerSP player, World world, ICommandSender sender) throws CommandException {
        if (args.length == 0){
            player.addChatMessage(new ChatComponentText("please add a command to remove"));
            return;
        }
        if (!Config.config.getCategory("customcommand").containsKey(args[0])){
            player.addChatMessage(new ChatComponentText("this is not a user made command"));
            return;
        }
        Config.config.getCategory("customcommand").remove(args[0]);
        if (!Objects.equals(args[0], "customcommand"))
            Config.config.getCategory(args[0]).clear();
        Config.config.save();
        ClientCommandHandler.instance.getCommands().remove(args[0]);
    }
}
