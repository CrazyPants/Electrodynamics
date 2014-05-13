package com.edxmod.electrodynamics.api;

import com.edxmod.electrodynamics.common.lib.EDXProps;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

/**
 * @author Royalixor
 */
public class EDXBlockHelper {

    public static Block get(String name) {
        return GameRegistry.findBlock(EDXProps.ID, name);
    }

    public static ItemStack get(String name, int meta) {
        return new ItemStack(get(name), 0, meta);
    }

    public static String getUniqueName(Block block) {
        return GameData.blockRegistry.getNameForObject(block);
    }

}
