package com.edxmod.electrodynamics.client.render.tile;

import com.edxmod.electrodynamics.client.render.EDXTileRenderer;
import com.edxmod.electrodynamics.client.render.WrappedModel;
import com.edxmod.electrodynamics.common.tile.TileMetalPress;
import com.edxmod.electrodynamics.common.util.RenderHelper;
import org.lwjgl.opengl.GL11;

/**
 * @author dmillerw
 */
public class RenderTileMetalPress extends EDXTileRenderer<TileMetalPress> {

	public static WrappedModel metalPress;

	static {
		metalPress = new WrappedModel("blocks/metalPress.obj", "blocks/metalPress.png");
	}

	public void renderTileAt(TileMetalPress tile, double x, double y, double z, float delta) {
		GL11.glPushMatrix();

		GL11.glTranslated(x, y, z);

		GL11.glTranslated(0.5, 0, 0.5);
		GL11.glRotated(RenderHelper.getRotationAngle(tile.orientation), 0, 1, 0);
		GL11.glTranslated(-0.5, 0, -0.5);

		metalPress.bindTexture();
		metalPress.renderAll();

		GL11.glPopMatrix();
	}
}
