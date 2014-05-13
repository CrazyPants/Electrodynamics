package com.edxmod.electrodynamics.client.render.tile;

import com.edxmod.electrodynamics.client.render.WrappedModel;
import com.edxmod.electrodynamics.common.tile.TileBasicKiln;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 * @author Royalixor
 */
public class RenderTileBasicKiln extends TileEntitySpecialRenderer {

    public static final String RENDER_DOOR = "KilnDoor";

    private static WrappedModel basicKiln;

    static {
        basicKiln = new WrappedModel("blocks/basicKiln.obj", "blocks/basicKiln.png");
    }

    private void renderKilnAt(TileBasicKiln tileBasicKiln, double x, double y, double z, float partial) {
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);

        basicKiln.bindTexture();
        basicKiln.renderAllExcept(RENDER_DOOR);

        // Essentially this sets the pivot point for the next rotation
        GL11.glTranslated(0.0625F * 2, 0, 1 - (0.0625F / 2));

        GL11.glRotated(-tileBasicKiln.currentAngle, 0, 1, 0);

        // We then reverse that translation to keep the actual render at the proper point
        GL11.glTranslated(-(0.0625F * 2), 0, -1 + (0.0625F / 2));

        basicKiln.renderOnly(RENDER_DOOR);

        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partial) {
        renderKilnAt((TileBasicKiln) tile, x, y, z, partial);
    }
}
