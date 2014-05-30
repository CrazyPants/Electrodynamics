package com.edxmod.electrodynamics.common.block.machine;

import com.edxmod.electrodynamics.common.block.EDXBlocks;
import com.edxmod.electrodynamics.common.block.prefab.EDXTileBlock;
import com.edxmod.electrodynamics.common.tile.TileCoreMachine;
import com.edxmod.electrodynamics.common.tile.TileCrank;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float fx, float fy, float fz) {
		if (player.isSneaking()) {
			ItemStack held = player.getHeldItem();

			if (held == null || Block.getBlockFromItem(held.getItem()) == this) {
				if (!world.isRemote) {
					dropBlockAsItem(world, x, y, z, 0, 0);
					world.setBlockToAir(x, y, z);
				}
				return true;
			}
		} else {
			if (!world.isRemote) {
				TileCrank tile = (TileCrank) world.getTileEntity(x, y, z);
				tile.crank();
			}
			return true;
		}
		return false;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		if (!world.isRemote) {
			TileCrank tile = (TileCrank) world.getTileEntity(x, y, z);

			if (world.getBlock(x + tile.orientation.offsetX, y + tile.orientation.offsetY, z + tile.orientation.offsetZ) != EDXBlocks.hammerMill) {
				dropBlockAsItem(world, x, y, z, 0, 0);
				world.setBlockToAir(x, y, z);
			}
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	@Override
	public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 start, Vec3 end) {
		TileCoreMachine tile = (TileCoreMachine) world.getTileEntity(x, y, z);

		switch (tile.orientation) {
			case SOUTH: setBlockBounds(0.28125F, 0.28125F, 0.625F, 0.71875F, 0.71875F, 1); break;
			case NORTH: setBlockBounds(0.28125F, 0.28125F, 0, 0.71875F, 0.71875F, 0.375F); break;
			case WEST: setBlockBounds(0, 0.28125F, 0.28125F, 0.375F, 0.71875F, 0.71875F); break;
			case EAST: setBlockBounds(0.625F, 0.28125F, 0.28125F, 1, 0.71875F, 0.71875F); break;
			default: setBlockBounds(0, 0, 0, 1, 1, 1); break;
		}

		return super.collisionRayTrace(world, x, y, z, start, end);
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
