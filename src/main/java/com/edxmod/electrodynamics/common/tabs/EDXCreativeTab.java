package com.edxmod.electrodynamics.common.tabs;

import com.edxmod.electrodynamics.api.EDXBlockHelper;
import com.edxmod.electrodynamics.api.EDXItemHelper;
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
                return new ItemStack(EDXBlockHelper.get("table"), 1, 1);
            case ITEMS:
                return new ItemStack(EDXItemHelper.get("hammer"), 1, 1);
            case TOOLS:
                return new ItemStack(EDXItemHelper.get("hammer"), 1, 1);
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
