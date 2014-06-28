package com.edxmod.electrodynamics.common.tile;

import com.edxmod.electrodynamics.common.tile.nbt.NBTHandler;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author dmillerw
 */
public class TileCrank extends TileCoreMachine {

	@NBTHandler.NBTData
	public boolean reverse = false;
	@NBTHandler.NBTData
	public float spin = 0F;

	@Override
	public void onClientUpdate(NBTTagCompound nbt) {
		if (nbt.hasKey("spin")) {
			spin = nbt.getFloat("spin");
		}
	}

	@Override
	public void updateEntity() {
		if (spin > 0) {
			spin -= 20F;
		}

		if (worldObj.isRemote) {
			TileHammerMill tile = (TileHammerMill) worldObj.getTileEntity(xCoord + orientation.offsetX, yCoord + orientation.offsetY, zCoord + orientation.offsetZ);

			if (tile != null) {
				tile.angle = spin;
			}
		}
	}

	public void crank() {
		if (spin <= 0) {
			TileHammerMill tile = (TileHammerMill) worldObj.getTileEntity(xCoord + orientation.offsetX, yCoord + orientation.offsetY, zCoord + orientation.offsetZ);
			tile.charge++;

			spin = 360F;

			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setFloat("spin", spin);
			sendClientUpdate(nbt);
		}
	}
}
