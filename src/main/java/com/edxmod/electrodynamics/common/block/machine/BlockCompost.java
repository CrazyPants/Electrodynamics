package com.edxmod.electrodynamics.common.block.machine;

import com.edxmod.electrodynamics.common.block.prefab.EDXTileMultiBlock;
import com.edxmod.electrodynamics.common.tile.machine.TileCompost;
import com.edxmod.electrodynamics.common.util.ArrayHelper;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Thlayli
 */
public class BlockCompost extends EDXTileMultiBlock{

    private static final String[] NAMES = new String[]{"Wood", "Stone"};

    public BlockCompost(){
        super(Material.wood);
    }

    @Override
    public int[] getSubtypes() {
        return ArrayHelper.getArrayIndexes(NAMES);
    }

    @Override
    public String getNameForType(int type) {
        return ArrayHelper.safeGetArrayIndex(NAMES,type);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        TileCompost tileCompost = new TileCompost();
        return new TileCompost();
    }

    @Override
    public boolean useCustomRender() {
        return true;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {

    }
}
