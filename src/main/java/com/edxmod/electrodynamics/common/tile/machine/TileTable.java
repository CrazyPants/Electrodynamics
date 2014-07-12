package com.edxmod.electrodynamics.common.tile.machine;

import com.edxmod.electrodynamics.common.lib.tool.ToolDefinition;
import com.edxmod.electrodynamics.common.lib.DurabilityMapping;
import com.edxmod.electrodynamics.common.block.EDXBlocks;
import com.edxmod.electrodynamics.common.network.PacketFX;
import com.edxmod.electrodynamics.common.recipe.EDXRecipes;
import com.edxmod.electrodynamics.common.recipe.wrapper.TableRecipe;
import com.edxmod.electrodynamics.common.tile.core.TileCore;
import com.edxmod.electrodynamics.common.network.nbt.NBTHandler;
import com.edxmod.electrodynamics.common.util.InventoryHelper;
import com.edxmod.electrodynamics.common.util.ItemHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

/**
 * @author dmillerw
 */
public class TileTable extends TileCore {

	@NBTHandler.NBTData
    public ItemStack stack;

	@NBTHandler.NBTData
	public float durability;

	@Override
	public void onBlockBroken() {
		if (stack != null) {
			InventoryHelper.dropItem(worldObj, xCoord, yCoord, zCoord, ForgeDirection.UNKNOWN, stack, new Random());
		}
	}

	public void setStack(ItemStack stack) {
		this.stack = stack;

		if (stack != null) {
			durability = DurabilityMapping.INSTANCE.getDurability(stack);
		}

		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

    public boolean smash(EntityPlayer player) {
        int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		ItemStack tool = player.getCurrentEquippedItem();
        Random random = new Random();

        if (meta == 0) {
            if (stack != null && ItemHelper.isBlock(stack, Blocks.stone_slab) && stack.getItemDamage() == 0 && ToolDefinition.isType(tool, ToolDefinition.HAMMER)) {
                PacketFX.breakFX(worldObj, xCoord, yCoord, zCoord, new ItemStack(Blocks.stone_slab));
                getWorldObj().playSoundEffect(xCoord, yCoord, zCoord, "edx:oreCrumble", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);

                stack = null;
                worldObj.setBlock(xCoord, yCoord, zCoord, EDXBlocks.table, 1, 3);

				return true;
            }
        } else if (meta == 1) {
            if (stack != null) {
                TableRecipe output = EDXRecipes.TABLE.get(stack, tool);

				if (output != null) {
					durability -= ToolDefinition.getStrength(tool);

                    if (durability <= 0F) {
						if (stack.getItem() instanceof ItemBlock) {
							PacketFX.breakFX(worldObj, xCoord, yCoord, zCoord, stack);
						}

						if (output.damageTool) {
							tool.damageItem(1, player);
						}

						stack = output.getOutput(false);
						worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

						return true;
					}
                }
            }
        }

		return false;
    }

}
