package com.edxmod.electrodynamics.common.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author dmillerw
 */
public class EntityHelper {

	public static ForgeDirection get2DRotation(EntityLivingBase entity) {
		int l = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) {
			return ForgeDirection.getOrientation(3);
		} else if (l == 1) {
			return ForgeDirection.getOrientation(4);
		} else if (l == 2) {
			return ForgeDirection.getOrientation(2);
		} else if (l == 3) {
			return ForgeDirection.getOrientation(5);
		} else {
			return ForgeDirection.UNKNOWN;
		}
	}

}
