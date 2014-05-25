package com.edxmod.electrodynamics.common.recipe;

import com.edxmod.electrodynamics.common.block.BlockStorage;
import com.edxmod.electrodynamics.common.block.EDXBlocks;
import com.edxmod.electrodynamics.common.item.EDXItems;
import com.edxmod.electrodynamics.common.item.prefab.EDXItem;
import com.edxmod.electrodynamics.common.item.resource.ItemResource;
import com.edxmod.electrodynamics.common.recipe.RecipeManager;
import com.edxmod.electrodynamics.common.util.StackHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Royalixor
 */
public class EDXRecipes {

    private static List frontRecipes = new ArrayList();

    public static void initialize() {
        addCraftingRecipes();
        addOreRegistration();
        addSmeltingRecipes();
        CraftingManager.getInstance().getRecipeList().addAll(0, frontRecipes);

        // Ours
        RecipeManager.INSTANCE.init();
    }

    private static void addCraftingRecipes() {
		for (int i=0; i<ItemResource.NAMES.length; i++) {
			ItemStack ingot = new ItemStack(EDXItems.resourceIngot, 1, i);
			ItemStack storage = BlockStorage.getStorageFromResource(ingot);

			if (storage != null) {
				GameRegistry.addShapedRecipe(storage, "III", "III", "III", 'I', ingot);
				GameRegistry.addShapelessRecipe(StackHelper.copyAndResize(ingot, 9), storage);
			}
		}
    }

    private static void addOreRegistration() {
    }

    private static void addSmeltingRecipes() {
    }

}
