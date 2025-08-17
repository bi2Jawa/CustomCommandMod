package com.github.bi2jawa.customcommands;

import com.github.bi2jawa.customcommands.Commands.*;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
    public boolean isSent(){
        return TopCommand.isSent || BottomCommand.isSent || DownCommand.isSent || UpCommand.isSent || ThroughCommand.isSent;
    }

    public void setSentFalse(){
        TopCommand.isSent = BottomCommand.isSent = DownCommand.isSent = UpCommand.isSent = ThroughCommand.isSent= false;
    }
}
