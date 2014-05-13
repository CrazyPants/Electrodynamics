package com.edxmod.electrodynamics.common.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * @author Royalixor
 */
public class EDXItems {

    public static void init() {
        registerItems();
    }

    private static void registerItems() {
        registerItem(new ItemGrindings().setUnlocalizedName("grindings"));
        registerItem(new ItemDust().setUnlocalizedName("dust"));
        registerItem(new ItemTool().setUnlocalizedName("tool"));
        registerItem(new ItemHammer().setUnlocalizedName("hammer"));
        //registerItem(new ItemSeeds().setUnlocalizedName("seeds"));
    }

    public static void registerItem(Item item) {
        GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
    }
}
