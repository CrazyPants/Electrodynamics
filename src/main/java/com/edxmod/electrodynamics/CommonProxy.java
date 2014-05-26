package com.edxmod.electrodynamics;

import com.edxmod.electrodynamics.common.block.EDXBlocks;
import com.edxmod.electrodynamics.common.core.handler.BlockEventHandler;
import com.edxmod.electrodynamics.common.core.handler.GuiHandler;
import com.edxmod.electrodynamics.common.core.handler.SpiderTracker;
import com.edxmod.electrodynamics.common.item.EDXItems;
import com.edxmod.electrodynamics.common.recipe.EDXRecipes;
import com.edxmod.electrodynamics.common.recipe.RecipeParser;
import com.edxmod.electrodynamics.common.world.WorldProviderSkyblockHell;
import com.edxmod.electrodynamics.common.world.generation.WorldGenInfernalTree;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;

import java.io.File;
import java.io.IOException;

/**
 * @author Royalixor.
 */
public class CommonProxy {

    public void preInit() {
		EDXBlocks.initialize();
		EDXItems.initialize();
		EDXRecipes.initialize();

		File recipes = new File(Electrodynamics.configPath, "recipes/");
		if (!recipes.exists()) {
			recipes.mkdirs();
		}

		for (File file : recipes.listFiles()) {
			String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);

			if (extension.equalsIgnoreCase("json")) {
				RecipeParser.parseFile(file);
			}
		}

		NetworkRegistry.INSTANCE.registerGuiHandler(Electrodynamics.instance, new GuiHandler());

		MinecraftForge.EVENT_BUS.register(new BlockEventHandler());

		FMLCommonHandler.instance().bus().register(new SpiderTracker());
    }

    public void init() {

    }

	public void postInit() {
		try {
			RecipeParser.dumpItems(new File(Electrodynamics.configPath, "key_dump.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		DimensionManager.unregisterProviderType(-1);
		DimensionManager.unregisterDimension(-1);
		DimensionManager.registerProviderType(-1, WorldProviderSkyblockHell.class, false);
		DimensionManager.registerDimension(-1, -1);
    }

}
