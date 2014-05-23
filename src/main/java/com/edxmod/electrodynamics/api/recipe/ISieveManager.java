package com.edxmod.electrodynamics.api.recipe;

import com.edxmod.electrodynamics.api.util.RandomStack;
import net.minecraft.item.ItemStack;

/**
 * @author dmillerw
 */
public interface ISieveManager {

	public void register(ItemStack input, RandomStack[] output, int duration);

}
