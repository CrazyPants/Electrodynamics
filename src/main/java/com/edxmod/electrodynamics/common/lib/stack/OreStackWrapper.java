package com.edxmod.electrodynamics.common.lib.stack;

import net.minecraftforge.oredict.OreDictionary;

/**
 * @author dmillerw
 */
public class OreStackWrapper extends GenericStackWrapper<String> {

	public OreStackWrapper(String contents) {
		super(contents);
	}

	@Override
	public GenericStackWrapper<String> copy() {
		return new OreStackWrapper(contents);
	}

	@Override
	public boolean equals(GenericStackWrapper<String> wrapper) {
		if (wrapper.contents.isEmpty() || OreDictionary.getOres(wrapper.contents).isEmpty()) {
			return false;
		}

		return wrapper.contents.equals(contents);
	}

	@Override
	public int hashCode() {
		return contents.hashCode();
	}
}
