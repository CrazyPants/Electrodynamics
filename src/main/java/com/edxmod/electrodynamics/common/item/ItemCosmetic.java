package com.edxmod.electrodynamics.common.item;

import com.edxmod.electrodynamics.common.core.EDXCreativeTab;
import com.edxmod.electrodynamics.common.item.prefab.EDXMultiItem;

/**
 * @author dmillerw
 */
public class ItemCosmetic extends EDXMultiItem {

	public static final String[] NAMES = new String[] {"corruption", "righteousness"};

	public ItemCosmetic() {
		super(EDXCreativeTab.ITEMS);

		setHasSubtypes(true);
		setMaxStackSize(1);
		setMaxDamage(0);
	}

	@Override
	public String[] getNames() {
		return NAMES;
	}

	@Override
	public String getIconPrefix() {
		return "heart";
	}
}
