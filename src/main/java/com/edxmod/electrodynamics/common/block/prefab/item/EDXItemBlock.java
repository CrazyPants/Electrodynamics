package com.edxmod.electrodynamics.common.block.prefab.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

/**
 * @author Royalixor
 */
public class EDXItemBlock extends ItemBlock {

	public EDXItemBlock(Block block) {
		super(block);
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}
}
