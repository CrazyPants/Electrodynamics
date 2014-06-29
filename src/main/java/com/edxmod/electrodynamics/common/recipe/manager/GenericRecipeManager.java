package com.edxmod.electrodynamics.common.recipe.manager;

import com.edxmod.electrodynamics.common.recipe.wrapper.IGenericRecipe;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmillerw
 */
public class GenericRecipeManager<T extends IGenericRecipe> {

	private List<T> recipes = new ArrayList<T>();

	public void register(T recipe) {
		recipes.add(recipe);
	}

	public T get(ItemStack stack) {
		for (IGenericRecipe recipe : recipes) {
			if (recipe.valid(stack)) {
				return (T) recipe;
			}
		}
		return null;
	}
}
