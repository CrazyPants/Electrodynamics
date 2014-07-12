package com.edxmod.electrodynamics.common.core.handler;

import com.edxmod.electrodynamics.client.gui.GuiHandSieve;
import com.edxmod.electrodynamics.client.gui.GuiInfernalFurnace;
import com.edxmod.electrodynamics.client.gui.GuiTray;
import com.edxmod.electrodynamics.common.inventory.InventoryItem;
import com.edxmod.electrodynamics.common.inventory.container.ContainerHandSieve;
import com.edxmod.electrodynamics.common.inventory.container.ContainerInfernalFurnace;
import com.edxmod.electrodynamics.common.inventory.container.ContainerTray;
import com.edxmod.electrodynamics.common.tile.machine.TileInfernalFurnace;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author dmillerw
 */
public class GuiHandler implements IGuiHandler {

    public static final int GUI_TRAY = 0;
	public static final int GUI_HAND_SIEVE = 1;
	public static final int GUI_INFERNAL_FURNACE = 2;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GUI_TRAY:
                return new ContainerTray(player, new InventoryItem(player.getHeldItem(), 1));

			case GUI_HAND_SIEVE:
				return new ContainerHandSieve(player, new InventoryItem(player.getHeldItem(), 1));

			case GUI_INFERNAL_FURNACE:
				return new ContainerInfernalFurnace(player, (TileInfernalFurnace) world.getTileEntity(x, y, z));
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GUI_TRAY:
                return new GuiTray(player, new InventoryItem(player.getHeldItem(), 1));

			case GUI_HAND_SIEVE:
				return new GuiHandSieve(player, new InventoryItem(player.getHeldItem(), 1));

			case GUI_INFERNAL_FURNACE:
				return new GuiInfernalFurnace(player, (TileInfernalFurnace) world.getTileEntity(x, y, z));
        }

        return null;
    }

}
