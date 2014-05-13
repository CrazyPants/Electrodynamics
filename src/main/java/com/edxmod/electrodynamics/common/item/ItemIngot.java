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
public class ItemIngot extends EDXMultiItem {

    private static String[] ingots = {""};
    private IIcon[] textures;

    public ItemIngot() {
        setMaxDamage(0);
        setHasSubtypes(true);
        setCreativeTab(EDXCreativeTab.ITEMS.get());
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        textures = new IIcon[ingots.length];

        for (int i = 0; i < ingots.length; ++i) {
            textures[i] = iconRegister.registerIcon(EDXProps.RESOURCE_PREFIX + "component/" + ingots[i]);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        int meta = itemStack.getItemDamage();

        if (meta < 0 || meta >= ingots.length) {
            meta = 0;
        }
        return super.getUnlocalizedName() + "." + ingots[meta];
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
        for (int meta = 0; meta < ingots.length; ++meta) {
            list.add(new ItemStack(item, 1, meta));
        }
    }

}
