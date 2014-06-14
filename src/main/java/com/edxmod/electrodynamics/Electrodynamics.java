package com.edxmod.electrodynamics;

import com.edxmod.electrodynamics.common.lib.EDXProps;
import com.edxmod.electrodynamics.common.network.PacketHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * @author Royalixor.
 */
@Mod(modid = EDXProps.ID, name = EDXProps.NAME, version = EDXProps.VERSION, dependencies = "required-after:Forge@[10.12.2.1121,)")
public class Electrodynamics {

    @Mod.Instance(EDXProps.ID)
    public static Electrodynamics instance;

    @SidedProxy(clientSide = EDXProps.CLIENT, serverSide = EDXProps.SERVER)
    public static CommonProxy proxy;

    public static String configPath;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        configPath = event.getModConfigurationDirectory() + "/EDX/";
		proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
		PacketHandler.initialize();
		proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit();
	}

}