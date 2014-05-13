package com.edxmod.electrodynamics.api;

import com.edxmod.electrodynamics.common.lib.EDXProps;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author Royalixor
 */
public class EDXItemHelper {

    public static Item get(String name) {
        return GameRegistry.findItem(EDXProps.ID, name);
    }

    public static ItemStack get(String name, int meta) {
        return new ItemStack(get(name), 0, meta);
    }

    public static String getUniqueName(Item item) {
        return GameData.itemRegistry.getNameForObject(item);
    }

}
