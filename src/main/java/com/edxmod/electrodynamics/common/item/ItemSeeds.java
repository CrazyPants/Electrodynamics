package com.edxmod.electrodynamics.common.item;

import com.edxmod.electrodynamics.common.tabs.EDXCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

/**
 * @author Royalixor
 */
public class ItemSeeds extends Item implements IPlantable {

    private Block blockType;
    private Block soilBlock;

    public ItemSeeds(Block blockType, Block soilBlock) {
        this.blockType = blockType;
        this.soilBlock = soilBlock;

        setCreativeTab(EDXCreativeTab.ITEMS.get());
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return null;
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z) {
        return null;
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
        return 0;
    }
}
