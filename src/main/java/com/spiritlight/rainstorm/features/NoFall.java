package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.Spirit;
import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.events.EventHandler;
import com.spiritlight.rainstorm.util.Messenger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;

public class NoFall extends Mod implements EventHandler.Listener {
    public String modName = "NoFall";
    boolean enabled = false;

    public void enable() {
        Spirit.events.add(this);
        Messenger.send("opened nofall");
        enabled = true;
    }
    public void disable() {
        Spirit.events.remove(this);
        Messenger.send("closed nofall");
        enabled = false;
    }

    @Override
    public void onEvent(Event event) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player == null) return;
        if (!enabled || !player.isAirBorne || player.capabilities.isCreativeMode) return;
        if(player.motionY < -0.5) {
            try {
                Minecraft.getMinecraft().getConnection().sendPacket(new CPacketPlayer(true));
            } catch (NullPointerException ignored) {}
        }
    }
}
