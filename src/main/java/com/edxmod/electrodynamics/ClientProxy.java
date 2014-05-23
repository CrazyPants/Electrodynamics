package com.edxmod.electrodynamics;

import com.edxmod.electrodynamics.client.render.item.*;
import com.edxmod.electrodynamics.client.render.tile.*;
import com.edxmod.electrodynamics.common.lib.EDXProps;
import com.edxmod.electrodynamics.common.tile.*;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
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
        ClientRegistry.bindTileEntitySpecialRenderer(TileSinteringOven.class, new RenderTileSinteringOven());
        ClientRegistry.bindTileEntitySpecialRenderer(TileSieveTable.class, new RenderTileSieveTable());

        // ITEM
        MinecraftForgeClient.registerItemRenderer(GameRegistry.findItem(EDXProps.ID, "table"), new RenderItemTable());
        MinecraftForgeClient.registerItemRenderer(GameRegistry.findItem(EDXProps.ID, "sinteringOven"), new RenderItemSinteringOven());
        MinecraftForgeClient.registerItemRenderer(GameRegistry.findItem(EDXProps.ID, "sieveTable"), new RenderItemSieveTable());
    }
}
