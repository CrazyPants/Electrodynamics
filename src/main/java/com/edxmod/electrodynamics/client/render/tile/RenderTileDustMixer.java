package com.edxmod.electrodynamics.client.render.tile;

import com.edxmod.electrodynamics.client.render.WrappedModel;
import com.edxmod.electrodynamics.common.tile.TileDustMixer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 * @author Royalixor
 */
public class RenderTileDustMixer extends TileEntitySpecialRenderer {

    public static final String RENDER_MIXER = "Dustmixer___Mixer_1";
    public static final String RENDER_LID = "Dustmixer___Lid_1";
    public static final String RENDER_GLASS = "Dustmixer___Glass_1";

    private static WrappedModel dustMixer;

    static {
        dustMixer = new WrappedModel("blocks/dustMixer.obj", "blocks/dustMixer.png");
    }

    private void renderMixerAt(TileDustMixer tileDustMixer, double x, double y, double z, float partial) {
        GL11.glPushMatrix();

        // Required for any models with partially transparent textures
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);

        GL11.glTranslated(x, y, z);

        dustMixer.bindTexture();
        dustMixer.renderAllExcept(RENDER_MIXER, RENDER_GLASS, RENDER_LID);

        GL11.glPushMatrix();

        if (tileDustMixer.isActive()) {
            float rotation = (((float) tileDustMixer.getWorldObj().getTotalWorldTime()) / 2.5F) * (180F / (float) Math.PI);

            GL11.glTranslated(0.5, 0, 0.5);
            GL11.glRotated(rotation, 0, 1, 0);
            GL11.glTranslated(-0.5, 0, -0.5);
        }

        // Render mixer first
        dustMixer.renderOnly(RENDER_MIXER);

        GL11.glPopMatrix();

        // Lid animation
        GL11.glTranslated(0, 1 - (0.0625F * 2), 0.0625F * 2);
        GL11.glRotated(-tileDustMixer.currentAngle, 1, 0, 0);
        GL11.glTranslated(0, -1 + (0.0625F * 2), -(0.0625F * 2));

        GL11.glPushMatrix();
        GL11.glTranslated(0, -(0.0625F / 2), 0);
        dustMixer.renderOnly(RENDER_GLASS, RENDER_LID);
        GL11.glPopMatrix();

        GL11.glDisable(GL11.GL_BLEND);

        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partial) {
        renderMixerAt((TileDustMixer) tile, x, y, z, partial);
    }
}
