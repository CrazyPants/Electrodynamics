package com.edxmod.electrodynamics.client.render.item;

import com.edxmod.electrodynamics.client.lib.Model;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * @author Royalixor
 */
public class RenderItemSpawnMarker implements IItemRenderer {

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();

		GL11.glRotated(180, 0, 1, 0);

		if (type == ItemRenderType.ENTITY) {
			GL11.glTranslated(-0.5, 0, -0.5);
		}

		if (type == ItemRenderType.INVENTORY) {
			GL11.glTranslated(0.1, 0, 0.1);
		}

		Model.SPAWN_MARKER.bindTexture();
		Model.SPAWN_MARKER.renderAll();

		GL11.glPopMatrix();
	}
}
