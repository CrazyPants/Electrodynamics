package com.edxmod.electrodynamics.common.block.prefab.item;

import com.edxmod.electrodynamics.common.block.prefab.EDXMultiBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

/**
 * Created by Thlayli
 */
public class EDXMultiMachineBlock extends EDXMachineBlock {

    public EDXMultiMachineBlock(Block block) {
        super(block);
        setHasSubtypes(true);
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
