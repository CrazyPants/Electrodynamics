package com.edxmod.electrodynamics.api.recipe;

import com.edxmod.electrodynamics.api.tool.ToolDefinition;
import net.minecraft.item.ItemStack;

/**
 * @author dmillerw
 */
public interface ITableManager {

	public void registerDurablity(ItemStack stack, float durability);

	public void registerRecipe(Object input, ToolDefinition tool, Object output);

}
