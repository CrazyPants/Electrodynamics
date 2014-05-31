package com.edxmod.electrodynamics.common.recipe;

import com.edxmod.electrodynamics.api.tool.ToolDefinition;
import com.edxmod.electrodynamics.common.block.BlockStorage;
import com.edxmod.electrodynamics.common.block.EDXBlocks;
import com.edxmod.electrodynamics.common.item.EDXItems;
import com.edxmod.electrodynamics.common.item.prefab.EDXItem;
import com.edxmod.electrodynamics.common.item.resource.ItemResource;
import com.edxmod.electrodynamics.common.recipe.manager.SieveManager;
import com.edxmod.electrodynamics.common.recipe.manager.TableManager;
import com.edxmod.electrodynamics.common.util.StackHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmillerw
 */
public class EDXRecipes {

	public static TableManager TABLE = new TableManager();

	public static SieveManager SIEVE = new SieveManager();

    public static void initialize() {
		// TABLE - STONE
		TABLE.registerHammerRecipe(Blocks.stone, Blocks.cobblestone, 1F);
		TABLE.registerHammerRecipe(Blocks.cobblestone, Blocks.gravel, 1F);
		TABLE.registerHammerRecipe(Blocks.gravel, Blocks.sand, 1F);
		TABLE.registerHammerRecipe(Blocks.sand, new ItemStack(EDXBlocks.componentGround, 0, 0), 1F);

		// TABLE - NETHERRACK
		TABLE.registerHammerRecipe(Blocks.netherrack, new ItemStack(EDXBlocks.componentGround, 0, 1), 1F);
		TABLE.registerHammerRecipe(new ItemStack(EDXBlocks.componentGround, 0, 1), Blocks.soul_sand, 1F);
		TABLE.registerHammerRecipe(Blocks.soul_sand, new ItemStack(EDXBlocks.componentGround, 0, 3), 1F);

		// TABLE - WOOD
		for (int i=0; i<4; i++) {
			TABLE.registerRecipe(new ItemStack(Blocks.log, 1, i), new ItemStack(Blocks.planks, 6, i),  ToolDefinition.AXE, 1F);
		}
    }

    private static void addCraftingRecipes() {
		for (int i=0; i<ItemResource.NAMES.length; i++) {
			ItemStack ingot = new ItemStack(EDXItems.resourceIngot, 1, i);
			ItemStack storage = BlockStorage.getStorageFromResource(ingot);

			if (storage != null) {
				GameRegistry.addShapedRecipe(storage, "III", "III", "III", 'I', ingot);
				GameRegistry.addShapelessRecipe(StackHelper.copyAndResize(ingot, 9), storage);
			}
		}
    }
}
