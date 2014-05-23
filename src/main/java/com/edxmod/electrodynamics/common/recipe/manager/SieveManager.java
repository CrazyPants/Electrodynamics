package com.edxmod.electrodynamics.common.recipe.manager;

import com.edxmod.electrodynamics.api.recipe.ISieveManager;
import com.edxmod.electrodynamics.api.util.RandomStack;
import com.edxmod.electrodynamics.common.util.UtilItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author dmillerw
 */
public class SieveManager implements ISieveManager {

	public static class SieveRecipe {
		private final ItemStack input;
		private final RandomStack[] output;

		private final boolean ignoreNBT;

		public SieveRecipe(ItemStack input, RandomStack[] output, boolean ignoreNBT) {
			this.input = input;
			this.output = output;
			this.ignoreNBT = ignoreNBT;
		}

		public boolean isInput(ItemStack stack) {
			if (stack.getItemDamage() == OreDictionary.WILDCARD_VALUE || input.getItemDamage() == OreDictionary.WILDCARD_VALUE) {
				return stack.getItem() == input.getItem() && (ignoreNBT || ItemStack.areItemStackTagsEqual(stack, input));
			} else {
				return ((stack.getItem() == input.getItem()) && stack.getItemDamage() == input.getItemDamage()) && (ignoreNBT || ItemStack.areItemStacksEqual(stack, input));
			}
		}

		public ItemStack[] getOutput() {
			List<ItemStack> out = new ArrayList<ItemStack>();

			for (RandomStack stack : output) {
				out.add(stack.get());
			}

			return out.toArray(new ItemStack[out.size()]);
		}
	}

	private Set<SieveRecipe> recipes = new HashSet<SieveRecipe>();

	@Override
	public void register(ItemStack input, RandomStack[] output) {
		if (input == null || output == null || output.length < 1) {
			return;
		}

		register(new SieveRecipe(input, output, true));
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
