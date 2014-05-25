package com.edxmod.electrodynamics;

import com.edxmod.electrodynamics.client.render.item.*;
import com.edxmod.electrodynamics.client.render.tile.*;
import com.edxmod.electrodynamics.common.block.EDXBlocks;
import com.edxmod.electrodynamics.common.lib.EDXProps;
import com.edxmod.electrodynamics.common.tile.*;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author Royalixor.
 */
public class ClientProxy extends CommonProxy {

    public static int renderPass;

    @Override
    public void preInit() {
		super.preInit();
    }

    @Override
    public void registerRenders() {
		super.preInit();

        // BLOCK

        // TILE
        ClientRegistry.bindTileEntitySpecialRenderer(TileTable.class, new RenderTileTable());
		ClientRegistry.bindTileEntitySpecialRenderer(TileSieveTable.class, new RenderTileSieveTable());
		ClientRegistry.bindTileEntitySpecialRenderer(TileSinteringOven.class, new RenderTileSinteringOven());
		ClientRegistry.bindTileEntitySpecialRenderer(TileHammerMill.class, new RenderTileHammerMill());
		ClientRegistry.bindTileEntitySpecialRenderer(TileKiln.class, new RenderTileKiln());
		ClientRegistry.bindTileEntitySpecialRenderer(TileMetalPress.class, new RenderTileMetalPress());

        // ITEM
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EDXBlocks.table), new RenderItemTable());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EDXBlocks.tableSieve), new RenderItemSieveTable());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EDXBlocks.sinteringOven), new RenderItemSinteringOven());
    	MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EDXBlocks.hammerMill), new RenderItemHammerMill());
    	MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EDXBlocks.kiln), new RenderItemKiln());
    	MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EDXBlocks.metalPress), new RenderItemMetalPress());
	}
}
