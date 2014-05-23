package com.edxmod.electrodynamics.common.item.prefab;

import com.edxmod.electrodynamics.common.core.EDXCreativeTab;
import com.edxmod.electrodynamics.common.lib.EDXProps;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import scala.annotation.varargs;

/**
 * @author dmillerw
 */
public class ItemEDX extends Item {

	private IIcon icon;

	public ItemEDX(EDXCreativeTab tab) {
		super();

		setCreativeTab(tab.get());
	}

	@Override
	public IIcon getIconFromDamage(int damage) {
		return icon;
	}

	@Override
	public void registerIcons(IIconRegister register) {
		if (!getIcon().isEmpty()) {
			icon = register.registerIcon(EDXProps.RESOURCE_PREFIX + getIcon());
		}
	}

	public String getIcon() {
		return "";
	}

}
