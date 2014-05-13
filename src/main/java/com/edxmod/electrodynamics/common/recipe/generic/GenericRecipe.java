package com.edxmod.electrodynamics.common.recipe.generic;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * @author dmillerw
 */
public class GenericRecipe {

    private final ItemStack input;
    private final ItemStack output;

    private boolean ignoreNBT = true;

    public GenericRecipe(ItemStack input, ItemStack output, boolean ignoreNBT) {
        this.input = input;
        this.output = output;
        this.ignoreNBT = ignoreNBT;
    }

    public boolean isInput(ItemStack stack) {
        if (stack.getItemDamage() == OreDictionary.WILDCARD_VALUE || input.getItemDamage() == OreDictionary.WILDCARD_VALUE) {
            return stack.getItem() == input.getItem() && (ignoreNBT || ItemStack.areItemStackTagsEqual(stack, input));
        } else {
            return ((stack.getItem() == input.getItem()) && stack.getItemDamage() == input.getItemDamage()) && (ignoreNBT || ItemStack.areItemStacksEqual(stack, input));
        }
    }

    public ItemStack getOutput(ItemStack input) {
        ItemStack out = output.copy();
        out.stackSize = input.stackSize;
        return out;
    }

}
