package com.edxmod.electrodynamics.api.tool;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.*;

/**
 * @author dmillerw
 */
public enum ToolDefinition {

	HAMMER,
	AXE,
	PICKAXE,
	SWORD,
	SHOVEL,
	HOE,
	HAND;

	private static Map<ToolDefinition, List<ItemStack>> toolDefinitionMap = new EnumMap<ToolDefinition, List<ItemStack>>(ToolDefinition.class);
	private static Map<ItemStack, Float> toolStrengthMap = new HashMap<ItemStack, Float>();

	static {
		for (ToolDefinition definition : ToolDefinition.values()) {
			toolDefinitionMap.put(definition, new ArrayList<ItemStack>());
		}
	}

	public static void register(ItemStack tool, ToolDefinition definition, float strength) {
		register(tool, definition, strength, false);
	}

	public static void register(ItemStack tool, ToolDefinition definition, float strength, boolean useDamage) {
		if (!useDamage) {
			tool.setItemDamage(OreDictionary.WILDCARD_VALUE);
		}

		List<ItemStack> toolList = toolDefinitionMap.get(definition);
		toolList.add(tool);
		toolDefinitionMap.put(definition, toolList);

		toolStrengthMap.put(tool, strength);
	}

	public static boolean isType(ItemStack stack, ToolDefinition definition) {
		if (stack == null && definition == HAND) return true;
		List<ItemStack> toolList = toolDefinitionMap.get(definition);
		for (ItemStack stack1 : toolList) {
			if (stack != null) {
				if (stack.isItemEqual(stack1)) return true;
			}
		}
		return false;
	}

	public static float getStrength(ItemStack stack) {
		for (Map.Entry<ItemStack, Float> entry : toolStrengthMap.entrySet()) {
			if (entry.getKey().isItemEqual(stack)) return entry.getValue();
		}
		return 0F;
	}

}
