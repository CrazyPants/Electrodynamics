package com.edxmod.electrodynamics.common.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Royalixor
 */
public class TileSinteringOven extends TileCore {

	@SideOnly(Side.CLIENT)
	public float currentAngle = 0.0F;

    public boolean open = false;

    @Override
    public void readCustomNBT(NBTTagCompound nbt) {
        open = nbt.getBoolean("open");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbt) {
        nbt.setBoolean("open", open);
    }

	@Override
	public void onPokeReceived() {
		open = !open;
	}

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
}
