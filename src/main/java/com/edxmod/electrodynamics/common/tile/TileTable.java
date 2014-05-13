package com.edxmod.electrodynamics.common.tile;

import com.edxmod.electrodynamics.api.EDXBlockHelper;
import com.edxmod.electrodynamics.common.network.PacketFX;
import com.edxmod.electrodynamics.common.recipe.generic.GenericRecipe;
import com.edxmod.electrodynamics.common.recipe.RecipeManager;
import com.edxmod.electrodynamics.common.util.UtilItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Random;

/**
 * @author dmillerw
 */
public class TileTable extends TileCore {

    public ItemStack stack;

    public byte stackRotation = 0;

    @Override
    public void readCustomNBT(NBTTagCompound nbt) {
        if (nbt.hasKey("stack")) {
            stack = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("stack"));
        } else {
            stack = null;
        }

        stackRotation = nbt.getByte("rotation");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbt) {
        if (stack != null) {
            NBTTagCompound stackNBT = new NBTTagCompound();
            stack.writeToNBT(stackNBT);
            nbt.setTag("stack", stackNBT);
        }

        nbt.setByte("rotation", stackRotation);
    }

    public void rotate() {
        stackRotation++;
        if (stackRotation > 3) {
            stackRotation = 0;
        }

        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    public void smash(EntityPlayer player) {
        int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        Random random = new Random();

        if (meta == 0) {
            if (stack != null && UtilItem.isBlock(stack, Blocks.stone_slab) && stack.getItemDamage() == 0) {
                PacketFX.breakFX(worldObj, xCoord, yCoord, zCoord, new ItemStack(Blocks.stone_slab));
                getWorldObj().playSoundEffect(xCoord, yCoord, zCoord, "edx:oreCrumble", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);

                stack = null;
                worldObj.setBlock(xCoord, yCoord, zCoord, EDXBlockHelper.get("table"), 1, 3);
            }
        } else if (meta == 1) {
            if (stack != null) {
                GenericRecipe output = RecipeManager.INSTANCE.table.get(stack);

                if (output != null) {
                    if (stack.getItem() instanceof ItemBlock) {
                        PacketFX.breakFX(worldObj, xCoord, yCoord, zCoord, stack);
                        getWorldObj().playSoundEffect(xCoord, yCoord, zCoord, "edx:oreCrumble", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                    }

                    stack = output.getOutput(stack);
                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                }
            }
        }
    }

}
