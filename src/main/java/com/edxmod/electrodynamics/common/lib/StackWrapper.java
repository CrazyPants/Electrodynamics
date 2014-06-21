package com.edxmod.electrodynamics.common.lib;

import net.minecraft.item.ItemStack;

public class StackWrapper {

	private ItemStack wrap;

	public StackWrapper(ItemStack toWrap) {
		wrap = toWrap;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof StackWrapper)) return false;
		StackWrapper isw = (StackWrapper) obj;
		if (wrap.getHasSubtypes()) {
			return isw.wrap.isItemEqual(wrap);
		} else {
			return isw.wrap == wrap;
		}
	}

	@Override
	public int hashCode() {
		return System.identityHashCode(wrap);
	}
}