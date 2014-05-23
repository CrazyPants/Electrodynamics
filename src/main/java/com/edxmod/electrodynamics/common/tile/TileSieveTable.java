package com.edxmod.electrodynamics.common.tile;

import com.edxmod.electrodynamics.common.recipe.RecipeManager;
import com.edxmod.electrodynamics.common.recipe.manager.SieveManager;
import com.edxmod.electrodynamics.common.util.InventoryHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;
import java.util.Random;

/**
 * @author Royalixor
 */
public class TileSieveTable extends TileCore {

	private SieveManager.SieveRecipe processingRecipe;

	public ItemStack processing;

    @Override
    public void readCustomNBT(NBTTagCompound nbt) {

    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbt) {

    }

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (processing == null) {
				AxisAlignedBB scan = AxisAlignedBB.getBoundingBox(0, 1, 0, 1, 2, 1).offset(xCoord, yCoord, zCoord);
				List entities = worldObj.getEntitiesWithinAABB(EntityItem.class, scan);

				if (entities != null && entities.size() > 0) {
					EntityItem item = (EntityItem) entities.get(0);
					if (item.getEntityItem() != null) {
						SieveManager.SieveRecipe recipe = RecipeManager.INSTANCE.sieve.get(item.getEntityItem());

						if (recipe != null) {
							processingRecipe = recipe;
							processing = item.getEntityItem().copy();
							processing.stackSize = 1;
							item.getEntityItem().stackSize--;

							if (item.getEntityItem().stackSize <= 0) {
								item.setDead();
							}
						}
					}
				}
			} else {
				ItemStack[] output = processingRecipe.getOutput();
				Random random = new Random();

				for (ItemStack out : output) {
					InventoryHelper.ejectItem(worldObj, xCoord, yCoord - 1, zCoord, ForgeDirection.DOWN, out, random);
				}

				processingRecipe = null;
				processing = null;
			}
		}
	}
}
