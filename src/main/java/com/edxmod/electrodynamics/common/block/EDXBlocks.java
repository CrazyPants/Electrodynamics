package com.edxmod.electrodynamics.common.block;

import com.edxmod.electrodynamics.common.block.core.BlockSpawnMarker;
import com.edxmod.electrodynamics.common.block.item.ItemBlockCrank;
import com.edxmod.electrodynamics.common.block.item.ItemBlockWaterMill;
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

	// MACHINES
	public static Block table;
	public static Block tableSieve;
	public static Block sinteringOven;
	public static Block hammerMill;
	public static Block crank;
	public static Block kineticCrank;
	public static Block waterMill;
	public static Block kiln;
	public static Block metalPress;
	public static Block barrel;
	public static Block crucible;
	public static Block infernalFurnace;

	public static Block componentGround;
	public static Block componentWood;
	public static Block storage;
	public static Block limestone;
	public static Block netherGrass;
	public static Block spawnMarker;
	public static Block infernalLog;
	public static Block richInfernalLog;
	public static Block infernalLeaves;
	public static Block infernalSapling;

    public static void initialize() {
		table = new BlockTable().setBlockName("table");
		tableSieve = new BlockSieveTable().setBlockName("table_sieve");
		sinteringOven = new BlockSinteringOven().setBlockName("sintering_oven");
		hammerMill = new BlockHammerMill().setBlockName("hammer_mill");
		crank = new BlockCrank().setBlockName("crank");
		kineticCrank = new BlockKineticCrank().setBlockName("kinetic_crank");
		waterMill = new BlockWaterMill().setBlockName("water_mill");
		kiln = new BlockKiln().setBlockName("kiln");
		metalPress = new BlockMetalPress().setBlockName("metal_press");
		barrel = new BlockBarrel().setBlockName("barrel");
		crucible = new BlockCrucible().setBlockName("crucible");
		infernalFurnace = new BlockInfernalFurnace().setBlockName("infernal_furnace");

		componentGround = new BlockComponentGround().setBlockName("component_ground");
		componentWood = new BlockComponentWood().setBlockName("component_wood");
		storage = new BlockStorage().setBlockName("storage");
		limestone = new BlockLimestone().setBlockName("limestone");
		netherGrass = new BlockNetherGrass().setBlockName("nether_grass");
		spawnMarker = new BlockSpawnMarker().setBlockName("spawn_marker");
		infernalLog = new BlockInfernalLog(false).setBlockName("infernal_log");
		richInfernalLog = new BlockInfernalLog(true).setBlockName("infernal_log");
		infernalLeaves = new BlockInfernaLeaves().setBlockName("infernal_leaves");
		infernalSapling = new BlockInfernalSapling().setBlockName("infernal_sapling");

        registerBlock(table, EDXItemMultiBlock.class);
        registerBlock(tableSieve, EDXItemBlock.class);
		registerBlock(sinteringOven, EDXMachineBlock.class);
		registerBlock(hammerMill, EDXMachineBlock.class);
		registerBlock(crank, ItemBlockCrank.class);
		registerBlock(kineticCrank, EDXMachineBlock.class);
		registerBlock(waterMill, ItemBlockWaterMill.class);
		registerBlock(kiln, EDXMachineBlock.class);
		registerBlock(metalPress, EDXMachineBlock.class);
		registerBlock(barrel, EDXItemBlock.class);
		registerBlock(crucible, EDXItemBlock.class);
		registerBlock(infernalFurnace, EDXMachineBlock.class);

		registerBlock(componentGround, EDXItemMultiBlock.class);
		registerBlock(componentWood, EDXItemMultiBlock.class);
		registerBlock(storage, EDXItemMultiBlock.class);
		registerBlock(limestone, EDXItemMultiBlock.class);
		registerBlock(netherGrass, EDXItemBlock.class);
		registerBlock(spawnMarker);
		registerBlock(infernalLog);
		registerBlock(richInfernalLog, "rich_infernal_log");
		registerBlock(infernalLeaves);
		registerBlock(infernalSapling);

		GameRegistry.registerTileEntity(TileTable.class, "edx:table");
		GameRegistry.registerTileEntity(TileSieveTable.class, "edx:table_sieve");
    	GameRegistry.registerTileEntity(TileSinteringOven.class, "edx:sintering_oven");
		GameRegistry.registerTileEntity(TileHammerMill.class, "edx:hammer_mill");
		GameRegistry.registerTileEntity(TileCrank.class, "edx:crank");
		GameRegistry.registerTileEntity(TileKineticCrank.class, "edx:kinetic_crank");
		GameRegistry.registerTileEntity(TileWaterMill.class, "edx:water_mill");
		GameRegistry.registerTileEntity(TileKiln.class, "edx:kiln");
		GameRegistry.registerTileEntity(TileMetalPress.class, "edx:metal_press");
		GameRegistry.registerTileEntity(TileBarrel.class, "edx:barrel");
		GameRegistry.registerTileEntity(TileCrucible.class, "edx:crucible");
		GameRegistry.registerTileEntity(TileInfernalFurnace.class, "edx:infernal_furnace");
		GameRegistry.registerTileEntity(TileSpawnMarker.class, "edx:spawn_marker");
	}

    public static void registerBlock(Block block) {
        GameRegistry.registerBlock(block, block.getUnlocalizedName().replace("tile.", ""));
    }

	public static void registerBlock(Block block, String override) {
		GameRegistry.registerBlock(block, override);
	}

    public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass) {
        GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.", ""));
    }

	public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass, String override) {
		GameRegistry.registerBlock(block, itemBlockClass, override);
	}

    public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass, Object... constructorArgs) {
        GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.", ""), null, constructorArgs);
    }
}
