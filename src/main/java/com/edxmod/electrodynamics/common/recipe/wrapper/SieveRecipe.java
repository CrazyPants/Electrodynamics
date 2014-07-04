package com.edxmod.electrodynamics.common.recipe.wrapper;

import com.edxmod.electrodynamics.common.lib.RandomStack;
import com.edxmod.electrodynamics.common.util.StackHelper;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SieveRecipe implements IGenericRecipe<ItemStack[]> {

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
	public int getDuration() {
		return duration;
	}

	@Override
	public boolean valid(ItemStack stack) {
		return StackHelper.areStacksSimilar(stack, input, ignoreNBT);
	}

	@Override
	public ItemStack[] get(ItemStack input) {
		List<ItemStack> out = new ArrayList<ItemStack>();

		for (RandomStack stack : output) {
			out.add(stack.get());
		}

		return out.toArray(new ItemStack[out.size()]);
	}
}