package com.edxmod.electrodynamics.common.util;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author dmillerw
 */
public class EventUtil {

    public static enum Type {
        FML,
        FORGE,
        BOTH
    }

    public static void register(Object instance, Type type) {
        if (type == Type.FML || type == Type.BOTH) {
            FMLCommonHandler.instance().bus().register(instance);
        }

        if (type == Type.FORGE || type == Type.BOTH) {
            MinecraftForge.EVENT_BUS.register(instance);
        }
    }
}
