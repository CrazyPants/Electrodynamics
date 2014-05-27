package com.edxmod.electrodynamics.client.render.tile;

import com.edxmod.electrodynamics.client.render.WrappedModel;
import com.edxmod.electrodynamics.common.block.prefab.EDXTileMultiBlock;
import com.edxmod.electrodynamics.common.block.prefab.EDXTileBlock;
import com.edxmod.electrodynamics.common.tile.TileTable;
import com.edxmod.electrodynamics.common.util.ItemHelper;
import com.edxmod.electrodynamics.common.util.RenderHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 * @author dmillerw
 */
public class RenderTileTable extends TileEntitySpecialRenderer {

    public static final float WOOD_RENDER_MAX = 0.5625F;
    public static final float STONE_RENDER_MAX = 0.875F;

    private static WrappedModel woodTable;
    private static WrappedModel stoneTable;

    static {
        woodTable = new WrappedModel("blocks/basicTable.obj", "blocks/basicTable.png");
        stoneTable = new WrappedModel("blocks/smashingTable.obj", "blocks/smashingTable.png");
    }

    private void renderTableAt(TileTable tile, double x, double y, double z, float partial) {
        GL11.glPushMatrix();

        GL11.glTranslated(x, y, z);

        switch (tile.getBlockMetadata()) {
            case 0: {
                woodTable.bindTexture();
                woodTable.renderAll();

                break;
            }

            case 1: {
                stoneTable.bindTexture();
                stoneTable.renderAll();

                break;
            }
        }

        GL11.glTranslated(0.5, 0, 0.5);

        if (tile.stack != null) {
            float renderMax = tile.getBlockMetadata() == 0 ? WOOD_RENDER_MAX : STONE_RENDER_MAX;
            if (tile.stack.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(tile.stack.getItem()).getRenderType())) {
                Block block = Block.getBlockFromItem(tile.stack.getItem());
                boolean fixOffset = false;

                // EDX specific rendering, since we offset our models
                if (block instanceof EDXTileBlock) {
                    fixOffset = ((EDXTileBlock) block).useCustomRender();
                } else if (block instanceof EDXTileMultiBlock) {
                    fixOffset = ((EDXTileMultiBlock) block).useCustomRender();
                }

                if (fixOffset) {
                    // If it has a model render, move it back down a half, as we offset our entity render by a half
                    GL11.glTranslated(0, -0.25, 0);
                }

                GL11.glTranslated(0, renderMax + 0.0625F, 0);
                GL11.glScaled(2, 2, 2);
            } else {
                GL11.glTranslated(0, renderMax + (0.0625F / 2), -(0.0625F * 3.65));
                GL11.glRotated(90, 1, 0, 0);
            }

            RenderHelper.renderItemStack(tile.stack, true);
        }

        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partial) {
        renderTableAt((TileTable) tile, x, y, z, partial);
    }

}
