package com.edxmod.electrodynamics.common.recipe.wrapper;

import net.minecraft.item.ItemStack;

/**
 * @author dmillerw
 */
public interface IGenericRecipe<T> {

    public boolean valid(ItemStack stack);

    public T get(ItemStack stack);
}
