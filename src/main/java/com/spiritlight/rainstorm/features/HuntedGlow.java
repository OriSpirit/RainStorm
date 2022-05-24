package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.util.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class HuntedGlow extends Mod {
    @SubscribeEvent
    public void playerEvent(PlayerEvent event) {
        try {
            final PlayerUtils playerUtils = new PlayerUtils();
            // if(!enabled || event.getEntityPlayer().equals(playerUtils.getPlayer())) return;
            if (event.player.getDisplayName().toString().contains("Player")) {
                final EntityPlayer player = event.player;
                player.setGlowing(true);
            }
        } catch (NullPointerException ignored) {}
    }
}
