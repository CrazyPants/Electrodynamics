package com.edxmod.electrodynamics;

import com.edxmod.electrodynamics.common.block.EDXBlocks;
import com.edxmod.electrodynamics.common.config.EDXConfiguration;
import com.edxmod.electrodynamics.common.core.EDXRecipes;
import com.edxmod.electrodynamics.common.core.handler.GuiHandler;
import com.edxmod.electrodynamics.common.events.EDXEvents;
import com.edxmod.electrodynamics.common.item.EDXItems;
import com.edxmod.electrodynamics.common.network.PacketPipeline;
import com.edxmod.electrodynamics.common.lib.EDXProps;
import com.edxmod.electrodynamics.common.world.EDXWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

/**
 * @author Royalixor.
 */
@Mod(modid = EDXProps.ID, name = EDXProps.NAME, version = EDXProps.VERSION, dependencies = "required-after:Forge@[10.12.0.1024,)")
public class Electrodynamics {

    @Mod.Instance(EDXProps.ID)
    public static Electrodynamics instance;

    @SidedProxy(clientSide = EDXProps.CLIENT, serverSide = EDXProps.SERVER)
    public static CommonProxy proxy;

    public static final PacketPipeline packetPipeline = new PacketPipeline();

    public static String configPath;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        configPath = event.getModConfigurationDirectory() + "/EDX/";
        EDXConfiguration.init(configPath);

        EDXBlocks.init();
        EDXItems.init();
        EDXEvents.init();
        EDXRecipes.init();
        EDXWorldGenerator.init();

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        proxy.registerRenders();
		proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        packetPipeline.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        packetPipeline.postInit();
    }

}