package com.edxmod.electrodynamics.client.render.tile;

import com.edxmod.electrodynamics.client.lib.Model;
import com.edxmod.electrodynamics.client.render.EDXTileRenderer;
import com.edxmod.electrodynamics.common.tile.machine.TileSieveTable;
import org.lwjgl.opengl.GL11;

/**
 * @author Royalixor
 */
public class RenderTileSieveTable extends EDXTileRenderer<TileSieveTable> {

	public void renderTileAt(TileSieveTable tile, double x, double y, double z, float delta) {
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);

		Model.TABLE_SIEVE.bindTexture();
		Model.TABLE_SIEVE.renderAll();

		GL11.glPopMatrix();
	}
}
