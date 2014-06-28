package com.edxmod.electrodynamics.common.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author dmillerw
 */
public class TileKineticCrank extends TileCoreMachine {

	@SideOnly(Side.CLIENT)
	public float angle = 0F;

	public float speed = 0F;

	public boolean stopTick = false;

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			TileHammerMill tile = (TileHammerMill) worldObj.getTileEntity(xCoord + orientation.getOpposite().offsetX, yCoord + orientation.getOpposite().offsetY, zCoord + orientation.getOpposite().offsetZ);

			if (tile != null && worldObj.getTotalWorldTime() % (TileWaterMill.MAX_SPEED - speed) == 0) {
				tile.charge++;
			}
		} else {
			TileHammerMill tile = (TileHammerMill) worldObj.getTileEntity(xCoord + orientation.getOpposite().offsetX, yCoord + orientation.getOpposite().offsetY, zCoord + orientation.getOpposite().offsetZ);

			if (tile != null) {
				tile.angle = angle;
			}
		}
	}
}
