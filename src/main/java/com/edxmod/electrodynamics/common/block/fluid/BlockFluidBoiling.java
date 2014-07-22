package com.edxmod.electrodynamics.common.block.fluid;

import com.edxmod.electrodynamics.common.block.prefab.EDXBasicFluid;
import com.edxmod.electrodynamics.common.lib.EDXProps;
import com.edxmod.electrodynamics.common.lib.client.EnumParticle;
import com.edxmod.electrodynamics.common.particle.SteamFX;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

import java.util.Random;

/**
 * Created by Thlayli
 */
public class BlockFluidBoiling extends EDXBasicFluid {
    EnumParticle particleBubble;

    public BlockFluidBoiling(Fluid fluid, Material material) {
        super(fluid, material);
        setQuantaPerBlock(2);
        particleBubble = EnumParticle.BUBBLE;
    }

    @Override
    public void registerBlockIcons(IIconRegister register) {
        stillIcon = register.registerIcon(EDXProps.RESOURCE_PREFIX + "fluid/boilingWater_still");
        flowingIcon = register.registerIcon(EDXProps.RESOURCE_PREFIX + "fluid/boilingWater_flow");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        super.randomDisplayTick(world, x, y, z, rand);
        particleBubble.display(world, x + rand.nextDouble(), y, z + rand.nextDouble(), 0, 0.5, 0);
        FMLClientHandler.instance().getClient().effectRenderer.addEffect(new SteamFX(world, x + rand.nextDouble(), y + 1, z + rand.nextDouble(), rand.nextInt(2), 0));
    }
}
