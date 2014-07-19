package com.edxmod.electrodynamics.common.item;

import com.edxmod.electrodynamics.common.item.prefab.EDXItem;
import com.edxmod.electrodynamics.common.core.EDXCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * @author dmillerw
 */
public class ItemNet extends EDXItem {

    public ItemNet() {
        super(EDXCreativeTab.TOOLS);

        setMaxDamage(0);
        setMaxStackSize(1);
    }

    @Override
    public float getDigSpeed(ItemStack itemstack, Block block, int metadata) {
        return block == Blocks.web ? 25F : super.getDigSpeed(itemstack, block, metadata);
    }

    @Override
    public boolean canHarvestBlock(Block block, ItemStack stack) {
        return block == Blocks.web;
    }

    @Override
    public String getIcon() {
        return "tools/net";
    }
}
