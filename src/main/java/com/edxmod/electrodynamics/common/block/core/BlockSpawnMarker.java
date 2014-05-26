package com.edxmod.electrodynamics.common.block.core;

import com.edxmod.electrodynamics.common.core.EDXCreativeTab;
import com.edxmod.electrodynamics.common.core.data.WorldDataSpawnPosition;
import com.edxmod.electrodynamics.common.util.EntityHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

/**
 * @author dmillerw
 */
public class BlockSpawnMarker extends Block {

	public static ChunkCoordinates getSpawnPosition(World world) {
		if (world.perWorldStorage.loadData(WorldDataSpawnPosition.class, "edx:spawn_position") == null) {
			world.perWorldStorage.setData("edx:spawn_position", new WorldDataSpawnPosition());
		}
		return ((WorldDataSpawnPosition)world.perWorldStorage.loadData(WorldDataSpawnPosition.class, "edx:spawn_position")).spawn;
	}

	public static void setSpawnPosition(World world, ChunkCoordinates chunkCoordinates) {
		WorldDataSpawnPosition spawnPosition = new WorldDataSpawnPosition();
		spawnPosition.spawn = chunkCoordinates;

		world.perWorldStorage.setData("edx:spawn_position", spawnPosition);
	}

	public BlockSpawnMarker() {
		super(Material.rock);

		setBlockUnbreakable();
		setCreativeTab(EDXCreativeTab.BLOCKS.get());
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		if (!world.isRemote && world.provider.dimensionId == -1) {
			setSpawnPosition(world, BlockBed.func_149977_a(world, x, y, z, 0));
		}
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		world.setBlockMetadataWithNotify(x, y, z, EntityHelper.get2DRotation(entity).ordinal(), 3);
	}
}
