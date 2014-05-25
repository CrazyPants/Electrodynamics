package com.edxmod.electrodynamics.common.block;

import com.edxmod.electrodynamics.common.block.example.BlockRaytraceExample;
import com.edxmod.electrodynamics.common.block.machine.*;
import com.edxmod.electrodynamics.common.block.prefab.item.EDXItemBlock;
import com.edxmod.electrodynamics.common.block.prefab.item.EDXItemMultiBlock;
import com.edxmod.electrodynamics.common.block.prefab.item.EDXMachineBlock;
import com.edxmod.electrodynamics.common.tile.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

/**
 * @author Royalixor.
 */
public class EDXBlocks {

	public static Block table;
	public static Block tableSieve;
	public static Block sinteringOven;
	public static Block hammerMill;
	public static Block kiln;
	public static Block metalPress;

    public static void initialize() {
		table = new BlockTable().setBlockName("table");
		tableSieve = new BlockSieveTable().setBlockName("table_sieve");
		sinteringOven = new BlockSinteringOven().setBlockName("sintering_oven");
		hammerMill = new BlockHammerMill().setBlockName("hammer_mill");
		kiln = new BlockKiln().setBlockName("kiln");
		metalPress = new BlockMetalPress().setBlockName("metal_press");

        registerBlock(table, EDXItemMultiBlock.class);
        registerBlock(tableSieve, EDXItemBlock.class);
		registerBlock(sinteringOven, EDXMachineBlock.class);
		registerBlock(hammerMill, EDXMachineBlock.class);
		registerBlock(kiln, EDXMachineBlock.class);
		registerBlock(metalPress, EDXMachineBlock.class);

		GameRegistry.registerTileEntity(TileTable.class, "edx:table");
		GameRegistry.registerTileEntity(TileSieveTable.class, "edx:table_sieve");
    	GameRegistry.registerTileEntity(TileSinteringOven.class, "edx:sintering_oven");
		GameRegistry.registerTileEntity(TileHammerMill.class, "edx:hammer_mill");
		GameRegistry.registerTileEntity(TileKiln.class, "edx:kiln");
		GameRegistry.registerTileEntity(TileMetalPress.class, "edx:metal_press");
	}

    public static void registerBlock(Block block) {
        GameRegistry.registerBlock(block, block.getUnlocalizedName().replace("tile.", ""));
    }

    public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass) {
        GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.", ""));
    }

    public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass, Object... constructorArgs) {
        GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.", ""), null, constructorArgs);
    }
}
