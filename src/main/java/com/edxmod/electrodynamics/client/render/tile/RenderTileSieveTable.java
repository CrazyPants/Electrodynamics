package com.edxmod.electrodynamics.client.render.tile;

import com.edxmod.electrodynamics.client.render.WrappedModel;
import com.edxmod.electrodynamics.common.tile.TileSieveTable;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 * @author Royalixor
 */
public class RenderTileSieveTable extends TileEntitySpecialRenderer {

    private static WrappedModel sieveTable;

    static {
        sieveTable = new WrappedModel("blocks/sieveTable.obj", "blocks/sieveTable.png");
    }

    private void renderSieveAt(TileSieveTable tileSieveTable, double x, double y, double z, float partial) {
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);

        sieveTable.bindTexture();
        sieveTable.renderAll();

        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partial) {
        renderSieveAt((TileSieveTable) tile, x, y, z, partial);
    }
}
