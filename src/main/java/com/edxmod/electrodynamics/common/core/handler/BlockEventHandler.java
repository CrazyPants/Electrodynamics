package com.edxmod.electrodynamics.common.core.handler;

import com.edxmod.electrodynamics.common.item.EDXItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

/**
 * @author dmillerw
 */
public class BlockEventHandler {

	@SubscribeEvent
	public void onBlockHarvested(BlockEvent.HarvestDropsEvent event) {
		if (event.block == Blocks.web) {
			if (event.harvester != null) {
				EntityPlayer player = event.harvester;

				if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == EDXItems.net) {
					event.drops.clear();
					event.drops.add(new ItemStack(Blocks.web));
					event.dropChance = 1F;
				}
			}
		}
	}
}
