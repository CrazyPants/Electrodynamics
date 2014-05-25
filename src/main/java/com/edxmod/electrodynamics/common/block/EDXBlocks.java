package com.edxmod.electrodynamics.common.block;

import com.edxmod.electrodynamics.common.block.example.BlockRaytraceExample;
import com.edxmod.electrodynamics.common.block.machine.*;
import com.edxmod.electrodynamics.common.block.prefab.item.EDXItemBlock;
import com.edxmod.electrodynamics.common.block.prefab.item.EDXItemMultiBlock;
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

    public static void initialize() {
		table = new BlockTable().setBlockName("table");
		tableSieve = new BlockSieveTable().setBlockName("table_sieve");

        registerBlock(table, EDXItemMultiBlock.class);
        registerBlock(tableSieve, EDXItemBlock.class);

		GameRegistry.registerTileEntity(TileTable.class, "table");
		GameRegistry.registerTileEntity(TileSieveTable.class, "table_sieve");
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
