package com.edxmod.electrodynamics.common.block.machine;

import com.edxmod.electrodynamics.common.block.prefab.EDXTileMultiBlock;
import com.edxmod.electrodynamics.common.tile.machine.TileCompost;
import com.edxmod.electrodynamics.common.util.ArrayHelper;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Thlayli
 */
public class BlockCompost extends EDXTileMultiBlock {

    private static final String[] NAMES = new String[]{"Wood", "Stone"};

    public BlockCompost() {
        super(Material.wood);
    }

    @Override
    public int[] getSubtypes() {
        return ArrayHelper.getArrayIndexes(NAMES);
    }

    @Override
    public String getNameForType(int type) {
        return ArrayHelper.safeGetArrayIndex(NAMES, type);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileCompost();
    }

    @Override
    public boolean useCustomRender() {
        return true;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {

    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float fx, float fy, float fz) {
        if (!world.isRemote) {
            if (player.isSneaking()) {
                TileCompost tileCompost = (TileCompost) world.getTileEntity(x, y, z);
                if (tileCompost != null) {
                    tileCompost.lidOpen = !tileCompost.lidOpen;
                    tileCompost.sendPoke();
                }
            }
        }
        return player.isSneaking();
    }
}
