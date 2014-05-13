package com.edxmod.electrodynamics.common.block.world;

import com.edxmod.electrodynamics.common.block.prefab.EDXWorldDecorBlock;
import com.edxmod.electrodynamics.common.tabs.EDXCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IShearable;

import java.util.ArrayList;

/**
 * @author Royalixor
 */
public class BlockPlant extends EDXWorldDecorBlock implements IShearable {

    private static final String[] plantTypes = new String[]{"wormwood", "driedWormwood"};
    private IIcon[] textures;

    public BlockPlant() {
        super(Material.plants);
        setHardness(0.0F);
        setStepSound(Block.soundTypeGrass);
        setTickRandomly(true);
        setCreativeTab(EDXCreativeTab.BLOCKS.get());
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return false;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        return null;
    }
}
