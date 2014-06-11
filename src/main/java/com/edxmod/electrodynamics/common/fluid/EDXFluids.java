package com.edxmod.electrodynamics.common.fluid;

import com.edxmod.electrodynamics.common.fluid.gas.FluidGas;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class EDXFluids {

    public static Fluid fluidGas;

    public static void init() {
        registerFluids();
    }

    public static void registerFluids() {
        fluidGas = new FluidGas();
        FluidRegistry.registerFluid(fluidGas);
    }
}
