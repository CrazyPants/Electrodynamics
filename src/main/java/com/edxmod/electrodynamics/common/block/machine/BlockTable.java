package com.edxmod.electrodynamics.common.block.machine;

import com.edxmod.electrodynamics.client.render.tile.RenderTileTable;
import com.edxmod.electrodynamics.common.block.prefab.EDXTileMultiBlock;
import com.edxmod.electrodynamics.common.item.ItemHammer;
import com.edxmod.electrodynamics.common.raytrace.IRaytracable;
import com.edxmod.electrodynamics.common.raytrace.IndexedAABB;
import com.edxmod.electrodynamics.common.raytrace.RayTracer;
import com.edxmod.electrodynamics.common.tile.TileTable;
import com.edxmod.electrodynamics.common.util.ArrayHelper;
import com.edxmod.electrodynamics.common.util.ItemHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmillerw
 */
public class BlockTable extends EDXTileMultiBlock {

    private static final String[] NAMES = new String[]{"wood", "stone"};

	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float fx, float fy, float fz) {
        if (!world.isRemote) {
            TileTable tile = (TileTable) world.getTileEntity(x, y, z);

            if (tile != null) {
                if (!player.isSneaking() && side == 1) {
					ItemStack held = player.getHeldItem();

					if (held != null && tile.stack == null) {
						ItemStack stack = held.copy();
						stack.stackSize = 1;

						tile.setStack(stack);

						held.stackSize--;
						if (held.stackSize <= 0) {
							player.setCurrentItemOrArmor(0, null);
						}
					} else {
						if (!tile.smash(player)) {
							if (tile.stack != null && held == null) {
								player.setCurrentItemOrArmor(0, tile.stack.copy());
								tile.setStack(null);
								return true;
							} else if (tile.stack != null && held != null) {
								if (tile.stack.isItemEqual(held) && (held.stackSize + 1 <= held.getItem().getItemStackLimit(held))) {
									held.stackSize += tile.stack.stackSize;

									if (held.stackSize > held.getMaxStackSize()) {
										held.stackSize = held.getMaxStackSize();
										tile.stack.stackSize = held.stackSize - held.getMaxStackSize();
										world.markBlockForUpdate(x, y, z);
									} else {
										tile.setStack(null);
									}

									return true;
								}
							}
						}
					}
				}
            }
        }

        return !player.isSneaking();
    }

    @Override
    public int[] getSubtypes() {
        return ArrayHelper.getArrayIndexes(NAMES.length); // Forces all aspects of this block to base themselves off the NAMES array
    }

    @Override
    public String getNameForType(int type) {
        return NAMES[type];
    }

    @Override
    public boolean useCustomRender() {
        return true;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return Blocks.planks.getIcon(side, meta);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileTable();
    }
}
