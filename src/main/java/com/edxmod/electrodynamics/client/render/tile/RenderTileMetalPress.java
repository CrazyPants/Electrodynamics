package com.edxmod.electrodynamics.client.render.tile;

import com.edxmod.electrodynamics.client.render.WrappedModel;
import com.edxmod.electrodynamics.common.tile.TileMetalPress;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 * @author Royalixor
 */
public class RenderTileMetalPress extends TileEntitySpecialRenderer {

    public static final String RENDER_HANDLE = "MetalPress___Handel_2";

    private static WrappedModel metalPress;

    static {
        metalPress = new WrappedModel("blocks/metalPress.obj", "blocks/metalPress.png");
    }

    private void renderPressAt(TileMetalPress tileMetalPress, double x, double y, double z, float partial) {
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);

        metalPress.bindTexture();
        metalPress.renderAll();

        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partial) {
        renderPressAt((TileMetalPress) tile, x, y, z, partial);
    }
}
