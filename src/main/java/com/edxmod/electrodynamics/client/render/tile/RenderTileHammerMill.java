package com.edxmod.electrodynamics.client.render.tile;

import com.edxmod.electrodynamics.client.render.WrappedModel;
import com.edxmod.electrodynamics.common.tile.TileHammerMill;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 * @author Royalixor
 */
public class RenderTileHammerMill extends TileEntitySpecialRenderer {

    public static final String RENDER_THING = "HammerMill___Grinder_1";

    private static WrappedModel hammerMill;

    static {
        hammerMill = new WrappedModel("blocks/hammerMill.obj", "blocks/hammerMill.png");
    }

    private void renderMillAt(TileHammerMill tileHammerMill, double x, double y, double z, float partial) {
        GL11.glPushMatrix();

        GL11.glTranslated(x, y, z);

        hammerMill.bindTexture();
        hammerMill.renderAllExcept(RENDER_THING);

        float rotation = (((float) tileHammerMill.getWorldObj().getTotalWorldTime()) / 2F) * (180F / (float) Math.PI);

        // First we move the pivot point
        GL11.glTranslated(0, 0.55, 0.55);
        GL11.glRotated(rotation, 1, 0, 0);
        GL11.glTranslated(0, -0.55, -0.55);

        // Then we move the object in relation to that pivot point
        GL11.glPushMatrix();
        GL11.glTranslated(0, 0.02, 0);
        hammerMill.renderOnly(RENDER_THING);
        GL11.glPopMatrix();

        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partial) {
        renderMillAt((TileHammerMill) tile, x, y, z, partial);
    }
}
