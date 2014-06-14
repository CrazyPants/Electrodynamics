package com.edxmod.electrodynamics.common.block.world.gas;

import com.edxmod.electrodynamics.common.fluid.EDXFluids;
import com.edxmod.electrodynamics.common.tabs.EDXCreativeTab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class BlockGas extends BlockFluidClassic {

    Fluid fluidGas;

    public BlockGas() {
        super(EDXFluids.fluidGas, Material.water);
        fluidGas = EDXFluids.fluidGas;
        setCreativeTab(EDXCreativeTab.BLOCKS.get());
    }

    @Override
    public FluidStack drain(World world, int x, int y, int z, boolean doDrain) {
        return null;
    }

    @Override
    public boolean canDrain(World world, int x, int y, int z) {
        return false;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        fluidGas.setIcons(iconRegister.registerIcon("edx:world/gas_still"), iconRegister.registerIcon("edx:world/gas"));
        blockIcon = fluidGas.getFlowingIcon();
    }

    @Override
    public Fluid getFluid() {
        return fluidGas;
    }
}
