package com.edxmod.electrodynamics.client.render.block;

import com.edxmod.electrodynamics.ClientProxy;
import com.edxmod.electrodynamics.client.render.BetterRenderer;
import com.edxmod.electrodynamics.common.block.world.BlockOre;
import com.edxmod.electrodynamics.common.util.UtilRender;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

/**
 * @author Royalixor
 */
public class RenderBlockOre extends BetterRenderer {

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
        GL11.glPushMatrix();

        UtilRender.renderAllSides(renderer, block, Blocks.stone.getIcon(0, 0));
        UtilRender.renderAllSides(renderer, block, ((BlockOre) block).iconOverlays[meta]);

        GL11.glPopMatrix();
    }

    @Override
    public void renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer) {
        UtilRender.forceGraphics(true);
        UtilRender.forceLighting(0);

		Tessellator tessellator = Tessellator.instance;

		renderer.renderStandardBlock(block, x, y, z);
		UtilRender.setBrightness(world, x, y, z, block);
		UtilRender.renderAllSides(x, y, z, block, renderer, ((BlockOre)block).iconOverlays[world.getBlockMetadata(x, y, z)]);

        UtilRender.resetLighting();
        UtilRender.resetGraphics();
    }
}
