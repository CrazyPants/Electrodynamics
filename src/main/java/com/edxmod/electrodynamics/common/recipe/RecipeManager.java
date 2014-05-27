package com.edxmod.electrodynamics.common.recipe;

import com.edxmod.electrodynamics.api.tool.ToolDefinition;
import com.edxmod.electrodynamics.api.util.RandomStack;
import com.edxmod.electrodynamics.common.block.EDXBlocks;
import com.edxmod.electrodynamics.common.item.EDXItems;
import com.edxmod.electrodynamics.common.item.ItemHammer;
import com.edxmod.electrodynamics.common.item.prefab.EDXItem;
import com.edxmod.electrodynamics.common.recipe.manager.SieveManager;
import com.edxmod.electrodynamics.common.recipe.manager.TableManager;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * @author dmillerw
 */
public class RecipeManager {

    public static final RecipeManager INSTANCE = new RecipeManager();

    public void init() {
		// STONE
		table.registerHammerRecipe(Blocks.stone, Blocks.cobblestone);
		table.registerHammerRecipe(Blocks.cobblestone, Blocks.gravel);
		table.registerHammerRecipe(Blocks.gravel, Blocks.sand);
		table.registerHammerRecipe(Blocks.sand, new ItemStack(EDXBlocks.component, 0, 0));

		// NETHERRACK
		table.registerHammerRecipe(Blocks.netherrack, new ItemStack(EDXBlocks.component, 0, 1));
		table.registerHammerRecipe(new ItemStack(EDXBlocks.component, 0, 1), Blocks.soul_sand);
		table.registerHammerRecipe(Blocks.soul_sand, new ItemStack(EDXBlocks.component, 0, 3));

		// WOOD
		for (int i=0; i<4; i++) {
			table.registerRecipe(new ItemStack(Blocks.log, 1, i), ToolDefinition.AXE, new ItemStack(Blocks.planks, 6, i));
		}
    }

    public TableManager table = new TableManager();
	public SieveManager sieve = new SieveManager();

}
