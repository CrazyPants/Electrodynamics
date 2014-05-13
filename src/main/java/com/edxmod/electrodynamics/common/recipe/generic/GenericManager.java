package com.edxmod.electrodynamics.common.recipe.generic;

import com.edxmod.electrodynamics.common.util.UtilItem;
import net.minecraft.item.ItemStack;

import java.util.HashSet;
import java.util.Set;

/**
 * @author dmillerw
 */
public class GenericManager {

    private Set<GenericRecipe> recipes = new HashSet<GenericRecipe>();

    public void register(Object input, Object output) {
        if (input == null || output == null) {
            return;
        }

        register(new GenericRecipe(UtilItem.convertToItemStack(input), UtilItem.convertToItemStack(output), true));
    }

    public void register(GenericRecipe recipe) {
        recipes.add(recipe);
    }

    public GenericRecipe get(ItemStack input) {
        for (GenericRecipe recipe : recipes) {
            if (recipe.isInput(input)) {
                return recipe;
            }
        }
        return null;
    }

}
