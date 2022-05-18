package com.spiritlight.rainstorm.util;

import com.spiritlight.rainstorm.config.status;
import com.spiritlight.rainstorm.features.*;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;


public class KeyBindings
{
    public static final KeyBinding BLINK;
    public static final KeyBinding BLOCKTP;
    public static final KeyBinding NOFALL;
    public static final KeyBinding INFINIMANA;
    private static boolean alreadyCreated;

    public static void register() {
        if (KeyBindings.alreadyCreated) {
            return;
        }
        KeyBindings.alreadyCreated = true;
        ClientRegistry.registerKeyBinding(KeyBindings.BLOCKTP);
        ClientRegistry.registerKeyBinding(KeyBindings.BLINK);
        ClientRegistry.registerKeyBinding(KeyBindings.NOFALL);
        ClientRegistry.registerKeyBinding(KeyBindings.INFINIMANA);
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
        }
        if(KeyBindings.BLOCKTP.isPressed()) {
            if(BlockTP.isEnabled()) {
                BlockTP.disable();
            } else
                BlockTP.enable();
        }
        if(KeyBindings.NOFALL.isPressed()) {
            if(NoFall.isEnabled())
                NoFall.disable();
            else
                NoFall.enable();
        }
    }

    static {
        BLINK = new KeyBinding("Blink", Keyboard.KEY_R, "RainStorm");
        BLOCKTP = new KeyBinding("Block TP", Keyboard.KEY_G, "RainStorm");
        NOFALL = new KeyBinding("NoFall", Keyboard.KEY_K, "RainStorm");
        INFINIMANA = new KeyBinding("NoHunger", Keyboard.KEY_H, "RainStorm");
        KeyBindings.alreadyCreated = false;
    }
}
