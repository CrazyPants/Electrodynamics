package com.edxmod.electrodynamics.common.block;

import com.edxmod.electrodynamics.common.block.prefab.EDXMultiBlock;
import com.edxmod.electrodynamics.common.lib.EDXProps;
import com.edxmod.electrodynamics.common.util.ArrayHelper;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * @author dmillerw
 */
public class BlockComponentWood extends EDXMultiBlock {

	public static final String[] NAMES = new String[] {"sawdust", "sawdust_nether", "sawdust_nether_glow", "woodchips", "woodchips_nether", "woodchips_nether_glow"};
	public IIcon[] icons;

	public BlockComponentWood() {
		super(Material.wood, 2F, 2F);

		setStepSound(soundTypeWood);
		setHarvestLevel("axe", 0);
	}

	@Override
	public int[] getSubtypes() {
		return ArrayHelper.getArrayIndexes(NAMES.length); // Forces all aspects of this block to base themselves off the NAMES array
	}

	@Override
	public String getNameForType(int type) {
		return NAMES[type];
	}

	@Override
	public boolean useCustomRender() {
		return false;
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if (meta < 0 || meta >= NAMES.length) {
			meta = 0;
		}

		return icons[meta];
	}

	@Override
	public void registerBlockIcons(IIconRegister register) {
		icons = new IIcon[NAMES.length];
		for (int i=0; i<NAMES.length; i++) {
			icons[i] = register.registerIcon(EDXProps.RESOURCE_PREFIX + "component/" + NAMES[i]);
		}
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
		for (int meta = 0; meta < NAMES.length; ++meta) {
			list.add(new ItemStack(item, 1, meta));
		}
	}
}
