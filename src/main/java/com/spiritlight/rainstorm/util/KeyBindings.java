package com.spiritlight.rainstorm.util;

import com.spiritlight.rainstorm.config.status;
import com.spiritlight.rainstorm.features.Blink;
import com.spiritlight.rainstorm.features.BlockTP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;


public class KeyBindings
{
    public static final KeyBinding BLINK;
    public static final KeyBinding CLOSE_SILENTLY;
    public static final KeyBinding BLOCKTP;
    private static boolean alreadyCreated;

    public static void register() {
        if (KeyBindings.alreadyCreated) {
            return;
        }
        KeyBindings.alreadyCreated = true;
        ClientRegistry.registerKeyBinding(KeyBindings.CLOSE_SILENTLY);
    }

    @SubscribeEvent
    public void onEvent(final InputEvent.KeyInputEvent e) {
        if (KeyBindings.BLINK.isPressed()) {
            status.blink = !status.blink;
            if (status.blink) {
                Blink.enable();
            } else {
                Blink.disable();
            }
            return;
        }
        if (KeyBindings.CLOSE_SILENTLY.isPressed()) {
            status.cancelNextInventoryPacket = true;
            return;
        }
        if(KeyBindings.BLOCKTP.isPressed()) {
            BlockTP.setEnabled(true);
        }
    }

    static {
        BLINK = new KeyBinding("Blink", Keyboard.KEY_R, "RainStorm");
        BLOCKTP = new KeyBinding("Block TP", Keyboard.KEY_G, "RainStorm");
        CLOSE_SILENTLY = new KeyBinding("Close GUI silently", Keyboard.KEY_K, "RainStorm");
        KeyBindings.alreadyCreated = false;
    }
}
