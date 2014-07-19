package com.edxmod.electrodynamics;

import com.edxmod.electrodynamics.common.block.EDXBlocks;
import com.edxmod.electrodynamics.common.core.handler.BlockEventHandler;
import com.edxmod.electrodynamics.common.core.handler.BucketHandler;
import com.edxmod.electrodynamics.common.core.handler.GuiHandler;
import com.edxmod.electrodynamics.common.core.handler.SpiderTracker;
import com.edxmod.electrodynamics.common.fluid.EDXFluids;
import com.edxmod.electrodynamics.common.item.EDXItems;
import com.edxmod.electrodynamics.common.item.ItemHammer;
import com.edxmod.electrodynamics.common.lib.tool.ToolDefinition;
import com.edxmod.electrodynamics.common.recipe.EDXRecipes;
import com.edxmod.electrodynamics.common.recipe.core.RecipeParser;
import com.edxmod.electrodynamics.common.network.nbt.data.AbstractSerializer;
import com.edxmod.electrodynamics.common.util.EventUtil;
import com.edxmod.electrodynamics.common.world.WorldProviderSkyblockHell;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.DimensionManager;

import java.io.File;
import java.io.IOException;

/**
 * @author Royalixor.
 */
public class CommonProxy {

    public void preInit() {
        EDXFluids.initializeFluids();
        EDXBlocks.initialize();
        EDXItems.initialize();
        EDXFluids.initializeFluidContainers();
        EDXRecipes.initialize();

        AbstractSerializer.initialize();

        NetworkRegistry.INSTANCE.registerGuiHandler(Electrodynamics.instance, new GuiHandler());

        EventUtil.register(new BlockEventHandler(), EventUtil.Type.FML);
        EventUtil.register(new SpiderTracker(), EventUtil.Type.FORGE);
        EventUtil.register(BucketHandler.INSTANCE, EventUtil.Type.FORGE);
    }

    public void init() {

    }

    public void postInit() {
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

        try {
            RecipeParser.dumpItems(new File(Electrodynamics.configPath, "key_dump.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        DimensionManager.unregisterProviderType(-1);
        DimensionManager.unregisterDimension(-1);
        DimensionManager.registerProviderType(-1, WorldProviderSkyblockHell.class, false);
        DimensionManager.registerDimension(-1, -1);

        // Tool registration
        ToolDefinition.register(new ItemStack(EDXItems.hammerWood), ToolDefinition.HAMMER, ItemHammer.STRENGTH[0]);
        ToolDefinition.register(new ItemStack(EDXItems.hammerStone), ToolDefinition.HAMMER, ItemHammer.STRENGTH[1]);
        ToolDefinition.register(new ItemStack(EDXItems.hammerIron), ToolDefinition.HAMMER, ItemHammer.STRENGTH[2]);
        ToolDefinition.register(new ItemStack(EDXItems.hammerSteel), ToolDefinition.HAMMER, ItemHammer.STRENGTH[3]);
        ToolDefinition.register(new ItemStack(EDXItems.hammerDiamond), ToolDefinition.HAMMER, ItemHammer.STRENGTH[4]);

        // Axe registration
        for (Object key : GameData.getItemRegistry().getKeys()) {
            Item axe = (Item) Item.itemRegistry.getObject(key.toString());

            if (axe instanceof ItemAxe) {
                ToolDefinition.register(new ItemStack(axe), ToolDefinition.AXE, ((ItemTool) axe).func_150913_i().getEfficiencyOnProperMaterial());
            }
        }
    }
}
