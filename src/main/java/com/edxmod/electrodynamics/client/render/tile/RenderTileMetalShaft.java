package com.edxmod.electrodynamics.client.render.tile;

import com.edxmod.electrodynamics.client.lib.Model;
import com.edxmod.electrodynamics.client.render.EDXTileRenderer;
import com.edxmod.electrodynamics.common.tile.TileMetalShaft;
import com.edxmod.electrodynamics.common.util.RenderHelper;
import org.lwjgl.opengl.GL11;

/**
 * @author dmillerw
 */
public class RenderTileMetalShaft extends EDXTileRenderer<TileMetalShaft> {

	@Override
	public void renderTileAt(TileMetalShaft tile, double x, double y, double z, float delta) {
		GL11.glPushMatrix();

		GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);

		GL11.glRotated(RenderHelper.getRotationAngle(tile.orientation), 0, 1, 0);
		float angle = tile.angle;

		if (tile.flowLeft) {
			angle = -angle;
		}

		GL11.glRotated(angle, 1, 0, 0);

		Model.METAL_SHAFT.bindTexture();
		Model.METAL_SHAFT.renderAll();

		GL11.glPopMatrix();
	}
}
