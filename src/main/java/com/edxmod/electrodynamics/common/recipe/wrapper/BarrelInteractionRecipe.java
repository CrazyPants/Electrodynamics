package com.edxmod.electrodynamics.common.recipe.wrapper;

import com.edxmod.electrodynamics.common.util.StackHelper;
import net.minecraft.item.ItemStack;

import java.util.Random;

/**
 * @author dmillerw
 */
public class BarrelInteractionRecipe {

    public static final Random random = new Random();

    public final ItemStack input;
    public final ItemStack interact;
    public final ItemStack output;

    public BarrelInteractionRecipe(ItemStack input, ItemStack interact, ItemStack output) {
        this.input = input;
        this.interact = interact;
        this.output = output;
    }

    public boolean isValid(ItemStack input, ItemStack interact) {
        return StackHelper.areStacksSimilar(this.input, input, true) && (interact == null || StackHelper.areStacksSimilar(this.interact, interact, true));
    }

    public ItemStack getOutput() {
        return output.copy();
    }
}
