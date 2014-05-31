package com.edxmod.electrodynamics.common.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

/**
 * @author dmillerw
 */
public class StackHelper {

	public static ItemStack[] convert(Object object) {
		if (object instanceof ItemStack) {
			ItemStack stack = (ItemStack) object;
			if (stack.getItemDamage() < 0) {
				stack.setItemDamage(0);
			}
			return new ItemStack[] {stack};
		}

		if (object instanceof Block) {
			return new ItemStack[] {new ItemStack((Block)object, 1, OreDictionary.WILDCARD_VALUE)};
		}

		if (object instanceof Item) {
			return new ItemStack[] {new ItemStack((Item)object, 1, OreDictionary.WILDCARD_VALUE)};
		}

		if (object instanceof String) {
			List<ItemStack> ores = OreDictionary.getOres(object.toString());
			return ores.toArray(new ItemStack[ores.size()]);
		}

		return null;
	}

	public static ItemStack copyAndResize(ItemStack ingot, int size) {
		ItemStack copy = ingot.copy();
		copy.stackSize = size;
		return copy;
	}

}
