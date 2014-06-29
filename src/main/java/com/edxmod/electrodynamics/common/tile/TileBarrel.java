package com.edxmod.electrodynamics.common.tile;

import com.edxmod.electrodynamics.common.recipe.wrapper.BarrelRecipe;
import com.edxmod.electrodynamics.common.tile.nbt.NBTHandler;
import com.edxmod.electrodynamics.common.util.StackHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author dmillerw
 */
public class TileBarrel extends TileCore {

	public static final float DIMENSION_MIN = 0.0625F;
	public static final float DIMENSION_MAX = 0.9375F;
	public static final float DIMENSION_FILL = DIMENSION_MAX - DIMENSION_MIN;

	private BarrelRecipe recipe;

	@NBTHandler.NBTData
	public ItemStack contents;

	private int duration = 0;

	@SideOnly(Side.CLIENT)
	public Block blockCache;

	@SideOnly(Side.CLIENT)
	public int maxStackSize = 1;

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (duration > 0) {
				duration--;
			}

			if (duration == 0 && recipe != null) {
				contents = recipe.get(contents)[0];
				updateContents();

				recipe = null;
			}
		}
	}

	@Override
	public void onClientUpdate(NBTTagCompound nbt) {
		if (nbt.hasKey("contents")) {
			contents = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("contents"));
			blockCache = Block.getBlockFromItem(contents.getItem());

			if (contents.getItem() == Items.water_bucket) {
				blockCache = Blocks.water;
			} else if (contents.getItem() == Items.lava_bucket) {
				blockCache = Blocks.lava;
			}
		} else {
			contents = null;
			blockCache = null;
		}

		if (nbt.hasKey("max")) {
			maxStackSize = nbt.getInteger("max");
		}
	}

	private void updateContents() {
		NBTTagCompound nbt = new NBTTagCompound();
		if (contents != null) {
			NBTTagCompound tag = new NBTTagCompound();
			contents.writeToNBT(tag);
			nbt.setTag("contents", tag);
		}
		nbt.setInteger("max", recipe.maxStackSize);
		sendClientUpdate(nbt);
	}

	public boolean addWithUpdate(ItemStack stack) {
		boolean result = add(stack);
		if (result) {
			updateContents();
		}
		return result;
	}

	public boolean add(ItemStack stack) {
		if (stack == null || (contents != null && !StackHelper.areStacksSimilar(contents, stack, true))) {
			return false;
		}

		BarrelRecipe temp = new BarrelRecipe(new ItemStack(Blocks.dirt, 8, 1), new ItemStack(Blocks.grass), true, 8, 20, 0);

		if (temp == null) {
			return false;
		}

		if (contents != null) {
			if (contents.stackSize + 1 > recipe.maxStackSize) {
				return false;
			}

			contents.stackSize++;
			stack.stackSize--;
		} else {
			recipe = temp;
			contents = stack.copy();
			contents.stackSize = 1;
			stack.stackSize--;
		}

		if (recipe.exactStackSize) {
			if (contents.stackSize == recipe.input.stackSize) {
				duration = recipe.duration;
			}
		} else {
			duration = recipe.duration;
		}

		return true;
	}
}
