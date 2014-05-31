package com.edxmod.electrodynamics.common.tile;

import com.edxmod.electrodynamics.common.item.EDXItems;
import com.edxmod.electrodynamics.common.item.prefab.EDXItem;
import com.edxmod.electrodynamics.common.recipe.EDXRecipes;
import com.edxmod.electrodynamics.common.recipe.manager.SieveManager;
import com.edxmod.electrodynamics.common.recipe.manager.TableManager;
import com.edxmod.electrodynamics.common.util.InventoryHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;
import java.util.Random;

/**
 * @author dmillerw
 */
public class TileHammerMill extends TileCoreMachine implements ISidedInventory {

	public static final byte MAX_STAGE = 5;

	private static final int INVENTORY_SIZE = 9;

	public ItemStack[] processing = new ItemStack[INVENTORY_SIZE];

	public byte grindingStage;

	public boolean spinning = false;

	public float charge = 0F;
	public float spinLeft = 0F;

	@Override
	public void writeCustomNBT(NBTTagCompound nbt) {
		nbt.setByte("stage", grindingStage);
	}

	@Override
	public void readCustomNBT(NBTTagCompound nbt) {
		grindingStage = nbt.getByte("stage");
	}

	@Override
	public void onClientUpdate(NBTTagCompound nbt) {
		if (nbt.hasKey("stage")) {
			grindingStage = nbt.getByte("stage");
		}
	}

	@Override
	public void onPokeReceived() {
		spinLeft += 360F;
	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			// Collect items
			AxisAlignedBB scan = AxisAlignedBB.getBoundingBox(0, 1, 0, 1, 2, 1).offset(xCoord, yCoord, zCoord);
			List entities = worldObj.getEntitiesWithinAABB(EntityItem.class, scan);

			if (entities != null && entities.size() > 0) {
				EntityItem item = (EntityItem) entities.get(0);
				if (item.getEntityItem() != null) {
//					SieveManager.SieveRecipe recipe = RecipeManager.INSTANCE.sieve.get(item.getEntityItem());

//					if (recipe != null) {
						ItemStack stack = item.getEntityItem().copy();
						stack.stackSize = 1;

						if (TileEntityHopper.func_145889_a(this, stack, 1) == null) {
							item.getEntityItem().stackSize--;

							if (item.getEntityItem().stackSize <= 0) {
								item.setDead();
							}
						}
//					}
				}
			}

			// Process items
			// Recipe would be here
			for (int i=0; i<processing.length; i++) {
				ItemStack processed = processing[i];

				if (processed != null && processed.stackSize > 0) {
					float durability = 0F;

					if (charge >= durability) {
						charge -= durability;

						Random random = new Random();

//						for (ItemStack stack : recipe.getOutput()) {
//							InventoryHelper.ejectItem(worldObj, xCoord, yCoord, zCoord, ForgeDirection.UP, stack, random);
//						}

						processed.stackSize--;
						if (processed.stackSize <= 0) {
							processing[i] = null;
						}

						break;
					}
				}
			}

			if (spinLeft % 90F == 0 && spinning) {
				charge++;
			}

			if (spinLeft <= 0) {
				spinning = false;
				spinLeft = 0;
			}
		}

		if (spinLeft > 0) {
			spinLeft -= 20F;
		} else {
			spinLeft = 0;
		}
	}

	public void crank() {
		if (spinLeft <= 0) {
			spinLeft += 360F;
			sendPoke();
		}
	}

	public void updateStage() {
		grindingStage++;
		if (grindingStage >= MAX_STAGE) {
			grindingStage = 0;
		}

		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setByte("stage", grindingStage);
		sendClientUpdate(nbt);
	}

	/* IINVENTORY / ISIDEDINVENTORY */
	@Override
	public int getSizeInventory() {
		return INVENTORY_SIZE;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return processing[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amt) {
		if (processing[slot] != null) {
			ItemStack itemstack;

			if (processing[slot].stackSize <= amt) {
				itemstack = processing[slot];
				processing[slot] = null;
				return itemstack;
			} else {
				itemstack = processing[slot].splitStack(amt);

				if (processing[slot].stackSize == 0) {
					processing[slot] = null;
				}

				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (processing[slot] != null) {
			ItemStack itemstack = processing[slot];
			processing[slot] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		processing[slot] = stack;
	}

	@Override
	public String getInventoryName() {
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return side == 1 ? new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8} : new int[0];
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return side == 1 && EDXRecipes.SIEVE.get(stack) != null;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return false;
	}
}
