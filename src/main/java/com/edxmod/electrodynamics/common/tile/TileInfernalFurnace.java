package com.edxmod.electrodynamics.common.tile;

import com.edxmod.electrodynamics.common.tile.nbt.NBTHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

/**
 * @author dmillerw
 */
public class TileInfernalFurnace extends TileCoreMachine implements ISidedInventory {

	public static final int[] SLOTS_TOP = new int[] {0};
	// Two comes before one to give slot 2 priority
	public static final int[] SLOTS_BOTTOM = new int[] {2, 1};
	public static final int[] SLOTS_SIDE = new int[] {1};

	public static final int SIZE = 3;
	public static final int COOK_TIME = 200;
	public static final int LIT_COOK_TIME = 400;

	@NBTHandler.NBTData
	private ItemStack[] inv = new ItemStack[SIZE];

	/**
	 * The number of ticks that the furnace will keep burning
	 */
	@NBTHandler.NBTData
	public int furnaceBurnTime;
	/**
	 * The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for
	 */
	@NBTHandler.NBTData
	public int currentItemBurnTime;
	/**
	 * The number of ticks that the current item has been cooking for
	 */
	@NBTHandler.NBTData
	public int furnaceCookTime;

	@NBTHandler.NBTData
	public boolean lit = false;

//	@Override
//	public void writeCustomNBT(NBTTagCompound nbt) {
//		nbt.setShort("BurnTime", (short) this.furnaceBurnTime);
//		nbt.setShort("CookTime", (short) this.furnaceCookTime);
//		nbt.setBoolean("lit", lit);
//		NBTTagList nbttaglist = new NBTTagList();
//
//		for (int i = 0; i < inv.length; ++i) {
//			if (inv[i] != null) {
//				NBTTagCompound item = new NBTTagCompound();
//				item.setByte("Slot", (byte) i);
//				inv[i].writeToNBT(item);
//				nbttaglist.appendTag(item);
//			}
//		}
//
//		nbt.setTag("Items", nbttaglist);
//	}

//	@Override
//	public void readCustomNBT(NBTTagCompound nbt) {
//		NBTTagList nbttaglist = nbt.getTagList("Items", 10);
//		inv = new ItemStack[this.getSizeInventory()];
//
//		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
//			NBTTagCompound item = nbttaglist.getCompoundTagAt(i);
//			byte b0 = item.getByte("Slot");
//
//			if (b0 >= 0 && b0 < inv.length) {
//				inv[b0] = ItemStack.loadItemStackFromNBT(item);
//			}
//		}
//
//		this.lit = nbt.getBoolean("lit");
//		this.furnaceBurnTime = nbt.getShort("BurnTime");
//		this.furnaceCookTime = nbt.getShort("CookTime");
//		this.currentItemBurnTime = TileEntityFurnace.getItemBurnTime(lit ? new ItemStack(Items.lava_bucket) : inv[1]);
//	}

	public int getCookTime() {
		return lit ? LIT_COOK_TIME : COOK_TIME;
	}

	public boolean isBurning() {
		return furnaceBurnTime > 0;
	}

	@Override
	public void updateEntity() {
		boolean flag = isBurning();
		boolean flag1 = false;

		if (furnaceBurnTime > 0) {
			--furnaceBurnTime;
		}

		if (!worldObj.isRemote) {
			if (furnaceBurnTime == 0 && (canSmelt() || lit)) {
				currentItemBurnTime = furnaceBurnTime = TileEntityFurnace.getItemBurnTime(lit ? new ItemStack(Items.lava_bucket) : inv[1]);

				if (!lit && furnaceBurnTime > 0) {
					flag1 = true;

					if (inv[1] != null) {
						--inv[1].stackSize;

						if (inv[1].stackSize == 0) {
							inv[1] = inv[1].getItem().getContainerItem(inv[1]);
						}
					}
				}
			}

			if (furnaceBurnTime > 0 && canSmelt()) {
				++furnaceCookTime;

				if (furnaceCookTime == getCookTime()) {
					furnaceCookTime = 0;
					smeltItem();
					flag1 = true;
				}
			} else {
				furnaceCookTime = 0;
			}

			if (flag != isBurning()) {
				flag1 = true;
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, isBurning() ? 1 : 0, 3);
			}
		}

		if (flag1) {
			markDirty();
		}
	}

	private boolean canSmelt() {
		if (inv[0] == null) {
			return false;
		} else {
			ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(inv[0]);
			if (result == null) {
				return false;
			}
			if (inv[2] == null) {
				return true;
			}
			if (!inv[2].isItemEqual(result)) {
				return false;
			}
			int resultSize = inv[2].stackSize + result.stackSize;
			return resultSize <= getInventoryStackLimit() && resultSize <= inv[2].getMaxStackSize();
		}
	}

	public void smeltItem() {
		if (canSmelt()) {
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(inv[0]);

			if (inv[2] == null) {
				inv[2] = itemstack.copy();
			} else if (inv[2].getItem() == itemstack.getItem()) {
				inv[2].stackSize += itemstack.stackSize;
			}

			--inv[0].stackSize;

			if (inv[0].stackSize <= 0) {
				inv[0] = null;
			}
		}
	}

	/* CLIENT METHODS */
	@SideOnly(Side.CLIENT)
	public int getScaledCookProgess(int scale) {
		return furnaceCookTime * scale / getCookTime();
	}

	@SideOnly(Side.CLIENT)
	public int getRemainingTimeScaled(int scale) {
		if (currentItemBurnTime == 0) {
			currentItemBurnTime = getCookTime();
		}
		return furnaceBurnTime * scale / currentItemBurnTime;
	}

	/* IINVENTORY */
	@Override
	public int getSizeInventory() {
		return SIZE;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inv[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (inv[slot] != null) {
			ItemStack stack;

			if (inv[slot].stackSize <= amount) {
				stack = inv[slot];
				inv[slot] = null;
				return stack;
			} else {
				stack = inv[slot].splitStack(amount);

				if (inv[slot].stackSize <= 0) {
					inv[slot] = null;
				}

				return stack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (inv[slot] != null) {
			ItemStack stack = inv[slot];
			inv[slot] = null;
			return stack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inv[slot] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
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

	/* ISIDEDINVENTORY */
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return side == 0 ? SLOTS_BOTTOM : (side == 1 ? SLOTS_TOP : SLOTS_SIDE);
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return side != 0 || side != 1 || stack.getItem() == Items.bucket;
	}

}
