package com.edxmod.electrodynamics.common.block;

import com.edxmod.electrodynamics.common.core.EDXCreativeTab;
import net.minecraft.block.BlockLog;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author dmillerw
 */
public class BlockInfernalLog extends BlockLog {

	public BlockInfernalLog() {
		super();

		setCreativeTab(EDXCreativeTab.BLOCKS.get());
	}

	@Override
	protected IIcon getSideIcon(int value) {
		return Blocks.log.getIcon(2, 0);
	}

	@Override
	protected IIcon getTopIcon(int value) {
		return Blocks.log.getIcon(1, 0);
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
