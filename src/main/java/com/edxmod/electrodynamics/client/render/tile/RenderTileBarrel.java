package com.edxmod.electrodynamics.client.render.tile;

import com.edxmod.electrodynamics.client.lib.Model;
import com.edxmod.electrodynamics.client.render.EDXTileRenderer;
import com.edxmod.electrodynamics.common.tile.machine.TileBarrel;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;

/**
 * @author dmillerw
 */
public class RenderTileBarrel extends EDXTileRenderer<TileBarrel> {

	public static final String[] LID_PARTS = new String[]{"lid", "lidHandle"};

	@Override
	public void renderTileAt(TileBarrel tile, double x, double y, double z, float delta) {
		GL11.glPushMatrix();

		GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);

		Model.BARREL_WOOD.bindTexture();
		Model.BARREL_WOOD.renderAllExcept(LID_PARTS);
		if (tile.hasLid) {
			Model.BARREL_WOOD.renderOnly(LID_PARTS);
		}

		if (tile.contents != null) {
			float level = TileBarrel.DIMENSION_MIN;
			level += (TileBarrel.DIMENSION_FILL * ((float) tile.contents.stackSize / (float) tile.maxStackSize));
			renderContents(Block.getBlockFromItem(tile.contents.getItem()), tile.contents.getItemDamage(), level);
		}

		GL11.glPopMatrix();
	}

	private void renderContents(Block block, int meta, float level) {
		IIcon top = block.getIcon(1, meta);
		float min = -0.5F + 0.125F;
		float max = 0.5F - 0.125F;

		bindTexture(TextureMap.locationBlocksTexture);

		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();

		tessellator.setColorOpaque_I(block.getBlockColor());

		tessellator.setNormal(0, 1, 0);

		tessellator.addVertexWithUV(min, level - 0.5F, min, top.getMinU(), top.getMinV());
		tessellator.addVertexWithUV(min, level - 0.5F, max, top.getMinU(), top.getMaxV());
		tessellator.addVertexWithUV(max, level - 0.5F, max, top.getMaxU(), top.getMaxV());
		tessellator.addVertexWithUV(max, level - 0.5F, min, top.getMaxU(), top.getMinV());

		tessellator.draw();
	}
}
