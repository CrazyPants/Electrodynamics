package com.edxmod.electrodynamics.common.block.prefab.item;

import com.edxmod.electrodynamics.common.tile.core.TileCoreMachine;
import com.edxmod.electrodynamics.common.util.EntityHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author dmillerw
 */
public class EDXMachineBlock extends EDXItemBlock {

    public EDXMachineBlock(Block block) {
        super(block);
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        boolean result = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);

        if (result) {
            TileCoreMachine tile = (TileCoreMachine) world.getTileEntity(x, y, z);

            tile.orientation = EntityHelper.get2DRotation(player).getOpposite();
        }

        return result;
    }
}
