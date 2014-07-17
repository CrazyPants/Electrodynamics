package com.edxmod.electrodynamics.common.block.machine;

import com.edxmod.electrodynamics.common.block.prefab.EDXTileMultiBlock;
import com.edxmod.electrodynamics.common.tile.machine.TileBarrel;
import com.edxmod.electrodynamics.common.util.ArrayHelper;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author dmillerw
 */
public class BlockBarrel extends EDXTileMultiBlock {

	public static final String[] NAMES = new String[] {"wood", "stone"};

	public BlockBarrel() {
		super(Material.wood);

		setBlockBounds(0.0625F, 0, 0.0625F, 0.9375F, 1, 0.9375F);
	}

	@Override
	public int[] getSubtypes() {
		return ArrayHelper.getArrayIndexes(NAMES);
	}

	@Override
	public String getNameForType(int type) {
		return ArrayHelper.safeGetArrayIndex(NAMES, type);
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
	public void getSubBlocks(Item block, CreativeTabs tab, List list) {
		for (int i = 0; i < NAMES.length; i++) {
			list.add(new ItemStack(this, 1, i));
		}
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
