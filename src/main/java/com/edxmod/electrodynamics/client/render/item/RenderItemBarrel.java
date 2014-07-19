package com.edxmod.electrodynamics.client.render.item;

import com.edxmod.electrodynamics.client.lib.Model;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * @author dmillerw
 */
public class RenderItemBarrel implements IItemRenderer {

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
            GL11.glRotated(180, 0, 1, 0);
            GL11.glTranslated(0.1, 0, 0.1);
        }

        switch (item.getItemDamage()) {
            case 1:
                Model.BARREL_STONE.bindTexture();
                Model.BARREL_STONE.renderAll();
                break;
            case 0:
            default:
                Model.BARREL_WOOD.bindTexture();
                Model.BARREL_WOOD.renderAll();
                break;
        }

        GL11.glPopMatrix();
    }
}
