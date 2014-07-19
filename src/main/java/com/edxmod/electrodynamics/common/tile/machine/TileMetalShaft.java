package com.edxmod.electrodynamics.common.tile.machine;

import com.edxmod.electrodynamics.common.tile.core.TileCoreMachine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author dmillerw
 */
public class TileMetalShaft extends TileCoreMachine {

    @SideOnly(Side.CLIENT)
    public float angle = 0F;
}
