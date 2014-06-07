package com.edxmod.electrodynamics.api.tool;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author dmillerw
 */
public interface ICrankable {

	public void crank();

	public boolean canConnect(ForgeDirection side);

}
