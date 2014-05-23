package com.edxmod.electrodynamics.common.item;

import com.edxmod.electrodynamics.Electrodynamics;
import com.edxmod.electrodynamics.common.core.handler.GuiHandler;
import com.edxmod.electrodynamics.common.inventory.InventoryItem;
import com.edxmod.electrodynamics.common.lib.EDXProps;
import com.edxmod.electrodynamics.common.core.EDXCreativeTab;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author Royalixor
 */
public class ItemTool extends Item {

	private static String[] tools = {"spudPeeler", "screwdriver", "sinteringTray", "solderIron", "treeTap", "wireCutters", "wirelessScanner", "handSieve", "mixingJar"};
	private IIcon[] textures;

	public ItemTool() {
		setHasSubtypes(true);
		setMaxStackSize(1);
		setCreativeTab(EDXCreativeTab.TOOLS.get());
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
	public String getUnlocalizedName(ItemStack itemStack) {
		int meta = itemStack.getItemDamage();

		if (meta < 0 || meta >= tools.length) {
			meta = 0;
		}
		return super.getUnlocalizedName() + "." + tools[meta];
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		textures = new IIcon[tools.length];

		for (int i = 0; i < tools.length; ++i) {
			textures[i] = iconRegister.registerIcon(EDXProps.RESOURCE_PREFIX + "tools/" + tools[i]);
		}
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}
		return textures[meta];
	}

	@Override
	public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
		for (int meta = 0; meta < tools.length; ++meta) {
			list.add(new ItemStack(item, 1, meta));
		}
	}
}