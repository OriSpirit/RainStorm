package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.events.EventHandler;
import com.spiritlight.rainstorm.events.EventListener;
import com.spiritlight.rainstorm.util.Messenger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.eventhandler.Event;

public class Spider extends Mod implements EventHandler.Listener {
    public String modName = "Base";
    public void enable() {
        EventHandler.add(this);
        Messenger.send("opened spider");
        enabled = true;
    }
    public void disable() {
        EventHandler.remove(this);
        Messenger.send("closed spider");
        enabled = false;
    }

    @Override
    public void onEvent(Event event) {
        if(!enabled) return;
        Messenger.send("Event check");
        final EntityPlayerSP player = Minecraft.getMinecraft().player;
        if(player == null) return;
        if(player.collidedHorizontally)
            player.motionY = 0.2;
    }

}
