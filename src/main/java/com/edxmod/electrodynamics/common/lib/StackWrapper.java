package com.edxmod.electrodynamics.common.lib;

import com.edxmod.electrodynamics.common.util.StackHelper;
import net.minecraft.item.ItemStack;

public class StackWrapper {

	private ItemStack wrap;

	public StackWrapper(ItemStack toWrap) {
		wrap = toWrap;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof StackWrapper)) {
			return false;
		}

		StackWrapper isw = (StackWrapper) obj;

		if (isw.wrap == null || isw.wrap.getItem() == null) {
			return false;
		}

		return StackHelper.areStacksSimilar(wrap, isw.wrap, false);
	}

	@Override
	public int hashCode() {
		int hashCode = 1;
		hashCode = (37 * hashCode) + wrap.getItem().hashCode();
		hashCode = (37 * hashCode) + wrap.getItemDamage();

		return hashCode;
	}
}