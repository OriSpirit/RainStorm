package com.spiritlight.rainstorm.util;

import com.spiritlight.rainstorm.constants.Game;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class Messenger {
    public static void send(String s) {
        try {
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString(Game.PREFIX + s));
        } catch (NullPointerException ignored) {}
    }

    public static void send(TextComponentString s) {
        try {
            Minecraft.getMinecraft().player.sendMessage(s);
        } catch (NullPointerException ignored) {}
    }

    public static void send (ITextComponent s) {
        try {
            Minecraft.getMinecraft().player.sendMessage(s);
        } catch (NullPointerException ignored) {}
    }
}
