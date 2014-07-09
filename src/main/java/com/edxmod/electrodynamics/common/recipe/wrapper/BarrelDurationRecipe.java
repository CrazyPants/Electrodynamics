package com.edxmod.electrodynamics.common.recipe.wrapper;

import com.edxmod.electrodynamics.common.util.StackHelper;
import net.minecraft.item.ItemStack;

import java.util.Random;

/**
 * @author dmillerw
 */
public class BarrelDurationRecipe {

	public static final Random random = new Random();

	public final ItemStack input;
	public final ItemStack output;

	private final int durationConstant;
	private final int durationModifier;

	public final boolean requireLid;

	public BarrelDurationRecipe(ItemStack input, ItemStack output, int durationConstant, int durationModifier, boolean requireLid) {
		this.input = input;
		this.output = output;
		this.durationConstant = durationConstant;
		this.durationModifier = durationModifier;
		this.requireLid = requireLid;
	}

	public int getDuration() {
		return durationConstant + random.nextInt(durationModifier + 1);
	}

	public boolean isValid(ItemStack input) {
		return StackHelper.areStacksSimilar(this.input, input, true);
	}

	public ItemStack getOutput() {
		return output.copy();
	}
}
