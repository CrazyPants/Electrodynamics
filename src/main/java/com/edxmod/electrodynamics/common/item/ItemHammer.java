package com.edxmod.electrodynamics.common.item;

import com.edxmod.electrodynamics.common.item.prefab.ItemEDX;
import com.edxmod.electrodynamics.common.lib.EDXProps;
import com.edxmod.electrodynamics.common.tabs.EDXCreativeTab;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * @author Royalixor
 */
public class ItemHammer extends ItemEDX {

	public static final float[] STRENGTH = new float[] {1F, 2F, 2.5F, 5F, 10F};

    private IIcon icon;

	private final String type;

	private final Object component;

    public ItemHammer(int damage, String type, Object component) {
		super(EDXCreativeTab.TOOLS);

		setMaxStackSize(1);
		setMaxDamage(damage);
		setUnlocalizedName("hammer_" + type);

		this.type = type;
		this.component = component;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
		icon = iconRegister.registerIcon(EDXProps.RESOURCE_PREFIX + "tools/" + type + "Hammer");
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        return icon;
    }

}
