package com.spiritlight.rainstorm;

import com.spiritlight.rainstorm.events.ConnectionEvent;
import com.spiritlight.rainstorm.features.__init__;
import com.spiritlight.rainstorm.util.KeyBindings;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = MainMod.MODID, name = MainMod.NAME, version = MainMod.VERSION, clientSideOnly = true)
public class MainMod
{
    public static final String MODID = "spirit";
    public static final String NAME = "RainStorm";
    public static final String VERSION = "1.0";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        KeyBindings.register();
        MinecraftForge.EVENT_BUS.register(new KeyBindings());
        MinecraftForge.EVENT_BUS.register(new ConnectionEvent());
        MinecraftForge.EVENT_BUS.register(new com.spiritlight.rainstorm.util.EventHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        __init__.run();
    }


}
