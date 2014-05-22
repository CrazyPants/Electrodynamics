package com.edxmod.electrodynamics.common.block;

import com.edxmod.electrodynamics.common.block.example.BlockRaytraceExample;
import com.edxmod.electrodynamics.common.block.machine.*;
import com.edxmod.electrodynamics.common.block.prefab.item.EDXItemBlock;
import com.edxmod.electrodynamics.common.block.prefab.item.EDXItemMultiBlock;
import com.edxmod.electrodynamics.common.block.world.BlockLog;
import com.edxmod.electrodynamics.common.block.world.BlockOre;
import com.edxmod.electrodynamics.common.block.world.EmptyBlock;
import com.edxmod.electrodynamics.common.tile.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

/**
 * @author Royalixor.
 */
public class EDXBlocks {

    public static void init() {
        registerBlocks();
        registerTileEntities();
    }

    private static void registerBlocks() {
        registerBlock(new BlockTable().setBlockName("table"), EDXItemMultiBlock.class);
//        registerBlock(new BlockLimestone().setBlockName("limestone"), EDXItemMultiBlock.class);
//        registerBlock(new BlockOre().setBlockName("ore"), EDXItemMultiBlock.class);
//        registerBlock(new BlockStorage().setBlockName("storage"), EDXItemMultiBlock.class);
//        registerBlock(new BlockLog().setBlockName("log"), EDXItemBlock.class);
//        registerBlock(new BlockBasicKiln().setBlockName("basicKiln"), EDXItemBlock.class);
//        registerBlock(new BlockSinteringOven().setBlockName("sinteringOven"), EDXItemBlock.class);
        registerBlock(new BlockSieveTable().setBlockName("sieveTable"), EDXItemBlock.class);
//        registerBlock(new EmptyBlock().setBlockName("empty"));
    }

    private static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileTable.class, "table");
//        GameRegistry.registerTileEntity(TileBasicKiln.class, "basicKiln");
//        GameRegistry.registerTileEntity(TileSinteringOven.class, "sinteringOven");
        GameRegistry.registerTileEntity(TileSieveTable.class, "sieveTable");
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
