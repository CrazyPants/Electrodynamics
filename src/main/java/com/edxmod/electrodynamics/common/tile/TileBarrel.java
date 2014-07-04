package com.edxmod.electrodynamics.common.tile;

import java.util.Random;

/**
 * @author dmillerw
 */
public class TileBarrel extends TileCore {

	public static final float DIMENSION_MIN = 0.0625F;
	public static final float DIMENSION_MAX = 0.9375F;
	public static final float DIMENSION_FILL = DIMENSION_MAX - DIMENSION_MIN;

	public static Random random = new Random();
}
