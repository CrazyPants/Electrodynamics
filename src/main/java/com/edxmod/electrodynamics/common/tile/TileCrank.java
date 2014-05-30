package com.edxmod.electrodynamics.common.tile;

import net.minecraft.nbt.NBTTagCompound;

/**
 * @author dmillerw
 */
public class TileCrank extends TileCoreMachine {

	public boolean reverse = false;

	@Override
	public void writeCustomNBT(NBTTagCompound nbt) {
		nbt.setBoolean("reverse", reverse);
	}

	@Override
	public void readCustomNBT(NBTTagCompound nbt) {
		reverse = nbt.getBoolean("reverse");
	}

	public void crank() {
		TileHammerMill tile = (TileHammerMill) worldObj.getTileEntity(xCoord + orientation.offsetX, yCoord + orientation.offsetY, zCoord + orientation.offsetZ);
		tile.crank();
	}
}
