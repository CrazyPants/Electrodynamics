package com.edxmod.electrodynamics.common.item;

import com.edxmod.electrodynamics.common.item.prefab.ItemEDX;
import com.edxmod.electrodynamics.common.lib.EDXProps;
import com.edxmod.electrodynamics.common.core.EDXCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * @author dmillerw
 */
public class ItemNet extends ItemEDX {

	private IIcon icon;

	public ItemNet() {
		super(EDXCreativeTab.TOOLS);

		setMaxDamage(0);
		setMaxStackSize(1);
		setFull3D();
	}

	@Override
	public float getDigSpeed(ItemStack itemstack, Block block, int metadata) {
		return block == Blocks.web ? 25F : super.getDigSpeed(itemstack, block, metadata);
	}

	@Override
	public boolean canHarvestBlock(Block block, ItemStack stack) {
		return block == Blocks.web;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		icon = iconRegister.registerIcon(EDXProps.RESOURCE_PREFIX + "tools/net");
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		return icon;
	}
}
