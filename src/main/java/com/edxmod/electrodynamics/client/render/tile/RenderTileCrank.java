package com.edxmod.electrodynamics.client.render.tile;

import com.edxmod.electrodynamics.client.render.EDXTileRenderer;
import com.edxmod.electrodynamics.client.render.WrappedModel;
import com.edxmod.electrodynamics.common.tile.TileCrank;
import com.edxmod.electrodynamics.common.util.RenderHelper;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

/**
 * @author dmillerw
 */
public class RenderTileCrank extends EDXTileRenderer<TileCrank> {

	public static WrappedModel crank;

	static {
		crank = new WrappedModel("blocks/handCrank.obj", "blocks/handCrank.png");
	}

	public void renderTileAt(TileCrank tile, double x, double y, double z, float delta) {
		GL11.glPushMatrix();

		GL11.glDisable(GL11.GL_LIGHTING);

		ForgeDirection orientation = tile.orientation.getOpposite();

		GL11.glTranslated(x, y, z);

		GL11.glTranslated(0.5 - 0.03125F, 0, 0.5 - 0.03125F);
		GL11.glTranslated(0.03125F, 0, 0.03125F);
		GL11.glRotated(RenderHelper.getRotationAngle(orientation) + 90, 0, 1, 0);
		GL11.glTranslated(-0.03125F, 0, -0.03125F);

		GL11.glRotated(90, 0, 0, 1);
		GL11.glTranslated(0.5 - 0.0625F / 2, -0.5 - 0.03125F, 0);

		GL11.glTranslated(0.03125, 0, 0.03125);
		GL11.glRotated(tile.reverse ? tile.spin : tile.spin, 0, 1, 0);
		GL11.glTranslated(-0.03125, 0, -0.03125F);

		GL11.glPushMatrix();

		crank.bindTexture();
		crank.renderAll();

		GL11.glPopMatrix();

		GL11.glEnable(GL11.GL_LIGHTING);

		GL11.glPopMatrix();
	}
}
