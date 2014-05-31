package com.edxmod.electrodynamics.api.recipe;

import com.edxmod.electrodynamics.api.tool.ToolDefinition;
import net.minecraft.item.ItemStack;

/**
 * @author dmillerw
 */
public interface ITableManager {

	public void registerRecipe(Object input, Object output, ToolDefinition tool, float durability);

}
