package com.github.bi2jawa.customcommands;

import com.github.bi2jawa.customcommands.Commands.*;
import com.github.bi2jawa.customcommands.Commands.CommandAddons.CreateCustomCommand;
import com.github.bi2jawa.customcommands.Commands.CommandBases.CustomCommandBase;
import com.github.bi2jawa.customcommands.Commands.TpCommands.*;
import com.github.bi2jawa.customcommands.Commands.UtilityCommands.*;
import com.github.bi2jawa.customcommands.events.ChatBlocker;
import com.github.bi2jawa.customcommands.events.MacroTest;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.util.ArrayList;
import java.util.Objects;

@Mod(modid = "Custom Commands Mod", version = "0.2.0", useMetadata = true)
public class CustomCommandMod {
    public static ArrayList<TpCommandBase> tpCommands = new ArrayList<>();
    public static ArrayList<CustomCommandBase> commands = new ArrayList<>();
    @EventHandler
    public void init(FMLInitializationEvent event) {
        registerCommand(new TopCommand());
        registerCommand(new UpCommand());
        registerCommand(new BottomCommand());
        registerCommand(new DownCommand());
        registerCommand(new ThroughCommand());
        registerCommand(new ThruCommand());
        registerCommand(new CCMCommand());
        registerCommand(new TestCommand());
        registerCommand(new FindCommands());
        registerCommand(new RegisterCommand());
        registerCommand(new RemoveCommand());
        registerCommand(new TestConfig());

        MinecraftForge.EVENT_BUS.register(new ChatBlocker());
        MinecraftForge.EVENT_BUS.register(new MacroTest());
        MinecraftForge.EVENT_BUS.register(this);

        CreateCustomCommand.read(false);
    }

    public void registerCommand(CustomCommandBase command) {
        if (command.toggle)
            ClientCommandHandler.instance.registerCommand(command);
        for (int i = 0; i < commands.size(); i++) {
            CustomCommandBase commandBase = commands.get(i);
            if (Objects.equals(commandBase.getCommandName(), command.getCommandName())) {
                commands.set(i, commandBase);
                return;
            }
        }
        commands.add(command);
        if (command instanceof TpCommandBase) {
            tpCommands.add((TpCommandBase) command);
        }
    }
}



