package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.util.Messenger;
import com.spiritlight.rainstorm.util.PacketUtils;

public class Blink extends Mod {
    public String modName = "Blink";


    @Override
    public void enable() {
        PacketUtils.stopPacket();
        Messenger.send("Enabled blink");
        enabled = true;
    }

    @Override
    public void disable() {
        PacketUtils.startPacket(false);
        Messenger.send("Disabled blink");
        enabled = false;
    }

    public void disableRP() {
        PacketUtils.startPacket(true);
        Messenger.send("Disabled blink");
        enabled = false;
    }
}
