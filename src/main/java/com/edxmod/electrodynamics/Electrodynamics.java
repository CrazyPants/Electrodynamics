package com.edxmod.electrodynamics;

import com.edxmod.electrodynamics.common.block.EDXBlocks;
import com.edxmod.electrodynamics.common.recipe.EDXRecipes;
import com.edxmod.electrodynamics.common.core.handler.GuiHandler;
import com.edxmod.electrodynamics.common.item.EDXItems;
import com.edxmod.electrodynamics.common.lib.EDXProps;
import com.edxmod.electrodynamics.common.network.PacketPipeline;
import com.edxmod.electrodynamics.common.recipe.RecipeParser;
import com.edxmod.electrodynamics.common.world.WorldProviderSkyblockHell;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraftforge.common.DimensionManager;

import java.io.File;
import java.io.IOException;

/**
 * @author Royalixor.
 */
@Mod(modid = EDXProps.ID, name = EDXProps.NAME, version = EDXProps.VERSION, dependencies = "required-after:Forge@[10.12.0.1060,)")
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

        EDXBlocks.initialize();
        EDXItems.initialize();
        EDXRecipes.initialize();

		File recipes = new File(configPath, "recipes/");
		if (!recipes.exists()) {
			recipes.mkdirs();
		}

		for (File file : recipes.listFiles()) {
			String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);

			if (extension.equalsIgnoreCase("json")) {
				RecipeParser.parseFile(file);
			}
		}

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

		try {
			RecipeParser.dumpItems(new File(configPath, "key_dump.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		DimensionManager.unregisterProviderType(-1);
		DimensionManager.unregisterDimension(-1);
		DimensionManager.registerProviderType(-1, WorldProviderSkyblockHell.class, false);
		DimensionManager.registerDimension(-1, -1);
	}

}