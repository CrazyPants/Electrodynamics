package com.edxmod.electrodynamics.client.render.tile;

import com.edxmod.electrodynamics.client.render.WrappedModel;
import com.edxmod.electrodynamics.common.tile.TileCrank;
import com.edxmod.electrodynamics.common.tile.TileHammerMill;
import com.edxmod.electrodynamics.common.util.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

/**
 * @author dmillerw
 */
public class RenderTileCrank extends TileEntitySpecialRenderer {

	public static WrappedModel crank;

	static {
		crank = new WrappedModel("blocks/crank.obj", "blocks/crank.png");
	}

	public void renderCrankAt(TileCrank tile, double x, double y, double z, float partial) {
		GL11.glPushMatrix();

		GL11.glDisable(GL11.GL_LIGHTING);

		ForgeDirection orientation = tile.orientation.getOpposite();
		TileHammerMill hammerMill = (TileHammerMill) tile.getWorldObj().getTileEntity(tile.xCoord + tile.orientation.offsetX, tile.yCoord + tile.orientation.offsetY, tile.zCoord + tile.orientation.offsetZ);

		if (hammerMill == null) {
			// Probably the tick inbetween hammer mill breaking and the crank updating, so just cancel render
			return;
		}

		GL11.glTranslated(x, y, z);

		GL11.glTranslated(0.5 - 0.03125F, 0, 0.5 - 0.03125F);
		GL11.glTranslated(0.03125F, 0, 0.03125F);
		GL11.glRotated(RenderHelper.getRotationAngle(orientation) + 90, 0, 1, 0);
		GL11.glTranslated(-0.03125F, 0, -0.03125F);

		GL11.glRotated(90, 0, 0, 1);
		GL11.glTranslated(0.5 - 0.0625F / 2, -0.5 - 0.03125F, 0);

		GL11.glTranslated(0.03125, 0, 0.03125);
		GL11.glRotated(tile.reverse ? hammerMill.spinLeft : -hammerMill.spinLeft, 0, 1, 0);
		GL11.glTranslated(-0.03125, 0, -0.03125F);

		GL11.glPushMatrix();

		crank.bindTexture();
		crank.renderAll();

		GL11.glPopMatrix();

		GL11.glEnable(GL11.GL_LIGHTING);

		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partial) {
		renderCrankAt((TileCrank) tile, x, y, z, partial);
	}
}
