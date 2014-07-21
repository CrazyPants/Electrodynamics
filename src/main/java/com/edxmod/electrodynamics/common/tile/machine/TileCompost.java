package com.edxmod.electrodynamics.common.tile.machine;

import com.edxmod.electrodynamics.common.network.nbt.NBTHandler;
import com.edxmod.electrodynamics.common.tile.core.TileCoreMachine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Created by Thlayli
 */
public class TileCompost extends TileCoreMachine {

    @NBTHandler.NBTData
    public boolean lidOpen = true;

    @SideOnly(Side.CLIENT)
    public float currentAngle = 0f;

    public final float maxAngle = -45f;
    public final float minAngle = 14f;

    @Override
    public void onPoked(){
        lidOpen = !lidOpen;
    }

    @Override
    public void updateEntity(){
        if(worldObj.isRemote){
            currentAngle += lidOpen ? 4f : -4f;
            if(currentAngle <= maxAngle){
                currentAngle = maxAngle;
            }
            if(currentAngle >= minAngle){
                currentAngle = minAngle;
            }
        }
    }

}
