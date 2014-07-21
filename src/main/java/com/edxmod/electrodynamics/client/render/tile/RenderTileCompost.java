package com.edxmod.electrodynamics.client.render.tile;

import com.edxmod.electrodynamics.client.lib.Model;
import com.edxmod.electrodynamics.client.render.EDXTileRenderer;
import com.edxmod.electrodynamics.common.tile.machine.TileCompost;
import com.edxmod.electrodynamics.common.util.RenderHelper;
import org.lwjgl.opengl.GL11;

/**
 * Created by Thlayli
 */
public class RenderTileCompost extends EDXTileRenderer<TileCompost>{

    @Override
    public void renderTileAt(TileCompost tile, double x, double y, double z, float delta) {

        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);

        GL11.glTranslated(0.5,0.5,0.5);
        GL11.glRotated(RenderHelper.getRotationAngle(tile.orientation.getOpposite()),0,1,0);

        switch(tile.getBlockMetadata()){
            case 0:
                Model.COMPOST_WOOD.bindTexture();
                Model.COMPOST_WOOD.renderAll();
                break;
        }

        GL11.glPopMatrix();
    }
}
