package com.edxmod.electrodynamics.common.world.gen.feature;

import com.edxmod.electrodynamics.api.EDXBlockHelper;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

/**
 * @author M3gaFr3ak.
 */
public class GenWolframite implements IWorldGenerator {

    int count = 5;
    Block oreBlock;

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        oreBlock = EDXBlockHelper.get("ore");
        for (int i = 0; i < count; i++) {
            int x = chunkX * 16 + random.nextInt(16);
            int z = chunkZ * 16 + random.nextInt(16);
            int y = getFirstInstanceOfBlock(world, x, z, Blocks.stone, 32767);
            ForgeDirection[] sideDirs = {ForgeDirection.NORTH, ForgeDirection.WEST, ForgeDirection.SOUTH, ForgeDirection.EAST};

            if ((y != -1) && (y < 255)) {
                for (ForgeDirection dir : sideDirs) {
                    int sX = x + dir.offsetX;
                    int sZ = z + dir.offsetZ;

                    if (matches(world, sX, y, sZ, Blocks.stone, 32767)) {
                        break;
                    }
                }
                y = 5;
                new WorldGenMinable(oreBlock, 6, count, Blocks.stone).generate(world, random, x, y, z);
            }
        }
    }

    private boolean matches(World world, int x, int y, int z, Block block, int meta) {
        return world.getBlock(x, y, z) == block && ((meta == 32767) || world.getBlockMetadata(x, y, z) == meta);
    }

    private int getFirstInstanceOfBlock(World world, int x, int z, Block block, int meta) {
        int maxHeight = world.getHeightValue(x, z);
        for (int y = 0; y < maxHeight; y++) {
            if (matches(world, x, y, z, block, meta)) {
                return y;
            }
        }
        return -1;
    }
}
