package com.edxmod.electrodynamics.client.render.tile;

import com.edxmod.electrodynamics.client.render.EDXTileRenderer;
import com.edxmod.electrodynamics.client.render.WrappedModel;
import com.edxmod.electrodynamics.common.lib.MathFX;
import com.edxmod.electrodynamics.common.tile.TileSinteringOven;
import com.edxmod.electrodynamics.common.util.RenderHelper;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;

/**
 * @author Royalixor
 */
public class RenderTileSinteringOven extends EDXTileRenderer<TileSinteringOven> {

	public static final String PART_DOOR = "VIFS002";
	public static final String PART_GLASS = "VIFS003";

    private static WrappedModel sinteringOven;

    static {
        sinteringOven = new WrappedModel("blocks/sinteringOven.obj", "blocks/sinteringOven.png");
    }

    public void renderTileAt(TileSinteringOven tile, double x, double y, double z, float delta) {
        GL11.glPushMatrix();

        // Required for any models with partially transparent textures
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);

        GL11.glTranslated(x, y, z);

		GL11.glTranslated(0.5, 0, 0.5);
		GL11.glRotated(RenderHelper.getRotationAngle(tile.orientation), 0, 1, 0);
		GL11.glTranslated(-0.5, 0, -0.5);

		sinteringOven.bindTexture();

        sinteringOven.renderAllExcept(PART_DOOR, PART_GLASS);

        // Essentially this sets the pivot point for the next rotation
        GL11.glTranslated(0.0625F / 2, 0, 1 - (0.0625F / 2));

		float percent = tile.currentAngle / 90F;
		float sinerp = MathFX.sinerp(0, 1, percent);

        GL11.glRotated((-sinerp) * 90F, 0, 1, 0);

        // We then reverse that translation to keep the actual render at the proper point
        GL11.glTranslated(-(0.0625F / 2), 0, -1 + (0.0625F / 2));

        sinteringOven.renderOnly(PART_DOOR, PART_GLASS);

        GL11.glDisable(GL11.GL_BLEND);

        GL11.glPopMatrix();
    }
}
