package com.edxmod.electrodynamics.common.recipe;

import com.edxmod.electrodynamics.api.util.RandomStack;
import com.edxmod.electrodynamics.common.block.EDXBlocks;
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
		table.register(Blocks.stone, Blocks.cobblestone, 1F);
		table.register(Blocks.cobblestone, Blocks.gravel, 1F);
		table.register(Blocks.gravel, Blocks.sand, 1F);
		table.register(Blocks.sand, new ItemStack(EDXBlocks.component, 0, 0), 1F);

		// NETHERRACK
		table.register(Blocks.netherrack, new ItemStack(EDXBlocks.component, 0, 1), 1F);
		table.register(new ItemStack(EDXBlocks.component, 0, 1), Blocks.soul_sand, 1F);
		table.register(Blocks.soul_sand, new ItemStack(EDXBlocks.component, 0, 3), 1F);
    }

    public TableManager table = new TableManager();
	public SieveManager sieve = new SieveManager();

}
