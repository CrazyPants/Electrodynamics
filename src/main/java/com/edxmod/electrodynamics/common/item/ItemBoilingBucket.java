package com.edxmod.electrodynamics.common.item;

import com.edxmod.electrodynamics.common.lib.EDXProps;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.util.IIcon;

/**
 * Created by Thlayli on 16/07/2014.
 */
public class ItemBoilingBucket extends ItemBucket {

    public ItemBoilingBucket(Block p_i45331_1_) {
        super(p_i45331_1_);
    }


    @Override
    public void registerIcons(IIconRegister register) {
            itemIcon = Items.water_bucket.getIconFromDamage(0);
    }

    public IIcon getIconFromDamage(int damage) {
        return itemIcon;
    }


}
