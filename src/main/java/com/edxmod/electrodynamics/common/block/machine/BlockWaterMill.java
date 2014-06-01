package com.edxmod.electrodynamics.common.block.machine;

import com.edxmod.electrodynamics.common.block.EDXBlocks;
import com.edxmod.electrodynamics.common.block.prefab.EDXTileBlock;
import com.edxmod.electrodynamics.common.tile.TileBarrel;
import com.edxmod.electrodynamics.common.tile.TileCrank;
import com.edxmod.electrodynamics.common.tile.TileWaterMill;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * @author dmillerw
 */
public class BlockWaterMill extends EDXTileBlock {

	public BlockWaterMill() {
		super(Material.wood);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		if (!world.isRemote) {
			TileWaterMill tile = (TileWaterMill) world.getTileEntity(x, y, z);

			if (world.getBlock(x + tile.orientation.offsetX, y + tile.orientation.offsetY, z + tile.orientation.offsetZ) != EDXBlocks.kineticCrank) {
				dropBlockAsItem(world, x, y, z, 0, 0);
				world.setBlockToAir(x, y, z);
			}
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null; //TODO Large bounding box
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
