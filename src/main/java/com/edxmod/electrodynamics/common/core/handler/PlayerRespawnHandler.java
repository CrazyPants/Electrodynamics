package com.edxmod.electrodynamics.common.core.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author dmillerw
 */
public class PlayerRespawnHandler {

	@SubscribeEvent
	public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
		EntityPlayer player = event.player;
		World world = player.worldObj;

		world.createExplosion(player, player.posX, player.posY, player.posZ, 4.0F, true);
	}

}
