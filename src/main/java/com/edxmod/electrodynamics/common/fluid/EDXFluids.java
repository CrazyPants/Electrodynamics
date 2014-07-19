package com.edxmod.electrodynamics.common.fluid;

import com.edxmod.electrodynamics.common.block.EDXBlocks;
import com.edxmod.electrodynamics.common.core.handler.BucketHandler;
import com.edxmod.electrodynamics.common.item.EDXItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * Created by Thlayli
 */
public class EDXFluids {

    public static Fluid boilingWaterFluid;

    public static void initializeFluids(){
        boilingWaterFluid = new Fluid("boiling");
        FluidRegistry.registerFluid(boilingWaterFluid);
    }

    public static void initializeFluidContainers(){
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("boiling", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(EDXItems.boilingBucket), new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(EDXBlocks.boilingWater,EDXItems.boilingBucket);
    }
}
