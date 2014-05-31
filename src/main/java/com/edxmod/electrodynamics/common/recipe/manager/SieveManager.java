package com.edxmod.electrodynamics.common.recipe.manager;

import com.edxmod.electrodynamics.api.recipe.ISieveManager;
import com.edxmod.electrodynamics.api.util.RandomStack;
import com.edxmod.electrodynamics.common.recipe.wrapper.SieveRecipe;
import com.edxmod.electrodynamics.common.util.StackHelper;
import net.minecraft.item.ItemStack;

import java.util.HashSet;
import java.util.Set;

/**
 * @author dmillerw
 */
public class SieveManager implements ISieveManager {

	private Set<SieveRecipe> recipes = new HashSet<SieveRecipe>();

	@Override
	public void register(Object input, RandomStack[] output, int duration) {
		if (input == null || output == null || output.length < 1 || duration <= 0) {
			return;
		}

		ItemStack[] in = StackHelper.convert(input);

		for (ItemStack stack : in) {
			register(new SieveRecipe(stack, output, duration, true));
		}
	}

	public void register(SieveRecipe recipe) {
		recipes.add(recipe);
	}

	public SieveRecipe get(ItemStack input) {
		for (SieveRecipe recipe : recipes) {
			if (recipe.isInput(input)) {
				return recipe;
			}
		}
		return null;
	}
}
