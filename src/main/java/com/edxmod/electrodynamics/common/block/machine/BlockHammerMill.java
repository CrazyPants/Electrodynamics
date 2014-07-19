package com.edxmod.electrodynamics.common.block.machine;

import com.edxmod.electrodynamics.common.block.prefab.EDXTileBlock;
import com.edxmod.electrodynamics.common.tile.machine.TileHammerMill;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

/**
 * @author dmillerw
 */
public class BlockHammerMill extends EDXTileBlock {

    public BlockHammerMill() {
        super(Material.iron);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float fx, float fy, float fz) {
        TileHammerMill tile = (TileHammerMill) world.getTileEntity(x, y, z);
        if (side == tile.orientation.ordinal()) {
            if (!world.isRemote) {
                tile.updateStage();
                player.addChatComponentMessage(new ChatComponentText(String.format(StatCollector.translateToLocal("chat.hammer_mill.stage"), tile.grindingStage + 1)));
            }
            return true;
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileHammerMill();
    }

    @Override
    public boolean useCustomRender() {
        return true;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {

    }
}
