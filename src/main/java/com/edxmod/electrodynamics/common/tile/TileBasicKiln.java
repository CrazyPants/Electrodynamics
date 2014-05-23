package com.edxmod.electrodynamics.common.tile;

import com.edxmod.electrodynamics.common.inventory.InventoryItem;
import com.edxmod.electrodynamics.common.recipe.RecipeManager;
//import com.edxmod.electrodynamics.common.recipe.generic.GenericRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Royalixor
 */
public class TileBasicKiln extends TileCore {

    public boolean open = false;

    public float currentAngle = 0.0F;

    public InventoryItem tray;

    private int totalCookTime = 0;
    private int currentCookTime = 0;

    //TODO Fuel support

    @Override
    public void onPoke() {
        open = !open;
    }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) {
            currentAngle += (open ? 10F : -10F);

            if (currentAngle <= 0F) {
                currentAngle = 0F;
            } else if (currentAngle >= 90F) {
                currentAngle = 90F;
            }
        } else {
            if (tray != null && tray.getStackInSlot(0) != null) {
                if (!open) {
                    if (totalCookTime > 0 && currentCookTime < totalCookTime) {
                        currentCookTime++;
                    } else {
                        if (totalCookTime > 0) {
                            // Cook
                            totalCookTime = currentCookTime = 0;

//                            GenericRecipe out = RecipeManager.INSTANCE.kiln.get(tray.getStackInSlot(0));
//                            tray.setInventorySlotContents(0, out.getOutput(tray.getStackInSlot(0)));
//                            tray.markDirty();
                        } else {
                            // Check
//                            GenericRecipe out = RecipeManager.INSTANCE.kiln.get(tray.getStackInSlot(0));
//
//                            if (out != null) {
//                                totalCookTime = 200;
//                                currentCookTime = 0;
//                            }
                        }
                    }
                }
            } else {
                totalCookTime = currentCookTime = 0;
            }
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbt) {
        open = nbt.getBoolean("open");

        if (nbt.hasKey("tray")) {
            tray = new InventoryItem(ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("tray")), 1);
        } else {
            tray = null;
        }

        currentCookTime = nbt.getInteger("currentCookTime");
        totalCookTime = nbt.getInteger("totalCookTime");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbt) {
        nbt.setBoolean("open", open);

        if (tray != null) {
            NBTTagCompound tag = new NBTTagCompound();
            tray.getStack().writeToNBT(tag);
            nbt.setTag("tray", tag);
        }

        nbt.setInteger("currentCookTime", currentCookTime);
        nbt.setInteger("totalCookTime", totalCookTime);
    }
}
