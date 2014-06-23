package com.edxmod.electrodynamics.common.tile;

import com.edxmod.electrodynamics.common.tile.nbt.NBTHandler;

/**
 * @author dmillerw
 */
public class TileCrank extends TileCoreMachine {

	@NBTHandler.NBTData
	public boolean reverse = false;

	public void crank() {
		TileHammerMill tile = (TileHammerMill) worldObj.getTileEntity(xCoord + orientation.offsetX, yCoord + orientation.offsetY, zCoord + orientation.offsetZ);
		tile.crank();
	}
}
