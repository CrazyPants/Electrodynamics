package com.edxmod.electrodynamics.common.item;

import com.edxmod.electrodynamics.Electrodynamics;
import com.edxmod.electrodynamics.common.core.handler.GuiHandler;
import com.edxmod.electrodynamics.common.inventory.InventoryItem;
import com.edxmod.electrodynamics.common.item.prefab.EDXMultiItem;
import com.edxmod.electrodynamics.common.core.EDXCreativeTab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author Royalixor
 */
public class ItemTool extends EDXMultiItem {

	private static String[] NAMES = {"spudPeeler", "screwdriver", "sinteringTray", "solderIron", "treeTap", "wireCutters", "wirelessScanner", "handSieve", "mixingJar"};
	private IIcon[] textures;

	public ItemTool() {
		super(EDXCreativeTab.TOOLS);

		setMaxStackSize(1);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean idk) {
		switch (stack.getItemDamage()) {
			// Sintering Tray
			case 2: {
				InventoryItem inventory = new InventoryItem(stack, 1);
				ItemStack item = inventory.getStackInSlot(0);

				if (item != null) {
					list.add(item.getDisplayName() + " (" + item.stackSize + ")");
				} else {
					list.add("Empty");
				}

				break;
			}
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			switch (stack.getItemDamage()) {
				// Sintering Tray
				case 2: {
					if (player.isSneaking()) {
						player.openGui(Electrodynamics.instance, GuiHandler.GUI_TRAY, world, 0, 0, 0);
					}
					break;
				}
			}
		}

		return stack;
	}


	@Override
	public String[] getNames() {
		return NAMES;
	}

	@Override
	public String getIconPrefix() {
		return "tool";
	}
}