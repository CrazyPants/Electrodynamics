package com.edxmod.electrodynamics.common.block.misc;

import com.edxmod.electrodynamics.common.block.prefab.item.EDXTileBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Royalixor
 */
public class BlockPackage extends EDXTileBlock {

    public BlockPackage() {
        super(Material.wood);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return null;
    }

    @Override
    public boolean useCustomRender() {
        return false;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {

    }
}
