package com.github.bi2jawa.customcommands.utility;

import com.github.bi2jawa.customcommands.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S3APacketTabComplete;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.io.IOException;
import java.util.ArrayList;

@Cancelable
public class PacketReceived extends Event{
    public static Boolean intercept = false;
    public static Boolean reset = false;
    public final Packet<?> packet;
    public PacketReceived(Packet<?> packet) throws IOException{
        this.packet = packet;
        if (packet instanceof S3APacketTabComplete) {
            TabCompleteHandler(packet);
        }
    }
    public void TabCompleteHandler(Packet<?> packet) throws IOException{
        if (!intercept) {return;}
        S3APacketTabComplete tabComplete = (S3APacketTabComplete) packet;
        String[] commands = tabComplete.func_149630_c(); //func_149630_c = getSuggestions()
        if (commands.length == 0) {
            return;
        }
        if (reset) {ResetCommands(commands);}
        else {CheckCommands(commands);}
        intercept = false;
    }

    public void ResetCommands(String[] commands) {
        Config.clearConfig("server command");
        for (String command: commands)
            Config.writeConfig("server command", command, true);
    }

    public void CheckCommands(String[] commands) {
        ArrayList<String> newCommands = new ArrayList<String>();
        for (String command: commands) {
            if (!Config.readConfig("server command", command)) {
                newCommands.add(command);
            }
        }
        String output = "Server commands: ";
        for (String command: newCommands) {
            output = output + command + " ";
        }
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(output));
    }
}
