package com.edxmod.electrodynamics.common.recipe.wrapper;

import com.edxmod.electrodynamics.api.tool.ToolDefinition;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * @author dmillerw
 */
public class TableRecipe {

	private final ItemStack input;
	private final ItemStack output;

	private final ToolDefinition tool;

	private final boolean ignoreNBT;
	public final boolean damageTool;

	public TableRecipe(ItemStack input, ItemStack output, ToolDefinition tool, boolean ignoreNBT, boolean damageTool) {
		this.input = input;
		this.output = output;
		this.tool = tool;
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
