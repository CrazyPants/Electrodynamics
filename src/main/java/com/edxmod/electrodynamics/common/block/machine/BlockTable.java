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
public class BlockTable extends EDXTileMultiBlock implements IRaytracable {

    private static final String[] NAMES = new String[]{"wood", "stone"};

	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float fx, float fy, float fz) {
        if (!world.isRemote) {
            TileTable tile = (TileTable) world.getTileEntity(x, y, z);

            if (tile != null) {
                if (!player.isSneaking() && side == 1 && tile.stack == null && player.getHeldItem() != null) {
                    ItemStack stack = player.getHeldItem().copy();
                    stack.stackSize = 1;

					tile.setStack(stack);

                    player.getHeldItem().stackSize--;
                    if (player.getHeldItem().stackSize <= 0) {
                        player.setCurrentItemOrArmor(0, null);
                    }

                    return true;
                } else {
                    RayTracer.RaytraceResult result = RayTracer.doRaytrace(world, x, y, z, player);

                    if (result != null && result.hitID == 1) {
                        if (!player.isSneaking()) {
                            if (!tile.smash(player)) {
								if (tile.stack != null && player.getHeldItem() == null) {
									player.setCurrentItemOrArmor(0, tile.stack.copy());
									tile.stack = null;

									world.markBlockForUpdate(x, y, z);
									return true;
								} else if (tile.stack != null && player.getHeldItem() != null) {
									if (tile.stack.isItemEqual(player.getHeldItem()) && (player.getHeldItem().stackSize + 1 <= player.getHeldItem().getItem().getItemStackLimit(player.getHeldItem()))) {
										player.getHeldItem().stackSize += tile.stack.stackSize;

										if (player.getHeldItem().stackSize > player.getHeldItem().getMaxStackSize()) {
											tile.stack.stackSize = player.getHeldItem().stackSize - player.getHeldItem().getMaxStackSize();
										} else {
											tile.stack = null;
										}

										world.markBlockForUpdate(x, y, z);
										return true;
									}
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

    /* IRAYTRACABLE */
    @Override
    public List<IndexedAABB> getTargets(World world, int x, int y, int z) {
        List<IndexedAABB> targets = new ArrayList<IndexedAABB>();
        TileTable tile = (TileTable) world.getTileEntity(x, y, z);

        float renderMax = tile.getBlockMetadata() == 0 ? RenderTileTable.WOOD_RENDER_MAX : RenderTileTable.STONE_RENDER_MAX;

        // This target takes the place of setBlockBoundsBasedOnState
        targets.add(new IndexedAABB(0, getBlockBoundsMinX(), getBlockBoundsMinY(), getBlockBoundsMinZ(), getBlockBoundsMaxX(), renderMax, getBlockBoundsMaxZ()));

        if (tile.stack != null) {
            if (tile.stack.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(tile.stack.getItem()).getRenderType())) {
                Block block = ItemHelper.getBlock(tile.stack.getItem());
                AxisAlignedBB blockBounds = AxisAlignedBB.getBoundingBox(0.25, renderMax, 0.25, 0.75, renderMax + (block.getBlockBoundsMaxY() / 2), 0.75);
				targets.add(new IndexedAABB(1, blockBounds));
            } else {
                targets.add(new IndexedAABB(1, 0.25, renderMax, 0.25, 0.75, renderMax + 0.0625F, 0.75));
            }
        }

        return targets;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity) {
        // For this example, just sets the bounds to full. This can change
        setBlockBounds(0, 0, 0, 1, 1, 1);
        super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        RayTracer.RaytraceResult result = RayTracer.doRaytrace(world, x, y, z, Minecraft.getMinecraft().thePlayer);

        if (result != null && result.aabb != null) {
            // Returns the resulting bounding box, offset to match the block coordinates
            return result.aabb.offset(x, y, z);
        } else {
            return super.getSelectedBoundingBoxFromPool(world, x, y, z);
        }
    }

    @Override
    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 origin, Vec3 direction) {
        RayTracer.RaytraceResult result = RayTracer.doRaytrace(world, x, y, z, origin, direction);

        if (result == null) {
            return null;
        } else {
            return result.mob;
        }
    }

    @Override
    public MovingObjectPosition raytrace(World world, int x, int y, int z, Vec3 origin, Vec3 direction) {
        return super.collisionRayTrace(world, x, y, z, origin, direction);
    }
}
