package com.edxmod.electrodynamics.common.fluid.gas;

import net.minecraftforge.fluids.Fluid;

public class FluidGas extends Fluid {

    public FluidGas() {
        super("gasNatural");
        setDensity(-1000);
        setGaseous(true);
    }
}
