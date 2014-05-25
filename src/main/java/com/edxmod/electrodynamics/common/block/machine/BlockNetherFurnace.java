package com.edxmod.electrodynamics.common.block.machine;

import com.edxmod.electrodynamics.common.block.EDXBlocks;
import com.edxmod.electrodynamics.common.block.prefab.EDXTileBlock;
import com.edxmod.electrodynamics.common.core.EDXCreativeTab;
import com.edxmod.electrodynamics.common.lib.EDXProps;
import com.edxmod.electrodynamics.common.tile.TileNetherFurnace;
import com.edxmod.electrodynamics.common.util.EntityHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;
import java.util.Random;

public class BlockNetherFurnace extends EDXTileBlock {

	private IIcon[] icons;

	public BlockNetherFurnace() {
		super(Material.rock);
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		world.setBlockMetadataWithNotify(x, y, z, EntityHelper.get2DRotation(entity).ordinal(), 2);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileNetherFurnace();
	}

	@Override
	public boolean useCustomRender() {
		return false;
	}

	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		//TODO Tile interaction
		return getIcon(side, world.getBlockMetadata(x, y, z));
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if (side == meta) {
			return icons[2];
		} else if (side == 1) {
			return icons[3];
		} else {
			return icons[0];
		}
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		icons = new IIcon[4];

		icons[0] = iconRegister.registerIcon(EDXProps.RESOURCE_PREFIX + "machine/infernalFurnace_side");
		icons[1] = iconRegister.registerIcon(EDXProps.RESOURCE_PREFIX + "machine/infernalFurnace_front");
		icons[2] = iconRegister.registerIcon(EDXProps.RESOURCE_PREFIX + "machine/infernalFurnace_front_ON");
		icons[3] = iconRegister.registerIcon(EDXProps.RESOURCE_PREFIX + "machine/infernalFurnace_top");
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(this, 1, ForgeDirection.SOUTH.ordinal()));
	}
}