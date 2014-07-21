package com.edxmod.electrodynamics.client.render.item;

import com.edxmod.electrodynamics.client.lib.Model;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Created by Thlayli
 */
public class RenderItemCompost implements IItemRenderer{

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
            GL11.glTranslated(0.1, 0.0, 0.1);
            GL11.glRotated(180D, 0, 1, 0);
        }

        switch (item.getItemDamage()){
            case 0:
                Model.COMPOST_WOOD.bindTexture();
                Model.COMPOST_WOOD.renderAll();
                break;
            case 1:
                Model.COMPOST_STONE.bindTexture();
                Model.COMPOST_STONE.renderAll();
        }

        GL11.glPopMatrix();
    }
}
