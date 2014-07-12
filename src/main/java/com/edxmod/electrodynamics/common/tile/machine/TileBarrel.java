package com.edxmod.electrodynamics.common.tile.machine;

import com.edxmod.electrodynamics.common.recipe.EDXRecipes;
import com.edxmod.electrodynamics.common.recipe.wrapper.BarrelDurationRecipe;
import com.edxmod.electrodynamics.common.recipe.wrapper.BarrelInteractionRecipe;
import com.edxmod.electrodynamics.common.tile.core.TileCore;
import com.edxmod.electrodynamics.common.network.nbt.NBTHandler;
import com.edxmod.electrodynamics.common.util.StackHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.Random;

/**
 * @author dmillerw
 */
public class TileBarrel extends TileCore {

	public static final float DIMENSION_MIN = 0.0625F;
	public static final float DIMENSION_MAX = 0.875F;
	public static final float DIMENSION_FILL = DIMENSION_MAX - DIMENSION_MIN;

	public static Random random = new Random();

	@NBTHandler.NBTData
	public ItemStack contents;

	@NBTHandler.NBTData
	public int maxDuration = 0;

	@NBTHandler.NBTData
	public int duration = 0;

	/**
	 * For the benefit of the client...
	 */
	@NBTHandler.NBTData
	public int maxStackSize = 1;

	@NBTHandler.NBTData
	public boolean hasLid = false;

	@Override
	public void updateEntity() {
		if (maxDuration > 0) {
			if (contents != null) {
				if (duration > 0) {
					duration--;
				}

				if (duration == 0) {
					if (!worldObj.isRemote) {
						BarrelDurationRecipe recipe = EDXRecipes.BARREL.getDurationRecipe(contents);

						if (recipe != null && contents.stackSize == recipe.input.stackSize && (!recipe.requireLid || hasLid)) {
							contents = recipe.getOutput();
							maxDuration = duration = 0;

							maxStackSize = recipe.output.stackSize;

							markForUpdate();
						}
					}
				}
			}
		} else {
			if (contents != null) {
				if (!worldObj.isRemote) {
					BarrelDurationRecipe recipe = EDXRecipes.BARREL.getDurationRecipe(contents);

					if (recipe != null && (!recipe.requireLid || hasLid)) {
						maxDuration = duration = recipe.getDuration();
					}
				}
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

			maxDuration = 0;
			duration = 0;

			markForUpdate();

			return true;
		} else {
			contents = stack.copy();
			contents.stackSize = 1;

			stack.stackSize--;

			maxDuration = 0;
			duration = 0;

			markForUpdate();

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

		markForUpdate();

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
			markForUpdate();

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

			markForUpdate();

			return true;
		}
	}
}
