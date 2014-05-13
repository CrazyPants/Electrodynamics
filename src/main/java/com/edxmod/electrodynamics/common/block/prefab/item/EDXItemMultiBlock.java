package com.edxmod.electrodynamics.common.block.prefab.item;

import com.edxmod.electrodynamics.common.block.prefab.EDXMultiBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * @author dmillerw
 */
public class EDXItemMultiBlock extends ItemBlock {

    public EDXItemMultiBlock(Block block) {
        super(block);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + "." + ((EDXMultiBlock) this.field_150939_a).getNameForType(stack.getItemDamage());
    }

}
