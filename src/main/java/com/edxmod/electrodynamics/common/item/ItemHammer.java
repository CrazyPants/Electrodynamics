package com.edxmod.electrodynamics.common.item;

import com.edxmod.electrodynamics.common.item.prefab.EDXItem;
import com.edxmod.electrodynamics.common.core.EDXCreativeTab;

/**
 * @author Royalixor
 */
public class ItemHammer extends EDXItem {

	public static final float[] STRENGTH = new float[] {1F, 2F, 2.5F, 5F, 10F};

	private final String type;

	private final Object component;

	public ItemHammer(int damage, String type, Object component) {
		super(EDXCreativeTab.TOOLS);

		setMaxStackSize(1);
		setMaxDamage(damage);
		setUnlocalizedName("hammer_" + type);
		setFull3D();

		this.type = type;
		this.component = component;
	}

	@Override
	public String getIcon() {
		return "tools/" + type + "Hammer";
	}
}
