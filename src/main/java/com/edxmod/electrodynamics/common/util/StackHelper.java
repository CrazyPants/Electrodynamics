package com.edxmod.electrodynamics.common.util;

import net.minecraft.item.ItemStack;

/**
 * @author dmillerw
 */
public class StackHelper {

	public static ItemStack copyAndResize(ItemStack ingot, int size) {
		ItemStack copy = ingot.copy();
		copy.stackSize = size;
		return copy;
	}

}
