package com.edxmod.electrodynamics.client.render.tile;

import com.edxmod.electrodynamics.client.render.DynamicBlockRenderer;
import com.edxmod.electrodynamics.common.tile.TileBarrel;
import net.minecraft.init.Blocks;

/**
 * @author dmillerw
 */
public class RenderTileBarrel extends EDXTileRenderer<TileBarrel> {

	@Override
	public void renderTileAt(TileBarrel tile, double x, double y, double z, float delta) {
		DynamicBlockRenderer dynamicBlockRenderer = DynamicBlockRenderer.getInstance();
		dynamicBlockRenderer.initialize(tile);
		dynamicBlockRenderer.render(tile.getWorldObj(), tile.xCoord, tile.yCoord, tile.zCoord, Blocks.gravel);
		dynamicBlockRenderer.cleanup();
	}
}
