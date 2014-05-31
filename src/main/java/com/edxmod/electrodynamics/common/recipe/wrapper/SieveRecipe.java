package com.edxmod.electrodynamics.common.recipe.wrapper;

import com.edxmod.electrodynamics.api.util.RandomStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class SieveRecipe {

	private final ItemStack input;
	private final RandomStack[] output;

	private final int duration;

	private final boolean ignoreNBT;

	public SieveRecipe(ItemStack input, RandomStack[] output, int duration, boolean ignoreNBT) {
		this.input = input;
		this.output = output;
		this.duration = duration;
		this.ignoreNBT = ignoreNBT;
	}

	public boolean isInput(ItemStack stack) {
		if (stack.getItemDamage() == OreDictionary.WILDCARD_VALUE || input.getItemDamage() == OreDictionary.WILDCARD_VALUE) {
			return stack.getItem() == input.getItem() && (ignoreNBT || ItemStack.areItemStackTagsEqual(stack, input));
		} else {
			return ((stack.getItem() == input.getItem()) && stack.getItemDamage() == input.getItemDamage()) && (ignoreNBT || ItemStack.areItemStacksEqual(stack, input));
		}
	}

	public int getDuration() {
		return duration;
	}

	public ItemStack[] getOutput() {
		List<ItemStack> out = new ArrayList<ItemStack>();

		for (RandomStack stack : output) {
			out.add(stack.stack.copy());
		}

		return out.toArray(new ItemStack[out.size()]);
	}
}