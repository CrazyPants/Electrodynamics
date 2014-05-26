package com.edxmod.electrodynamics.common.block;

import com.edxmod.electrodynamics.common.core.EDXCreativeTab;
import com.edxmod.electrodynamics.common.lib.EDXProps;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author dmillerw
 */
public class BlockInfernalLog extends BlockLog {

	private IIcon[] icons;

	public BlockInfernalLog() {
		super();

		setCreativeTab(EDXCreativeTab.BLOCKS.get());
	}

	@Override
	protected IIcon getSideIcon(int meta) {
		return icons[meta];
	}

	@Override
	protected IIcon getTopIcon(int meta) {
		return Blocks.log.getIcon(1, 0);
	}

	@Override
	public void registerBlockIcons(IIconRegister register) {
		icons = new IIcon[2];
		icons[0] = register.registerIcon(EDXProps.RESOURCE_PREFIX + "world/infernalLog");
		icons[1] = register.registerIcon(EDXProps.RESOURCE_PREFIX + "world/infernalLog_rich");
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