package com.edxmod.electrodynamics.common.recipe.manager;

import com.edxmod.electrodynamics.api.recipe.ITableManager;
import com.edxmod.electrodynamics.api.tool.ToolDefinition;
import com.edxmod.electrodynamics.common.util.ItemHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author dmillerw
 */
public class TableManager implements ITableManager {

	public static class TableRecipe {
		private final ItemStack input;
		private final ToolDefinition tool;
		private final ItemStack output;

		private final boolean ignoreNBT;
		public final boolean damageTool;

		public TableRecipe(ItemStack input, ToolDefinition tool, ItemStack output, boolean ignoreNBT, boolean damageTool) {
			this.input = input;
			this.tool = tool;
			this.output = output;
			this.ignoreNBT = ignoreNBT;
			this.damageTool = damageTool;
		}

		public boolean isInput(ItemStack stack, ItemStack tool) {
			return isInputStack(stack) && isTool(tool);
		}

		private boolean isInputStack(ItemStack stack) {
			if (stack.getItemDamage() == OreDictionary.WILDCARD_VALUE || input.getItemDamage() == OreDictionary.WILDCARD_VALUE) {
				return stack.getItem() == input.getItem() && (ignoreNBT || ItemStack.areItemStackTagsEqual(stack, input));
			} else {
				return ((stack.getItem() == input.getItem()) && stack.getItemDamage() == input.getItemDamage()) && (ignoreNBT || ItemStack.areItemStacksEqual(stack, input));
			}
		}

		private boolean isTool(ItemStack stack) {
			return (ToolDefinition.isType(stack, tool));
		}

		public ItemStack getOutput(boolean equivalentSize) {
			ItemStack out = output.copy();
			if (equivalentSize) {
				out.stackSize = input.stackSize;
			}
			return out;
		}
	}

	private Set<TableRecipe> recipes = new HashSet<TableRecipe>();

	private Map<ItemStack, Float> durabilityMapping = new HashMap<ItemStack, Float>();

	public void registerDurablity(ItemStack stack, float durability) {
		durabilityMapping.put(stack, durability);
	}

	public float getDurability(ItemStack stack) {
		for (Map.Entry<ItemStack, Float> entry : durabilityMapping.entrySet()) {
			if (entry.getKey().isItemEqual(stack)) return entry.getValue();
		}
		return 1F;
	}

	public void registerRecipe(Object input, ToolDefinition tool, Object output) {
		if (input == null || tool == null || output == null) {
			return;
		}

		register(new TableRecipe(ItemHelper.convertToItemStack(input), tool, ItemHelper.convertToItemStack(output), true, true));
	}

	public void registerHammerRecipe(Object input, Object output) {
		if (input == null || output == null) {
			return;
		}

		register(new TableRecipe(ItemHelper.convertToItemStack(input), ToolDefinition.HAMMER, ItemHelper.convertToItemStack(output), true, true));
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

}
