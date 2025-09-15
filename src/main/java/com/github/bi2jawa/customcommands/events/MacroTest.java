package com.github.bi2jawa.customcommands.events;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import org.lwjgl.input.Keyboard;

public class MacroTest {
    @SubscribeEvent
    public void onClick(KeyInputEvent event) {
        if (Keyboard.isKeyDown(Keyboard.KEY_M)) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("hi"));
        }
    }
}
