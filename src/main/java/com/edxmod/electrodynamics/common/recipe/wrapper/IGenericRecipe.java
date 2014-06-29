package com.edxmod.electrodynamics.common.recipe.wrapper;

import net.minecraft.item.ItemStack;

/**
 * @author dmillerw
 */
public interface IGenericRecipe {

	public boolean valid(ItemStack stack);

	public ItemStack[] get(ItemStack stack);
}
