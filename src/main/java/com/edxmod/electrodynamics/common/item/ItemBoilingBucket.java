package com.edxmod.electrodynamics.common.item;

import com.edxmod.electrodynamics.common.core.EDXCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.util.IIcon;

/**
 * @author Thlayli
 */
public class ItemBoilingBucket extends ItemBucket {

    public ItemBoilingBucket(Block block) {
        super(block);
        setCreativeTab(EDXCreativeTab.ITEMS.get());
    }

    @Override
    public void registerIcons(IIconRegister register) {
        itemIcon = Items.water_bucket.getIconFromDamage(0);
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        return itemIcon;
    }
}