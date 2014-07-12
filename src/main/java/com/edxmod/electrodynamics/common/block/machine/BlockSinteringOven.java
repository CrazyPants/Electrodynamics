package com.edxmod.electrodynamics.common.block.machine;

import com.edxmod.electrodynamics.common.block.prefab.EDXTileBlock;
import com.edxmod.electrodynamics.common.tile.machine.TileSinteringOven;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Royalixor
 */
public class BlockSinteringOven extends EDXTileBlock {

	public BlockSinteringOven() {
		super(Material.iron);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float fx, float fy, float fz) {
		if (!world.isRemote) {
			if (player.isSneaking()) {
				TileSinteringOven tile = (TileSinteringOven) world.getTileEntity(x, y, z);

				if (tile != null) {
					tile.open = !tile.open;
					tile.sendPoke();
				}
			}
		}

		return player.isSneaking();
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileSinteringOven();
	}

	@Override
	public boolean useCustomRender() {
		return true;
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {

	}
}
