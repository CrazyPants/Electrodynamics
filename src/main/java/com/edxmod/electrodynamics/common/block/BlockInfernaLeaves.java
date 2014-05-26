package com.edxmod.electrodynamics.common.block;

import com.edxmod.electrodynamics.common.core.EDXCreativeTab;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author dmillerw
 */
public class BlockInfernaLeaves extends BlockLeaves {

	public BlockInfernaLeaves() {
		super();

		setCreativeTab(EDXCreativeTab.BLOCKS.get());
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return Blocks.leaves.getIcon(side, meta);
	}

	@Override
	public String[] func_150125_e() {
		return new String[] {getUnlocalizedName()};
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		return 0;
	}

	@Override
	public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		return true;
	}

	@Override
	public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side) {
		return true;
	}
}
