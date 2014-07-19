package com.edxmod.electrodynamics.common.core;

import com.edxmod.electrodynamics.common.block.EDXBlocks;
import com.edxmod.electrodynamics.common.item.EDXItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Locale;

/**
 * @author Royalixor.
 */
public enum EDXCreativeTab {

    BLOCKS,
    ITEMS,
    TOOLS;

    private final CreativeTabs tab;

    private EDXCreativeTab() {
        tab = new Tab();
    }

    public CreativeTabs get() {
        return tab;
    }

    private String getLabel() {
        return "edx." + name().toLowerCase(Locale.ENGLISH);
    }

    private ItemStack getItem() {
        switch (this) {
            case BLOCKS:
                return new ItemStack(EDXBlocks.table, 1, 0);
            case ITEMS:
                return new ItemStack(EDXItems.resourcePebble, 1, 0);
            case TOOLS:
                return new ItemStack(EDXItems.hammerWood, 1, 0);
            default:
                return null;
        }
    }

    public final class Tab extends CreativeTabs {

        private Tab() {
            super(getLabel());
        }

        @Override
        public ItemStack getIconItemStack() {
            return getItem();
        }

        @Override
        public Item getTabIconItem() {
            return getItem().getItem();
        }
    }
}
