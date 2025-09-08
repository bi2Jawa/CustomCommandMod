package com.github.bi2jawa.customcommands.utility;

import com.github.bi2jawa.customcommands.Commands.TpCommands.*;
import com.github.bi2jawa.customcommands.CustomCommandMod;
import net.minecraft.network.play.server.S3APacketTabComplete;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

public class ChatBlocker {
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event){
        boolean sent = isSent();
        String message = event.message.getFormattedText();
        if ((message.contains("Teleporting you to") || message.contains("Teleported")) && sent){
            event.setCanceled(true);
            setSentFalse();
        }
    }
    public boolean isSent() {
        ArrayList<TpCommandBase> commands = CustomCommandMod.tpCommands;
        for (TpCommandBase command : commands) {
            if (command.isSent) {
                return true;
            }
        }
        return false;
    }

    public void setSentFalse(){
        ArrayList<TpCommandBase> commands = CustomCommandMod.tpCommands;
        for (TpCommandBase command: commands) {
            command.isSent = false;
        }
    }
}
