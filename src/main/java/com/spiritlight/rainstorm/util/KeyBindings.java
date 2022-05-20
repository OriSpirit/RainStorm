package com.spiritlight.rainstorm.util;

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
    public static final KeyBinding FLY;
    public static final KeyBinding ENTITYVELOCITY;
    private static boolean alreadyCreated;

    public static void register() {
        if (KeyBindings.alreadyCreated) {
            return;
        }
        KeyBindings.alreadyCreated = true;
        ClientRegistry.registerKeyBinding(KeyBindings.BLOCKTP);
        ClientRegistry.registerKeyBinding(KeyBindings.BLINK);
        ClientRegistry.registerKeyBinding(KeyBindings.NOFALL);
        ClientRegistry.registerKeyBinding(KeyBindings.FLY);
        ClientRegistry.registerKeyBinding(KeyBindings.ENTITYVELOCITY);
    }

    @SubscribeEvent
    public void onEvent(final InputEvent.KeyInputEvent e) {
        if (KeyBindings.BLINK.isPressed()) {
            if(Blink.isEnabled())
                Blink.disable();
            else Blink.enable();
            return;
        }
        if(KeyBindings.BLOCKTP.isPressed()) {
            if(BlockTP.isEnabled()) {
                BlockTP.disable();
            } else {
                BlockTP.enable();
            }
            return;
        }
        if(KeyBindings.NOFALL.isPressed()) {
            if(NoFall.isEnabled())
                NoFall.disable();
            else NoFall.enable();
            return;
        }
        if(KeyBindings.FLY.isPressed()) {
            if(Fly.isEnabled())
                Fly.disable();
            else Fly.enable();
            return;
        }
        if(KeyBindings.ENTITYVELOCITY.isPressed()) {
            if(EntityVelocity.isEnabled())
                EntityVelocity.disable();
            else EntityVelocity.enable();
            return;
        }
    }

    static {
        BLINK = new KeyBinding("Blink", Keyboard.KEY_R, "RainStorm");
        BLOCKTP = new KeyBinding("Block TP", Keyboard.KEY_B, "RainStorm");
        NOFALL = new KeyBinding("NoFall", Keyboard.KEY_K, "RainStorm");
        FLY = new KeyBinding("Fly", Keyboard.KEY_G, "RainStorm");
        ENTITYVELOCITY = new KeyBinding("EntityVelocity", Keyboard.KEY_N, "RainStorm");
        KeyBindings.alreadyCreated = false;
    }
}
