package com.edxmod.electrodynamics.client.render.item;

import com.edxmod.electrodynamics.client.render.WrappedModel;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * @author dmillerw
 */
public class RenderItemTable implements IItemRenderer {

    private static WrappedModel woodTable;
    private static WrappedModel stoneTable;

    static {
        woodTable = new WrappedModel("blocks/basicTable");
        stoneTable = new WrappedModel("blocks/smashingTable");
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();

        if (type == ItemRenderType.ENTITY) {
            GL11.glTranslated(-0.5, 0, -0.5);
        }

        if (type == ItemRenderType.INVENTORY) {
            GL11.glTranslated(0.1, 0, 0.1);
        }

        switch (item.getItemDamage()) {
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

        GL11.glPopMatrix();
    }

}
