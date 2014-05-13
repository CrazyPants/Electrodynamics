package com.edxmod.electrodynamics.client.render.item;

import com.edxmod.electrodynamics.client.render.WrappedModel;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * @author dmillerw
 */
public class RenderItemHammer implements IItemRenderer {

    private static WrappedModel hammer;

    static {
        hammer = new WrappedModel("items/hammer.obj", "items/hammer.png");
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

        if (type == ItemRenderType.INVENTORY) {
            GL11.glScaled(1.75, 1.75, 1.75);
            GL11.glTranslated(0.3, 0, 0.3);
        }

        if (type == ItemRenderType.ENTITY) {
            GL11.glTranslated(0F, 0, -0.25F);
        }

        if (type == ItemRenderType.EQUIPPED) {
            GL11.glScaled(2, 2, 2);
            GL11.glRotated(45, 0, 1, 0);
            GL11.glTranslated(-0.15, 0, 0.7);
            GL11.glRotated(-75, 1, 0, 0);
        }

        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glRotated(-45, 0, 1, 0);
            GL11.glTranslated(0.25, 0.75, 0.75);
        }

        hammer.bindTexture();
        hammer.renderAll();

        GL11.glPopMatrix();
    }

}
