package com.edxmod.electrodynamics.common.block.machine;

import com.edxmod.electrodynamics.common.block.prefab.EDXTileBlock;
import com.edxmod.electrodynamics.common.tile.TileCrank;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author dmillerw
 */
public class BlockCrank extends EDXTileBlock {

	public BlockCrank() {
		super(Material.iron);

		setHardness(2F);
		setResistance(2F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileCrank();
	}

	@Override
	public boolean useCustomRender() {
		return true;
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {

	}
}
