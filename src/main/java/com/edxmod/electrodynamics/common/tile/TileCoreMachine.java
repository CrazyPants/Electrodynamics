package com.edxmod.electrodynamics.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author dmillerw
 */
public abstract class TileCoreMachine extends TileCore {

	public ForgeDirection orientation = ForgeDirection.UNKNOWN;

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		nbt.setByte("orientation", (byte) orientation.ordinal());
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		orientation = ForgeDirection.getOrientation(nbt.getByte("orientation"));
	}

}
