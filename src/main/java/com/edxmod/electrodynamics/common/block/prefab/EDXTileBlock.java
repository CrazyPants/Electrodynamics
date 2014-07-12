package com.edxmod.electrodynamics.common.block.prefab;

import com.edxmod.electrodynamics.common.core.EDXCreativeTab;
import com.edxmod.electrodynamics.common.tile.core.TileCore;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Royalixor
 */
public abstract class EDXTileBlock extends EDXBasicBlock implements ITileEntityProvider {

	public EDXTileBlock(Material material) {
		super(material, 2F, 2F);
		setCreativeTab(EDXCreativeTab.BLOCKS.get());
	}

	@Override
	public abstract TileEntity createNewTileEntity(World world, int meta);

	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
		super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);

		TileCore tile = (TileCore) p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);

		if (tile != null) {
			tile.onBlockBroken();
		}

		p_149749_1_.removeTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
	}

	public boolean onBlockEventReceived(World p_149696_1_, int p_149696_2_, int p_149696_3_, int p_149696_4_, int p_149696_5_, int p_149696_6_) {
		super.onBlockEventReceived(p_149696_1_, p_149696_2_, p_149696_3_, p_149696_4_, p_149696_5_, p_149696_6_);
		TileEntity tileentity = p_149696_1_.getTileEntity(p_149696_2_, p_149696_3_, p_149696_4_);
		return tileentity != null ? tileentity.receiveClientEvent(p_149696_5_, p_149696_6_) : false;
	}
}
