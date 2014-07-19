package com.edxmod.electrodynamics.common.block.fluid;

import com.edxmod.electrodynamics.common.block.prefab.EDXBasicFluid;
import com.edxmod.electrodynamics.common.core.EDXCreativeTab;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;

/**
 * Created by Thlayli
 */
public class BlockFluidBoiling extends EDXBasicFluid {

    //TODO add texture and steam particle effects

    public BlockFluidBoiling(Fluid fluid, Material material) {
        super(fluid, material);
        setCreativeTab(EDXCreativeTab.BLOCKS.get());
        setQuantaPerBlock(2);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) {
        stillIcon = Blocks.water.getIcon(0, 0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        return stillIcon;
    }
}
