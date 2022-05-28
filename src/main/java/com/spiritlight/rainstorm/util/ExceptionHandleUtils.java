package com.spiritlight.rainstorm.util;

import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.HoverEvent;

import java.util.Arrays;

public class ExceptionHandleUtils {
    public static void sendException(Exception e, String message, boolean printStackTrace) {
        if(message == null)
            message = "RainStorm ran into an exception: $errType: $err (Hover for details)";
        if(e == null)
            return;
        Style style;
        // use $err for error name; $errType for the other stuffs
        TextComponentString t = new TextComponentString(message.replaceAll("\\$err", e.getMessage()).replaceAll("\\$errType", e.getClass().getCanonicalName()));
        style = t.getStyle();
        TextComponentString s = new TextComponentString(e.getClass().getCanonicalName() + ": " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()).replaceAll("(,)", ",\n").replaceAll("\\[", "").replaceAll("]", "") + (printStackTrace ? "\n(Stacktrace printed in logs)" : ""));
        style.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, s));
        Messenger.send(t);
        if(printStackTrace)
            e.printStackTrace();
    }
}
