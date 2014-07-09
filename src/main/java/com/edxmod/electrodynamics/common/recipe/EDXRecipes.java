package com.edxmod.electrodynamics.common.recipe;

import com.edxmod.electrodynamics.common.block.BlockStorage;
import com.edxmod.electrodynamics.common.item.EDXItems;
import com.edxmod.electrodynamics.common.item.resource.ItemResource;
import com.edxmod.electrodynamics.common.lib.StackReference;
import com.edxmod.electrodynamics.common.lib.tool.ToolDefinition;
import com.edxmod.electrodynamics.common.recipe.manager.BarrelManager;
import com.edxmod.electrodynamics.common.recipe.manager.GenericRecipeManager;
import com.edxmod.electrodynamics.common.recipe.manager.TableManager;
import com.edxmod.electrodynamics.common.recipe.wrapper.BarrelDurationRecipe;
import com.edxmod.electrodynamics.common.recipe.wrapper.BarrelInteractionRecipe;
import com.edxmod.electrodynamics.common.recipe.wrapper.SieveRecipe;
import com.edxmod.electrodynamics.common.util.StackHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * @author dmillerw
 */
public class EDXRecipes {

	public static TableManager TABLE = new TableManager();

	public static GenericRecipeManager<SieveRecipe> SIEVE = new GenericRecipeManager<SieveRecipe>();

	public static BarrelManager BARREL = new BarrelManager();

    public static void initialize() {
		// TABLE - STONE
		TABLE.registerHammerRecipe(StackReference.STONE,       StackReference.COBBLESTONE, 1F);
		TABLE.registerHammerRecipe(StackReference.COBBLESTONE, StackReference.GRAVEL,      1F);
		TABLE.registerHammerRecipe(StackReference.GRAVEL,      StackReference.SAND,        1F);
		TABLE.registerHammerRecipe(StackReference.SAND,        StackReference.FINE_SAND,   1F);

		// TABLE - NETHERRACK
		TABLE.registerHammerRecipe(StackReference.NETHER_RIND, StackReference.NETHERRACK,  1F);
		TABLE.registerHammerRecipe(StackReference.NETHERRACK,  StackReference.NETHER_GRIT, 1F);
		TABLE.registerHammerRecipe(StackReference.NETHER_GRIT, StackReference.SOUL_SAND,   1F);
		TABLE.registerHammerRecipe(StackReference.SOUL_SAND,   StackReference.SOUL_DUST,   1F);

		// TABLE - WOOD
		for (int i=0; i<4; i++) {
			TABLE.registerRecipe(new ItemStack(Blocks.log, 1, i), new ItemStack(Blocks.planks, 6, i),  ToolDefinition.AXE, 1F);
		}

		// BARREL - TEST
		BARREL.registerInteractionRecipe(new BarrelInteractionRecipe(new ItemStack(Blocks.dirt, 8), new ItemStack(Items.wheat_seeds), new ItemStack(Blocks.grass, 8)));
		BARREL.registerDurationRecipe(new BarrelDurationRecipe(new ItemStack(Blocks.grass, 8), new ItemStack(Blocks.mycelium, 1), 20, 20, true));

		// BARREL - TEST 2
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
}
