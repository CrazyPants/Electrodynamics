package com.edxmod.electrodynamics.common.util;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

/**
 * @author dmillerw
 */
public class InventoryHelper {

	public static void ejectItem(World world, int x, int y, int z, ForgeDirection side, ItemStack item, Random random) {
		if (item != null) {
			double spawnX = x + 0.5D + 0.5D * side.offsetX;
			double spawnY = y + 0.5D + 0.5D * side.offsetY;
			double spawnZ = z + 0.5D + 0.5D * side.offsetZ;
			EntityItem entity = new EntityItem(world, spawnX, spawnY, spawnZ, item);

//			if (item.hasTagCompound()) {
//				entity.func_92059_d().func_77982_d((NBTTagCompound) item.func_77978_p().func_74737_b());
//			}

			world.spawnEntityInWorld(entity);
		}
	}

}
