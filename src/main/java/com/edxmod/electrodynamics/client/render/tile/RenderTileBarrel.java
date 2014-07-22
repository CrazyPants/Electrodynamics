package com.edxmod.electrodynamics.client.render.tile;

import com.edxmod.electrodynamics.client.lib.Model;
import com.edxmod.electrodynamics.client.render.EDXTileRenderer;
import com.edxmod.electrodynamics.common.lib.MathFX;
import com.edxmod.electrodynamics.common.tile.TileBarrel;
import com.edxmod.electrodynamics.common.tile.machine.TileCompost;
import org.lwjgl.opengl.GL11;

/**
 * Created by Thlayli
 */
public class RenderTileBarrel extends EDXTileRenderer<TileBarrel> {

    @Override
    public void renderTileAt(TileBarrel tile, double x, double y, double z, float delta) {

        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);

        Model.BARREL_WOOD.bindTexture();
        Model.BARREL_WOOD.renderAll();

        GL11.glPopMatrix();
    }
}
