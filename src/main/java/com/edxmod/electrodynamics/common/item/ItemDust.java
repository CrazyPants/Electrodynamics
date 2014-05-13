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
 * @author dmillerw
 */
public class ItemDust extends EDXMultiItem {

    private static String[] dusts = {"clay", "cobalt", "cobaltHexahydrate", "copper", "graphite", "lead", "limePure", "limestone", "lithium", "magnetite", "nickel", "silicon", "sulfur", "tellurium", "tungsten", "voidstone"};
    private IIcon[] textures;

    public ItemDust() {
        setCreativeTab(EDXCreativeTab.ITEMS.get());
        setHasSubtypes(true);
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        textures = new IIcon[dusts.length];

        for (int i = 0; i < dusts.length; ++i) {
            String dust = "dust" + Character.toUpperCase(dusts[i].charAt(0)) + dusts[i].substring(1);
            textures[i] = iconRegister.registerIcon(EDXProps.RESOURCE_PREFIX + "component/dusts/" + dust);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        int meta = itemStack.getItemDamage();

        if (meta < 0 || meta >= dusts.length) {
            meta = 0;
        }
        return super.getUnlocalizedName() + "." + dusts[meta];
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
        for (int meta = 0; meta < dusts.length; ++meta) {
            list.add(new ItemStack(item, 1, meta));
        }
    }

}
