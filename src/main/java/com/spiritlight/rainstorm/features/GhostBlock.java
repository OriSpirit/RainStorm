package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GhostBlock extends Mod {
    @SubscribeEvent
    public void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        if(!enabled || event.getHand().equals(EnumHand.OFF_HAND)) return;
        Minecraft.getMinecraft().world.setBlockToAir(event.getPos());
    }
}
