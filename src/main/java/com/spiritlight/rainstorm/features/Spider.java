package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.Spirit;
import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.events.UpdateListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class Spider extends Mod implements UpdateListener {
    public String modName = "Base";
    public void enable() {
        Spirit.INSTANCE.events.add(UpdateListener.class, this);
        enabled = true;
    }
    public void disable() {
        Spirit.INSTANCE.events.remove(UpdateListener.class, this);
        enabled = false;
    }

    @Override
    public void onUpdate() {
        if(!enabled) return;
        final EntityPlayerSP player = Minecraft.getMinecraft().player;
        if(player == null) return;
        if(player.collidedHorizontally)
            player.motionY = 0.2;
    }
}
