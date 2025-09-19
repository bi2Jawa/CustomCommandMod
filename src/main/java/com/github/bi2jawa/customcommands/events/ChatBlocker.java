package com.github.bi2jawa.customcommands.events;

import com.github.bi2jawa.customcommands.Commands.TpCommands.*;
import com.github.bi2jawa.customcommands.CustomCommandMod;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

public class ChatBlocker {
    boolean success = false;
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event){
        boolean sent = isSent();
        success = false;
        String message = event.message.getFormattedText();
        if (!sent){
            return;
        }
        if (message.contains("§cYou do not have permission to use TP in this house.")){
            event.setCanceled(true);
            setSentFalse();
            return;
        }
        if (message.contains("§aTeleporting you to") || message.contains("Teleported")){
            event.setCanceled(true);
            success = true;
            setSentFalse();
            return;
        }

        ArrayList<TpCommandBase> commands = CustomCommandMod.tpCommands;
        for (TpCommandBase command: commands) {
            if (command.isSent && success) {
                command.tpMessage();
            }
            command.isSent = false;
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
            if (command.isSent && success) {
                command.tpMessage();
            }
            command.isSent = false;
        }
    }
}
