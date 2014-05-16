package com.edxmod.electrodynamics.client.render.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraftforge.client.event.RenderWorldLastEvent;

/**
 * @author dmillerw
 */
public class RenderEventHandler {

	@SubscribeEvent
	public void onWorldRender(RenderWorldLastEvent event) {
		RenderBlocks renderBlocks = new RenderBlocks(Minecraft.getMinecraft().theWorld);
		renderBlocks.setRenderBounds(0, 0, 0, 0, 0, 0);
		renderBlocks.renderStandardBlock(Blocks.stone, 0, 0, 0);
		renderBlocks.setRenderBounds(0, 0, 0, 1, 1, 1);
	}

}
