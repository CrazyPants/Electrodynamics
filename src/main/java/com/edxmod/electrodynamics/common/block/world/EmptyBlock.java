package com.edxmod.electrodynamics.common.block.world;

import com.edxmod.electrodynamics.client.render.block.RenderEmptyBlock;
import com.edxmod.electrodynamics.common.block.prefab.EDXBasicBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

/**
 * @author Thlayli
 */
public class EmptyBlock extends EDXBasicBlock {
    public EmptyBlock() {
        super(Material.air);
    }

    @Override
    public boolean useCustomRender() {
        return false;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {

    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderType() {
        return RenderEmptyBlock.renderID;
    }

}
