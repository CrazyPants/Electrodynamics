package com.edxmod.electrodynamics.common.item.resource;

import com.edxmod.electrodynamics.common.core.EDXCreativeTab;
import com.edxmod.electrodynamics.common.item.prefab.EDXMultiItem;
import com.edxmod.electrodynamics.common.lib.EDXProps;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

/**
 * @author dmillerw
 */
public class ItemResource extends EDXMultiItem {

	public static final String[] NAMES = new String[] {"copper", "gold", "graphite", "iron", "lead", "nickel", "silver", "steel", "tin"};

	private String type;

	public ItemResource setType(String type) {
		setUnlocalizedName("resource." + type.toLowerCase());
		this.type = type;
		return this;
	}

	public ItemResource() {
		super(EDXCreativeTab.ITEMS);
	}

	@Override
	public void registerIcons(IIconRegister register) {
		icons = new IIcon[getNames().length];
		if (!getIconPrefix().isEmpty()) {
			for (int i=0; i<getNames().length; i++) {
				icons[i] = register.registerIcon(EDXProps.RESOURCE_PREFIX + getIconPrefix() + "/" + getNames()[i] + type);
			}
		}
	}

	@Override
	public String[] getNames() {
		return NAMES;
	}

	public String getIconPrefix() {
		return "resource";
	}

}
