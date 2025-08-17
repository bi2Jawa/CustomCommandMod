package com.github.bi2jawa.customcommands;

import com.github.bi2jawa.customcommands.Commands.*;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "CustomCommandMod", version = "0.0.1", useMetadata = true)
public class CutsomCommandMod {
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new TopCommand());
        ClientCommandHandler.instance.registerCommand(new UpCommand());
        ClientCommandHandler.instance.registerCommand(new BottomCommand());
        ClientCommandHandler.instance.registerCommand(new DownCommand());
        ClientCommandHandler.instance.registerCommand(new ThroughCommand());
        MinecraftForge.EVENT_BUS.register(new ChatBlocker());
        MinecraftForge.EVENT_BUS.register(this);
    }
}



