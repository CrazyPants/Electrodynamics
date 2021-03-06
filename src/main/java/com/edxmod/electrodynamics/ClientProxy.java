package com.edxmod.electrodynamics;

import com.edxmod.electrodynamics.client.render.block.RenderBlockOre;
import com.edxmod.electrodynamics.client.render.block.RenderEmptyBlock;
import com.edxmod.electrodynamics.client.render.item.*;
import com.edxmod.electrodynamics.client.render.tile.*;
import com.edxmod.electrodynamics.common.core.handler.DynamicOreCache;
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
    public ClientProxy preInit() {
        FMLCommonHandler.instance().bus().register(DynamicOreCache.INSTANCE);
        return this;
    }

    @Override
    public ClientProxy registerRenders() {
        // BLOCK
        RenderingRegistry.registerBlockHandler(new RenderBlockOre());
        RenderingRegistry.registerBlockHandler(new RenderEmptyBlock());

        // TILE
        ClientRegistry.bindTileEntitySpecialRenderer(TileTable.class, new RenderTileTable());
        ClientRegistry.bindTileEntitySpecialRenderer(TileHammerMill.class, new RenderTileHammerMill());
        ClientRegistry.bindTileEntitySpecialRenderer(TileBasicKiln.class, new RenderTileBasicKiln());
        ClientRegistry.bindTileEntitySpecialRenderer(TileDustMixer.class, new RenderTileDustMixer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileMetalPress.class, new RenderTileMetalPress());
        ClientRegistry.bindTileEntitySpecialRenderer(TileSinteringOven.class, new RenderTileSinteringOven());
        ClientRegistry.bindTileEntitySpecialRenderer(TileSieveTable.class, new RenderTileSieveTable());

        // ITEM
        MinecraftForgeClient.registerItemRenderer(GameRegistry.findItem(EDXProps.ID, "table"), new RenderItemTable());
        MinecraftForgeClient.registerItemRenderer(GameRegistry.findItem(EDXProps.ID, "hammerMill"), new RenderItemHammerMill());
        MinecraftForgeClient.registerItemRenderer(GameRegistry.findItem(EDXProps.ID, "basicKiln"), new RenderItemBasicKiln());
        MinecraftForgeClient.registerItemRenderer(GameRegistry.findItem(EDXProps.ID, "dustMixer"), new RenderItemDustMixer());
        MinecraftForgeClient.registerItemRenderer(GameRegistry.findItem(EDXProps.ID, "metalPress"), new RenderItemMetalPress());
        MinecraftForgeClient.registerItemRenderer(GameRegistry.findItem(EDXProps.ID, "sinteringOven"), new RenderItemSinteringOven());
        MinecraftForgeClient.registerItemRenderer(GameRegistry.findItem(EDXProps.ID, "sieveTable"), new RenderItemSieveTable());

        // For sledgehammer
        // MinecraftForgeClient.registerItemRenderer(EDXItemHelper.get("hammer"), new RenderItemHammer());

        return this;
    }
}
