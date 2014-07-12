package com.edxmod.electrodynamics.common.block;

import com.edxmod.electrodynamics.common.core.EDXCreativeTab;
import com.edxmod.electrodynamics.common.lib.EDXProps;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author dmillerw
 */
public class BlockInfernalLog extends BlockLog {

	private IIcon[] icons;

	private boolean rich;

	public BlockInfernalLog(boolean rich) {
		super();

		this.rich = rich;

		setCreativeTab(EDXCreativeTab.BLOCKS.get());
	}

	@Override
	protected IIcon getSideIcon(int meta) {
		return icons[rich ? 1 : 0];
	}

	@Override
	protected IIcon getTopIcon(int meta) {
		return icons[2];
	}

	@Override
	public void registerBlockIcons(IIconRegister register) {
		icons = new IIcon[3];
		icons[0] = register.registerIcon(EDXProps.RESOURCE_PREFIX + "world/infernalLog");
		icons[1] = register.registerIcon(EDXProps.RESOURCE_PREFIX + "world/infernalLog_rich");
		icons[2] = register.registerIcon(EDXProps.RESOURCE_PREFIX + "world/infernalLog_top");
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
