package com.edxmod.electrodynamics.common.tile;

import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Royalixor
 */
public class TileSinteringOven extends TileCore {

    public boolean open = false;

    public float currentAngle = 0.0F;

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) {
            currentAngle += (open ? 10F : -10F);

            if (currentAngle <= 0F) {
                currentAngle = 0F;
            } else if (currentAngle >= 90F) {
                currentAngle = 90F;
            }
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbt) {
        open = nbt.getBoolean("open");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbt) {
        nbt.setBoolean("open", open);
    }
}
