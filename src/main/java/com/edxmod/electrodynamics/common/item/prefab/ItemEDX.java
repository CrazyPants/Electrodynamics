package com.edxmod.electrodynamics.common.item.prefab;

import com.edxmod.electrodynamics.common.tabs.EDXCreativeTab;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

/**
 * @author dmillerw
 */
public class ItemEDX extends Item {

	public ItemEDX(EDXCreativeTab tab) {
		super();

		setCreativeTab(tab.get());
	}

	@Override
	public void registerIcons(IIconRegister register) {

	}
}
