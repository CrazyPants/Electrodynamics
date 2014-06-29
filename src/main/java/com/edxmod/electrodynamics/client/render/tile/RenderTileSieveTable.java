package com.edxmod.electrodynamics.client.render.tile;

import com.edxmod.electrodynamics.client.render.EDXTileRenderer;
import com.edxmod.electrodynamics.client.render.WrappedModel;
import com.edxmod.electrodynamics.common.tile.TileSieveTable;
import org.lwjgl.opengl.GL11;

/**
 * @author Royalixor
 */
public class RenderTileSieveTable extends EDXTileRenderer<TileSieveTable> {

    private static WrappedModel sieveTable;

    static {
        sieveTable = new WrappedModel("blocks/sieveTable");
    }

    public void renderTileAt(TileSieveTable tile, double x, double y, double z, float delta) {
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);

        sieveTable.bindTexture();
        sieveTable.renderAll();

        GL11.glPopMatrix();
    }
}
