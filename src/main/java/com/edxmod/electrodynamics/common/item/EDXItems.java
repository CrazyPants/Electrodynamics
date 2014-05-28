package com.edxmod.electrodynamics.common.item;

import com.edxmod.electrodynamics.common.item.resource.ItemResource;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * @author Royalixor
 */
public class EDXItems {

	// HAMMERS
	public static ItemHammer hammerWood;
	public static ItemHammer hammerStone;
	public static ItemHammer hammerIron;
	public static ItemHammer hammerSteel;
	public static ItemHammer hammerDiamond;

	// TOOLS
	public static Item net;
	public static Item handSieve;
	public static Item component;

	// RESOURCES
	public static Item resourceChunk;
	public static Item resourceClump;
	public static Item resourceDust;
	public static Item resourceFlake;
	public static Item resourceIngot;
	public static Item resourceNugget;
	public static Item resourcePebble;

	// OTHER
	public static Item seeds;
	public static Item cosmetic;

    public static void initialize() {
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
		component = new ItemComponent().setUnlocalizedName("component_item");
		registerItem(component);

		resourceChunk = new ItemResource().setType("Chunk");
		resourceClump = new ItemResource().setType("Clump");
		resourceDust = new ItemResource().setType("Dust");
		resourceFlake = new ItemResource().setType("Flakes");
		resourceIngot = new ItemResource().setType("Ingot");
		resourceNugget = new ItemResource().setType("Nugget");
		resourcePebble = new ItemResource().setType("Pebbles");
		registerItem(resourceChunk);
		registerItem(resourceClump);
		registerItem(resourceDust);
		registerItem(resourceFlake);
		registerItem(resourceIngot);
		registerItem(resourceNugget);
		registerItem(resourcePebble);

		seeds = new ItemSeeds().setUnlocalizedName("seeds");
		cosmetic = new ItemCosmetic().setUnlocalizedName("cosmetic");
		registerItem(seeds);
		registerItem(cosmetic);
    }

    public static void registerItem(Item item) {
        GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
    }
}
