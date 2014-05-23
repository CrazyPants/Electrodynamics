package com.edxmod.electrodynamics.common.inventory.container;

import com.edxmod.electrodynamics.common.inventory.InventoryItem;
import com.edxmod.electrodynamics.common.item.ItemHandSieve;
import com.edxmod.electrodynamics.common.recipe.RecipeManager;
import com.edxmod.electrodynamics.common.recipe.manager.SieveManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * @author dmillerw
 */
public class ContainerHandSieve extends ContainerItem {

    private EntityPlayer player;

    public ContainerHandSieve(EntityPlayer player, InventoryItem inventory) {
        super(inventory, player.inventory.currentItem + 27 + inventory.getSizeInventory());

        this.player = player;

        // Init slots
        addSlotToContainer(new Slot(inventory, 0, 80, 35) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return RecipeManager.INSTANCE.sieve.get(stack) != null;
			}

			@Override
			public void onSlotChanged() {
				ItemStack stack1 = getStack();

				if (stack1 != null) {
					SieveManager.SieveRecipe recipe = RecipeManager.INSTANCE.sieve.get(stack1);

					ItemHandSieve.setCurrentDuration(((InventoryItem) inventory).getStack(), 0);
					ItemHandSieve.setMaxDuration(((InventoryItem) inventory).getStack(), recipe.getDuration());
				}
			}
		});

        // Init player slots
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

}
