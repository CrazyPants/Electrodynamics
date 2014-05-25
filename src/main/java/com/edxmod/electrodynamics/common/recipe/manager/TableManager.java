package com.edxmod.electrodynamics.common.recipe.manager;

import com.edxmod.electrodynamics.api.recipe.ITableManager;
import com.edxmod.electrodynamics.common.util.ItemHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashSet;
import java.util.Set;

/**
 * @author dmillerw
 */
public class TableManager implements ITableManager {

	public static class TableRecipe {
		private final ItemStack input;
		private final ItemStack output;

		private final float durability;

		private final boolean ignoreNBT;

		public TableRecipe(ItemStack input, ItemStack output, float durability, boolean ignoreNBT) {
			this.input = input;
			this.output = output;
			this.durability = durability;
			this.ignoreNBT = ignoreNBT;
		}

		public boolean isInput(ItemStack stack) {
			if (stack.getItemDamage() == OreDictionary.WILDCARD_VALUE || input.getItemDamage() == OreDictionary.WILDCARD_VALUE) {
				return stack.getItem() == input.getItem() && (ignoreNBT || ItemStack.areItemStackTagsEqual(stack, input));
			} else {
				return ((stack.getItem() == input.getItem()) && stack.getItemDamage() == input.getItemDamage()) && (ignoreNBT || ItemStack.areItemStacksEqual(stack, input));
			}
		}

		public ItemStack getOutput(ItemStack input) {
			if (!isInput(input)) {
				return null;
			}

			ItemStack out = output.copy();
			out.stackSize = input.stackSize;
			return out;
		}

		public float getDurability() {
			return durability;
		}
	}

	private Set<TableRecipe> recipes = new HashSet<TableRecipe>();

	@Override
	public void register(Object input, Object output, float durability) {
		if (input == null || output == null) {
			return;
		}

		register(new TableRecipe(ItemHelper.convertToItemStack(input), ItemHelper.convertToItemStack(output), durability, true));
	}

	public void register(TableRecipe recipe) {
		recipes.add(recipe);
	}

	public TableRecipe get(ItemStack input) {
		for (TableRecipe recipe : recipes) {
			if (recipe.isInput(input)) {
				return recipe;
			}
		}
		return null;
	}

}
