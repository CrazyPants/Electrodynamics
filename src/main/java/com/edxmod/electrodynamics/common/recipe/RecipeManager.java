package com.edxmod.electrodynamics.common.recipe;

import com.edxmod.electrodynamics.api.EDXBlockHelper;
import com.edxmod.electrodynamics.api.EDXItemHelper;
import com.edxmod.electrodynamics.api.util.RandomStack;
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
		table.register(Blocks.stone, Blocks.cobblestone, 1F);
    }

    public TableManager table = new TableManager();
	public SieveManager sieve = new SieveManager();

}
