package com.edxmod.electrodynamics.client.render.tile;

import com.edxmod.electrodynamics.client.render.WrappedModel;
import com.edxmod.electrodynamics.common.tile.TileSinteringOven;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 * @author Royalixor
 */
public class RenderTileSinteringOven extends TileEntitySpecialRenderer {

    private static WrappedModel sinteringOven;

    static {
        sinteringOven = new WrappedModel("blocks/sinteringOven.obj", "blocks/sinteringOven.png");
    }

    private void renderOvenAt(TileSinteringOven tileSinteringOven, double x, double y, double z, float partial) {
        GL11.glPushMatrix();

        // Required for any models with partially transparent textures
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);

        GL11.glTranslated(x, y, z);

        sinteringOven.bindTexture();
        sinteringOven.renderAllExcept("Door", "Glass");

        // Essentially this sets the pivot point for the next rotation
        GL11.glTranslated(0.0625F / 2, 0, 1 - (0.0625F / 2));

        GL11.glRotated(-tileSinteringOven.currentAngle, 0, 1, 0);

        // We then reverse that translation to keep the actual render at the proper point
        GL11.glTranslated(-(0.0625F / 2), 0, -1 + (0.0625F / 2));

        sinteringOven.renderOnly("Door", "Glass");

        GL11.glDisable(GL11.GL_BLEND);

        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partial) {
        renderOvenAt((TileSinteringOven) tile, x, y, z, partial);
    }
}
