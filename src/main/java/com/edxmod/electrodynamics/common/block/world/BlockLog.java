package com.edxmod.electrodynamics.common.block.world;

import com.edxmod.electrodynamics.common.block.BlockStorage;
import com.edxmod.electrodynamics.common.block.prefab.EDXBasicBlock;
import com.edxmod.electrodynamics.common.lib.EDXProps;
import com.edxmod.electrodynamics.common.tabs.EDXCreativeTab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

/**
 * @author Royalixor
 */
public class BlockLog extends EDXBasicBlock {

    private IIcon[] textures;

    public BlockLog() {
        super(Material.wood);
        setStepSound(BlockStorage.soundTypeWood);
        setCreativeTab(EDXCreativeTab.BLOCKS.get());
        setHardness(2.0F);
        setHarvestLevel("axe", 0);
    }

    @Override
    public boolean useCustomRender() {
        return false;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == 0 || side == 1) {
            return textures[0];
        } else {
            return textures[1];
        }
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        textures = new IIcon[2];

        textures[0] = iconRegister.registerIcon(EDXProps.RESOURCE_PREFIX + "world/rubberWood_top");
        textures[1] = iconRegister.registerIcon(EDXProps.RESOURCE_PREFIX + "world/rubberWood_side");
    }

    @Override
    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    @Override
    public boolean isWood(IBlockAccess world, int x, int y, int z) {
        return true;
    }
}
