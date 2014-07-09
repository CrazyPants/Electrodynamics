package com.edxmod.electrodynamics.common.lib.stack;

import com.edxmod.electrodynamics.common.util.StackHelper;
import net.minecraft.item.ItemStack;

/**
 * @author dmillerw
 */
public class ItemStackWrapper extends GenericStackWrapper<ItemStack> {

	public ItemStackWrapper(ItemStack contents) {
		super(contents);
	}

	@Override
	public GenericStackWrapper<ItemStack> copy() {
		return new ItemStackWrapper(contents.copy());
	}

	@Override
	public boolean equals(GenericStackWrapper<ItemStack> wrapper) {
		if (wrapper.contents == null || wrapper.contents.getItem() == null) {
			return false;
		}

		return StackHelper.areStacksSimilar(wrapper.contents, contents, false);
	}

	@Override
	public int hashCode() {
		int hashCode = 1;
		hashCode = (37 * hashCode) + contents.getItem().hashCode();
		hashCode = (37 * hashCode) + contents.getItemDamage();

		return hashCode;
	}
}
