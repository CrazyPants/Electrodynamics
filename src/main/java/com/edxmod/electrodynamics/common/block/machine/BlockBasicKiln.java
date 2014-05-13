package com.edxmod.electrodynamics.common.block.machine;

import com.edxmod.electrodynamics.common.block.prefab.item.EDXTileBlock;
import com.edxmod.electrodynamics.common.inventory.InventoryItem;
import com.edxmod.electrodynamics.common.item.ItemTool;
import com.edxmod.electrodynamics.common.raytrace.IRaytracable;
import com.edxmod.electrodynamics.common.raytrace.IndexedAABB;
import com.edxmod.electrodynamics.common.raytrace.RayTracer;
import com.edxmod.electrodynamics.common.tile.TileBasicKiln;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Royalixor
 */
public class BlockBasicKiln extends EDXTileBlock implements IRaytracable {

    public static final int HIT_MAIN = 0;
    public static final int HIT_DOOR = 1;
    public static final int HIT_INSIDE = 2;

    public BlockBasicKiln() {
        super(Material.iron);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float fx, float fy, float fz) {
        if (!world.isRemote) {
            TileBasicKiln tile = (TileBasicKiln) world.getTileEntity(x, y, z);

            if (tile != null) {
                RayTracer.RaytraceResult result = RayTracer.doRaytrace(world, x, y, z, player);

                if (result.hitID == HIT_DOOR || (result.hitID == HIT_INSIDE && player.isSneaking())) {
                    tile.open = !tile.open;
                    tile.poke();
                    return true;
                } else {
                    if (result.hitID == HIT_INSIDE && tile.open) {
                        ItemStack held = player.getHeldItem();

                        // Is holding tray
                        if (held != null && tile.tray == null && held.getItem() instanceof ItemTool && held.getItemDamage() == 2) {
                            tile.tray = new InventoryItem(held, 1);
                            player.setCurrentItemOrArmor(0, null);
                            world.markBlockForUpdate(x, y, z);
                            return true;
                        } else if (held == null && tile.tray != null) {
                            player.setCurrentItemOrArmor(0, tile.tray.getStack());
                            tile.tray = null;
                            world.markBlockForUpdate(x, y, z);
                        }
                    }
                }
            }
        }

        return player.isSneaking();
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileBasicKiln();
    }

    @Override
    public boolean useCustomRender() {
        return true;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {

    }

    /* IRAYTRACABLE */
    @Override
    public List<IndexedAABB> getTargets(World world, int x, int y, int z) {
        List<IndexedAABB> targets = new ArrayList<IndexedAABB>();
        TileBasicKiln tile = (TileBasicKiln) world.getTileEntity(x, y, z);

        // This target takes the place of setBlockBoundsBasedOnState
        targets.add(new IndexedAABB(HIT_MAIN, 0, 0, 0, 1, 1, 1));

        // Door
        targets.add(new IndexedAABB(HIT_DOOR, 1, 0, 0, 1, 1, 1, 1 - 0.0625));

        if (tile.open) {
            targets.add(new IndexedAABB(HIT_INSIDE, 2, 0.25, 0.25, 0.25, 0.75, 0.75, 0.75 + (0.0625F * 3)));
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
