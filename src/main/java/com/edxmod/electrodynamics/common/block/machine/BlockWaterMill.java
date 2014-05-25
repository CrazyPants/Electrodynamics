package com.edxmod.electrodynamics.common.block.machine;

import com.edxmod.electrodynamics.common.block.prefab.EDXTileBlock;
import com.edxmod.electrodynamics.common.tile.TileBarrel;
import com.edxmod.electrodynamics.common.tile.TileWaterMill;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author dmillerw
 */
public class BlockWaterMill extends EDXTileBlock {

	public BlockWaterMill() {
		super(Material.wood);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileWaterMill();
	}

	@Override
	public boolean useCustomRender() {
		return true;
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {

	}
}
