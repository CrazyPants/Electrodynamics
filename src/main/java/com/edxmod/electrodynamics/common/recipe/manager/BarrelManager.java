package com.edxmod.electrodynamics.common.recipe.manager;

import com.edxmod.electrodynamics.common.recipe.wrapper.BarrelDurationRecipe;
import com.edxmod.electrodynamics.common.recipe.wrapper.BarrelInteractionRecipe;
import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * @author dmillerw
 */
public class BarrelManager {

    private List<BarrelDurationRecipe> durationRecipes = Lists.newArrayList();
    private List<BarrelInteractionRecipe> interactionRecipes = Lists.newArrayList();

    public void registerDurationRecipe(BarrelDurationRecipe recipe) {
        durationRecipes.add(recipe);
    }

    public void registerInteractionRecipe(BarrelInteractionRecipe recipe) {
        interactionRecipes.add(recipe);
    }

    public BarrelDurationRecipe getDurationRecipe(ItemStack stack) {
        for (BarrelDurationRecipe recipe : durationRecipes) {
            if (recipe.isValid(stack)) {
                return recipe;
            }
        }
        return null;
    }

    public BarrelInteractionRecipe getInteractionRecipe(ItemStack input, ItemStack interact) {
        for (BarrelInteractionRecipe recipe : interactionRecipes) {
            if (recipe.isValid(input, interact)) {
                return recipe;
            }
        }
        return null;
    }
}
