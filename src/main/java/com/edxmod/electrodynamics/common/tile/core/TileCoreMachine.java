package com.edxmod.electrodynamics.common.tile.core;

import com.edxmod.electrodynamics.common.network.nbt.NBTHandler;
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
