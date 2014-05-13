package com.edxmod.electrodynamics.common.events;

import com.edxmod.electrodynamics.common.events.misc.CapeHandler;
import com.edxmod.electrodynamics.common.events.network.ConnectionEventHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author Royalixor.
 */
public class EDXEvents {

    public static void init() {
        registerNetworkEventHandlers();
        registerMiscEvents();
    }

    private static void registerNetworkEventHandlers() {
        FMLCommonHandler.instance().bus().register(new ConnectionEventHandler());
    }

    private static void registerMiscEvents() {
        MinecraftForge.EVENT_BUS.register(new CapeHandler());
    }
}
