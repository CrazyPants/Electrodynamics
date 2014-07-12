package com.edxmod.electrodynamics.common.item;

import com.edxmod.electrodynamics.common.block.EDXBlocks;
import com.edxmod.electrodynamics.common.core.EDXCreativeTab;
import com.edxmod.electrodynamics.common.item.prefab.EDXMultiItem;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author dmillerw
 */
public class ItemSeeds extends EDXMultiItem {

	public static final String[] NAMES = new String[]{"grass", "nether_grass"};

	public ItemSeeds() {
		super(EDXCreativeTab.ITEMS);
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			Block block = world.getBlock(x, y, z);

			if (block == Blocks.dirt && stack.getItemDamage() == 0) {
				world.setBlock(x, y, z, Blocks.grass, 0, 3);
				stack.stackSize--;
				return true;
			} else if (block == Blocks.soul_sand && stack.getItemDamage() == 1) {
				world.setBlock(x, y, z, EDXBlocks.netherGrass, 0, 3);
				stack.stackSize--;
				return true;
			}
		}

		return false;
	}

	@Override
	public String[] getNames() {
		return NAMES;
	}

	@Override
	public String getIconPrefix() {
		return "world/seed";
	}
}
