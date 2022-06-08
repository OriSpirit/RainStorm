package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.events.EventHandler;
import com.spiritlight.rainstorm.util.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GhostBlock extends Mod implements EventHandler.Listener {
    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if(!enabled) return;
        if(event.getHand() == EnumHand.OFF_HAND) return;
        Minecraft.getMinecraft().world.setBlockToAir(event.getPos());
        PacketHandler.discardPacket("SPacketBlockChange", PacketHandler.READ);
    }

    @Override
    public void onEvent(Event event) {

    }
}
