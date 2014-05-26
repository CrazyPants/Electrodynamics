package com.edxmod.electrodynamics.common.block;

import com.edxmod.electrodynamics.common.core.EDXCreativeTab;
import com.edxmod.electrodynamics.common.world.generation.WorldGenInfernalTree;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.IGrowable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author dmillerw
 */
public class BlockInfernalSapling extends BlockBush implements IGrowable {

	public BlockInfernalSapling() {
		setBlockBounds(0.5F - 0.4F, 0.0F, 0.5F - 0.4F, 0.5F + 0.4F, 0.4F * 2.0F, 0.5F + 0.4F);
		setCreativeTab(EDXCreativeTab.BLOCKS.get());
	}

	public void updateTick(World world, int x, int y, int z, Random random) {
		if (!world.isRemote) {
			super.updateTick(world, x, y, z, random);

			if (world.getBlockLightValue(x, y + 1, z) >= 9 && random.nextInt(7) == 0) {
				grow(world, x, y, z, random);
			}
		}
	}

	@Override
	protected boolean canPlaceBlockOn(Block block) {
		return block == EDXBlocks.netherGrass;
	}

	public void grow(World world, int x, int y, int z, Random random) {
		WorldGenInfernalTree worldGenInfernalTree = new WorldGenInfernalTree(true);

		if (worldGenInfernalTree.generate(world, random, x, y, z)) {
			world.setBlock(x, y, z, EDXBlocks.infernalLog);
		}
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return Blocks.sapling.getIcon(side, meta);
	}

	/* IGROWABLE */
	@Override
	public boolean func_149851_a(World world, int x, int y, int z, boolean server) {
		return true;
	}

	@Override
	public boolean func_149852_a(World world, Random random, int x, int y, int z) {
		return random.nextDouble() < 0.45D;
	}

	@Override
	public void func_149853_b(World world, Random random, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);

		if ((meta & 8) == 0) {
			world.setBlockMetadataWithNotify(x, y, z, meta | 8, 4);
		} else {
			grow(world, x, y, z, random);
		}
	}

}
