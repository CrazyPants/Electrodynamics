package com.edxmod.electrodynamics.common.util;

import cpw.mods.fml.common.registry.GameData;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * @author dmillerw
 */
public class ItemHelper {

    public static int getID(ItemStack stack) {
        return getID(stack.getItem());
    }

    public static int getID(Block block) {
        return GameData.getBlockRegistry().getId(block);
    }

    public static int getID(Item item) {
        return GameData.getItemRegistry().getId(item);
    }

    public static boolean isBlock(ItemStack stack, Block block) {
        return getID(stack) == getID(block);
    }

    public static ItemStack convertToItemStack(Object object) {
        if (object instanceof ItemStack) {
            ItemStack itemStack = (ItemStack) object;
            if (itemStack.getItemDamage() < 0) {
                itemStack.setItemDamage(0);
            }
            return itemStack;
        }

        if (object instanceof Block) {
            return new ItemStack(Block.getBlockById(ItemHelper.getID((Block) object)), 1, OreDictionary.WILDCARD_VALUE);
        }

        if (object instanceof Item) {
            return new ItemStack(Item.getItemById(ItemHelper.getID((Item) object)), 1, OreDictionary.WILDCARD_VALUE);
        }
        return null;
    }

}
