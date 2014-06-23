package com.edxmod.electrodynamics.common.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author dmillerw
 */
public class TileKineticCrank extends TileCoreMachine {

	@SideOnly(Side.CLIENT)
	public float angle = 0F;
}
