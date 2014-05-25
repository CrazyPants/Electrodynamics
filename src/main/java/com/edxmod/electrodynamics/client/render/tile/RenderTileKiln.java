package com.edxmod.electrodynamics.client.render.tile;

import com.edxmod.electrodynamics.client.render.WrappedModel;
import com.edxmod.electrodynamics.common.tile.TileHammerMill;
import com.edxmod.electrodynamics.common.tile.TileKiln;
import com.edxmod.electrodynamics.common.util.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 * @author dmillerw
 */
public class RenderTileKiln extends TileEntitySpecialRenderer {

	public static WrappedModel kiln;

	static {
		kiln = new WrappedModel("blocks/kiln.obj", "blocks/kiln.png");
	}

	public void renderMillAt(TileKiln tile, double x, double y, double z, float partial) {
		GL11.glPushMatrix();

		GL11.glTranslated(x, y, z);

		GL11.glTranslated(0.5, 0, 0.5);
		GL11.glRotated(RenderHelper.getRotationAngle(tile.orientation), 0, 1, 0);
		GL11.glTranslated(-0.5, 0, -0.5);

		kiln.bindTexture();
		kiln.renderAll();

		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) {
		renderMillAt((TileKiln) var1, var2, var4, var6, var8);
	}
}
