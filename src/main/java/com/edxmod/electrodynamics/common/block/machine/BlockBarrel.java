package com.edxmod.electrodynamics.common.block.machine;

import com.edxmod.electrodynamics.common.block.prefab.EDXTileBlock;
import com.edxmod.electrodynamics.common.tile.machine.TileBarrel;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author dmillerw
 */
public class BlockBarrel extends EDXTileBlock {

	public BlockBarrel() {
		super(Material.wood);

		setBlockBounds(0.0625F, 0, 0.0625F, 0.9375F, 1, 0.9375F);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float fx, float fy, float fz) {
		if (!world.isRemote) {
			TileBarrel tile = (TileBarrel) world.getTileEntity(x, y, z);

			if (tile != null) {
				if (player.isSneaking()) {
					tile.hasLid = !tile.hasLid;
					tile.markForUpdate();
				} else {
					if (!tile.hasLid) {
						if (!tile.addItem(player, player.getHeldItem())) {
							if (!tile.interact(player, player.getHeldItem())) {
								return tile.removeItem(player);
							}
						}
					}
				}

				return true;
			}
		}

		return !player.isSneaking();
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileBarrel();
	}

	@Override
	public boolean useCustomRender() {
		return true;
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {

	}
}
