package com.edxmod.electrodynamics.common.item.prefab;

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
public abstract class EDXMultiItem extends Item {

    public EDXMultiItem() {
        setCreativeTab(EDXCreativeTab.ITEMS.get());
        setHasSubtypes(true);
    }

    @Override
    public abstract String getUnlocalizedName(ItemStack itemStack);

    @Override
    public abstract void registerIcons(IIconRegister iconRegister);

    @Override
    public abstract IIcon getIconFromDamage(int meta);

    @Override
    public abstract void getSubItems(Item item, CreativeTabs creativeTabs, List list);
}
