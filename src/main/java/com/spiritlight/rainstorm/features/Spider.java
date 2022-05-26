package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.events.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class Spider extends Mod implements Listener {
    public String modName = "Base";
    @Override
    public void onUpdate() {
        if(!enabled) return;
        final EntityPlayerSP player = Minecraft.getMinecraft().player;
        if(player == null) return;
        if(player.collidedHorizontally)
            player.motionY = 0.2;
    }
    public void enable() { enabled = true; }
    public void disable() { enabled = false; }

}
