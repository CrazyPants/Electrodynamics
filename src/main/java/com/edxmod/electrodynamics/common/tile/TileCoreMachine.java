package com.edxmod.electrodynamics.common.tile;

import com.edxmod.electrodynamics.common.tile.nbt.NBTHandler;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author dmillerw
 */
public abstract class TileCoreMachine extends TileCore {

	@NBTHandler.NBTData
	public ForgeDirection orientation = ForgeDirection.UNKNOWN;

	public TileCoreMachine() {
		super();

		this.handler.addField(TileCoreMachine.class, "orientation");
	}
}
