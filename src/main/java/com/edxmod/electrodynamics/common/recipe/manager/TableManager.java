package com.edxmod.electrodynamics.common.recipe.manager;

import com.edxmod.electrodynamics.common.lib.tool.ToolDefinition;
import com.edxmod.electrodynamics.common.recipe.wrapper.TableRecipe;
import com.edxmod.electrodynamics.common.util.DurabilityMapping;
import com.edxmod.electrodynamics.common.util.StackHelper;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmillerw
 */
public class TableManager {

	private List<TableRecipe> recipes = new ArrayList<TableRecipe>();

	public void registerHammerRecipe(Object input, Object output, float durability) {
		registerRecipe(input, output, ToolDefinition.HAMMER, durability);
	}

	public void registerRecipe(Object input, Object output, ToolDefinition tool, float durability) {
		if (input == null || output == null || tool == null || durability < 0F) {
			return;
		}

		ItemStack[] in = StackHelper.convert(input);
		ItemStack[] out = StackHelper.convert(output);

		if (out.length > 0) {
			for (ItemStack stack : in) {
				DurabilityMapping.INSTANCE.registerDurablity(stack, durability);
				register(new TableRecipe(stack, out[0], tool, true, true));
			}
		}
	}

	public void register(TableRecipe recipe) {
		recipes.add(recipe);
	}

	public TableRecipe get(ItemStack input, ItemStack tool) {
		for (TableRecipe recipe : recipes) {
			if (recipe.isInput(input, tool)) {
				return recipe;
			}
		}
		return null;
	}

	public TableRecipe get(ItemStack input, ToolDefinition tool) {
		for (TableRecipe recipe : recipes) {
			if (recipe.isInput(input, tool)) {
				return recipe;
			}
		}
		return null;
	}
}
