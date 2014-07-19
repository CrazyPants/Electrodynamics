package com.edxmod.electrodynamics.client.render.tile;

import com.edxmod.electrodynamics.client.lib.Model;
import com.edxmod.electrodynamics.client.render.EDXTileRenderer;
import com.edxmod.electrodynamics.common.tile.misc.TileSpawnMarker;
import com.edxmod.electrodynamics.common.util.RenderHelper;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

/**
 * @author dmillerw
 */
public class RenderTileSpawnMarker extends EDXTileRenderer<TileSpawnMarker> {

    public void renderTileAt(TileSpawnMarker tile, double x, double y, double z, float delta) {
        GL11.glPushMatrix();

        GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);

        GL11.glRotated(RenderHelper.getRotationAngle(ForgeDirection.getOrientation(tile.getBlockMetadata())), 0, 1, 0);

        Model.SPAWN_MARKER.bindTexture();
        Model.SPAWN_MARKER.renderAll();

        GL11.glPopMatrix();
    }
}
