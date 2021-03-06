package com.spiritlight.rainstorm.util;

import com.spiritlight.rainstorm.events.FeatureManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;


public class KeyBindings
{
    public static final KeyBinding[] keyBindings = new KeyBinding[7];
    private static boolean alreadyCreated;

    public static void register() {
        if (KeyBindings.alreadyCreated) {
            return;
        }
        KeyBindings.alreadyCreated = true;
        for(KeyBinding k : keyBindings)
            ClientRegistry.registerKeyBinding(k);
    }

    @SuppressWarnings("UnnecessaryReturnStatement")
    @SubscribeEvent
    public void onEvent(final InputEvent.KeyInputEvent e) {
        if(keyBindings[0].isPressed()) {
            FeatureManager.blink.toggle();
            return;
        }
        if(keyBindings[1].isPressed()) {
            FeatureManager.blockTP.toggle();
            return;
        }
        if(keyBindings[2].isPressed()) {
            FeatureManager.noFall.toggle();
            return;
        }
        if(keyBindings[3].isPressed()) {
            FeatureManager.fly.toggle();
            return;
        }
        if(keyBindings[4].isPressed()) {
            FeatureManager.entityVelocity.toggle();
            return;
        }
        if(keyBindings[5].isPressed()) {
            FeatureManager.ghostBlock.toggle();
            return;
        }
        if(keyBindings[6].isPressed()) {
            FeatureManager.spider.toggle();
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
        keyBindings[6] = new KeyBinding("Spider", Keyboard.KEY_J, "RainStorm");
        KeyBindings.alreadyCreated = false;
    }
}
