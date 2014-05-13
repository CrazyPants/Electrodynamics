package com.edxmod.electrodynamics.client.render.block;

import com.edxmod.electrodynamics.client.render.BetterRenderer;
import com.edxmod.electrodynamics.common.tabs.EDXCreativeTab;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

/**
 *
 */
public class RenderEmptyBlock extends BetterRenderer {

    public static int renderID;

    static {
        renderID = RenderingRegistry.getNextAvailableRenderId();
    }

    @Override
    public int getRenderId() {
        return renderID;
    }

    @Override
    public void renderInventoryBlock(RenderBlocks renderer, Block block, int meta) {

    }

    @Override
    public void renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer) {
        Tessellator t = Tessellator.instance;
        t.addTranslation(x, y, z);
        t.addVertex(0, 1, 1);
        t.addVertex(1, 1, 1);
        t.addVertex(1, 1, 0);
        t.addVertex(0, 1, 0);
        t.addTranslation(-x, -y, -z);
        renderer.renderStandardBlock(block, x, y, z);
    }

    @Override
    public void setCubeBounds(RenderBlocks renderer, float x, float y, float z, float w, float h) {
        //renderer.setRenderBounds(x, y, z, x + w, y + h, z + w);
        renderer.setRenderBounds(0d, 0d, 0d, 0d, 0d, 0d);
    }
}
