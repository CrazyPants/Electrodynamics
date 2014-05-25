package com.edxmod.electrodynamics.common.tile;

import com.edxmod.electrodynamics.common.block.EDXBlocks;
import com.edxmod.electrodynamics.common.item.ItemHammer;
import com.edxmod.electrodynamics.common.network.PacketFX;
import com.edxmod.electrodynamics.common.recipe.RecipeManager;
import com.edxmod.electrodynamics.common.recipe.manager.TableManager;
import com.edxmod.electrodynamics.common.util.ItemHelper;
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

	public float durability;

    public byte stackRotation = 0;

    @Override
    public void readCustomNBT(NBTTagCompound nbt) {
        if (nbt.hasKey("stack")) {
            stack = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("stack"));
        } else {
            stack = null;
        }

		durability = nbt.getFloat("durability");
        stackRotation = nbt.getByte("rotation");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbt) {
        if (stack != null) {
            NBTTagCompound stackNBT = new NBTTagCompound();
            stack.writeToNBT(stackNBT);
            nbt.setTag("stack", stackNBT);
        }

		nbt.setFloat("durability", durability);
        nbt.setByte("rotation", stackRotation);
    }

    public void rotate() {
        stackRotation++;
        if (stackRotation > 3) {
            stackRotation = 0;
        }

        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

	public void setStack(ItemStack stack) {
		this.stack = stack;

		TableManager.TableRecipe output = RecipeManager.INSTANCE.table.get(stack);

		if (output != null) {
			durability = output.getDurability();
		}

		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

    public void smash(EntityPlayer player) {
        int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		ItemStack hammer = player.getCurrentEquippedItem();
        Random random = new Random();

        if (meta == 0) {
            if (stack != null && ItemHelper.isBlock(stack, Blocks.stone_slab) && stack.getItemDamage() == 0) {
                PacketFX.breakFX(worldObj, xCoord, yCoord, zCoord, new ItemStack(Blocks.stone_slab));
                getWorldObj().playSoundEffect(xCoord, yCoord, zCoord, "edx:oreCrumble", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);

                stack = null;
                worldObj.setBlock(xCoord, yCoord, zCoord, EDXBlocks.table, 1, 3);
            }
        } else if (meta == 1) {
            if (stack != null) {
                TableManager.TableRecipe output = RecipeManager.INSTANCE.table.get(stack);

                if (output != null) {
					durability -= ItemHammer.STRENGTH[hammer.getItemDamage()];

                    if (durability <= 0F) {
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

}
