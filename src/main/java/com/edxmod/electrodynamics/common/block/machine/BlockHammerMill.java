package com.edxmod.electrodynamics.common.block.machine;

import com.edxmod.electrodynamics.common.block.prefab.EDXTileBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author dmillerw
 */
public class BlockHammerMill extends EDXTileBlock {

	public BlockHammerMill() {
		super(Material.iron);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

	@Override
	public boolean useCustomRender() {
		return true;
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {

	}
}
