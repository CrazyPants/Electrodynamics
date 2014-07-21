package com.edxmod.electrodynamics.client.render.tile;

import com.edxmod.electrodynamics.client.lib.Model;
import com.edxmod.electrodynamics.client.render.EDXTileRenderer;
import com.edxmod.electrodynamics.common.lib.MathFX;
import com.edxmod.electrodynamics.common.tile.machine.TileCompost;
import com.edxmod.electrodynamics.common.util.RenderHelper;
import org.lwjgl.opengl.GL11;

/**
 * Created by Thlayli
 */
public class RenderTileCompost extends EDXTileRenderer<TileCompost>{

    public final String lid = "lid";

    @Override
    public void renderTileAt(TileCompost tile, double x, double y, double z, float delta) {

        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
        GL11.glRotated(RenderHelper.getRotationAngle(tile.orientation.getOpposite()),0,1,0);

        switch(tile.getBlockMetadata()){
            case 0:
                Model.COMPOST_WOOD.bindTexture();
                Model.COMPOST_WOOD.renderAllExcept(lid);
                GL11.glPushMatrix();
                swingLid(tile);
                Model.COMPOST_WOOD.renderOnly(lid);
                GL11.glPopMatrix();
                break;
            case 1:
                Model.COMPOST_STONE.bindTexture();
                Model.COMPOST_STONE.renderAllExcept(lid);
                GL11.glPushMatrix();
                swingLid(tile);
                Model.COMPOST_STONE.renderOnly(lid);
                GL11.glPopMatrix();
                break;
        }
        GL11.glPopMatrix();
    }

    private void swingLid(TileCompost tile){
        float percentage;
        float sinerp;

        GL11.glTranslated(0, 0.37, 0.29);
        percentage = tile.currentAngle / tile.maxAngle;
        sinerp = MathFX.sinerp(0,1,percentage);
        GL11.glRotated((-sinerp)* tile.maxAngle, 1,0,0);
        GL11.glTranslated(0, -0.37, -0.29);
    }
}
