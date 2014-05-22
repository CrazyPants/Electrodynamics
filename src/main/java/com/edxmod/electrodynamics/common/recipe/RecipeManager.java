package com.edxmod.electrodynamics.common.recipe;

import com.edxmod.electrodynamics.api.EDXBlockHelper;
import com.edxmod.electrodynamics.api.EDXItemHelper;
import com.edxmod.electrodynamics.common.recipe.generic.GenericManager;
import net.minecraft.init.Blocks;

/**
 * @author dmillerw
 */
public class RecipeManager {

    public static final RecipeManager INSTANCE = new RecipeManager();

    public void init() {
		table.register(Blocks.stone, Blocks.cobblestone, 1F);
    }

    public GenericManager table = new GenericManager();
    public GenericManager kiln = new GenericManager();

}
