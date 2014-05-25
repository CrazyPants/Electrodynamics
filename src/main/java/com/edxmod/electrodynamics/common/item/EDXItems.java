package com.edxmod.electrodynamics.common.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * @author Royalixor
 */
public class EDXItems {

	public static ItemHammer hammerWood;
	public static ItemHammer hammerStone;
	public static ItemHammer hammerIron;
	public static ItemHammer hammerSteel;
	public static ItemHammer hammerDiamond;

	public static Item net;

	public static Item handSieve;

    public static void initialize() {
		// HAMMERS
		hammerWood = new ItemHammer(64, "wood", "plankWood");
		hammerStone = new ItemHammer(128, "stone", "cobblestone");
		hammerIron = new ItemHammer(160, "iron", "ingotIron");
		hammerSteel = new ItemHammer(320, "steel", "ingotSteel");
		hammerDiamond = new ItemHammer(640, "diamond", Items.diamond);
		registerItem(hammerWood);
		registerItem(hammerStone);
		registerItem(hammerIron);
		registerItem(hammerSteel);
		registerItem(hammerDiamond);

		net = new ItemNet().setUnlocalizedName("net");
		registerItem(net);

		handSieve = new ItemHandSieve().setUnlocalizedName("hand_sieve");
		registerItem(handSieve);
    }

    public static void registerItem(Item item) {
        GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
    }
}
