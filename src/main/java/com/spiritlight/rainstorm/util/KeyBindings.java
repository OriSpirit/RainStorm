package com.spiritlight.rainstorm.util;

import com.spiritlight.rainstorm.features.*;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;


public class KeyBindings
{
    public static KeyBinding[] keyBindings = new KeyBinding[6];
    private static boolean alreadyCreated;

    public static void register() {
        if (KeyBindings.alreadyCreated) {
            return;
        }
        KeyBindings.alreadyCreated = true;
        for(KeyBinding k : keyBindings)
            ClientRegistry.registerKeyBinding(k);
    }

    @SubscribeEvent
    public void onEvent(final InputEvent.KeyInputEvent e) {
        if(keyBindings[0].isPressed()) {
            Blink.toggle();
            return;
        }
        if(keyBindings[1].isPressed()) {
            BlockTP.toggle();
            return;
        }
        if(keyBindings[2].isPressed()) {
            NoFall.toggle();
            return;
        }
        if(keyBindings[3].isPressed()) {
            Fly.toggle();
            return;
        }
        if(keyBindings[4].isPressed()) {
            EntityVelocity.toggle();
            return;
        }
        if(keyBindings[5].isPressed()) {
            GhostBlock.toggle();
            return;
        }
    }

    static {
        keyBindings[0] = new KeyBinding("Blink", Keyboard.KEY_R, "RainStorm");
        keyBindings[1] = new KeyBinding("BlockTP", Keyboard.KEY_B, "RainStorm");
        keyBindings[2] = new KeyBinding("NoFall", Keyboard.KEY_K, "RainStorm");
        keyBindings[3] = new KeyBinding("Fly", Keyboard.KEY_G, "RainStorm");
        keyBindings[4] = new KeyBinding("EntityVelocity", Keyboard.KEY_N, "RainStorm");
        keyBindings[5] = new KeyBinding("GhostBlock", Keyboard.KEY_M, "RainStorm");
        KeyBindings.alreadyCreated = false;
    }
}
