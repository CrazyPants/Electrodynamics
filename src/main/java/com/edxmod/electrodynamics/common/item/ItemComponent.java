package com.edxmod.electrodynamics.common.item;

import com.edxmod.electrodynamics.common.core.EDXCreativeTab;
import com.edxmod.electrodynamics.common.item.prefab.EDXMultiItem;

/**
 * @author dmillerw
 */
public class ItemComponent extends EDXMultiItem {

	private static final String[] NAMES = {"twine", "twine_mesh"};

	public ItemComponent() {
		super(EDXCreativeTab.ITEMS);

		setHasSubtypes(true);
	}

	@Override
	public String[] getNames() {
		return NAMES;
	}

	@Override
	public String getIconPrefix() {
		return "component";
	}
}
