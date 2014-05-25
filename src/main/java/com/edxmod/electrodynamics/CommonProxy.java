package com.edxmod.electrodynamics;

import com.edxmod.electrodynamics.common.core.handler.BlockEventHandler;
import com.edxmod.electrodynamics.common.core.handler.PlayerRespawnHandler;
import com.edxmod.electrodynamics.common.core.handler.SpiderTracker;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author Royalixor.
 */
public class CommonProxy {

    public void preInit() {
		MinecraftForge.EVENT_BUS.register(new BlockEventHandler());

		FMLCommonHandler.instance().bus().register(new SpiderTracker());
		FMLCommonHandler.instance().bus().register(new PlayerRespawnHandler());
    }

    public void registerRenders() {

    }

}
