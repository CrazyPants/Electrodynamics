package com.edxmod.electrodynamics.common.item;

import com.edxmod.electrodynamics.common.item.prefab.EDXMultiItem;
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
public class ItemGrindings extends EDXMultiItem {

    private static String[] grindings = {"iron", "gold", "cobaltite", "chalcopyrite", "galena", "graphite", "magnetite", "nickel", "wolframite", "voidstone", "lithium"};
    private IIcon[] textures;

    public ItemGrindings() {
        setCreativeTab(EDXCreativeTab.ITEMS.get());
        setHasSubtypes(true);
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        textures = new IIcon[grindings.length];

        for (int i = 0; i < grindings.length; ++i) {
            textures[i] = iconRegister.registerIcon(EDXProps.RESOURCE_PREFIX + "component/grindings/" + grindings[i] + "Grindings");
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        int meta = itemStack.getItemDamage();

        if (meta < 0 || meta >= grindings.length) {
            meta = 0;
        }
        return super.getUnlocalizedName() + "." + grindings[meta];
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        if (meta < 0 || meta >= textures.length) {
            meta = 0;
        }
        return textures[meta];
    }

    @Override
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
        for (int meta = 0; meta < grindings.length; ++meta) {
            list.add(new ItemStack(item, 1, meta));
        }
    }
}
