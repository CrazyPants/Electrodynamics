package com.edxmod.electrodynamics.common.recipe.wrapper;

import com.edxmod.electrodynamics.common.util.StackHelper;
import net.minecraft.item.ItemStack;

import java.util.Random;

/**
 * @author dmillerw
 */
public class BarrelRecipe implements IGenericRecipe {

	private static final Random random = new Random();

	public final ItemStack input;
	public final ItemStack output;

	public final int maxStackSize;

	public final int duration;
	public final int durationModifier;

	public final boolean exactStackSize;

	public BarrelRecipe(ItemStack input, ItemStack output, boolean exactStackSize, int maxStackSize, int duration) {
		this(input, output, exactStackSize, maxStackSize, duration, 0);
	}

	public BarrelRecipe(ItemStack input, ItemStack output, boolean exactStackSize, int maxStackSize, int duration, int durationModifier) {
		this.input = input;
		this.output = output;
		this.exactStackSize = exactStackSize;
		this.maxStackSize = maxStackSize;
		this.duration = duration;
		this.durationModifier = durationModifier;
	}

	public int getDuration() {
		return duration + (random.nextInt(durationModifier));
	}

	@Override
	public boolean valid(ItemStack stack) {
		return StackHelper.areStacksSimilar(stack, input, true);
	}

	@Override
	public ItemStack[] get(ItemStack stack) {
		ItemStack out = output.copy();
		out.stackSize = stack.stackSize;
		return new ItemStack[] {out};
	}
}
