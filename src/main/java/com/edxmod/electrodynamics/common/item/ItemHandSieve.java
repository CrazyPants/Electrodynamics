package com.edxmod.electrodynamics.common.item;

import com.edxmod.electrodynamics.Electrodynamics;
import com.edxmod.electrodynamics.common.core.EDXCreativeTab;
import com.edxmod.electrodynamics.common.core.handler.GuiHandler;
import com.edxmod.electrodynamics.common.inventory.InventoryItem;
import com.edxmod.electrodynamics.common.item.prefab.ItemEDX;
import com.edxmod.electrodynamics.common.recipe.RecipeManager;
import com.edxmod.electrodynamics.common.recipe.manager.SieveManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * @author dmillerw
 */
public class ItemHandSieve extends ItemEDX {

	public static void process(ItemStack stack, EntityPlayer player) {
		InventoryItem inventoryItem = new InventoryItem(stack, 1);
		ItemStack stack1 = inventoryItem.getStackInSlot(0);

		if (stack1 != null) {
			SieveManager.SieveRecipe recipe = RecipeManager.INSTANCE.sieve.get(stack1);

			for (ItemStack stack2 : recipe.getOutput()) {
				player.dropPlayerItemWithRandomChoice(stack2, false);
			}

			stack1.stackSize--;
			if (stack1.stackSize <= 0) {
				inventoryItem.setInventorySlotContents(0, null);
			}
			inventoryItem.markDirty();
		}
	}

	public static int getCurrentDuration(ItemStack stack) {
		if (!stack.hasTagCompound()) {
			return 0;
		}

		NBTTagCompound nbt = stack.getTagCompound();

		if (!nbt.hasKey("current")) {
			return 0;
		}

		return nbt.getInteger("current");
	}

	public static int getMaxDuration(ItemStack stack) {
		if (!stack.hasTagCompound()) {
			return 0;
		}

		NBTTagCompound nbt = stack.getTagCompound();

		if (!nbt.hasKey("max")) {
			return 0;
		}

		return nbt.getInteger("max");
	}

	public static void setCurrentDuration(ItemStack stack, int duration) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}

		NBTTagCompound nbt = stack.getTagCompound();

		nbt.setInteger("current", duration);

		stack.setTagCompound(nbt);
	}

	public static void setMaxDuration(ItemStack stack, int duration) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}

		NBTTagCompound nbt = stack.getTagCompound();

		nbt.setInteger("max", duration);

		stack.setTagCompound(nbt);
	}

	public ItemHandSieve() {
		super(EDXCreativeTab.TOOLS);

		setMaxDamage(0);
		setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			if (player.isSneaking()) {
				player.openGui(Electrodynamics.instance, GuiHandler.GUI_HAND_SIEVE, world, 0, 0, 0);
			} else {
				if (getMaxDuration(stack) > 0) {
					player.setItemInUse(stack, getMaxDuration(stack));
				} else {
					InventoryItem inventoryItem = new InventoryItem(stack, 1);
					ItemStack stack1 = inventoryItem.getStackInSlot(0);

					if (stack1 != null) {
						SieveManager.SieveRecipe recipe = RecipeManager.INSTANCE.sieve.get(stack1);

						setCurrentDuration(stack, 0);
						setMaxDuration(stack, recipe.getDuration());
					}
				}
			}
		}

		return stack;
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
		int current = getCurrentDuration(stack);
		int max = getMaxDuration(stack);

		if (current < max) {
			setCurrentDuration(stack, current + 1);
		} else {
			process(stack, player);
			setCurrentDuration(stack, 0);
			setMaxDuration(stack, 0);
		}
	}

	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.bow;
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		int current = getCurrentDuration(stack);
		int max = getMaxDuration(stack);

		return max > 0 ? max / current : 1;
	}

	@Override
	public String getIcon() {
		return "tools/handSieve";
	}
}
