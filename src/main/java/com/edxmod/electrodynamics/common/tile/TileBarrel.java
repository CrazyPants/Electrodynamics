package com.edxmod.electrodynamics.common.tile;

import com.edxmod.electrodynamics.common.recipe.EDXRecipes;
import com.edxmod.electrodynamics.common.recipe.wrapper.BarrelDurationRecipe;
import com.edxmod.electrodynamics.common.recipe.wrapper.BarrelInteractionRecipe;
import com.edxmod.electrodynamics.common.tile.nbt.NBTHandler;
import com.edxmod.electrodynamics.common.util.StackHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Random;

/**
 * @author dmillerw
 */
public class TileBarrel extends TileCore {

	public static final float DIMENSION_MIN = 0.0625F;
	public static final float DIMENSION_MAX = 0.9375F;
	public static final float DIMENSION_FILL = DIMENSION_MAX - DIMENSION_MIN;

	public static Random random = new Random();

	@NBTHandler.NBTData
	public ItemStack contents;

	@NBTHandler.NBTData
	public int duration = -1;

	/** For the benefit of the client... */
	public int maxStackSize = 1;

	@Override
	public void onClientUpdate(NBTTagCompound nbt) {
		if (nbt.hasKey("contents")) {
			contents = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("contents"));
			maxStackSize = nbt.getInteger("max");
		} else if (nbt.hasKey("contents_null")) {
			contents = null;
		}
	}

	private void syncContents() {
		NBTTagCompound nbt = new NBTTagCompound();
		if (contents != null) {
			NBTTagCompound contents = new NBTTagCompound();
			this.contents.writeToNBT(contents);
			nbt.setTag("contents", contents);
			nbt.setInteger("max", maxStackSize);
		} else {
			nbt.setBoolean("contents_null", true);
		}
		sendClientUpdate(nbt);
	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (contents != null) {
				if (duration > 0) {
					duration--;
				}

				if (duration == 0) {
					BarrelDurationRecipe recipe = EDXRecipes.BARREL.getDurationRecipe(contents);

					if (recipe != null && contents.stackSize == recipe.input.stackSize) {
						contents = recipe.getOutput();
						duration = -1;

						maxStackSize = recipe.output.stackSize;

						syncContents();
					}
				} else if (duration == -1) {
					BarrelDurationRecipe recipe = EDXRecipes.BARREL.getDurationRecipe(contents);

					if (recipe != null) {
						duration = recipe.getDuration();
					}
				}
			} else {
				duration = -1;
			}
		}
	}

	public boolean addItem(EntityPlayer player, ItemStack stack) {
		if (stack == null || (contents != null && !StackHelper.areStacksSimilar(contents, stack, true))) {
			return false;
		}

		BarrelDurationRecipe durationRecipe = EDXRecipes.BARREL.getDurationRecipe(stack);
		BarrelInteractionRecipe interactionRecipe = EDXRecipes.BARREL.getInteractionRecipe(stack, null);

		if (durationRecipe == null && interactionRecipe == null) {
			return false;
		}

		maxStackSize = durationRecipe != null ? durationRecipe.input.stackSize : interactionRecipe.input.stackSize;

		if (contents != null) {
			if ((contents.stackSize + 1) > maxStackSize) {
				return false;
			}

			contents.stackSize++;
			stack.stackSize--;

			duration = -1;

			syncContents();

			return true;
		} else {
			contents = stack.copy();
			contents.stackSize = 1;

			stack.stackSize--;

			duration = -1;

			syncContents();

			return true;
		}
	}

	public boolean interact(EntityPlayer player, ItemStack stack) {
		if (contents == null || stack == null) {
			return false;
		}

		BarrelInteractionRecipe recipe = EDXRecipes.BARREL.getInteractionRecipe(contents, stack);

		if (recipe == null) {
			return false;
		}

		contents = recipe.getOutput();
		stack.stackSize--;

		maxStackSize = contents.stackSize;

		duration = -1;

		syncContents();

		return true;
	}

	public boolean removeItem(EntityPlayer player) {
		if (contents == null) {
			return false;
		}

		ItemStack held = player.getHeldItem();

		if (held == null) {
			player.setCurrentItemOrArmor(0, contents.copy());
			contents = null;
			syncContents();

			return true;
		} else {
			if (!StackHelper.areStacksSimilar(contents, held, true)) {
				return false;
			}

			if ((held.stackSize + contents.stackSize) > held.getMaxStackSize()) {
				return false;
			}

			held.stackSize += contents.stackSize;

			contents = null;

			syncContents();

			return true;
		}
	}
}
