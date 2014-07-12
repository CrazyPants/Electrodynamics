package com.edxmod.electrodynamics;

import com.edxmod.electrodynamics.client.model.FixedTechneModelLoader;
import com.edxmod.electrodynamics.client.render.item.*;
import com.edxmod.electrodynamics.client.render.tile.*;
import com.edxmod.electrodynamics.common.block.EDXBlocks;
import com.edxmod.electrodynamics.common.tile.machine.*;
import com.edxmod.electrodynamics.common.tile.misc.TileSpawnMarker;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.AdvancedModelLoader;

/**
 * @author Royalixor.
 */
public class ClientProxy extends CommonProxy {

	public static int renderPass;

	@Override
	public void preInit() {
		super.preInit();

		AdvancedModelLoader.registerModelHandler(new FixedTechneModelLoader());

		// BLOCK

		// TILE
		ClientRegistry.bindTileEntitySpecialRenderer(TileTable.class, new RenderTileTable());
		ClientRegistry.bindTileEntitySpecialRenderer(TileSieveTable.class, new RenderTileSieveTable());
		ClientRegistry.bindTileEntitySpecialRenderer(TileSinteringOven.class, new RenderTileSinteringOven());
		ClientRegistry.bindTileEntitySpecialRenderer(TileHammerMill.class, new RenderTileHammerMill());
		ClientRegistry.bindTileEntitySpecialRenderer(TileHandCrank.class, new RenderTileCrank());
		ClientRegistry.bindTileEntitySpecialRenderer(TileKineticCrank.class, new RenderTileKineticCrank());
		ClientRegistry.bindTileEntitySpecialRenderer(TileWaterMill.class, new RenderTileWaterMill());
		ClientRegistry.bindTileEntitySpecialRenderer(TileKiln.class, new RenderTileKiln());
		ClientRegistry.bindTileEntitySpecialRenderer(TileMetalPress.class, new RenderTileMetalPress());
		ClientRegistry.bindTileEntitySpecialRenderer(TileBarrel.class, new RenderTileBarrel());
		ClientRegistry.bindTileEntitySpecialRenderer(TileSpawnMarker.class, new RenderTileSpawnMarker());
		ClientRegistry.bindTileEntitySpecialRenderer(TileMetalShaft.class, new RenderTileMetalShaft());

		// ITEM
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EDXBlocks.table), new RenderItemTable());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EDXBlocks.tableSieve), new RenderItemSieveTable());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EDXBlocks.sinteringOven), new RenderItemSinteringOven());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EDXBlocks.hammerMill), new RenderItemHammerMill());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EDXBlocks.kineticCrank), new RenderItemKineticCrank());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EDXBlocks.kiln), new RenderItemKiln());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EDXBlocks.metalPress), new RenderItemMetalPress());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EDXBlocks.barrel), new RenderItemBarrel());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EDXBlocks.spawnMarker), new RenderItemSpawnMarker());
	}
}
